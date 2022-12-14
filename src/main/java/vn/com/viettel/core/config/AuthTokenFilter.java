package vn.com.viettel.core.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.com.viettel.core.dto.request.Jwt;
import vn.com.viettel.core.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthTokenFilter extends OncePerRequestFilter {

    /**
     *
     * @param servletRequest
     * @param response
     * @param fc
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse response, FilterChain fc) throws ServletException, IOException {
        String jwt = JwtUtils.resolveToken(servletRequest);
        if (jwt != null) {
            Authentication authentication = authenticate(jwt);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        fc.doFilter(servletRequest, response);
    }

    /**
     * @param jwtString
     * @return
     */
    public Authentication authenticate(String jwtString) {
        Jwt jwt = (Jwt) JwtUtils.getDataFromJwt(jwtString, Jwt.class);
        if (jwt != null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            if (jwt.getRealmAccess() != null && jwt.getRealmAccess().getRoles() != null) {
                List<String> listRole = jwt.getRealmAccess().getRoles();
                if (listRole != null && listRole.size() > 0) {
                    for (String roleName : listRole) {
                        GrantedAuthority grant = new SimpleGrantedAuthority(roleName);
                        grantedAuths.add(grant);
                    }
                }
            }
            return new UsernamePasswordAuthenticationToken(jwt, "", grantedAuths);
        }
        return null;
    }
}
