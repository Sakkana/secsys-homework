package com.smali.secretchallenge;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.pore.mylibrary.PoRELab;
import com.smali.myapplication.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 在子线程中更新 UI
        changeUI();

        // 访问私有成员
        try {
            privateMemberAccess();
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public void privateMemberAccess() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        // 访问 curStr
        Field field = Class
                .forName("com.pore.mylibrary.PoRELab")
                .getDeclaredField("curStr");

        field.setAccessible(true);

        PoRELab poRELab = new PoRELab();
        Object strCur = field.get(poRELab);

        assert strCur != null;
        System.out.println("I get priavte member strCur: " + strCur.toString());

        // 访问 privateMethod
        Method method = Class
                .forName("com.pore.mylibrary.PoRELab")
                .getDeclaredMethod("privateMethod", new Class[]{String.class, String.class});

        method.setAccessible(true);
        method.invoke(poRELab, "Sakana", "Here");
    }

    public void changeUI() {
        EditText editTextInput = findViewById(R.id.editTextInput);
        Button buttonShowDialog = findViewById(R.id.buttonShowDialog);

        buttonShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                showDialog(editTextInput.getText().toString());
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void showDialog(String inputText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(inputText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}