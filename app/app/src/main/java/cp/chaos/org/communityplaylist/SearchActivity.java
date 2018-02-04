package cp.chaos.org.communityplaylist;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import cp.chaos.org.communityplaylist.json.PlaylistPut;
import cp.chaos.org.communityplaylist.json.SongList;
import cp.chaos.org.communityplaylist.json.Song;

public class SearchActivity extends AppCompatActivity {

    private boolean addedSong = false;
    private RequestQueue requestQueue;

    private String baseUrl = "http://10.129.18.106:8080";

    private ArrayAdapter<Song> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupList();

        requestQueue = Volley.newRequestQueue(this);
    }


    private void setupList() {
        arrayAdapter = new ArrayAdapter<Song>(SearchActivity.this,
                android.R.layout.simple_expandable_list_item_1);
        ListView myPlaylistView = findViewById(R.id.searchResultListView);
        myPlaylistView.setAdapter(arrayAdapter);
        myPlaylistView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Song song = (Song) adapterView.getItemAtPosition(i);
                sendPutPlaylistRequest(song);
                addedSong = true;

            }
        });
    }

    public void backToOverviewClick(View view) {
        if (addedSong) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
        } else {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
        }
        finish();
    }

    public void searchClick(View view) {
        arrayAdapter.clear();
        sendSearchRequest();
    }
    private void sendPutPlaylistRequest(Song song) {
        String url = baseUrl + "/playlist";

        PlaylistPut playlistPut = new PlaylistPut();
        List<Song> songs = new ArrayList<>();
        songs.add(song);
        playlistPut.setSongs(songs);
        playlistPut.setUserId(getUserId());

        Gson gson = new Gson();
        String playlistPutJson = gson.toJson(playlistPut);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(playlistPutJson);
        } catch(JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Sending playlistPut json: " + jsonObject.toString());

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        requestQueue.add(putRequest);
    }

    private void sendSearchRequest() {
        EditText searchText = findViewById(R.id.searchEditText);
        String searchTerm = searchText.getText().toString();

        String url = baseUrl + "/song/" + searchTerm;

        // Next, we create a new JsonArrayRequest. This will use Volley to make a HTTP request
        // that expects a JSON Array Response.
        // To fully understand this, I'd recommend readng the office docs: https://developer.android.com/training/volley/index.html
        JsonObjectRequest searchRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseSearchResultJson(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our repo list.
//                        setRepoListText("Error while calling REST API");

                        Log.e("Volley", error.toString());
                        Log.e("Volley", error.getCause().toString());
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(searchRequest);
    }

    private void parseSearchResultJson(JSONObject response) {
        Gson gson = new Gson();
        System.out.println(response.toString());
        SongList songList = gson.fromJson(response.toString(), SongList.class);

        for (Song song : songList.getSongs()) {
            arrayAdapter.add(song);
        }

//        // Check the length of our response (to see if the user has any repos)
//        if (response.length() > 0) {
//            // The user does have repos, so let's loop through them all.
//            for (int i = 0; i < response.length(); i++) {
//                try {
//                    // For each repo, add a new line to our repo list.
//                    JSONObject jsonObj = response.getJSONObject(i);
//
//                    Gson gson = new Gson();
//                    Song song = gson.fromJson(jsonObj.toString(), Song.class);
//
//                    arrayAdapter.add(song);
////                                    addToRepoList(repoName, lastUpdated);
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e("Volley", "Invalid JSON Object.");
//                }
//
//            }
//        } else {
//            // The user didn't have any repos.
////                            setRepoListText("No repos found.");
//        }
    }

    public long getUserId() {
        return 1;
    }

}
