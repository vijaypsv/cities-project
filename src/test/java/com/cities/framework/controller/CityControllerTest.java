package com.cities.framework.controller;

import static com.cities.framework.controller.CityPageAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import com.cities.core.data.CityRepository;

@WebMvcTest(controllers = CityController.class)
class CityControllerTest {

	@MockBean
	private CityRepository cityRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void evaluatesPageableParameter() throws Exception {

		mockMvc.perform(get("/api/cities/queryByPage").param("page", "5").param("size", "10"))
				.andExpect(status().isOk());

		ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
		verify(cityRepository).findAll(pageableCaptor.capture());
		PageRequest pageable = (PageRequest) pageableCaptor.getValue();

		assertThat(pageable).hasPageNumber(5);
		assertThat(pageable).hasPageSize(10);

	}
}
