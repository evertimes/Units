package unit5.task3.view;

import java.util.List;
import unit5.task3.model.domain.Film;

public class FilmCollectionView {

    private final static String MAIN_PROMPT = "1.Просмотреть колекцию \n2.Добавить фильм\n3.Удалить фильм.\n"
        + "4.Изменить фильм \n5.Сохранить и выйти\n";
    private static final String WRONG_MENU_NUM = "Неверный пункт меню!";
    private static final String SELECT_FILM_PROMPT = "Выберите фильм:";
    private static final String FILM_NAME_PROMPT = "Введите название фильма:";
    private static final String ACTOR_NAME_PROMPT = "Введите имя актера:";
    private static final String FILM_LIST_POINT = "%d. Название фильма: \"%s\" \t Имя главного актера: \"%s\"\n";

    public void printMainPrompt() {
        System.out.println(MAIN_PROMPT);
    }

    public void printErorMessage() {
        System.out.println(WRONG_MENU_NUM);
    }

    public void printSelectFilmPrompt() {
        System.out.println(SELECT_FILM_PROMPT);
    }

    public void printFilmNamePrompt() {
        System.out.println(FILM_NAME_PROMPT);
    }

    public void printFilmActorNamePrompt() {
        System.out.println(ACTOR_NAME_PROMPT);
    }

    public void printFilmColection(List<Film> films) {
        for (int i = 0; i < films.size(); i++) {
            Film film = films.get(i);
            System.out.printf(FILM_LIST_POINT, i, film.getFilmName(),
                              film.getMainActor().getName());
        }
    }
}
