package titanimite.cpsc362.csuf.com.mycsuf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {
    private long id;
    private DBHelper database;
    private EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        id = intent.getLongExtra("note_id", -1);

        database = DBHelper.getInstance(this);

        msg = (EditText) findViewById(R.id.editTextMsg);

        msg.setText(database.getNote(id).getMsg());
    }

    @Override
    protected void onPause() {
        super.onPause();

        Note note = database.getNote(id);
        note.setMsg(msg.getText().toString());

        database.updateNote(note);
    }
}
