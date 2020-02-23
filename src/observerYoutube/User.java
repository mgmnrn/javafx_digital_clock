package observerYoutube;

public class User {
    private String name;
    private Channel ch;

    public User(String name) {
        this.name = name;
    }

    public Channel getCh() {
        return ch;
    }

    public void setCh(Channel ch) {
        this.ch = ch;
    }

    public void update(){
        System.out.println("Video uploaded");
    }
}
