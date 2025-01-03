public class Main {
    public static void main(String[] args) {
            BTree tree = new BTree(3); // Derece 3 olan bir B-Tree oluşturuyoruz (maksimum 5 anahtar)
            // Anahtarlar ekleniyor
            tree.insert(10);
            tree.insert(20);
            tree.insert(5);
            tree.insert(6);
            tree.insert(15);
            System.out.println("B-Tree Traversal:");
            tree.traverse(); // Ağacı sıralı olarak yazdır
            // Arama işlemi
            System.out.println("\n20 anahtarı bulundu mu? " + tree.search(20)); // true
            System.out.println("25 anahtarı bulundu mu? " + tree.search(25)); // false
            // Silme işlemi
            tree.delete(6);
            System.out.println("\nB-Tree Traversal After Deletion:");
            tree.traverse(); // Silme sonrası ağaç yazdır

    }
}