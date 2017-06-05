package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by Carlton on 5/6/2017.
 */

public class SignInToEditKnowledgeBase {

    // private attribute declarations... instantiation is done in the initializeComponents method
    private TextField username;
    private PasswordField password;
    private Label usernameLabel, passwordLabel, siLabel, poweredByJavaFx;
    private Button signIn;
    private BorderPane bPane;
    private VBox vbox;
    private GridPane gridPane;
    private MenuBar menuBar;
    private Menu Dia_Check, support;
    private MenuItem close, help;
    private Separator separator;
    private Stage primaryStage;
    private Scene scene;

    public SignInToEditKnowledgeBase(Stage primaryStage) {		// class primary constructor
        // TODO Auto-generated constructor stub			//
        this.primaryStage = primaryStage;					//
        initializeComponents();							// methods from the class are called when the class has been instantiated
        menus();
        addNodesToPanes();
        setActionEvents();
        //setStageScene();
    }

    private void initializeComponents(){			// this method when called is where most of the attributes of the class are instantiated
        username = new TextField();
        username.setPromptText("Enter username");
        username.setPrefWidth(250);
        password = new PasswordField();
        password.setPromptText("Enter password");
        username.setPrefWidth(250);
        usernameLabel = new Label("Username : ");
        passwordLabel = new Label("Password : ");
        signIn = new Button("Sign In");
        siLabel = new Label("Sign In");
        siLabel.setPadding(new Insets(0, 160, 0, 0));
        siLabel.setId("signInLabel");

        poweredByJavaFx = new Label("Powered by JavaFx");
        poweredByJavaFx.setId("poweredByJavaFx");

        // menu
        menuBar = new MenuBar();
        Dia_Check = new Menu("Dia-Check");
        support = new Menu("Support");
        close = new MenuItem("Close");
        help = new MenuItem("Help");
        // menu

        // layout managers
        bPane = new BorderPane();
        vbox = new VBox(5);
        gridPane = new GridPane();
        // layout managers

        separator = new Separator();
    }

    private void menus(){			// method that adds the close menu item to the menu
        Dia_Check.getItems().add(close);
        support.getItems().add(help);
        menuBar.getMenus().addAll(Dia_Check, support);
    }

    private void addNodesToPanes(){			// method to outline how and where the nodes are to be render/ positioned on the GUI
        bPane.setTop(menuBar);
        bPane.setBottom(poweredByJavaFx);
        BorderPane.setAlignment(poweredByJavaFx, Pos.BOTTOM_RIGHT);
        bPane.setCenter(vbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(siLabel, gridPane);

        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setId("grid-Pane");


        GridPane.setHalignment(separator, HPos.CENTER);
        gridPane.add(separator, 0, 0);
        GridPane.setColumnSpan(separator, 3);
        GridPane.setHalignment(usernameLabel, HPos.LEFT);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(passwordLabel, 0, 2);
        GridPane.setHalignment(username, HPos.RIGHT);
        gridPane.add(username, 1, 1);
        gridPane.add(password, 1, 2);
        GridPane.setHalignment(signIn, HPos.CENTER);
        gridPane.add(signIn, 1, 3);

        vbox.setPadding(new Insets(0, 0, 0, 180));
    }


    public void setStageScene(){
        scene = new Scene(bPane, 700, 500);			// borderpane is the root of the scene graph. The width of the scene is 900px and the heightbeing 950px
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // uses external style sheet to enhance aesthetics of the GUI
        primaryStage.setScene(scene);		// set the scene of the stage
        primaryStage.centerOnScreen();	// set the stage to the center of the screen
        primaryStage.show();			// make the stage visible
    }

    private void setActionEvents(){				// all action events method

        signIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                if(!username.getText().isEmpty() && !password.getText().isEmpty()){
                    try {
                        if(readFromFile()){
                            new SimpleAssertToEditKnowledgeBaseEthnicity(primaryStage).setStageScene();
                        }else{
                            incorrectEntry();
                        }
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        close.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                primaryStage.setScene(new MainMenu(primaryStage).updateGUI());
            }
        });
    }

    private void incorrectEntry(){		// method called when incorrect credentials were entered. It simply clears the textfield and password field. It also outlines them red
        username.setText("");
        password.setText("");
        username.setPromptText("Username maybe incorrect");
        password.setPromptText("Password maybe incorrect");
        username.setStyle("-fx-focus-color: red; -fx-faint-focus-color:red;");
        password.setStyle("-fx-focus-color: red; -fx-faint-focus-color:red;");
    }

    private boolean readFromFile() throws FileNotFoundException, IOException{		// read credentials from text file named Adminz
        File file = new File("../DiaCheck/src/Adminz.txt");
        FileInputStream fis = new FileInputStream(file);

        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = "";
        String delims = "[;]";
        String[] tokens;
        String temp1 = "", temp2 = "";
        int x = 0;
        boolean flag = false;

        while ((line = br.readLine()) != null){
            tokens = line.split(delims);
            while(x < tokens.length-1){
                temp1 = tokens[x];
                temp2 = tokens[x+1];
                if(username.getText().equals(temp1) && password.getText().equals(temp2)){
                    flag = true;
                    break;
                }else{
                    flag = false;
                }
                x++;
            }
            x = 0;
            if(flag){
                break;
            }
        }
        br.close();
        return flag;
    }
}

