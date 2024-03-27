package org.example.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ThirdApp extends Application {
    private static final int ROWS = 5;
    private static final int COLS = 6;

    private static final int[][] MATRIX = new int[ROWS][COLS];

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        TextArea area = new TextArea();
        area.setPrefSize(400, 310);
        Button uploadBtn = new Button("Upload data from the file");
        uploadBtn.setPrefSize(400, 30);

        uploadBtn.setOnAction(event -> populateMatrix());
        Button processBtn = new Button("Process data");
        processBtn.setOnAction(actionEvent -> {
            processMatrix();
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    area.appendText(MATRIX[i][j] + " ");
                }
                area.appendText("\n");
            }
        });

        processBtn.setPrefSize(400, 30);
        Button saveBtn = new Button("Save data");
        saveBtn.setPrefSize(400, 30);
        saveBtn.setOnAction(actionEvent -> saveMatrix());

        root.getChildren().add(area);
        root.getChildren().add(uploadBtn);
        root.getChildren().add(processBtn);
        root.getChildren().add(saveBtn);

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Third app");
        stage.setScene(scene);
        stage.show();
    }

    private void saveMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                sb.append(MATRIX[i][j]).append(" ");
            }
            sb.append("\n");
        }

        try {
            Files.write(Path.of("src/main/resources/result.txt"), sb.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processMatrix() {
        int counter = 0;


        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (j == 1 && MATRIX[i][j] == 1) counter++;
            }
        }

        if (counter == 2) processing();

    }

    private static void processing() {
        int max = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (i == 0 && MATRIX[i][j] > max) max = MATRIX[i][j];
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (i == 0 && MATRIX[i][j] == max) MATRIX[i][j] /= 2;
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (MATRIX[i][j] == 1) MATRIX[i][j] = 0;
            }
        }
    }

    private static void populateMatrix() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("src/main/resources/data.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            for (int j = 0; j < split.length; j++) {
                int value = Integer.parseInt(split[j]);
                MATRIX[i][j] = value;
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}