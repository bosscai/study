package com.example.test.classloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test.R
import com.example.test.utils.FileUtils
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_dex_class_loader.*
import java.io.File
import javax.security.auth.login.LoginException

class DexClassLoaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dex_class_loader)
        // 加载dex类并执行其中的方法，此处需要实现 DexClassLoader的使用
        btn_start.setOnClickListener {
            loadDexClass()
        }
    }

    private fun loadDexClass() {
        val cacheDir = FileUtils.getCacheDir(baseContext)
        val dexPath = cacheDir.absolutePath + File.separator + "DexImpl.dex"
        val desFile = File(dexPath)
        if (!desFile.exists()) {
            // 请先将dexImpl.dex拷贝到app的缓存目录下
            desFile.createNewFile()
            FileUtils.copyFiles(this, "DexImpl.dex", desFile)
        }
        /**
         * 参数1 dexPath：待加载的dex文件路径，如果是外存路径，一定要加上读外存文件的权限
         * 参数2 optimizedDirectory：解压后的dex存放位置，此位置一定要是可读写且仅该应用可读写（安全性考虑），所以只能放在data/data下。
         * 参数3 libraryPath：指向包含本地库(so)的文件夹路径，可以设为null
         * 参数4 parent：父级类加载器，一般可以通过Context.getClassLoader获取到，也可以通过ClassLoader.getSystemClassLoader()取到。
         */
        val dexClassLoader = DexClassLoader(dexPath, cacheDir.absolutePath, null, classLoader)
        val clazz = dexClassLoader.loadClass("com/example/test/classloader/impl/DexImpl")
        val newInstance: IDex = clazz.newInstance() as IDex
        Toast.makeText(this, newInstance.message, Toast.LENGTH_SHORT).show()
    }
}