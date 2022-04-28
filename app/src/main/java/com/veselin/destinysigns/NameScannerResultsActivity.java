package com.veselin.destinysigns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class NameScannerResultsActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd = new InterstitialAd(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palm_scanner_results);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        setup();
    }
    private void setup(){
        TextView title = findViewById(R.id.title);
        TextView text = findViewById(R.id.text);
        title.setText("Your Face Says:");
        Random random = new Random();
        text.setText(texts[random.nextInt(12)]);
    }
    //Fill texts with relevant descriptions
    String[] texts = {
            "Heyy!! You’re facial features tell that you’re attractive and charismatic.",
            "Heyy!! You’re facial features tell that you’re confident and daring.",
            "Heyy!! You’re facial features tell that you’re earnest and fair.",
            "Heyy!! You’re facial features tell that you’re elegant and extraordinary.",
            "Heyy!! You’re facial features tell that you’re faithful and friendly.",
            "Heyy!! You’re facial features tell that you’re humorous and fun-loving.",
            "Heyy!! You’re facial features tell that you’re insightful and innovative.",
            "Heyy!! You’re facial features tell that you’re open and optimistic",
            "Heyy!! You’re facial features tell that you’re romantic and sensitive.",
            "Heyy!! You’re facial features tell that you’re warm and lovable."
    };
    @Override
    public void onBackPressed() {
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            NameScannerResultsActivity.super.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }
}