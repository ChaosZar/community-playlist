package cp.chaos.org.communityplaylist;

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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cp.chaos.org.communityplaylist.json.Song;
import cp.chaos.org.communityplaylist.json.SongList;

public class OverviewActivity extends AppCompatActivity {

    private static final int LOGIN_REQUEST_CODE = 1;
    private static final int SEARCH_REQUEST_CODE = 2;

    private String baseUrl = "http://10.129.18.106:8080";
    private RequestQueue requestQueue;

    private ArrayAdapter<Song> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        setupList();
        requestQueue = Volley.newRequestQueue(this);
        //        startActivity(new Intent(this, LoginActivity.class));
        startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST_CODE);
    }

    private void setupList() {
        arrayAdapter = new ArrayAdapter<Song>(OverviewActivity.this,
                android.R.layout.simple_expandable_list_item_1);
        ListView myPlaylistView = findViewById(R.id.myPlaylistView);
        myPlaylistView.setAdapter(arrayAdapter);
    }

    public void openSearchClick(View view) {
        startActivityForResult(new Intent(this, SearchActivity.class), SEARCH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == LOGIN_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
//                arrayAdapter.add("Logged in as: " + data.getStringExtra("result"));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        } else if(requestCode == SEARCH_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this.getApplicationContext(), "Successfully added song(s)!", Toast.LENGTH_LONG).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this.getApplicationContext(), "Sorry you didn't find any songs!", Toast.LENGTH_LONG).show();
            }
        }
        refreshMyPlaylist();
    }

    private void refreshMyPlaylist() {
        String url = baseUrl + "/playlist/1";

        // Next, we create a new JsonArrayRequest. This will use Volley to make a HTTP request
        // that expects a JSON Array Response.
        // To fully understand this, I'd recommend readng the office docs: https://developer.android.com/training/volley/index.html
        JsonObjectRequest myPlaylistRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        parsePlaylistGet(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our repo list.
                        //                        setRepoListText("Error while calling REST API");

//                        Log.e("Volley", error.toString());
//                        Log.e("Volley", error.getCause().toString());
                        error.printStackTrace();
                    }
                });
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(myPlaylistRequest);
    }

    private void parsePlaylistGet(JSONObject response) {
        Gson gson = new Gson();
        System.out.println(response.toString());
        SongList songList = gson.fromJson(response.toString(), SongList.class);

        for (Song song : songList.getSongs()) {
            arrayAdapter.add(song);
        }
    }
}
