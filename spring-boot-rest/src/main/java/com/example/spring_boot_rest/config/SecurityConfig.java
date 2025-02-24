package com.example.spring_boot_rest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailService;
   @Bean
   public AuthenticationProvider authProvider(){
       DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
       provider.setUserDetailsService(userDetailService);
       //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
       provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
       return  provider;
   }

    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
      //this is for disabling csrf token
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        //here we use stateless so that everytime will get different session id.
        http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return   http.build();

}

//multiple user as we can't define multple user into application.properties
 /*  @Bean
        public UserDetailsService userDetailsService(){
        UserDetails user= User.withDefaultPasswordEncoder().username("pawan").password("12345").roles("user").build();
        UserDetails admin= User.withDefaultPasswordEncoder().username("kumar").password("12345").roles("admin").build();

       return new InMemoryUserDetailsManager(user,admin);
   }*/


}
