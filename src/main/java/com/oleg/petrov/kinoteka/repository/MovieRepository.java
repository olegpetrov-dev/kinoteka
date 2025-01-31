package com.oleg.petrov.kinoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oleg.petrov.kinoteka.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
