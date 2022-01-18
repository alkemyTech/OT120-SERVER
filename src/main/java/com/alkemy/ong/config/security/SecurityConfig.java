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

    private static final String[] publicEndpoint = {
            "/swagger-resources/**",
            "/swagger-ui/**", "/v2/api-docs",
            "/v3/api-docs",
            "/api/docs",
            "/api/docs/**",
            "/api/docs/swagger-ui",
            "/swagger-ui.html",
            "/**/swagger-ui/**",
            "/auth/**",
            "/auth/me",
            "/users/auth/register",
            "/users/auth/**",
            "/news/{id}/comments",
            "/swagger-ui"
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

                .antMatchers(publicEndpoint).permitAll()

                .antMatchers(HttpMethod.PUT, "/activities/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .antMatchers(HttpMethod.GET,"/categories").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/categories").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/categories/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .antMatchers(HttpMethod.DELETE, "/comments/**").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET,    "/comments").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .antMatchers(HttpMethod.GET, "/contact").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/contacts/**").hasAnyAuthority(ApplicationRole.USER.getName())

                .antMatchers(HttpMethod.POST, "/members").hasAnyAuthority(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.PUT, "/members/{id}").hasAnyAuthority(ApplicationRole.USER.getName())
                .antMatchers(HttpMethod.GET, "/members").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/members/page").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, "/members/**").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .antMatchers(HttpMethod.PUT, "/news/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/news/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/news/**").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/news/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .antMatchers(HttpMethod.PUT, "/organization/public/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET,"/organization/public/**").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .antMatchers(HttpMethod.PUT, "/slides/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/slides/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/slides/**").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/slides").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, "/slides/**").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .antMatchers(HttpMethod.PUT, "/testimonials/{id}").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.POST, "/testimonials").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, "/testimonials/**").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .antMatchers(HttpMethod.GET, "/users").hasAnyAuthority(ApplicationRole.ADMIN.getName())
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority(ApplicationRole.ADMIN.getName())

                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }
}
