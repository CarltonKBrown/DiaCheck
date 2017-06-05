package application;

/**
 * Created by Carlton on 5/6/2017.
 */
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleAssertToEditKnowledgeBaseEthnicity {

    // private attribute declarations... instantiation is done in the initializeComponents method
    private Stage stage;
    private MenuBar menuBar;
    private Menu Dia_Check, support;
    private MenuItem signout, help;
    private Button assertButton, cancel;
    private BorderPane root;
    private VBox vBox;
    private GridPane gp;
    private HBox hBox1, hBox2;

    private CheckBox black, chinese, lebanese, syrian, white, indian;
    private String black_msg = "";
    private String chinese_msg = "";
    private String white_msg = "";
    private String lebanese_msg = "";
    private String indian_msg = "";
    private String syrian_msg = "";
    private String allrace_msg = "";

    public SimpleAssertToEditKnowledgeBaseEthnicity(Stage stage){  // class primary constructor
        this.stage = stage;		//
        initializeComponents();			//
        addNodesToPanes();				// methods from the class are called when the class has been instantiated
        registerListeners();
    }

    private void initializeComponents(){			// this method when called is where most of the attributes of the class are instantiated

        assertButton = new Button("Assert");
        cancel = new Button("cancel");

        root = new BorderPane();
        vBox = new VBox(30);
        gp = new GridPane();
        hBox1 = new HBox(10);
        hBox2 = new HBox(10);

        // menu
        menuBar = new MenuBar();
        Dia_Check = new Menu("Dia-Check");
        support = new Menu("Support");
        signout = new MenuItem("Sign out");
        help = new MenuItem("Help");
        // menu
    }

    private void addNodesToPanes(){		// method to outline how and where the nodes are to be render/ positioned on the GUI

        Dia_Check.getItems().add(signout);			// method that adds the close menu item to the menu
        support.getItems().add(help);
        menuBar.getMenus().addAll(Dia_Check, support);

        Label instruction = new Label("Please check any ethnicity now at risk");
        instruction.setStyle("-fx-font-size: 20");

        black = new CheckBox("Black Ethnicity");
        chinese = new CheckBox("Chinese Ethnicity");
        lebanese = new CheckBox("Lebanese Ethnicity");
        syrian = new CheckBox("Syrian Ethnicity");
        white = new CheckBox("White Ethnicity");
        indian = new CheckBox("Indian Ethnicity");

        root.setTop(menuBar);
        root.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(gp);
        gp.setVgap(20);
        gp.setPadding(new Insets(0, 0, 0, 150));
        hBox1.getChildren().add(instruction);
        GridPane.setHalignment(hBox1, HPos.CENTER);
        gp.add(instruction, 0, 0);
        GridPane.setHalignment(black, HPos.CENTER);
        gp.add(black, 0, 1);
        GridPane.setHalignment(chinese, HPos.CENTER);
        gp.add(chinese, 0, 2);
        GridPane.setHalignment(lebanese, HPos.CENTER);
        gp.add(lebanese, 0, 3);
        GridPane.setHalignment(syrian, HPos.CENTER);
        gp.add(syrian, 0, 4);
        GridPane.setHalignment(white, HPos.CENTER);
        gp.add(white, 0, 5);
        GridPane.setHalignment(indian, HPos.CENTER);
        gp.add(indian, 0, 6);

        hBox2.getChildren().addAll(assertButton, cancel);
        hBox2.setPadding(new Insets(25, 0, 0, 90));

        GridPane.setHalignment(hBox2, HPos.CENTER);
        gp.add(hBox2, 0, 7);
    }


    public void setStageScene(){
        Scene scene = new Scene(root,600,450);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Dia-Check currently executing....");
        stage.centerOnScreen();
        stage.show();
    }

    private void registerListeners(){  // all action events method

        assertButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                if(!black.isSelected() && !white.isSelected() && !chinese.isSelected() && !lebanese.isSelected() && !syrian.isSelected() && !indian.isSelected()){
                    JOptionPane.showMessageDialog(null, "***** PLEASE SELECT POSSIBLE AT RISK ETHNICITY *****","Risk Ethinicty Assertion", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    if(black.isSelected()){
                        String race = "black";
                        String eth = ProJava.Ethnic(race);
                        black_msg= "\nBlack "+ eth;
                    }
                    if(white.isSelected()){
                        String race = "white";
                        String eth = ProJava.Ethnic(race);
                        white_msg= "\nWhite "+ eth;
                    }
                    if(chinese.isSelected()){
                        String race = "chinese";
                        String eth = ProJava.Ethnic(race);
                        chinese_msg= "\nChinese "+ eth;
                    }
                    if(lebanese.isSelected()){
                        String race = "lebanese";
                        String eth = ProJava.Ethnic(race);
                        lebanese_msg= "\nLebanese "+ eth;
                    }
                    if(syrian.isSelected()){
                        String race = "syrian";
                        String eth = ProJava.Ethnic(race);
                        syrian_msg= "\nSyrian "+ eth;
                    }
                    if(indian.isSelected()){
                        String race = "indian";
                        String eth = ProJava.Ethnic(race);
                        indian_msg= "\nIndian "+ eth;
                    }

                    allrace_msg = black_msg + white_msg + chinese_msg + lebanese_msg + syrian_msg + indian_msg;
                    JOptionPane.showMessageDialog(null, allrace_msg,"Risk Ethinicty Assertion", JOptionPane.INFORMATION_MESSAGE);
                    stage.setScene(new MainMenu(stage).updateGUI());
                }
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                stage.setScene(new MainMenu(stage).updateGUI());
            }
        });

        signout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
            }
        });

    }
}
