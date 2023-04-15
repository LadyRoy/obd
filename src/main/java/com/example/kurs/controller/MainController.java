package com.example.kurs.controller;

import com.example.kurs.helper.SceneHelper;
import com.example.kurs.loader.FxmlPageLoader;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("main.fxml")
public class MainController {
    private final FxmlPageLoader fxmlPageLoader;

    @Autowired
    public MainController(FxmlPageLoader fxmlPageLoader) {
        this.fxmlPageLoader = fxmlPageLoader;
    }

    public void onGoToAuthorPage(ActionEvent event) {
        SceneHelper.switchScene(event, fxmlPageLoader.loadFxmlFile(AuthorController.class));
    }

    public void onGoToPublishingPage(ActionEvent event) {
        SceneHelper.switchScene(event, fxmlPageLoader.loadFxmlFile(PublishingHouseController.class));
    }

    public void onGoToReaderPage(ActionEvent event) {
        SceneHelper.switchScene(event, fxmlPageLoader.loadFxmlFile(ReaderController.class));
    }

    public void onGoToBookPage(ActionEvent event) {
        SceneHelper.switchScene(event, fxmlPageLoader.loadFxmlFile(BookController.class));
    }
}
