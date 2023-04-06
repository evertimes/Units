package unit2.task3.messenger;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import unit2.task3.User;

public class TimedMessenger extends Messenger{

    @Override
    public void sendMessage(User from, User to, String message) {
        if(messageQueues.containsKey(to)) {
            String timedMessage = String.format("[%s] From %s: %s", LocalDateTime.now(),from.getName(),message);
            messageQueues.get(to).add(timedMessage);
        }else{
            throw new NoSuchElementException("User not found in this messenger");
        }
    }
}
