package com.example.test.mvvm.fragment;

import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.test.R;
import com.example.test.mvvm.viewmodel.TestViewModel;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/5/10 17:35
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class ShowFragment extends BaseFragment {

    private TextView mTvContent;
    private TestViewModel model;

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
                }
            });
        }
    }
}
