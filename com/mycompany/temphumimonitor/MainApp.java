package com.mycompany.temphumimonitor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;
import utils.AnalyseProtocol;
import utils.ClientNetTransmission;
import utils.MaxMinFunctions;
import utils.ServerNetTransmission;
import utils.UDPServer;


public class MainApp extends Application {
    /* 全屏的尺寸 */
    double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    
    

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
//        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
               
        
        stage.setTitle("南京基地");
        stage.setScene(scene);
        stage.setWidth(SCREEN_WIDTH);
        stage.setHeight(SCREEN_HEIGHT);
        stage.show();
        
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
