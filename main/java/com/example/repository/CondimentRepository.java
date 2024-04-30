package com.example.repository;

import com.example.entity.Condiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondimentRepository extends JpaRepository<Condiment, Long> {

}
