import java.util.*;
public class HuffmanCoding {
    // Karakter frekanslarını hesaplayan yöntem
    public static Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }
        return frequencyMap;
    }

    // Huffman ağacını oluşturan yöntem
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

        // Frekans tablosunu kullanarak düğümleri sıraya ekle
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Huffman ağacını oluştur
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();  // En düşük frekanslı düğüm
            HuffmanNode right = pq.poll(); // İkinci en düşük frekanslı düğüm
            HuffmanNode newNode = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.add(newNode); // Yeni iç düğümü sıraya ekle
        }

        return pq.poll(); // Kök düğümü döndür
    }

    // Huffman kodlarını çıkaran yöntem
    public static void generateHuffmanCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) { // Yaprak düğüm
            huffmanCodes.put(root.character, code);
        }
        generateHuffmanCodes(root.left, code + "0", huffmanCodes);
        generateHuffmanCodes(root.right, code + "1", huffmanCodes);
    }

    // Metni Huffman kodlarına dönüştüren yöntem
    public static String encodeText(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(ch));
        }
        return encodedText.toString();
    }
}
