package com.cities.framework.controller;

import java.util.Objects;

import org.assertj.core.api.AbstractAssert;
import org.springframework.data.domain.Pageable;

public class CityPageAssert extends AbstractAssert<CityPageAssert, Pageable> {
	CityPageAssert(Pageable pageable) {
		super(pageable, CityPageAssert.class);
	}

	static CityPageAssert assertThat(Pageable actual) {
		return new CityPageAssert(actual);
	}

	CityPageAssert hasPageSize(int expectedPageSize) {
		if (!Objects.equals(actual.getPageSize(), expectedPageSize)) {
			failWithMessage("expected page size <%s> and obtained <%s>", expectedPageSize, actual.getPageSize());
		}
		return this;
	}

	CityPageAssert hasPageNumber(int expectedPageNumber) {
		if (!Objects.equals(actual.getPageNumber(), expectedPageNumber)) {
			failWithMessage("expected page number <%s> and obtained <%s>", expectedPageNumber, actual.getPageNumber());
		}
		return this;
	}
}
