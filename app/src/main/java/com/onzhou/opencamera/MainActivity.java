package com.onzhou.opencamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;

import com.onzhou.live.ILiveRoom;
import com.onzhou.live.LiveRoom;

import junit.framework.Assert;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE_CAMERA = 1000;

    private SurfaceView cameraView;

    private ILiveRoom mLiveRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CODE_CAMERA);
        } else {
            setupViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE_CAMERA != requestCode) {
            super.onRequestPermissionsResult(requestCode,
                    permissions,
                    grantResults);

        } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupViews();
        }
    }

    private void setupViews() {
        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        mLiveRoom = new LiveRoom();
        mLiveRoom.setPreviewView(cameraView);
    }

}
