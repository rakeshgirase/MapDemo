package com.exuberant.maps.lib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rakesh on 10-Jun-2017.
 */

public class MultiMap<K, V> extends HashMap<K, Set<V>> {

    public Set<V> add(K key, V value) {
        Set<V> values = new HashSet<>();
        values.add(value);
        return this.add(key, values);
    }

    public Set<V> add(K key, Set<V> values) {
        Set<V> set;
        if (this.containsKey(key)) {
            set = this.get(key);
        } else {
            set = new HashSet();
        }
        set.addAll(values);
        return super.put(key, set);
    }

}
