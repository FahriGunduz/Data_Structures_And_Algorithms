public class Basic_Operations {
    Node root; // Ağacın kök düğümünü temsil eder

    Basic_Operations() {
        root = null; // Başlangıçta ağaç boş
    }

    // İnsanların daha kolay anlaması için ekleme (insert) fonksiyonu
    public void insert(int data) {
        Node newNode = new Node(data); // Yeni bir düğüm oluştur
        if (root == null) { // Eğer ağaç boşsa, kök düğüm olarak ayarla
            root = newNode;
            return;
        }
        Node current = root;
        while (true) {
            if (data < current.data) { // Yeni veri mevcut düğümden küçükse sol alt ağaç
                if (current.left == null) {
                    current.left = newNode; // Sol alt ağaca düğümü ekle
                    break;
                }
                current = current.left; // Sol alt ağaçta ilerle
            } else { // Yeni veri mevcut düğümden büyükse sağ alt ağaç
                if (current.right == null) {
                    current.right = newNode; // Sağ alt ağaca düğümü ekle
                    break;
                }
                current = current.right; // Sağ alt ağaçta ilerle
            }
        }
    }

    // Silme (delete) fonksiyonu
    public void delete(int data) {
        Node current = root;
        Node parent = null;

        // Silinecek düğümü bulana kadar ağacı gezin
        while (current != null && current.data != data) {
            parent = current;
            if (data < current.data) {
                current = current.left; // Sol alt ağaçta ilerle
            } else {
                current = current.right; // Sağ alt ağaçta ilerle
            }
        }

        // Eğer düğüm bulunamazsa, işlem sonlandırılır
        if (current == null) {
            System.out.println("Düğüm bulunamadı.");
            return;
        }

        // 1. Durum: Yaprak düğümse (çocukları yok)
        if (current.left == null && current.right == null) {
            if (parent == null) { // Silinen düğüm kökse
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // 2. Durum: Tek çocuk varsa
        else if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right; // Çocuğu seç
            if (parent == null) {
                root = child; // Kök düğümü güncelle
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        // 3. Durum: İki çocuğu varsa
        else {
            Node successorParent = current;
            Node successor = current.right;

            // Sağ alt ağacın en küçük düğümünü bul
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // Successor'un değerini silinen düğüme ata
            current.data = successor.data;

            // Successor düğümünü sil
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }

        System.out.println(data + " başarıyla silindi.");
    }

    // Preorder traversal (Kök -> Sol -> Sağ)
    public void preorder() {
        preorderRec(root);
        System.out.println(); // Satır sonu
    }
    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " "); // Kök düğümü yazdır
            preorderRec(root.left); // Sol alt ağaca git
            preorderRec(root.right); // Sağ alt ağaca git
        }
    }

    // Inorder traversal (Sol -> Kök -> Sağ)
    public void inorder() {
        inorderRec(root);
    }
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left); // Sol alt ağaca git
            System.out.print(root.data + " "); // Kök düğümü yazdır
            inorderRec(root.right); // Sağ alt ağaca git
        }
    }

    // Postorder traversal (Sol -> Sağ -> Kök)
    public void postorder() {
        postorderRec(root);
        System.out.println(); // Satır sonu
    }
    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left); // Sol alt ağaca git
            postorderRec(root.right); // Sağ alt ağaca git
            System.out.print(root.data + " "); // Kök düğümü yazdır
        }
    }

    // Ağacın yüksekliğini hesaplayan fonksiyon
    public int height() {
        return heightRec(root);
    }
    private int heightRec(Node root) {
        if (root == null) {
            return 0; // Boş ağacın yüksekliği 0'dır
        }
        int leftHeight = heightRec(root.left); // Sol alt ağacın yüksekliği
        int rightHeight = heightRec(root.right); // Sağ alt ağacın yüksekliği
        return Math.max(leftHeight, rightHeight) + 1; // Daha büyük olan +1
    }

    // Ağacın toplam eleman sayısını hesaplayan fonksiyon
    public int size() {
        return sizeRec(root);
    }
    private int sizeRec(Node root) {
        if (root == null) {
            return 0; // Boş ağacın eleman sayısı 0'dır
        }
        return sizeRec(root.left) + sizeRec(root.right) + 1; // Sol + Sağ + Kendi
    }
}
