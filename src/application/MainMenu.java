package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Created by Carlton on 5/6/2017.
 */


public class MainMenu{

    // private attribute declarations... instantiation is done in the initializeComponents method
    Label title, poweredByJavaFx;
    Button editKnowledgeBaseButton, surveyButton,  exitButton;
    BorderPane root;
    VBox vBox;
    GridPane gp;
    MenuBar menuBar;
    Menu Dia_Check, support;
    MenuItem exit, help;

    Stage primaryStage;
    Scene scene;

    public MainMenu(Stage primaryStage){		// class primary constructor
        this.primaryStage = primaryStage;	//
        initializeComponents();			//
        addNodesToPanes();					//	 methods from the class are called when the class has been instantiated
        registerListeners();
    }

    public void initializeComponents(){			// this method when called is where most of the attributes of the class are instantiated
        title = new Label("Dia-Check");
        title.setId("titleofApplication");
        poweredByJavaFx = new Label("Powered by JavaFx");
        poweredByJavaFx.setId("poweredByJavaFx");

        editKnowledgeBaseButton = new Button("Edit Knowledge Base");
        surveyButton = new Button("Enter Survey");
        exitButton = new Button("Exit Application");

        root = new BorderPane();
        vBox = new VBox(30);
        gp = new GridPane();

        // menu
        menuBar = new MenuBar();
        Dia_Check = new Menu("Dia-Check");
        support = new Menu("Support");
        exit = new MenuItem("Exit");
        help = new MenuItem("Help");
        // menu
    }

    public void addNodesToPanes(){		// method to outline how and where the nodes are to be render/ positioned on the GUI

        Dia_Check.getItems().add(exit); 	// method that adds the close menu item to the menu
        support.getItems().add(help);
        menuBar.getMenus().addAll(Dia_Check, support);

        root.setTop(menuBar);
        root.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(title, gp);
        gp.setVgap(20);
        gp.setPadding(new Insets(0, 0, 0, 285));
        GridPane.setHalignment(editKnowledgeBaseButton, HPos.CENTER);
        gp.add(editKnowledgeBaseButton, 0, 0);
        GridPane.setHalignment(surveyButton, HPos.CENTER);
        gp.add(surveyButton, 0, 1);
        GridPane.setHalignment(exitButton, HPos.CENTER);
        gp.add(exitButton, 0, 2);

        root.setBottom(poweredByJavaFx);
        BorderPane.setAlignment(poweredByJavaFx, Pos.BOTTOM_RIGHT);
    }

    public javafx.scene.Scene updateGUI(){
        scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        return scene;
    }

    public void setStageScene(){
        Scene scene = new Scene(root,700,500);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Dia-Check currently executing....");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void setStageScene(Scene scatta){
        scatta.setRoot(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scatta);
        primaryStage.setHeight(500);
        primaryStage.setWidth(700);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Dia-Check currently executing....");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void registerListeners(){  	// all action events method
        editKnowledgeBaseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                new SignInToEditKnowledgeBase(primaryStage).setStageScene();
            }

        });
        surveyButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                new SurveyClass(primaryStage);
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                primaryStage.close();
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                primaryStage.close();
            }
        });
    }
}

