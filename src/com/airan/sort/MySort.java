package com.airan.sort;

/**
 * Created by 11084919 on 2018/8/20.//升序
 */
public class MySort {


    public void quickSort(int[] a,int low,int high){
        if(low < high){
            int first = low;
            int last = high;
            int key = a[low];
            while (first < last){
                while (first < last && a[last] >= key){
                    last--;
                }
                a[first] = a[last];
                while (first < last && a[first] <= key){
                    first++;
                }
                a[last] = a[first];
            }
            a[first] = key;
            quickSort(a,low,first-1);
            quickSort(a,first+1,high);
        }
    }

    public void bubbleSort(int[] a){
        int n = a.length;
        boolean flag = false;
        while (!flag){
            flag = true;
            for(int i = 1;i < n;i++){
                if(a[i-1] < a[i]){
                    int temp = a[i-1];
                    a[i-1] = a[i];
                    a[i] = temp;
                    flag = false;
                }
            }
            n--;
        }
    }

    private final static int NOT_FOUND = -1;
    //二分法查询//数据已排序//返回查询数据的在数组中的下标
    public int binarySearch(int[] a,int x,int low,int high){
        if(low <= high){//注意等于的临界条件和快锁排序不一样
            int mid = (low + high)/2;
            if(x < a[mid]){
                return binarySearch(a,x,low,mid-1);
            }else if(x > a[mid]){
                return binarySearch(a,x,mid+1,high);
            }else{
                return mid;
            }
        }
        return NOT_FOUND;
    }

}