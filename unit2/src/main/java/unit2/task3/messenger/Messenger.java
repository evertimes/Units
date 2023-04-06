package unit2.task3.messenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import unit2.task3.User;

public abstract class Messenger {
    protected final HashMap<User, ArrayList<String>> messageQueues;
    protected final HashMap<User, Integer> userOffsets;

    public Messenger(){
        messageQueues = new HashMap<>();
        userOffsets = new HashMap<>();
    }

    public abstract void sendMessage(User from, User to, String message);

    public String readMessage(User forUser){
        int offset = userOffsets.get(forUser);
        ArrayList<String> userQueue = messageQueues.get(forUser);
        if(offset<userQueue.size()){
            userOffsets.put(forUser,offset+1);
            return messageQueues.get(forUser).get(offset);
        }
        throw new NoSuchElementException("No new messages!");
    }

    public List<String> readAllNewMessages(User forUser){
        int offset = userOffsets.get(forUser);
        ArrayList<String> userQueue = messageQueues.get(forUser);
        if(offset<userQueue.size()){
            ArrayList<String> result = new ArrayList<>();
            for (; offset < userQueue.size(); offset++){
                result.add(userQueue.get(offset));
            }
            userOffsets.put(forUser,offset);
            return result;
        }
        throw new NoSuchElementException("No new messages!");
    }

    public void registerUser(User user){
        messageQueues.put(user, new ArrayList<>());
        userOffsets.put(user,0);
    }
}
