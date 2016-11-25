package in.expedite.auth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class RestAuthenticationEntryPoint  implements AuthenticationEntryPoint {
	
	private static final Logger LOG = Logger.getLogger(RestAuthenticationEntryPoint.class.getName());
	
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

    	// This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
    	LOG.log(Level.SEVERE, "Not Authorised : ["+request.getRequestURI()+"]", authException);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

	
}