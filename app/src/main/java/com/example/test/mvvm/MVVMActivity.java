package com.example.test.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.test.R;
import com.example.test.mvvm.fragment.CommentFragment;
import com.example.test.mvvm.viewmodel.VideoCommentViewModel;

public class MVVMActivity extends AppCompatActivity {

    public static final String TAG = "MVVMActivity";

    private VideoCommentViewModel commentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmactivity);
        commentViewModel =  new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(VideoCommentViewModel.class);
        commentViewModel.getCommentAction().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isOpen) {
                Log.e(TAG, "onChanged: " + isOpen);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.comment_slide_bottom_in, R.anim.comment_slide_bottom_out);
                if (isOpen) {
                    fragmentTransaction.add(R.id.root_container, new CommentFragment(), CommentFragment.TAG).commit();
                } else {
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(CommentFragment.TAG);
                    if (fragment != null) {
                        fragmentTransaction.remove(fragment).commitAllowingStateLoss();
                    }
                }
            }
        });
    }
}