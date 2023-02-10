package org.example.service;

import com.somespi.interfaces.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author ArchCST
 * @date 2023/1/12
 */
@Component
public class OutputServiceCaller implements ApplicationRunner {
    @Autowired
    OutputService outputService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        outputService.outputServiceCalled();
    }
}
