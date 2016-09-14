package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;


public class HangMan extends Application{
    BorderPane pane = new BorderPane();
    int lettersFound = 0;
    ArrayList<HangmanTile> wordTiles = new ArrayList<HangmanTile>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(pane,600,600);
        primaryStage.setTitle("Hang Man");
        Line baseline = new Line(20, 500, 250, 500);
        Line post = new Line(60, 500, 60, 100);
        Line crossbar = new Line(60, 100, 200, 100);
        Line noose = new Line(200, 100, 200, 140);
//        drawHead();
//        drawBody();
//        drawArm("left");
//        drawArm("right");
//        drawLeg("left");
//        drawLeg("right");
        pane.getChildren().addAll(baseline, post, crossbar, noose);

        char[] word = getWord().toCharArray();
        FlowPane wordDisplay = new FlowPane();
        wordDisplay.setHgap(10);
        wordDisplay.setAlignment(Pos.CENTER);
        for (char letter : word) {
            HangmanTile tile = new HangmanTile(letter);
            wordTiles.add(tile);
            wordDisplay.getChildren().add(tile);
        }
        pane.setBottom(wordDisplay);
        primaryStage.setScene(scene);
        primaryStage.show();
        Scanner input = new Scanner(System.in);


        System.out.println("Go ahead and guess a letter");
        scene.setOnKeyPressed(e ->{
            char guess = e.getText().charAt(0);
            for(HangmanTile tile : wordTiles) {
                if (tile.letter == guess) {
                    tile.text.setText("" + guess);
                    lettersFound++;
                }
            }
        });
    }



    public boolean isGameOver(){
        return (lettersFound == wordTiles.size());
    }
    private void drawHead() {
        Circle head = new Circle(200, 165, 25);
        head.setFill(null);
        head.setStroke(Color.BLACK);
        pane.getChildren().add(head);
    }

    private void drawBody() {
        Line body = new Line(200, 190, 200, 350);
        pane.getChildren().add(body);
    }

    private void drawArm(String side) {
        Line arm = new Line();
        if (side.equals("left")) {
            arm = new Line(200, 230, 150, 300);
        } else if (side.equals("right")) {
            arm = new Line(200, 230, 250, 300);
        }
        pane.getChildren().add(arm);
    }

    private void drawLeg(String side) {
        Line leg = new Line();
        if (side.equals("left")) {
            leg = new Line(200, 350, 150, 425);
        } else if (side.equals("right")) {
            leg = new Line(200, 350, 250, 425);
        }
        pane.getChildren().add(leg);
    }
    private String getWord(){
        return "toast";
    }


    public class HangmanTile extends Pane{
        char letter;
        Text text;

        public HangmanTile(char letter){
            this.letter = letter;
            text = new Text(this.getWidth()/2, this.getHeight()/2, "_");
            text.setFont(Font.font("Courier", 36));
            this.getChildren().add(text);
        }
        public char getLetter(){
            return this.letter;
        }
    }
}

