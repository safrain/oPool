//package safrain.nodelet.util;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class CheapMap<K, V> implements Map<K, V> {
//	private List<Entry<K, V>> entrys;
//
//	private int getKeyIndex(Object key) {
//		int size = entrys.size();
//		if (key == null) {
//			for (int i = 0; i < size; i++)
//				if (entrys.get(i).getKey() == null)
//					return i;
//		} else {
//			for (int i = 0; i < size; i++)
//				if (key.equals(entrys.get(i).getKey()))
//					return i;
//		}
//		return -1;
//	}
//
//	private int getValueIndex(Object value) {
//		int size = entrys.size();
//		if (value == null) {
//			for (int i = 0; i < size; i++)
//				if (entrys.get(i).getValue() == null)
//					return i;
//		} else {
//			for (int i = 0; i < size; i++)
//				if (value.equals(entrys.get(i).getValue()))
//					return i;
//		}
//		return -1;
//	}
//
//	@Override
//	public int size() {
//		return entrys.size();
//	}
//
//	@Override
//	public boolean isEmpty() {
//		return entrys.isEmpty();
//	}
//
//	@Override
//	public boolean containsKey(Object key) {
//		return getKeyIndex(key) >= 0;
//	}
//
//	@Override
//	public boolean containsValue(Object value) {
//		return getValueIndex(value) >= 0;
//	}
//
//	@Override
//	public V get(Object key) {
//		int index = getKeyIndex(key);
//		if (index < 0) {
//			return null;
//		}
//		return entrys.get(index).getValue();
//	}
//
//	@Override
//	public V put(K key, V value) {
//		int index = getKeyIndex(key);
//		if (index < 0) {
//			// keys.add(key);
//			// values.add(value);
//			return null;
//		} else {
//			return null;// values.set(index, value);
//		}
//	}
//
//	@Override
//	public V remove(Object key) {
//		// int index = keys.indexOf(key);
//		// if (index < 0) {
//		// return null;
//		// } else {
//		// keys.remove(index);
//		// return values.remove(index);
//		// }
//		return null;
//	}
//
//	@Override
//	public void putAll(Map<? extends K, ? extends V> m) {
//		for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
//			put(entry.getKey(), entry.getValue());
//		}
//
//	}
//
//	@Override
//	public void clear() {
//		// keys.clear();
//		// values.clear();
//	}
//
//	@Override
//	public Set<K> keySet() {
//		return null;
//	}
//
//	@Override
//	public Collection<V> values() {
//		return null;// Collections.unmodifiableList(values);
//	}
//
//	@Override
//	public Set<java.util.Map.Entry<K, V>> entrySet() {
//		return null;
//	}
//
//	public class EntrySet implements Set<Entry<K, V>> {
//
//		@Override
//		public int size() {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//
//		@Override
//		public boolean isEmpty() {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean contains(Object o) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public Iterator<java.util.Map.Entry<K, V>> iterator() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Object[] toArray() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public <T> T[] toArray(T[] a) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public boolean add(java.util.Map.Entry<K, V> e) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean remove(Object o) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean containsAll(Collection<?> c) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean addAll(Collection<? extends java.util.Map.Entry<K, V>> c) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean retainAll(Collection<?> c) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean removeAll(Collection<?> c) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public void clear() {
//			// TODO Auto-generated method stub
//
//		}
//
//	}
//
//}