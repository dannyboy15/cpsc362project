package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Daniel on 10/2/17.
 */

public class NotesAdapter extends BaseAdapter {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private Context context;
    private ArrayList<Note> noteData;
    private Note tempNote;
    private DBHelper database;
    private SessionManager session;
    private static LayoutInflater inflater = null;
    int i=0;



    /*************  CustomAdapter Constructor *****************/
    public NotesAdapter(Activity a, Context c, ArrayList<Note> d, DBHelper database) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        noteData = d;
        this.database = database;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public NotesAdapter(Activity a, Context c, DBHelper database) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        this.database = database;
        session = SessionManager.getInstance(c);

        loadNoteData();


        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private void loadNoteData() {
        noteData = database.getUsersNotes(session.getUserId());
    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
        if(noteData == null)
            return 0;
        if(noteData.size() <= 0)
            return 0;
        return noteData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        long id = noteData.get(position).getId();
        Log.d("Club Adapter", String.valueOf(id));
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if(convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            view = inflater.inflate(R.layout.note_list_item, parent, false);

            /****** View Holder Object to contain tabitem.xml file elements ******/
            holder = new ViewHolder();
            holder.shortMsgTV = (TextView) view.findViewById(R.id.shortMsgTextView);
            holder.classTV = (TextView) view.findViewById(R.id.classTextView);
            holder.dateCreatedTV= (TextView)view.findViewById(R.id.dateCreatedTextView);

            /************  Set holder with LayoutInflater ************/
            view.setTag(holder);
        }
        else
            holder = (ViewHolder)view.getTag();

        if(noteData == null || noteData.size()<=0) {}
        else {
            /***** Get each Model object from Arraylist ********/
            tempNote = noteData.get(position);
            String shortMsg = tempNote.getMsg();
            if(shortMsg.length() > 40) {
                shortMsg = tempNote.getMsg().substring(0, 41) + "...";
            }

            String className = database.getClass(tempNote.getClassId()).getClassName();

            holder.shortMsgTV.setText(shortMsg);
            holder.classTV.setText(className);
            holder.dateCreatedTV.setText(tempNote.getDateCreated());
        }


            /******** Set Item Click Listner for LayoutInflater for each row *******/

        return view;

    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
        public TextView shortMsgTV;
        public TextView classTV;
        public TextView dateCreatedTV;
    }

}
