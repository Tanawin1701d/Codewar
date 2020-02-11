package binarysearch;

public class bsearch1 {
    public static void b1(){
        int[] Data = {0,1,2,3,4,5,6,7};
        int find = 7;
        boolean stop = false;
        int mid = Data.length/2;
        int min = 0;
        int max = Data.length;

        while (stop == false) {
            if (Data[mid] > find){
                max = mid;
            }else if (Data[mid] < find){
                min = mid;
            }
            else stop = true;
            mid = (min+max)/2;
        }
    }
}
