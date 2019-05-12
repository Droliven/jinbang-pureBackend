package com.jinbang.controller;

import com.jinbang.mapper.UserMapper;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.model.User;
import com.jinbang.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class BasicController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ItemService itemService;

    @RequestMapping("/")
    public ModelAndView cover(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cover");
        return mv;
    }
//    @RequestMapping("/")
//    public String cover(){
//        return "index";
//    }
    @GetMapping ("/signin")
    public ModelAndView signin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signin");
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/home")
    public ModelAndView signinPost(User user, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User userDB = userMapper.loadByUserName(user.getName());
        if(userDB != null){
//            String pwdDB = DigestUtils.md5DigestAsHex(userDB.getPwd().getBytes());
            String pwdDB = userDB.getPwd();
//            System.out.println("user.getPwd() " + user.getPwd());
//            System.out.println("pwdDB " + pwdDB);
            if(pwdDB.equals(user.getPwd())){
                System.out.println(user.getName() + " 密码正确登录成功");
                // 写入 session
                session.setAttribute("name", user.getName());
                modelAndView.setViewName("home");
                modelAndView.addObject("name", user.getName());
//                List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps;
//                item_asr_usr_ik_kps = itemService.getItem_Asr_Usr_IK_KpAll();
//                modelAndView.addObject("items", item_asr_usr_ik_kps);
            } else{
                System.out.println("密码错误");
                modelAndView.setViewName("relogin");
                modelAndView.addObject("user", new User());
            }
        } else {
            System.out.println(user.getName() + " 不存在");
            modelAndView.setViewName("relogin");
            modelAndView.addObject("user", new User());
        }
        return modelAndView;
    }
    @RequestMapping("/signout")
    public ModelAndView signout(HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cover");
        // 取出 session 中的 name
        Object name = session.getAttribute("name");
        System.out.println(name.toString() + "注销！");
        session.removeAttribute("name");
        return mv;
    }
}
