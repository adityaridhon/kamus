package com.example.sumpah;

import javafx.scene.image.Image;

public class RedBlackTree {
    private Node root;

    public void add(String word, String translation, String imagePath) {
        Node newNode = (imagePath != null && !imagePath.isEmpty())
                ? new Node(word, translation, imagePath)
                : new Node(word, translation);

        root = addRecursive(root, newNode);
        root.setRed(false); // Root HITAM
    }

    private Node addRecursive(Node current, Node newNode) {
        if (current == null) {
            return newNode;
        }

        if (newNode.getKey().compareTo(current.getKey()) < 0) {
            current.setLeft(addRecursive(current.getLeft(), newNode));
        } else if (newNode.getKey().compareTo(current.getKey()) > 0) {
            current.setRight(addRecursive(current.getRight(), newNode));
        }
        return current; // Abaikan balancing untuk kesederhanaan
    }

    public String translate(String word) {
        Node node = findNode(word);
        return node != null ? node.getValue() : null;
    }

    public String translateReverse(String word) {
        Node node = findReverseNode(word);
        return node != null ? node.getKey() : null;
    }

    public Image getGimmickImage(String word) {
        Node node = findNode(word);
        return node != null ? node.getGimmickImage() : null;
    }

    private Node findNode(String key) {
        Node current = root;
        while (current != null) {
            if (key.equals(current.getKey())) {
                return current;
            }
            current = key.compareTo(current.getKey()) < 0 ? current.getLeft() : current.getRight();
        }
        return null;
    }

    private Node findReverseNode(String value) {
        Node current = root;
        while (current != null) {
            if (value.equals(current.getValue())) {
                return current;
            }
            current = value.compareTo(current.getValue()) < 0 ? current.getLeft() : current.getRight();
        }
        return null;
    }
}
