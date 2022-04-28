package com.veselin.destinysigns;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ScanPalmActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_PERMISSION = 101;
    public static final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private TextureView tv;
    private ImageView iv;
    private static final String TAG = "RealTimeFaceDetectionActivity";

    public static CameraX.LensFacing lens = CameraX.LensFacing.FRONT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_palm);
        tv = findViewById(R.id.face_texture_view);
        iv = findViewById(R.id.face_image_view);
        if (allPermissionsGranted()) {
            tv.post(this::startCamera);
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSION);
        }
    }

    @SuppressLint("RestrictedApi")
    private void startCamera() {
        initCamera();
        ImageButton ibSwitch = findViewById(R.id.btn_switch_face);
        ibSwitch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                if (lens == CameraX.LensFacing.FRONT)
                    lens = CameraX.LensFacing.BACK;
                else
                    lens = CameraX.LensFacing.FRONT;
                try {
                    Log.i(TAG, "" + lens);
                    CameraX.getCameraWithLensFacing(lens);
                    ScanPalmActivity.this.initCamera();
                } catch (CameraInfoUnavailableException e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
        shotCamera();
    }

    private void initCamera() {
        CameraX.unbindAll();
        PreviewConfig pc = new PreviewConfig
                .Builder()
                .setTargetResolution(new Size(tv.getWidth(), tv.getHeight()))
                .setLensFacing(lens)
                .build();

        Preview preview = new Preview(pc);
        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
            @Override
            public void onUpdated(@NonNull Preview.PreviewOutput output) {
                ViewGroup vg = (ViewGroup) tv.getParent();
                vg.removeView(tv);
                vg.addView(tv, 0);
                tv.setSurfaceTexture(output.getSurfaceTexture());
            }
        });

        ImageAnalysisConfig iac = new ImageAnalysisConfig
                .Builder()
                .setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                .setTargetResolution(new Size(tv.getWidth(), tv.getHeight()))
                .setLensFacing(lens)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis(iac);
//        imageAnalysis.setAnalyzer(Runnable::run,
//                new MLKitFacesAnalyzer(tv, iv, lens));
        CameraX.bindToLifecycle(this, preview, imageAnalysis);
    }

    private void shotCamera(){
        ImageView btn = findViewById(R.id.shot_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(ScanPalmActivity.this, PalmScannerResultsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (allPermissionsGranted()) {
                tv.post(this::startCamera);
            } else {
                Toast.makeText(this,
                        "Permissions not granted by the user.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
