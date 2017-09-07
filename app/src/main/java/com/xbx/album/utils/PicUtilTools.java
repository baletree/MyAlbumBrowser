package com.xbx.album.utils;

import android.content.Context;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.xbx.album.bean.PhotoInfo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Eric on 2017/7/18.
 */

public class PicUtilTools {
    private static PicUtilTools utilTools;

    public static PicUtilTools getInstance() {
        if (utilTools == null)
            utilTools = new PicUtilTools();
        return utilTools;
    }

    /**
     * @param mContext 横向图片展示每张图片的大小
     * @param rlyt
     * @param imageNum
     */
    public void getImageHorizontalSize(Context mContext, View rlyt, int imageNum, int position) {
        ViewGroup.LayoutParams params = rlyt.getLayoutParams();
        int width = getScreenWidth(mContext);
        int safeDis = dip2px(mContext, 0);
        int ivWidth = ((width - safeDis) / imageNum);
        if (position % imageNum == imageNum - 1)
            rlyt.setPadding(0, dip2px(mContext, 3f), 0, 0);
        else
            rlyt.setPadding(0, dip2px(mContext, 3f), dip2px(mContext, 3f), 0);
        params.width = ivWidth;
        params.height = ivWidth;
        rlyt.setLayoutParams(params);
    }

    /**
     * @param context 获取手机屏幕宽度
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * @param dipValue scale  （DisplayMetrics类中属性density） 将dip或dp值转换为px值，保证尺寸大小不变
     * @return
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * @return 从sd卡获取图片资源
     */
    public List<String> getImagePathFromSD() {
        // 图片列表
        List<String> imagePathList = new ArrayList<>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().toString() + File.separator
                + "DCIM" + File.separator + "Camera";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsImageFile(file.getPath())) {
                imagePathList.add(file.getPath());
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }

    /**
     * @param fName 文件名 检查扩展名，得到图片格式的文件
     * @return
     */
    private boolean checkIsImageFile(String fName) {
        boolean isImageFile;
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg") || FileEnd.equals("bmp") || FileEnd.equals("mp4")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    public static void XLog(String text) {
        Log.i("xbxAlbum", text);
    }

    public String getTimeStampToStr(long timeStamp) {
        String strTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeStamp);
        strTime = sdf.format(date);
        return strTime;
    }

    /**
     * @param picPathList 获取本地的图片信息
     * @return
     */
    public List<PhotoInfo> getLocalPhotoInfo(List<String> picPathList) {
        SimpleDateFormat old = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        Date date = null;
        PhotoInfo info = null;
        List<PhotoInfo> localPhotos = new ArrayList<>();
        try {
            for (String pathStr : picPathList) {
                File file = new File(pathStr);
                info = new PhotoInfo();
                info.setName(file.getName());
                info.setPhotoPath(file.getPath());
                ExifInterface fileExif = new ExifInterface(file.getPath());
                String time = fileExif.getAttribute(ExifInterface.TAG_DATETIME);
                if (!TextUtils.isEmpty(time)) {
                    time = time.replace("T", " ");
                    time = time.replace("-", ":");
                    date = old.parse(time);
                } else {
                    date = new Date();
                }
                info.setTime(date);
                localPhotos.add(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (localPhotos != null)
            sortPhotoInfo(localPhotos);
        return localPhotos;
    }

    /**
     * @param photos 按照时间排序
     */
    public void sortPhotoInfo(List<PhotoInfo> photos) {
        Collections.sort(photos, new Comparator<PhotoInfo>() {
            @Override
            public int compare(PhotoInfo lhs, PhotoInfo rhs) {
                return rhs.getTime().compareTo(lhs.getTime());
            }
        });
    }
}
