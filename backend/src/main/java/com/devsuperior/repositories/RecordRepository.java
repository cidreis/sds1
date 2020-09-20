package com.devsuperior.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.entities.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{

}
