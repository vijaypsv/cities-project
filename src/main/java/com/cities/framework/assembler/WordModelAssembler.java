package com.cities.framework.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.cities.core.domain.Word;
import com.cities.framework.controller.WordController;

@Component
public class WordModelAssembler implements RepresentationModelAssembler<Word, EntityModel<Word>> {

	@Override
	  public EntityModel<Word> toModel(Word word) {

	    return EntityModel.of(word, //
	    	linkTo(methodOn(WordController.class).findAll()).withRel("words"),
	    	linkTo(methodOn(WordController.class).getWord(word.getId())).withSelfRel());
	  }
}




