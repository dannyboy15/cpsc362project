package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.UUID;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Daniel on 10/16/17.
 */

public class SessionManager implements Parcelable {
    // Shared pref mode
    private int PRIVATE_MODE = MODE_PRIVATE;

    // Sharedpref file name
    private static final String PREF_NAME = "TitanimitePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    private static final String KEY_SU_ID = "id";

    // User name (make variable public to access from outside)
    public static final String KEY_FIRST_NAME = "first_name";

    // User name (make variable public to access from outside)
    public static final String KEY_LAST_NAME = "last_name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    private static SessionManager session;

    // Shared Preferences
    private  SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context mContext;

    private String sessionId;

    private String firstName;

    private String lastName;

    private String email;

    private StudentUser student;

    public static synchronized SessionManager getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (session == null) {
            session = new SessionManager(context);
        }
        return session;
    }

    // Constructor
    public SessionManager(Context context) {
        sessionId = UUID.randomUUID().toString();
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    protected SessionManager(Parcel in) {
//        pref = (SharedPreferences) in.readValue(SharedPreferences.class.getClassLoader());
//        editor = (SharedPreferences.Editor) in.readValue(SharedPreferences.Editor.class.getClassLoader());
//        mContext = (Context) in.readValue(Context.class.getClassLoader());
//        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
//        editor = pref.edit();
        sessionId = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
    }

    public static final Creator<SessionManager> CREATOR = new Creator<SessionManager>() {
        @Override
        public SessionManager createFromParcel(Parcel in) {
            return new SessionManager(in);
        }

        @Override
        public SessionManager[] newArray(int size) {
            return new SessionManager[size];
        }
    };

    public void createLoginSession(int id, String firstName, String lastName, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putInt(KEY_SU_ID, id);

        // Storing name in pref
        editor.putString(KEY_FIRST_NAME, firstName);

        // Storing name in pref
        editor.putString(KEY_LAST_NAME, lastName);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    public void createLoginSession(StudentUser s){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putInt(KEY_SU_ID, s.getId());

        // Storing name in pref
        editor.putString(KEY_FIRST_NAME, s.getFirstName());

        // Storing name in pref
        editor.putString(KEY_LAST_NAME, s.getLastName());

        // Storing email in pref
        editor.putString(KEY_EMAIL, s.getEmail());

        // commit changes
        editor.commit();

        student = s;

        update();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_FIRST_NAME, pref.getString(KEY_FIRST_NAME, null));

        // user name
        user.put(KEY_LAST_NAME, pref.getString(KEY_LAST_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    public void setNewActivityContext (Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(mContext, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(i);
        }

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

//        // After logout redirect user to Loing Activity
//        Intent i = new Intent(mContext, LoginActivity.class);
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // Staring Login Activity
//        mContext.startActivity(i);
    }

    public void update () {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(pref);
//        dest.writeValue(editor);
//        dest.writeValue(mContext);
        dest.writeString(sessionId);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public String getFirstName() {
        return pref.getString(KEY_FIRST_NAME, "");
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return pref.getString(KEY_LAST_NAME, "");
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, "");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SessionManager{" +
                "pref=" + pref +
                ", editor=" + editor +
                ", mContext=" + mContext +
                ", sessionId=" + sessionId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
