package com.henote.lastfm.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henote.lastfm.R;
import com.henote.lastfm.model.ArtistDTO;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ArtistListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.linearLayoutRoot)
    LinearLayout linearLayoutRoot;
    @BindView(R.id.textViewArtistNAme)
    TextView textViewArtistNAme;
    @BindView(R.id.circleImageViewProfile)
    CircleImageView circleImageViewProfile;

    private ArtistListOnClickListener clickListener;

    public ArtistListViewHolder(View itemView, ArtistListOnClickListener artistListClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.clickListener = artistListClickListener;
    }

    public void bind(final ArtistDTO dto) {
        if (dto.getArtistName() != null) {
            textViewArtistNAme.setText(dto.getArtistName());
        }
        linearLayoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onSelect(dto,getAdapterPosition());
            }
        });
        /*if (dto.getArtistUrl() != null) {
            Glide.with(circleImageViewProfile.getContext()).
                    load(dto.getArtistUrl())
                    .error(R.drawable.ic_account_circle)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(circleImageViewProfile);
        }*/
    }
}
