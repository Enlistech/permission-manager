/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Enlistech
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.enlistech.permissionhelper

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.*

class FragmentManagePermission : Fragment() {
    private val KEY_PERMISSION = 999
    private var permissionResult: PermissionResult? = null
    private lateinit var permissionsAsk: Array<String>
    override fun onCreate(arg0: Bundle?) {
        super.onCreate(arg0)
        retainInstance = false
    }

    /**
     * @param context       current Context
     * @param permission    String permission to ask
     * @return boolean      true/false
     */
    fun isPermissionGranted(context: Context?, permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ContextCompat.checkSelfPermission(context!!, permission!!) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * @param context       current Context
     * @param permissions   String[] permission to ask
     * @return boolean      true/false
     */
    fun isPermissionsGranted(context: Context?, permissions: Array<String?>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
        var granted = true
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context!!, permission!!) != PackageManager.PERMISSION_GRANTED) granted = false
        }
        return granted
    }

    private fun internalRequestPermission(permissionAsk: Array<String>) {
        var arrayPermissionNotGranted: Array<String?>
        val permissionsNotGranted = ArrayList<String>()
        for (i in permissionAsk.indices) {
            if (!isPermissionGranted(activity, permissionAsk[i])) {
                permissionsNotGranted.add(permissionAsk[i])
            }
        }
        if (permissionsNotGranted.isEmpty()) {
            if (permissionResult != null) permissionResult!!.permissionGranted()
        } else {
            arrayPermissionNotGranted = arrayOfNulls(permissionsNotGranted.size)
            arrayPermissionNotGranted = permissionsNotGranted.toArray(arrayPermissionNotGranted)
            requestPermissions(arrayPermissionNotGranted, KEY_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != KEY_PERMISSION) {
            return
        }
        val permissionDenied: List<String> = LinkedList()
        var granted = true
        for (grantResult in grantResults) {
            if (!(grantResults.size > 0 && grantResult == PackageManager.PERMISSION_GRANTED)) granted = false
        }
        if (permissionResult != null) {
            if (granted) {
                permissionResult!!.permissionGranted()
            } else {
                for (s in permissionDenied) {
                    if (!shouldShowRequestPermissionRationale(s)) {
                        permissionResult!!.permissionForeverDenied()
                        return
                    }
                }
                permissionResult!!.permissionDenied()
            }
        }
    }

    /**
     * @param permission        String permission ask
     * @param permissionResult  callback PermissionResult
     */
    fun askCompactPermission(permission: String, permissionResult: PermissionResult?) {
        permissionsAsk = arrayOf(permission)
        this.permissionResult = permissionResult
        internalRequestPermission(permissionsAsk)
    }

    /**
     * @param permissions       String[] permissions to ask
     * @param permissionResult  callback PermissionResult
     */
    fun askCompactPermissions(permissions: Array<String>, permissionResult: PermissionResult?) {
        permissionsAsk = permissions
        this.permissionResult = permissionResult
        internalRequestPermission(permissionsAsk)
    }

    fun openSettingsApp(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + context.packageName)
        startActivity(intent)
    }
}