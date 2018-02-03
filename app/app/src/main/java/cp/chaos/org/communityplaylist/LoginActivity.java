package cp.chaos.org.communityplaylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginClick(View view) {
        EditText userName = findViewById(R.id.userName);
        if (userName.getText().toString().trim().isEmpty()) {
            userName.setError("Bitte gib deinen Namen an");
            return;
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", userName.getText().toString());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
