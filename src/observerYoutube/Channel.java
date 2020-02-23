package observerYoutube;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private List<User> myUsers = new ArrayList<>();
    private String name;

    public void subscribe(User user){
        myUsers.add(user);
    }

    public void unSubscribe(User user){
        myUsers.remove(user);
    }

    public void notifyUsers(){
        myUsers.forEach(User::update);
    }
}
