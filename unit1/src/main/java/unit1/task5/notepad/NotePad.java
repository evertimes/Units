package unit1.task5.notepad;

public class NotePad {

    private static final int INITIAL_CAPACITY = 3;
    private Note[] notes;
    private int nextIndex;
    private int capacity;

    public NotePad() {
        notes = new Note[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        nextIndex = 0;
    }

    public void getNotes() {
        if (nextIndex == 0) {
            System.out.println("Notes are empty");
        }
        for (int i = 0; i < nextIndex; i++) {
            System.out.printf("Note %d:%s\n", i, notes[i].getNote());
        }
    }

    public void updateNotes(int noteNumber, Note newNote) {
        if (noteNumber >= nextIndex) {
            System.out.printf("Note with number %d doesnt exists\n",noteNumber);
        } else {
            notes[noteNumber] = newNote;
        }
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
}
