package com.liyunkun.week6_1custortoolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.liyunkun.cutortoolbar.CustorToolBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustorToolBar toolBar = (CustorToolBar) findViewById(R.id.toolbar);
        //自定义Toolbar的监听（实现其中的两个接口）
        toolBar.setImgClick(new CustorToolBar.IOnImgClick() {
            @Override
            public void onLeftImgClick() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightImgClick() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
