import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        // Orijinal diziyi yazdır
        System.out.println("Orijinal Dizi: " + Arrays.toString(array));

        // Bubble Sort'u çağır
        BubbleSort.bubbleSort(array);

        // Sıralanmış diziyi yazdır
        System.out.println("Sıralanmış Dizi: " + Arrays.toString(array));
    }
}