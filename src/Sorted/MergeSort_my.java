package Sorted;

public class MergeSort_my {

    void merge(int data[],int start,int middle,int range){
        int range1 = middle-start+1;
        int range2 = range-middle;
        int[] Left = new int[range1];
        int[] Right = new int[range2];

        for(int i = 0 ; i < range1 ; i++){
           Left[i] = data[start+i];
        }
        for(int j = 0 ; j < range2 ; j++ ){
            Right[j] = data[middle+1+j];
        }

        int i = 0, j = 0, k=start;
        while(i < range1 && j < range2){
            if(Left[i] <= Right[j]){
                data[k] = Left[i];
                i++;
            }
            else{
                data[k] = Right[j];
                j++;
            }
            k++;
        }

        while (i < range1) {
            data[k] = Left[i];
            i++; k++;
        }
        while (j < range2) {
            data[k] = Right[j];
            j++;
            k++;
        }

    }
    void sort(int data[],int start,int range){
        if(start < range){
            int m = (start+range)/2;
            sort(data,start,m);
            sort(data,m+1, range);
            merge(data,start,m,range);
        }
    }

    public static void main(String args[]){
        int data[] = {12, 11, 13, 5, 6, 7};
        MergeSort_my ob = new MergeSort_my();
        ob.sort(data, 0, data.length-1);
        for (int i = 0; i < data.length ; i++) {
            System.out.println(data[i]+" ");
        }
    }
}
