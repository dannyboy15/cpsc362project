package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Daniel on 10/2/17.
 */

public class ClassAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private Context context;
    private JSONArray data;
    private static LayoutInflater inflater = null;
    JSONObject tempValues = null;
    int i=0;

    private ArrayList<Classes> classData;
    private Classes mclass;



    /*************  CustomAdapter Constructor *****************/
    public ClassAdapter(Activity a, Context c, JSONArray d) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        data = d;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /*************  CustomAdapter Constructor *****************/
    public ClassAdapter(Activity a, Context c, ArrayList<Classes> d) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        classData = d;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
        if(classData == null)
            return 0;
        if(classData.size() <= 0)
            return 0;
        return classData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        long id = classData.get(position).getId();
        Log.d("Club Adapter", String.valueOf(id));
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if(convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            view = inflater.inflate(R.layout.class_list_item, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/
            holder = new ViewHolder();
            holder.timeTV = (TextView) view.findViewById(R.id.profName);
            holder.nameTV=(TextView)view.findViewById(R.id.className);
            holder.locationTV=(TextView)view.findViewById(R.id.classLocation);

            /************  Set holder with LayoutInflater ************/
            view.setTag(holder);
        }
        else
            holder = (ViewHolder)view.getTag();

        if(classData.size()<=0) {
//            holder.text.setText("No Data");

        }
        else {
            /***** Get each Model object from Arraylist ********/
//            tempValues=null;

            mclass = classData.get(position);
            String timeStr = mclass.getClassStartTime() + " - " + mclass.getClassEndTime();
            holder.timeTV.setText(timeStr);
            holder.nameTV.setText(mclass.getClassName());
            holder.locationTV.setText(mclass.getClassRoom());

//            try {
//                tempValues = (JSONObject) data.get(position);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            /************  Set Model values in Holder elements ***********/
//
//            try {
//                String timeStr = tempValues.getString("startTime") + " - " + tempValues.getString("endTime");
//
//                holder.timeTV.setText(timeStr);
//                holder.nameTV.setText(tempValues.getString("name"));
//                holder.locationTV.setText(tempValues.getString("room"));
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
        Context context = activity.getApplicationContext();
        CharSequence text = String.valueOf(v.getId());
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(context, ClassInfoFull.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        intent.putextra("your_extra","your_class_value");
        context.startActivity(intent);

//        finish();
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
        public TextView timeTV;
        public TextView nameTV;
        public TextView locationTV;
    }

}
