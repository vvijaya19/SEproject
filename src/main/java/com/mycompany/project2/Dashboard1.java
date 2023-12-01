/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.project2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.animation.Transition;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;


/**
 *
 * @author vyshn
 */
public class Dashboard1 extends Application {

    private static Scene scene;
    private static Dashboard1 instance;
    private static Pane p;
    private static Pane p2;
    private TranslateTransition translateTransition;
    private List<Object> IC= new ArrayList<Object>();
    private HashMap<String, Object> hm = new HashMap<>();
   // private Image droneImage = new Image("C:/Users/vyshn/OneDrive/Document/NetBeansProjects/project2/src/main/resources/Images/drone.jpg"); // Replace "drone.png" with your image file path
   // private ImageView drone = new ImageView(droneImage);
    public Dashboard1() {
       
    }

    public static Dashboard1 getInstance()  {
        if (instance == null) {
            instance = new Dashboard1();
        }
        return instance;
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 800, 500);
        p=new Pane();
        p=(Pane) scene.getRoot();
        JTello j=new JTello();
        try{
        j.connect();
        j.takeOff();
        }
        catch(Exception e)
        {
         System.out.println("Not Done");
         System.out.println(e);
        }
       ObservableList<Node> observableList = FXCollections.observableArrayList();
        observableList=p.getChildren();
        TreeView t=(TreeView)p.lookup("#tv");
        p2=(Pane)p.lookup("#p2");
        System.out.println(t);
        System.out.println(p2);
        TreeItem<String> rootItem = new TreeItem<>("Root");
        t.setRoot(rootItem);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void change_price()
    {
        popup("New Price"," change Price","price");
    }
    @FXML
    public void change_x()
    {
       popup("New x-location"," change x","x");
    }
    @FXML
    public void change_y()
    {
          popup("New y-location"," change y","y");
    }
    @FXML
    public void change_length()
    {
      popup("New Length"," change Length","length");
    }
    @FXML
    public void change_name()
    {
         popup("New Name"," change Name","name");
    }
    @FXML
    public void change_width()
    {
         popup("New Width"," change Width","width");
    }
    @FXML
    public void change_height()
    {
         popup("New Height"," change Height","height");
    }
    @FXML
    public void add_item(){
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Enter Details");
        VBox popupLayout = new VBox();
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        Label xLabel = new Label("x-coordinate:");
        TextField xField = new TextField();
        Label yLabel = new Label("y-coordinate:");
        TextField yField = new TextField();
        Label lengthLabel = new Label("Length:");
        TextField lengthField = new TextField();
        Label widthLabel = new Label("Width:");
        TextField widthField = new TextField();
        Label heightLabel = new Label("Height:");
        TextField heightField = new TextField();
        Button saveButton = new Button("Save");
       
        saveButton.setOnAction(e -> {
            
            TreeView t=(TreeView)p.lookup("#tv");
            TreeItem<String> selectedItem = (TreeItem<String>) t.getSelectionModel().getSelectedItem();
            ItemContainer ic=(ItemContainer)hm.get(selectedItem.getValue());
            if (ic!= null) {
                String nf=nameField.getText();
                int pf=Integer.parseInt(priceField.getText());
                int x=Integer.parseInt(xField.getText());
                int y=Integer.parseInt(yField.getText());
                int lf=Integer.parseInt(lengthField.getText());
                int wf=Integer.parseInt(widthField.getText());
                int hf=Integer.parseInt(heightField.getText());
                Item i=new Item(nf,pf,x,y,lf,wf,hf);
                ic.getItemCollection().add(i);
                TreeItem<String> newItem = new TreeItem<String>(nameField.getText());
                selectedItem.getChildren().add(newItem);
                Rectangle square = new Rectangle(x , y ,lf,wf);
                square.setFill(null); 
                 square.setId(nf);
                square.setStroke(Color.BLACK);
                p2.getChildren().add(square); 
                Text text = new Text(x +lf+12, y +wf+ 4,nf); // Create a Text node next to the square
                text.setId("text"+nf);
                p2.getChildren().add(text); 
            }
             
            popupStage.close();
        });

        popupLayout.getChildren().addAll(nameLabel, nameField,priceLabel,priceField,xLabel,xField,yLabel,yField,lengthLabel,lengthField,widthLabel,widthField,heightLabel,heightField, saveButton);
        Scene popupScene = new Scene(popupLayout, 250, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
    @FXML
    public void add_item_container(){
       Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Enter Details");

        VBox popupLayout = new VBox();
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
      
        Label xLabel = new Label("x-coordinate:");
        TextField xField = new TextField();
       
        Label yLabel = new Label("y-coordinate:");
        TextField yField = new TextField();
        
        Label lengthLabel = new Label("Length:");
        TextField lengthField = new TextField();
        
        Label widthLabel = new Label("Width:");
        TextField widthField = new TextField();
       
        Label heightLabel = new Label("Height:");
        TextField heightField = new TextField();
        
        Button saveButton = new Button("Save");
        
        popupLayout.getChildren().addAll(nameLabel, nameField,priceLabel,priceField,xLabel,xField,yLabel,yField,lengthLabel,lengthField,widthLabel,widthField,heightLabel,heightField, saveButton);

         
        saveButton.setOnAction(e -> {
           
            String nf=nameField.getText();
            int pf=Integer.parseInt(priceField.getText());
            int x=Integer.parseInt(xField.getText());
            int y=Integer.parseInt(yField.getText());
            int lf=Integer.parseInt(lengthField.getText());
            int wf=Integer.parseInt(widthField.getText());
            int hf=Integer.parseInt(heightField.getText());
            TreeView t=(TreeView)p.lookup("#tv");
            TreeItem<String> selectedItem = (TreeItem<String>) t.getSelectionModel().getSelectedItem();
            ItemContainer ic=(ItemContainer)hm.get(selectedItem.getValue());  
            TreeItem<String> a = new TreeItem<String>(nf);
            ItemContainer ic1=new ItemContainer(nf,pf,x,y,lf,wf,hf,new ArrayList<Item>());
            if(ic==null)
                {t.getRoot().getChildren().addAll(a); }
            else
                {
                  selectedItem.getChildren().add(a);
                }
            hm.put(nf,ic1);
            Rectangle square = new Rectangle(x , y ,lf,wf);
            square.setFill(null); 
            square.setStroke(Color.BLACK);
            square.setId(nf);
            System.out.println(square.getId());
            p2.getChildren().add(square); 
            Text text = new Text(x +lf+12, y +wf+ 4,nf); // Create a Text node next to the square
            text.setId("text"+nf);
            p2.getChildren().add(text); 
            popupStage.close();
        });

        Scene popupScene = new Scene(popupLayout, 400, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
        
    }
    @FXML
    public void delete_item(){
        
      TreeView t=(TreeView)p.lookup("#tv");
      TreeItem<String> selectedItem = (TreeItem<String>) t.getSelectionModel().getSelectedItem();
            if (selectedItem != null && selectedItem.getParent() != null) {
                selectedItem.getParent().getChildren().remove(selectedItem);
                ItemContainer ic=(ItemContainer)hm.get(selectedItem.getValue());
            if (ic!= null) {
               hm.remove(ic.getName());
            }
                 
            }
    }
    
    public void popup(String a,String b, String c){
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Enter Details");

        // Create the content for the popup
        VBox popupLayout = new VBox();
        TreeView t=(TreeView)p.lookup("#tv");
        TextField field= new TextField(a);
        Button button = new Button(b);
        button.setOnAction(e -> {
            TreeItem<String> selectedItem = (TreeItem<String>) t.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String newtext = field.getText();
               
                if(hm.containsKey(selectedItem.getValue()))
                {
        
                 ItemContainer ic= (ItemContainer)hm.get(selectedItem.getValue());
                 switch (c){
                    case "name": ic.setName(newtext);
                                 selectedItem.setValue(newtext); 
                                 break;
                    case "price":ic.setPrice(Integer.parseInt(field.getText()));
                                 break;
                    case "x":ic.setx(Integer.parseInt(field.getText()));
                              Rectangle retrievedSquarex = (Rectangle) p2.lookup("#"+ic.name);
                              Text retrievedTextx= (Text)p2.lookup("#text"+ic.name);
                              System.out.println("Hello "+retrievedSquarex);
                               if (retrievedSquarex != null) {
                                    retrievedSquarex.setStroke(Color.BLACK);
                                    retrievedSquarex.setX(Integer.parseInt(field.getText()));
                                    retrievedTextx.setX( Integer.parseInt(field.getText())+ retrievedSquarex.getWidth() / 2 - retrievedTextx.getLayoutBounds().getWidth() / 2);
                                    
                                }
                             break;
                    case "y":ic.sety(Integer.parseInt(field.getText()));
                            Rectangle retrievedSquarey = (Rectangle) p2.lookup("#"+ic.name);
                              Text retrievedTexty= (Text)p2.lookup("#text"+ic.name);
                               if (retrievedSquarey != null) {
                                    retrievedSquarey.setStroke(Color.BLACK);
                                    retrievedSquarey.setY(Integer.parseInt(field.getText()));
                                    retrievedTexty.setY( Integer.parseInt(field.getText())+ retrievedSquarey.getWidth() / 2 - retrievedTexty.getLayoutBounds().getWidth() / 2);
                               }
                             break;
                    case "length":ic.setLength(Integer.parseInt(field.getText()));
                                  Rectangle retrievedSquarelength = (Rectangle) p2.lookup("#"+ic.name);
                               if (retrievedSquarelength != null) {
                                    retrievedSquarelength.setStroke(Color.BLACK);
                                    retrievedSquarelength.setWidth(Integer.parseInt(field.getText()));
                               }
                                  break;
                    case "width": ic.setWidth(Integer.parseInt(field.getText()));
                     Rectangle retrievedSquarewidth = (Rectangle) p2.lookup("#"+ic.name);
                               if (retrievedSquarewidth != null) {
                                    retrievedSquarewidth.setStroke(Color.BLACK);
                                    retrievedSquarewidth.setWidth(Integer.parseInt(field.getText()));
                               }
                                  break;
                    case "height": ic. setHeight(Integer.parseInt(field.getText()));
                                    Rectangle retrievedSquareheight = (Rectangle) p2.lookup("#"+ic.name);
                               if (retrievedSquareheight != null) {
                                    retrievedSquareheight.setStroke(Color.BLACK);
                                    retrievedSquareheight.setHeight(Integer.parseInt(field.getText()));
                               }
                                   break;
                    
                }
                }
                else
                {
                   ItemContainer ic1=(ItemContainer)hm.get(selectedItem.getParent().getValue());
                   Item i=ic1.getItemCollection().stream()
                           .filter(obj->obj.getName().equals(selectedItem.getValue()))
                           .findFirst()
                           .orElse(null);
                switch (c){
                    case "name": i.setName(newtext);
                                 selectedItem.setValue(newtext); 
                                 break;
                    case "price": i.setPrice(Integer.parseInt(field.getText()));
                                  break;
                    case "x":i.setx(Integer.parseInt(field.getText()));
                            Rectangle retrievedSquarex = (Rectangle) p2.lookup("#"+i.name);
                              Text retrievedTextx= (Text)p2.lookup("#text"+i.name);
                              System.out.println("Hello "+retrievedSquarex);
                               if (retrievedSquarex != null) {
                                    retrievedSquarex.setStroke(Color.BLACK);
                                    retrievedSquarex.setX(Integer.parseInt(field.getText()));
                                    retrievedTextx.setX( Integer.parseInt(field.getText())+ retrievedSquarex.getWidth() / 2 - retrievedTextx.getLayoutBounds().getWidth() / 2);
                                    
                                }
                             break;
                    case "y":i.sety(Integer.parseInt(field.getText()));
                             Rectangle retrievedSquarey = (Rectangle) p2.lookup("#"+i.name);
                              Text retrievedTexty= (Text)p2.lookup("#text"+i.name);
                               if (retrievedSquarey != null) {
                                    retrievedSquarey.setStroke(Color.BLACK);
                                    retrievedSquarey.setY(Integer.parseInt(field.getText()));
                                    retrievedTexty.setY( Integer.parseInt(field.getText())+ retrievedSquarey.getWidth() / 2 - retrievedTexty.getLayoutBounds().getWidth() / 2);
                               }
                             break;
                    case "length":i.setLength(Integer.parseInt(field.getText()));
                                   Rectangle retrievedSquarelength = (Rectangle) p2.lookup("#"+i.name);
                               if (retrievedSquarelength != null) {
                                    retrievedSquarelength.setStroke(Color.BLACK);
                                    retrievedSquarelength.setWidth(Integer.parseInt(field.getText()));
                               }
                                  break;
                    case "width": i.setWidth(Integer.parseInt(field.getText()));
                                   Rectangle retrievedSquarewidth = (Rectangle) p2.lookup("#"+i.name);
                               if (retrievedSquarewidth != null) {
                                    retrievedSquarewidth.setStroke(Color.BLACK);
                                    retrievedSquarewidth.setWidth(Integer.parseInt(field.getText()));
                               }
                                  break;
                    case "height": i. setHeight(Integer.parseInt(field.getText()));
                    Rectangle retrievedSquareheight = (Rectangle) p2.lookup("#"+i.name);
                               if (retrievedSquareheight != null) {
                                    retrievedSquareheight.setStroke(Color.BLACK);
                                    retrievedSquareheight.setHeight(Integer.parseInt(field.getText()));
                               }
                                   break;
                    
                }
                }
                
            }
             popupStage.close();
        });
        popupLayout.getChildren().addAll( field,button);

        Scene popupScene = new Scene(popupLayout, 550, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
    
//     void scan_item()
//    {
//    
//        drone.setFitWidth(50);
//        drone.setFitHeight(50);
//        p2.getChildren().add(drone);
//        
//        translateTransition = new TranslateTransition(Duration.seconds(2), drone);
//        RadioButton RB1=(RadioButton)p.lookup("#RB1");
//        RadioButton RB2=(RadioButton)p.lookup("#RB2");
//         ToggleGroup groupFromButton = RB1.getToggleGroup();
//         
//         groupFromButton.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                handleRadioButtonSelection((RadioButton) newValue);
//            }
//        });
//
//    }
//    private void handleRadioButtonSelection(RadioButton selectedRadioButton) {
//        // Adjust target positions based on the selected radio button
//        double targetX = 0;
//        double targetY = 0;
//
//        if (selectedRadioButton.getText().equals("Visit Item/ item Container")) {
//            targetX = 200;
//            targetY = 100;
//        } else if (selectedRadioButton.getText().equals("Scan Farm")) {
//            targetX = 300;
//            targetY = 150;
//        }
//
//       translateTransition.setToX(targetX - drone.getLayoutX());
//        translateTransition.setToY(targetY - drone.getLayoutY());
//        translateTransition.play();
//    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard1.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
