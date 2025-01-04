public class HuffmanNode {
    char character;  // Karakter (sadece yaprak düğümlerde bulunur)
    int frequency;   // Karakterin frekansı
    HuffmanNode left;  // Sol çocuk düğüm
    HuffmanNode right; // Sağ çocuk düğüm

    // Constructor
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // İç düğüm (karakter olmadan)
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '-';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}
