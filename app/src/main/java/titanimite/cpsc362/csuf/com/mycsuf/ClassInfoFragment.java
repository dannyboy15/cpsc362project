package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeoutException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private long id;
    private Classes mClass;
    private DBHelper database;

    public ClassInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassInfoFragment newInstance(String param1, String param2) {
        ClassInfoFragment fragment = new ClassInfoFragment();
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
        Log.i("Class Frag Info", String.valueOf(id));
        database = DBHelper.getInstance(getActivity());
        mClass = database.getClass(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_info, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView classNum = (TextView) view.findViewById(R.id.classNum);
        TextView className = (TextView) view.findViewById(R.id.className);
        TextView daysText = (TextView) view.findViewById(R.id.days);
        TextView startTime = (TextView) view.findViewById(R.id.startTime);
        TextView endTime = (TextView) view.findViewById(R.id.endTime);
        TextView location = (TextView) view.findViewById(R.id.location);
        TextView prof = (TextView) view.findViewById(R.id.prof);


        classNum.setText(mClass.getClassNumber());
        className.setText(mClass.getClassName());
        daysText.setText(mClass.getClassDays());
        startTime.setText(mClass.getClassStartTime());
        endTime.setText(mClass.getClassEndTime());
        location.setText(mClass.getClassRoom());
        prof.setText(mClass.getClassProf());

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String room = ((TextView) v).getText().toString();
                String query = "";
                if(room.toLowerCase().contains("cs")) {
                    query = "https://www.google.com/maps/place/College+of+Engineering+%26+Computer+Science/@33.8819056,-117.8841177,18z/data=!4m8!1m2!2m1!1scsuf+computer+science+building!3m4!1s0x0:0xa1199206c3371c88!8m2!3d33.882343!4d-117.882656";
                }
                else if (room.toLowerCase().contains("e")) {
                    query = "https://www.google.com/maps/place/CSUF+College+of+Engineering+and+Computer+Science/@33.8819056,-117.8841177,18z/data=!4m8!1m2!2m1!1scsuf+computer+science+building!3m4!1s0x80dcd5cdcd04b9fb:0x85165632fb1d932c!8m2!3d33.8823149!4d-117.882999";
                }
                else if (room.toLowerCase().contains("hm")) {
                    query = "https://www.google.com/maps/place/McCarthy+Hall,+Fullerton,+CA+92831/@33.8796623,-117.8861321,19z/data=!3m1!4b1!4m8!1m2!2m1!1scsuf+computer+science+building!3m4!1s0x80dcd5ce236f33f7:0x9647b730e0a0b779!8m2!3d33.8796612!4d-117.8855836";
                }
                else if (room.toLowerCase().contains("hss")) {
                    query = "https://www.google.com/maps/place/College+of+Humanities+and+Social+Sciences/@33.8804169,-117.884607,19.89z/data=!4m8!1m2!2m1!1scsuf+computer+science+building!3m4!1s0x80dcd5ce09ae1749:0xeeaf45044e6f56ce!8m2!3d33.8804656!4d-117.8841618";
                }
                else {
                    query = "https://www.google.com/maps/place/California+State+University+Fullerton/@33.882927,-117.8891201,17z/data=!3m1!4b1!4m5!3m4!1s0x80dcd5ce8cc61391:0x2b9810bbb94af355!8m2!3d33.8829226!4d-117.8869261";
                }

//                Uri gmmIntentUri = Uri.parse("geo:33.8819056,-117.8841177?z=20");
                Uri gmmIntentUri = Uri.parse(query);

                Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                intent.setPackage("com.google.android.apps.maps");
//                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
//                }

            }
        });
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
