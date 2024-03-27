package org.example.test;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondApp extends Application {

    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        VBox leftSide = new VBox();
        VBox rightSide = new VBox();

        Label rawText = new Label("Raw text: ");
        rawText.alignmentProperty().setValue(Pos.CENTER);
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(textArea);
        textArea.setPrefHeight(600);
        textArea.setPrefWidth(400);

        leftSide.getChildren().add(rawText);
        leftSide.getChildren().add(scrollPane);

        Label label = new Label("Words / sequence of words");
        label.alignmentProperty().setValue(Pos.CENTER);
        TextField input = new TextField();

        VBox buttonsPanel = new VBox();
        input.setPrefSize(400, 500);
        Button replaceButton = new Button("Replace text");
        replaceButton.setPrefSize(400, 150);
        Button addTextButton = new Button("Add text");
        addTextButton.setPrefSize(400, 150);

        onActionReplaceButton(replaceButton, textArea, input);
        onActionAddButton(addTextButton, textArea, input);

        buttonsPanel.getChildren().add(replaceButton);
        buttonsPanel.getChildren().add(addTextButton);
        buttonsPanel.alignmentProperty().setValue(Pos.CENTER);

        rightSide.getChildren().add(label);
        rightSide.getChildren().add(input);
        rightSide.getChildren().add(buttonsPanel);

        root.getChildren().add(leftSide);
        root.getChildren().add(rightSide);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Second app");
        stage.setScene(scene);
        stage.show();
    }

    private static void onActionReplaceButton(Button replaceButton, TextArea textArea, TextField input) {
        replaceButton.setOnAction(action -> {
            String raw = textArea.getText();
            String replace = input.getText();
            String replaced = raw.replace(replace, "$");
            textArea.setText(replaced);
        });
    }

    private static void onActionAddButton(Button addTextButton, TextArea textArea, TextField input) {
        addTextButton.setOnAction(actionEvent ->  {
            String currentText = textArea.getText();
            String textToAppend = input.getText();
            String appended = currentText.concat(textToAppend);
            textArea.setText(appended);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}