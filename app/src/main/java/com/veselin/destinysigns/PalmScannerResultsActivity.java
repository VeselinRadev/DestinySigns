package com.veselin.destinysigns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class PalmScannerResultsActivity extends AppCompatActivity {
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
        title.setText("Your Palm Says:");
        Random random = new Random();
        text.setText(texts[random.nextInt(12)]);
    }
    String[] texts = {
            "If you have square hand, you are likely to have an organizational & practical approach towards life. You may help out many with your remarkable practicality.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "You are a born leader. You are responsible by nature and often spend hours on self introspection and self reflection. You generally set very high standards for yourself & also for those around you.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "You are a disciplinarian who hates to break discipline, laws and regulations. Sticking to set rules gives you mental satisfaction.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "You are eloquent, expressive, communicative and come with great oratorical skills and many hidden talents.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "If the head line ends in a fork like shape, you will be excellent at debating, at using your sea of imagination and psychic powers all through life.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "If your headline is thin and wavery, you may always be preyed by dialectic between the emotional & practical thoughts. Your attention span will be short lived and you may be restless by nature.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "Depression, melancholia and disappointments may torment you often. Life could be full of struggles but you learn as you struggle. So, cheer up!\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "You are a non-complex, practical individual who always speak plainly and to the point.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "Heart line may have several distributaries coming out of it. If the branching lines move upward, you may feel a magnetic attraction towards opposite sex and may have a fulfilling positive relationship. If the branching lines move downward, relationship sector may encounter a lot of challenges and spells of unhappiness.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "You may wear your heart on your sleeves; giving and taking heart may seem a child's play to you and you may be least bothered about the real meaning of the emotion called love and the responsibilities related to it.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "No one can beat you in your professional. Star sits on your destiny and promises fortune, wealth, prosperity and a highly productive professional life.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "A helpful relative or family member is likely to shower his or her grace on you by supporting you constantly & consistently all along your career path.\nYour sign is linked with your roller coaster emotions.\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.",
            "Although your desk is sparkling clean today, the bright shine emanating from the surface may be hiding an oversight. Associates might throw some hurdles into your grand master plan and  your bank account may seem to be colluding now. Moving too fast can sometimes not give others enough time to recalibrate to your forward motion. Heed the hiccups; trust that everyone will eventually catch up.\\nYour sign is linked with your roller coaster emotions.\\nYou’re a true romantic. You enjoy almost everything about being in a partnership, and even when it’s bad you’ll do everything in your power to make improvements.\\nKeep putting in the work for maximum rewards. You have a real feel for money now, and any investments you make should be geared toward the long-term.\""
    };
    @Override
    public void onBackPressed() {
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            PalmScannerResultsActivity.super.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }
}