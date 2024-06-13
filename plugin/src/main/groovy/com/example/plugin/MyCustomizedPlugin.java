package com.example.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.LibraryExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * 描述：第一个自定义的Plugin插件
 */
public class MyCustomizedPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("-----------------MyCustomizedPlugin Start----------------");
        String name = project.getName();
        System.out.println(name);
        final AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        appExtension.registerTransform(new CustomizedTransform(project));
    }
}
