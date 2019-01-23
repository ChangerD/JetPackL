package com.huace.jetpacklearn;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @author dingjiawei
 * Created on 2018/12/29.
 */
public class TestWorker extends Worker {

    Context context;

    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        String params = getInputData().getString("params");
        Looper.prepare();
        Toast.makeText(context,params,Toast.LENGTH_SHORT).show();
        Looper.loop();
        return Result.success();
    }
}
