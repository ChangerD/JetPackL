 package com.huace.jetpacklearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huace.jetpacklearn.component.WorkManagerActivity;

import androidx.appcompat.app.AppCompatActivity;

 /**
  * @author lenovo
  */
 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,WorkManagerActivity.class));
            }
        });

    }
}
