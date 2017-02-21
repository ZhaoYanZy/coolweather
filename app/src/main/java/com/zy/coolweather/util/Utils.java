package com.zy.coolweather.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.bumptech.glide.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵岩 on 2017/1/4.
 * 工具类
 */
public class Utils {

    //存放活动集合
    private static List<Activity> activityList = new ArrayList<>();
    //Toast
    private static Toast toast;
    //Dialog
    private static AlertDialog.Builder builder;

    //向List中添加活动
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    //从集合中移除活动
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    //获取栈顶Activity
    public static Activity getTopActivity(){
        if(activityList.isEmpty()){
            return null;
        }else {
            return activityList.get(activityList.size() - 1);
        }
    }

    //销毁集合中的所有活动并杀掉当前进程
    public static void finishAll() {
        //循环销毁集合中的所有活动
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        //杀掉当前进程
        Process.killProcess(Process.myPid());
    }


    //Toast提示
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * Dialog提示
     * @param context 上下文
     * @param title   标题
     * @param msg     信息
     */
    public static void showDialog(Context context, String title, String msg) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //运行时权限请求操作回调接口
    public interface PermissionListener {
        //授权成功
        void onGranted();
        //授权失败
        void onDenied(List<String> deniedPermission);
    }

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * 
	 * @param context
	 *            上下文
	 * @param dipValue
	 *            尺寸dip
	 * @return 像素值
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (scale * dipValue + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * 
	 * @param context
	 *            上下文
	 * @param pxValue
	 *            尺寸像素
	 * @return DIP值
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
	 * 
	 * @param context
	 *            上下文
	 * @param pxValue
	 *            尺寸像素
	 * @return SP值
	 */
	public static int px2sp(Context context, float pxValue) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 sp 的单位 转成为 px
	 * 
	 * @param context
	 *            上下文
	 * @param spValue
	 *            SP值
	 * @return 像素值
	 */
	public static int sp2px(Context context, float spValue) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}


}
