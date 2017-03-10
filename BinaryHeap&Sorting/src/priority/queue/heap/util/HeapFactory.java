package priority.queue.heap.util;

import java.util.HashMap;

import priority.queue.heap.Heap;

public class HeapFactory<T extends Comparable<T>, E> {
	
	private static HeapFactory factory
	= new HeapFactory();
	private  HashMap<String, Class<? extends Heap<E,T>>>
	registeredHeaps;

	public HeapFactory() {
		registeredHeaps = new HashMap<>();
	}

	public static HeapFactory getInstance() {
		return factory;
	}

	public void registerHeap(final String key,
			final Class<? extends Heap<E,T>> heapClass) {
		registeredHeaps.put(key, heapClass);
	}

	public Heap getHeap(final String key) {
		try {
			final Class<? extends Heap> heapClass
			= registeredHeaps.get(key);
			return heapClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
}
