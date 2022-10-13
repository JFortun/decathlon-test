package com.example.demo.core.shoe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoeRepository extends JpaRepository<ShoeEntity, Integer> {
}
