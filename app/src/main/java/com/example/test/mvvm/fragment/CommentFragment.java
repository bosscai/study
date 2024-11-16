package com.example.test.mvvm.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.mvvm.viewmodel.VideoCommentViewModel;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/10/13 16:38
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：评论面板的Fragment
 */
public class CommentFragment extends BaseFragment {

    public static final String TAG = "CommentFragment";

    private ViewGroup mContentRootView;

    private VideoCommentViewModel commentViewModel;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_comment;
    }

    @Override
    protected void initData() {
        commentViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(VideoCommentViewModel.class);
    }

    @Override
    protected void initView() {
        mContentRootView = initChildViewById(R.id.comment_root_view);
        mContentRootView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + v.getTag());
                commentViewModel.sendCommentCloseAction();
            }
        });

        commentViewModel.getStickyAction().observe(getViewLifecycleOwner(), str -> {
            Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
        });
    }
}
