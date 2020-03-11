package com.gaobo.system.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 验证登录用户名和密码的地方。可以调用数据库写逻辑。可以添加角色，
 */
@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("loadUserByUsername {}",username);
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //角色前面加上ROLE_,这个地方同时可以配置权限，这是为了区别权限才加的。
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_admin"));

        if(!StringUtils.isEmpty(username)){
            if(!"gaobo".equals(username)){
                throw new UsernameNotFoundException("not found");
            }else {
                return new User(username, passwordEncoder.encode("123456"), grantedAuthorities);
            }
        }
        return null;
    }
}
