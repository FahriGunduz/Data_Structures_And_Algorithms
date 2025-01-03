public class BTreeNode {
    int[] keys; // Anahtarları tutar
    BTreeNode[] children; // Çocukları tutar
    int keyCount; // Düğümdeki anahtar sayısı
    boolean isLeaf; // Yaprak düğüm olup olmadığını gösterir

    // Yapıcı metod (Constructor)
    BTreeNode(int t, boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new int[2 * t - 1]; // Maksimum anahtar sayısı
        this.children = new BTreeNode[2 * t]; // Maksimum çocuk sayısı
        this.keyCount = 0; // Başlangıçta anahtar sayısı sıfır
    }
}
