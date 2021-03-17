package by.bsu.plesko.controller;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import by.bsu.plesko.app.Main;
import by.bsu.plesko.util.FileMetadata;
import by.bsu.plesko.entity.Info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button idButton;

    @FXML
    private TableView<Info> table;

    @FXML
    private TableColumn<Info, String> fileNameColumn;

    @FXML
    private TableColumn<Info, String> imageSizeColumn;

    @FXML
    private TableColumn<Info, String> resolutionColumn;

    @FXML
    private TableColumn<Info, String> colorDepthColumn;

    @FXML
    private TableColumn<Info, String> compressionColumn;

    @FXML
    private Button idDirButton;

    @FXML
    void initialize() {
        idButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All files", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("GIF", "*.gif"),
                    new FileChooser.ExtensionFilter("TIF", "*.tif"),
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("PCX", "*.pcx")
            );
            List<File> file = fileChooser.showOpenMultipleDialog(Main.primarySt);
            if (file != null) {
                table.setItems(getObsListInfo(file));
                fileNameColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("fileName"));
                imageSizeColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("imageSize"));
                resolutionColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("resolution"));
                colorDepthColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("colorDepth"));
                compressionColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("compression"));
            }
        });

        idDirButton.setOnAction(event -> {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File file = fileChooser.showDialog(Main.primarySt);
            if (file != null) {
                table.setItems(getObsListInfo(Arrays.asList(file.listFiles())));
                fileNameColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("fileName"));
                imageSizeColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("imageSize"));
                resolutionColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("resolution"));
                colorDepthColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("colorDepth"));
                compressionColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("compression"));
            }
        });
    }

    private ObservableList<Info> obs = FXCollections.observableArrayList();
    private ObservableList<Info> getObsListInfo(List<File> file) {
        file.forEach(x -> {
            FileMetadata fileMetadata = new FileMetadata(x);
            obs.add(new Info(fileMetadata.getFileName(), fileMetadata.getImageSize(), fileMetadata.getResolution(), fileMetadata.getColorDepth(), fileMetadata.getCompression()));
        });
        return obs;
    }
}
