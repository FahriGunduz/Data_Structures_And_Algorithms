public class BubbleSort {
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        // Dizi boyunca tekrar eden döngü
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Bu turda değişim yapılıp yapılmadığını takip eder

            // Her döngüde bitişe kadar olan kısmı kontrol et
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Eğer bir eleman, kendisinden sonraki elemandan büyükse yer değiştir
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true; // Değişim yapıldığını işaretle
                }
            }

            // Eğer hiçbir değişim yapılmamışsa dizi sıralıdır, döngüyü sonlandır
            if (!swapped) {
                break;
            }
        }
    }
}
