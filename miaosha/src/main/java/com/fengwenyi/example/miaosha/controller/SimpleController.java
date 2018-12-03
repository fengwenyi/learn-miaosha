package com.fengwenyi.example.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Wenyi Feng
 * @since 2018-11-29
 */
@Controller
@RequestMapping("/demo")
public class SimpleController {

    @RequestMapping("/thymeleaf")
    //@ResponseBody
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Zhangsan");
        return "hello";
    }

}
