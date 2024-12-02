package com.example.sumpah;

import javafx.scene.image.Image;

public class Node {
    private String key;
    private String value;
    private Node left, right;
    private boolean isRed;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.isRed = true; // Default node baru adalah merah
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

    public void setRed(boolean red) {
        isRed = red;
    }

    public static class GimmickNode extends Node {
        private Image gimmickImage;

        public GimmickNode(String key, String value, String imagePath) {
            super(key, value);
            if (imagePath != null && !imagePath.isEmpty()) {
                this.gimmickImage = new Image(imagePath);
            }
        }

        public Image getGimmickImage() {
            return gimmickImage;
        }

        public void setGimmickImage(String imagePath) {
            if (imagePath != null && !imagePath.isEmpty()) {
                this.gimmickImage = new Image(imagePath);
            }
        }
    }
}
