package com.es.apiPedidos.utils.mapper;

import com.es.apiPedidos.dto.ProductoDTO;
import com.es.apiPedidos.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public static ProductoDTO entityToDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setCategoria(producto.getCategoria());
        productoDTO.setStock(producto.getStock());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setDescripcion(producto.getDescripcion());
        return productoDTO;
    }

    public static Producto DTOToEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setStock(productoDTO.getStock());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setDescripcion(productoDTO.getDescripcion());
        return producto;
    }

}
