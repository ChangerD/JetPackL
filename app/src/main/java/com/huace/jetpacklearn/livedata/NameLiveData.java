package com.huace.jetpacklearn.livedata;

import androidx.lifecycle.LiveData;

/**
 * @author dingjiawei
 * Created on 2019/1/11.
 */
public class NameLiveData extends LiveData<String> {

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

    public void setName(String name) {
        setValue(name);
    }
}
