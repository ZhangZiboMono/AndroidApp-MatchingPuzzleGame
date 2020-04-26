package com.example.matchingpuzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.Delayed;

public class WelcomeScreen extends AppCompatActivity {

    MediaPlayer welBgm;
    Button but;

    private View decorView;
    TextView tv_1;
    TextView tv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        welBgm = MediaPlayer.create(WelcomeScreen.this,R.raw.bgm_wel);
        welBgm.start();

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });
        Animation animation= AnimationUtils.loadAnimation(WelcomeScreen.this,R.anim.bounce);
        Animation animation2= AnimationUtils.loadAnimation(WelcomeScreen.this,R.anim.sample_anim3);

        ImageView welcome_gif = (ImageView) findViewById(R.id.welcome_gif);
        Glide.with(this).load(R.drawable.welcome_cha).into(welcome_gif);

        ImageView welcome_gif2 = (ImageView) findViewById(R.id.welcome_gif2);
        Glide.with(this).load(R.drawable.welcome_loa).into(welcome_gif2);

        welcome_gif.startAnimation(animation2);

        tv_1 = (TextView)findViewById(R.id.tv_1);
        tv_2 = (TextView)findViewById(R.id.tv_2);

        tv_1.startAnimation(animation);
        tv_2.startAnimation(animation);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    @Override
    protected void onResume() {
        super.onResume();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.but_letsgo);
        but = (Button)findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                startActivity(new Intent(WelcomeScreen.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        welBgm.release();
        finish();
    }
}
