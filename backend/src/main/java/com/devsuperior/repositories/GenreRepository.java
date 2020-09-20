package com.devsuperior.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
