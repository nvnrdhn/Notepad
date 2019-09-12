package com.hoverlight.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Note> notes;
    private OnItemClickListener clickListener;
    public NoteAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        viewHolder = new NoteViewHolder(inflater.inflate(R.layout.placeholder, viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        NoteViewHolder vh = (NoteViewHolder) viewHolder;
        if (notes.get(position).getTitle().isEmpty())
            vh.title.setText("<empty>");
        else
            vh.title.setText(notes.get(position).getTitle());
        if (notes.get(position).getContent().isEmpty())
            vh.content.setText("<empty>");
        else
            vh.content.setText(notes.get(position).getContent());
        vh.id.setText("#"+String.valueOf(notes.get(position).getId()+1));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    
    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, content, id;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(notes.get(getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note notes);
    }

    public void SetOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
    
    public void deleteNote(int id) {
        notes.remove(id);
        notifyDataSetChanged();
    }

    public void addNote(Note note) {
        notes.add(note);
        notifyDataSetChanged();
    }
}
