#include <jni.h>
#include <iostream>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_faw_nativelib_NativeLib_stringFromJNI(
        JNIEnv* env,
        jclass clazz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_faw_nativelib_NativeLib_add(JNIEnv *env, jclass thiz, jint x, jint y) {
    return x + y;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_faw_nativelib_NativeLib_dealPic(JNIEnv *env, jclass clazz, jstring path) {
    std::cout << "Path: " << path << std::endl;
    printf("String: %s", path);
}