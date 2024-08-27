package com.example.test.classloader.impl;

import com.example.test.classloader.IDex;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/23
 * describe:
 **/
public class DexImpl implements IDex {
    @Override
    public String getMessage() {
        return this.getClass().getName() + "is loaded by DexClassLoader";
    }
}
