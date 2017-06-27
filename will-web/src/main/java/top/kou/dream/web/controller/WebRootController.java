package top.kou.dream.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ZXL on 2017/6/27.
 */
@Controller
@RequestMapping("/root")
public class WebRootController {

    @ResponseBody
    @RequestMapping
    public String test(String data) {
        return "root-".concat(data);
    }

}
