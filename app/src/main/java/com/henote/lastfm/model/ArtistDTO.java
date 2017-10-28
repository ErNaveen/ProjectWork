package com.henote.lastfm.model;


public class ArtistDTO  {
    private String artistName;
    private int listeners;
    private String mbId;
    private String artistUrl;
    private int streamAble;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public int getStreamAble() {
        return streamAble;
    }

    public void setStreamAble(int streamAble) {
        this.streamAble = streamAble;
    }

    @Override
    public String toString() {
        return "ArtistListParser{" +
                "artistName='" + artistName + '\'' +
                ", listeners=" + listeners +
                ", mbId='" + mbId + '\'' +
                ", artistUrl='" + artistUrl + '\'' +
                ", streamAble='" + streamAble + '\'' +
                '}';
    }
}
