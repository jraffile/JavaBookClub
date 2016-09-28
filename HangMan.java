import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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


public class HangMan extends Application{
    BorderPane pane = new BorderPane();
    int lettersFound = 0;
    int MAX_GUESSES = 6;
    int guessesLeft = MAX_GUESSES;
    ArrayList<HangmanTile> wordTiles = new ArrayList<HangmanTile>();
    ArrayList<HangmanTile> alphabetTiles = new ArrayList<>();
    ArrayList<Character> alphabet = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(pane,600,600);
        primaryStage.setTitle("Hang Man");
        Line baseline = new Line(20, 500, 250, 500);
        Line post = new Line(60, 500, 60, 100);
        Line crossbar = new Line(60, 100, 200, 100);
        Line noose = new Line(200, 100, 200, 140);
        pane.getChildren().addAll(baseline, post, crossbar, noose);

        String wordString = getWord();
        char[] word = wordString.toCharArray();
        FlowPane wordDisplay = new FlowPane();
        wordDisplay.setHgap(10);
        wordDisplay.setAlignment(Pos.CENTER);
        for (char letter : word) {
            HangmanTile tile = new HangmanTile(letter);
            wordTiles.add(tile);
            wordDisplay.getChildren().add(tile);
        }
        pane.setBottom(wordDisplay);

        for (char ch = 'a'; ch <= 'z'; ch++){
            alphabet.add(ch);
        }


        FlowPane alphabetDisplay = new FlowPane();
        alphabetDisplay.setPrefWidth(200);
        displayAlphabet(alphabetDisplay);




        primaryStage.setScene(scene);
        primaryStage.show();


        scene.setOnKeyPressed(e ->{
            if (!isLetterPressed(e)) { return; }
            else {
                char guess = e.getText().charAt(0);
                if (alphabet.indexOf(guess) == -1) {
                    return;
                } else if (guessesLeft > 0) {
                    if (wordString.indexOf(guess) == -1) {
                        draw();
                        guessesLeft--;
                    } else {
                        for (HangmanTile tile : wordTiles) {
                            if (tile.letter == guess) {
                                tile.text.setText("" + guess);
                                lettersFound++;
                            }
                            if (isGameOver()) {
                                guessesLeft = 0;
                            }
                        }

                    }
                    alphabet.set(alphabet.indexOf(guess), ' ');
                    displayAlphabet(alphabetDisplay);

                }
            }
        });
    }

    public void displayAlphabet(FlowPane alphabetDisplay){
        alphabetDisplay.setHgap(10);
        alphabetDisplay.setAlignment(Pos.CENTER);
        alphabetDisplay.getChildren().clear();
        for (int i = 0; i < 26; i++) {
            HangmanTile tile = new HangmanTile(alphabet.get(i));
            alphabetTiles.add(tile);
            tile.text.setText(alphabet.get(i).toString());
            alphabetDisplay.getChildren().add(tile);
        }
        pane.setRight(alphabetDisplay);
    }


    public boolean isGameOver(){
        return (lettersFound == wordTiles.size());
    }

    public void draw(){
        switch (guessesLeft) {
            case 1:
                drawLeg("right");
                System.out.print("You Lose");
            case 2:
                drawLeg("left");
                break;
            case 3:
                drawArm("right");
                break;
            case 4:
                drawArm("left");
                break;
            case 5:
                drawBody();
                break;
            case 6:
                drawHead();
                break;
        }
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
    }

    public boolean isLetterPressed(KeyEvent e){
        switch (e.getCode()) {
            case A: return true;
            case B: return true;
            case C: return true;
            case D: return true;
            case E: return true;
            case F: return true;
            case G: return true;
            case H: return true;
            case I: return true;
            case J: return true;
            case K: return true;
            case L: return true;
            case M: return true;
            case N: return true;
            case O: return true;
            case P: return true;
            case Q: return true;
            case R: return true;
            case S: return true;
            case T: return true;
            case U: return true;
            case V: return true;
            case W: return true;
            case X: return true;
            case Y: return true;
            case Z: return true;
        }
        return false;
    }
    public static void main(String[] args) {
        launch(args);
    }

}

