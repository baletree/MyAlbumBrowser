package com.xbx.album.utils;

import android.content.Context;

import com.xbx.album.R;
import com.xbx.album.bean.PhotoGroupInfo;
import com.xbx.album.bean.PhotoInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by peng on 2017/8/23.
 */

public class PhotoDealUtil {
    private static PhotoDealUtil utilTools;

    public static PhotoDealUtil getInstance() {
        if (utilTools == null)
            utilTools = new PhotoDealUtil();
        return utilTools;
    }

    /**
     * @param photos  将照片按日期分组
     * @param context
     * @return
     */
    public List<PhotoGroupInfo> groupByTime(List<PhotoInfo> photos, Context context) {
        List<PhotoGroupInfo> list = new ArrayList<>();
        PhotoGroupInfo info = null;
        List<PhotoInfo> childPhotos = null;
        for (int i = 0; i < photos.size(); i++) {
            boolean isExit = false;
            String time = getDateString(photos.get(i).getTime(), context)[0];
            for (int j = 0; j < list.size(); j++) {
                if (time.equals(list.get(j).getTime())) {
                    isExit = true;
                    list.get(j).getPhotos().add(photos.get(i));
                    break;
                }
            }
            if (!isExit) {
                info = new PhotoGroupInfo();
                childPhotos = new ArrayList<>();
                childPhotos.add(photos.get(i));
                info.setTime(time);
                info.setPhotos(childPhotos);
                list.add(info);
            }
        }
        return list;
    }

    /**
     * @param date    格式化时间显示
     * @param context
     * @return
     */
    public String[] getDateString(Date date, Context context) {
        String[] dayOfWeek = context.getResources().getStringArray(R.array.day_of_week);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");
        SimpleDateFormat format1 = new SimpleDateFormat("MM月dd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
        String[] time = new String[2];
        time[1] = format2.format(date);
        Calendar c = Calendar.getInstance();
        int year1 = c.get(Calendar.YEAR);
        int day1 = c.get(Calendar.DAY_OF_YEAR);
        c.setTime(date);
        int year2 = c.get(Calendar.YEAR);
        int day2 = c.get(Calendar.DAY_OF_YEAR);
        if (day1 - day2 < 7) {
            time[0] = dayOfWeek[c.get(Calendar.DAY_OF_WEEK) - 1];
        } else if (year1 == year2) {
            time[0] = format1.format(date);
        } else {
            time[0] = format.format(date);
        }
        return time;
    }
}
