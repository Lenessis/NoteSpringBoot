package com.note.noteproject2.service;

import com.note.noteproject2.model.Role;
import com.note.noteproject2.model.User;
import com.note.noteproject2.repository.RoleRepository;
import com.note.noteproject2.repository.UserRepository;
import com.note.noteproject2.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {

        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if(optional.isPresent())
            user = optional.get();
        else
            throw new RuntimeException("User for id: " + id +" not found!");

        return user;
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User save(UserRegistrationDto userDTO) {

        User user = new User(userDTO.getName(),
                            userDTO.getSurname(),
                            userDTO.getLogin(),
                            passwordEncoder.encode(userDTO.getPassword()),
                            userDTO.getAge(),
                            Arrays.asList(this.roleRepository.findByName("LIMITED_USER")));

        return userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        this.userRepository.save(user);
    }


    // --- Finding if user with specific login exists
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(username);

        if(user == null)
        {
            throw new UsernameNotFoundException("Błędny login lub hasło");
        }

        System.out.println(mapRolesToAuthorities(user));
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), mapRolesToAuthorities(user));

    }

    private Collection <? extends GrantedAuthority> mapRolesToAuthorities(User user)
    {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getRole()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        System.out.println(authorities);
        return authorities;
    }

    /*@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }*/
}
