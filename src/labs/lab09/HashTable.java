package labs.lab09;

public class HashTable<V> {
    public static final int DEF_MAX_HASH_SIZE = 10;
    private final DLinkedList<V>[] hashList;

    /**
     * c'tor
     * construct a hash-table of max-size "DEF_MAX_HASH_SIZE".
     */
    public HashTable() {
        this(DEF_MAX_HASH_SIZE);
    }

    /**
     * construct a hash-table of size 'hashSize'.
     *
     * @param hashSize, the size of the constructed hash-table.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public HashTable(int hashSize) {
        hashList = new DLinkedList[hashSize];
        for (int i = 0; i < hashSize; i++) {
            hashList[i] = new DLinkedList<V>();
        }
    }

    /**
     * @param val
     * @return true if the hash-table contains val, otherwise return false
     */
    public boolean contains(V val) {
        DLinkedList<V> linkedList = getLinkedListByVal(val);
        linkedList.goToBeginning();
        if (linkedList.remove(val) != null) {
            linkedList.insert(val);
            return true;
        }
        return false;
    }

    /**
     * Add val to the hash-table.
     *
     * @param val
     * @return If the val has already exist in the the hash-table, it will not be
     * added again. Return true if the val was added successfully. Otherwise
     * return false.
     */
    public boolean add(V val) {
        if (contains(val)) {
            return false;
        }
        DLinkedList<V> linkedList = getLinkedListByVal(val);
        linkedList.goToBeginning();
        linkedList.insert(val);
        return true;
    }

    /**
     * Remove val from the hash-table.
     *
     * @param val
     * @return Return true if the val was removed successfully. Otherwise return
     * false.
     */
    public boolean remove(V val) {
        DLinkedList<V> linkedList = getLinkedListByVal(val);
        linkedList.goToBeginning();
        return linkedList.remove(val) != null;
    }

    /**
     * clear al the data in the hash-table
     */
    public void clear() {
        for (DLinkedList<V> linkedList : hashList) {
            linkedList.clear();
        }
    }

    /**
     * @return true if the hase-table is empty, otherwise return false.
     */
    public boolean isEmpty() {
        for (DLinkedList<V> linkedList : hashList) {
            if (!linkedList.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private DLinkedList<V> getLinkedListByVal(V value) {
        return hashList[Math.abs(value.hashCode() % hashList.length)];
    }
}