/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneybagger;

import game.SpriteManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point2D;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author superanguri
 */
public class Stadium {

    private SpriteManager manager = new SpriteManager();
    private Canvas field;
    private int time;
    Font font = Font.font("Times New Roman", FontWeight.BOLD, 30);
    String text = "Score: ";

    boolean runShit = true;

    public Scene getStadium() throws IOException {
        time = 0;
        field = new Canvas(500, 500);
        field.getGraphicsContext2D().setFill(Color.RED);
        field.getGraphicsContext2D().setStroke(Color.BLACK);
        field.getGraphicsContext2D().setLineWidth(2);
        field.getGraphicsContext2D().setFont(font);
        field.getGraphicsContext2D().strokeText(text + " " + time, 0, 460);
        field.getGraphicsContext2D().fillText(text + " " + time, 0, 460);
        game.Character jewCursor = new game.Character(200, 214, 1, 0, "/moneybagger/shlomo.gif", 0, 0, 0);
        
        BorderPane bp = new BorderPane(field);

        for (int i = 0; i < 20; i++) {
            manager.setSprite(new game.Character(128, 128, 1, 0, "/moneybagger/money_bag.png", 0, (int) (Math.random() * (500 - 218)), (int) (Math.random() * (500 - 218))));
        }

        for(game.Character bag:manager.getList())
            field.getGraphicsContext2D().drawImage(bag.getSpritesheet().getAFRAMES().get(0), bag.getxPos(), bag.getyPos());

        Scene scene = new Scene(bp);
        //scene.setCursor(new ImageCursor(jewCursor.getSpritesheet().getAFRAMES().get(0)));
        scene.setOnKeyReleased(e -> {
            if (e.getCode().toString().equals("S")) {
                runShit = false;
            }
        });

        scene.setOnMouseReleased(e -> {
                
            for(game.Character bag:manager.getList()){
                if(bag.getBounds().contains(new Point2D(e.getSceneX(), e.getSceneY()))){
                    manager.removeSprite(bag);
                    refreshStage();
                    break;
                }
            }
        }
        );
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (runShit) {
                    time++;
                    field.getGraphicsContext2D().clearRect(0, 430, 500, 60);
                    field.getGraphicsContext2D().strokeText(text + " " + time, 0, 460);
                    field.getGraphicsContext2D().fillText(text + " " + time, 0, 460);

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Stadium.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(manager.getList().size() == 0)
                        runShit = false;
                }
                field.getGraphicsContext2D().clearRect(0, 0, 500, 500);
                field.getGraphicsContext2D().strokeText(">>GAME OVER<< SCORE: "+time, 25, 250);
                field.getGraphicsContext2D().fillText(">>GAME OVER<< SCORE: "+time, 25, 250);
                
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Stadium.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.exit(0);
            }
        }).start();

        return scene;
    }

    private void refreshStage() {
        field.getGraphicsContext2D().clearRect(0, 0, 500, 430);
        for (game.Character bag:manager.getList()){
            
            field.getGraphicsContext2D().drawImage(bag.getSpritesheet().getAFRAMES().get(0), bag.getxPos(), bag.getyPos());
        }
    }
}
