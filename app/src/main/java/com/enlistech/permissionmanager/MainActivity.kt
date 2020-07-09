package com.enlistech.permissionmanager

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.enlistech.permissionhelper.ActivityManagePermission
import com.enlistech.permissionhelper.PermissionResult

class MainActivity : ActivityManagePermission() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent()
    }

    private fun setContent() {
        val btnOpenCamera = findViewById<View>(R.id.btn_openCamera) as Button
        btnOpenCamera.setOnClickListener {
            val permissionAsk = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            askCompactPermissions(permissionAsk, object : PermissionResult {
                override fun permissionGranted() {}
                override fun permissionDenied() {}
                override fun permissionForeverDenied() {}
            })
        }
    }
}