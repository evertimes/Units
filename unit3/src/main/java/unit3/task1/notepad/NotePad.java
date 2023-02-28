package unit3.task1.notepad;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class NotePad {

    private Note[] notes;
    private int nextIndex;
    private int capacity;

    public NotePad(int initialCapacity) {
        notes = new Note[initialCapacity];
        capacity = initialCapacity;
        nextIndex = 0;
    }

    public List<Note> getNotes() {
        return Arrays.stream(notes).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void addNote(Note note) {
        if (nextIndex >= capacity) {
            Note[] newNotesArray = new Note[capacity * 2];
            System.arraycopy(notes, 0, newNotesArray, 0, capacity);
            capacity *= 2;
            notes = newNotesArray;
        }
        notes[nextIndex++] = note;
    }

    public void updateNotes(int noteNumber, Note newNote) {
        if (noteNumber - 1 >= nextIndex || noteNumber <= 0) {
            System.out.printf("Note with number %d doesnt exists\n", noteNumber);
        } else {
            notes[noteNumber - 1] = newNote;
        }
    }

    public int getCapacity() {
        return capacity;
    }
}
