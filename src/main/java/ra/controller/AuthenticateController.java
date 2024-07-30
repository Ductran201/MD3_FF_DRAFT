package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.dto.FormSignInRequest;
import ra.model.dto.FormSignUpRequest;
import ra.model.entity.RoleName;
import ra.model.entity.User;
import ra.service.AuthenticateService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Transactional
@Controller
@RequestMapping({"", "signIn"})
public class AuthenticateController {
    @Autowired
    private AuthenticateService authenticateService;

    //========================== SIGN IN ================================
    @GetMapping("")
    public String formSignIn(Model model) {
        model.addAttribute("formSignIn", new FormSignInRequest());
        return "SignIn";
    }

    @PostMapping("signIn")
    public String signIn(@ModelAttribute FormSignInRequest formSignInRequest, HttpSession session) {
        User user = authenticateService.signIn(formSignInRequest);
        if (user != null) {
//        has User or null -> move thymeleaf
            session.setAttribute("userCurrent", user); // related to 1 session account
            // check role(admin or user)
            if (user.getRoleSet().stream().anyMatch(r -> r.getRoleName().equals(RoleName.ROLE_ADMIN))) {
                //open admin page
//                return "redirect:/admin/dashboard";
                return "redirect:/category";
            }
            return "redirect:/category";
        } else {
            return "redirect:/"; // reset page sign in
        }
    }

    // =================================== SIGN UP ====================================
    // open sign up page
    @GetMapping("signUp")
    public String formSignUp(Model model) {
        model.addAttribute("formSignUp", new FormSignUpRequest());
        return "SignUp";
    }

    // sign up new account
    @PostMapping("signUp")
//    public String signUp(Model model) { // distinct between model and @modelAttribute
    public String signUp(@Valid @ModelAttribute(name = "formSignUp") FormSignUpRequest formSignUpRequest, BindingResult result, Model model) {
        //name of @modelAttribute must match with model in getMapping : model.addAttribute("formSignUp", new FormSignUpRequest());
//        public String signUp(@Valid @ModelAttribute FormSignUpRequest formSignUpRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formSignUp", formSignUpRequest);
            return "SignUp";
        }
        authenticateService.signUp(formSignUpRequest);
        return "redirect:/";
        //if has any error of input -> show tag <span>
    }

    @GetMapping("403")
    public String _403() {
        return "403";
    }


}
