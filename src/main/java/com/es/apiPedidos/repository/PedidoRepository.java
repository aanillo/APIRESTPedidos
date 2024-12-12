package com.es.apiPedidos.repository;

import com.es.apiPedidos.model.Pedido;
import com.es.apiPedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByUsuario(Usuario usuario);
}
