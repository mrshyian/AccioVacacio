package com.codecool.travelhelper.user;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
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


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUserTable user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        Collection<SimpleGrantedAuthority> roles= new ArrayList<>();
        user.getRole().forEach(role ->{
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(user.getUserEMail(), user.getPassword(), roles);
    }

    public MyUserTable saveUserToBD(MyUserTable user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserRoleTable saveRoleToBD(UserRoleTable role){
        return roleRepository.save(role);
    }

    public MyUserTable getUser(String email){
        return userRepository.findByEmail(email);
    }

    public List<MyUserTable> getUsers(){
        return userRepository.findAll();
    }

    public void setUserRole(String email, String roleName){
        MyUserTable user= userRepository.findByEmail(email);
        UserRoleTable role = roleRepository.findByName(roleName);
        user.getRole().add(role);
    }


}
