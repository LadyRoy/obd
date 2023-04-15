package com.example.kurs;

import com.example.kurs.controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder()
                .sources(Launcher.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) {
        var fxWeaver = context.getBean(FxWeaver.class);
        var root = (Parent) fxWeaver.loadView(MainController.class);
        var scene = new Scene(root);

        stage.setTitle("Главная страница");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }
}
