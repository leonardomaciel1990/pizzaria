package com.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pizzaria.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
