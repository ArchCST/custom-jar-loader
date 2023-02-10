package com.customjar.targetclass;

import com.somespi.interfaces.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author ArchCST
 * 2023/2/10
 */
@Component
public class InputServiceCaller implements ApplicationRunner {
    @Autowired
    InputService inputService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        inputService.inputServiceCalled();
    }
}
