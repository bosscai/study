package com.example.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * 描述：第一个自定义的Plugin插件
 */
public class MyCustomizedPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("---------------------------------Start---------------------------------");
        String name = project.getName();
        System.out.println(name);
        System.out.println("---------------------------------End---------------------------------");
    }
}
