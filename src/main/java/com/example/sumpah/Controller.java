package com.example.sumpah;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML
    private TextField searchField;

    @FXML
    private TextArea resultArea;

    @FXML
    private ImageView imageView;

    @FXML
    private MenuButton menuButton;

    private final RedBlackTree tree = new RedBlackTree();
    private boolean isIndoToEng = true;

    @FXML
    public void initialize() {
        MenuItem indoToEng = new MenuItem("Indo → Eng");
        indoToEng.setOnAction(e -> setIndoToEng());

        MenuItem engToInd = new MenuItem("Eng → Indo");
        engToInd.setOnAction(e -> setEngToInd());

        menuButton.getItems().addAll(indoToEng, engToInd);
        //DATA KAMUS
        tree.add("apel", "apple", "D:\\KULIAH\\AKADEMIK\\SEMESTER 3\\STRUKDAT\\kamus\\src\\main\\resources\\assets\\apel.jpg");
        tree.add("matahari", "sun", "D:\\KULIAH\\AKADEMIK\\SEMESTER 3\\STRUKDAT\\kamus\\src\\main\\resources\\assets\\Matahari.jpg");
        tree.add("motor","bike", null);
        tree.add("hewan", "animal", null);
        tree.add("kipas", "fan", null);
        tree.add("kursi", "chair", null);
        tree.add("air", "water", null);
        tree.add("pintu", "door", null);
    }


    @FXML
    public void setIndoToEng() {
        isIndoToEng = true;
        menuButton.setText("Indo → Eng");
        resultArea.clear();
        searchField.clear();
        imageView.setImage(null);
        System.out.println("Mode diatur: Indonesia → Inggris");
    }

    @FXML
    public void setEngToInd() {
        isIndoToEng = false;
        menuButton.setText("Eng → Indo");
        resultArea.clear();
        searchField.clear();
        imageView.setImage(null);
        System.out.println("Mode diatur: Inggris → Indonesia");
    }

    @FXML
    public void searchWord() {
        String word = searchField.getText().trim();
        if (word.isEmpty()) {
            resultArea.setText("Masukkan kata untuk mencari terjemahan.");
            imageView.setImage(null);
            return;
        }

        String translation;
        Image gimmickImage = null;

        if (isIndoToEng) {
            // Translate from Indonesia to English
            translation = tree.translate(word);
            gimmickImage = tree.getGimmickImage(word);  // Get gimmick image
        } else {
            // Translate from English to Indonesia
            translation = tree.translateReverse(word);  // Reverse translation
            if (translation != null) {
                gimmickImage = tree.getGimmickImage(translation);
            }
        }

        if (translation == null) {
            resultArea.setText("Kata tidak ditemukan.");
            imageView.setImage(null);
        } else {
            resultArea.setText(translation);

            if (gimmickImage != null) {
                imageView.setImage(gimmickImage);
            } else {
                imageView.setImage(null);
            }
        }
    }

}

