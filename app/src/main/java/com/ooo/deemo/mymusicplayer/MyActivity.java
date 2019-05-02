package com.ooo.deemo.mymusicplayer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.litepal.LitePal;

public class MyActivity extends AppCompatActivity {

    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        LitePal.initialize(this);
    }

public static Context getContext(){

        return mContext;

}

}
