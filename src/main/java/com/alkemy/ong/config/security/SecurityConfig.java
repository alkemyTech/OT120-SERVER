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
                .antMatchers(allAuthorized).permitAll()
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
    
    final String[] allAuthorized = {
            "/auth/**",
            "/users/auth/**"
    };
    
    final String[] adminGetAuthorized = {
    		"/slides/**",
    		"/news/**",
    		"/users",
    		"/contact",
    		"/organization/public/**",
    		"/comments"
    };
    
    final String[] adminPostAuthorized = {
    		"/categories",
    		"/news/**",
    		"/testimonials"  	
    };
    
    final String[] adminPutAuthorized = {
    		"/news/{id}",
    		"/organization/public/{id}",
    		"/categories/{id}",
    		"/testimonials/{id}",
    		"/activities/{id}",
    		"/slides/{id}",
    		"/comments/**"
    };
    
    final String[] adminDeleteAuthorized = {
    		"/categories/**",
    		"/testimonials/**",
    		"/members/**",
    		"/slides/**",
    		"/comments/**"
    };
    
    final String[] userPostAuthorized = {
    		"/members",
    		"/contacts/**"
    };
    
    final String[] userPutAuthorized = {
    		"/members/{id}",
    		"/comments/**"
    };
    
    final String[] userDeleteAuthorized = {
    		"/categories/**",
    		"/users/**",
    		"/testimonials/**",
    		"/members/**",
    		"/comments/**"
    };

}
