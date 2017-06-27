package top.kou.dream.fun.service;

import org.springframework.stereotype.Service;

/**
 * Created by ZXL on 2017/6/27.
 */
@Service
public class TestService {

    public String process(String parameter) {
        return "top.kou.dream.fun.service.TestService.process - "
                .concat(parameter)
                .concat(" - ");
    }

}
