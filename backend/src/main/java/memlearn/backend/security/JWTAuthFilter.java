package memlearn.backend.security;

import lombok.extern.slf4j.Slf4j;
import memlearn.backend.model.PlanningUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final MongoDbUserDetailsService detailsService;

    public JWTAuthFilter(JWTUtils jwtUtils, MongoDbUserDetailsService detailsService) {
        this.jwtUtils = jwtUtils;
        this.detailsService = detailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{Optional<String> jwtToken = getJwtToken(request);

        if (jwtToken.isPresent()) {

            String userName = jwtUtils.extractUserName(jwtToken.get());
            UserDetails userDetails = detailsService.loadUserByUsername(userName);

            if (jwtUtils.validateToken(jwtToken.get(), userName)) {
                UsernamePasswordAuthenticationToken authentication = createAuthentication(request, userDetails);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }}catch(Exception e) {
            log.debug("failed to parsed token", e);
        }



        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().
                        buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }


    private Optional<String> getJwtToken(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader != null) {
            String token = authorizationHeader.replace("Baerer", "").trim();
            return Optional.of(token);
        }
        return Optional.empty();
    }

}
