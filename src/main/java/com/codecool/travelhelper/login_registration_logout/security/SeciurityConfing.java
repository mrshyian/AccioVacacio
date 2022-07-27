package com.codecool.travelhelper.login_registration_logout.security;

import lombok.RequiredArgsConstructor;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SeciurityConfing extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("1 configure");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("2 configure");
        ThAuthenticationFilter thAuthenticationFilter = new ThAuthenticationFilter(authenticationManagerBean());
        thAuthenticationFilter.setFilterProcessesUrl("/app/login");
        http.cors();
        http.csrf().disable(); // po sprawdzeniu postmanem usunąć tą linijkę kodu
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/","/login/**","/refreshToken/**","/app/login/**","/registration/**","/userpage/**").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/auth/users/**").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/admin/user/save/**").hasAnyRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(thAuthenticationFilter);
        http.addFilterBefore(new ThAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        System.out.println(" 3 authenticationManagerBean");
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        System.out.println(" 4 corsConfigurationSource");
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins((List.of("http://localhost:3000")));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization","Cache-Control","Content-Type","Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
        configuration.setAllowedMethods(List.of("GET","PUT","POST","DELETE","HEAD","OPTIONS","PATCH"));
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);
        return urlBasedCorsConfigurationSource;
    }
}
