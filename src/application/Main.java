package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Carlton on 5/6/2017.
 */
public class Main extends Application {

    public static ProJava proJava = new ProJava();

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            new MainMenu(primaryStage).setStageScene();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  static void main(String[] args){
        launch(args);
    }
}
