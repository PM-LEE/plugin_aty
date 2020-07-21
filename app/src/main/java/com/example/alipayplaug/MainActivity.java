package com.example.alipayplaug;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onload();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoZ();
            }
        });
    }

    public void onload() {
        File file = new File(Environment.getExternalStorageDirectory(), "plug2.apk");
        if (file.exists())
            ApkLoader.getInstance().loadFromPath(file.getAbsolutePath(), this.getApplicationContext());
    }

    public void gotoZ() {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", ApkLoader.getInstance().getLaunchAty());
        startActivity(intent);
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }
}
