package unit6.task4.service;

import java.util.List;
import unit6.task4.domain.User;

public interface SearchService {

    List<User> searchForFriendsInWidth(User me, String name);
    List<User> searchForFriendsInDepth(User me, String name);
}