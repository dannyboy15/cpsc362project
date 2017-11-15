package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link NotesFragment.OnFragmentInteractionListener}
 * interface.
 */
public class NotesFragment extends ListFragment implements AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NotesFragment.OnFragmentInteractionListener mListener;

    ArrayList<Note> notesArr;
    NotesAdapter adapter;

    View.OnTouchListener touchListener;
    private DBHelper database;
    private SessionManager session;

    public NotesFragment() {
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
    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Notes");

        loadUserNotes();

        touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Snackbar.make(getView(), "Touched", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                return false;
            }
        };


        adapter = new NotesAdapter(getActivity(), getActivity().getApplication().getApplicationContext(), notesArr, database);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_notes, null);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });



        return v;
    }


    private void loadUserNotes() {

        notesArr = database.getUsersNotes(session.getUserId());

        long userId = session.getUserId();

        StudentUser s = database.getStudentUser(userId);
        ArrayList<String> classArr = s.getClassIds(0);
        Log.i("Class Fragment:", classArr.toString());

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        database = DBHelper.getInstance(getActivity().getApplicationContext());
        session = SessionManager.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public void onResume() {

        super.onResume();
        adapter.notifyDataSetChanged();
        loadUserNotes();


    }

    private void openDialog() {
        // TODO: New intent for notes

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        List<String> list = new ArrayList<String>();
        long userId = session.getUserId();

        StudentUser s = database.getStudentUser(userId);
        ArrayList<String> classArr = s.getClassIds(0);

        if(classArr.size() > 0) {
            for(String str: classArr) {
                if(str.isEmpty())
                    break;
                long classId = Long.parseLong(str);

                Classes mClass = database.getClass(classId);
                list.add(mClass.getClassName());
            }
        }


        ArrayAdapter<String> sprAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);

        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.classesSpr);
        spinner.setAdapter(sprAdapter);
        sprAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Add A Note")
                .setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String classStr = spinner.getSelectedItem().toString();
                        long classId = database.checkClassExist(classStr);

                        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                        Note note = new Note(session.getUserId(), classId, date, "", "");
                        long note_id = database.insertNote(note);
                        Log.i("Notes Frag", String.valueOf(note_id));
                        note.setId(note_id);

                        Intent intent = new Intent(getActivity(), NoteActivity.class);
                        intent.putExtra("note_id", note_id);

                        startActivity(intent);



//                        updateNotes(dialogView);
//                        Snackbar.make(getView(), "Class added", Snackbar.LENGTH_SHORT)
//                                .setAction("Action", null).show();
                    }
                })
                .setNegativeButton("Cancel", null).create();
        dialog.show();
    }

    private void updateNotes(View view) {
        Note note = getData(view);
        Log.d("TAG", note.toString());

        notesArr.add(note);
        adapter.notifyDataSetChanged();
    }

    private Note getData(View dialog) {
        String classNameTxt = ((EditText)dialog.findViewById(R.id.classNameTextEdit)).getText().toString();
        String classNumTxt = ((EditText)dialog.findViewById(R.id.classNumTextEdit)).getText().toString();
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

        Note note = new Note();
        long classId = database.checkClassExist(classNameTxt);
        Log.i("ClassFragment", String.valueOf(classId));


        long userId = session.getUserId();

        StudentUser s = database.getStudentUser(userId);
        s.addClassId(classId);
        Log.i("ClassFragment", s.toString());
        database.updateStudentUser(s);


        return note;
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
        if (context instanceof NotesFragment.OnFragmentInteractionListener) {
            mListener = (NotesFragment.OnFragmentInteractionListener) context;
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
//        Snackbar.make(getView(), "Item touched: " + String.valueOf(id), Snackbar.LENGTH_SHORT)
//                .setAction("Action", null).show();

//        Log.i("Class Frag", String.valueOf(id));
//        Bundle bundle = new Bundle();
//        bundle.putLong("id", id);
//        FragmentManager fm = getFragmentManager();
//        ClassInfoFragment classInfoFragment = new ClassInfoFragment();
//        classInfoFragment.setArguments(bundle);
//
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.contentArea, classInfoFragment).addToBackStack(null);
//        ft.commit();

        Intent intent = new Intent(getActivity(), NoteActivity.class);
        intent.putExtra("note_id", id);

        startActivity(intent);

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
