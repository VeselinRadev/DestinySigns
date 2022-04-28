package com.veselin.destinysigns;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class LoveMatchingActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd = new InterstitialAd(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_matching);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        setup();
    }

    private void setup(){
        Button btn = findViewById(R.id.calculate_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                EditText first = findViewById(R.id.first_name_txt);
                EditText second = findViewById(R.id.second_name_txt);
                if(first.length() == 0 && second.length() == 0)
                    Toast.makeText(LoveMatchingActivity.this, "Please enter names", Toast.LENGTH_SHORT).show();
                else {
                    TextView txt = findViewById(R.id.love_percent_txt);
                    FrameLayout frameLayout = findViewById(R.id.result_layout);
                    frameLayout.setVisibility(View.VISIBLE);
                    String percent = percentLove() + "%";
                    txt.setText(percent);
                    Toast.makeText(LoveMatchingActivity.this, first.getText() + " and " + second.getText() + " have " + percent + " match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private String percentLove(){
        Random random = new Random();
        return String.valueOf(random.nextInt(100));
    }
    @Override
    public void onBackPressed() {
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            LoveMatchingActivity.super.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }
}