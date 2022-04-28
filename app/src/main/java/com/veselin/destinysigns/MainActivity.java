package com.veselin.destinysigns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView DivingBallButton = findViewById(R.id.divining_ball_btn);
        DivingBallButton.setOnClickListener(MenuItemSelected);

        CardView LoveMatchingButton = findViewById(R.id.love_match_btn);
        LoveMatchingButton.setOnClickListener(MenuItemSelected);

        CardView ScanFaceButton = findViewById(R.id.scan_face_btn);
        ScanFaceButton.setOnClickListener(MenuItemSelected);

        CardView ScanPalmButton = findViewById(R.id.scan_palm_btn);
        ScanPalmButton.setOnClickListener(MenuItemSelected);

        CardView NameMeaningButton = findViewById(R.id.name_meaning_btn);
        NameMeaningButton.setOnClickListener(MenuItemSelected);

    }

    private View.OnClickListener MenuItemSelected = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.divining_ball_btn:
                    intent = new Intent(MainActivity.this, DiviningBallActivity.class);
                    break;
                case R.id.love_match_btn:
                    intent = new Intent(MainActivity.this, LoveMatchingActivity.class);
                    break;
                case R.id.scan_face_btn:
                    intent = new Intent(MainActivity.this, RealTimeFaceDetectionActivity.class);
                    break;
                case R.id.scan_palm_btn:
                    intent = new Intent(MainActivity.this, ScanPalmActivity.class);
                    break;
                case R.id.name_meaning_btn:
                    intent = new Intent(MainActivity.this, NameMeaningActivity.class);
                    break;
            }
            startActivity(intent);
        }
    };

}