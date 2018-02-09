package com.unionx.wantuo.utils;
public class ArrayUtil {
	/**
	 * @param b
	 * @return  排序后的数组
	 */
	 public static int[] DataSort(int b[]){
       for(int i=0;i<b.length;i++){ 
	     for(int j=0;j<b.length-i-1;j++){
	       int temp=0;
	       if(b[j]>b[j+1]){
	         temp=b[j];
	         b[j]=b[j+1];
	         b[j+1]=temp;
	      }
	     }
	  }
        return b;
    }
}
