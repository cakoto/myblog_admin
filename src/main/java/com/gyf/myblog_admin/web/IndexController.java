package com.gyf.myblog_admin.web;

import com.gyf.myblog_admin.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        /*
         * 控制返回一个页面+后台控制信息
         */
        //int i = 9/0;
//        String blog = null;
//        if(blog == null){
//            //return 404;不要这样做
//            throw new NotFoundException("博客不存在");
//        }
        System.out.println("-------index------");
        return "index";
    }

}
