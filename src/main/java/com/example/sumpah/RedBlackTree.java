package com.example.sumpah;

import javafx.scene.image.Image;
import java.util.Random;

public class RedBlackTree {
    private Node root;

    // Menambahkan kata beserta terjemahan dan opsional gimmick image
    public void add(String word, String translation, String imagePath) {
        Node newNode;
        if (imagePath != null && !imagePath.isEmpty()) {
            newNode = new Node.GimmickNode(word, translation, imagePath);
        } else {
            newNode = new Node(word, translation);
        }

        root = addRecursive(root, newNode);
        root.setRed(false); // Root harus selalu hitam
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
        // Logika keseimbangan red-black tree diabaikan untuk kesederhanaan
        return current;
    }

    // Cari terjemahan berdasarkan kata (misalnya Indonesia → Inggris)
    public String translate(String word) {
        Node node = findNode(word);
        if (node != null) {
            return node.getValue();
        }
        return null;
    }

    // Cari terjemahan balik (misalnya Inggris → Indonesia)
    public String translateReverse(String word) {
        Node node = findReverseNode(word);
        if (node != null) {
            return node.getKey();
        }
        return null;
    }

    // Mencari Node berdasarkan kunci
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

    // Mencari Node berdasarkan nilai
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

    // Ambil gambar gimik jika ada
    public Image getGimmickImage(String word) {
        Node node = findNode(word);
        if (node instanceof Node.GimmickNode gimmickNode) {
            return gimmickNode.getGimmickImage();
        }
        return null; // Jika tidak ada gimik
    }

    // Mendapatkan kata acak (contoh untuk "Word of the Day")
    public String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(countNodes(root)); // Menghitung jumlah total node
        return getNodeAtIndex(root, new int[]{0}, randomIndex).getKey(); // Traverse in-order untuk node tertentu
    }

    private int countNodes(Node current) {
        if (current == null) {
            return 0;
        }
        return 1 + countNodes(current.getLeft()) + countNodes(current.getRight());
    }

    private Node getNodeAtIndex(Node current, int[] currentIndex, int targetIndex) {
        if (current == null) {
            return null;
        }

        // Traverse kiri
        Node leftResult = getNodeAtIndex(current.getLeft(), currentIndex, targetIndex);
        if (leftResult != null) {
            return leftResult;
        }

        // Cek node saat ini
        if (currentIndex[0] == targetIndex) {
            return current;
        }
        currentIndex[0]++;

        // Traverse kanan
        return getNodeAtIndex(current.getRight(), currentIndex, targetIndex);
    }
}
