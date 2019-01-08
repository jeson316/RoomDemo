package com.example.room.jeson316.roomdemo.others;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.room.jeson316.roomdemo.R;


/**
 * 测试使用layer-list作为背景图片时候，
 * item中距离属性top、bottom ...等会根据不同分辨率进行适配
 * <p>
 * 注意：该activiy的背景通过theme主题进行的配置 layer-list
 */
public class LayerListDemoActivity extends AppCompatActivity {

    public static Intent createInstance(Context context) {
        return new Intent(context, LayerListDemoActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_base_container);
    }
}
