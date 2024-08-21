//
// Created by ccx on 2023/11/23.
//
#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_example_test_ComponentActivity_stringFromJNI (JNIEnv *env, jobject){
    std::string hello = "hello From C++";
    return env->NewStringUTF(hello.c_str());
}