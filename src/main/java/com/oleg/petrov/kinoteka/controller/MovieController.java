package com.oleg.petrov.kinoteka.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.oleg.petrov.kinoteka.domain.Movie;
import com.oleg.petrov.kinoteka.repository.MovieRepository;

@RestController
@RequestMapping("/movies")
class MovieController {
  private final MovieRepository repository;

  public MovieController(MovieRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  public Movie create(@RequestBody @Valid Movie movie) {
    return repository.save(movie);
  }

  @GetMapping
  public List<Movie> getAll() {
    return repository.findAll();
  }

  @PutMapping("/{id}")
  public Movie update(@PathVariable Long id, @RequestBody @Valid Movie movie) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Запись не найдена");
    }
    movie.setId(id);
    return repository.save(movie);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Запись не найдена");
    }
    repository.deleteById(id);
  }
}