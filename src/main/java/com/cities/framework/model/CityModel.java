package com.cities.framework.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.cities.core.domain.City;

public class CityModel {

	public CityModel() {
	}

	public CityModel(Page<City> content) {
		this.content = content.get().collect(Collectors.toList());
		this.totalPages = content.getTotalPages();
		this.totalElements = content.getTotalElements();

	}

	public CityModel(List<City> content) {
		this.content = content;
		this.totalPages = 1;
		this.totalElements = content.size();

	}

	public List<City> getContent() {
		return content;
	}

	public void setContent(List<City> content) {
		this.content = content;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	private List<City> content;
	private long totalPages;
	private long totalElements;
}
