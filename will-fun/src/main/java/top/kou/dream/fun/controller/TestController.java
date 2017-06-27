package top.kou.dream.fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kou.dream.fun.service.TestService;

/**
 * Created by ZXL on 2017/6/27.
 */
@Controller
@RequestMapping("/fun/test")
public class TestController {

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping
    public String test(String parameter) {
        return testService.process(parameter);
    }
}
