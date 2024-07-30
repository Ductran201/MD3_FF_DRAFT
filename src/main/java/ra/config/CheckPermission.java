package ra.config;

import org.springframework.web.servlet.HandlerInterceptor;
import ra.model.entity.RoleName;
import ra.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPermission implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("userCurrent");
        if (user==null){ // have no sign in session in browser
            response.sendRedirect("signIn");
            return false;
        }else {
            // check permission access to link of admin
            if (user.getRoleSet().stream().anyMatch(r->r.getRoleName().equals(RoleName.ROLE_ADMIN))){
                return true;
            }else {
                response.sendRedirect("/403");
                return false;
            }

        }
    }
}
