package unit2.task3;

import java.util.NoSuchElementException;
import unit2.task3.messenger.InfoMessenger;
import unit2.task3.messenger.Messenger;
import unit2.task3.messenger.RegularMessenger;
import unit2.task3.messenger.TimedMessenger;

public class EntryPoint {

    public static void main(String[] args) {
        Messenger regularMessenger = new RegularMessenger();
        Messenger timedMessenger = new TimedMessenger();
        Messenger infoMessenger = new InfoMessenger();

        User userFrom = new User("Andrey", regularMessenger);
        User userTo = new User("Sasha", regularMessenger);

        userFrom.sendMessage(userTo,"Hello!");
        System.out.println(userTo.readMessage());
        userFrom.sendMessage(userTo,"How are you?");
        userFrom.sendMessage(userTo, "What is your name?");
        System.out.println(userTo.readAllNewMessages());
        try{
            userTo.readMessage();
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        userFrom = new User("Andrey", timedMessenger);
        userTo = new User("Sasha", timedMessenger);

        userFrom.sendMessage(userTo,"Hello!");
        System.out.println(userTo.readMessage());

        userFrom = new User("Andrey", infoMessenger);
        userTo = new User("Sasha", infoMessenger);

        userFrom.sendMessage(userTo,"Hello!");
        System.out.println(userTo.readMessage());
    }
}
