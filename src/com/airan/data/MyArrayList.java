package com.airan.data;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by 11084919 on 2018/8/6.
 */
//继承Iterable类表示一定会实现一个iterator()接口
public class MyArrayList<T> implements Iterable{

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private T[] theItems;

    public MyArrayList(){
        init();
    }

    public boolean add(T x){
        add(size(),x);
        return true;
    }

    public void add(int idx,T x){
        //判断一下数组是否需要扩容
        if(theItems.length == size()){
            ensureCapacity(size()*2 + 1);
        }
        for(int i = size();i > idx;i--){
            theItems[i] = theItems[i-1];
        }
        theItems[idx] = x;
        theSize++;
    }

    private void init(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public T get(int idx){
        if(idx >= size() || idx < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    public T set(int idx,T newVal){
        if(idx >= size() || idx < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldItem = theItems[idx];
        theItems[idx] = newVal;
        return oldItem;
    }

    private int size(){
        return theSize;
    }

    public T remove(int idx){
        T removeItem = theItems[idx];
        for(int i = idx; i < theItems.length-1; i++){
            theItems[idx] = theItems[idx+1];
        }
        theSize--;
        return removeItem;
    }

    //用于初始化和扩容
    public void ensureCapacity( int newCapacity ){
        if( newCapacity < theSize ){
            return;
        }

        T[] old = theItems;
        theItems = (T[])new Objects[newCapacity];
        //theItems = new T[newCapacity];//此种操作是错误的，因为泛型T不能直接new
        for(int i = 0; i < theSize; i++){
            theItems[i] = old[i];
        }
    }

    public Iterator iterator(){
        return new ArrayListIterator();
    }


    //迭代器就是内部类
    private class ArrayListIterator implements Iterator{
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        //返回当前下标的数据，并且下标向后推移
        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}