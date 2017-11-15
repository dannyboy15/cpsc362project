package titanimite.cpsc362.csuf.com.mycsuf;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 11/6/17.
 */

public class Classes {
    private final String SEPARATOR = ",";

    private long id;
    private String className;
    private String classNumber;
    private String classDays;
    private String classStartTime;
    private String classEndTime;
    private String classProf;
    private String classRoom;
    private String classDesc;

    public Classes() {
    }

    public Classes(String className, String classNumber, String classDays, String classStartTime,
                   String classEndTime, String classProf, String classRoom, String classDesc) {
        this.className = className;
        this.classNumber = classNumber;
        this.classDays = classDays;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classProf = classProf;
        this.classRoom = classRoom;
        this.classDesc = classDesc;
    }

    public Classes(String className, String classNumber, ArrayList<String> classDays, String classStartTime,
                   String classEndTime, String classProf, String classRoom, String classDesc) {
        this.className = className;
        this.classNumber = classNumber;
        this.classDays = arrayToString(classDays);
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classProf = classProf;
        this.classRoom = classRoom;
        this.classDesc = classDesc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassDays() {
        return classDays;
    }

    public ArrayList<String> getClassDays(int i) {
        return stringToArray(classDays);
    }

    public void setClassDays(String classDays) {
        this.classDays = classDays;
    }

    public void setClassDays(ArrayList<String> classDays) {
        this.classDays = arrayToString(classDays);
    }

    public String getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(String classStartTime) {
        this.classStartTime = classStartTime;
    }

    public String getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    public String getClassProf() {
        return classProf;
    }

    public void setClassProf(String classProf) {
        this.classProf = classProf;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", classDays='" + classDays + '\'' +
                ", classStartTime='" + classStartTime + '\'' +
                ", classEndTime='" + classEndTime + '\'' +
                ", classProf='" + classProf + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", classDesc='" + classDesc + '\'' +
                '}';
    }

    public String arrayToString(ArrayList<String> daysArr) {
        String str = "";

        for (int i = 0; i < daysArr.size(); ++i) {
            str += daysArr.get(i);

            if(i < daysArr.size() - 1)
                str += SEPARATOR;
        }

        return str;
    }

    public ArrayList<String> stringToArray(String daysStr) {
        ArrayList<String> daysArr = new ArrayList<String>(Arrays.asList(daysStr.split(SEPARATOR)));
        return daysArr;
    }


}