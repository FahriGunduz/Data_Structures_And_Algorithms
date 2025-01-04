public class QuickSort {
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Diziyi bölen pivot indeksi
            int pivotIndex = partition(array, low, high);

            // Pivotun sol tarafını sırala
            quickSort(array, low, pivotIndex - 1);

            // Pivotun sağ tarafını sırala
            quickSort(array, pivotIndex + 1, high);
        }
    }
    //Bölme (Partition) işlemi.
    //Pivot elemanına göre diziyi böler ve pivotun doğru indeksini döndürür.
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Pivot elemanını seç (genelde son eleman)
        int i = low - 1; // Daha küçük elemanların son indeksi

        // Pivot dışındaki elemanları kontrol et
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++; // Daha küçük bir eleman bulundu
                // Elemanları yer değiştir
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Pivotu doğru yerine yerleştir
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1; // Pivotun doğru konumu
    }

}
