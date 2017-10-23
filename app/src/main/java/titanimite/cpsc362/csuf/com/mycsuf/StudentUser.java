package titanimite.cpsc362.csuf.com.mycsuf;

import java.util.ArrayList;

/**
 * Created by Daniel on 10/16/17.
 */

public class StudentUser {
    private int id;
    private String firstName;
    private String lastName;
    private String major;
    private String email;
    private String password;
    private ArrayList<Long> classIds;

    public StudentUser() {
    }

    public StudentUser (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentUser (String firstName, String lastName, String major, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ArrayList<Long> getClassIds() {
        return classIds;
    }

    public void setClassIds(ArrayList<Long> classIds) {
        this.classIds = classIds;
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
}
