package com.example.test;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;
import com.google.common.collect.FluentIterable;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

public class InjectTransform extends Transform {

    private static final String TAG = InjectTransform.class.getSimpleName();

    @Override
    public String getName() {
        return TAG;
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        transformInvocation.getInputs().forEach(input -> {
            //这是app壳工程下的class
            input.getDirectoryInputs().forEach(directoryInput -> {
                transformDirectoryInput(directoryInput, outputProvider);
            });
            //这是引入的产物的class，implementation、api等引入的bundle
            input.getJarInputs().forEach(jarInput ->
                    transformJarInput(jarInput, outputProvider));
        });

    }

    private void transformJarInput(JarInput jarInput, TransformOutputProvider provider) {

        File dest = provider.getContentLocation(
                jarInput.getFile().getAbsolutePath(),
                jarInput.getContentTypes(),
                jarInput.getScopes(),
                Format.JAR);
        try {
            FileUtils.copyFile(jarInput.getFile(), dest);
        } catch (IOException e) {

        }
        File file = jarInput.getFile();
        FluentIterable<File> allFiles = FileUtils.getAllFiles(file);
        allFiles.forEach(new Consumer<File>() {
            @Override
            public void accept(File file) {
                if (file.getName().endsWith(".jar")) {
                    String className = file.getName().substring(0, file.getName().length() - 4);
                    String methodName = className.substring(className.lastIndexOf(".") + 1);
                    try {
                        writeToFile(className, methodName, "jar.txt");
                    } catch (IOException ignored) {

                    }
                }
            }
        });
    }

    private void transformDirectoryInput(DirectoryInput input, TransformOutputProvider provider) {
        File dest = provider.getContentLocation(
                input.getFile().getAbsolutePath(),
                input.getContentTypes(),
                input.getScopes(),
                Format.DIRECTORY);

        //建文件夹
        FileUtils.mkdirs(dest);
        try {
            FileUtils.copyDirectory(input.getFile(), dest);
        } catch (IOException e) {

        }
        File file = input.getFile();
        if (file.isDirectory()) {
            FluentIterable<File> files = FileUtils.getAllFiles(file);
            files.forEach(new Consumer<File>() {
                @Override
                public void accept(File file) {
                    if (file.getName().endsWith(".class")) {
                        String className = file.getName().substring(0, file.getName().length() - 6);
                        String methodName = className.substring(className.lastIndexOf(".") + 1);
                        if (methodName.equals("onClick"))
                        try {
                            writeToFile(className, methodName, "classes.txt");
                        }catch (IOException ignored){

                        }

                    }
                }
            });
        }
    }

    private void writeToFile(String className, String methodName, String fileName) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            File file = new File(
                    "/Users/ccx/Desktop/AndroidProjects/study/app/build/intermediates/transforms"
                            + "/test/"
                            + fileName
            );
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            long fileLength = randomAccessFile.length();
            randomAccessFile.seek(fileLength);
            randomAccessFile.writeBytes(className + "$" + methodName + "\n");
        } finally {
            randomAccessFile.close();
        }
    }
}
