package com.hoverlight.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    RecyclerView notes;
    public static NoteAdapter noteAdapter;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note temp = new Note(noteAdapter.getItemCount(), "", "");
                noteAdapter.addNote(temp);
                Intent note = new Intent(MainActivity.this, NoteActivity.class);
                note.putExtra("NOTE", temp);
                startActivity(note);
            }
        });
        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        NavigationView nav = findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(this);
        notes = findViewById(R.id.notes);
        noteAdapter = new NoteAdapter(new ArrayList<Note>());
        noteAdapter.SetOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note notes) {
                Intent note = new Intent(MainActivity.this, NoteActivity.class);
                note.putExtra("NOTE", notes);
                startActivity(note);
            }
        });
        notes.setAdapter(noteAdapter);
        notes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        notes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.exit)
            finish();
        return true;
    }
}
