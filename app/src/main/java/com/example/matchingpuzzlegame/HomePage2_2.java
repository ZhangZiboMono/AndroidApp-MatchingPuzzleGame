package com.example.matchingpuzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class HomePage2_2 extends AppCompatActivity {

    MediaPlayer welBgm;
    Button button1, button2, button3, button4;

    private View decorView;
    TextView tv_1;

    double score1;
    double score2;
    double score12;

    DecimalFormat df = new DecimalFormat("######0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2_2);

        score2 = getIntent().getExtras().getDouble("Value1");
        score1 = getIntent().getExtras().getDouble("Value2");

        welBgm = MediaPlayer.create(HomePage2_2.this,R.raw.bgm_wel);
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
        Animation animation= AnimationUtils.loadAnimation(HomePage2_2.this,R.anim.bounce);


        tv_1 = (TextView)findViewById(R.id.tv_1);

        tv_1.startAnimation(animation);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.but_letsgo);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        button1.setText("Level-1\nScore: "+df.format(score1));
        button2.setText("Level-2\nScore: "+df.format(score2));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRes();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRes2();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(getApplicationContext(), MainActivityL3.class);
                score12 = score1 + score2;
                intent.putExtra("Value2",score12);
                intent.putExtra("Value1",score1);
                startActivity(intent);
                finish();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void showRes() {
        final Dialog dialog=new Dialog(HomePage2_2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.result_dialog);
        Button sure_guide=dialog.findViewById(R.id.sure_guide);
        TextView resultShow=dialog.findViewById(R.id.resultShow);
        resultShow.setText("Your Level-1 score is: "+df.format(score1)+"\nConquer the next level");

        sure_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showRes2() {
        final Dialog dialog=new Dialog(HomePage2_2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.result_dialog);
        Button sure_guide=dialog.findViewById(R.id.sure_guide);
        TextView resultShow=dialog.findViewById(R.id.resultShow);
        resultShow.setText("Your Level-2 score is: "+df.format(score2)+"\nConquer the next level");

        sure_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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

    }

    @Override
    protected void onPause() {
        super.onPause();
        welBgm.release();
        finish();
    }
}
