package JavaDataStructure.hash.WeakHashMap;

public class JavaWeakHashMap {

    // TODO. 使用WeakHashMap来代表缓存，当键的外部引用过期后，自动从Map中移除，避免内存泄露
    // WeakHashMap<key, value>
    //
    // Hash table based implementation of the Map interface, with weak keys.
    // An entry in a WeakHashMap will automatically be removed when its key is no longer in ordinary use.
    // More precisely, the presence of a mapping for a given key will not prevent the key from being discarded by the garbage collector,
    // that is, made finalizable, finalized, and then reclaimed.
    // When a key has been discarded its entry is effectively removed from the map,
    // so this class behaves somewhat differently from other Map implementations.
}
