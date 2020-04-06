package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mappers.NotesMapper;
import com.udacity.cloudstorage.models.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesMapper notesMapper;

    public List<Notes> getAllNotes(int userid) throws Exception {
        List<Notes> notes = notesMapper.findByUserId(userid);
        if (notes == null) {
            throw new Exception();
        }
        return notes;
    }

    public void addNote(Notes note, int userid) {
        notesMapper.insertNote(note, userid);
    }

    public void updateNote(Notes note) {
        notesMapper.updateNote(note);
    }

    public void deleteNote(int noteid) {
        notesMapper.deleteNote(noteid);
    }

}
