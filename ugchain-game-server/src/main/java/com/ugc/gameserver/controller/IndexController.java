package com.ugc.gameserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fanjl on 2017/4/25.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(){
        return "webwallet";
    }
}
