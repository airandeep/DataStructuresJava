package com.airan.data;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by 11084919 on 2018/8/7.
 */
public class MyLinkedList<T> implements Iterable{
    //在c++中这里使用的是结构体struct，现在在java中改用类 默认是私有private
    class Node{
        Node(){
            data = null;
            prev = null;
            next = null;
        }
        //内部类中变量定义成私有的，内部类实例化对象也可以访问
        Node(T _data,Node _prev,Node _next){
            data = _data;prev = _prev;next = _next;
        }

        T data;
        Node prev;
        Node next;
    }


    private int theSize;
    private int modCount = 0;//操作数量
    private Node head;
    private Node tail;

    public MyLinkedList(){
        init();
    }

    private void init(){
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        theSize = 0;
        modCount++;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return theSize == 0;
    }

    //插入方法，在尾节点之前插入数据
    public void add(T x){
//        add(size(),x);
        Node newNode = new Node(x,tail.prev,tail);
        tail.prev.next = newNode;
        tail.prev = newNode;
    }

    public void add(int idx,T x){
        addBefore(getNode(idx),x);
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    public T set(int idx,T newVal){
        Node node = getNode(idx);
        T oldVal = node.data;
        node.data = newVal;
        return oldVal;
    }

    private T remove(int idx){
        return remove(getNode(idx));
    }


  //基础方法
    private void addBefore(Node p,T x){
        Node temp = new Node(x,p.prev,p);
        p.prev.next = temp;
        p.prev = temp;
        theSize++;
        modCount++;
    }

    private T remove(Node p){
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount++;
        return p.data;
    }

    //获取对应下标的节点Node，所有其他方法的基础 重要的例程
    private Node getNode(int idx){
        Node result;
        //当idx等于size()时返回尾部哨兵节点用于插数据
        if(idx < 0 || idx > size()){
            throw new IndexOutOfBoundsException();
        }
        if(idx < size()/2){
            result = head.next;
            for(int i = 0;i< idx;i++){
                result = result.next;
            }
        }else{
            result = tail;
            for(int i = size();i>idx;i--){
                result = result.prev;
            }
        }

        return result;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator{

        private Node current = head.next;
        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T x = current.data;
            current = current.next;
            return x;
        }

        public void remove(){
            //返回当前数据之后，节点向后挪动了一位
            MyLinkedList.this.remove(current.prev);
        }
    }

}