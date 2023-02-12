package unit2.task3.messenger;

import java.util.NoSuchElementException;
import unit2.task3.User;

public class RegularMessenger extends Messenger {

    @Override
    public void sendMessage(User from, User to, String message) {
        if (messageQueues.containsKey(to)) {
            String regularMessage = String.format("From %s: %s", from.getName(), message);
            messageQueues.get(to).add(regularMessage);
        } else {
            throw new NoSuchElementException("User not found in this messenger");
        }
    }
}
