package cn.archcst.customjarloader;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author ArchCST
 * @date 2023/1/12
 */
public class BeanInjector implements ImportBeanDefinitionRegistrar {
    private static final String CLASS_FILE_SUFFIX = ".class";
    private static final String JAR_FILE_EXTENSION = "jar";
    private static final String CUSTOM_JAR_DIR = "CUSTOM_JAR_DIR";


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        String customJarDirectory = System.getenv(CUSTOM_JAR_DIR);

        Collection<File> packages = findAllJarPackage(customJarDirectory);

        for (File pkg : packages) {
            JarFile jarFile = convertToJarFile(pkg);

            List<String> clazzNames = findClazzNames(jarFile);

            try {
                ClassLoader classLoader = JarFileClassLoaderUtils.getClassLoader(pkg.toURI().toString());

                for (String clazzName : clazzNames) {
                    assert classLoader != null;
                    Class<?> clazz = classLoader.loadClass(clazzName);
                    registerIfComponent(registry, clazz);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static JarFile convertToJarFile(File pkg) {
        JarFile jarFile;
        try {
            jarFile = new JarFile(pkg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jarFile;
    }

    private static void registerIfComponent(BeanDefinitionRegistry registry, Class<?> clazz) {
        if (clazz.isAnnotationPresent(Component.class)) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            BeanDefinition beanDefinition = builder.getBeanDefinition();
            registry.registerBeanDefinition(clazz.getName(), beanDefinition);
        }
    }

    private List<String> findClazzNames(JarFile jarFile) {
        ArrayList<String> clazzNames = new ArrayList<>();

        Enumeration<JarEntry> jarEntries = jarFile.entries();

        while (jarEntries.hasMoreElements()) {
            String entryFileName = jarEntries.nextElement().getName();
            if (entryFileName.endsWith(CLASS_FILE_SUFFIX)) {
                String clazzName = entryFileName.substring(0, entryFileName.lastIndexOf(CLASS_FILE_SUFFIX))
                        .replace(File.separatorChar, '.');
                clazzNames.add(clazzName);
            }
        }

        return clazzNames;
    }

    private Collection<File> findAllJarPackage(String customJarDirectory) {
        return FileUtils.listFiles(new File(customJarDirectory), new String[]{JAR_FILE_EXTENSION}, true);
    }
}
