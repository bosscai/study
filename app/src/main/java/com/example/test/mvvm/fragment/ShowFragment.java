package com.example.test.mvvm.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.test.R;
import com.example.test.mvvm.viewmodel.TestViewModel;

import java.util.List;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/5/10 17:35
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class ShowFragment extends BaseFragment {

    private TextView mTvContent;
    private TestViewModel model;

    public static final String TAG = "ShowFragment";

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_show;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ViewModelStoreOwner owner = getActivity();
        if (owner != null){
            mTvContent = initChildViewById(R.id.tv_show);
            model = new ViewModelProvider(owner, new ViewModelProvider.NewInstanceFactory()).get(TestViewModel.class);
            model.getStr().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Toast.makeText(getContext(), "CCC", Toast.LENGTH_SHORT).show();
                    mTvContent.setText(s);
                    getPackageName(getContext());
                }
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("LIFE",TAG + " onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LIFE",TAG + " onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("LIFE",TAG + " onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("LIFE",TAG + " onViewCreated: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("LIFE",TAG + " onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("LIFE",TAG + " onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("LIFE",TAG + " onResume: ");
    }

    private void getPackageName(Context context){
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                Log.e(TAG, "getPackageName: " + pn );
//                if (pn.equals("com.tencent.mm")) {
//                    return true;
//                }
            }
        }
    }
}
