package com.mhmd_dev.Event_Ticket_Platform.filters;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.User;
import com.mhmd_dev.Event_Ticket_Platform.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor

//once per request so spring will only run this once per request
public class UserProvisioningFilter extends OncePerRequestFilter {

  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {

    // get the authentication from the security context
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()
        && authentication.getPrincipal() instanceof Jwt jwt) {

      // get the keycloak id from the jwt through getSubject() which returns a string
      UUID keyCloakId = UUID.fromString(jwt.getSubject());

      // check if the user exists in the database
      // if not create a new user
      if (!userRepository.existsById(keyCloakId)) {
        User user = new User();
        user.setId(keyCloakId);
        user.setName(jwt.getClaims().get("preffered_username").toString());
        user.setEmail(jwt.getClaims().get("email").toString());
        userRepository.save(user);
      }

      // continue with the next filter in the chain
      //means we don't want to stop the request/response cycle
      filterChain.doFilter(request, response);
    }

  }

}
//Core idea of this component:
//Every time an authenticated request comes in, check if this Keycloak user exists in our DB. If not, create them automatically


