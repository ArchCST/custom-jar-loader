package cn.archcst.utils.customjar;

import com.somebean.spi.SomeBeanInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author ArchCST
 * @date 2023/1/12
 */
@Component
public class SomeAppRunner implements ApplicationRunner {
    @Autowired
    SomeBeanInterface someBeanInterface;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        someBeanInterface.callBean();
    }
}
