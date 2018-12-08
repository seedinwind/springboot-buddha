package org.buddha.wise.auth.service;


import org.buddha.wise.auth.model.AuthUser;
import org.buddha.wise.auth.model.User;
import org.buddha.wise.auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by seedinwind on 18/5/17.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", username));
        } else {
            //这里返回上面继承了 UserDetails  接口的用户类,为了简单我们写个工厂类
            return AuthUser.create(user);
        }
    }
}
