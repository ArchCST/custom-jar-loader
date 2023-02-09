package cn.archcst;

import cn.archcst.utils.customjar.BeanInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author ArchCST
 * @date 2023/1/12
 */
@SpringBootApplication
@Import(BeanInjector.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
