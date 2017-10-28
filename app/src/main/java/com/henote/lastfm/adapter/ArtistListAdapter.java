package com.henote.lastfm.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henote.lastfm.R;
import com.henote.lastfm.model.ArtistDTO;

import java.util.List;

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListViewHolder> {

    private List<ArtistDTO> list = null;
    private ArtistListOnClickListener clickListener;

    public ArtistListAdapter(List<ArtistDTO> mList) {
        this.list = mList;
    }

    public void setDataChange(List<ArtistDTO> artistDTOList) {
        this.list = artistDTOList;
        //now, tell the adapter about the update
        notifyDataSetChanged();
    }

    public void setClickListener(ArtistListOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ArtistListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_artist_list_row, parent, false);
        return new ArtistListViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(ArtistListViewHolder holder, int position) {
        ArtistDTO artistDTO = list.get(position);
        holder.bind(artistDTO);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
