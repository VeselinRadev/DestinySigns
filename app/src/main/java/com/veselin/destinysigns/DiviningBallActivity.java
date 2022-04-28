package com.veselin.destinysigns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;


public class DiviningBallActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private String[] answers = {"No", "Yes", "No opinion", "Surely", "Maybe", "Rather no",
            "I will say yes", "Complicated", "Certainly", "Perhaps", "Not really"};
    InterstitialAd mInterstitialAd = new InterstitialAd(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divining_ball);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        descrpitonToast();
        initialiseShakingFunctionality();
    }

    private void descrpitonToast(){
        Toast.makeText(this, "Ask the BALL \"YES/NO\" question then shake it or click it and you will know the answer", Toast.LENGTH_LONG).show();
    }

    private String randomAnswer(){
        Random random = new Random();
        int index = random.nextInt(11);
        return answers[index];
    }

    private void initialiseShakingFunctionality(){
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                activateBall();
            }
        });

        ImageView ballImage = findViewById(R.id.ball_image);
        ballImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateBall();
            }
        });
    }

    private void activateBall(){
        ImageView ballImage = findViewById(R.id.ball_image);
        ballImage.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake));
        TextView answerText = findViewById(R.id.answer_txt);
        answerText.setText(randomAnswer());
        answerText.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
    }

    @Override
    public void onResume() {
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            DiviningBallActivity.super.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }
}