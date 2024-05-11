package org.example;

public class AVLTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;
    public static int iter = 0;
    public static int iterSearch = 0;
    public static int iterDelete = 0;

    @Override
    public Tree<T> insert(T data) {
        root = insert(data, root);
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        // обычная реализация бинарного дерева поиска
        iter++;
        if (node == null) {
            return new Node<>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void delete(T data) {
        iterDelete = 0;
        root = delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        iterDelete++;
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            // One Child or Leaf Node (no children)
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }
            // Two Children
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            System.out.println(node);
            traverseInOrder(node.getRightChild());
        }
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private Node<T> applyRotation(Node<T> node) {
        iterDelete++;
//        iter++;
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<T> rotateRight(Node<T> node) {
//        iter++;
        iterDelete++;
        Node<T> leftNode = node.getLeftChild();
        Node<T> centerNode = leftNode.getRightChild();
        leftNode.setRightChild(node);
        node.setLeftChild(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node<T> rotateLeft(Node<T> node) {
//        iter++;
        iterDelete++;
        Node<T> rightNode = node.getRightChild();
        Node<T> centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(node);
        node.setRightChild(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private void updateHeight(Node<T> node) {
//        iter++;
        iterDelete++;
        int maxHeight = Math.max(
                height(node.getLeftChild()),
                height(node.getRightChild())
        );
        node.setHeight(maxHeight + 1);
    }

    private int balance(Node<T> node) {
        iterDelete++;
        return node != null ? height(node.getLeftChild()) - height(node.getRightChild()) : 0;
    }

    private int height(Node<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    public void search(T data) {
//        System.out.println(searchElem(data, root).getData().equals(data));
        iterSearch = 0;
        searchElem(data, root);
    }

    public Node<T> searchElem(T data, Node<T> elem) {
        iterSearch++;
        if (elem == null || elem.getData().equals(data)){
            return elem;
        }
        if (data.compareTo(elem.getData()) < 0){
            return searchElem(data, elem.getLeftChild());
        }
        return searchElem(data, elem.getRightChild());

    }

}