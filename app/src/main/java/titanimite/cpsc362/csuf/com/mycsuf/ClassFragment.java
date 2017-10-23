package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassFragment extends ListFragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    JSONArray allClasses;
    ListView listView;
    ClassAdapter adapter;
    TextView emptyText;

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void setEmptyText(CharSequence text) {
        super.setEmptyText(text);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassFragment newInstance(String param1, String param2) {
        ClassFragment fragment = new ClassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Classes");

        allClasses = new JSONArray();

//        getSampleData();

        adapter = new ClassAdapter(getActivity(), getActivity().getApplication().getApplicationContext(), allClasses);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

//        final View emptyView = getView().findViewById(android.R.id.empty);
//        getListView().setEmptyView(emptyView);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_class, null);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
//        ListView lv = (ListView) getView().findViewById(R.id.listView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });

//        emptyText = (TextView) v.findViewById(android.R.id.empty);

        testDB();


        return v;
    }

    private void testDB() {
        DBHelper db = new DBHelper(getActivity());
        StudentUser s1 = new StudentUser("First", "last", "major", "email@email.com", "pass");
        StudentUser s2 = new StudentUser("First2", "last2", "major2", "email2@email.com", "pass2");
        long id1 = db.insertStudentUser(s1);
        long id2 = db.insertStudentUser(s2);

//        ArrayList<String> dbAll = db.getAllCotacts();
        Log.i("Class Fragment: DB_CHEK", db.getStudentUser(id1).toString());
        Log.i("Class Fragment: DB_CHEK", db.getStudentUser(id2).toString());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    private void openDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View dialogView = inflater.inflate(R.layout.dialog_add_class, null);

        final TextView etName = (EditText) dialogView.findViewById(R.id.classNameTextEdit);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Add A Class")
                .setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        updateClasses(dialogView);
                        Snackbar.make(getView(), "Class added", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
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

    private void getSampleData() {
        for (int i = 0; i < 13; i++) {
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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_class, container, false);
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
