package com.note.noteproject2.service;

import com.note.noteproject2.model.Role;
import com.note.noteproject2.model.User;
import com.note.noteproject2.repository.RoleRepository;
import com.note.noteproject2.repository.UserRepository;
import com.note.noteproject2.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*public UserServiceImpl (UserRepository userRepository)
    {
        super();
        this.userRepository = userRepository;
    }*/

    @Override
    public User save(UserRegistrationDto userDTO) {

        /*ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));*/

        User user = new User(userDTO.getName(),
                            userDTO.getSurname(),
                            userDTO.getLogin(),
                            passwordEncoder.encode(userDTO.getPassword()),
                            userDTO.getAge(),
                            /*roles);*/
                            Arrays.asList(new Role("USER")));

        return userRepository.save(user);
    }


    // --- Finding if user with specific login exists
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(username);

        if(user == null)
        {
            throw new UsernameNotFoundException("Błędny login lub hasło");
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    /*@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }*/
}
