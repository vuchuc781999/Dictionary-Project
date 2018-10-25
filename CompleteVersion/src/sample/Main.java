package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
    public static Stage window;
    public static AVLTree wordTree;

    static {
        window = null;
        wordTree = new AVLTree();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Dictionary");
        Scene scene = new Scene(root, 790, 590);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/icon/application-icon.png"));
        primaryStage.show();
        window = primaryStage;
        DBConnection.connect();
        String sql = "SELECT * from tbl_edict";
        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                wordTree.insertWord(new Word(resultSet.getString("word"), resultSet.getString("detail")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
        DBConnection.disconnect();
    }
}
