package com.example.kurs.helper;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class SceneHelper {

    private SceneHelper() {
    }

    public static void switchScene(Event event, Parent root) {
        var stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        var scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
