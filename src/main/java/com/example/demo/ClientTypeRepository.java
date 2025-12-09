package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Long> {
    Optional<ClientType> findByName(String name);
    boolean existsByName(String name);
}
