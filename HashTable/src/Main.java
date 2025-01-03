public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(5);

        // Ekleme işlemleri
        hashTable.insert("Ahmet", 85);
        hashTable.insert("Zeynep", 92);
        hashTable.insert("Ali", 78);
        hashTable.insert("Ece", 90);
        hashTable.insert("Cem", 88); // Çakışma olasılığı

        // Yazdırma
        System.out.println("Hash Table:");
        hashTable.display();

        // Silme işlemi
        hashTable.delete("Ali");
        hashTable.delete("Mehmet"); // Tabloda yok

        // Yazdırma sonrası kontrol
        System.out.println("\nSilme sonrası Hash Table:");
        hashTable.display();

        // Değer alma
        System.out.println("\nAhmet'in notu: " + hashTable.get("Ahmet"));
        System.out.println("Ece'nin notu: " + hashTable.get("Ece"));
    }
}
