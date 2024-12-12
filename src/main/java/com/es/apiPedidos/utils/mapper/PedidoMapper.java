package com.es.apiPedidos.utils.mapper;

import com.es.apiPedidos.dto.PedidoDTO;
import com.es.apiPedidos.model.Pedido;
import com.es.apiPedidos.model.Producto;
import com.es.apiPedidos.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public static PedidoDTO entityToDto(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setDestino(pedido.getDestino());
        pedidoDTO.setFechaPedido(pedido.getFechaPedido());
        pedidoDTO.setFechaLlgada(pedido.getFechaLlegada());
        pedidoDTO.setImporte(pedido.getImporte());
        pedidoDTO.setUsuario(pedido.getUsuario() != null ? pedido.getUsuario() : null);
        pedidoDTO.setProductos(pedido.getProductos()
                .stream()
                .map(Producto::getNombre)
                .collect(Collectors.toList()));


        return pedidoDTO;
    }

    public static Pedido DtoToEntity(PedidoDTO pedidoDTO, Usuario usuario, List<Producto> productos) {
        Pedido pedido = new Pedido();
        pedido.setDestino(pedidoDTO.getDestino());
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());
        pedido.setFechaLlegada(pedidoDTO.getFechaLlgada());
        pedido.setImporte(pedidoDTO.getImporte());
        pedido.setUsuario(usuario);
        pedido.setProductos(productos);

        return pedido;
    }
}
