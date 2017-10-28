package com.henote.lastfm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.henote.lastfm.adapter.ArtistListAdapter;
import com.henote.lastfm.adapter.ArtistListOnClickListener;
import com.henote.lastfm.model.ArtistDTO;
import com.henote.lastfm.parser.ArtistListParser;
import com.henote.lastfm.util.CustomDialog;
import com.henote.lastfm.util.KeyboardUtil;
import com.henote.lastfm.util.PG;
import com.henote.lastfm.util.UrlConstants;
import com.henote.lastfm.util.Validation;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ArtistListOnClickListener {

    @BindView(R.id.editTextArtistName)
    EditText editTextArtistName;
    @BindView(R.id.recyclerViewList)
    RecyclerView recyclerViewList;
    @BindView(R.id.noRecord)
    TextView noRecord;


    private static final String TAG = MainActivity.class.getSimpleName();
    private Context context;
    private List<ArtistDTO> list = new ArrayList<>();
    private ArtistListAdapter artistListAdapter = null;
    private LinearLayoutManager mLayoutManager;
    private int index = 1;
    private boolean isLoading = true;
    private String strArtistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        bindRecyclerView();

    }

    private void bindRecyclerView() {
        artistListAdapter = new ArtistListAdapter(list);
        artistListAdapter.setClickListener(this);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerViewList.setLayoutManager(mLayoutManager);
        recyclerViewList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewList.setAdapter(artistListAdapter);

        recyclerViewList.addOnScrollListener(new EndlessOnScrollListener(mLayoutManager) {
            @Override
            public void onScrolledToEnd() {
                Log.e("Position", "Last item reached");
                if (isLoading) {
                    //Log.d(TAG, "item count---" + mLayoutManager.getItemCount());
                    // put your Load more code
                    index++;
                    Log.d(TAG, "index:  " + index);
                    callGetArtistService(strArtistName, index);
                }
            }
        });
    }

    @Override
    public void onSelect(ArtistDTO artistDTO, int position) {
        new CustomDialog(context, "Artist Name:   " + artistDTO.getArtistName());
    }

    @OnClick(R.id.imageViewClick)
    public void searchArtist() {
        KeyboardUtil.hideSoftKeyboard(context);
        strArtistName = editTextArtistName.getText().toString().trim();
        if (!Validation.isEmpty(strArtistName)) {
            new CustomDialog(context, "Please enter artist name.");
        } else {
            index = 1;
            callGetArtistService(strArtistName, index);
        }
    }

    private void callGetArtistService(final String strArtistName, final int page) {
        final ProgressDialog pg = PG.showProgressDialog(context);
        String tag_json_obj = "json_obj_req";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlConstants.BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d(TAG, "Response Object: " + response.toString());
                try {
                    JSONObject jsObject = new JSONObject(response);
                    JSONObject jsonObject = jsObject.getJSONObject("results").getJSONObject("artistmatches");
                    //Log.d(TAG, "artistArray Object: " + jsonObject.toString());
                    list.addAll(ArtistListParser.parseArtist(jsonObject));
                    //Log.d(TAG, "list size:  " + String.valueOf(list.size()));
                    if (list.size() > 0) {
                        //editTextArtistName.setText(null);
                        artistListAdapter.setDataChange(list);
                    } else {
                        noRecord.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Hide Progress Dialog
                PG.hideProgressDialog(pg);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // Hide Progress Dialog
                PG.hideProgressDialog(pg);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("artist", strArtistName);
                params.put("api_key", "261e6f48fec2d22099fc6a9de32e4de1");
                params.put("format", "json");
                params.put("limit", String.valueOf(10));
                params.put("page", String.valueOf(page));
                Log.d(TAG, "Request object: " + params.toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getApp().addToRequestQueue(stringRequest, tag_json_obj);
    }
}
