package com.gaobo.oss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/actuator/health","/v2/api-docs/**", "/swagger-resources/**", "/swagger-ui.html/**","/webjars/**").permitAll().antMatchers(HttpMethod.GET,"/files/**").permitAll().anyRequest().authenticated();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("ojfl%loLU938234%$12!*^");
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }
}
