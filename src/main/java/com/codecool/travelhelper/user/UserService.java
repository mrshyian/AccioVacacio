package com.codecool.travelhelper.user;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private LoginImpl loginImpl;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUserTable user = userRepository.findByUserEMail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
//        String ROLE_PREFIX = "ROLE_";
        Collection<SimpleGrantedAuthority> roles= new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));
        roles.add(new SimpleGrantedAuthority(user.getRole()));


        return new User(user.getUserEMail(), user.getPassword(), roles);
    }

    public MyUserTable saveUserToBD(MyUserTable user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public MyUserTable getUser(String email){
        return userRepository.findByUserEMail(email);
    }

    public MyUserTable getUserById(String id){
        return userRepository.findMyUserTableById(Long.valueOf(id));
    }

    public List<MyUserTable> getUsers(){
        return userRepository.findAll();
    }

    public void setUserRole(String email, String roleName){
        MyUserTable user= userRepository.findByUserEMail(email);
        user.setRole(roleName);
    }

    public LoginImpl getLoginImpl(){
        return this.loginImpl;
    }


}
