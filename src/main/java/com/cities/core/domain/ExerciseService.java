package com.cities.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cities.core.data.CityRepository;

@Service
public class ExerciseService {

	/**
	 * Private class for representing piles for the patience sorting algorithm.
	 */
	private static class Pile<E extends Comparable<? super E>> implements Comparable<Pile<E>> {
		public E value;
		public Pile<E> pointer;

		public int compareTo(Pile<E> y) {
			return value.compareTo(y.value);
		}
	}

	/**
	 * Calculates the longest possible sequence of city Id's which form an ascending
	 * list of numbers
	 * 
	 * @return list of cities with ascending ids
	 */
	public List<City> calculate() {
		List<City> cities = cityRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		return biggestSequence(cities);
	}

	/**
	 * Returns the biggest ascending sequence based on patience sorting algorithm.
	 * <br>
	 * Since it is not necesary to have the whole pile (we only have to compare to
	 * the top of the pile to determine in which pile we must put the current
	 * element), we only keep the top of the pile and a pointer to the top of the
	 * previous pile (this top may change in the future so we keep the pointer re
	 * retrieve the value)
	 * 
	 * @see <a href=
	 *      "https://en.wikipedia.org/wiki/Patience_sorting">https://en.wikipedia.org/wiki/Patience_sorting</a>
	 * 
	 * @param list list of comparable elements
	 * @return biggest sequence
	 */
	private static <E extends Comparable<? super E>> List<E> biggestSequence(List<E> list) {
		List<Pile<E>> pileTops = new ArrayList<Pile<E>>();

		for (E x : list) {
			Pile<E> node = new Pile<E>();
			node.value = x;
			int i = Collections.binarySearch(pileTops, node);
			if (i < 0)
				i = ~i;
			if (i != 0)
				node.pointer = pileTops.get(i - 1);
			if (i != pileTops.size())
				pileTops.set(i, node);
			else
				pileTops.add(node);
		}

		List<E> result = new ArrayList<E>();
		for (Pile<E> node = pileTops.size() == 0 ? null
				: pileTops.get(pileTops.size() - 1); node != null; node = node.pointer)
			result.add(node.value);
		Collections.reverse(result);
		return result;
	}

	@Autowired
	private CityRepository cityRepository;
}
