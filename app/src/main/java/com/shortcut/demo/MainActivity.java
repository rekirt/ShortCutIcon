package com.shortcut.demo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    EditText et_message_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_message_count = (EditText)findViewById(R.id.et_message_count);


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String currentHomePackage = resolveInfo.activityInfo.packageName;

        TextView textViewHomePackage = (TextView) findViewById(R.id.tv_package_name);
        textViewHomePackage.setText("launcher:" + currentHomePackage);
    }


    public void show(View v){
        int count = 0;
        try {
            count = Integer.parseInt(et_message_count.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Error input", Toast.LENGTH_SHORT).show();
        }
        boolean success = ShortcutBadger.applyCount(MainActivity.this, count);
        Toast.makeText(getApplicationContext(), "Set count=" + count + ", success=" + success, Toast.LENGTH_SHORT).show();
    }

    public void hiden(View v){
        boolean success = ShortcutBadger.removeCount(MainActivity.this);
        Toast.makeText(getApplicationContext(), "success=" + success, Toast.LENGTH_SHORT).show();
    }



}
