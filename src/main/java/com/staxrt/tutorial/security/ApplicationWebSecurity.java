package com.staxrt.tutorial.security;

import com.staxrt.tutorial.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author Givantha Kalansuriya
 * @Project spring-boot-rest-api-auth-jwt-tutorial
 */
@EnableWebSecurity
public class ApplicationWebSecurity extends WebSecurityConfigurerAdapter {

    public static final String SIGN_UP_URL = "/api/v1/users";

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationWebSecurity(UserDetailServiceImpl userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailService = userDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests() // Add a new custom security filter
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll() // Only Allow Permission for create user endpoint
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))// Add JWT Authentication Filter
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))// Add JWT Authorization Filter
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues()); //Allow/restrict our CORS permitting requests from any source
        return source;
    }
}
