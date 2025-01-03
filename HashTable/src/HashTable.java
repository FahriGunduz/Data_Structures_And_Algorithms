public class HashTable {
    private String[] keys;   // Anahtarları saklayan dizi
    private int[] values;    // Değerleri saklayan dizi
    private int size;

    // Hashtable yapıcı metodu
    public HashTable(int size) {
        this.size = size;
        keys = new String[size];
        values = new int[size];
        for (int i = 0; i < size; i++) {
            keys[i] = null; // Tüm anahtarlar başlangıçta null
        }
    }

    // Hash fonksiyonu
    private int hashFunction(String key) {
        return Math.abs(key.hashCode() % size);
    }

    // Anahtar-değer çifti ekleme
    public void insert(String key, int value) {
        int index = hashFunction(key);
        int originalIndex = index; // Sonsuz döngüyü önlemek için başlangıç indeksi saklanır

        while (keys[index] != null) {
            if (keys[index].equals(key)) { // Anahtar zaten varsa, değeri günceller
                values[index] = value;
                return;
            }
            index = (index + 1) % size; // Çakışma varsa bir sonraki indekse bak
            if (index == originalIndex) { // Tablo doluysa
                System.out.println("Tablo dolu, ekleme yapılamıyor.");
                return;
            }
        }

        // Boş bir indeks bulundu, yeni anahtar ve değer eklenir
        keys[index] = key;
        values[index] = value;
    }

    // Anahtar-değer çifti silme
    public void delete(String key) {
        int index = hashFunction(key);
        int originalIndex = index;

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                keys[index] = null; // Anahtar silinir
                values[index] = 0;  // Değeri sıfır yapar
                System.out.println(key + " tablodan silindi.");
                return;
            }
            index = (index + 1) % size; // Bir sonraki indekse geç
            if (index == originalIndex) { // Tüm tablo dolaşıldıysa
                break;
            }
        }

        System.out.println(key + " tablosunda bulunamadı.");
    }

    // Anahtarın değerini alma
    public Integer get(String key) {
        int index = hashFunction(key);
        int originalIndex = index;

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index]; // Anahtar bulunduğunda değeri döner
            }
            index = (index + 1) % size;
            if (index == originalIndex) { // Tüm tablo dolaşıldıysa
                break;
            }
        }

        return null; // Anahtar bulunamazsa null döner
    }

    // Tüm tablodaki verileri yazdırma
    public void display() {
        for (int i = 0; i < size; i++) {
            if (keys[i] != null) {
                System.out.println("Index " + i + ": [" + keys[i] + ": " + values[i] + "]");
            } else {
                System.out.println("Index " + i + ": [Boş]");
            }
        }
    }
}
