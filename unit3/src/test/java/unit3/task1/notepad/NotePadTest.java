package unit3.task1.notepad;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotePadTest {

    @Test
    void getNotes_whenNotepadEmpty_returnOptionalNull() {
        var notePad = new NotePad(2);
        var notes = notePad.getNotes();
        Assertions.assertTrue(notes.isEmpty());
    }

    @Test
    void getNotes_whenNotepadNotEmpty_returnNotesWithSameOrder() {
        var notePad = new NotePad(2);
        var note1 = new Note("note1");
        var note2 = new Note("note2");
        notePad.addNote(note1);
        notePad.addNote(note2);
        var notes = notePad.getNotes();
        Assertions.assertFalse(notes.isEmpty());
        Assertions.assertEquals(List.of(note1, note2), notes);
    }

    @Test
    void addNotes_whenSizeLimitExceeded_thenIncreaseCapacity() {
        var notePad = new NotePad(2);
        var note1 = new Note("note1");
        var note2 = new Note("note2");
        notePad.addNote(note1);
        notePad.addNote(note2);

        Assertions.assertEquals(2, notePad.getCapacity());
        var note3 = new Note("note3");
        notePad.addNote(note3);

        Assertions.assertEquals(4, notePad.getCapacity());
        Assertions.assertEquals(notePad.getNotes(), List.of(note1, note2, note3));
    }

    @Test
    void addNotes_whenSizeLimitExceeded_thenDoNotIncreaseCapacity() {
        var notePad = new NotePad(3);
        var note1 = new Note("note1");
        var note2 = new Note("note2");
        notePad.addNote(note1);
        notePad.addNote(note2);

        Assertions.assertEquals(3, notePad.getCapacity());
        var note3 = new Note("note3");
        notePad.addNote(note3);

        Assertions.assertEquals(3, notePad.getCapacity());
    }

    @Test
    void updateNote_whenNoteDoesntExists_thenDoNothing() {
        var notePad = new NotePad(3);
        var note1 = new Note("note1");
        var note2 = new Note("note2");
        notePad.addNote(note1);
        notePad.addNote(note2);

        notePad.updateNotes(3, new Note("new Note3"));

        Assertions.assertEquals(List.of(note1, note2), notePad.getNotes());
        Assertions.assertEquals(3, notePad.getCapacity());
    }

    @Test
    void updateNote_whenNoteExists_thenUpdateNote() {
        var notePad = new NotePad(3);
        var note1 = new Note("note1");
        var note2 = new Note("note2");
        notePad.addNote(note1);
        notePad.addNote(note2);

        var newNote2 = new Note("new Note2");
        notePad.updateNotes(2, newNote2);

        Assertions.assertEquals(List.of(note1, newNote2), notePad.getNotes());
        Assertions.assertEquals(3, notePad.getCapacity());
    }

    @Test
    void updateNote_whenNoteNumberNegative_thenDoNothing() {
        var notePad = new NotePad(3);
        var note1 = new Note("note1");
        var note2 = new Note("note2");
        notePad.addNote(note1);
        notePad.addNote(note2);

        var newNote2 = new Note("new Note2");
        notePad.updateNotes(-2, newNote2);

        Assertions.assertEquals(List.of(note1, note2), notePad.getNotes());
        Assertions.assertEquals(3, notePad.getCapacity());
    }
}
