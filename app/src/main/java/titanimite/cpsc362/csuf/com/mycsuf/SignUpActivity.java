package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstName, lastName, major, email;
    private CheckBox tosAgree;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = (EditText) findViewById(R.id.firstNameTextEdit);
        lastName = (EditText) findViewById(R.id.lastNameTextEdit);
        major = (EditText) findViewById(R.id.majorTextEdit);
        email = (EditText) findViewById(R.id.emailTextEdit);
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
                    updateSharedPreferenes();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void updateSharedPreferenes() {
        // getFieldValues();
        String firstNameTxt = firstName.getText().toString();
        String lastNameTxt = lastName.getText().toString();
        String majorTxt = major.getText().toString();
        String emailTxt = email.getText().toString();
        boolean tosAgreeVal = tosAgree.isChecked();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("TitanimitePref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("firstName", firstNameTxt);
        editor.putString("lastName", lastNameTxt);
        editor.putString("major", majorTxt);
        editor.putString("email", emailTxt);
        editor.putBoolean("tosAgree", tosAgreeVal);
        editor.putBoolean("is_logged_in", true);

        editor.commit();
    }

    private boolean isFieldsFilledOut() {
        boolean isFilledOut = true;
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

        return isFilledOut;
    }


}
