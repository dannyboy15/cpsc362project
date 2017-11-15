package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Daniel on 10/2/17.
 */

public class ClubAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private Context context;
    private ArrayList<Club> clubData;
    private Club tempClub;
    private DBHelper database;
    private JSONArray data;
    private static LayoutInflater inflater = null;
    JSONObject tempValues = null;
    int i=0;



    /*************  CustomAdapter Constructor *****************/
    public ClubAdapter(Activity a, Context c, JSONArray d) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        data = d;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public ClubAdapter(Activity a, Context c, DBHelper database) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        this.database = database;

        updateClubDB();

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private void updateClubDB() {
        clubData = new ClubData().getClubArr();
        for (Club c: clubData) {
            long id = database.checkClubExist(c.getClubName());

            if(id == -1) {
                id = database.insertClub(c);
            }
            c.setId(id);
        }
    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
        if(clubData == null)
            return 0;
        if(clubData.size() <= 0)
            return 0;
        return clubData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        long id = clubData.get(position).getId();
        Log.d("Club Adapter", String.valueOf(id));
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if(convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            view = inflater.inflate(R.layout.club_list_item, parent, false);

            /****** View Holder Object to contain tabitem.xml file elements ******/
            holder = new ViewHolder();
            holder.letterTV = (TextView) view.findViewById(R.id.clubLetter);
            holder.nameTV = (TextView) view.findViewById(R.id.className);
            holder.dayTV= (TextView)view.findViewById(R.id.clubMeetDay);
            holder.placeTV =(TextView)view.findViewById(R.id.clubMeetPlace);

            /************  Set holder with LayoutInflater ************/
            view.setTag(holder);
        }
        else
            holder = (ViewHolder)view.getTag();

        if(clubData == null || clubData.size()<=0) {
//            holder.text.setText("No Data");

        }
        else {
            /***** Get each Model object from Arraylist ********/
//            tempValues=null;
            tempClub = clubData.get(position);
            String name = tempClub.getClubName();
            holder.letterTV.setText(String.valueOf(name.charAt(0)));
            holder.nameTV.setText(name);
            holder.dayTV.setText(tempClub.getMeetDay());
            holder.placeTV.setText(tempClub.getMeetPlace());

//            try {
//                tempValues = (JSONObject) data.get(position);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            /************  Set Model values in Holder elements ***********/
//
//            try {
//                String name = tempValues.getString("name");
//                holder.letterTV.setText(String.valueOf(name.charAt(0)));
//                holder.nameTV.setText(name);
//                holder.dayTV.setText(tempValues.getString("day"));
//                holder.placeTV.setText(tempValues.getString("place"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }



            /******** Set Item Click Listner for LayoutInflater for each row *******/

//            view.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
//            Context context = activity.getApplicationContext();
//            CharSequence text = String.valueOf(v.getId());
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//
//        Intent intent = new Intent(context, ClassInfoFull.class);
//        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        intent.putextra("your_extra","your_class_value");
//        context.startActivity(intent);

//        finish();
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
        public TextView letterTV;
        public TextView nameTV;
        public TextView dayTV;
        public TextView placeTV;
    }

//    @Override
//    public void onClick(View v) {
//        Log.v("CustomAdapter", "=====Row button clicked=====");
//    }
//
//
//    }

    public JSONObject getViewInfo(int position) {
        JSONObject viewInfo = new JSONObject();
        if(data.length()<=0) {
//            holder.text.setText("No Data");

        }
        else {
            /***** Get each Model object from Arraylist ********/
//            tempValues=null;
            try {
                viewInfo = (JSONObject) data.get(position);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return viewInfo;
    }

}
