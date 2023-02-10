package org.example;

import cn.archcst.customjarloader.BeanInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author ArchCST
 * ${DATE}
 */
@SpringBootApplication
@Import(BeanInjector.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}