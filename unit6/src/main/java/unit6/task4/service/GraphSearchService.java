package unit6.task4.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import unit6.task4.domain.User;

public class GraphSearchService implements SearchService {

    @Override
    public List<User> searchForFriendsInWidth(User me, String name) {
        Deque<User> nextUsers = new ArrayDeque<>();
        nextUsers.add(me);

        List<User> result = new ArrayList<>();
        Set<User> used = new HashSet<>();

        while (!nextUsers.isEmpty()) {
            User user = nextUsers.remove();
            used.add(user);

            if (user.getName().equals(name)) {
                result.add(user);
            }

            user.getFriends().stream().filter(friend -> !used.contains(friend) && !nextUsers.contains(friend)).forEach(nextUsers::add);
        }

        return result;
    }

    @Override
    public List<User> searchForFriendsInDepth(User me, String name) {
        Deque<User> nextUsers = new ArrayDeque<>();
        nextUsers.push(me);

        List<User> result = new ArrayList<>();
        Set<User> used = new HashSet<>();

        while (!nextUsers.isEmpty()) {
            User user = nextUsers.pop();
            used.add(user);

            if (user.getName().equals(name)) {
                result.add(user);
            }

            user.getFriends().stream().filter(friend -> !used.contains(friend) && !nextUsers.contains(friend)).forEach(nextUsers::push);
        }

        return result;
    }
}
