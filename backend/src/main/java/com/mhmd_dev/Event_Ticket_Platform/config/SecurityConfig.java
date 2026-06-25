package com.mhmd_dev.Event_Ticket_Platform.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.mhmd_dev.Event_Ticket_Platform.filters.UserProvisioningFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean //this allows the function to be injected into other classes
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();
    JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();

    authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
      Collection<GrantedAuthority> authorities =
          new ArrayList<>(scopeConverter.convert(jwt));
      Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");

      if (realmAccess != null && realmAccess.get("roles") instanceof List<?> roles) {
        roles.stream()
            .map(Object::toString)
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .forEach(authorities::add);
      }

      return authorities;
    });

    return authenticationConverter;
  }

  @Bean
  public SecurityFilterChain filterChain(
      HttpSecurity http,
      UserProvisioningFilter userProvisioningFilter,
      JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
    http
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers(HttpMethod.GET, "/api/v1/published-events/**").permitAll()
                .requestMatchers("/api/v1/events").hasRole("ORGANIZER")
                .requestMatchers("/api/v1/ticket-validations").hasRole("STAFF")
                .anyRequest().authenticated())
        .csrf(csrf -> csrf.disable())
        //stateless session management because we are using JWT
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //
        .oauth2ResourceServer(oauth2 ->
            oauth2.jwt(jwt ->
                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter) 
            ))
          //this means that the userProvisioningFilter will be run after the BearerTokenAuthenticationFilter
          //BearerTokenAuthenticationFilter is the default filter for oauth2ResourceServer
        .addFilterAfter(userProvisioningFilter, BearerTokenAuthenticationFilter.class);

    return http.build();
  }

}

//This is the configuration for the security of the application. It is used to define the rules for authentication and authorization.

//first, it is configured to allow all GET requests to the /api/v1/published-events/** endpoint, regardless of the user's role.

//then, it is configured to allow only users with the role of ORGANIZER to access the /api/v1/events endpoint.

//then, it is configured to allow only users with the role of STAFF to access the /api/v1/ticket-validations endpoint.

//finally, it is configured to allow all other requests to be authenticated using JWT tokens.

//csrf is disabled because it is not needed for this application because it is a REST API and not a web application.

//session management:
// User logs in via Keycloak
//      ↓
// Keycloak issues a JWT token
//      ↓
// Every request sends that JWT in the Authorization header
//      ↓
// Server validates the token, done — no memory needed

//oauth2ResourceServer:
// Tells Spring: "this app is a resource server that accepts JWT tokens". It will automatically validate the JWT signature against Keycloak's public key on every request. Then it uses your custom jwtAuthenticationConverter to extract the roles.


