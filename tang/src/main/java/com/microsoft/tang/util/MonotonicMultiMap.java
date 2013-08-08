package com.microsoft.tang.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MonotonicMultiMap<K,V> implements Collection<Entry<K,V>> {
  private final Map<K, Set<V>> map = new MonotonicMap<K, Set<V>>();
  private int size = 0;
  public void put(K key, V v) {
    Set<V> vals = map.get(key);
    if(vals == null) {
      vals = new MonotonicSet<V>();
      map.put(key,vals);
    }
    vals.add(v);
    size++;
  }
  public Set<V> getValuesForKey(K key) {
    Set<V> ret = map.get(key);
    if(ret == null) {
      return new MonotonicSet<V>();
    } else {
      return ret;
    }
  }
  public boolean contains(K key, V v) {
    Set<V> vals = map.get(key);
    if(vals != null) {
      return vals.contains(v);
    }
    return false;
  }
  @Override
  public boolean add(Entry<K, V> e) {
    put(e.getKey(), e.getValue());
    return true;
  }
  @Override
  public boolean addAll(Collection<? extends Entry<K, V>> c) {
    boolean ret = false;
    for(Entry<K,V> e : c) {
      add(e);
      ret = true;
    }
    return ret;
  }
  @Override
  public void clear() {
    throw new UnsupportedOperationException("MonotonicMultiMap cannot be cleared!");
  }
  @SuppressWarnings("unchecked")
  @Override
  public boolean contains(Object o) {
    Entry<?,?> e = (Entry<?,?>)o;
    return contains((K)e.getKey(), (V)e.getValue());
  }
  @Override
  public boolean containsAll(Collection<?> c) {
    for(Object o : c) {
      if(!contains(o)) { return false; }
    }
    return true;
  }
  @Override
  public boolean isEmpty() {
    return size == 0;
  }
  @Override
  public Iterator<Entry<K, V>> iterator() {
    final Iterator<Entry<K, Set<V>>> it = map.entrySet().iterator();
    return new Iterator<Entry<K,V>>() {
      Iterator<V> cur;
      K curKey;
      @Override
      public boolean hasNext() {
        return it.hasNext() || (cur != null && cur.hasNext());
      }

      @Override
      public Entry<K,V> next() {
        if(cur == null) {
          if(it.hasNext()) {
            Entry<K,Set<V>> e = it.next();
            curKey = e.getKey();
            cur = e.getValue().iterator();
          }
        }
        final K k = curKey;
        final V v = cur.next();
        if(!cur.hasNext()) { cur = null; }
        
        return new Entry<K,V>() {

          @Override
          public K getKey() {
            return k;
          }

          @Override
          public V getValue() {
            return v;
          }

          @Override
          public V setValue(V value) {
            throw new UnsupportedOperationException();
          }
          
        };
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
      
    };
  }
  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException("MonotonicMultiMap does not support non-monotonic method remove!");
  }
  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException("MonotonicMultiMap does not support non-monotonic method removeAll!");
  }
  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException("MonotonicMultiMap does not support non-monotonic method retainAll!");
  }
  @Override
  public int size() {
    return size;
  }
  @Override
  public Entry<K,V>[] toArray() {
    throw new UnsupportedOperationException("No toArray() for MonotonicMulitMap (yet)");
  }
  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("No toArray() for MonotonicMulitMap (yet)");
  }
}
