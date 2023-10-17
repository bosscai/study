package com.example.test.mvvm.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.mvvm.viewmodel.VideoCommentViewModel;
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
    private VideoCommentViewModel commentViewModel;
    private ViewGroup mParentComment;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_right_operation;
    }

    @Override
    protected void initData() {
        commentViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(VideoCommentViewModel.class);
    }

    @Override
    protected void initView() {
        mAvatarView = initChildViewById(R.id.img_avatar);
        mFollowView = initChildViewById(R.id.img_follow);
        mParentComment = initChildViewById(R.id.parent_comment);
        mFollowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了关注", Toast.LENGTH_SHORT).show();

            }
        });
        mParentComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commentViewModel != null){
                    commentViewModel.sendCommentOpenAction();
                }
            }
        });


    }
}
