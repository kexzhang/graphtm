package com.hrtek.controller;

import com.hrtek.domain.User;
import com.hrtek.entity.Temp;
import com.hrtek.service.impl.TempServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/temp")
public class TempController {
   @Autowired
    private TempServiceImpl tempService;
   static Map<Long,User>  users = Collections.synchronizedMap(new HashMap<Long,User>());
   @RequestMapping("/queryTempAll")
    public ModelAndView quertTempAll(){
       List<Temp> tempList =tempService.queryTempAll();
       for(Temp temp :  tempList){
           System.out.println(temp.getEname()+" ");
       }
       System.out.println("------------------------");
      // model.addAttribute(tempList);
       ModelAndView mav =new ModelAndView("temp");
       mav.addObject(tempList);
       return  mav;
   }
   @RequestMapping("/queryTempAlls")
    public  ModelAndView queryTempAlls(){
        Iterable<Temp> tempList = tempService.queryTempAllByRepository();
        for (Temp temp :tempList){
            System.out.println(temp.getEname()+"---respository");
        }
       ModelAndView mav =new ModelAndView("temp");
       mav.addObject(tempList);
       return  mav;

   }
   @ApiOperation(value="获取列表",notes = "")
   @GetMapping(value = "/")
   @ResponseBody
    public List<User> getUserList(){
        //获取temp列表
        List<User> r = new ArrayList<User>(users.values());
       System.out.println(users.values());
        return  r ;
    }
    @ApiOperation(value = "创建用户",notes = "创建实体User")
    @ApiImplicitParam(name = "user",value = "实例详细实体User",required = true,dataType = "user")
    @PostMapping(value = "/")
    @ResponseBody
    public String postUser(@ModelAttribute  User user){
        //处理请求，创建Temp
        users.put(user.getId(),user);
        return  "success";

    }
    @GetMapping(value="/{id}")
    @ResponseBody
    @ApiOperation(value = "获取实体详细信息",notes = "根据url的Id来获取详细信息")
    @ApiImplicitParam(name = "id",value = "实例ID",required = true,dataType = "Long",paramType = "path")
    public  User getUser(@PathVariable Long id){
        //id 获取实体内容
       // Long ids =Long.valueOf(id);
        return  users.get(id);
    }
    @ApiOperation(value = "更新信息",notes = "根据实体唯一ID进行更新,需要传递实体对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "实体ID",required = true,dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user",value = "用户实体User",required = true,dataType = "User")
    })
    @PutMapping(value="/{id}")
    @ResponseBody
    public String putUser(@PathVariable Long id,@ModelAttribute User user){
        System.out.println(id);
     //处理put请求，更新Temp信息
        User u =users.get(id);
         u.setAge(user.getAge());
         u.setName(user.getName());
         users.put(id,u);
         return  "success";
    }
    @ApiOperation(value = "删除信息",notes = "根据url传递的唯一Id删除内容")
    @ApiImplicitParam(name = "id",value = "实体ID",required = true,dataType = "Long",paramType = "path")
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public  String DeleteUser(@PathVariable Long id){
        users.remove(id);
        return "success";
    }

}
