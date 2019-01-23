package com.huace.jetpacklearn.component;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.huace.jetpacklearn.BR;
import com.huace.jetpacklearn.R;
import com.huace.jetpacklearn.databinding.ActivityDataBindingBinding;
import com.huace.jetpacklearn.entity.User;
import com.huace.jetpacklearn.viewmodel.NameViewModel;

public class DataBindingActivity extends AppCompatActivity {

    User user;
    ActivityDataBindingBinding viewDataBinding;
    NameViewModel nameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
        user = new User();
        user.setName("JWDING");
        viewDataBinding.setUser(user);
        viewDataBinding.setCtrl(this);
        viewDataBinding.setNameViewModel(nameViewModel);
        viewDataBinding.setLifecycleOwner(this);
    }

    public void onClickTV(User user) {
        user.setName(Math.random()+"");
        viewDataBinding.setUser(user);
    }
}
