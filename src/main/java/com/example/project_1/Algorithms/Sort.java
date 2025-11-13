package com.example.project_1.Algorithms;

//The class that I implements the heap sort in
public class Sort {

    public static <T extends Comparable<T>> void heapSort(T[] array){
        //To make the array as a max heap, where we heapify every element that have children
        for (int i = array.length/2-1; i >= 0; i--) {
            heapify(array, i, array.length - 1);
        }

        for (int i = array.length - 1; i > 0; i--) {
            //To make the current largest element at the end of the array
            swap(array, 0, i);
            //To heapify the array without the element that we swaped to the end since it already sorted
            heapify(array, 0, i - 1);
        }

    }

    //n is the index of the last element in the array
    //i is the index of the element that we heapify
    private static <T extends Comparable<T>> void heapify(T[] array,int i,int n){

        while(n>= i*2+1){
            //If the element's have 2 children
            if (n >= i*2+2){
                //To compare it to the largest between it children
                if (array[i*2+1].compareTo(array[i*2+2]) > 0){
                    if (array[i*2+1].compareTo(array[i]) > 0) {
                        swap(array, i, i * 2 + 1);
                        i = i * 2 + 1;
                    }
                    else
                        break;
                }else{
                    if (array[i*2+2].compareTo(array[i]) > 0) {
                        swap(array, i, i * 2 + 2);
                        i = i * 2 + 2;
                    }
                    else
                        break;
                }
            }
            //If the element's have 1 child
            else if (n == i*2+1){
                //To compare it to it only child
                if (array[i*2+1].compareTo(array[i]) > 0) {
                    swap(array, i, i * 2 + 1);
                    i = i * 2 + 1;
                }
                //To break if the upper condition didnt work
                break;
            }
        }
    }

    private static <T> void swap(T[] array,int i,int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
