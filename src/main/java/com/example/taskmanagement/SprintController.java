package com.example.taskmanagement;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.scene.text.*;


public class SprintController implements Initializable {
    @FXML
    private CheckBox checkBox;

    @FXML
    private Label deadLine;

    @FXML
    private Text hours;

    @FXML
    private Text minutes;

    @FXML
    private Text seconds;

    @FXML
    private AnchorPane menu;

    @FXML
    private Button rstBt;

    @FXML
    private Button pauseBt;

    @FXML
    private AnchorPane timer;

    @FXML
    private ComboBox<Integer> hoursIp;

    @FXML
    private Button exitBt;

    @FXML
    private ComboBox<Integer> minutesIp;

    @FXML
    private ComboBox<Integer> secondsIp;

    @FXML
    private Button startBt;

    Integer currSeconds;

    Map<Integer,String> numberMap;
    Thread thrd;


    Integer hmsToSeconds (Integer h,Integer m,Integer s){
        Integer hTo=h*3600;
        Integer mTo=m*60;
        Integer total=hTo +mTo+s;
        return total;
    }

    LinkedList<Integer> secondsToHms (Integer currSeconds){
        Integer hours =currSeconds/3600;
        currSeconds=currSeconds%3600;
        Integer minutes =currSeconds/60;
        currSeconds=currSeconds%60;
        Integer seconds =currSeconds;
        LinkedList<Integer> answer=new LinkedList<>();
        answer.add(hours);
        answer.add(minutes);
        answer.add(seconds);
        return answer;

    }
    void startCountDown(){
        thrd=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        setOutput();
                        Thread.sleep(1000);
                        if(currSeconds==0){
                            Platform.runLater(() ->Controller.showAlert("Over", "Time is over.", Alert.AlertType.INFORMATION));
                            thrd.stop();

                        }
                        currSeconds -=1;

                    }

                }catch(Exception e){
                    System.out.println("can't run");

                }
            }
        });
        thrd.start();


    }

    void setOutput(){
        LinkedList<Integer> currHms=secondsToHms(currSeconds);
        hours.setText(numberMap.get(currHms.get(0)));
        minutes.setText(numberMap.get(currHms.get(1)));
        seconds.setText(numberMap.get(currHms.get(2)));
    }

    @FXML
    void start(MouseEvent event) {
        currSeconds=hmsToSeconds(hoursIp.getValue(),minutesIp.getValue(),secondsIp.getValue());
        hoursIp.setValue(0);
        minutesIp.setValue(0);
        secondsIp.setValue(0);
        scrollUp();
    }

    @FXML
    void exit(MouseEvent event) {
        thrd.stop();
        hours.setText("00");
        minutes.setText("00");
        seconds.setText("00");
        if(checkBox.isSelected()){

            ((Controller)(Controller.listupdater)).removeFromAllTasks();

        }
        ((Controller)(Controller.listupdater)).removeDay();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        ObservableList<Integer> minutesAndSecondsList = FXCollections.observableArrayList();
        for(int i=0;i<=60;i++){
            if(0<=i && i<=24){
                hoursList.add(Integer.valueOf(i));
            }
            minutesAndSecondsList.add(Integer.valueOf(i));
        }
        hoursIp.setItems(hoursList);
        hoursIp.setValue(0);

        minutesIp.setItems(minutesAndSecondsList);
        minutesIp.setValue(0);

        secondsIp.setItems(minutesAndSecondsList);
        secondsIp.setValue(0);


        numberMap =new TreeMap<Integer,String>();
        for(Integer i=0; i<=60;i++){
            if(0<=i && i<=9){
                numberMap.put(i,"0"+i.toString());

            } else {
                numberMap.put(i,i.toString());
            }
        }

    }
    void scrollUp(){
        TranslateTransition tr1= new TranslateTransition();
        tr1.setDuration(Duration.millis(100));
        tr1.setToX(0);
        tr1.setToY(-200);
        tr1.setNode(menu);

        TranslateTransition tr2= new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(200);
        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(timer);

        ParallelTransition pt =new ParallelTransition(tr1,tr2);
        pt.setOnFinished(e ->{
            try{
                startCountDown();
            }
            catch(Exception ex){
                System.out.println("can't start");
            }

        });
        pt.play();

    }
    @FXML
    void rst(MouseEvent event) {
        thrd.stop();
        hours.setText("00");
        minutes.setText("00");
        seconds.setText("00");


    }
    @FXML
    void pause(MouseEvent event) {

        try{

            if(pauseBt.getText().equals("Pause")){

                pauseBt.setText("Continue");
                thrd.suspend();


            }
            else{
                pauseBt.setText("Pause");
                thrd.resume();



            }}
        catch (Exception exc){
            System.out.println("can't pause");
        }

    }




}
