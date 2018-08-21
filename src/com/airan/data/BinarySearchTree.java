package com.airan.data;

import java.nio.BufferUnderflowException;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by 11084919 on 2018/8/20.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private class Node{
        T data;
        Node left;
        Node right;
        Node(T data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
        Node(T data){
            this(data,null,null);
        }
    }

    private Node root;

    public BinarySearchTree(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contain(T x){
        return contain(x,root);
    }

    public T findMin(){
        if(root == null){
            throw new BufferUnderflowException();
        }
        return findMin(root).data;
    }

    public T findMax(){
        if(root == null){
            throw new BufferUnderflowException();
        }
        return findMax(root).data;
    }

    public void insert(T x){
        root = insert(x,root);
    }

    public void remove(T x){
        root  = remove(x,root);
    }

    public void printTree(){
        printIn(root);
    }



    private boolean contain(T x,Node t){
        if(t != null){
            if(x.compareTo(t.data) < 0){
                contain(x,t.left);
            }else if(x.compareTo(t.data) > 0){
                contain(x,t.right);
            }else{
                return true;
            }
        }
        return false;
    }

    private Node findMin(Node t){
        if(t != null){
            while (t.left != null){
                t = t.left;
            }
        }
        return t;
    }

    private Node findMax(Node t){
        if(t == null){
            return null;
        }else if(t.right == null){
            return t;
        }else{
            return findMax(t.right);
        }
    }

    //java 这个Node t t是按值传递
    //如果已经存在含有此数据节点，直接返回此节点的引用即可；否则返回新new节点的地址
    private Node insert(T x,Node t){
        if(t == null){
            return new Node(x);
        }else if(x.compareTo(t.data) < 0){
            t.left = insert(x,t.left);
        }else if(x.compareTo(t.data) > 0){
            t.right = insert(x,t.right);
        }
        return t;
    }

    //java只有按值传递没有按地址传递，不像c++可以指针引用
    private void insert1(T x,Node t){
        if(t == null){
            t = new Node(x);
        }else if(x.compareTo(t.data) < 0){
            insert1(x,t.left);
        }else if(x.compareTo(t.data) > 0){
            insert1(x,t.right);
        }
    }

    //java都是按值传递//所谓的非基本数据类型的参数传递类似与c++的指针引用
    //删除最难处理的是当前节点有2个儿子(删除策略:用此节点右子树的最小节点数据代替本节点数据，然后删除右子树最小节点则将问题转化为删除单儿子节点)
    private Node  remove(T x,Node t){
        if(t != null){
            int flag = x.compareTo(t.data);
            if(flag < 0){
                t.left = remove(x,t.left);
            }else if(flag > 0){
                t.right = remove(x,t.right);
            }else{
                if(t.left != null && t.right != null){
                    t.data = findMin(t.right).data;
                    remove(t.data,t.right);
                }else{
                    t = t.left != null ? t.left : t.right;
                }
            }
        }
        //每一次递归都会调用这个return
        return t;//这里的return 将前后联系起来很关键。
    }



    private void printIn(Node t){
        if(t != null){
            printIn(t.left);
            System.out.print(t.data + " ");
            printIn(t.right);
        }
    }

    //层次遍历
    private void printLevel(Node t){
        if(t != null){
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(t);
            while (!queue.isEmpty()){
                Node temp = queue.remove();
                System.out.print(temp.data + " ");
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