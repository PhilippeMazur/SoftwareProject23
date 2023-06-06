package edu.ap.backendspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private JwtAuthEntryPoint authEntryPoint;

    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors(withDefaults()).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .antMatchers("/users/login").permitAll()
                        .antMatchers(HttpMethod.GET, "/users/").hasAuthority("DEVELOPER")
                        .antMatchers(HttpMethod.POST, "/users/**").hasAuthority("DEVELOPER")
                        .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("DEVELOPER")
                        .antMatchers(HttpMethod.PUT, "/users/**").authenticated()
                        .antMatchers(HttpMethod.DELETE,"/application").hasAnyAuthority("DEVELOPER", "INITIATIEFNEMER")
                        .antMatchers("/application/**").authenticated()
                        .antMatchers("/career/**").authenticated()
                        .antMatchers("/certificate/**").authenticated()
                        .antMatchers("/users/").authenticated())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    throws Exception{
            return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return  new JWTAuthenticationFilter();
    }

}
