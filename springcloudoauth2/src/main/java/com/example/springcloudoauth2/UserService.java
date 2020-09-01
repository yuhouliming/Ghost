package com.example.springcloudoauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private List<User> userList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData(){
        String passWord = passwordEncoder.encode("9527");
        userList = new ArrayList<>();
        userList.add(new User("macro", passWord, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("andy", passWord, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("mark", passWord, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));

    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(userName)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(findUserList)){
            return findUserList.get(0);
        }else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }
}
