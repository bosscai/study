package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.taobao.android.weex_ability.page.WeexFragment;
import com.taobao.android.weex_framework.IMUSRenderListener;
import com.taobao.android.weex_framework.MUSDKInstance;
import com.taobao.android.weex_framework.MUSInstance;

import java.util.HashMap;
import java.util.Map;

public class WeexStudyActivity extends FragmentActivity implements WeexFragment.OnMSDowngradeListener, IMUSRenderListener {

    private WeexFragment mPageFragment;
    private String musUrl;
    private String webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weex_study);

        addMSFragment();
    }

    private void addMSFragment() {
        Map<String, String> options = new HashMap<>();
        mPageFragment = WeexFragment.newInstance(musUrl, webUrl, null,null, options);
        mPageFragment.setOnDowngradeListener(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.weex_container, mPageFragment, WeexFragment.FRAGMENT_TAG);
        ft.commit();
    }

    @Override
    public void onDowngrade() {

    }

    @Override
    public void onForeground(MUSInstance instance) {

    }

    @Override
    public void onPrepareSuccess(MUSInstance instance) {

    }

    @Override
    public void onRenderSuccess(MUSInstance instance) {

    }

    @Override
    public void onRenderFailed(MUSInstance instance, int type, String errorMsg, boolean isFatal) {

    }

    @Override
    public void onRefreshSuccess(MUSInstance instance) {

    }

    @Override
    public void onRefreshFailed(MUSInstance instance, int type, String errorMsg, boolean isFatal) {

    }

    @Override
    public void onJSException(MUSInstance instance, int type, String errorMsg) {

    }

    @Override
    public void onFatalException(MUSInstance instance, int type, String errorMsg) {

    }

    @Override
    public void onDestroyed(MUSDKInstance instance) {

    }
}