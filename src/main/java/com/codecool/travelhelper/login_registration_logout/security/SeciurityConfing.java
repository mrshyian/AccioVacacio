package com.codecool.travelhelper.login_registration_logout.security;

import com.codecool.travelhelper.user.UserService;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
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
    private final UserService userService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ThAuthenticationFilter thAuthenticationFilter = new ThAuthenticationFilter(authenticationManagerBean(), userService);
        thAuthenticationFilter.setFilterProcessesUrl("/app/login");
        http.cors();
        http.csrf().disable(); //TODO create csrf token
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        http.antMatchers(HttpMethod.GET, "/index*", "/static/**", "/*.js", "/*.json", "/*.ico", "/rest").permitAll()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers(
//                "/usermainbar",
//                "/auth/refreshToken",
//                "/get_friend_by_nick/**",
//                "/placewanttogo",
//                "/photos",
//                "/notes",
//                "/search_friend/**",
//                "/search_friend/id/**",
//                "/add_friend/**",
//                "/remove_friend/**",
//                "/mail_to_friend/new_message_in_chat",
//                "/mail_to_friend/all_chats",
//                "/mail_to_friend/**",
//                "/mail_to_friend/messages/**",
//                "/albumsfromtrips").hasAuthority("USER");
//
//        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(thAuthenticationFilter);
        http.addFilterBefore(new ThAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins((List.of("http://localhost:3000")));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization","Cache-Control","Content-Type","Access-Control-Allow-Origin","Access-Control-Allow-Credentials" , "Access-Control-Request-Headers", "X-XSRF-TOKEN"));
        configuration.setAllowedMethods(List.of("GET","PUT","POST","DELETE","HEAD","OPTIONS","PATCH"));
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);
        return urlBasedCorsConfigurationSource;
    }
}
