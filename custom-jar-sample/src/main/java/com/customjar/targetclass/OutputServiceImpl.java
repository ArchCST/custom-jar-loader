package com.customjar.targetclass;

import com.customjar.targetclass.config.ConfigBean;
import com.somespi.interfaces.OutputService;
import org.springframework.stereotype.Component;

/**
 * 输出方向接口的实现，由主程序主动调用，本插件做实现
 * @author ArchCST
 * @date 2023/1/12
 */
@Component
public class OutputServiceImpl implements OutputService {
    static {
        System.out.println("OutputServiceImpl 类已加载");
    }

    ConfigBean configBean;

    public OutputServiceImpl(ConfigBean configBean) {
        System.out.println("OutputServiceImpl bean 已创建");
        this.configBean = configBean;
    }

    @Override
    public void outputServiceCalled() {
        System.out.println("OutputServiceImpl bean 被调用，配置为：" + configBean.getSomeProperty());
    }
}
