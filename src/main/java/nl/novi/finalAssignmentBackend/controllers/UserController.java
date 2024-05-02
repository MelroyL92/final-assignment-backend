package nl.novi.finalAssignmentBackend.controllers;


import nl.novi.finalAssignmentBackend.Service.UploadOrderService;
import nl.novi.finalAssignmentBackend.Service.UserService;
import nl.novi.finalAssignmentBackend.dtos.user.UserDto;
import nl.novi.finalAssignmentBackend.entities.UploadOrder;
import nl.novi.finalAssignmentBackend.entities.User;
import nl.novi.finalAssignmentBackend.exceptions.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
    @RestController
    @RequestMapping(value = "/users")
    public class UserController {

        private final UserService userService;
        private final UploadOrderService uploadOrderService;

        public UserController(UserService userService, UploadOrderService uploadOrderService) {
            this.userService = userService;
            this.uploadOrderService = uploadOrderService;
        }


        @GetMapping(value = "")
        public ResponseEntity<List<UserDto>> getUsers() {

            List<UserDto> userDtos = userService.getUsers();

            return ResponseEntity.ok().body(userDtos);
        }

        @GetMapping(value = "/{username}")
        public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {

            UserDto optionalUser = userService.getUser(username);


            return ResponseEntity.ok().body(optionalUser);

        }

        @PostMapping(value = "")
        public ResponseEntity<UserDto> createCustomer(@RequestBody UserDto dto) {


            String newUsername = userService.createUser(dto);
            userService.addAuthority(newUsername, "ROLE_USER");

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                    .buildAndExpand(newUsername).toUri();

            return ResponseEntity.created(location).build();

        }

        @PutMapping(value = "/{username}")
        public ResponseEntity<UserDto> updateCustomer(@PathVariable("username") String username, @RequestBody UserDto dto) {

            userService.updateUser(username, dto);

            return ResponseEntity.noContent().build();
        }

        @PutMapping("/{username}/orders/{orderId}")
        public ResponseEntity<String>addUserToOrder(@PathVariable String username, @PathVariable  Long orderId) {
            userService.addUserToOrder(username, orderId);
            return ResponseEntity.ok("the user with id " + username + " has been assigned to invoice " + orderId);
        }

        @PutMapping("/{username}/shoppinglists/{shoppingListId}")
        public ResponseEntity<String>addUserToShoppingList(@PathVariable String username, @PathVariable Long shoppingListId){
            userService.addUserToShoppingList(username,shoppingListId);
            return ResponseEntity.ok("the user with id " + username + " has been assigned to the shoppinglist");
        }


        @DeleteMapping(value = "/{username}")
        public ResponseEntity<Object> deleteCustomer(@PathVariable("username") String username) {
            userService.deleteUser(username);
            return ResponseEntity.noContent().build();
        }

        @GetMapping(value = "/{username}/authorities")
        public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
            return ResponseEntity.ok().body(userService.getAuthorities(username));
        }

        @PostMapping(value = "/{username}/authorities")
        public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
            }

            @PostMapping("/{username}/uploadOrder")
            public ResponseEntity<User>addOrderFileToUser(@PathVariable("username")String username, @RequestBody MultipartFile file) throws IOException {
             String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                     .path("/users/{username}/uploadOrder")
                     .buildAndExpand(username)
                     .toUriString();

                UploadOrder uploadOrder = uploadOrderService.storeFile(file, url);
                User user = userService.addOrderFile(username, uploadOrder);

                return ResponseEntity.created(URI.create(url)).body(user);
                }


         @GetMapping("/{username}/uploadOrder")
         public ResponseEntity<byte[]>getUploadedOrder(@PathVariable("username")String username){

            UploadOrder uploadOrder = userService.getUploadedOrderFromUser(username);

             MediaType mediaType;

             try{
                 mediaType = MediaType.parseMediaType(uploadOrder.getContentType());
             } catch (InvalidMediaTypeException ignore){
                 mediaType = MediaType.APPLICATION_OCTET_STREAM;
             }

             return ResponseEntity
                     .ok()
                     .contentType(mediaType)
                     .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + uploadOrder.getTitle())
                     .body(uploadOrder.getContents());
         }


        @DeleteMapping(value = "/{username}/authorities/{authority}")
        public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
            userService.removeAuthority(username, authority);
            return ResponseEntity.noContent().build();
        }

    }
