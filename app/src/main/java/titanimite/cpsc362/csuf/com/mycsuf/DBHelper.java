package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

import java.util.ArrayList;
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
    private static final String TABLE_CLUBS = "clubs";
    private static final String TABLE_NOTES = "notes";


    // Common column names
//    private static final String KEY_ID = "id";

    // STUDENT table - column names
    private static final String KEY_SU_ID = "id";
    private static final String KEY_SU_FIRST_NAME = "first_name";
    private static final String KEY_SU_LAST_NAME = "last_name";
    private static final String KEY_SU_MAJOR = "major";
    private static final String KEY_SU_EMAIL = "email";
    private static final String KEY_SU_PASSWORD = "password";
    private static final String KEY_SU_CLASSES = "classes";

    // CLASS table - column names
    private static final String KEY_CLASS_ID = "class_id";
    private static final String KEY_CLASS_NAME = "class_name";
    private static final String KEY_CLASS_NUMBER = "class_number";
    private static final String KEY_CLASS_DAYS = "class_days";
    private static final String KEY_CLASS_START_TIME = "start_time";
    private static final String KEY_CLASS_END_TIME = "end_time";
    private static final String KEY_CLASS_PROF_NAME = "prof_name";
    private static final String KEY_CLASS_ROOM = "class_room";
    private static final String KEY_CLASS_DESC = "class_desc";

    // CLUB table - column names
    private static final String KEY_CLUB_ID = "club_id";
    private static final String KEY_CLUB_NAME = "club_name";
    private static final String KEY_CLUB_MEET_DAY = "club_meet_day";
    private static final String KEY_CLUB_MEET_TIME = "club_meet_time";
    private static final String KEY_CLUB_MEET_PLACE = "club_meet_place";
    private static final String KEY_CLUB_EMAIL = "club_email";
    private static final String KEY_CLUB_DESC = "club_desc";
    private static final String KEY_CLUB_LINK = "club_link";
    private static final String KEY_CLUB_IMG_URI = "club_img_uri";

    // NOTES table - column names
    private static final String KEY_NOTES_ID = "notes_id";
    private static final String KEY_NOTES_SU_ID = "notes_su_id";
    private static final String KEY_NOTES_CLASS_ID = "notes_class_id";
    private static final String KEY_NOTES_DATE_CREATE = "notes_date_create";
    private static final String KEY_NOTES_STATUS = "notes_status";
    private static final String KEY_NOTES_MSG = "notes_msg";



    // Table Create Statements

    // Student table create statement
    private static final String CREATE_TABLE_STUDENT_USER =
            "CREATE TABLE " + TABLE_STUDENT_USER + "(" +
                    KEY_SU_ID + " INTEGER PRIMARY KEY," +
                    KEY_SU_FIRST_NAME + " TEXT," +
                    KEY_SU_LAST_NAME + " TEXT," +
                    KEY_SU_MAJOR + " TEXT," +
                    KEY_SU_EMAIL + " TEXT," +
                    KEY_SU_PASSWORD + " TEXT," +
                    KEY_SU_CLASSES + " TEXT" + ")";

    // Classes table create statement
    private static final String CREATE_TABLE_CLASSES =
            "CREATE TABLE " + TABLE_CLASSES + "(" +
                    KEY_CLASS_ID + " INTEGER PRIMARY KEY," +
                    KEY_CLASS_NAME + " TEXT," +
                    KEY_CLASS_NUMBER + " TEXT," +
                    KEY_CLASS_DAYS + " TEXT," +
                    KEY_CLASS_START_TIME + " TEXT," +
                    KEY_CLASS_END_TIME + " TEXT," +
                    KEY_CLASS_PROF_NAME + " TEXT," +
                    KEY_CLASS_ROOM + " TEXT," +
                    KEY_CLASS_DESC + " TEXT" + ")";

    // Clubs table create statement
    private static final String CREATE_TABLE_CLUBS =
            "CREATE TABLE " + TABLE_CLUBS + "(" +
                    KEY_CLUB_ID + " INTEGER PRIMARY KEY," +
                    KEY_CLUB_NAME + " TEXT," +
                    KEY_CLUB_MEET_DAY + " TEXT," +
                    KEY_CLUB_MEET_TIME + " TEXT," +
                    KEY_CLUB_MEET_PLACE + " TEXT," +
                    KEY_CLUB_EMAIL + " TEXT," +
                    KEY_CLUB_DESC + " TEXT," +
                    KEY_CLUB_LINK + " TEXT," +
                    KEY_CLUB_IMG_URI + " TEXT" + ")";

    // Clubs table create statement
    private static final String CREATE_TABLE_NOTES =
            "CREATE TABLE " + TABLE_NOTES + "(" +
                    KEY_NOTES_ID + " INTEGER PRIMARY KEY," +
                    KEY_NOTES_SU_ID + " INTEGER," +
                    KEY_NOTES_CLASS_ID + " INTEGER," +
                    KEY_NOTES_DATE_CREATE + " TEXT," +
                    KEY_NOTES_STATUS + " TEXT," +
                    KEY_NOTES_MSG + " TEXT" + ")";



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
        db.execSQL(CREATE_TABLE_CLASSES);
        db.execSQL(CREATE_TABLE_CLUBS);
        db.execSQL(CREATE_TABLE_NOTES);

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

    /*
    *
    * Student User CRUD instructions
    *
    */

    public long insertStudentUser (StudentUser student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SU_FIRST_NAME, student.getFirstName());
        values.put(KEY_SU_LAST_NAME, student.getLastName());
        values.put(KEY_SU_MAJOR, student.getMajor());
        values.put(KEY_SU_EMAIL, student.getEmail());
        values.put(KEY_SU_PASSWORD, student.getPassword());
        values.put(KEY_SU_CLASSES, student.getClassIds());

        // insert row
        long student_id = db.insert(TABLE_STUDENT_USER, null, values);

        return student_id;
    }

    public StudentUser getStudentUser(long student_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_STUDENT_USER + " WHERE "
                + KEY_SU_ID + " = " + student_id;

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
        student.setClassIds(c.getString(c.getColumnIndex(KEY_SU_CLASSES)));

        Log.e(LOG, student.toString());

        return student;
    }

    public boolean updateStudentUser(StudentUser s) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SU_FIRST_NAME, s.getFirstName());
        values.put(KEY_SU_LAST_NAME, s.getLastName());
        values.put(KEY_SU_MAJOR, s.getMajor());
        values.put(KEY_SU_EMAIL, s.getEmail());
        values.put(KEY_SU_PASSWORD, s.getPassword());
        values.put(KEY_SU_CLASSES, s.getClassIds());

        db.update(TABLE_STUDENT_USER, values, KEY_SU_ID + " = ?",
                new String[] { String.valueOf(s.getId()) });


        return false;
    }

    public long checkUserEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT_USER + " WHERE "
                + KEY_SU_EMAIL + " = \'" + email + "\';";
        // SELECT TOP 1 1 FROM products WHERE id = 'some value';

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Log.i(LOG, "Count: " + String.valueOf(c.getCount()));
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            Log.i(LOG, "User ID: " + String.valueOf(c.getInt(c.getColumnIndex(KEY_SU_ID))));
            return c.getInt(c.getColumnIndex(KEY_SU_ID));
        }
        else {
            return -1;
        }

    }

    public long checkUserEmailAndPass(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT_USER + " WHERE "
                + KEY_SU_EMAIL + " = \'" + email + "\';";

        Cursor c = db.rawQuery(selectQuery, null);

        Log.i(LOG, "Count: " + String.valueOf(c.getCount()));
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            String dbPass = c.getString(c.getColumnIndex(KEY_SU_PASSWORD));
            if (dbPass.equals(pass)) {
                Log.i(LOG, "User ID: " + String.valueOf(c.getInt(c.getColumnIndex(KEY_SU_ID))));
                return c.getInt(c.getColumnIndex(KEY_SU_ID));
            }
            else {
                return -1;
            }
        }
        else {
            return -1;
        }

    }


    /*
    *
    * Classes CRUD instructions
    *
    */

    public long insertClass (Classes classes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CLASS_NAME, classes.getClassName());
        values.put(KEY_CLASS_NUMBER, classes.getClassNumber());
        values.put(KEY_CLASS_DAYS, classes.getClassDays());
        values.put(KEY_CLASS_START_TIME, classes.getClassStartTime());
        values.put(KEY_CLASS_END_TIME, classes.getClassEndTime());
        values.put(KEY_CLASS_PROF_NAME, classes.getClassProf());
        values.put(KEY_CLASS_ROOM, classes.getClassRoom());
        values.put(KEY_CLASS_DESC, classes.getClassDesc());

        // insert row
        long class_id = db.insert(TABLE_CLASSES, null, values);

        return class_id;
    }

    public Classes getClass(long class_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_CLASSES + " WHERE "
                + KEY_CLASS_ID + " = " + class_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Classes mClass = new Classes();
        mClass.setId(c.getInt(c.getColumnIndex(KEY_CLASS_ID)));
        mClass.setClassName(c.getString(c.getColumnIndex(KEY_CLASS_NAME)));
        mClass.setClassNumber(c.getString(c.getColumnIndex(KEY_CLASS_NUMBER)));
        mClass.setClassDays(c.getString(c.getColumnIndex(KEY_CLASS_DAYS)));
        mClass.setClassStartTime(c.getString(c.getColumnIndex(KEY_CLASS_START_TIME)));
        mClass.setClassEndTime(c.getString(c.getColumnIndex(KEY_CLASS_END_TIME)));
        mClass.setClassProf(c.getString(c.getColumnIndex(KEY_CLASS_PROF_NAME)));
        mClass.setClassRoom(c.getString(c.getColumnIndex(KEY_CLASS_ROOM)));
        mClass.setClassDesc(c.getString(c.getColumnIndex(KEY_CLASS_DESC)));

        Log.e(LOG, mClass.toString());

        return mClass;
    }

    public long checkClassExist(String className) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_CLASSES + " WHERE "
                + KEY_CLASS_NAME + " = \'" + className + "\';";
        // SELECT TOP 1 1 FROM products WHERE id = 'some value';

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Log.i(LOG, "Count: " + String.valueOf(c.getCount()));

        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            Log.i(LOG, "Class ID: " + String.valueOf(c.getInt(c.getColumnIndex(KEY_CLASS_ID))));
            return c.getInt(c.getColumnIndex(KEY_CLASS_ID));
        }
        else {
            return -1;
        }

    }


    /*
    *
    * Clubs CRUD instructions
    *
    */

    public long insertClub(Club club) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CLUB_NAME, club.getClubName());
        values.put(KEY_CLUB_MEET_DAY, club.getMeetDay());
        values.put(KEY_CLUB_MEET_TIME, club.getMeetTime());
        values.put(KEY_CLUB_MEET_PLACE, club.getMeetPlace());
        values.put(KEY_CLUB_EMAIL, club.getEmail());
        values.put(KEY_CLUB_DESC, club.getEmail());
        values.put(KEY_CLUB_LINK, club.getLink());
        values.put(KEY_CLUB_IMG_URI, club.getImgUri());

        // insert row
        long club_id = db.insert(TABLE_CLUBS, null, values);

        return club_id;
    }

    public Club getClub(long club_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_CLUBS + " WHERE "
                + KEY_CLUB_ID + " = " + club_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Club club = new Club();
        club.setId(c.getInt(c.getColumnIndex(KEY_CLUB_ID)));
        club.setClubName(c.getString(c.getColumnIndex(KEY_CLUB_NAME)));
        club.setMeetDay(c.getString(c.getColumnIndex(KEY_CLUB_MEET_DAY)));
        club.setMeetTime(c.getString(c.getColumnIndex(KEY_CLUB_MEET_TIME)));
        club.setMeetPlace(c.getString(c.getColumnIndex(KEY_CLUB_MEET_PLACE)));
        club.setEmail(c.getString(c.getColumnIndex(KEY_CLUB_EMAIL)));
        club.setDesc(c.getString(c.getColumnIndex(KEY_CLUB_DESC)));
        club.setLink(c.getString(c.getColumnIndex(KEY_CLUB_LINK)));
        club.setImgUri(c.getString(c.getColumnIndex(KEY_CLUB_IMG_URI)));

        Log.e(LOG, club.toString());

        return club;
    }

    public long checkClubExist(String clubName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_CLUBS + " WHERE "
                + KEY_CLUB_NAME + " = \'" + clubName + "\';";
        // SELECT TOP 1 1 FROM products WHERE id = 'some value';

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Log.i(LOG, "Count: " + String.valueOf(c.getCount()));

        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            Log.i(LOG, "Class ID: " + String.valueOf(c.getInt(c.getColumnIndex(KEY_CLUB_ID))));
            return c.getInt(c.getColumnIndex(KEY_CLUB_ID));
        }
        else {
            return -1;
        }

    }





    /*
    *
    * Notes CRUD instructions
    *
    */

    public long insertNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NOTES_SU_ID, note.getSutId());
        values.put(KEY_NOTES_CLASS_ID, note.getClassId());
        values.put(KEY_NOTES_DATE_CREATE, note.getDateCreated());
        values.put(KEY_NOTES_STATUS, note.getStatus());
        values.put(KEY_NOTES_MSG, note.getMsg());

        // insert row
        long note_id = db.insert(TABLE_NOTES, null, values);

        return note_id;
    }

    public Note getNote(long note_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NOTES + " WHERE "
                + KEY_NOTES_ID + " = " + note_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Note note = new Note();
        note.setId(c.getInt(c.getColumnIndex(KEY_NOTES_ID)));
        note.setSuId(c.getInt(c.getColumnIndex(KEY_NOTES_SU_ID)));
        note.setClassId(c.getInt(c.getColumnIndex(KEY_NOTES_CLASS_ID)));
        note.setDateCreated(c.getString(c.getColumnIndex(KEY_NOTES_DATE_CREATE)));
        note.setStatus(c.getString(c.getColumnIndex(KEY_NOTES_STATUS)));
        note.setMsg(c.getString(c.getColumnIndex(KEY_NOTES_MSG)));

        Log.e(LOG, note.toString());

        return note;
    }

    public boolean updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTES_SU_ID, note.getSutId());
        values.put(KEY_NOTES_CLASS_ID, note.getClassId());
        values.put(KEY_NOTES_DATE_CREATE, note.getDateCreated());
        values.put(KEY_NOTES_STATUS, note.getStatus());
        values.put(KEY_NOTES_MSG, note.getMsg());

        db.update(TABLE_NOTES, values, KEY_NOTES_ID + " = ?",
                new String[] { String.valueOf(note.getId()) });

        return false;
    }

    public ArrayList<Note> getUsersNotes(long user_id) {
        ArrayList<Note> notes = new ArrayList<Note>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NOTES + " WHERE "
                + KEY_NOTES_SU_ID + " = " + user_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(c.getInt(c.getColumnIndex(KEY_NOTES_ID)));
                note.setSuId(c.getInt(c.getColumnIndex(KEY_NOTES_SU_ID)));
                note.setClassId(c.getInt(c.getColumnIndex(KEY_NOTES_CLASS_ID)));
                note.setDateCreated(c.getString(c.getColumnIndex(KEY_NOTES_DATE_CREATE)));
                note.setStatus(c.getString(c.getColumnIndex(KEY_NOTES_STATUS)));
                note.setMsg(c.getString(c.getColumnIndex(KEY_NOTES_MSG)));

                // adding to todo list
                notes.add(note);
            } while (c.moveToNext());
        }

        return notes;
    }

//    public long checkNoteExist(String noteName) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT * FROM " + TABLE_CLUBS + " WHERE "
//                + KEY_CLUB_NAME + " = \'" + clubName + "\';";
//        // SELECT TOP 1 1 FROM products WHERE id = 'some value';
//
//        Log.e(LOG, selectQuery);
//
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        Log.i(LOG, "Count: " + String.valueOf(c.getCount()));
//
//        if (c != null && c.getCount()>0) {
//            c.moveToFirst();
//            Log.i(LOG, "Class ID: " + String.valueOf(c.getInt(c.getColumnIndex(KEY_CLUB_ID))));
//            return c.getInt(c.getColumnIndex(KEY_CLUB_ID));
//        }
//        else {
//            return -1;
//        }
//
//    }




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

//    public boolean insertClass (String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.insert("contacts", null, contentValues);
//        return true;
//    }

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

//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }
//
//    public Integer deleteContact (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("contacts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }

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
