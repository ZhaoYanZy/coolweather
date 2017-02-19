package com.zy.coolweather;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zy.coolweather.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵岩 on 2017/1/5.
 */

public class BaseActivity  extends AppCompatActivity{

    //请求操作回调
    public static Utils.PermissionListener mListener;
    //权限请求码
    public static final int REQUEST_CODE = 100;

    @Override//创建Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //打印出当前活动的类名
        Log.d("BaseActivity",getClass().getSimpleName());
        //把当前活动添加到活动管理器中
        Utils.addActivity(this);
    }

    //运行时权限申请
    public static void requestRuntimePermission(String[] permissions,
                                                Utils.PermissionListener permissionListener){
        //获取栈顶Activity
        Activity topActivity = Utils.getTopActivity();
        //如果为null，则返回(即没有Activity则不需要申请权限)
        if(topActivity == null){
            return;
        }
        mListener = permissionListener;
        //需要请求的权限列表集合
        List<String> permissionList = new ArrayList<>();
        //检查权限是否已被授权
        for (String permission : permissions) {
            if(ContextCompat.checkSelfPermission(topActivity,permission)
                    != PackageManager.PERMISSION_GRANTED){
                //未授权时把该权限添加到请求集合
                permissionList.add(permission);
            }
            if(permissionList.isEmpty()){
                //所有权限都已被授权，回调onGranted方法
                permissionListener.onGranted();
            }else{
                //进行请求权限操作
                ActivityCompat.requestPermissions(topActivity,
                        permissionList.toArray(new String[permissionList.size()]),
                        REQUEST_CODE);
            }
            
        }
    }

    /**
     *请求权限回调
     * @param requestCode   请求码
     * @param permissions   请求的权限
     * @param grantResults   请求结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                //授权结果不为空
                if (grantResults.length > 0) {
 		//权限请求失败集合
                List<String> deniedPermissionList = new ArrayList<>();
                    //判断授权结果
                    for (int i = 0; i < grantResults.length; i++) {
                        int result = grantResults[i];
                        if(result != PackageManager.PERMISSION_GRANTED){
                            //保存用户拒绝权限
                            deniedPermissionList.add(permissions[i]);
                        }
                    }
                    if(deniedPermissionList.isEmpty()){
                        //都已授权,回调onGranted()方法
                        mListener.onGranted();
                    }else{
                        //有权限被拒绝，回调onDenied()方法
                        mListener.onDenied(deniedPermissionList);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override//销毁Activity
    protected void onDestroy() {
        super.onDestroy();
        //将一个销毁的活动从活动管理器里移除
        Utils.removeActivity(this);
    }
}
