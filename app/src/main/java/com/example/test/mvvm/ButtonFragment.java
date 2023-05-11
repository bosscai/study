package com.example.test.mvvm;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.test.R;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/5/10 11:56
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class ButtonFragment extends BaseFragment {

    private Button mBtnStart;
    private TestViewModel model;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_button;
    }

    @Override
    protected void initView() {
        ViewModelStoreOwner owner = getActivity();
        if (owner != null) {
            model = new ViewModelProvider(owner, new ViewModelProvider.NewInstanceFactory()).get(TestViewModel.class);
            mBtnStart = initChildViewById(R.id.btn_start);
            mBtnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "HHH", Toast.LENGTH_SHORT).show();
                    model.getStr().postValue(System.currentTimeMillis() + "");
                }
            });
            Log.e(TAG, "initView: " + model.getStr().hashCode() + " this: " + this);
        }
    }
}