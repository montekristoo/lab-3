package org.example.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("file:src/main/resources/problem.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);

        VBox rootVBox = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        VBox xBox = createBorderedVBox(100, 100);
        gridPane.add(xBox, 0, 0);
        Label label = new Label("X = ");
        xBox.setAlignment(javafx.geometry.Pos.CENTER);
        xBox.getChildren().add(label);

        HBox hbox1 = createBorderedHBox(200);
        hbox1.setAlignment(javafx.geometry.Pos.CENTER);
        TextField textField = new TextField();
        hbox1.getChildren().add(textField);
        gridPane.add(hbox1, 1, 0);

        VBox vbox2 = createBorderedVBox(100, 100);
        gridPane.add(vbox2, 0, 1);
        Label label2 = new Label("Y = ");
        vbox2.setAlignment(javafx.geometry.Pos.CENTER);
        vbox2.getChildren().add(label2);

        HBox hbox2 = createBorderedHBox(100);
        hbox2.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.add(hbox2, 1, 1);
        TextField textField2 = new TextField();
        hbox2.getChildren().add(textField2);

        VBox vbox3 = createBorderedVBox(100, 100);
        gridPane.add(vbox3, 0, 2);
        Label label3 = new Label("B = ");
        vbox3.setAlignment(javafx.geometry.Pos.CENTER);
        vbox3.getChildren().add(label3);

        HBox hbox3 = createBorderedHBox(100);
        hbox3.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.add(hbox3, 1, 2);
        TextField textField3 = new TextField();
        hbox3.getChildren().add(textField3);
        HBox first = new HBox();
        first.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
        first.getChildren().add(gridPane);
        first.getChildren().add(imageView);


        Label third = new Label("Result = ");
        third.setPrefSize(600, 100);
        third.setAlignment(javafx.geometry.Pos.CENTER);

        HBox second = new HBox();
        second.setSpacing(100);
        second.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
        Button calculateBtn = new Button("Calculate");

        calculateBtn.setOnAction(e -> {
            double x = Double.parseDouble(textField.getText());
            double a = Double.parseDouble(textField2.getText());
            double b = Double.parseDouble(textField3.getText());

            if (a == 0 && b == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("A and B cannot be 0 at the same time");
                alert.showAndWait();
                return;
            }

            double result;

            if (x <= 7) {
                result = x + 4 * Math.pow(a, 2) + Math.pow(b, 2);
            } else {
                result = x * Math.pow(a + b, 2);
            }


            third.setText("Result = " + result);
        });

        Button clearBtn = new Button("Clear");

        clearBtn.setOnAction(e -> {
            textField.clear();
            textField2.clear();
            textField3.clear();
            third.setText("Result = ");
        });
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> primaryStage.close());
        second.getChildren().add(calculateBtn);
        second.getChildren().add(clearBtn);
        second.getChildren().add(exitBtn);
        rootVBox.getChildren().add(first);
        rootVBox.getChildren().add(second);
        rootVBox.getChildren().add(third);

        Scene scene = new Scene(rootVBox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Layout Example");
        primaryStage.show();
    }

    private VBox createBorderedVBox(int width, int height) {
        VBox box = new VBox();
        box.setPrefSize(width, height);

        return box;
    }

    private HBox createBorderedHBox(double width) {
        HBox box = new HBox();
        box.setPrefSize(width, 100);
        return box;
    }

    public static void main(String[] args) {
        launch();
    }
}