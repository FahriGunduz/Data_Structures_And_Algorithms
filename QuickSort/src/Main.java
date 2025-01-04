import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        // Orijinal diziyi yazdır
        System.out.println("Orijinal Dizi: " + Arrays.toString(array));

        // Quick Sort'u çağır
        QuickSort.quickSort(array, 0, array.length - 1);

        // Sıralanmış diziyi yazdır
        System.out.println("Sıralanmış Dizi: " + Arrays.toString(array));
    }
}