package com.huace.jetpacklearn.component;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.huace.jetpacklearn.R;
import com.huace.jetpacklearn.viewmodel.NameViewModel;

/**
 * @author lenovo
 */
public class LiveDataActivity extends AppCompatActivity {

    NameViewModel nameViewModel;

    TextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        nameTV = findViewById(R.id.tv_name);
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nameTV.setText(s);
            }
        };
        nameViewModel.getName().observe(this, nameObserver);
    }
}
