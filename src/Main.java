import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

    Stage window;
    Boolean valFlag;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Screenboxer");

        //GridPane 10px padding
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label aLabel = new Label("A");
        GridPane.setConstraints(aLabel, 0, 0);

        Label bLabel = new Label("B");
        GridPane.setConstraints(bLabel, 1, 0);

        Label cLabel = new Label("C");
        GridPane.setConstraints(cLabel, 0, 2);

        Label dLabel = new Label("D");
        GridPane.setConstraints(dLabel, 1, 2);

        TextField aField = new TextField();
        aField.setPromptText("A");
        GridPane.setConstraints(aField, 0, 1);

        TextField bField = new TextField();
        bField.setPromptText("B");
        GridPane.setConstraints(bField, 1, 1);

        TextField cField = new TextField();
        cField.setPromptText("C");
        GridPane.setConstraints(cField, 0, 3);

        TextField dField = new TextField();
        dField.setPromptText("D");
        GridPane.setConstraints(dField, 1, 3);

        Label resultLabel = new Label("Calculation:");
        GridPane.setConstraints(resultLabel, 0, 5);

        Label answerLabel = new Label();
        answerLabel.setText("A : B = C : D");
        GridPane.setConstraints(answerLabel, 1, 5);

        Button calcButton = new Button("Calculate");
        calcButton.setOnAction(e -> {
            valFlag = isInt(aField, aField.getText());
            valFlag = isInt(bField, bField.getText());
            valFlag = isInt(cField, cField.getText());
            valFlag = isInt(dField, dField.getText());

            if(valFlag){
                double result = calculateRatio(aField.getText(), bField.getText(), cField.getText(), dField.getText());
                if((cField.getText().length() > 0) && (dField.getText().length() == 0))
                    answerLabel.setText(aField.getText() + " : " + bField.getText() +" = " + cField.getText() + " : " + result);
                else if ((dField.getText().length() > 0) && (cField.getText().length() == 0))
                    answerLabel.setText(aField.getText() + " : " + bField.getText() +" = " + result + " : " + dField.getText());
            }
            else
                answerLabel.setText("A : B = C : D");
        });
        GridPane.setConstraints(calcButton, 0, 4);

        grid.getChildren().addAll( aLabel, bLabel, cLabel, dLabel, aField, bField, cField, dField, resultLabel, answerLabel, calcButton);

        Scene scene = new Scene(grid, 345,200);
        scene.getStylesheets().add("PumpkinDot.css");
        Image icon = new Image("file:logo.png");
        window.getIcons().add(icon);

        window.setScene(scene);
        window.show();

    }

    public static double calculateRatio(String aInput, String bInput, String cInput, String dInput){

        Double aDub = Double.parseDouble(aInput);
        Double bDub = Double.parseDouble(bInput);
        Double multiplier, result;

        result = 0.0;

        if(cInput.length() == 0){
            Double dDub = Double.parseDouble(dInput);
            multiplier = dDub / bDub;
            result = aDub * multiplier;

        }

        else if(dInput.length() == 0) {
            Double cDub = Double.parseDouble(cInput);
            multiplier = cDub / aDub;
            result = bDub * multiplier;
        }

        return result;
    }

    private boolean isInt(TextField input, String message) {
        if(message.length() > 0){
            try {
                int number = Integer.parseInt(input.getText());
                System.out.println("Number entered is: " + number);
                return true;
            } catch (NumberFormatException e) {

                System.out.println("Error: " + message + " is not a number");
                AlertBox.display("Input Error","Error: \"" + message + "\" is not an Integer");
                return false;
            }
        }
        return true;
    }


}
