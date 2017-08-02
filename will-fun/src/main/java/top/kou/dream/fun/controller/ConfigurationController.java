package top.kou.dream.fun.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kou.dream.fun.service.ConfigurationService;

/**
 * Created by ZXL on 2017/8/1.
 */
@Controller
@RequestMapping("/fun/config")
public class ConfigurationController {
    private static final Logger RUN_LOG = Logger.getLogger(ConfigurationController.class);

    @Autowired
    private ConfigurationService configurationService;

    @ResponseBody
    @RequestMapping("/get")
    public String get(String key) {
        return configurationService.get(key);
    }

    @ResponseBody
    @RequestMapping("/set")
    public String set(String key, String value) {
        return configurationService.set(key, value);
    }
}
