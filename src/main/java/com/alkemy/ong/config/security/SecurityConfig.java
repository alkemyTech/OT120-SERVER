package com.alkemy.ong.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alkemy.ong.config.ApplicationRole;

@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.csrf()
                .disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(unsecured).permitAll()
                .antMatchers(HttpMethod.PUT, adminAndUserPutAuthorized).hasAnyAuthority(ApplicationRole.ADMIN.getName(), ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.DELETE, adminAndUserDeleteAuthorized).hasAnyAuthority(ApplicationRole.ADMIN.getName(), ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.POST, userPostAuthorized).hasAuthority(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.PUT, userPutAuthorized).hasAuthority(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.DELETE, userDeleteAuthorized).hasAuthority(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.GET, adminGetAuthorized).hasAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, adminPostAuthorized).hasAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, adminPutAuthorized).hasAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, adminDeleteAuthorized).hasAuthority(ApplicationRole.ADMIN.getName())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }
        
    final String[] unsecured = {
            "/auth/**",
            "/users/auth/**"
    };
    final String[] adminAndUserPutAuthorized = {
    		"/comments/{id}"
    };
    
    final String[] adminAndUserDeleteAuthorized = {
    		"/categories/{id}",
    		"/comments/{id}",
    		"/members/{id}",
    		"/testimonials/{id}"

    };
    
    final String[] adminGetAuthorized = {
    		"/comments",
    		"/contact",
    		"/news/{id}",
     		"/organization/public/{id}",
    		"/slides/{id}",
    		"/users"
    };
    
    final String[] adminPostAuthorized = {
    		"/categories",
    		"/testimonials"  	
    };
    
    final String[] adminPutAuthorized = {
    		"/slides/{id}",
    };
    
    final String[] adminDeleteAuthorized = {
    		"/slides/{id}",
    };
    
    final String[] userPostAuthorized = {
    		"/contacts/{id}",
    		"/members"

    };
    
    final String[] userPutAuthorized = {
    		"/members/{id}"
    };
    
    final String[] userDeleteAuthorized = {
    		"/users/{id}"
    };

}
