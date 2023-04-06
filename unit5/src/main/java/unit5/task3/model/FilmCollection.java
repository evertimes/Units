package unit5.task3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import unit5.task3.model.domain.Film;

public class FilmCollection implements Serializable {

    private static final long serialVersionUID = -3096507979791133158L;

    private List<Film> films = new ArrayList<>();

    public List<Film> getFilms() {
        return films;
    }

    public void updateFilmByNum(int number, Film film) {
        films.set(number, film);
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public void deleteFilm(Film film) {
        films.remove(film);
    }

    public Film getFilmByNum(int num) {
        return films.get(num);
    }
}
