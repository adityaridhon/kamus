package com.example.sumpah;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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
    private CheckBox indToEngCheckBox;

    @FXML
    private CheckBox engToIndCheckBox;

    @FXML
    private ImageView imageView; // Tambahkan ini pada FXML untuk menampilkan gambar gimik

    private final RedBlackTree tree = new RedBlackTree();
    private boolean isIndoToEng = true; // Default penerjemahan Indonesia → Inggris

    public void initialize() {
        tree.add("apel", "apple", "D:\\KULIAH\\kamus\\src\\main\\resources\\assets\\apel.jpg");
        tree.add("matahari", "sun", "D:\\KULIAH\\kamus\\src\\main\\resources\\assets\\Matahari.jpg");
        tree.add("hewan", "animal", null);
        tree.add("kipas", "fan", null);
        tree.add("kursi", "chair", null);
        tree.add("air", "water", null);
        tree.add("pintu", "door", null);
        tree.add("pakaian", "clothes", null);
        tree.add("sepatu", "shoes", null);
        tree.add("motor", "bike", null);

        // Atur status awal checkbox
        indToEngCheckBox.setSelected(true); // Default Indonesia → Inggris
        engToIndCheckBox.setSelected(false);
    }

    @FXML
    public void setIndoToEng() {
        if (indToEngCheckBox.isSelected()) {
            isIndoToEng = true;
            engToIndCheckBox.setSelected(false); // Pastikan hanya satu checkbox yang aktif
            resultArea.setText("Penerjemahan: Indonesia → Inggris");
        } else {
            // Cegah kedua checkbox tidak aktif bersamaan
            indToEngCheckBox.setSelected(true);
        }
    }

    @FXML
    public void setEngToInd() {
        if (engToIndCheckBox.isSelected()) {
            isIndoToEng = false;
            indToEngCheckBox.setSelected(false); // Pastikan hanya satu checkbox yang aktif
            resultArea.setText("Penerjemahan: Inggris → Indonesia");
        } else {
            // Cegah kedua checkbox tidak aktif bersamaan
            engToIndCheckBox.setSelected(true);
        }
    }

    @FXML
    public void searchWord() {
        String word = searchField.getText().trim();

        if (word.isEmpty()) {
            resultArea.setText("Harap masukkan kata yang ingin diterjemahkan.");
            imageView.setImage(null); // Kosongkan gambar
            return;
        }

        // Terjemahkan kata berdasarkan pilihan arah
        String result = (isIndoToEng) ? tree.translate(word) : tree.translateReverse(word);

        if (result != null) {
            resultArea.setText(result);

            // Periksa apakah ada gambar gimik untuk kata ini
            Image gimmickImage = tree.getGimmickImage(word);
            if (gimmickImage != null) {
                imageView.setImage(gimmickImage); // Tampilkan gambar jika ada
            } else {
                imageView.setImage(null); // Kosongkan gambar jika tidak ada
            }
        } else {
            resultArea.setText("Kata tidak ditemukan dalam kamus!");
            imageView.setImage(null); // Kosongkan gambar
        }
    }
}
