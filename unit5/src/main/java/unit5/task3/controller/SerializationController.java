package unit5.task3.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import unit5.task3.model.FilmCollection;

public class SerializationController {

    public FilmCollection loadCollection() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("unit5/src/main/resources"
                                                                                                 + "/FilmCollection"))) {
            return (FilmCollection) objectInputStream.readObject();
        } catch (EOFException e) {
            return new FilmCollection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveCollection(FilmCollection filmCollection) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
            new FileOutputStream("unit5/src/main/resources/FilmCollection"));) {
            objectOutputStream.writeObject(filmCollection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
