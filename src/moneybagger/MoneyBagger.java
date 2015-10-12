/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneybagger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import game.Character;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.ImageCursor;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
/**
 *
 * @author superanguri
 */
public class MoneyBagger extends Application {
    Stadium stadium;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Character jewCursor = new Character(200, 214, 1, 0, "/moneybagger/shlomo.gif", 0, 0, 0);
        ImageView image = new ImageView(jewCursor.getSpritesheet().getAFRAMES().get(0));
        Button btn = new Button();
        btn.setText("START THE GREED");
        StackPane root = new StackPane();
        root.getChildren().addAll(image, btn);
        Scene scene = new Scene(root, 300, 278);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stadium = new Stadium();
                try {
                    primaryStage.setScene(stadium.getStadium());
                } catch (IOException ex) {
                    Logger.getLogger(MoneyBagger.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
        
        
        
        scene.setCursor(new ImageCursor(jewCursor.getSpritesheet().getAFRAMES().get(0)));
        
        primaryStage.setTitle("Get The Money");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
       
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(e->{
            primaryStage.close();
            stadium.runShit = false;
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
