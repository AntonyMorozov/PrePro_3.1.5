package ru.kata.spring.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.model.User;
import ru.kata.spring.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;

    @Autowired
    public LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    public LoginSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        User user = userService.getUserByName(authentication.getName()).orElse(null);
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/");
        } else if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user/" + user.getId());
        }
    }
}
