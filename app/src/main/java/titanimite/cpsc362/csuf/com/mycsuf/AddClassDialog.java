package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Daniel on 9/27/17.
 */

public class AddClassDialog extends DialogFragment implements View.OnClickListener {

    String classNameTxt;
    String classNumTxt;
    ArrayList<String> daysArr;
    String startTimeTxt;
    String endTimeTxt;
    String profTxt;
    String roomTxt;
    String descriptionTxt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_add_class, null))
                // Add action buttons
                .setPositiveButton("Add Class", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        classNameTxt = ((EditText)getActivity().findViewById(R.id.classNameTextEdit))
                                .getText().toString();
                        classNumTxt = ((EditText)getActivity().findViewById(R.id.classNumTextEdit))
                                .getText().toString();
                        getCheckedBoxes();
                        startTimeTxt = ((EditText)getActivity().findViewById(R.id.startTimeEditText))
                                .getText().toString();
                        endTimeTxt = ((EditText)getActivity().findViewById(R.id.endTimeEditText))
                                .getText().toString();
                        profTxt = ((EditText)getActivity().findViewById(R.id.profNameTextEdit))
                                .getText().toString();
                        roomTxt = ((EditText)getActivity().findViewById(R.id.roomTextEdit))
                                .getText().toString();
                        descriptionTxt = ((EditText)getActivity().findViewById(R.id.descriptionEditText))
                                .getText().toString();

//                        addNewClass();
                    }

                    private void addNewClass () {
//                        JSONObject newClassJson = ((AddClassDialog)dialog).getNewClasses();
//                        TextView newClass = (TextView)getActivity().findViewById(R.id.addClassMsgTextView);
//                        newClass.setText(getNewClasses().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddClassDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onClick(View v) {

    }

    public JSONObject getNewClasses() {
        JSONObject jsonClass = new JSONObject();
        try {
            jsonClass.put("name", classNameTxt);
            jsonClass.put("num", classNameTxt);
            jsonClass.put("days", new JSONArray(daysArr));
            jsonClass.put("startTime", startTimeTxt);
            jsonClass.put("endTime", endTimeTxt);
            jsonClass.put("prof", profTxt);
            jsonClass.put("room", roomTxt);
            jsonClass.put("description", descriptionTxt);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonClass;
    }

    private void getCheckedBoxes() {
        CheckBox cb;
        LinearLayout checkBoxes = (LinearLayout) getActivity().findViewById(R.id.daysCheckboxes);
        for (int i = 0; i < checkBoxes.getChildCount(); i++) {
            cb = (CheckBox)checkBoxes.getChildAt(i);
            if(cb.isChecked()) {
                daysArr.add(cb.getText().toString());
            }
        }
    }

}
