package com.cities.framework.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cities.core.data.CityRepository;
import com.cities.core.domain.City;
import com.cities.core.domain.ExerciseService;
import com.cities.exceptions.CityNotFoundException;
import com.cities.framework.assembler.CityModelAssembler;
import com.cities.framework.model.CityModel;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cities")
public class CityController {

	@GetMapping("/")
	public CollectionModel<EntityModel<City>> findAll() {
		List<EntityModel<City>> cities = cityRepository.findAll().stream().map(cityModelAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(cities, linkTo(methodOn(CityController.class).findAll()).withSelfRel());

	}

	/**
	 * Returns cities with paging. Also accepts a parameter fil filterin by name
	 * 
	 * @param name     name filter
	 * @param pageable paging parameters
	 * @return cities list
	 */
	@GetMapping("/queryByPage")
	public ResponseEntity<CityModel> queryByPage(@RequestParam(required = false, defaultValue = "") String name,
			Pageable pageable) {

		Page<City> cities = cityRepository.findByNameContains(name, pageable);
		if (cities.isEmpty()) {
			return new ResponseEntity<CityModel>(HttpStatus.NO_CONTENT);
		}
		CityModel citiesModel = new CityModel(cities);
		return new ResponseEntity<CityModel>(citiesModel, HttpStatus.OK);
	}

	/**
	 * Returns all the cities with paging
	 * 
	 * @param name     name filter
	 * @param pageable paging parameters
	 * @return Page of {@link com.cities.core.domain.City City}
	 */
	@GetMapping("/search")
	public PagedModel<EntityModel<City>> search(@RequestParam(required = false, defaultValue = "") String name,
			Pageable pageable) {

		Page<City> cities = cityRepository.findByNameContains(name, pageable);
		return pagedResourcesAssembler.toModel(cities, cityModelAssembler);
	}

	/**
	 * Returns a city from its id
	 * 
	 * @param id city id
	 * @return {@link com.cities.core.domain.City City} found
	 */
	@GetMapping("/{id}")
	public EntityModel<City> getCity(@PathVariable Long id) {

		City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));

		return cityModelAssembler.toModel(city);
	}

	/**
	 * Calculates the longest possible sequence of city Id's which form an ascending
	 * list of numbers
	 * 
	 * @return list of cities with ascending ids
	 */
	@GetMapping("/algorithm")
	public ResponseEntity<CityModel> algorithm() {

		List<City> cities = exerciseService.calculate();
		if (cities.isEmpty()) {
			return new ResponseEntity<CityModel>(HttpStatus.NO_CONTENT);
		}
		CityModel citiesModel = new CityModel(cities);
		return new ResponseEntity<CityModel>(citiesModel, HttpStatus.OK);
	}

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private PagedResourcesAssembler<City> pagedResourcesAssembler;

	@Autowired
	private CityModelAssembler cityModelAssembler;

	@Autowired
	private ExerciseService exerciseService;
}
