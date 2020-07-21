package com.example.taobao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.commonlib.AliDymateAty;

public class BaseActivity extends Activity implements AliDymateAty {

    protected Activity proxyActivity;

    @Override
    public void attachAty(Activity baseAty) {

        this.proxyActivity = baseAty;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (proxyActivity==null)
            super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {
        if (proxyActivity==null)
            super.onStart();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
        if (proxyActivity==null)
            super.onStop();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (proxyActivity != null)
            proxyActivity.setContentView(layoutResID);
        else
            super.setContentView(layoutResID);
    }

    @Override
    public Window getWindow() {
        if (proxyActivity != null)
            return proxyActivity.getWindow();
        else
            return super.getWindow();
    }


    @Override
    public Resources getResources() {
        if (proxyActivity != null)
            return proxyActivity.getResources();
        else
            return super.getResources();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {
    }

    @Override
    public ClassLoader getClassLoader() {
        if (proxyActivity != null)
            return proxyActivity.getClassLoader();
        else
            return super.getClassLoader();
    }

    @Override
    public WindowManager getWindowManager() {
        if (proxyActivity != null)
            return proxyActivity.getWindowManager();
        else
            return super.getWindowManager();
    }

    @Override
    public void startActivity(Intent intent) {
        if (proxyActivity != null) {
            intent.putExtra("className", intent.getComponent().getClassName());
            proxyActivity.startActivity(intent);
        } else
            super.startActivity(intent);
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (proxyActivity != null) {
            return proxyActivity.getApplicationInfo();
        } else
            return super.getApplicationInfo();
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (proxyActivity != null) {
            return proxyActivity.getLayoutInflater();
        } else
        return super.getLayoutInflater();
    }

    @Override
    public View findViewById(int id) {
        return super.findViewById(id);
    }
}
