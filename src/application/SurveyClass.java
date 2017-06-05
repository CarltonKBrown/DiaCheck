package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Carlton on 5/6/2017.
 */


public class SurveyClass {

    // private attribute declarations... instantiation is done in the initializeComponents method
    private Label label, ageGroupQuestion, genderQuestion, ethnicityQuestion, heightQuestion1, heightQuestion2, weightQuestion, prediabetesQuestion, inActivityQuestion, GestationalQuestion, Polycystic_Overian_Syndrome_Question;
    private RadioButton agegroup1, agegroup2, agegroup3, agegroup4, malegroup, femalegroup, blackEthnicity, chineseEthnicity, lebaneseEthnicity, syrianEthnicity, whiteEthnicity, indianEthnicity, yesAnswer1, noAnswer1, yesAnswer2, noAnswer2, yesAnswer3, noAnswer3, yesAnswer4, noAnswer4;
    private ToggleGroup group1, group2, group3, group4, group5, group6, group7, group8;
    private Button submit, back;

    private BorderPane bPane;
    private VBox mainVBox, subVbox1, subVbox2;
    private FlowPane flowPane, flowPane2, flowPane3, flowPane4, flowPane5, flowPane6, flowPane7, flowPane8;
    private ScrollPane scrollPane;
    private Scene scene;
    private Stage primaryStage;

    private MenuBar menuBar;
    private Menu Dia_Check, support;
    private MenuItem close, help;

    private TextField heightTextField1;
    private  TextField heightTextField2;
    private TextField weightTextField;
    private BufferedImage bufferedImage;
    private Image backicon;
    private ImageView imageView;

    private String res1 = "";
    private String res2 = "";
    private String res3 = "";
    private String res4 = "";
    private String ethnic = "";
    private String age = "";
    private String gender = "";
    private String messgae ="";
    // private attribute declarations... instantiation is done in the initializeComponents method


    public SurveyClass(Stage primaryStage){    // class primary constructor
        this.primaryStage = primaryStage;	   //
        initializeComponents();					//
        menus();								// methods from the class are called when the class has been instantiated
        addNodesToPanes();
        addRootPaneToScene();
        addListeners();
        setStageScene();
    }

    private void initializeComponents() {			// this method when called is where most of the attributes of the class are instantiated

        // JavaFX label
        label = new Label("Want to know if you are at risk of getting type 2 Diabetes.... Complete the survey below to find out...");
        label.setId("lbl");


        // buttons
        submit = new Button("Submit");
        back = new Button();
        // buttons

        // adding image to button
        try {
            bufferedImage = ImageIO.read(new File("../DiaCheck/src/application/back arrow.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        backicon = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView = new ImageView();
        imageView.setImage(backicon);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        back.setGraphic(imageView);

        // adding image to button

        ageGroupQuestion = new Label("1.	To what age group do you belong? ");
        agegroup1 = new RadioButton("5 - 15 age group");
        agegroup2 = new RadioButton("16 - 30 age group");
        agegroup3 = new RadioButton("31 - 44 age group");
        agegroup4 = new RadioButton("45 and older age group");
        agegroup1.setSelected(true);



        // this class allows only one radio button to be selected at a time
        group1 = new ToggleGroup();
        agegroup1.setToggleGroup(group1);
        agegroup2.setToggleGroup(group1);
        agegroup3.setToggleGroup(group1);
        agegroup4.setToggleGroup(group1);
        // this class allows only one radio button to be selected at a time

        // using the setStyle method for some internal syling of the GUI controls
        agegroup1.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        agegroup2.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        agegroup3.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        agegroup4.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        // using the setStyle method for some internal syling of the GUI controls


        genderQuestion = new Label("2.	Gender: ");
        genderQuestion.setPadding(new Insets(10, 0, 0, 0));
        malegroup = new RadioButton("Male");
        femalegroup = new RadioButton("Female");

        group2 = new ToggleGroup();
        malegroup.setToggleGroup(group2);
        femalegroup.setToggleGroup(group2);

        malegroup.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        femalegroup.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");


        ethnicityQuestion = new Label("3.	What is your ethnicity? ");  // JavaFX Label

        // radio buttons
        blackEthnicity = new RadioButton("Black");
        chineseEthnicity = new RadioButton("Chinese");
        lebaneseEthnicity = new RadioButton("Lebanese");
        syrianEthnicity = new RadioButton("Syrian");
        whiteEthnicity = new RadioButton("White");
        indianEthnicity = new RadioButton("Indian");
        blackEthnicity.setSelected(true);
        // radio buttons

        // adding radio button controls to group object
        group3 = new ToggleGroup();
        blackEthnicity.setToggleGroup(group3);
        chineseEthnicity.setToggleGroup(group3);
        lebaneseEthnicity.setToggleGroup(group3);
        syrianEthnicity.setToggleGroup(group3);
        whiteEthnicity.setToggleGroup(group3);
        indianEthnicity.setToggleGroup(group3);
        // adding radio button controls to group object


        // using the setStyle method for some internal syling of the GUI controls
        blackEthnicity.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        chineseEthnicity.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        lebaneseEthnicity.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        syrianEthnicity.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        whiteEthnicity.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        indianEthnicity.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        // using the setStyle method for some internal syling of the GUI controls

        heightQuestion1 = new Label("4.	How tall are you?  feet:");
        heightTextField1 = new TextField();
        heightTextField1.setPrefSize(50, 20);		// sets the prefered size of the textfield(50 wide and 20 height)
        flowPane2 = new FlowPane();
        flowPane2.setHgap(3);

        heightQuestion2 = new Label(" inches: ");
        heightTextField2 = new TextField();
        heightTextField2.setPrefSize(50, 20);
        flowPane2 = new FlowPane();
        flowPane2.setHgap(3);

        weightQuestion = new Label("5.	How much do you way in pounds [lbs]?");
        weightTextField = new TextField();
        weightTextField.setPrefSize(50, 20);
        flowPane3 = new FlowPane();
        flowPane3.setHgap(3);

        prediabetesQuestion = new Label("6.	Have you ever been diagnosed with pre-diabetes by a Certified Medical Doctor?");
        yesAnswer1 = new RadioButton("Yes");
        noAnswer1 = new RadioButton("No");

        group4 = new ToggleGroup(); // this class allows only one radio button to be selected at a time
        yesAnswer1.setToggleGroup(group4);
        noAnswer1.setToggleGroup(group4);
        flowPane4 = new FlowPane();
        flowPane4.setHgap(10); // sets the horizontal spacing between child node(s)

        yesAnswer1.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        noAnswer1.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");

        inActivityQuestion = new Label("7.	Do you excercise often? [@ least ones or twice a week]");
        yesAnswer2 = new RadioButton("Yes");
        noAnswer2 = new RadioButton("No");

        group5 = new ToggleGroup();
        yesAnswer2.setToggleGroup(group5);
        noAnswer2.setToggleGroup(group5);
        flowPane5 = new FlowPane();
        flowPane5.setHgap(10);

        yesAnswer2.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        noAnswer2.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");

        GestationalQuestion = new Label("8.	Have you previously been diagnosed with Gestational Diabetes by a Certified Medical Doctor during pregnancy?");
        yesAnswer3 = new RadioButton("Yes");
        noAnswer3 = new RadioButton("No");

        group6 = new ToggleGroup();   // this class allows only one radio button to be selected at a time
        yesAnswer3.setToggleGroup(group6);
        noAnswer3.setToggleGroup(group6);
        flowPane6 = new FlowPane();
        flowPane6.setHgap(10);

        yesAnswer3.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        noAnswer3.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");

        Polycystic_Overian_Syndrome_Question = new Label("9.	Have you previously been diagnosed with Polycystic ovarian syndrome by a Certified Medical Doctor?");
        yesAnswer4 = new RadioButton("Yes");
        noAnswer4 = new RadioButton("No");

        group7 = new ToggleGroup();    // this class allows only one radio button to be selected at a time
        yesAnswer4.setToggleGroup(group7);
        noAnswer4.setToggleGroup(group7);
        flowPane7 = new FlowPane();
        flowPane7.setHgap(10);

        yesAnswer4.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");
        noAnswer4.setStyle("-fx-font-family: Forte; -fx-text-fill: grey; -fx-font-size: 13pt;");


        // layout managers
        bPane = new BorderPane();
        mainVBox = new VBox(25);
        mainVBox.setId("MainVBox");
        mainVBox.setPadding(new Insets(0, 0, 0, 10));
        scrollPane = new ScrollPane();
        subVbox1 = new VBox(10);
        subVbox2 = new VBox(10);
        flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane8 = new FlowPane();
        flowPane8.setHgap(20);
        flowPane8.setPadding(new Insets(0, 0, 10, 400));
        // layout managers

        // menu
        menuBar = new MenuBar();
        Dia_Check = new Menu("Dia-Check");
        support = new Menu("Support");
        close = new MenuItem("Close");
        help = new MenuItem("Help");
        // menu
    }

    private void menus(){
        Dia_Check.getItems().add(close);			// method that adds the close menu item to the menu
        support.getItems().add(help);
        menuBar.getMenus().addAll(Dia_Check, support);
    }

    private void addNodesToPanes(){ 		// method to outline how and where the nodes are to be render/ positioned on the GUI

        bPane.setTop(menuBar);  // position the menubar object at the to of the scene
        bPane.setCenter(scrollPane); // scrollpane node of the scene graph placed at the center of the scene
        bPane.setBottom(flowPane8); // FlowPane layout manager set to the lowest end of the scene

        // adding Insets to the different objects (top, right, lower end, left)
        VBox.setMargin(agegroup1, new Insets(0, 0, 0, 8));
        VBox.setMargin(agegroup2, new Insets(0, 0, 0, 8));
        VBox.setMargin(agegroup3, new Insets(0, 0, 0, 8));
        VBox.setMargin(agegroup4, new Insets(0, 0, 0, 8));
        subVbox1.getChildren().addAll(agegroup1, agegroup2, agegroup3, agegroup4); // adding radio buttons to VBox parent node
        FlowPane.setMargin(malegroup, new Insets(0, 0, 0, 80));
        flowPane.getChildren().addAll(malegroup, femalegroup);
        VBox.setMargin(blackEthnicity, new Insets(0, 0, 0, 8));
        VBox.setMargin(chineseEthnicity, new Insets(0, 0, 0, 8));
        VBox.setMargin(lebaneseEthnicity, new Insets(0, 0, 0, 8));
        VBox.setMargin(syrianEthnicity, new Insets(0, 0, 0, 8));
        VBox.setMargin(whiteEthnicity, new Insets(0, 0, 0, 8));
        VBox.setMargin(indianEthnicity, new Insets(0, 0, 0, 8));
        subVbox2.getChildren().addAll(blackEthnicity, chineseEthnicity, lebaneseEthnicity, syrianEthnicity, whiteEthnicity, indianEthnicity); // adding radio buttons to VBox parent node

        flowPane2.getChildren().addAll(heightQuestion1, heightTextField1);
        flowPane2.getChildren().addAll(heightQuestion2, heightTextField2);
        flowPane3.getChildren().addAll(weightQuestion, weightTextField);
        flowPane4.getChildren().addAll(yesAnswer1, noAnswer1);
        FlowPane.setMargin(yesAnswer1, new Insets(0, 0, 0, 80));
        flowPane5.getChildren().addAll(yesAnswer2, noAnswer2);
        FlowPane.setMargin(yesAnswer2, new Insets(0, 0, 0, 80));
        flowPane6.getChildren().addAll(yesAnswer3, noAnswer3);
        FlowPane.setMargin(yesAnswer3, new Insets(0, 0, 0, 80));
        flowPane7.getChildren().addAll(yesAnswer4, noAnswer4);
        FlowPane.setMargin(yesAnswer4, new Insets(0, 0, 20, 80));
        FlowPane.setMargin(noAnswer4, new Insets(0, 0, 20, 0));

        mainVBox.getChildren().addAll(label, ageGroupQuestion, subVbox1, genderQuestion, flowPane, ethnicityQuestion, subVbox2, flowPane2, flowPane3, prediabetesQuestion, flowPane4, inActivityQuestion, flowPane5, GestationalQuestion, flowPane6, Polycystic_Overian_Syndrome_Question, flowPane7);

        scrollPane.setContent(mainVBox);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);   // set to never allow horizontal scrolling
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);	// set to always allow vertical scrolling

        flowPane8.getChildren().addAll(back, submit);
    }

    private void addRootPaneToScene(){
        scene = new Scene(bPane, 900, 950);  // borderpane is the root of the scene graph. The width of the scene is 900px and the heightbeing 950px
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    }

    private void setStageScene(){
        primaryStage.setScene(scene);		// set the scene of the stage
        primaryStage.centerOnScreen();		// set the stage to the center of the screen
        primaryStage.show();				// make the stage visible
    }

    private void addListeners(){   // Listeners for button events and text field events
        group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group2.getSelectedToggle() != null) {
                    if(malegroup.isSelected()){
                        yesAnswer3.setDisable(true);
                        noAnswer3.setDisable(true);
                        yesAnswer4.setDisable(true);
                        noAnswer4.setDisable(true);
                    }
                    if(femalegroup.isSelected()){
                        yesAnswer3.setDisable(false);
                        noAnswer3.setDisable(false);
                        yesAnswer4.setDisable(false);
                        noAnswer4.setDisable(false);
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

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                primaryStage.setScene(new MainMenu(primaryStage).updateGUI());
            }
        });

        submit.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(group1.getSelectedToggle()==null || group2.getSelectedToggle()==null || group3.getSelectedToggle()==null ||
                        group4.getSelectedToggle()==null || group5.getSelectedToggle()==null || ((!yesAnswer3.isDisabled() && !noAnswer3.isDisable()) && group6.getSelectedToggle()==null)
                        || ((!yesAnswer4.isDisabled() && !noAnswer4.isDisable()) && group7.getSelectedToggle()==null) || heightQuestion1.getText().isEmpty() || heightQuestion2.getText().isEmpty() ||
                        weightTextField.getText().isEmpty()){
                    String warn = "**** PLEASE COMPLETE SURVEY ****";
                    JOptionPane.showMessageDialog(null, warn, "MISSING SURVEY DATA", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    if(agegroup1.isSelected()){
                        age = "5";
                    }
                    if(agegroup2.isSelected()){
                        age = "16";
                    }
                    if(agegroup3.isSelected()){
                        age = "31";
                    }
                    if(agegroup4.isSelected()){
                        age = "45";
                    }
                    if(malegroup.isSelected()){
                        gender= "male";
                    }
                    if(femalegroup.isSelected()){
                        gender= "female";
                    }
                    if(blackEthnicity.isSelected()){
                        ethnic= "black";
                    }
                    if(chineseEthnicity.isSelected()){
                        ethnic= "chinese";
                    }
                    if(lebaneseEthnicity.isSelected()){
                        ethnic= "lebanese";
                    }
                    if(syrianEthnicity.isSelected()){
                        ethnic= "syrian";
                    }
                    if(whiteEthnicity.isSelected()){
                        ethnic= "white";
                    }
                    if(indianEthnicity.isSelected()){
                        ethnic= "indian";
                    }
                    String fam_history= "1";
                    String feet= heightTextField1.getText();
                    String inches= heightTextField2.getText();
                    String pounds = weightTextField.getText();

                    if(yesAnswer1.isSelected()){
                        res1= "1";
                    }
                    if(noAnswer1.isSelected()){
                        res1= "2";
                    }

                    if(yesAnswer2.isSelected()){
                        res2= "1";
                    }
                    if(noAnswer2.isSelected()){
                        res2= "2";
                    }

                    if(yesAnswer3.isSelected() && !yesAnswer3.isDisable()){
                        res3= "1";
                    }

                    if(noAnswer3.isSelected() && !noAnswer3.isDisable()){
                        res3= "2";
                    }

                    if(yesAnswer4.isSelected() && !yesAnswer4.isDisable()){
                        res4= "1";
                    }
                    if(noAnswer4.isSelected() && !noAnswer4.isDisable()){
                        res4= "2";
                    }
                    if(malegroup.isSelected()){
                        messgae = ProJava.javaToProlog(age, gender, ethnic, pounds, feet, inches, res2, fam_history, res1);
                    }
                    if(femalegroup.isSelected()){
                        messgae = ProJava.javaToProlog(age, gender, ethnic, pounds, feet, inches, res2, fam_history, res1, res3, res4);
                    }

                    JOptionPane.showMessageDialog(null,messgae, "Risk Level (1-12) Low Risk to High Risk",JOptionPane.INFORMATION_MESSAGE );

                    MainMenu start_page = new MainMenu(primaryStage);
                    Scene temp = start_page.updateGUI();
                    start_page.setStageScene(temp);

                }
            }
        });
    }

}