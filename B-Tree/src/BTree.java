class BTree {
    BTreeNode root; // B-Tree'nin kök düğümü
    int t; // B-Tree'nin minimum derecesi (yani 2 * t - 1 anahtar)

    // BTree yapıcı metod (constructor)
    BTree(int t) {
        this.t = t;
        this.root = new BTreeNode(t, true); // Başlangıçta kök yaprak düğüm olarak oluşturuluyor
    }

    // Anahtar ekleme fonksiyonu
    void insert(int key) {
        // Eğer kök düğüm doluysa, ağacı bölelim
        if (root.keyCount == 2 * t - 1) {
            // Yeni kök oluştur ve eski kökü yeni köke bağla
            BTreeNode newRoot = new BTreeNode(t, false);
            newRoot.children[0] = root;
            // Kökü bölelim
            splitChild(newRoot, 0, root);
            root = newRoot; // Kökü güncelle
        }
        // Kök dışında bir düğüme anahtar ekleme
        insertNonFull(root, key);
    }

    // B-Tree'deki belirli bir çocuğa anahtar eklemek için yardımcı fonksiyon
    private void insertNonFull(BTreeNode node, int key) {
        int i = node.keyCount - 1;

        // Eğer düğüm yapraksa
        if (node.isLeaf) {
            // Anahtarı doğru pozisyonda yerleştir
            while (i >= 0 && node.keys[i] > key) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.keyCount++;
        } else {
            // Eğer düğüm yaprak değilse
            while (i >= 0 && node.keys[i] > key) {
                i--;
            }
            i++;

            // Eğer çocuk düğüm doluysa, bölme işlemi yap
            if (node.children[i].keyCount == 2 * t - 1) {
                splitChild(node, i, node.children[i]);

                // Yeni çocuğu tekrar kontrol et
                if (node.keys[i] < key) {
                    i++;
                }
            }
            // Çocuğa anahtarı ekle
            insertNonFull(node.children[i], key);
        }
    }

    // Çocuğu bölme fonksiyonu (B-Tree'nin temel bölme işlemi)
    private void splitChild(BTreeNode parent, int childIndex, BTreeNode child) {
        BTreeNode newChild = new BTreeNode(t, child.isLeaf);
        newChild.keyCount = t - 1;

        // Çocuğun anahtarlarının bir kısmını yeni çocuğa kopyala
        System.arraycopy(child.keys, t, newChild.keys, 0, t - 1);
        if (!child.isLeaf) {
            System.arraycopy(child.children, t, newChild.children, 0, t);
        }
        child.keyCount = t - 1;

        // Parent düğümünde çocuğu ve anahtarı ekle
        System.arraycopy(parent.children, childIndex + 1, parent.children, childIndex + 2, parent.keyCount - childIndex);
        parent.children[childIndex + 1] = newChild;
        System.arraycopy(parent.keys, childIndex, parent.keys, childIndex + 1, parent.keyCount - childIndex);
        parent.keys[childIndex] = child.keys[t - 1];
        parent.keyCount++;
    }

    // Anahtar silme fonksiyonu
    void delete(int key) {
        deleteRecursive(root, key);
    }

    // Rekürsif olarak silme işlemini gerçekleştiren fonksiyon
    private void deleteRecursive(BTreeNode node, int key) {
        int index = findKey(node, key);

        // Anahtar düğümde var mı?
        if (index < node.keyCount && node.keys[index] == key) {
            // Eğer yapraksa, sadece sil
            if (node.isLeaf) {
                for (int i = index; i < node.keyCount - 1; i++) {
                    node.keys[i] = node.keys[i + 1];
                }
                node.keyCount--;
            } else {
                // Eğer yaprak değilse, daha karmaşık bir silme işlemi
                BTreeNode predNode = node.children[index];
                BTreeNode succNode = node.children[index + 1];

                if (predNode.keyCount >= t) {
                    // Önceki düğümdeki anahtar en büyükse, onu al
                    int pred = getPredecessor(node, index);
                    node.keys[index] = pred;
                    deleteRecursive(predNode, pred);
                } else if (succNode.keyCount >= t) {
                    // Sonraki düğümdeki anahtar en küçükse, onu al
                    int succ = getSuccessor(node, index);
                    node.keys[index] = succ;
                    deleteRecursive(succNode, succ);
                } else {
                    // Hem önceki hem de sonraki düğümdeki anahtarlar yetersizse, düğümleri birleştir
                    merge(node, index);
                    deleteRecursive(predNode, key);
                }
            }
        } else {
            // Anahtar düğümde yoksa, doğru çocuğa yönlendir
            if (node.isLeaf) {
                System.out.println("Anahtar bulunamadı!");
                return;
            }

            boolean isLastChild = (index == node.keyCount);
            BTreeNode child = node.children[index];
            if (child.keyCount < t) {
                fill(node, index);
            }

            if (isLastChild && index > node.keyCount) {
                deleteRecursive(node.children[index - 1], key);
            } else {
                deleteRecursive(node.children[index], key);
            }
        }
    }

    // Çocuğu doldurma (yetersiz anahtara sahip çocukları düzeltme)
    private void fill(BTreeNode node, int index) {
        if (index > 0 && node.children[index - 1].keyCount >= t) {
            borrowFromPrev(node, index);
        } else if (index < node.keyCount && node.children[index + 1].keyCount >= t) {
            borrowFromNext(node, index);
        } else {
            if (index < node.keyCount) {
                merge(node, index);
            } else {
                merge(node, index - 1);
            }
        }
    }

    // Önceden çocuk düğümden borç alma
    private void borrowFromPrev(BTreeNode node, int index) {
        BTreeNode child = node.children[index];
        BTreeNode sibling = node.children[index - 1];

        for (int i = child.keyCount - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
        }

        if (!child.isLeaf) {
            for (int i = child.keyCount; i >= 0; i--) {
                child.children[i + 1] = child.children[i];
            }
        }

        child.keys[0] = node.keys[index - 1];
        if (!child.isLeaf) {
            child.children[0] = sibling.children[sibling.keyCount];
        }

        node.keys[index - 1] = sibling.keys[sibling.keyCount - 1];
        child.keyCount++;
        sibling.keyCount--;
    }

    // Sonraki çocuk düğümden borç alma
    private void borrowFromNext(BTreeNode node, int index) {
        BTreeNode child = node.children[index];
        BTreeNode sibling = node.children[index + 1];

        child.keys[child.keyCount] = node.keys[index];
        if (!child.isLeaf) {
            child.children[child.keyCount + 1] = sibling.children[0];
        }

        node.keys[index] = sibling.keys[0];

        for (int i = 1; i < sibling.keyCount; i++) {
            sibling.keys[i - 1] = sibling.keys[i];
        }

        if (!sibling.isLeaf) {
            for (int i = 1; i <= sibling.keyCount; i++) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }

        child.keyCount++;
        sibling.keyCount--;
    }

    // Düğümleri birleştirme işlemi
    private void merge(BTreeNode node, int index) {
        BTreeNode child = node.children[index];
        BTreeNode sibling = node.children[index + 1];

        child.keys[t - 1] = node.keys[index];
        for (int i = 0; i < sibling.keyCount; i++) {
            child.keys[i + t] = sibling.keys[i];
        }

        if (!child.isLeaf) {
            for (int i = 0; i <= sibling.keyCount; i++) {
                child.children[i + t] = sibling.children[i];
            }
        }

        for (int i = index + 1; i < node.keyCount; i++) {
            node.keys[i - 1] = node.keys[i];
        }

        for (int i = index + 2; i <= node.keyCount; i++) {
            node.children[i - 1] = node.children[i];
        }

        child.keyCount += sibling.keyCount + 1;
        node.keyCount--;
    }

    // Düğümdeki anahtar konumunu bulma
    private int findKey(BTreeNode node, int key) {
        int index = 0;
        while (index < node.keyCount && node.keys[index] < key) {
            index++;
        }
        return index;
    }

    // Anahtarın önceki öğesini bulma
    private int getPredecessor(BTreeNode node, int index) {
        BTreeNode current = node.children[index];
        while (!current.isLeaf) {
            current = current.children[current.keyCount];
        }
        return current.keys[current.keyCount - 1];
    }

    // Anahtarın sonraki öğesini bulma
    private int getSuccessor(BTreeNode node, int index) {
        BTreeNode current = node.children[index + 1];
        while (!current.isLeaf) {
            current = current.children[0];
        }
        return current.keys[0];
    }

    // Arama fonksiyonu
    boolean search(int key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(BTreeNode node, int key) {
        int i = 0;
        while (i < node.keyCount && key > node.keys[i]) {
            i++;
        }
        if (i < node.keyCount && node.keys[i] == key) {
            return true; // Anahtar bulundu
        }
        if (node.isLeaf) {
            return false; // Anahtar bulunamadı
        }
        return searchRecursive(node.children[i], key); // Çocuğa git
    }

    // Ağacı sıralı olarak yazdırma
    void traverse() {
        traverseRecursive(root);
    }

    private void traverseRecursive(BTreeNode node) {
        for (int i = 0; i < node.keyCount; i++) {
            if (!node.isLeaf) {
                traverseRecursive(node.children[i]);
            }
            System.out.print(node.keys[i] + " ");
        }
        if (!node.isLeaf) {
            traverseRecursive(node.children[node.keyCount]);
        }
    }
}
