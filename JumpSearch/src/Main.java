import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        // Örnek sıralı dizi
        int[] array = {2, 3, 4, 10, 40, 50, 70, 90, 100};

        // Aranacak eleman
        int target = 40;

        // Sonuçları yazdır
        System.out.println("Dizi: " + Arrays.toString(array));
        System.out.println("Aranan eleman: " + target);
        JumpSearch.displaySearchResult(array, target);
    }
}