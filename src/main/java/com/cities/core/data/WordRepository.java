package com.cities.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cities.core.domain.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

}