// Ağaç yapısında kullanılacak düğüm sınıfı
public class Node {
    int data; // Düğümün taşıdığı veri
    Node left, right; // Sol ve sağ alt ağaçlara işaretçiler

    // Düğümün oluşturulması için yapılandırıcı (constructor)
    Node(int data) {
        this.data = data; // Gelen veriyi düğüme ata
        left = null; // Başlangıçta sol alt ağaç boş
        right = null; // Başlangıçta sağ alt ağaç boş
    }
}
