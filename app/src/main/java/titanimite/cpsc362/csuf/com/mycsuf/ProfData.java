package titanimite.cpsc362.csuf.com.mycsuf;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Daniel on 10/11/17.
 */

public class ProfData {

    private JSONArray profs;

    public ProfData() {
        profs = new JSONArray();
        loadData();
    }

    private void loadData() {
        JSONObject prof1 = new JSONObject();
        JSONObject prof2 = new JSONObject();
        JSONObject prof3 = new JSONObject();
        JSONObject prof4 = new JSONObject();
        JSONObject prof5 = new JSONObject();

        try {
            prof1.put("name", "Professor X");
            prof1.put("class", "CPSC 121");
            prof1.put("loc", "CS-521");
            prof1.put("hours", "9:00AM-10:00AM");

            prof2.put("name", "Professor Y");
            prof2.put("class", "CPSC 131");
            prof2.put("loc", "CS-522");
            prof2.put("hours", "11:00AM-12:00PM");

            prof3.put("name", "Professor Z");
            prof3.put("class", "CPSC 315");
            prof3.put("loc", "CS-523");
            prof3.put("hours", "1:00PM-2:00PM");

            prof4.put("name", "Professor A");
            prof4.put("class", "CPSC 322");
            prof4.put("loc", "CS-524");
            prof4.put("hours", "1:00PM-2:00PM");

            prof5.put("name", "Professor B");
            prof5.put("class", "CPSC 362");
            prof5.put("loc", "CS-524");
            prof5.put("hours", "5:00PM-6:00PM");

            profs.put(prof1);
            profs.put(prof2);
            profs.put(prof3);
            profs.put(prof4);
            profs.put(prof5);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getData() {
        return profs;
    }

    public JSONObject getDataFor(int position) {
        try {
            return profs.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}
