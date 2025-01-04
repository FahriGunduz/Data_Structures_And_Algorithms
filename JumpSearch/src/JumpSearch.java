public class JumpSearch {
    public static int jumpSearch(int[] array, int target) {
        int n = array.length; // Dizinin boyutu

        // Blok boyutunu hesapla (√n)
        int blockSize = (int) Math.sqrt(n); // Blok büyüklüğü
        int currentStep = blockSize;       // Mevcut blok sonunu temsil eder
        int prev = 0;                      // Bir önceki blok sonunu temsil eder

        // Doğru bloğu bulana kadar bloklar arasında sıçrama yap
        while (currentStep < n && array[currentStep - 1] < target) {
            prev = currentStep;            // Bir sonraki bloğa geç
            currentStep += blockSize;      // Blok sonunu güncelle

            // Eğer blok diziyi aşarsa, eleman dizide yok
            if (prev >= n) {
                return -1;
            }
        }

        // Doğrusal arama: Doğru blokta hedef elemanı ara
        while (prev < Math.min(currentStep, n) && array[prev] < target) {
            prev++; // Eleman bulunana kadar indeks artır
        }

        // Eğer hedef eleman bulunursa, indeksini döndür
        if (prev < n && array[prev] == target) {
            return prev;
        }

        // Eleman bulunamazsa -1 döndür
        return -1;
    }
    public static void displaySearchResult(int[] array, int target) {
        int result = jumpSearch(array, target); // Jump Search'i çağır
        if (result != -1) {
            System.out.println("Eleman bulundu: İndeks = " + result);
        } else {
            System.out.println("Eleman bulunamadı.");
        }
    }
}
