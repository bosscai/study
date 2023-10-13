package com.example.test.mvvm.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.mvvm.fragment.BaseFragment;
import com.example.test.view.CircleImageView;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/10/13 14:24
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：短视频关注、点赞、评论、收藏和转发等操作Fragment
 */
public class RightOperationFragment extends BaseFragment {

    private CircleImageView mAvatarView;
    private ImageView mFollowView;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_right_operation;
    }

    @Override
    protected void initView() {
        mAvatarView = initChildViewById(R.id.img_avatar);
        mFollowView = initChildViewById(R.id.img_follow);
        mFollowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了关注", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
