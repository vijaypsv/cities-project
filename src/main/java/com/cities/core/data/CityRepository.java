package com.cities.core.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cities.core.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	Page<City> findByNameContains(String name, Pageable pageable);
}