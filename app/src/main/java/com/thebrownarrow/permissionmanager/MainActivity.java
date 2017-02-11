package com.thebrownarrow.permissionmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thebrownarrow.permissionhelper.ActivityManagePermission;
import com.thebrownarrow.permissionhelper.PermissionResult;
import com.thebrownarrow.permissionhelper.PermissionUtils;

public class MainActivity extends ActivityManagePermission {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContent();
    }

    private void setContent() {
        Button btnOpenCamera = (Button) findViewById(R.id.btn_openCamera);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String permissionAsk[] = {PermissionUtils.Manifest_CAMERA, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE};

                askCompactPermissions(permissionAsk, new PermissionResult() {
                    @Override
                    public void permissionGranted() {

                    }

                    @Override
                    public void permissionDenied() {

                    }

                    @Override
                    public void permissionForeverDenied() {

                    }
                });
            }
        });
    }
}
