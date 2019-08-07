package com.nomad.web;

import com.nomad.dao.UserRepository;
import com.nomad.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    public UserController() {
    }

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*@RequestMapping(method = RequestMethod.GET)
    public String users(Model model) {  //Map model
        model.addAttribute(userRepository.findUsers(
                Long.MAX_VALUE, 20
        ));
        //键userList根据类型自动推断
        *//*model.addAttribute("userList", userRepository.findUsers(
                Long.MAX_VALUE, 20
        ));*//*

        return "users";
    }*/

    //替代版本
    //处理器方法返回对象或集合时，值会放到模型Model中,从而放到视图中，模型的key根据类型自动推出
    // 逻辑试图名称根据请求路径推出，即users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> users() {
        return userRepository.findUsers(Long.MAX_VALUE, 20);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register1", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User());
        return "registerForm1";
    }

    //1.调用User()构造器，setter方法把表单字段填充到对象
    //2.校验User输入，但不会阻止表单提交
    //3.Errors参数要跟在@valid注解的参数后面
    @RequestMapping(value = {"/register", "/register1"}, method = RequestMethod.POST)
    public String processRegistration(
            @Valid User user,
            Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm1";
        }

        userRepository.save(user);
        return "redirect:/user/" + user.getUsername();
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public String showUserProfile(
            @PathVariable String username, Model model
    ) {
        User user = userRepository.findUserByName(username);
        model.addAttribute(user);
        return "profile";
    }
}
