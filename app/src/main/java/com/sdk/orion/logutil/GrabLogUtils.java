package com.sdk.orion.logutil;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by songyongmeng on 2017/6/2.
 */

public class GrabLogUtils {

    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static void write(String tag, String content) {
        if (isSdCardExist()) {
            write("tag：" + tag + "\r\ncontent：" + content + "\r\n记录时间：" + getCurrentTime() + "\r\n" + "  " + "\r\n");
        }
    }

    public static void write(String data) {
        if (isSdCardExist())
            try {
                File file = new File(createSDCardDir(),
                        getCurrentData() + ".log");
                //第二个参数意义是说是否以append方式添加内容
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                String info = data + "\r\n";
                bw.write(info);
                bw.flush();
                System.out.println("写入成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    public static String getCurrentData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    public static File createSDCardDir() {
        File sdcardDir = Environment.getExternalStorageDirectory();
        String path = sdcardDir.getPath() + "/SYM-Log";
        File path1 = new File(path);
        if (!path1.exists())
            path1.mkdirs();
        return path1;
    }
}
