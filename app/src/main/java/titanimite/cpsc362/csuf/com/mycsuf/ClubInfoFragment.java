package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClubInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClubInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private JSONObject clubInfo;

    private long id;
    private DBHelper database;
    private Club club;

    public ClubInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubInfoFragment newInstance(String param1, String param2) {
        ClubInfoFragment fragment = new ClubInfoFragment();
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

        id = getArguments().getLong("id");
        Log.i("Club Frag Info", String.valueOf(id));
        database = DBHelper.getInstance(getActivity());
        club = database.getClub(id);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView clubNameTV = (TextView) getView().findViewById(R.id.nameTV);
        TextView meetDateTV = (TextView) getView().findViewById(R.id.meetingDateTV);
        TextView meetLocTV = (TextView) getView().findViewById(R.id.meetingLocTV);
        TextView contactTV = (TextView) getView().findViewById(R.id.contactInfoTV);
        TextView descTV = (TextView) getView().findViewById(R.id.descTv);

        clubNameTV.setText(club.getClubName());
        meetDateTV.setText(club.getMeetDay() + " at " + club.getMeetTime());
        meetLocTV.setText(club.getMeetPlace());
        contactTV.setText(club.getEmail());
        descTV.setText(club.getDesc());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_club_info, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openDialog();

            }
        });



        return v;

    }

    private void openDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View dialogView = inflater.inflate(R.layout.dialog_send_email, null);

        final EditText email = (EditText) dialogView.findViewById(R.id.emailTextEdit);
        final EditText subject = (EditText) dialogView.findViewById(R.id.subjectTextEdit);
        final EditText msg = (EditText) dialogView.findViewById(R.id.messageTextEdit);

        email.setText(club.getEmail());
        subject.setText("Would like info on " + club.getClubName());

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Send Email to " + club.getClubName())
                .setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/html");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email.getText().toString()});
                        intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                        intent.putExtra(Intent.EXTRA_TEXT, msg.getText());

                        startActivity(Intent.createChooser(intent, "Send Email to " + club.getClubName()));
                    }
                })
                .setNegativeButton("Cancel", null).create();
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
