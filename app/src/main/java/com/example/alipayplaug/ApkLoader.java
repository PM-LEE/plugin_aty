package com.example.alipayplaug;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class ApkLoader {
    private static final ApkLoader ourInstance = new ApkLoader();

    private DexClassLoader dexClassLoader;
    private Resources apkResurce;
    private  Context mContext;
    private String pagename;
    private String launchAty;

    public static ApkLoader getInstance() {
        return ourInstance;
    }

    private ApkLoader() {

    }

    /**
     * 加载dex 文件
     * @param path
     */
    public void loadFromPath(String path,Context context){
        mContext = context;
        File dexFile = mContext.getDir("dex",Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(path,dexFile.getAbsolutePath(),null,mContext.getClassLoader());
        //resource
        try {
            Class<?> aClass = Class.forName("android.content.res.AssetManager");
            Object newInstance = aClass.getConstructor().newInstance();
            Method addAssetPath = aClass.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(newInstance,path);

            apkResurce = new Resources((AssetManager)newInstance,mContext.getResources().getDisplayMetrics(),mContext.getResources().getConfiguration());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return;
        }

        PackageManager manager = context.getPackageManager();
        PackageInfo packageArchiveInfo = manager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        for (ActivityInfo activity : packageArchiveInfo.activities) {
            if (activity.name.contains("MainActivity")){
                launchAty = activity.name;
            }
        }

        pagename = packageArchiveInfo.packageName;


        Toast.makeText(mContext,"Load apk success:"+pagename,0).show();


    }

    public Resources getResurce(){
        return apkResurce;
    }

    public DexClassLoader getClassLoader(){
        return dexClassLoader;
    }

    public String getPagename(){
        return pagename;
    }


    public String getLaunchAty() {
        return launchAty;
    }
}
