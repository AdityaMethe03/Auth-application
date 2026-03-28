package com.authentication.auth_app_backend.config;

import com.authentication.auth_app_backend.dtos.ApiError;
import com.authentication.auth_app_backend.security.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            authorizedHttpRequests ->
                authorizedHttpRequests
                    .requestMatchers("/api/v1/auth/register")
                    .permitAll()
                    .requestMatchers("/api/v1/auth/login")
                    .permitAll()
                    .requestMatchers("/api/v1/auth/refresh")
                    .permitAll()
                        .requestMatchers("/api/v1/auth/logout")
                        .permitAll()
                    .anyRequest()
                    .authenticated())
        .exceptionHandling(
            ex ->
                ex.authenticationEntryPoint(
                    (request, response, e) -> {
                      // error message
                      e.printStackTrace();
                      response.setStatus(401);
                      response.setContentType("application/json");

                      String message = "Unauthorized Access !" + e.getMessage();
                      String error = (String) request.getAttribute("error");
                      if (error != null) {
                        message = error;
                      }

                      //                      Map<String, Object> errorMap =
                      //                          Map.of("message", message, "statusCode", 401);
                      var apiError =
                          ApiError.of(
                              HttpStatus.UNAUTHORIZED.value(),
                              "Unauthorized Access!",
                              message,
                              request.getRequestURI(),
                              true);
                      var objectMapper = new ObjectMapper();
                      response.getWriter().write(objectMapper.writeValueAsString(apiError));
                    }))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }

  //  @Bean
  //  public UserDetailsService users() {
  //    User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
  //
  //    UserDetails user1 = userBuilder.username("aditya").password("12345").roles("ADMIN").build();
  //    UserDetails user2 = userBuilder.username("vivek").password("12345").roles("ADMIN").build();
  //    UserDetails user3 =
  // userBuilder.username("prakarsh").password("12345").roles("ADMIN").build();
  //
  //    return new InMemoryUserDetailsManager(user1, user2, user3);
  //  }
}
