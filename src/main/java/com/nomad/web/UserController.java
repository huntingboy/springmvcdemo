package com.nomad.web;

import com.nomad.dao.UserRepository;
import com.nomad.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
//@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;//用于获取项目实际路径以保存上传文件
    @Autowired
    private HttpServletRequest request;


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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(
            @Valid User user,
            Errors errors) {
        if (errors.hasErrors()) {
            List<ObjectError> errorList = errors.getAllErrors();
            for (ObjectError error :
                    errorList) {
                System.out.println(error.getDefaultMessage());
            }
            return "registerForm";
        }

        userRepository.save(user);
        return "redirect:/user/" + user.getUsername();
    }

    @RequestMapping(value = "/register1", method = RequestMethod.POST)
    public String processRegistration1(
            @Valid User user,
            Errors errors) {
        if (errors.hasErrors()) {
            List<ObjectError> errorList = errors.getAllErrors();
            for (ObjectError error :
                    errorList) {
                System.out.println(error.getDefaultMessage());
            }
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

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "uploadFileForm";
    }

    @RequestMapping(value = "/upload1", method = RequestMethod.GET)
    public String upload1() {
        return "uploadFileForm1";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String processUpload(
            @RequestPart("userPicture") MultipartFile file
    ) {  //byte[] userPicture 后者 Part
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        //保证上传后的文件名唯一，防止被覆盖
        File targetFile = new File(path, UUID.randomUUID().toString() + fileName); //不是new File(path+fileName) ，否则会保存在根目录，调试了好久。。。
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/list";
    }

    @RequestMapping(value = "/upload1", method = RequestMethod.POST)
    public String processUpload1(
            @RequestPart("userPictures") MultipartFile[] files
    ) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        if (files != null && files.length > 0) {
            for (MultipartFile file :
                    files) {
                try {
                    file.transferTo(new File(path, file.getOriginalFilename()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        String filePath = request.getSession().getServletContext().getRealPath("/") + "/upload/";
//        ModelAndView mav = new ModelAndView("fileList");
        File uploadDest = new File(filePath);
        String[] fileNames = uploadDest.list();
        model.addAttribute("fileNames", fileNames);
        for (int i = 0; i < fileNames.length; i++) {
            System.out.println(fileNames[i]);
        }
        return "fileList";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadFile(String fileName,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" +
                fileName);

        String path = request.getSession().getServletContext().getRealPath("upload");
        try {
            InputStream inputStream = new FileInputStream(new File(path, fileName));
            OutputStream os = response.getOutputStream();
            byte[] bytes = new byte[2048];
            int length;
            while ((length = inputStream.read(bytes)) > 0) {
                os.write(bytes, 0, length);
            }
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
