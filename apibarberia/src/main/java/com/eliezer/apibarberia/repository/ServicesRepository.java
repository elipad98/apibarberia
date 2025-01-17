package com.eliezer.apibarberia.repository;

import com.eliezer.apibarberia.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository <Service,Integer>{
    boolean existsByName(String name);
}
