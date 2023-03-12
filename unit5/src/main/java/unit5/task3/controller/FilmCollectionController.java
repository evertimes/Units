package unit5.task3.controller;

import java.util.Scanner;
import unit5.task3.model.FilmCollection;
import unit5.task3.model.domain.Actor;
import unit5.task3.model.domain.Film;
import unit5.task3.view.FilmCollectionView;

public class FilmCollectionController {

    private FilmCollectionView view;
    private FilmCollection filmCollection;
    private SerializationController serializationController;
    private Scanner scn = new Scanner(System.in);

    public FilmCollectionController() {
        view = new FilmCollectionView();
        serializationController = new SerializationController();
    }

    public void start() {
        filmCollection = serializationController.loadCollection();
        boolean stop = false;
        do {
            view.printMainPrompt();
            switch (scn.nextInt()) {
                case 1 -> printCollection();
                case 2 -> addFilm();
                case 3 -> removeFilm();
                case 4 -> editFilm();
                case 5 -> stop = true;
                default -> view.printErorMessage();
            }
        } while (!stop);
        serializationController.saveCollection(filmCollection);
    }

    private void removeFilm() {
        view.printSelectFilmPrompt();
        filmCollection.deleteFilm(filmCollection.getFilmByNum(scn.nextInt()));
    }

    private void addFilm() {
        scn.nextLine();
        view.printFilmNamePrompt();
        String filmName = scn.nextLine();
        view.printFilmActorNamePrompt();
        String actorName = scn.nextLine();
        Actor actor = new Actor(actorName);
        Film film = new Film(filmName, actor);
        filmCollection.addFilm(film);
    }

    private void printCollection() {
        view.printFilmColection(filmCollection.getFilms());
    }

    private void editFilm() {
        view.printSelectFilmPrompt();
        int filmToUpdate = scn.nextInt();
        scn.nextLine();
        view.printFilmNamePrompt();
        String filmName = scn.nextLine();
        view.printFilmActorNamePrompt();
        String actorName = scn.nextLine();
        Actor actor = new Actor(actorName);
        Film film = new Film(filmName, actor);
        filmCollection.updateFilmByNum(filmToUpdate, film);
    }
}
