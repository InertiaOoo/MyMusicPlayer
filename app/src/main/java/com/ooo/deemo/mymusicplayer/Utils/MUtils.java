package com.ooo.deemo.mymusicplayer.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

import com.ooo.deemo.mymusicplayer.Song;

import org.litepal.LitePal;

import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.List;

public class MUtils {
    //定义一个集合，存放从本地读取到的内容
    public static List<Song> list;

    public static Song song;



    public static List<Song> getmusic(Context context) {

        list = new ArrayList<>();


        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                , null, null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);

        if (cursor != null) {
            int cusId = 0;
            while (cursor.moveToNext()) {

                song = new Song();
                song.setID(cusId++);
               String musicfullname = (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

               if (musicfullname.contains(".")){
song.setSong(musicfullname.substring(0,musicfullname.indexOf(".")));

               }else {
                   song.setSong(musicfullname);

               }
song.setSinger(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                song.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
              song.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
               song.setSize(song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));


//                把歌曲名字和歌手切割开
                if (song.getSize() > 1000 * 800&&song.getDuration()>0) {
                    if (song.getSong().contains("-")) {
                        String[] str = song.getSong().split("-");
                        song.setSinger(str[0]);
                        song.setSong( str[1]);
                    }
                    list.add(song);
                }

                song.save();
            }
        }

        cursor.close();
        return list;

    }


    //    转换歌曲时间的格式
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            String tt = time / 1000 / 60 + ":0" + time / 1000 % 60;
            return tt;
        } else {
            String tt = time / 1000 / 60 + ":" + time / 1000 % 60;
            return tt;
        }
    }


}