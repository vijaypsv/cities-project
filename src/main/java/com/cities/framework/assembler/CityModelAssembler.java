package com.cities.framework.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.cities.core.domain.City;
import com.cities.framework.controller.CityController;

@Component
public class CityModelAssembler implements RepresentationModelAssembler<City, EntityModel<City>> {

	@Override
	  public EntityModel<City> toModel(City city) {

	    return EntityModel.of(city, //
	    	linkTo(methodOn(CityController.class).findAll()).withRel("cities"),
	    	linkTo(methodOn(CityController.class).getCity(city.getId())).withSelfRel());
	  }
}




