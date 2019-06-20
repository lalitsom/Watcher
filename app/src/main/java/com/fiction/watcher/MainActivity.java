package com.fiction.watcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);
        startService(new Intent(this,WatcherService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public  void update(View V){
        String file_data = read_file();
        TextView plain_text = (TextView) findViewById(R.id.plain_text);
        plain_text.setText(file_data);
    }


    public  String read_file(){
        String filename = "Watcher_logs";
        FileInputStream InputStream;


        File file = getBaseContext().getFileStreamPath(filename);
        StringBuffer fileContent = new StringBuffer("");
        if (file.exists()) {
            try {
                InputStream = openFileInput(filename);

                byte[] buffer = new byte[1024];
                int n;
                while ((n = InputStream.read(buffer)) != -1)                {
                    fileContent.append(new String(buffer, 0, n));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        Log.e(this.getClass().toString(),fileContent.toString());
        return fileContent.toString();
    }

}
