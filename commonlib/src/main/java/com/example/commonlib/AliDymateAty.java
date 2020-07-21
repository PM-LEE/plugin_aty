package com.example.commonlib;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public interface AliDymateAty {
    void attachAty(Activity baseAty);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onStop();

    <T extends View> T findViewById(int id);

    Window getWindow();

    Resources getResources();
    void onResume();

}
