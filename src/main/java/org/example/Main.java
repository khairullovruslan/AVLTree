package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10000; ++i){
            numbers.add(random.nextInt(0, 100000));
        }
        Tree<Integer> avlTree = new AVLTree<>();
        for (int i  = 0; i < 10000; i++){
//            long before = System.nanoTime();
//            AVLTree.iter = 0;
            avlTree.insert(numbers.get(i));
//            System.out.println(AVLTree.iter);
//            long after = System.nanoTime();
//            System.out.println(after - before);
        }

//        for (int i = 0; i < 100; i++){
////            long before = System.nanoTime();
//            avlTree.search(numbers.get(random.nextInt(0, 10000)));
////            long after = System.nanoTime();
//            System.out.println(AVLTree.iterSearch);
//        }
        for (int i = 0; i < 1000; i++){
//            long before = System.nanoTime();
            avlTree.delete(numbers.get(random.nextInt(0, 5000)));
//            long after = System.nanoTime();
            System.out.println(AVLTree.iterDelete);
//            System.out.println(AVLTree.iterSearch);
        }

    }
}