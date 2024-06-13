// IServer.aidl
package com.example.test;

// Declare any non-default types here with import statements

interface IServer {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int startService(String s);

    String stopService(int num);
}