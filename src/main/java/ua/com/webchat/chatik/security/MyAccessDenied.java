package ua.com.webchat.chatik.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Component
public class MyAccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException,
                                                        ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            System.out.println("User " +authentication.getName() + " attempt to access protected page: "
                    + httpServletRequest.getRequestURI());
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/accessDenied");
    }
}
