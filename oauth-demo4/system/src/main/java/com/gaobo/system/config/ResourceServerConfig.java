package com.gaobo.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 控制每个url的访问限制， 有的放行，有的必须认证才可以访问
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Autowired
    DefaultTokenServices tokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(false);
        resources.tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/actuator/health","/v2/api-docs/**", "/swagger-resources/**", "/swagger-ui.html/**","/webjars/**").permitAll().anyRequest().authenticated();

        //添加过滤器
        http.addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(new AtLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new AfterLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
