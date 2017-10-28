package com.henote.lastfm.parser;


import android.util.Log;

import com.henote.lastfm.MainActivity;
import com.henote.lastfm.model.ArtistDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArtistListParser {
    private static final String TAG = ArtistListParser.class.getSimpleName();

    public static List<ArtistDTO> parseArtist(JSONObject jsonObject) {
        ArtistDTO artistDTO = null;
        List<ArtistDTO> userList = new ArrayList<ArtistDTO>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("artist");
            //Log.d(TAG, "" + jsonArray.toString());
            int sizeArray = jsonArray.length();
            for (int i = 0; i < sizeArray; i++) {
                JSONObject userObject = jsonArray.getJSONObject(i);
                artistDTO = new ArtistDTO();

                if (userObject.has("name")) {
                    artistDTO.setArtistName(userObject.getString("name"));
                }
                if (userObject.has("listeners")) {
                    artistDTO.setListeners(userObject.getInt("listeners"));
                }
                if (userObject.has("mbid")) {
                    artistDTO.setMbId(userObject.getString("mbid"));
                }
                if (userObject.has("url")) {
                    artistDTO.setArtistUrl(userObject.getString("url"));
                }
                if (userObject.has("streamable")) {
                    artistDTO.setStreamAble(userObject.getInt("streamable"));
                }


                userList.add(artistDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Log.d("List Size", String.valueOf(userList.size()));
        return userList;
    }
}
