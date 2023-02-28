package unit2.task3.messenger;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import unit2.task3.User;

public class InfoMessenger extends Messenger {

    @Override
    public void sendMessage(User from, User to, String message) {
        if (messageQueues.containsKey(to)) {
            String infoMessage = String.format("[%s] From %s by %s: %s",
                                               LocalDateTime.now(), from.getName(),
                                               this.getClass().getSimpleName(), message);
            messageQueues.get(to).add(infoMessage);
        } else {
            throw new NoSuchElementException("User not found in this messenger");
        }
    }
}
