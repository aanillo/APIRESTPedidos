package com.es.apiPedidos.service;

import com.es.apiPedidos.dto.ProductoDTO;
import com.es.apiPedidos.error.exception.BadRequestException;
import com.es.apiPedidos.error.exception.NotFoundException;
import com.es.apiPedidos.utils.mapper.ProductoMapper;
import com.es.apiPedidos.model.Producto;
import com.es.apiPedidos.repository.ProductoRepository;
import com.es.apiPedidos.utils.validate.ProductoValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoDTO insert(ProductoDTO productoDTO) {
        Producto producto = ProductoMapper.DTOToEntity(productoDTO);

        String error;

        error = ProductoValidate.isValidName(productoDTO.getNombre());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = ProductoValidate.isValidCategory(productoDTO.getCategoria());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = ProductoValidate.isValidStock(productoDTO.getStock());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = ProductoValidate.isValidPrice(productoDTO.getPrecio());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        productoRepository.save(producto);
        ProductoDTO prodDTO = ProductoMapper.entityToDTO(producto);
        return prodDTO;
    }

    public ProductoDTO getByName(String nombre) {
        Producto producto = productoRepository
                .findByNombre(nombre)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado producto con el nombre indicado"));

        if(producto == null) {
            throw new BadRequestException("El producto no puede ser nulo");
        } else {
            ProductoDTO productoDTO = ProductoMapper.entityToDTO(producto);
            return productoDTO;
        }
    }

    public List<ProductoDTO> getAllProducts() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> prodDtos = new ArrayList<>();

        for(Producto p : productos) {
            ProductoDTO productoDTO = ProductoMapper.entityToDTO(p);
            prodDtos.add(productoDTO);
        }
        return prodDtos;
    }


}
