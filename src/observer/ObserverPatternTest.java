package observer;

public class ObserverPatternTest {
    public static void main(String[] args) {
        MyTopic topic = new MyTopic();

        Observer obj1 = new MyTopicSubscriber("obj1");
        Observer obj2 = new MyTopicSubscriber("obj2");
        Observer obj3 = new MyTopicSubscriber("obj3");

        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        obj1.setSubject(topic);
        obj2.setSubject(topic);
        obj3.setSubject(topic);

        obj1.update();

        topic.postMesage("hi");
    }
}
