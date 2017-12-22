package com.hrtek.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@EnableAutoConfiguration
public class LoginController {

    @RequestMapping("/index")
    public  String index(){
        return  "hi,springboot!";
    }
    @RequestMapping("/")
    @ResponseBody
    public  String login(){

        return  "11111111111";
    }


    public  static  void  main (String [] args ) {
        SpringApplication.run(LoginController.class,args);
    }
}
