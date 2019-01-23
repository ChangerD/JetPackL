package com.huace.jetpacklearn.viewmodel;

import com.huace.jetpacklearn.livedata.NameLiveData;

import androidx.lifecycle.ViewModel;

/**
 * @author dingjiawei
 * Created on 2019/1/11.
 */
public class NameViewModel extends ViewModel {

    private NameLiveData mName = new NameLiveData();

    public NameLiveData getName() {
        initName();
        return mName;
    }

    private void initName() {
        mName.setName("JWDING");
    }

    public void setName(String name){
        mName.setName(name);
    }
}
