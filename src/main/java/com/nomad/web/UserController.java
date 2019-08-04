package com.nomad.web;

import com.nomad.dao.UserRepository;
import com.nomad.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

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
    @RequestMapping(method = RequestMethod.GET)
    public List<User> users() {
        return userRepository.findUsers(Long.MAX_VALUE, 20);
    }
}