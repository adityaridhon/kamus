package com.example.sumpah;

import javafx.scene.image.Image;

public class Node {
    private final String key;       // Kata asli
    private final String value;     // Terjemahan
    private Node left;
    private Node right;
    private boolean isRed;
    private String gimmickImagePath; // Path gambar gimik

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.gimmickImagePath = null;
    }

    public Node(String key, String value, String gimmickImagePath) {
        this.key = key;
        this.value = value;
        this.gimmickImagePath = gimmickImagePath;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean isRed) {
        this.isRed = isRed;
    }

    public Image getGimmickImage() {
        if (gimmickImagePath != null && !gimmickImagePath.isEmpty()) {
            return new Image(gimmickImagePath);
        }
        return null;
    }
}
