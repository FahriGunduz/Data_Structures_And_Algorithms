import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Giriş metni
        String text = "huffman coding example";

        // 1. Karakter frekanslarını hesapla
        Map<Character, Integer> frequencyMap = HuffmanCoding.calculateFrequency(text);

        // 2. Huffman ağacını oluştur
        HuffmanNode root = HuffmanCoding.buildHuffmanTree(frequencyMap);

        // 3. Huffman kodlarını çıkar
        Map<Character, String> huffmanCodes = new HashMap<>();
        HuffmanCoding.generateHuffmanCodes(root, "", huffmanCodes);

        // 4. Sonuçları yazdır
        System.out.println("Huffman Kodları:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 5. Kodlanmış metni oluştur ve yazdır
        String encodedText = HuffmanCoding.encodeText(text, huffmanCodes);
        System.out.println("\nKodlanmış Metin: " + encodedText);

    }
}