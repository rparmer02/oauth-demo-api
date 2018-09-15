package com.rparmer.sample.security.config;

import com.rparmer.sample.properties.AuthProperties;
import com.rparmer.sample.security.github.GithubAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private GithubAuthenticationFilter githubAuthenticationFilter;

    @Autowired
    private AuthProperties authProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user").hasAuthority(authProperties.getGithubAdminOrg())
                .anyRequest().authenticated()
            .and()
                .addFilterBefore(githubAuthenticationFilter, BasicAuthenticationFilter.class);
    }
}
