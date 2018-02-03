package cp.chaos.org.communityplaylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OverviewActivity extends AppCompatActivity {

    private static final int LOGIN_REQUEST_CODE = 1;

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        setupList();
        //        startActivity(new Intent(this, LoginActivity.class));
        startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST_CODE);
    }

    private void setupList() {
        arrayAdapter = new ArrayAdapter<String>(OverviewActivity.this,
                android.R.layout.simple_expandable_list_item_1);
        ListView myPlaylistView = findViewById(R.id.myPlaylistView);
        myPlaylistView.setAdapter(arrayAdapter);
    }

    public void openSearchClick(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                arrayAdapter.add("Logged in as: " + data.getStringExtra("result"));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
