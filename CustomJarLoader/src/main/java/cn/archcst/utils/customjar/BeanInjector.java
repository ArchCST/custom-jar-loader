package cn.archcst.utils.customjar;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ArchCST
 * @date 2023/1/12
 */
public class BeanInjector implements ImportBeanDefinitionRegistrar {
     private final String targetUrl = "file:/Users/cst/git/class-loader-custom-jar/custom-jar-dir/TragetJar-1.0-SNAPSHOT.jar";
    private final String pluginClass = "com.customjar.targetclass.SomeBean";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        try {
            ClassLoader classLoader = ClassLoaderUtils.getClassLoader(targetUrl);
            Class<?> clazz = classLoader.loadClass(pluginClass);
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            BeanDefinition beanDefinition = builder.getBeanDefinition();
            registry.registerBeanDefinition(clazz.getName(), beanDefinition);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
