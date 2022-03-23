package com.example.idpaproject1;

import com.example.idpaproject1.other.JDOMParser;
import com.example.idpaproject1.other.NiermanJagadishAlgorithm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox layout = new VBox();
        VBox layout2 = new VBox();
        HBox layout_filepickers = new HBox();
        HBox layout_fileTextAreas = new HBox();
        layout_filepickers.setAlignment(Pos.CENTER);
        layout_fileTextAreas.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);
        layout_filepickers.setSpacing(20);
        layout_fileTextAreas.setSpacing(20);
        layout2.setSpacing(20);


        HBox layout_parsedfiles = new HBox();
        VBox layout_parsed = new VBox();
        layout_parsed.setAlignment(Pos.CENTER);
        layout_parsed.setSpacing(20);


        VBox layout_TED = new VBox();
        HBox editDistanceBox = new HBox();
        HBox editScriptBox = new HBox();
        layout_TED.setAlignment(Pos.CENTER);


        layout_TED.setSpacing(20);
        editDistanceBox.setSpacing(40);
        editScriptBox.setSpacing(40);
        editDistanceBox.setAlignment(Pos.CENTER);
        editScriptBox.setAlignment(Pos.CENTER);

        Label label_editDistance = new Label("Edit Distance: ");
        TextField area_ED = new TextField();
        Label label_editScript = new Label("Edit Script: ");
        TextArea area_ES = new TextArea();
        editDistanceBox.getChildren().addAll(label_editDistance,area_ED );
        editScriptBox.getChildren().addAll(label_editScript, area_ES);
        Button button_goBack = new Button("Post-process and Go Back to choosing other files");

        Label nandj_done = new Label("After Running N&J...");
        nandj_done.setStyle("-fx-font: 40 helvetica; -fx-text-fill: crimson;");
        layout_TED.getChildren().addAll(nandj_done, editDistanceBox, editScriptBox, button_goBack);

        NiermanJagadishAlgorithm nandj = new NiermanJagadishAlgorithm();




        Scene scene_welcome = new Scene(layout, 700, 700);
        Scene scene_choosefile = new Scene(layout2, 700, 700);
        Scene scene_parsing = new Scene(layout_parsed, 700, 700);
        Scene scene_TED = new Scene(layout_TED, 700, 700);

        Label label1 = new Label("XML Processing tool");
        Label label3 = new Label("Note: All XML files have been preprocessed.");

        label1.setStyle("-fx-font: 50 helvetica; -fx-text-fill: crimson;");
        label3.setStyle("-fx-font: 30 helvetica; -fx-text-fill:  black;");

        Label label2 = new Label("Please choose two files");
        label2.setStyle("-fx-font: 30 helvetica; -fx-text-fill:  black;");

        Button button = new Button("Begin");
        button.setMaxWidth(100);

        Button button_firstfile = new Button("Choose 1st file");
        Button button_secondfile = new Button("Choose 2nd file");

        TextField firstFile = new TextField();
        TextField secondFile = new TextField();

        TextField firstParsedFile = new TextField();
        TextField secondParsedFile = new TextField();




        FileChooser fileChooser = new FileChooser();
        FileChooser fileChooser_2 = new FileChooser();

        fileChooser.setInitialDirectory(new File("C:\\Users\\user\\Desktop\\Spring 2022\\IDPA - Project 1\\src"));
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("XML files", "*.xml"));

        fileChooser_2.setInitialDirectory(new File("C:\\Users\\user\\Desktop\\Spring 2022\\IDPA - Project 1\\src"));
        fileChooser_2.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("XML files", "*.xml"));

        button_firstfile.setOnAction(
                e -> {
                    File selectedFile = fileChooser.showOpenDialog(primaryStage);

                    if (selectedFile != null) {

                        firstFile.setText(selectedFile.getAbsolutePath()
                                );
                    }

                }
        );

        button_secondfile.setOnAction(
                e -> {
                    File selectedFile = fileChooser_2.showOpenDialog(primaryStage);

                    if (selectedFile != null) {

                        secondFile.setText(selectedFile.getAbsolutePath()
                        );
                    }

                }
        );



        button.setOnAction(e -> primaryStage.setScene(scene_choosefile));

        Button button_proceed = new Button("Proceed To Parsing");
        Button button_calculate = new Button ("Calculed Edit Distance and Edit Script");

        button_calculate.setAlignment(Pos.CENTER);

        TextField text = new TextField();
        text.setMaxWidth(100);

        Label doneParsing = new Label();
        doneParsing.setStyle("-fx-font: 40 helvetica; -fx-text-fill: crimson;");

        layout.getChildren().addAll(label1, label3, button);
        layout_filepickers.getChildren().addAll(button_firstfile, button_secondfile);
        layout_fileTextAreas.getChildren().addAll(firstFile, secondFile);
        layout2.getChildren().addAll(label2, layout_filepickers, layout_fileTextAreas, button_proceed);
        layout_parsedfiles.getChildren().addAll(firstParsedFile, secondParsedFile);
        layout_parsed.getChildren().addAll(doneParsing, button_calculate);



        button_proceed.setOnAction(


                actionEvent -> {




                    primaryStage.setScene(scene_parsing);
                    doneParsing.setText("Done Parsing - Check console");
                    nandj.parseXML(firstFile.getText());
                    nandj.parseXML(secondFile.getText());

                }
        );

        button_calculate.setOnAction(
                actionEvent -> {

                    Object[] TED_arr = nandj.NiermanJagadishExecute(nandj.parseXML(firstFile.getText()).getDocumentElement(), nandj.parseXML(secondFile.getText()).getDocumentElement(), nandj.parseXML(firstFile.getText()), nandj.parseXML(secondFile.getText()));

                    primaryStage.setScene(scene_TED);
                    doneParsing.setText("Done Parsing - Check console");

                   area_ED.setText(String.valueOf(TED_arr[0]));
                   area_ES.setText(String.valueOf(TED_arr[1]));

                }
        );

        button_goBack.setOnAction(actionEvent -> {

            primaryStage.setScene(scene_welcome);

            try (FileOutputStream output =
                         new FileOutputStream("src\\out-dom.xml")) {
                JDOMParser.writeXml(nandj.parseXML(firstFile.getText()), output);
            } catch (IOException | TransformerException e) {
                e.printStackTrace();
            }
            try (FileOutputStream output =
                         new FileOutputStream("src\\out-dom1.xml")) {
                JDOMParser.writeXml(nandj.parseXML(secondFile.getText()), output);
            } catch (IOException | TransformerException e) {
                e.printStackTrace();
            }



































































































































































































                }
        );







        primaryStage.setTitle("XML Processing Tool");
        primaryStage.setScene(scene_welcome);
        primaryStage.show();
    }

}