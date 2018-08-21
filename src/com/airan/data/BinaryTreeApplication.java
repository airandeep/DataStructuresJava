package com.airan.data;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 11084919 on 2018/8/20.
 */


class Node{
    Object data;
    Node left;
    Node right;
    Node(Object data,Node left,Node right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTreeApplication {
    private static Stack<Node> a = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int n = input.length();
        for(int i = 0;i < n;i++){
            char temp = input.charAt(i);
            if(temp == '+' || temp == '*'){
                Node rightItem = a.peek();a.pop();
                Node leftItem = a.peek();a.pop();
                Node tempNode = new Node(temp,leftItem,rightItem);
                a.push(tempNode);
            }else{
                Node tempNode = new Node(temp,null,null);
                a.push(tempNode);
            }
        }
        //栈中留下的Node型数据即为表达式树
    }


}