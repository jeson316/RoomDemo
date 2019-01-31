package com.example.room.jeson316.roomdemo.animdemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.room.jeson316.roomdemo.R;

/**
 * Created by Jeson316 on 2019/1/29.
 */
public class ValueAnimActivity extends AppCompatActivity {

    private LinearLayout ll_btns;
    private ImageView iv;


    public static Intent createInstance(Context context) {
        return new Intent(context, ValueAnimActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        initUI();
    }

    private void initUI() {
        ll_btns = findViewById(R.id.ll_button_container);
        iv = findViewById(R.id.iv_anim);
        addListener();

        addButton("Test",null);
    }

    private void addListener() {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ValueAnimActivity.this, " 我是图片" + iv.getTop(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addButton(String name, @Nullable Animator cls) {
        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText(name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animGo(iv);
            }
        });
        ll_btns.addView(button);
    }


    private void animGo(final View view) {
        ValueAnimator animator = ValueAnimator.ofInt(view.getTop(), -1000);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
//                view.layout(view.getLeft(), value, view.getRight(), view.getBottom());
                view.layout(view.getLeft(), value, view.getRight(), view.getBottom());
            }
        });
        animator.start();
    }
}
