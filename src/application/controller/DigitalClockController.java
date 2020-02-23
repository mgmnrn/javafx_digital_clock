package application.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DigitalClockController {
    @FXML
    private Label hour;

    @FXML
    private Label minute;

    @FXML
    private Label second;

    @FXML
    private Button up;

    @FXML
    private Button change;

    @FXML
    private Button down;

    @FXML
    private Button play;

    @FXML
    private FontAwesomeIconView alarm;

    @FXML
    private FontAwesomeIconView battery;

    @FXML
    private Label batteryPercent;

    @FXML
    private Label gradus;

    @FXML
    private Label date;

    @FXML
    private Label slash1;

    @FXML
    private Label slash2;

    private Random random = new Random();
    private LocalDate localDate = LocalDate.now();
    private FadeTransition ft = null;
    private boolean bool = false;
    private Battery myBattery;
    private Label liveLabel = null;
    private int globalIndex = -1;
    private Thread nowTimeThread = null;
    private LocalTime localTime = null;
    private TimeTask task = null;
    private Timer timer = null;
    private boolean liveState = false;

    @FXML
    void initialize() {
        myBattery = new Battery(300, null);
        Thread batStart = new Thread(myBattery);
        batStart.start();

        Thread batPer = new Thread(() -> {
            while (myBattery.getCurrentCapacity() > 0) {
                Platform.runLater(() -> batteryPercent.setText(myBattery.getBatteryPercent()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Battery is low, turn off");
            power();
        }, "batPer");
        batPer.start();

        Thread batIco = new Thread(() -> {
            while (myBattery.getCurrentCapacity() > 0) {
                Platform.runLater(() -> battery.setGlyphName(myBattery.getBatteryIcon()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "batIco");
        batIco.start();

        up.setOnMouseClicked(event -> {
            if (liveState) {
                int num = Integer.parseInt(liveLabel.getText());
                num++;
                liveLabel.setText(String.valueOf(num));
            }
        });

        List<Label> labels = Arrays.asList(hour, minute, second);
        change.setOnMouseClicked(event -> {
            if (liveLabel != null) {
                blink(liveLabel, false);
                blink(labels.get(getQueue()), true);
            } else {
                blink(labels.get(getQueue()), true);
            }
        });

        down.setOnMouseClicked(event -> {
            if (liveState) {
                int num = Integer.parseInt(liveLabel.getText());
                num--;
                liveLabel.setText(String.valueOf(num));
            }
        });

        alarm.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    String old = alarm.getGlyphName();
                    if (old.equals("BELL_ALT"))
                        alarm.setGlyphName("BELL_SLASH_ALT");
                    else alarm.setGlyphName("BELL_ALT");
                }
            }
        });

        play.setOnMouseClicked(e -> {
            if (liveLabel != null)
                blink(liveLabel, false);

            localTime = LocalTime.of(Integer.parseInt(hour.getText()), Integer.parseInt(minute.getText()), Integer.parseInt(second.getText()));
            task = new TimeTask(localTime.toSecondOfDay());
            timer = new Timer();
            timer.schedule(task, 1000, 1000);
            nowTimeThread = new Thread(() -> {
                while (true) {
                    Platform.runLater(() -> {
                        second.setText(String.valueOf(task.getSecond()));
                        minute.setText(String.valueOf(task.getMinute()));
                        hour.setText(String.valueOf(task.getHour()));
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            nowTimeThread.start();
            liveState = false;
        });

        gradus.setText(Integer.toString(random.nextInt((30 - (-9)) + 1) + (-9)));
        date.setText(String.valueOf(localDate));
    }

    private int getQueue() {
        if (globalIndex < 2) {
            return ++globalIndex;
        } else {
            globalIndex = -1;
            return ++globalIndex;
        }
    }

    private void blink(Label label, Boolean bool) {
        liveState = true;
        if (bool) {
            liveLabel = label;
            ft = new FadeTransition(Duration.seconds(0.5), label);
            ft.setFromValue(0);
            ft.setToValue(1.0);
            ft.setCycleCount(Animation.INDEFINITE);
            ft.play();
        } else {
            label.setOpacity(1.0);
            ft.stop();
        }
    }

    @FXML
    void power() {
        if (!myBattery.isAlive())
            bool = false;
        date.setVisible(bool);
        alarm.setVisible(bool);
        batteryPercent.setVisible(bool);
        gradus.setVisible(bool);
        hour.setVisible(bool);
        slash1.setVisible(bool);
        minute.setVisible(bool);
        slash2.setVisible(bool);
        second.setVisible(bool);
        up.setVisible(bool);
        change.setVisible(bool);
        down.setVisible(bool);
        play.setVisible(bool);
        bool = !bool;
    }
}
