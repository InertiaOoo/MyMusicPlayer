package com.ooo.deemo.mymusicplayer;

import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.ooo.deemo.mymusicplayer.Fragment.FragmentFree;
import com.ooo.deemo.mymusicplayer.Fragment.FragmentMain;

import java.util.Timer;
import java.util.TimerTask;


public class TheMainActivity extends AppCompatActivity implements View.OnClickListener ,
        FragmentMain.OnFragmentInteractionListener,FragmentFree.OnFragmentInteractionListener{

    private Button firstbt;
    private Button secondbt;

    private static boolean mBackKeyPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


//setStatusBar();
       hideBottomUIMenu();

        firstbt = findViewById(R.id.fa_first_bt);
        secondbt = findViewById(R.id.fa_second_bt);
        replaceFragment(new FragmentFree());


        firstbt.setOnClickListener(this);
        secondbt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fa_first_bt:
                replaceFragment(new FragmentFree());

                break;


            case R.id.fa_second_bt:
                replaceFragment(new FragmentMain());
                break;


            default:


                break;

        }


    }
    //隐藏底部栏
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    //状态栏透明
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }

    //双击返回键退出程序
    @Override
    public void onBackPressed() {


        hideBottomUIMenu();


        if(!mBackKeyPressed){

            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

            mBackKeyPressed=true;

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            },2000);
        }else {

            this.finish();
            System.exit(0);
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentView,fragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}