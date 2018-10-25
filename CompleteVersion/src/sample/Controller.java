package sample;

import Translate.Translator;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Alert alert;
    private Dialog<Pair<String, String>> dialog;
    private TextField wordTarget;
    private TextArea wordExplain;
    private Label wordTargetLabel;
    private Label wordExplainLabel;
    private Alert deletionAlert;
    private File readme = new File("readme.txt");
    private boolean isEnglish;
    @FXML
    private Menu fileMenu;
    @FXML
    private Menu viewMenu;
    @FXML
    private Menu changeLangMenu;
    @FXML
    private Menu aboutMenu;
    @FXML
    private MenuItem ETFMI;
    @FXML
    private MenuItem ANWMI;
    @FXML
    private MenuItem DSWMI;
    @FXML
    private MenuItem exitMI;
    @FXML
    private MenuItem enMI;
    @FXML
    private MenuItem viMI;
    @FXML
    private MenuItem aboutMI;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Label enLabel1;
    @FXML
    private Label viLabel1;
    @FXML
    private Button readButton1;
    @FXML
    private TextField wordSearchBar;
    @FXML
    private ListView<String> resultList;
    @FXML
    private TextArea resultWord;
    @FXML
    private TextArea enPara;
    @FXML
    private TextArea viPara;
    @FXML
    private Label enLabel2;
    @FXML
    private Label viLabel2;
    @FXML
    private Button readButton2;
    @FXML
    private Button translateButton;

    public static ObservableList<String> list;

    public Controller() {
        isEnglish = true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        resultList.setItems(list);
        alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/icon/application-icon.png"));
        dialog = new Dialog<>();
        dialog.setTitle("Add new word");
        dialog.setHeaderText("New word:");
        stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/icon/application-icon.png"));

        ButtonType confirmButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        wordTarget = new TextField();
        wordTarget.setPromptText("Word Target...");
        wordExplain = new TextArea();
        wordExplain.setPromptText("Word Explain...");

        wordTargetLabel = new Label("Word Target:");
        wordExplainLabel = new Label("Word Explain:");

        grid.add(wordTargetLabel, 0, 0);
        grid.add(wordTarget, 1, 0);
        grid.add(wordExplainLabel, 0, 1);
        grid.add(wordExplain, 1, 1);

        Node _confirmButton = dialog.getDialogPane().lookupButton(confirmButton);
        _confirmButton.setDisable(true);

        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            _confirmButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButton) {
                return new Pair<>(wordTarget.getText(), wordExplain.getText());
            }
            else return null;
        });

        deletionAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deletionAlert.setTitle("Confirmation");
        deletionAlert.setContentText("Do you really want to delete the selected word?");

        stage = (Stage) deletionAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/icon/application-icon.png"));

        ButtonType buttonYes = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonNo = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ping www.google.com");
            int x = process.waitFor();
            if (x != 0) {
                translateButton.setDisable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alertOn(String alertContent) {
        alert.setContentText(alertContent);
        alert.show();
    }

    private void alertOff(String alertContent) {
        alert.hide();
        alert.setContentText(alertContent);
        alert.getButtonTypes().add(new ButtonType("OK"));
        alert.showAndWait();
        alert.getButtonTypes().clear();
        alert.setTitle("");
        alert.setContentText("");
    }

    public void translateParagraph(ActionEvent event) {
        String inputParagraph = enPara.getText();
        if (inputParagraph != null) {
            if (!inputParagraph.isEmpty()) {
                alertOn(isEnglish?"Translating...":"Đang dịch...");
                String resultParagrap = "";
                try {
                    resultParagrap = Translator.translate("en", "vi", inputParagraph);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viPara.setText(resultParagrap);
                alertOff(isEnglish?"Translated !!":"Đã dịch xong !!");
            }
        }
    }

    public void readParagraph(ActionEvent event) {
        String text = enPara.getText();
        if (text != null) {
            if (!text.isEmpty()) {
                alertOn(isEnglish?"Reading...":"Đang đọc...");
                System.setProperty("mbrola.base", "lib\\mbrola");
                Voice voice = VoiceManager.getInstance().getVoice("mbrola_us1");
                voice.allocate();
                voice.speak(text);
                voice.deallocate();
                alertOff(isEnglish?"Read !!":"Đã đọc xong !!");
            }
        }
    }

    public void updateResultList(KeyEvent event) {
        list.clear();
        resultWord.clear();
        String searchCharacters = wordSearchBar.getText();
        if (searchCharacters != null) {
            if (!searchCharacters.isEmpty()) {
                Main.wordTree.approximateSearch(new Word(searchCharacters, ""));
            }
        }
    }

    public void updateResultText(MouseEvent event) {
        String word = resultList.getSelectionModel().getSelectedItem();
        if (word != null) {
            if (!word.isEmpty()) {
                resultWord.setText(Main.wordTree.search(new Word(word, "")).getWord_explain());
            }
        }
    }

    public void readWord(ActionEvent event) {
        String word = resultList.getSelectionModel().getSelectedItem();
        if (word != null) {
            if (!word.isEmpty()) {
                System.setProperty("mbrola.base", "lib\\mbrola");
                Voice voice = VoiceManager.getInstance().getVoice("mbrola_us1");
                voice.allocate();
                voice.speak(word);
                voice.deallocate();
            }
        }
    }

    public void exitProgram(ActionEvent event) {
        Main.window.close();
    }

    public void openReadme(ActionEvent event) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (readme.exists()) {
                try {
                    desktop.open(readme);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void changeLangToVie(ActionEvent e) {
        if (isEnglish) {
            fileMenu.setText("Tệp");
            ANWMI.setText("Thêm từ mới...");
            ETFMI.setText("Xuất ra tệp...");
            exitMI.setText("Thoát (Alt+F4)");
            viewMenu.setText("Xem");
            changeLangMenu.setText("Thay đổi ngôn ngữ");
            enMI.setText("Tiếng Anh");
            viMI.setText("Tiếng Việt");
            aboutMenu.setText("Giới thiệu");
            aboutMI.setText("Giới thiệu...");
            tab1.setText("Dịch từ");
            tab2.setText("Dịch đoạn văn");
            wordSearchBar.setPromptText("Nhập từ...");
            enLabel1.setText("Tiếng Anh:");
            viLabel1.setText("Tiếng Việt:");
            readButton1.setText("Đọc");
            enPara.setPromptText("Nhập đoạn văn...");
            viPara.setPromptText("Kết quả sẽ được hiển thị ở đây.");
            enLabel2.setText("Tiếng Anh:");
            viLabel2.setText("Tiếng Việt:");
            readButton2.setText("Đọc");
            translateButton.setText("Dịch");
            dialog.setTitle("Thêm từ mới");
            dialog.setHeaderText("Từ mới:");
            wordTarget.setPromptText("Từ...");
            wordExplain.setPromptText("Nghĩa...");
            wordTargetLabel.setText("Từ:");
            wordExplainLabel.setText("Nghĩa:");
            deletionAlert.setTitle("Xác nhận");
            deletionAlert.setContentText("Bạn có thực sự muốn xoá từ đã được chọn?");
            DSWMI.setText("Xoá từ được chọn...");
        }
        isEnglish = false;
    }

    public void changeLangToEng(ActionEvent e) {
        if (!isEnglish) {
            fileMenu.setText("File");
            ANWMI.setText("Add new word...");
            ETFMI.setText("Export to file...");
            exitMI.setText("Exit (Alt+F4)");
            viewMenu.setText("View");
            changeLangMenu.setText("Chang Language");
            enMI.setText("English");
            viMI.setText("Vietnamese");
            aboutMenu.setText("About");
            aboutMI.setText("About...");
            tab1.setText("Word Translator");
            tab2.setText("Paragraph Tranlastor");
            wordSearchBar.setPromptText("Input word...");
            enLabel1.setText("English:");
            viLabel1.setText("Vietnamese:");
            readButton1.setText("Read");
            enPara.setPromptText("Input Paragraph...");
            viPara.setPromptText("Result will be show here.");
            enLabel2.setText("English:");
            viLabel2.setText("Vietnamese:");
            readButton2.setText("Read");
            translateButton.setText("Translate");
            dialog.setTitle("Add new word");
            dialog.setHeaderText("New word:");
            wordTarget.setPromptText("Word target...");
            wordExplain.setPromptText("Word explain...");
            wordTargetLabel.setText("Word explain:");
            wordExplainLabel.setText("Word target:");
            deletionAlert.setTitle("Confirmation");
            deletionAlert.setContentText("Do you really want to delete the selected word?");
            DSWMI.setText("Delete selected word...");
        }
        isEnglish = true;
    }

    public void exportToFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        FileChooser.ExtensionFilter txtChooser = new FileChooser.ExtensionFilter("Text Document File", "*.txt");
        fileChooser.getExtensionFilters().add(txtChooser);
        File file = fileChooser.showSaveDialog(Main.window);
        if (file != null) {
            Main.wordTree.exportToFile(file);
        }
    }

    public void addNewWord(ActionEvent event) {
        Optional<Pair<String, String>> newWord = dialog.showAndWait();
        newWord.ifPresent(_newWord -> {
            Main.wordTree.insertWord(new Word(_newWord.getKey(), _newWord.getValue()));
        });
    }

    public void deleteWord(ActionEvent event) {
        String word = resultList.getSelectionModel().getSelectedItem();
        int selectedIndex = resultList.getSelectionModel().getSelectedIndex();
        if (word != null) {
            deletionAlert.setContentText("Delete \"" + word + "\"");
            Optional<ButtonType> result = deletionAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Main.wordTree.deleteWord(new Word(word, ""));
                list.remove(selectedIndex);
            }
        }
    }
}
