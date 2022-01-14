package com.alkemy.ong.config.security;

import com.alkemy.ong.config.ApplicationRole;
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

    String[] adminAuthorized = {

            "/auth/me",
            "/users/auth/register"
    };

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
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/members").hasRole(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.PUT, "/members/{id}").hasRole(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.GET, "/slides/**").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/news/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/organization/public/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/news/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, "/categories/**").hasAnyRole(ApplicationRole.ADMIN.getName(), ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.POST, "/categories").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/users").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/news/**").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/contact").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/members").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/members/page").hasRole(ApplicationRole.USER.getName())
                .antMatchers(adminAuthorized).permitAll()
                .antMatchers(HttpMethod.GET,"/organization/public/**").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/categories").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/contacts/**").hasRole(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.POST, "/news/**").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/categories/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/testimonials/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/activities/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/users").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/slides/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/slides/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/testimonials").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/users/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/news/{id}").hasRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, "/categories/**")
                .hasAnyRole(ApplicationRole.ADMIN.getName(), ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.DELETE, "/users/**")
                .hasAnyRole(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.DELETE, "/testimonials/**")
                .hasAnyRole(ApplicationRole.ADMIN.getName(), ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.DELETE, "/members/**")
                .hasAnyRole(ApplicationRole.ADMIN.getName(), ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.DELETE, "/slides/**")
                .hasAnyRole(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, "/comments/**").hasAnyRole(ApplicationRole.ADMIN.getName(), ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.GET,"/comments").hasAnyRole(ApplicationRole.ADMIN.getName())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }
}

