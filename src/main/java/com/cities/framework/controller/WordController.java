package com.cities.framework.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cities.core.data.WordRepository;
import com.cities.core.domain.Word;
import com.cities.exceptions.WordNotFoundException;
import com.cities.framework.assembler.WordModelAssembler;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/words")
public class WordController {

	@GetMapping("/")
	public CollectionModel<EntityModel<Word>> findAll() {
		List<EntityModel<Word>> words = wordRepository.findAll().stream().map(wordModelAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(words, linkTo(methodOn(WordController.class).findAll()).withSelfRel());

	}

	/**
	 * Returns a word from its id
	 * 
	 * @param id word id
	 * @return {@link com.cities.core.domain.Word Word} found
	 */
	@GetMapping("/{id}")
	public EntityModel<Word> getWord(@PathVariable Long id) {

		Word word = wordRepository.findById(id).orElseThrow(() -> new WordNotFoundException(id));

		return wordModelAssembler.toModel(word);
	}

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	private WordModelAssembler wordModelAssembler;

}
