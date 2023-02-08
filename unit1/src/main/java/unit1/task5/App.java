package unit1.task5;

import unit1.task5.notepad.Note;
import unit1.task5.notepad.NotePad;

public class App {

    public static void main(String[] args) {
        notepad();
    }

    public static void notepad(){
        NotePad notePad = new NotePad();

        notePad.getNotes();

        notePad.addNote(new Note("First note"));
        notePad.addNote(new Note("Second note"));
        notePad.addNote(new Note("Third note"));
        notePad.getNotes();

        notePad.addNote(new Note("Fouth note"));
        notePad.getNotes();

        notePad.updateNotes(10,new Note("Updated Note"));
        notePad.getNotes();

        notePad.updateNotes(3,new Note("Updated Note"));
        notePad.getNotes();
    }
}
