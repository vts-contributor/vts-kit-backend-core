package vn.com.viettel.core.utils;

import org.keycloak.KeycloakPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

public class KeycloakUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakUtils.class);

    /**
     * Lay id user login tu token
     *
     * @param authentication
     * @return
     */
    public static String getIdUserLogin(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            String userId = principal.getKeycloakSecurityContext().getToken().getId();
            return userId;
        } catch (Exception e) {
            LOGGER.error("Loi! getIdUserLogin: ", e);
            return null;
        }
    }

    /**
     * Lay user name login tu token
     *
     * @param authentication
     * @return
     */
    public static String getUserLogin(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            String userId = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
            return userId;
        } catch (Exception e) {
            LOGGER.error("Loi! getIdUserLogin: ", e);
            return null;
        }
    }

    /**
     * Get string accessToken
     * @param authentication
     * @return
     */
    public static String getStringToken(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            String userId = principal.getKeycloakSecurityContext().getTokenString();
            return userId;
        } catch (Exception e) {
            LOGGER.error("Loi! getIdUserLogin: ", e);
            return null;
        }
    }
}
