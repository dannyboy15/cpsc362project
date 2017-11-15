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
 * Created by Daniel on 11/6/17.
 */

public class TutoringAdapter extends BaseAdapter{

    /*********** Declare Used Variables *********/
    private Activity activity;
    private JSONArray data;
    private Context context;
    private static LayoutInflater inflater = null;
    JSONObject tempValues = null;
    int i=0;

    /*************  CustomAdapter Constructor *****************/
    public TutoringAdapter(Activity a, Context c, JSONArray d) {

        /********** Take passed values **********/
        activity = a;
        context = c;
        data = d;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        if(data == null)
            return 0;
        if(data.length() <= 0)
            return 0;
        return data.length();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        TutoringAdapter.ViewHolder holder;

        if(convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            view = inflater.inflate(R.layout.tutoring_list_item, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/
            holder = new TutoringAdapter.ViewHolder();
            holder.titleTV = (TextView) view.findViewById(R.id.tutorTitleTV);
            holder.linkTV=(TextView)view.findViewById(R.id.tutorLinkTV);

            /************  Set holder with LayoutInflater ************/
            view.setTag(holder);
        }
        else
            holder = (TutoringAdapter.ViewHolder)view.getTag();

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
                holder.titleTV.setText(tempValues.getString("title"));
                holder.linkTV.setText(tempValues.getString("link"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        /******** Set Item Click Listner for LayoutInflater for each row *******/

        return view;
    }


    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
        public TextView titleTV;
        public TextView linkTV;
    }
}
