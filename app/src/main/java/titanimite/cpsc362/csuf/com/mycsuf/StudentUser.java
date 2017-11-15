package titanimite.cpsc362.csuf.com.mycsuf;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 10/16/17.
 */

public class StudentUser {
    private static final String SEPARATOR = ",";

    private long id;
    private String firstName;
    private String lastName;
    private String major;
    private String email;
    private String password;
    private String classIds;

    public StudentUser() {
    }

    public StudentUser (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentUser (String firstName, String lastName, String major, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.email = email;
        this.password = password;
        this.classIds = "";
    }

    public StudentUser (String firstName, String lastName, String major, String email, String password, String classIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.email = email;
        this.password = password;
        this.classIds = classIds;
    }

    public StudentUser (String firstName, String lastName, String major, String email, String password, ArrayList<String> classIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.email = email;
        this.password = password;
        this.classIds = arrayToString(classIds);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getClassIds(int i) {
        return stringToArray(classIds);
    }

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

    public void setClassIds(ArrayList<String> classIds) {
        this.classIds = arrayToString(classIds);
    }

    public void addClassId(long classId) {
        if(!classIds.isEmpty())
            classIds += SEPARATOR;

        this.classIds += String.valueOf(classId);
    }

    @Override
    public String toString() {
        return "StudentUser{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", major='" + major + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", classIds=" + classIds +
                '}';
    }

    public String arrayToString(ArrayList<String> arr) {
        String str = "";

        for (int i = 0; i < arr.size(); ++i) {
            str += arr.get(i);

            if(i < arr.size() - 1)
                str += SEPARATOR;
        }

        return str;
    }

    public ArrayList<String> stringToArray(String str) {
        Log.i("Class StudentUser: ", str);
        String [] strArr = str.split(SEPARATOR);

        Log.i("Class StudentUser: ", strArr.toString());
        ArrayList<String> arr = new ArrayList<String>();

        for(String s : strArr) {
            arr.add(s);
        }
        Log.i("Class StudentUser: ", arr.toString());
        return arr;
    }
}
