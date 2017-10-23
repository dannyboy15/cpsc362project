package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Session Manager
        SessionManager session = SessionManager.getInstance(getApplicationContext());
        Log.i("Splash Activity", session.toString());

        if (session.isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra("session", session);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
//            intent.putExtra("session", session);
            startActivity(intent);
            finish();
        }
    }

//    private boolean isLoggedIn() {
//        SharedPreferences pref = getSharedPreferences("TitanimitePref", Context.MODE_PRIVATE);
//        return pref.getBoolean("is_logged_in", false);
//    }
}
