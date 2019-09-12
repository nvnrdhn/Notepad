package com.hoverlight.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    Button save, clear, delete;
    EditText title, content;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Bundle temp = getIntent().getExtras();
        note = (Note) temp.getSerializable("NOTE");
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setTitle(title.getText().toString());
                note.setContent(content.getText().toString());
                MainActivity.noteAdapter.deleteNote(note.getId());
                MainActivity.noteAdapter.addNote(note);
                finish();
            }
        });
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("");
                content.setText("");
            }
        });
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.noteAdapter.deleteNote(note.getId());
                finish();
            }
        });
        title.setText(note.getTitle());
        content.setText(note.getContent());
    }
}
