package com.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pizzaria.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
