package com.huace.jetpacklearn.component;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.huace.jetpacklearn.TestWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

/**
 * WorkManager组件学习
 *
 * @author dingjiawei
 * Created on 2018/12/29.
 */
public class WorkManagerActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //传入数据
        Data data = new Data.Builder().putString("params", "It's me!").build();
        //单次任务
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(TestWorker.class).setInputData(data).build();
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo != null) {
                    Log.d("WorkManagerActivity", workInfo.getState() + "");
                }
            }
        });
        //添加约束
        Constraints constraints = new Constraints.Builder()
                .setRequiresDeviceIdle(true)
                //指定{@link WorkRequest}运行时设备是否为空闲
                .setRequiresCharging(true)
                //指定要运行的{@link WorkRequest}是否应该插入设备
                .setRequiredNetworkType(NetworkType.UNMETERED)
                /**
                 * 指定网络状态执行任务
                 * NetworkType.NOT_REQUIRED：对网络没有要求
                 * NetworkType.CONNECTED：网络连接的时候执行
                 * NetworkType.UNMETERED：不计费的网络比如WIFI下执行
                 * NetworkType.NOT_ROAMING：非漫游网络状态
                 * NetworkType.METERED：计费网络比如3G，4G下执行。
                 */
                .setRequiresBatteryNotLow(true)
                //指定设备电池是否不应低于临界阈值
                .setRequiresCharging(true)
                //网络状态
                .setRequiresDeviceIdle(true)
                //指定{@link WorkRequest}运行时设备是否为空闲
                .setRequiresStorageNotLow(true)
                //指定设备可用存储是否不应低于临界阈值
                //.addContentUriTrigger(myUri,false)
                //指定内容{@link android.net.Uri}时是否应该运行{@link WorkRequest}更新
                .build();
        //链式结构
        WorkManager.getInstance().beginWith(oneTimeWorkRequest).then(oneTimeWorkRequest).enqueue();
        //任务链
        WorkContinuation workContinuation =
                WorkManager.getInstance().beginWith(oneTimeWorkRequest).then(oneTimeWorkRequest);
        //任务唯一性
        WorkContinuation workContinuation1 =
                WorkManager.getInstance().beginUniqueWork("unique", ExistingWorkPolicy.KEEP, oneTimeWorkRequest);
        //合并任务链
        List<WorkContinuation> workContinuations = new ArrayList<>();
        workContinuations.add(workContinuation);
        workContinuations.add(workContinuation1);
        WorkContinuation.combine(workContinuations).then(oneTimeWorkRequest).enqueue();

        //循环任务
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(TestWorker.class, 3, TimeUnit.SECONDS).setConstraints(constraints).build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);

    }

}
