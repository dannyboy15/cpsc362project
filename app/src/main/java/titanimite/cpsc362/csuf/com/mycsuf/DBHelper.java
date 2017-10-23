package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Daniel on 10/2/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String LOG = "DBHelper: ";

    private static DBHelper sInstance;

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    public static final String DATABASE_NAME = "StudentManager.db";

    // Table names
    private static final String TABLE_STUDENT_USER = "student";
    private static final String TABLE_CLASSES = "classes";
    private static final String TABLE_TODO_PROF = "prof";

    // Common column names
    private static final String KEY_ID = "id";

    // STUDENT table - column names
    private static final String KEY_SU_ID = "id";
    private static final String KEY_SU_FIRST_NAME = "first_name";
    private static final String KEY_SU_LAST_NAME = "last_name";
    private static final String KEY_SU_MAJOR = "major";
    private static final String KEY_SU_EMAIL = "email";
    private static final String KEY_SU_PASSWORD = "password";



    // Table Create Statements
    // Student table create statement
    private static final String CREATE_TABLE_STUDENT_USER =
            "CREATE TABLE " + TABLE_STUDENT_USER + "(" +
                    KEY_SU_ID + " INTEGER PRIMARY KEY," +
                    KEY_SU_FIRST_NAME + " TEXT," +
                    KEY_SU_LAST_NAME + " TEXT," +
                    KEY_SU_MAJOR + " TEXT," +
                    KEY_SU_EMAIL + " TEXT," +
                    KEY_SU_PASSWORD + " TEXT" + ")";


//    public static final String CLASSES_TABLE_NAME = "classes";
//    public static final String CLASSES_COLUMN_ID = "id";
//    public static final String CLASSES_COLUMN_NAME = "name";
//    public static final String CLASSES_COLUMN_EMAIL = "email";
//    public static final String CLASSES_COLUMN_STREET = "street";
//    public static final String CLASSES_COLUMN_CITY = "place";
//    public static final String CLASSES_COLUMN_PHONE = "phone";
//    private static final String SQL_CREATE_CLASSES_TABLE =
//            "CREATE TABLE " + CLASSES_TABLE_NAME + " (" +
//                    CLASSES_COLUMN_ID + " INTEGER PRIMARY KEY," +
//                    CLASSES_COLUMN_NAME + " TEXT," +
//                    CLASSES_COLUMN_EMAIL + " TEXT)";
    private HashMap hp;

    public static synchronized DBHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Student table
        db.execSQL(CREATE_TABLE_STUDENT_USER);
        //TODO: create class table
        //TODO: create prof table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + CLASSES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_USER);
        //TODO: upgrade class table
        //TODO: upgrade prof table

        onCreate(db);
    }

    public long insertStudentUser (StudentUser student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SU_FIRST_NAME, student.getFirstName());
        values.put(KEY_SU_LAST_NAME, student.getLastName());
        values.put(KEY_SU_MAJOR, student.getMajor());
        values.put(KEY_SU_EMAIL, student.getEmail());
        values.put(KEY_SU_PASSWORD, student.getPassword());

        // insert row
        long student_id = db.insert(TABLE_STUDENT_USER, null, values);

        return student_id;
    }

    public StudentUser getStudentUser(long student_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_STUDENT_USER + " WHERE "
                + KEY_ID + " = " + student_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        StudentUser student = new StudentUser();
        student.setId(c.getInt(c.getColumnIndex(KEY_SU_ID)));
        student.setFirstName(c.getString(c.getColumnIndex(KEY_SU_FIRST_NAME)));
        student.setLastName(c.getString(c.getColumnIndex(KEY_SU_LAST_NAME)));
        student.setMajor(c.getString(c.getColumnIndex(KEY_SU_MAJOR)));
        student.setEmail(c.getString(c.getColumnIndex(KEY_SU_EMAIL)));
        student.setPassword(c.getString(c.getColumnIndex(KEY_SU_PASSWORD)));

        Log.e(LOG, student.toString());

        return student;
    }

    public long checkUserEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + KEY_SU_ID + " FROM " + TABLE_STUDENT_USER + " WHERE "
                + KEY_SU_EMAIL + " = " + email;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
            return c.getInt(c.getColumnIndex(KEY_SU_ID));
        }
        else {
            return -1;
        }

    }

//    public long insertStudent (String name, String email) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_FIRST_NAME, name);
//        values.put(KEY_EMAIL, email );
//
//        // insert row
//        long student_id = db.insert(TABLE_STUDENT_USER, null, values);
//
//        return student_id;
//    }

//    public String getStudentUser(long student_id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT_USER + " WHERE "
//                + KEY_ID + " = " + student_id;
//
//        Log.e(LOG, selectQuery);
//
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        if (c != null)
//            c.moveToFirst();
//
//        String s = "";
//        s = c.getString(c.getColumnIndex(KEY_FIRST_NAME)) + "-" +c.getString(c.getColumnIndex(KEY_EMAIL));
//
//        return s;
//    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

//    public List<Todo> getAllToDos() {
//        List<Todo> todos = new ArrayList<Todo>();
//        String selectQuery = "SELECT  * FROM " + TABLE_TODO;
//
//        Log.e(LOG, selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                Todo td = new Todo();
//                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
//                td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
//                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
//
//                // adding to todo list
//                todos.add(td);
//            } while (c.moveToNext());
//        }
//
//        return todos;
//    }

//    public boolean insertClass (String name, String email) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("email", email);
//        db.insert(CLASSES_TABLE_NAME, null, contentValues);
//        return true;
//    }

    public boolean insertClass (String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }

//    public Cursor getData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from " + CLASSES_TABLE_NAME + " where id="+id+"", null );
//        return res;
//    }

//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, CLASSES_TABLE_NAME);
//        return numRows;
//    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

//    public ArrayList<String> getAllCotacts() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from " + CLASSES_TABLE_NAME, null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CLASSES_COLUMN_NAME)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
}
