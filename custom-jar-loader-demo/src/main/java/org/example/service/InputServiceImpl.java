package org.example.service;

import com.somespi.interfaces.InputService;
import org.springframework.stereotype.Component;

/**
 * 输入方向接口的实现，由主程序提供Bean，通过IOC注入到插件中供插件调用
 * @author ArchCST
 * 2023/2/10
 */
@Component
public class InputServiceImpl implements InputService {
    @Override
    public void inputServiceCalled() {
        System.out.println("input service called");
    }
}
