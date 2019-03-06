import java.lang.Math;

public class HybridSort {

    private static final int INSERTION_SORT_THRESHOLD = 4;

    public HybridSort()
    {
    }

    /**
     * Randomly picks a pivot value, partitions/splits the array, then
     * finds the new index of the pivot
     * @param arr the given array
     * @param left the leftmost index
     * @param right the right index
     * @return the new index of the pivot after being quicksorted once
     */

    public int quicksort (double [] arr, int left, int right)
    {
        if (left < right) {
            return partition(arr, left, right);

//            quicksort(arr, left, p - 1);
//            quicksort(arr, p + 1, right);

        }
        return -1;
    }

    /**
     * Partitions, or splits, a given array. Also locates the new index of the pivot
     * @param arr the given array
     * @param left the left index to start searching
     * @param right the right index to start searching
     * @return the new index of pivot
     */
    private int partition(double[] arr, int left, int right) {
        if (left < right) {
            int pivot = (int) ((arr.length - 2)* Math.random());
            System.out.println("pivot index = " + pivot);
            System.out.println("pivot value = " + arr[pivot]);
            int i = left; // Avoids re-sorting the pivot
            int j = right;
            while (i < j) {
                while (i <= right && arr[i] <= arr[pivot]) {
                    ++i;
                }
                while (j >= i && arr[j] > arr[pivot]) {
                    --j;
                }
                if (i <= right && i < j) {
                    swap(arr, i, j);
                }
            }
            swap(arr, pivot, j);  // pivot to the middle
            return j;
        }
        return left;
    }

    /**
     * Swaps 2 elements in an array
     * @param myArray the integer array
     * @param i the previous, first index
     * @param j the subsequent, second index
     */
    private void swap(double[] myArray, int i, int j)
    {
        double temp = myArray[i];
        myArray[i] = myArray[j];
        myArray[j] = temp;
    }

    /**
     * Sorts a subarray using a chosen method of run-time O(n^2)
     * @param myArray the given subarray
     * @param left the left index to start searching
     * @param right the right index to start searching
     */
    public void quadraticSort(double [] myArray, int left, int right){
        // keeps track of how much you are decrementing index
        int difference = 0;
        for (int index = left; index <= right; index ++)
        {
            double minValue = myArray[index]; // create the temp variable for current value
            int prevIndex = index - 1;
            // Checks to see if any elements in myArray[0, ... index - 1]
            // if greater than the temp/minValue.
            while (prevIndex >= 0 && myArray[prevIndex] > minValue) {
                // if the previous value is greater than the temp/current value, switch
                myArray[index] = myArray[index - 1];
                prevIndex --; // keep going back
                index --;
                difference ++;
            }
            myArray[prevIndex + 1] = minValue; // update where the temp/minValue is
            // so you readjust where you left off
            index = index + difference;
            difference = 0;
        }
    }

    /**
     * Determines which sorting algorithm (quick sort or modified quadratic) to use on the
     * given array based on the array length
     * @param myArray the given array to be sorted
     * @param left the left index to start searching
     * @param right the right index to start searching
     */
    public void hybridsort (double [] myArray, int left, int right)
    {
        if (myArray.length <= INSERTION_SORT_THRESHOLD)
        {
            quadraticSort(myArray, left, right);
        }
        else {
            quicksort(myArray, left, right);
        }
    }

    public static void main(String[] args){
        HybridSort myHybridSort = new HybridSort();
        double[] myArray = {5, 2, 9, 12, 6, 8, 3, 7 };
        System.out.println("New index of pivot: " + myHybridSort.quicksort(myArray, 0, myArray.length - 1));
        for (double value: myArray)
        {
            System.out.print(value + " ");
        }


//        myHybridSort.quadraticSort(myArray, 1, myArray.length - 1);
//        System.out.println("\nAfter Quadratic Sort");
//        for (double value: myArray)
//        {
//            System.out.print(value + " ");
//        }

        System.out.println();
        myHybridSort.hybridsort(myArray, 0, myArray.length - 1);
        System.out.println("After Hybrid Sort: ");
        for (double value: myArray)
        {
            System.out.print(value + " ");
        }
    }
}
