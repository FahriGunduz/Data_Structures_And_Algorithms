// Programın giriş noktası olan Main sınıfı
public class Main {
    public static void main(String[] args) {
        // Ağaç işlemleri için Basic_Operations sınıfından bir nesne oluştur
        Basic_Operations tree = new Basic_Operations();

        // Ağaca düğümler ekle
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Ağacın durumunu yazdır
        printTreeDetails(tree, "Başlangıç durumu:");

        // 40 değerine sahip düğümü sil
        tree.delete(40);

        // Güncellenmiş ağacın durumunu yazdır
        printTreeDetails(tree, "Silme işleminden sonra:");
    }

    // Ağacın detaylarını yazdıran yardımcı bir metod
    private static void printTreeDetails(Basic_Operations tree, String message) {
        System.out.println(message);
        System.out.println("Height: " + tree.height()); // Ağacın yüksekliğini yazdır
        System.out.println("Size: " + tree.size()); // Ağacın toplam eleman sayısını yazdır
        System.out.print("Inorder: ");
        tree.inorder(); // Ağacın inorder sıralamasını yazdır
        System.out.println();
        System.out.print("Preorder: ");
        tree.preorder(); // Ağacın preorder sıralamasını yazdır
        System.out.print("Postorder: ");
        tree.postorder(); // Ağacın postorder sıralamasını yazdır
        System.out.println();
    }
}
