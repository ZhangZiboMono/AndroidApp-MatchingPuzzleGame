package com.example.matchingpuzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

public class EndingScreen extends AppCompatActivity {

    MediaPlayer endBgm;

    Button restart;
    Button quit;
    private View decorView;

    double score3;
    TextView tv, tv2;
    ImageView iv;

    DecimalFormat df = new DecimalFormat("######0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);

        score3 = getIntent().getExtras().getDouble("Value3");

        endBgm = MediaPlayer.create(EndingScreen.this,R.raw.bgm_end);
        endBgm.start();

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        ImageView welcome_gif = (ImageView) findViewById(R.id.welcome_gif);
        Glide.with(this).load(R.drawable.ending).into(welcome_gif);

        ImageView welcome_gif2 = (ImageView) findViewById(R.id.welcome_gif2);
        Glide.with(this).load(R.drawable.ending).into(welcome_gif2);

        Animation animation= AnimationUtils.loadAnimation(EndingScreen.this,R.anim.bounce);
        Animation animation2= AnimationUtils.loadAnimation(EndingScreen.this,R.anim.mixed_anim);

        tv = (TextView)findViewById(R.id.tv);
        tv2 = (TextView)findViewById(R.id.tv2);
        iv = (ImageView)findViewById(R.id.iv);

        iv.startAnimation(animation2);


        tv2.setText("Your total score is \n"+df.format(score3));
        tv.startAnimation(animation);
        tv2.startAnimation(animation);

        restart = (Button)findViewById(R.id.restart);
        quit = (Button)findViewById(R.id.quit);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
    protected void onPause() {
        super.onPause();
        endBgm.release();
        finish();
    }
}
