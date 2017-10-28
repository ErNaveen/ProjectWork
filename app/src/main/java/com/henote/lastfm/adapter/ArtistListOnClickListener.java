package com.henote.lastfm.adapter;


import com.henote.lastfm.model.ArtistDTO;

public interface ArtistListOnClickListener {
    void onSelect(ArtistDTO artistDTO, int position);
}
