package com.airan.data;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by 11084919 on 2018/8/21.
 */
public class AvlTree<T extends Comparable<? super T>>
{
    class Node{
        T data;
        Node left;
        Node right;
        int height;
        Node(T data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
        Node(T data){
            this(data,null,null);
        }
    }

    private Node root;
    private int theSize;

    public AvlTree(){
        root = null;
        theSize = 0;
    }

    private int height(Node node){
        return node == null ? -1 : node.height;
    }


    public void insert(T x){
        root = insert(x,root);
        theSize++;
    }

    public void printTree(){
        printLevel(root);
    }

    public int size(){
        return theSize;
    }

    private Node insert(T x,Node t){
        if(t == null){
            return new Node(x);//刚new出来的节点高度为0
        }else if(x.compareTo(t.data) < 0){
            t.left = insert(x,t.left);
            //这里表示左边插得
            if(height(t.left) - height(t.right) > 1){//说明此节点t已经失去平衡，需要判断新插入节点是不是在t节点的左儿子的左儿子或右儿子
                if(x.compareTo(t.left.data) < 0){
                    t = RWLL(t);
                }else{
                    t = RWLR(t);
                }
            }
        }else if(x.compareTo(t.data) > 0){
            t.right = insert(x,t.right);
            if(height(t.right) - height(t.left) > 1){
                if(x.compareTo(t.right.data) > 0){
                    t = RWRR(t);
                }else{
                    t = RWRL(t);
                }
            }
        }

        t.height = Math.max(height(t.left),height(t.right)) + 1;
        return t;//return是为了连接起来
    }

//    private Node remove(T x,Node p){
//        if(p!=null){
//
//        }
//    }

    private Node RWLL(Node p){
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        p.height = Math.max(height(p.left),height(p.right)) + 1;
        q.height = Math.max(height(q.left),p.height) + 1;
        return q;
    }

    private Node RWRR(Node p){
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        p.height = Math.max(height(p.left),height(p.right)) + 1;
        q.height = Math.max(p.height,height(q.right)) + 1;
        return q;
    }

    private Node RWLR(Node p){
        p.left = RWRR(p.left);
        return RWLL(p);
    }

    private Node RWRL(Node p){
        p.right = RWLL(p.right);
        return RWRR(p);
    }

    private Node findMax(Node p){
        if(p != null){
            while (p.right != null){
                p = p.right;
            }
        }
        return p;
    }

    private Node findMin(Node p){
        if(p == null){
            return null;
        }else if(p.left == null){
            return p;
        }else{
            return findMin(p.left);
        }
    }

    private void printLn(Node node){
        if(node != null){
            printLn(node.left);
            System.out.print(node.data + ":" + node.height + " ");
            printLn(node.right);
        }
    }

    private void printLevel(Node node){
        if(node != null){
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(node);
            while (!queue.isEmpty()){
                Node temp = queue.poll();
                System.out.print(temp.data + ":" + temp.height + " ");
                if(temp.left != null){
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
            }
        }
    }


}