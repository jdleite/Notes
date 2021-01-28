package br.com.notes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.notes.R;

import java.util.TimerTask;

public class IntrodutoryActivity extends AppCompatActivity {
    ImageView logo,appName,splashImg;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intodutory);




                logo = findViewById(R.id.logo);
                appName = findViewById(R.id.app_name);
                splashImg = findViewById(R.id.img);
                lottieAnimationView = findViewById(R.id.lottie);

                splashImg.animate().translationY(-2600).setDuration(1000).setStartDelay(2000);
                logo.animate().translationY(2400).setDuration(1000).setStartDelay(2000);
                appName.animate().translationY(2400).setDuration(1000).setStartDelay(2000);
                lottieAnimationView.animate().translationY(2400).setDuration(1000).setStartDelay(2000);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(IntrodutoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },3500);





    }
}