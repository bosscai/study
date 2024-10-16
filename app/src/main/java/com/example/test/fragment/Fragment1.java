package com.example.test.fragment;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.mvvm.fragment.BaseFragment;
import com.example.test.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/19
 * describe:
 **/
public class Fragment1 extends BaseFragment {

    private Button mBtSavePic, mBtShowPic;

    private ImageView mImgShow;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_1;
    }

    @Override
    protected void initView() {
        mBtSavePic = getRootView().findViewById(R.id.bt_save_pic);
        mBtShowPic = getRootView().findViewById(R.id.bt_show_pic);
        mImgShow = getRootView().findViewById(R.id.img_show);
        mBtSavePic.setOnClickListener(view -> {
//            savePic();
            AssetManager manager = getActivity().getAssets();
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                //采用边读边写的方式
                is = manager.open("gril.jpeg");
                fos = new FileOutputStream(new File(getActivity().getFilesDir(), "gril.jpeg"));
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                Toast.makeText(getContext(), "保存成功", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        mBtShowPic.setOnClickListener(view -> {
//            showPic();
            String[] list = getActivity().getApplication().getFilesDir().list();
            for (String file : list) {
                Log.e(TAG, "initView: " + file);
            }
            String filePath = getActivity().getFilesDir().getAbsolutePath()+"/gril.jpeg";
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            mImgShow.setImageBitmap(bitmap);
        });
    }

    @Override
    protected void initData() {
        File filesDir = getActivity().getApplication().getFilesDir();
        Log.e(TAG, "filesDir: " + filesDir.getAbsolutePath() + " exists: " + filesDir.exists());
        boolean ccx = FileUtils.mkdirCustomDir(getContext(), "ccx");
        Log.e(TAG, "mkdir res: " + ccx);
        String picPath = FileUtils.getFilesDir(getContext()).getAbsolutePath() + File.separator + "ccx" + File.separator + "test.jpg";
    }
}
