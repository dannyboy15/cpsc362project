package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ListFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TutoringFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TutoringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutoringFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private JSONArray data;
    TutoringAdapter adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TutoringFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TutoringFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TutoringFragment newInstance(String param1, String param2) {
        TutoringFragment fragment = new TutoringFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tutoring, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Tutoring");

        setTempData();

        adapter = new TutoringAdapter(getActivity(), getActivity().getApplication().getApplicationContext(), data);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    private void openDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View dialogView = inflater.inflate(R.layout.dialog_make_appt, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Set an appointment")
                .setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Snackbar.make(getView(), "Appointment set", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                })
                .setNegativeButton("Cancel", null).create();


        List<String> list = new ArrayList<String>();
        for (int i=0; i<data.length(); i++) {
            try {
                JSONObject item = (JSONObject) data.get(i);
                String str = item.getString("title");
                list.add(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> sprAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);

        Spinner spinner = (Spinner) dialogView.findViewById(R.id.classesSpr);
        spinner.setAdapter(sprAdapter);
        sprAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final EditText aptDate = (EditText) dialogView.findViewById(R.id.editTextStatus);
        final Calendar today = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                today.set(Calendar.YEAR, year);
                today.set(Calendar.MONTH, month);
                today.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                aptDate.setText(sdf.format(today.getTime()));
            }
        };

        aptDate.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dateListener, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
//        dialog.show();

        final EditText aptTime = (EditText) dialogView.findViewById(R.id.apptTime);
        int hour = today.get(Calendar.HOUR_OF_DAY);
        int minute = today.get(Calendar.MINUTE);
        final TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                today.set(Calendar.HOUR, selectedHour);
                today.set(Calendar.MINUTE, selectedMinute);

                String myFormat = "h:mm a"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                aptTime.setText(sdf.format(today.getTime()));
            }
        };

        aptTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), timeListener, today.get(Calendar.HOUR), today.get(Calendar.MINUTE), false).show();
            }
        });

//        mTimePicker.setTitle("Select Time");
        dialog.show();

    }

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
        Snackbar.make(getView(), "Item touched: " + String.valueOf(position), Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
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

    private void setTempData() {
        data = new JSONArray();

        JSONObject tutor1 = new JSONObject();
        JSONObject tutor2 = new JSONObject();
        JSONObject tutor3 = new JSONObject();
        JSONObject tutor4 = new JSONObject();

        try {
            tutor1.put("title", "SI Tutoring");
            tutor1.put("link", "http://www.fullerton.edu/si/");

            tutor2.put("title", "General Tutoring for Computer Science");
            tutor2.put("link", "http://www.fullerton.edu/ecs/cs/resources/tutoring.php");

            tutor3.put("title", "Writing Center");
            tutor3.put("link", "http://english.fullerton.edu/writing_center/");

            tutor4.put("title", "Tutoring for GE’s");
            tutor4.put("link", "http://www.fullerton.edu/ulc/");

            data.put(tutor1);
            data.put(tutor2);
            data.put(tutor3);
            data.put(tutor4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
