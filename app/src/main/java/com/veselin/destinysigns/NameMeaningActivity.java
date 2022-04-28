package com.veselin.destinysigns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class NameMeaningActivity extends AppCompatActivity {
    String answer = "Your name describes you as: \n ";
    InterstitialAd mInterstitialAd = new InterstitialAd(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_meaning);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        Button btn = (Button) findViewById(R.id.find_meaning_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "Your name describes you as: \n ";
                EditText nameTxt = findViewById(R.id.name_txt);
                TextView resultTxt = findViewById(R.id.name_description_txt);
                String name = String.valueOf(nameTxt.getText()).toLowerCase();
                if (!NameMeaningActivity.this.isStringContainsLatinCharactersOnly(name))
                    Toast.makeText(NameMeaningActivity.this, "Please use only latin letters for your name!", Toast.LENGTH_SHORT).show();
                else {
                    String[] letters = name.split("");
                    for (String letter : letters) {
                        answer += NameMeaningActivity.this.getRandomWord(letter) + ", ";
                    }
                    answer = answer.substring(0, answer.lastIndexOf(",")) + "!";
                    answer = answer.substring(0, answer.lastIndexOf(",")) + " and" + answer.substring(answer.lastIndexOf(",") + 1);
                    resultTxt.setText(answer);
                    resultTxt.setVisibility(View.VISIBLE);

                }

            }
        });
    }

    private boolean isStringContainsLatinCharactersOnly(String iStringToCheck)
    {
        return iStringToCheck.matches("^[a-z.]+$");
    }

    private String getRandomWord(String letter){
        String word;
        Random random = new Random();
        String[] words;
        switch (letter) {
            case "a":
                words = A;
                break;
            case "b":
                words = B;
                break;
            case "c":
                words = C;
                break;
            case "d":
                words = D;
                break;
            case "e":
                words = E;
                break;
            case "f":
                words = F;
                break;
            case "g":
                words = G;
                break;
            case "h":
                words = H;
                break;
            case "i":
                words = I;
                break;
            case "j":
                words = J;
                break;
            case "k":
                words = K;
                break;
            case "l":
                words = L;
                break;
            case "m":
                words = M;
                break;
            case "n":
                words = N;
                break;
            case "o":
                words = O;
                break;
            case "p":
                words = P;
                break;
            case "q":
                words = Q;
                break;
            case "r":
                words = Rl;
                break;
            case "s":
                words = S;
                break;
            case "t":
                words = T;
                break;
            case "u":
                words = U;
                break;
            case "v":
                words = V;
                break;
            case "w":
                words = W;
                break;
            case "x":
                words = X;
                break;
            case "y":
                words = Y;
                break;
            case "z":
                words = Z;
                break;
            default:
                words = new String[5];
        }
        word = words[random.nextInt(4)];
        return word;
    }
    String[] A = {"Amazing", "Artistic", "Adaptable", "Athletic", "Adventurous"};
    String[] B = {"Beloved", "Beautiful", "Brainy", "Best", "Boundless"};
    String[] C = {"Champion", "Clever", "Capable", "Confident", "Captain"};
    String[] D = {"Diverse", "Decent", "Disciplined", "Dynamic", "Divine"};
    String[] E = {"Earthly", "Ecstatic", "Entertaining", "Expressive", "Energetic"};
    String[] F = {"Fascinating", "Funny", "Fabulous", "Fearless", "Fantastic"};
    String[] G = {"Glamorous", "Gorgeous", "Greatest", "Gutsy", "Gentle"};
    String[] H = {"Happy", "Humble", "Healthy", "Honest", "Handsome"};
    String[] I = {"Immortal", "Inspiring", "Impressive", "Iconic", "Incredible"};
    String[] J = {"Jovial", "Jarring", "Jesting", "Jolly", "Joyful"};
    String[] K = {"Kind", "Knowledgeable", "Keen", "Kind", "Knowledgeable"};
    String[] L = {"Leader", "Logical", "Loyal", "Lovely", "Legend"};
    String[] M = {"Mysterious", "Marvelous", "Maestro", "Magical", "Majestic"};
    String[] N = {"Native", "Nice", "Natural", "Notable", "Nervous"};
    String[] O = {"Original", "Obedient", "Optimistic", "Observant", "Odd"};
    String[] P = {"Peaceful", "Powerful", "Positive", "Popular", "Patriotic"};
    String[] Q = {"Qualified", "Quick", "Quaint", "Quality", "Quaint"};
    String[] Rl = {"Responsible", "Reliable", "Royal", "Ruler", "Romantic"};
    String[] S = {"Seductive", "Self-confident", "Saintly", "Saviour", "Selfish"};
    String[] T = {"Truthful", "Thankful", "Trendy", "Tactical", "Tough"};
    String[] U = {"Understanding", "Unbeatable", "Useful", "Unique", "Undivided"};
    String[] V = {"Victorious", "Vague", "Valuable", "Visionary", "Versatile"};
    String[] W = {"Worthy", "Wild", "Wonderful", "Wealthy", "Wise"};
    String[] X = {"Xfactor", "Xtra", "Xerothermic", "Xenogeneic", "Xtra"};
    String[] Y = {"Young", "Yield", "Yummy", "Youthful", "Young"};
    String[] Z = {"Zany", "Zippy", "Zealous", "Zymolytic", "Zingy"};
    @Override
    public void onBackPressed() {
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            NameMeaningActivity.super.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }
}