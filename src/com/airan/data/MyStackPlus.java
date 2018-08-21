package com.airan.data;

import java.util.Stack;

/**
 * Created by 11084919 on 2018/8/16.
 */
public class MyStackPlus {

    public boolean jus(String input){
        Stack<Character> a = new Stack<>();
        int n = input.length();
        for(int i = 0;i < n;i++){
            if(input.charAt(i) == ')'){
                if(a.isEmpty()){
                    return false;
                }else{
                    while (a.peek() != '('){
                        char test = a.pop();
                        if(test == '['){
                            return false;
                        }
                        if(a.isEmpty()){
                            return false;
                        }
                    }
                    a.pop();
                }
            }else if(input.charAt(i) == ']'){
                if(a.isEmpty()){
                    return false;
                }else{
                    while (a.peek() != '['){
                        char test = a.pop();
                        if(test == '('){
                            return false;
                        }
                        if(a.isEmpty()){
                            return false;
                        }
                    }
                    a.pop();
                }
            }else{
                a.push(input.charAt(i));
            }
        }
        return true;
    }

}