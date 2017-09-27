package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean isLoggedIn() {
        SharedPreferences pref = getSharedPreferences("MyClockinPref", Context.MODE_PRIVATE);
        return pref.getBoolean("is_logged_in", false);
    }
}
