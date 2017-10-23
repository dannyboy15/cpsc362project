package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.spec.DHGenParameterSpec;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstName, lastName, major, email, pass, verify;
    private CheckBox tosAgree;
    private Button submit;
    private SessionManager session;
    private DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        session = SessionManager.getInstance(getApplicationContext());
//        session = (SessionManager) getIntent().getExtras().getParcelable("session");
//        session.setNewActivityContext(getApplicationContext());
        database = DBHelper.getInstance(getApplicationContext());

        firstName = (EditText) findViewById(R.id.firstNameTextEdit);
        lastName = (EditText) findViewById(R.id.lastNameTextEdit);
        major = (EditText) findViewById(R.id.majorTextEdit);
        email = (EditText) findViewById(R.id.emailTextEdit);
        pass = (EditText) findViewById(R.id.passTextEdit);
        verify = (EditText) findViewById(R.id.verifyTextEdit);
        tosAgree = (CheckBox) findViewById(R.id.tosAgree);
        submit = (Button) findViewById(R.id.submitButton);

        tosAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    submit.setEnabled(true);
                else
                    submit.setEnabled(false);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFieldsFilledOut()) {
//                    updateSharedPreferenes();
                    createUser();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("session", session);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void createUser() {
        // getFieldValues();
        String firstNameTxt = firstName.getText().toString();
        String lastNameTxt = lastName.getText().toString();
        String majorTxt = major.getText().toString();
        String emailTxt = email.getText().toString();
        String passTxt = pass.getText().toString();
        boolean tosAgreeVal = tosAgree.isChecked();

        StudentUser user = new StudentUser(firstNameTxt, lastNameTxt, majorTxt, emailTxt, passTxt);
        long id = database.insertStudentUser(user);

        user.setId((int)id);
        Log.i("SU Activity", user.toString());
        session.createLoginSession(user);

    }

    private void updateSharedPreferenes() {
        // getFieldValues();
        String firstNameTxt = firstName.getText().toString();
        String lastNameTxt = lastName.getText().toString();
        String majorTxt = major.getText().toString();
        String emailTxt = email.getText().toString();
        String passTxt = pass.getText().toString();
        boolean tosAgreeVal = tosAgree.isChecked();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("TitanimitePref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String emails = pref.getString("saved_emails", "");
        JSONArray emailsArr = new JSONArray();
        try {
            emailsArr = new JSONArray(emails);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        editor.putString("firstName", firstNameTxt);
        editor.putString("lastName", lastNameTxt);
        editor.putString("major", majorTxt);
        editor.putString("email", emailTxt);
        editor.putBoolean("tosAgree", tosAgreeVal);
        editor.putBoolean("is_logged_in", true);

        JSONObject emailPass = new JSONObject();
        try {
            emailPass.put("email", emailTxt.toString());
            emailPass.put("pass", passTxt.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        emailsArr.put(emailPass);
        editor.putString("saved_emails", emailsArr.toString());

        editor.commit();
    }

    private boolean isFieldsFilledOut() {
        boolean isFilledOut = true;

        if(!isPassSame()) {
            Log.i("PASSWORDS: ", "Don't Match!");
            verify.setError("Passwords don't match");
            isFilledOut = false;
        }
        if(firstName.getText().toString().trim().equals("")) {
            firstName.setError("Your first name is required!");
            isFilledOut = false;
        }
        if(lastName.getText().toString().trim().equals("")) {
            lastName.setError("Your last name is required!");
            isFilledOut = false;
        }
        if(major.getText().toString().trim().equals("")) {
            major.setError("Your major is required!");
            isFilledOut = false;
        }
        if(email.getText().toString().trim().equals("")) {
            email.setError("Your email is required!");
            isFilledOut = false;
        }
        else if (isEmailTaken(email.getText().toString().trim())){
            email.setError("This email is already taken");
            isFilledOut = false;
        }

        return isFilledOut;
    }

    private boolean isEmailTaken(String email) {
        long id = database.checkUserEmail(email);

        return id != -1;
    }

    private boolean isPassSame() {
        return pass.getText().toString().trim().equals(verify.getText().toString().trim());
    }


}
