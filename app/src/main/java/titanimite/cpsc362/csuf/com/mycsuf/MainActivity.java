package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView addClassMsg;
    Button addClassBtn;
    DialogFragment dialog;
    JSONArray allClasses;
    ListView listView;
    ClassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allClasses = new JSONArray();
        listView = (ListView) findViewById(R.id.listView);

        getSampleData();

        adapter = new ClassAdapter(this, getApplicationContext(), allClasses);
        listView.setAdapter(adapter);

//        addClassMsg = (TextView)findViewById(R.id.addClassMsgTextView);
        addClassBtn = (Button)findViewById(R.id.addClassBtn);

        addClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();


//                DialogFragment addClassDialog = new DialogFragment();
//                // custom dialog
//                final Dialog dialog = new Dialog(getApplicationContext());
//                dialog.setContentView(R.layout.dialog_add_class);
//                dialog.setTitle("Add A Class");
//
//                // set the custom dialog components - text, image and button
////                TextView text = (TextView) dialog.findViewById(R.id.text);
////                text.setText("Android custom dialog example!");
////                ImageView image = (ImageView) dialog.findViewById(R.id.image);
////                image.setImageResource(R.drawable.ic_launcher);
//
//                Button dialogButton = (Button)dialog.findViewById(R.id.submitButton);
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });

//                dialog = new AddClassDialog();
//
//                dialog.show(getFragmentManager(), "addClassDF");

            }
        });

    }

    private void getSampleData() {
        for (int i = 0; i < 3; i++) {
            JSONObject jsonClass = new JSONObject();
            try {
                jsonClass.put("name", "class" + i);
                jsonClass.put("num", "classNum" + i);
                jsonClass.put("startTime", i);
                jsonClass.put("endTime", i + 5);
                jsonClass.put("prof", "prof" + i);
                jsonClass.put("room", "room" + i);
                jsonClass.put("description", "description" + i);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            allClasses.put(jsonClass);
        }

    }

    private void openDialog() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View dialogView = inflater.inflate(R.layout.dialog_add_class, null);

        final TextView etName = (EditText) dialogView.findViewById(R.id.classNameTextEdit);
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Add A Class")
                .setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        updateClasses(dialogView);
                    }
                })
                .setNegativeButton("Cancel", null).create();
        dialog.show();
    }

    private void updateClasses(View view) {
        JSONObject newClass = getData(view);
        Log.d("TAG", newClass.toString());
        allClasses.put(newClass);
        adapter.notifyDataSetChanged();
//        addClassMsg.setText(allClasses.toString());
    }

    private JSONObject getData(View dialog) {
        String classNameTxt = ((EditText)dialog.findViewById(R.id.classNameTextEdit)).getText().toString();
        String classNumTxt = ((EditText)dialog.findViewById(R.id.classNumTextEdit)).getText().toString();
        ArrayList<String> daysArr  = getCheckedBoxes(dialog);
        String startTimeTxt = ((EditText)dialog.findViewById(R.id.startTimeEditText))
                .getText().toString();
        String endTimeTxt = ((EditText)dialog.findViewById(R.id.endTimeEditText))
                .getText().toString();
        String profTxt = ((EditText)dialog.findViewById(R.id.profNameTextEdit))
                .getText().toString();
        String roomTxt = ((EditText)dialog.findViewById(R.id.roomTextEdit))
                .getText().toString();
        String descriptionTxt = ((EditText)dialog.findViewById(R.id.descriptionEditText))
                .getText().toString();

        JSONObject jsonClass = new JSONObject();
        try {
            jsonClass.put("name", classNameTxt);
            jsonClass.put("num", classNumTxt);
            jsonClass.put("startTime", startTimeTxt);
            jsonClass.put("endTime", endTimeTxt);
            jsonClass.put("prof", profTxt);
            jsonClass.put("room", roomTxt);
            jsonClass.put("description", descriptionTxt);
            jsonClass.put("days", new JSONArray(daysArr));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonClass;
    }

    private ArrayList<String> getCheckedBoxes(View dialog) {
        ArrayList<String> temp = new ArrayList<String>();
        CheckBox cb;
        LinearLayout checkBoxes = (LinearLayout)dialog.findViewById(R.id.daysCheckboxes);
        for (int i = 0; i < checkBoxes.getChildCount(); i++) {
            cb = (CheckBox)checkBoxes.getChildAt(i);
            if(cb.isChecked()) {
                temp.add(cb.getText().toString());
            }
        }

        return temp;
    }

    private boolean hasClasses() {
        SharedPreferences pref = getSharedPreferences("TitanimitePref", Context.MODE_PRIVATE);
        int numClasses = pref.getInt("num_classes", 0);
        return numClasses > 0;
    }
}
