package com.customjar.targetclass;

import com.somebean.spi.SomeBeanInterface;

/**
 * @author ArchCST
 * @date 2023/1/12
 */
public class SomeBean implements SomeBeanInterface {
    static {
        System.out.println("类已加载");
    }
    public SomeBean() {
        System.out.println("Bean 已创建");
    }

    @Override
    public void callBean() {
        System.out.println("Bean 被调用");
    }
}
