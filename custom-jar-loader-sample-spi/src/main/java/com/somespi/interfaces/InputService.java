package com.somespi.interfaces;

/**
 * 输入方向接口，由主程序提供实现，通过IOC注入到插件中供插件调用
 * @author ArchCST
 * 2023/2/10
 */
public interface InputService {
    void inputServiceCalled();
}
