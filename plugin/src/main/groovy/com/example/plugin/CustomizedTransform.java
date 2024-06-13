package com.example.plugin;

import com.android.build.api.transform.*;
import com.android.build.gradle.internal.pipeline.TransformManager;

import org.apache.tools.ant.util.LeadPipeInputStream;
import org.gradle.api.Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import groovyjarjarasm.asm.ClassVisitor;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2024/5/20 10:23
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class CustomizedTransform extends Transform {

    private Project project;
    private ArrayList<String> dirFiles;

    public CustomizedTransform(Project project) {
        this.project = project;
        dirFiles = new ArrayList<>();
//        new ClassVisitor()
    }

    @Override
    public String getName() {
        return "auto-transform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        //接收的输入类型
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        //扫描范围
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
        project.getLogger().warn("Transform start!");
        project.getLogger().warn("inputs size: " + inputs.size());
        for (TransformInput input : inputs) {
            final Collection<JarInput> jarInputs = input.getJarInputs();
            project.getLogger().warn("jarInputs size: " + jarInputs.size());
            for (JarInput jarInput : jarInputs) {
//                project.getLogger().warn("jarInput " + jarInput.getFile().getAbsolutePath());
                scanJar(jarInput.getFile());
            }
            final Collection<DirectoryInput> directoryInputs = input.getDirectoryInputs();
            project.getLogger().warn("directoryInputs size: " + directoryInputs.size());
            for (DirectoryInput directoryInput : directoryInputs) {
//                project.getLogger().warn("directoryInput " + directoryInput.getFile().getAbsolutePath());
//                if (directoryInput.getFile().isDirectory()) {
//                    project.getLogger().warn("directoryInput " + directoryInput.getFile().getAbsolutePath());
//                }
                scanDir(directoryInput.getFile(), dirFiles);
            }

        }
    }

    private void scanJar(File file) throws IOException {
        final JarFile jarFile = new JarFile(file);
        final Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            final JarEntry jarEntry = entries.nextElement();
            final String name = jarEntry.getName();
            writeInfo("/Users/ccx/Desktop/AndroidProjects/study/info.txt", name);
        }
        jarFile.close();
    }

    private void scanDir(File file, ArrayList<String> paths) throws IOException {
        if (file == null) {
            return;
        }
        if(file.isFile()) {
            writeInfo("/Users/ccx/Desktop/AndroidProjects/study/dirInfo.txt", file.getAbsolutePath());
            paths.add(file.getAbsolutePath());
            return;
        }
        final File[] files = file.listFiles();
        for (File item : files) {
            if (item.isDirectory()) {
                scanDir(item, paths);
            } else {
                paths.add(item.getAbsolutePath());
                writeInfo("/Users/ccx/Desktop/AndroidProjects/study/dirInfo.txt", item.getPath());
            }
        }
    }

    private void writeInfo(String name, String content) throws IOException {
        final File file = new File(name);
        if (!file.exists()) {
            file.createNewFile();
        }
        final FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(content + "\n");
        fileWriter.flush();
        fileWriter.close();
    }
}
