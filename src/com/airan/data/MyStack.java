package com.airan.data;

import java.util.List;

/**
 * Created by 11084919 on 2018/8/16.
 */
public class MyStack<T> {
    private List<T> data;

    public void push(T x){
        data.add(x);
    }

    public void pop(){
        data.remove(data.size()-1);
    }

    public T top(){
        return data.get(data.size()-1);
    }

}