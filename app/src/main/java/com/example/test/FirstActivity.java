package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTimerStart, mTimerCancel, mAnimationStart;
    private Timer timer;
    private TimerTask timerTask;
    private ImageView imageView, imageViewPre;
    private TransitionDrawable transition;
    private Animation bottomOut, bottomIn, topOut, topIn;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 123:
//                    Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initView();
    }

    private void initView() {
        mTimerStart = findViewById(R.id.btn_timer_start);
        mTimerCancel = findViewById(R.id.btn_timer_cancel);
        mAnimationStart = findViewById(R.id.btn_animation);
        mTimerStart.setOnClickListener(this);
        mTimerCancel.setOnClickListener(this);
        mAnimationStart.setOnClickListener(this);
        imageView = findViewById(R.id.img_show);
        imageViewPre = findViewById(R.id.img_show_pre);
        timer = new Timer();

        //渐变过渡
        transition = (TransitionDrawable) getResources().getDrawable(R.drawable.transition_anim);
        imageView.setImageDrawable(transition);

        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.beauty_1));
        imageViewPre.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.beauty_2));


        bottomOut = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_out);
        bottomIn = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_in);

        topOut = AnimationUtils.loadAnimation(this, R.anim.slide_top_out);
        topIn = AnimationUtils.loadAnimation(this, R.anim.slide_top_in);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_timer_start:
                Toast.makeText(getApplicationContext(), "定时器将在5秒后开始", Toast.LENGTH_SHORT).show();

                timerTask = new TimerTask() {
                    private int num = 5;
                    @Override
                    public void run() {
                        if (num > 0){
                            Message message = new Message();
                            message.obj = num;
                            message.what = 123;
                            handler.sendMessage(message);
//                            handler.post(()->Toast.makeText(getApplicationContext(), num, Toast.LENGTH_SHORT).show());
                            Log.e("TAG", "run: " + num );
                            num--;
                        } else {
                            cancel();
                        }
//                        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(getApplicationContext(), "定时器开始", Toast.LENGTH_SHORT).show());
//                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "定时器开始", Toast.LENGTH_SHORT).show());
                    }
                };
                timer.schedule(timerTask, 2000, 1000);
                break;
            case R.id.btn_timer_cancel:
                Toast.makeText(getApplicationContext(), "定时器取消", Toast.LENGTH_SHORT).show();
                if (timerTask != null) {
                    timerTask.cancel();
                }
                timer.cancel();
                timer = null;
                break;
            case R.id.btn_animation:
//                transition.startTransition(2000);
//                imageViewPre.startAnimation(bottomOut);
//                imageView.startAnimation(bottomIn);
                imageViewPre.startAnimation(topOut);
                imageView.startAnimation(topIn);
                if (timer == null){
                    Log.e("TAG", "timer == null" );
                }
                break;
        }
    }
}