package com.xbx.album.bean;

import java.util.List;

/**
 * Created by peng on 2017/8/23.
 */

public class PhotoGroupInfo {

    private String time;
    private List<PhotoInfo> photos;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<PhotoInfo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoInfo> photos) {
        this.photos = photos;
    }
}