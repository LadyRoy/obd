package com.example.kurs.helper;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;

import java.time.LocalDate;

public final class DirectoryChooserHelper {

    public static String getAbsolutePath(ActionEvent event, String pdfFileName) {
        var directoryChooser = new DirectoryChooser();
        var selectedDirectory = directoryChooser.showDialog(((Node) event.getSource()).getScene().getWindow());

        return selectedDirectory.getAbsolutePath() + "\\" + pdfFileName + "-" + LocalDate.now() + ".pdf";
    }
}
