package observerYoutube;

public class Youtube {
    public Youtube() {
        Channel rcChannel = new Channel();

        User user1 = new User("Bat");
        User user2 = new User("Bold");
        User user3 = new User("Sukh");

        rcChannel.subscribe(user1);
        rcChannel.subscribe(user2);
        rcChannel.subscribe(user3);

        rcChannel.notifyUsers();
    }

    public static void main(String[] args) {
        new Youtube();
    }
}
