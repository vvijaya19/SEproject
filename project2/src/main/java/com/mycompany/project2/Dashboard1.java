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
import java.util.Set;
import java.util.stream.Collectors;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.util.Duration;

/**
 *
 * @author vyshn
 */
public class Dashboard1 extends Application {

    private static Scene scene;
    private static Pane p;
    private static Pane p2;
    private List<Object> IC= new ArrayList<Object>();
    private HashMap<String, Object> hm = new HashMap<>();
     private static Dashboard1 instance;
   private static Image droneImage;
    private boolean m = true;
    private   static  ImageView droneImageView;
    private  int linesCovered = 0;
    public Dashboard1() {
        
    }

    public static Dashboard1 getInstance() {
        if (instance == null) {
            instance = new Dashboard1();
        }
        return instance;
    }
    @Override
    public void start(Stage stage) throws IOException {
        instance= Dashboard1.getInstance();
        scene = new Scene(loadFXML("primary"), 1200, 700);
        p=new Pane();
        p=(Pane) scene.getRoot();
        ObservableList<Node> observableList = FXCollections.observableArrayList();
        observableList=p.getChildren();
        TreeView t=(TreeView)p.lookup("#tv");
        p2=(Pane)p.lookup("#p2");
        System.out.println(t);
        System.out.println(p2);
        TreeItem<String> rootItem = new TreeItem<>("Root");
        TreeItem<String> command=new TreeItem<>("command-center");
        TreeItem<String> drone=new TreeItem<>("drone");
        t.setRoot(rootItem);
        rootItem.getChildren().add(command);
        command.getChildren().add(drone);
         Rectangle square = new Rectangle(20 , 20 ,100,100);
         square.setFill(null); 
            square.setStroke(Color.BLACK);
         p2.getChildren().add(square);
          droneImage = new Image(getClass().getResourceAsStream("/drone.png"));
                   droneImageView = new ImageView(droneImage);
                   System.out.println ("Imageview"+ droneImageView);
                   droneImageView.setX(30);
                   droneImageView.setY(30);
                 droneImageView.setFitWidth(50); // Set the desired width
                 droneImageView.setFitHeight(50);
                 droneImageView.setId("drone");
              p2.getChildren().add(droneImageView);
         
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void dothis()
    {
         TreeView t=(TreeView)p.lookup("#tv");
     TreeItem<String> selectedItem = ( TreeItem<String>)t.getSelectionModel().getSelectedItem();
            System.out.println("Item Selected"+ selectedItem.getValue());
            Label rl=(Label)p.lookup("#labeli");
            Label rlm=(Label)p.lookup("#labelm");
            // Check if an item is selected
            Set<String>keys=hm.keySet();
                    for(String key:keys)
                        System.out.println("key is:"+key);
            if (selectedItem != null && !selectedItem.getValue().equals("Root")) {
                if(!hm.containsKey(selectedItem.getValue()))
                 {
                      System.out.println("Item checked"+ selectedItem.getValue());
                   ItemContainer ic1=(ItemContainer)hm.get(selectedItem.getParent().getValue());
                    System.out.println(selectedItem.getParent().getValue());
                    System.out.println(hm.containsKey(selectedItem.getParent().getValue()));
//                    Set<String>keys=hm.keySet();
//                    for(String key:keys)
//                        System.out.println(key);
                   if(ic1!=null){
                        System.out.println("Itemchecked 2"+ selectedItem.getValue());
                   Item i=ic1.getItemCollection().stream()
                           .filter(obj->obj.getName().equals(selectedItem.getValue()))
                           .findFirst()
                           .orElse(null);
                   
                   if(rl!=null)
                   {
                     p.getChildren().remove(rl);
                   }
                   if(rlm!=null)
                   {
                     p.getChildren().remove(rlm);
                   }
                   Label label = new Label("Purchase price: "+String.valueOf(i.price));
                   label.setId("labeli");
                   Label labelm=new Label("Market price: "+String.valueOf(i.marketprice));
                   labelm.setId("labelm");
                    
        // Set the layout coordinates of the Label
                    label.setLayoutX(20.0); // X-coordinate
                    label.setLayoutY(10.0);
                   p.getChildren().add(label);
                     p.getChildren().add(labelm);
                   System.out.println("done");
                 }
            }
            else
                {
                  int ppic=0;
                  int mppic=0;
                  ItemContainer ic=(ItemContainer)hm.get(selectedItem.getValue());
                  ppic+=ic.price;
                  if(ic!=null)
                  {
                  for(Item i:ic.getItemCollection()){
                     ppic+=i.price;
                     mppic+=i.marketprice;
                  }
                      
                  }
                   for (TreeItem<String> child : selectedItem.getChildren()) {
                    
                       if(hm.containsKey(child.getValue()))
                       {
                          ItemContainer icc=(ItemContainer)hm.get(child.getValue());
                          ppic+=icc.price;
                  if(icc!=null)
                  {
                  for(Item i:icc.getItemCollection()){
                     ppic+=i.price;
                     mppic+=i.marketprice;
                  }
                      
                  }
                       }
                   }
                  if(rl!=null)
                   {
                     p.getChildren().remove(rl);
                   }
                  if(rlm!=null)
                   {
                     p.getChildren().remove(rlm);
                   }
                   Label label = new Label("Purchase price: "+String.valueOf(ppic));
                   label.setId("labeli");
                     Label labelm=new Label("Market price: "+String.valueOf(mppic));
                   labelm.setId("labelm");
                    
        // Set the layout coordinates of the Label
                    label.setLayoutX(20.0); // X-coordinate
                    label.setLayoutY(10.0);
                   p.getChildren().add(label);
                    p.getChildren().add(labelm);
                   System.out.println("done");
                
                }
    
    }
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
    public void change_market()
    {
        popup("New Market value","change Market value","market");
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
        Label marketLabel = new Label("Market value:");
        TextField marketField = new TextField();
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
                int mf=Integer.parseInt(marketField.getText());
                Item i=new Item(nf,pf,x,y,lf,wf,hf,mf);
                ic.getItemCollection().add(i);
                TreeItem<String> newItem = new TreeItem<String>(nameField.getText());
                selectedItem.getChildren().add(newItem);
                if(nf.equals("drone"))
                {
                  droneImage = new Image(getClass().getResourceAsStream("/drone.png"));
                   droneImageView = new ImageView(droneImage);
                   droneImageView.setX(x);
                   droneImageView.setY(y);
                 droneImageView.setFitWidth(50); // Set the desired width
                 droneImageView.setFitHeight(50);
                 droneImageView.setId(nf);
                 p2.getChildren().add(droneImageView);

                }
                else
                {
                Rectangle square = new Rectangle(x , y ,lf,wf);
                square.setFill(null); 
                 square.setId(nf);
                square.setStroke(Color.BLACK);
                p2.getChildren().add(square); 
                Text text = new Text(x +lf ,y +wf,nf); // Create a Text node next to the square
                text.setId("text"+nf);
                p2.getChildren().add(text); 
                }
            }
             
            popupStage.close();
        });

        popupLayout.getChildren().addAll(nameLabel, nameField,priceLabel,priceField,xLabel,xField,yLabel,yField,lengthLabel,lengthField,widthLabel,widthField,heightLabel,heightField,marketLabel,marketField, saveButton);
        Scene popupScene = new Scene(popupLayout, 250,450);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
    @FXML
    public void transition()
    {
        System.out.println("Am i here?");
         System.out.println(droneImageView);
      TranslateTransition transition = new TranslateTransition(Duration.seconds(2), droneImageView);
      TreeView t=(TreeView)p.lookup("#tv");
            TreeItem<String> selectedItem = (TreeItem<String>) t.getSelectionModel().getSelectedItem();
            if(hm.containsKey(selectedItem.getValue())){
            ItemContainer ic=(ItemContainer)hm.get(selectedItem.getValue());
            if (ic!= null) 
            {
      transition.setToX(ic.getx()-50);
        transition.setToY(ic.gety()-50);
        transition.play();
        System.out.println("Did i go?");
            }
    }
            else
            {
              ItemContainer ic1=(ItemContainer)hm.get(selectedItem.getParent().getValue());
                   Item i=ic1.getItemCollection().stream()
                           .filter(obj->obj.getName().equals(selectedItem.getValue()))
                           .findFirst()
                           .orElse(null);
                transition.setToX(i.getx()-50);
        transition.setToY(i.gety()-50);
        transition.play();
            }
            
    }
    @FXML
    public void go_home()
    {
     TranslateTransition transition = new TranslateTransition(Duration.seconds(2), droneImageView);
      transition.setToX(20);
        transition.setToY(20);
        transition.play();
    }
    @FXML
    public void transition_farm()
    {
     
        // Calculate the number of horizontal lines to cover
        int numLines = (int) (p2.getHeight() / 50);
      linesCovered=0;
      m=true;
        // Create TranslateTransition for horizontal movement
        TranslateTransition horizontalTransition = new TranslateTransition(Duration.seconds(1), droneImageView);
        horizontalTransition.setByX(p2.getWidth() - 50);
        horizontalTransition.setInterpolator(Interpolator.LINEAR);

        // Create TranslateTransition for vertical movement
        TranslateTransition verticalTransition = new TranslateTransition(Duration.seconds(1), droneImageView);
        verticalTransition.setByY(50);
        verticalTransition.setInterpolator(Interpolator.LINEAR);

        // Set up sequential animation (horizontal, then vertical)
        horizontalTransition.setOnFinished(event -> {
            m = !m;  if (linesCovered < numLines) {
                linesCovered++;
                verticalTransition.play();
            }
            
        });

        // Set up sequential animation (vertical, then horizontal)
        verticalTransition.setOnFinished(event -> {
            if (m) {
                
                horizontalTransition.setByX(p2.getWidth() - 50);
            } else {
                horizontalTransition.setByX(-(p2.getWidth() - 50));
            }
            horizontalTransition.play();
        });

        // Start the horizontal animation
        horizontalTransition.play();

      
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
                 Rectangle retrievedSquarex = (Rectangle) p2.lookup("#"+selectedItem.getValue());
                Text retrievedTextx= (Text)p2.lookup("#text"+selectedItem.getValue());
                p2.getChildren().remove( retrievedSquarex);
                p2.getChildren().remove( retrievedTextx);
                if(!hm.containsKey(selectedItem.getValue()))
                {
                    ItemContainer ic1=(ItemContainer)hm.get(selectedItem.getParent().getValue());
                   Item i=ic1.getItemCollection().stream()
                           .filter(obj->obj.getName().equals(selectedItem.getValue()))
                           .findFirst()
                           .orElse(null);
                   ic1.getItemCollection().remove(i);
                }
                else
                {
                ItemContainer ic=(ItemContainer)hm.get(selectedItem.getValue());
            if (ic!= null) {
               hm.remove(ic.getName());
            }
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
                    case "name": hm.remove(ic);
                        Text retrievedTextxn= (Text)p2.lookup("#text"+selectedItem.getValue());
                                 retrievedTextxn.setText(newtext);
                                 ic.setName(newtext);
                                 hm.put(newtext,ic);
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
                    case "name": Text retrievedTextxn= (Text)p2.lookup("#text"+selectedItem.getValue());
                                 retrievedTextxn.setText(newtext);
                        i.setName(newtext);
                                 selectedItem.setValue(newtext); 
                                 break;
                    case "price": i.setPrice(Integer.parseInt(field.getText()));
                                  break;
                    case "x":i.setx(Integer.parseInt(field.getText()));
                             if(i.getName().equals("drone"))
                                     {
                                        ImageView droneImageView=(ImageView)p2.lookup("#"+i.name);
                                        if(droneImageView!=null)
                                        {
                                         droneImageView.setX(Integer.parseInt(field.getText()));
                                        }
                                     }
                             else
                             {
                            Rectangle retrievedSquarex = (Rectangle) p2.lookup("#"+i.name);
                              Text retrievedTextx= (Text)p2.lookup("#text"+i.name);
                              System.out.println("Hello "+retrievedSquarex);
                               if (retrievedSquarex != null) {
                                    retrievedSquarex.setStroke(Color.BLACK);
                                    retrievedSquarex.setX(Integer.parseInt(field.getText()));
                                    retrievedTextx.setX( Integer.parseInt(field.getText())+ retrievedSquarex.getWidth() / 2 - retrievedTextx.getLayoutBounds().getWidth() / 2);
                                    
                                }
                             }
                             break;
                    case "y":i.sety(Integer.parseInt(field.getText()));
                     if(i.getName().equals("drone"))
                                     {
                                        ImageView droneImageView=(ImageView)p2.lookup("#"+i.name);
                                        if(droneImageView!=null)
                                        {
                                         droneImageView.setY(Integer.parseInt(field.getText()));
                                        }
                                     }
                            else
                     {
                             Rectangle retrievedSquarey = (Rectangle) p2.lookup("#"+i.name);
                              Text retrievedTexty= (Text)p2.lookup("#text"+i.name);
                               if (retrievedSquarey != null) {
                                    retrievedSquarey.setStroke(Color.BLACK);
                                    retrievedSquarey.setY(Integer.parseInt(field.getText()));
                                    retrievedTexty.setY( Integer.parseInt(field.getText())+ retrievedSquarey.getWidth() / 2 - retrievedTexty.getLayoutBounds().getWidth() / 2);
                               }
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
                    case "market": i.setMarketprice(Integer.parseInt(field.getText()));
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
