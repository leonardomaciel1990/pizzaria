package com.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pizzaria.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
