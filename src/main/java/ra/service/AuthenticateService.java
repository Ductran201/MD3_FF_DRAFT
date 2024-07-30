package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.RoleDao;
import ra.dao.UserDao;
import ra.model.dto.FormSignInRequest;
import ra.model.dto.FormSignUpRequest;
import ra.model.entity.Role;
import ra.model.entity.RoleName;
import ra.model.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticateService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    User findByEmail() {
        return null;
    }

    public boolean signUp(FormSignUpRequest formSignUpRequest) {
        //get the user role -> add to list role that User have to have.
        Set<Role> roleSet= new HashSet<>();
        roleSet.add(roleDao.findByRoleName(RoleName.ROLE_USER));
        User user = User.builder()
                .email(formSignUpRequest.getEmail())
                .password(formSignUpRequest.getPassword())
                .fullName(formSignUpRequest.getFullName())
                .roleSet(roleSet)
                .createdDate(new Date())
                .status(true)
                .build();
        return userDao.signUp(user);
    }

    public User signIn(FormSignInRequest formSignInRequest) {
//        signInDao.
        // logic check email and pass in dao
        // sign in false -> back to sign in page
        // sign in success -> check role -> move thymeleaf
        return userDao.getAuthUser(formSignInRequest.getEmail(), formSignInRequest.getPassword());
    }


}
