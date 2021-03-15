class VariableSorting {
    public int[] bubbleSort(int[] array) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    isSorted = false;
                    swap(array, i, i + 1);
                    /*
                    current = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = current;
                     */
                }
            }
        }
        return array;
    }

    public int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            ///  int current = array[i];
            while (j >= 0 && array[j + 1] < array[j]) {
                swap(array, j + 1, j);
/*
                current = array[j + 1];
                array[j + 1] = array[j];
                array[j] = current;
                 */
                j--;
            }
        }
        return array;
    }

    public int[] selectionSort(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < array.length; j++) {
            int current = 0;
            for (int i = j; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                    current = i;
                }
            }
            swap(array, current, j);
            /*
            array[current] = array[j];
            array[j] = min;
             */
            min = Integer.MAX_VALUE;

        }
        return array;
    }

    public int[] mergeSort(int[] array, int left, int right) {
        if (right <= left) return array;
        int mid = (right + left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);

        return array;
    }

    private void merge(int[] array, int left, int mid, int right) {
        int leftLength = mid - left + 1;
        int rightLength = right - mid;
// здесь array делится на 2 подмассива -left и right
        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];
        //цикл присваевает значения элементов левой и правой части
        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = array[left + i];
        }
        for (int i = 0; i < rightLength; i++) {
            rightArray[i] = array[mid + i + 1];
        }
//здесь происходит слияние - сравнивается первый элемент правого и первый элемент левого массива,
// меньшее значение присваевается массиву array
        int leftIndex = 0; //индекс левого подмассива
        int rightIndex = 0; //индекс правого подмассива

        for (int i = left; i < right + 1; i++) {

            if (leftIndex < leftLength && rightIndex < rightLength) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (rightIndex < rightLength) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            } else {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
        }
    }


    public int[] quickSort(int[] array, int begin, int end) {
        if (end <= begin) return array;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
        return array;


    }

    public int partition(int[] array, int begin, int end) {
        int pivot = end;//стержень
        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                swap(array, counter, i); //элементы с индексом counter и i меняются местами
                counter++;
                /*
                int temp=array[counter];
                array[counter]=array[i];
                array[i]=temp;
                counter++;

                 */
            }
        }
        swap(array, pivot, counter); //элементы с индексом counter и pivot меняются местами
        /*
        int temp=array[pivot];
        array[pivot]=array[counter];
        array[counter]=temp;
*/
        return counter;
    }

    void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

public class SortingAlgorithms {
    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 30, 1, 2, 7, 6, 8, 30, 50};
        VariableSorting sorting = new VariableSorting();
        // System.out.println(Arrays.toString(sorting.bubbleSort(array)));
        // System.out.println(Arrays.toString(sorting.insertionSort(array)));
        //System.out.println(Arrays.toString(sorting.selectionSort(array)));
        //System.out.println(Arrays.toString(sorting.mergeSort(array, 0, array.length - 1)));
        // System.out.println(Arrays.toString(sorting.quickSort(array, 0, 7)));
        test1();
        test2();
    }
        public static void test1(){
            VariableSorting sorting=new VariableSorting();
            int testLength=10000;
            int [] arr1=new int[testLength];
            int [] arr2=new int[testLength];
            int [] arr3=new int[testLength];
            int [] arr4=new int[testLength];
            System.out.println("\n------Случайный массив------");

            for (int i=0;i<testLength;i++){
                arr1[i]=arr2[i]=arr3[i]=arr4[i]=(int)Math.round(Math.random()*1000);
            }
            System.out.println("Быстрая сортировка");
            measureTime(()->sorting.quickSort(arr1,0,arr1.length-1));

            System.out.println("Cортировка слиянием");
            measureTime(()->sorting.mergeSort(arr2,0,arr2.length-1));

            System.out.println("Cортировка пузырьком");
            measureTime(()->sorting.bubbleSort(arr3));

            System.out.println("Cортировка выбором");
            measureTime(()->sorting.insertionSort(arr4));

    }
    public static void test2(){
        VariableSorting sorting=new VariableSorting();
        int testLength=10000;
        int [] arr1=new int[testLength];
        int [] arr2=new int[testLength];
        int [] arr3=new int[testLength];
        int [] arr4=new int[testLength];
        System.out.println("\n------Отсортированный массив------");

        for (int i=0;i<testLength;i++){
            arr1[i]=arr2[i]=arr3[i]=arr4[i]=i;
        }
        System.out.println("Быстрая сортировка");
        measureTime(()->sorting.quickSort(arr1,0,arr1.length-1));

        System.out.println("Cортировка слиянием");
        measureTime(()->sorting.mergeSort(arr2,0,arr2.length-1));

        System.out.println("Cортировка пузырьком");
        measureTime(()->sorting.bubbleSort(arr3));

        System.out.println("Cортировка выбором");
        measureTime(()->sorting.insertionSort(arr4));

    }

    public static void measureTime(Runnable task){
        long startTime=System.currentTimeMillis();
        task.run();
        long elapsed=System.currentTimeMillis()-startTime;
        System.out.println("Затраченное время: " + elapsed+" ms");
    }
}
