package cp.chaos.org.communityplaylist;

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
        finish();
    }
}
