package com.feedhenry.securenativeandroidtemplate.storagesample.ui.views;

import com.feedhenry.securenativeandroidtemplate.domain.models.Note;
import com.feedhenry.securenativeandroidtemplate.mvp.views.AppView;

import java.util.Collection;
import java.util.List;

/**
 * Define the interface for the view that will show the notes list
 */

public interface NoteListAppView extends AppView {

    void renderNotes(List<Note> notesCollection);

    void viewNote(Note noteToView);
}
