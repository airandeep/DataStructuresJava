package com.airan;

import com.airan.data.AvlTree;
import com.airan.data.BinarySearchTree;

public class Main {

    public static void main(String[] args) {
        AvlTree<Integer> avlTree = new AvlTree<>();
        for(int i = 1;i <= 100000;i++){
            avlTree.insert(i);
        }
        avlTree.printTree();
//        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
//        for(int i = 1;i <= 100000;i++){
//            binarySearchTree.insert(i);
//        }

    }
}
