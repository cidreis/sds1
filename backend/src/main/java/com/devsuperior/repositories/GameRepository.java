package com.devsuperior.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

}
