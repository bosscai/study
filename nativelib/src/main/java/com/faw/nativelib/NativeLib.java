package com.faw.nativelib;

public class NativeLib {

    // Used to load the 'nativelib' library on application startup.
    static {
        System.loadLibrary("nativelib");
    }

    /**
     * A native method that is implemented by the 'nativelib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromJNI();

    public static native int add(int x, int y);

    public static native void dealPic(String path);
}