package com.smali.secretchallenge;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.smali.myapplication.R;

/**
 * 完成 lab1 的功能
 *
 * 1. 让应用开机自启
 * 2. 在没有用户操作的情况下启动一个 service
 *
 */
public class SecretBootReceiver extends BroadcastReceiver {
    private Activity activity;

    public SecretBootReceiver() {

    }

    public SecretBootReceiver(Activity activity) {
        this.activity = activity;
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // 启动 Activity
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);

            // 启动 SecretService
            Intent serviceIntent = new Intent(context, SecretService.class);

            // 启动后台线程
            if (ContextCompat
                    .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // 尚未授予权限 -> 授予
                ActivityCompat
                        .requestPermissions((Activity) context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            } else {
                context.startService(serviceIntent);
                Log.d("SecretBootService", "start thread!");
            }
        }
    }
}
