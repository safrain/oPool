package safrain.nodelet.util;

import java.util.Comparator;
import java.util.List;

public class SortUtil {
	public static <T> void mergeSort(List<T> src, List<T> dest, Comparator<T> c) {
		dest.clear();
		dest.addAll(src);
		mergeSort(src, dest, 0, src.size(), 0, c);
	}

	private static <T> void mergeSort(List<T> src, List<T> dest, int low,
			int high, int off, Comparator<T> c) {
		int length = high - low;

		// Insertion sort on smallest arrays
		if (length < 7) {
			for (int i = low; i < high; i++)
				for (int j = i; j > low
						&& c.compare(dest.get(j - 1), dest.get(j)) > 0; j--)
					swap(dest, j, j - 1);
			return;
		}

		// Recursively sort halves of dest into src
		int destLow = low;
		int destHigh = high;
		low += off;
		high += off;
		int mid = (low + high) >>> 1;
		mergeSort(dest, src, low, mid, -off, c);
		mergeSort(dest, src, mid, high, -off, c);

		// If list is already sorted, just copy from src to dest. This is an
		// optimization that results in faster sorts for nearly ordered
		// lists.
		if (c.compare(src.get(mid - 1), src.get(mid)) <= 0) {
			for (int i = low; i < length; i++) {
				dest.set(destLow + i, src.get(i));
			}
			// System.arraycopy(src, low, dest, destLow, length);
			return;
		}

		// Merge sorted halves (now in src) into dest
		for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
			if (q >= high || p < mid && c.compare(src.get(p), src.get(q)) <= 0)
				dest.set(i, src.get(p++));
			else
				dest.set(i, src.get(q++));
		}
	}

	private static <T> void swap(List<T> list, int a, int b) {
		T r = list.get(a);
		list.set(a, list.get(b));
		list.set(b, r);
	}
}
