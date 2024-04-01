package nl.novi.finalAssignmentBackend.Service;


import java.util.ArrayList;


/*TODO annotatie*/
public class CustomUserDetailsService implements UserDetailsService {

    /*TODO inject userservice */



    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.getUser(username);


        String password = userDto.getPassword();

        Set<Authority> authorities = userDto.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }

}