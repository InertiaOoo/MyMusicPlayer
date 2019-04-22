package com.ooo.deemo.mymusicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ooo.deemo.mymusicplayer.Utils.MusicUtils;

import java.util.List;

/**
 * Author by Deemo, Date on 2019/4/22.
 * Have a good day
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context context;
    private List<Song> list;
    private int position_flag = 0;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            Song song = list.get(position);

            holder.songView.setText(song.getSong());

            holder.singerView.setText(song.getSinger());


        int duration = list.get(position).getDuration();
        String time = MusicUtils.formatTime(duration);
        holder.durationView.setText(time);

            holder.positionView.setText(position+1+"");

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView songView;
TextView singerView;
TextView durationView;
TextView positionView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

songView = itemView.findViewById(R.id.item_mymusic_song);
            singerView = itemView.findViewById(R.id.item_mymusic_singer);
            durationView = itemView.findViewById(R.id.item_mymusic_duration);

            positionView = itemView.findViewById(R.id.item_mymusic_postion);


        }
    }


    public MusicListAdapter(List<Song> list) {
        this.list = list;
    }
}
