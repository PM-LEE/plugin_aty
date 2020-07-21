package com.example.alipayplaug;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.commonlib.AliDymateAty;

public class ProxyActivity extends Activity {

    private AliDymateAty  aliDymateAty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String className = getIntent().getStringExtra("className");
        try {
            Class<?> aClass = getClassLoader().loadClass(className);
            aliDymateAty = ((AliDymateAty) aClass.newInstance());
            aliDymateAty.attachAty(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        aliDymateAty.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        aliDymateAty.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        aliDymateAty.onResume();
    }

    @Override
    public void startActivity(Intent intent) {
        Intent intent1 = new Intent(this, ProxyActivity.class);
        if (intent.getComponent()!=null&&intent.getComponent().getClassName()!=null){
            intent1.putExtra("className",intent.getComponent().getClassName());
        }else
        intent1.putExtra("className",intent.getStringExtra("className"));
        super.startActivity(intent1);
    }

    @Override
    public ClassLoader getClassLoader() {
        Log.e("ProxyActivity","getClassLoader()");
        return ApkLoader.getInstance().getClassLoader();
    }

    @Override
    public Resources getResources() {
        Log.e("ProxyActivity","getResources()");
        return ApkLoader.getInstance().getResurce();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return super.getApplicationInfo();
    }
}
