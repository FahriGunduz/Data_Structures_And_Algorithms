public class BinarySearch {
    // Binary Search Yöntemi
    public static void binarySearch(int[] array, int target) {
        int low = 0; // Dizinin başlangıç indeksi
        int high = array.length - 1; // Dizinin son indeksi

        while (low <= high) {
            int mid = low + (high - low) / 2; // Ortadaki elemanı bul

            if (array[mid] == target) {
                System.out.println("Eleman bulundu: İndeks = " + mid); // Eleman bulundu
                return;
            }
            if (array[mid] < target) {
                low = mid + 1; // Sağ yarıya git
            } else {
                high = mid - 1; // Sol yarıya git
            }
        }
        // Eleman bulunamadıysa buraya ulaşılır
        System.out.println("Eleman bulunamadı.");
    }
}
