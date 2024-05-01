package nl.novi.finalAssignmentBackend.Service;


import jakarta.transaction.Transactional;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.Repository.UserRepository;
import nl.novi.finalAssignmentBackend.dtos.user.UserDto;
import nl.novi.finalAssignmentBackend.entities.*;
import nl.novi.finalAssignmentBackend.exceptions.BadRequestException;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.exceptions.UsernameNotFoundException;
import nl.novi.finalAssignmentBackend.utils.RandomStringGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, ShoppingListRepository shoppingListRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
    }


    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = userRepository.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
       userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public void addOrderToUser(String username, Long orderId){
        User user = userRepository.findById(username).orElseThrow(()->new UsernameNotFoundException(username));
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new RecordNotFoundException("order with id " + orderId + " does not exist."));

        if (order.getUser() != null) {
            throw new BadRequestException("User " + username + " is already associated with an order.");
        }
            order.setUser(user);
            orderRepository.save(order);
    }

    @Transactional
    public User addOrderFile(String username, UploadOrder uploadOrder){
        Optional<User> optionalUser = userRepository.findById(username); // big question if this will even work
        if(optionalUser.isEmpty()){
            throw new RecordNotFoundException("user not found");
        }
        User user = optionalUser.get();
        user.setUploadOrder(uploadOrder);
        return userRepository.save(user);

    }

    @Transactional

    public UploadOrder getUploadedOrderFromUser (String username){
        Optional<User> optionalUser = userRepository.findById(username);
        if(optionalUser.isEmpty()){
            throw new RecordNotFoundException("student with name " + username + " has not been found");
        }
        return optionalUser.get().getUploadOrder();
    }

    public void addUserToShoppingList(String username, Long shoppingListId){
        User user = userRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException(username));
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(()-> new RecordNotFoundException("shopping list with id " + shoppingListId + " does not exist"));

        if (shoppingList.getUser()!= null) {
            throw new BadRequestException();
        }

        shoppingList.setUser(user);
        shoppingListRepository.save(shoppingList);
    }

}
