package com.example.test.weex2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Keep;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.android.weex_ability.WeexEngine;
import com.taobao.android.weex_framework.IMUSActivityNav;
import com.taobao.android.weex_framework.MUSInstance;
import com.taobao.android.weex_framework.util.MUSLog;

import java.io.Serializable;

@Keep
public class AppWeexEngineAutoInit implements Serializable {

    private static boolean sInit = false;

    @Keep
    public static synchronized void init(Application application){
        if (sInit){
            return;
        }
        sInit = true;
        WeexEngine.Config config = WeexEngine.Config.create().activityNav(new IMUSActivityNav() {
            @Override
            public boolean push(Context context, MUSInstance instance, String options) {
                JSONObject obj = JSON.parseObject(options);
                String url = obj.getString("url");
                Intent intent = new Intent();
                intent.setData(Uri.parse(url));
                application.startActivity(intent);
                return true;
            }

            @Override
            public boolean pop(Context context, MUSInstance instance, String options) {
                MUSLog.d("pop: " + options);
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
                return true;
            }
        }).debug(false).build();

        WeexEngine.getInstance().init(application, config);

    }
}
