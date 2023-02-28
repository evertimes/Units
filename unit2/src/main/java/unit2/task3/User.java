package unit2.task3;

import java.util.List;
import java.util.Objects;
import unit2.task3.messenger.Messenger;

public class User {

    private final Messenger messenger;
    private final String name;

    public User(String name, Messenger messenger) {
        this.name = name;
        this.messenger = messenger;
        messenger.registerUser(this);
    }

    public void sendMessage(User to, String message) {
        messenger.sendMessage(this, to, message);
    }

    public String readMessage() {
        return messenger.readMessage(this);
    }

    public String getName() {
        return name;
    }

    public List<String> readAllNewMessages() {
        return messenger.readAllNewMessages(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!Objects.equals(messenger, user.messenger)) {
            return false;
        }
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        int result = messenger != null ? messenger.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
