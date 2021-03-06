package titanimite.cpsc362.csuf.com.mycsuf;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Daniel on 10/2/17.
 */

public class ProfAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private Context context;
    private JSONArray data;
    private static LayoutInflater inflater = null;
    JSONObject tempValues = null;
    int i=0;



    /*************  CustomAdapter Constructor *****************/
    public ProfAdapter(Activity a, Context c, JSONArray d) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        data = d;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public ProfAdapter(Activity a, Context c) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        data = new ProfData().getData();

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
        if(data == null)
            return 1;
        if(data.length() <= 0)
            return 1;
        return data.length();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if(convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            view = inflater.inflate(R.layout.prof_list_item, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/
            holder = new ViewHolder();
            holder.nameTV = (TextView) view.findViewById(R.id.profName);
            holder.timeTV=(TextView)view.findViewById(R.id.className);
            holder.locationTV=(TextView)view.findViewById(R.id.profLocation);

            /************  Set holder with LayoutInflater ************/
            view.setTag(holder);
        }
        else
            holder = (ViewHolder)view.getTag();

        if(data.length()<=0) {
//            holder.text.setText("No Data");

        }
        else {
            /***** Get each Model object from Arraylist ********/
//            tempValues=null;
            try {
                tempValues = (JSONObject) data.get(position);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            /************  Set Model values in Holder elements ***********/

            try {
                holder.nameTV.setText(tempValues.getString("name"));
                holder.timeTV.setText(tempValues.getString("hours"));
                holder.locationTV.setText(tempValues.getString("loc"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



            /******** Set Item Click Listner for LayoutInflater for each row *******/

//            view.setOnClickListener(this);

        return view;

    }

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
////        intent.putextra("your_extra","your_class_value");
//        context.startActivity(intent);

//        finish();
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
        public TextView nameTV;
        public TextView timeTV;
        public TextView locationTV;
    }

//    @Override
//    public void onClick(View v) {
//        Log.v("CustomAdapter", "=====Row button clicked=====");
//    }
//
//
//    }

}
