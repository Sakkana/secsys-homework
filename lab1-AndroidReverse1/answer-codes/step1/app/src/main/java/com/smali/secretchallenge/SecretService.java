package com.smali.secretchallenge;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.security.Provider;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 被启动的服务
 *
 * 1. 获得设备的 GPS 定位
 *
 * 2. 每隔 3s 使用 Toast 在屏幕上显示经纬度
 * 在 Android 开发中，Toast 是一种轻量级的通知方式，用于在屏幕上显示短暂的消息。
 * 它通常用于向用户提供简短的提示、反馈或信息。
 * Toast 消息以短暂的方式显示在屏幕上，通常是在屏幕底部的中间位置。
 * 它会在一段时间后自动消失，不需要用户进行交互操作。
 *
 */
public class SecretService extends Service {
    private static final String TAG = "SecretService";
    private LocationManager locationManager;
    private LocationListener locationListener;
    private TimerTask timerTask;
    private Timer timer;
    private Handler handler;
    private double latitude;
    private double longitude;

    public SecretService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("ServiceCast")
    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // 获取定位信息
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // 获取经纬度
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                // 后台日志
                Log.d(TAG, "[Latitude: " + latitude + "] & [Longitude: " + longitude + "]");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }

        };

        // 检查权限并请求更新
        if (ActivityCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "Location Permission Denied!");
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        // 开启定时器
        startTimer();
    }

    // 创建并启动定时器
    private void startTimer() {
        handler = new Handler(Looper.getMainLooper());
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Toast 信息到前端
                        String ToastMessage = "Location Information\n[Latitude: " + latitude + "] \n [Longitude: " + longitude + "]";
                        showToast(ToastMessage);
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 3000);;
    }

    // 关闭定时器
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        } else {
            timerTask.cancel();
        }
    }

    // 显示 Toast
    private void showToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    // 服务启动
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
