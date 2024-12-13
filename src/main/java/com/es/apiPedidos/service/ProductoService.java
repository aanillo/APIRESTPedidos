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

    public ProductoDTO getById(String id) {
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El formato del id es incorrecto");
        }

        Producto producto = productoRepository
                .findById(idL)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado producto con el nombre indicado"));


        ProductoDTO prodDTO = ProductoMapper.entityToDTO(producto);
        return prodDTO;
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


    public ProductoDTO update(String id, ProductoDTO productoDTO) {
        if(productoDTO == null) {
            throw new NotFoundException("El producto no se ha encontrado");
        }

        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El formato del id es incorrecto: debe ser numérico.");
        }

        Producto productoExistente = productoRepository.findById(idL)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el producto con dicha id"));

        String error;

        if (productoDTO.getNombre() != null && !productoDTO.getNombre().isEmpty()) {
            error = ProductoValidate.isValidName(productoDTO.getNombre());
            if (!error.isEmpty()) {
                throw new BadRequestException(error);
            }
            productoExistente.setNombre(productoDTO.getNombre());
        }

        if (productoDTO.getCategoria() != null && !productoDTO.getCategoria().isEmpty()) {
            error = ProductoValidate.isValidCategory(productoDTO.getCategoria());
            if (!error.isEmpty()) {
                throw new BadRequestException(error);
            }
            productoExistente.setCategoria(productoDTO.getCategoria());
        }

        if (productoDTO.getStock() >= 0) {
            error = ProductoValidate.isValidStock(productoDTO.getStock());
            if (!error.isEmpty()) {
                throw new BadRequestException(error);
            }
            productoExistente.setStock(productoDTO.getStock());
        }

        if (productoDTO.getPrecio() > 0) {
            error = ProductoValidate.isValidPrice(productoDTO.getPrecio());
            if (!error.isEmpty()) {
                throw new BadRequestException(error);
            }
            productoExistente.setPrecio(productoDTO.getPrecio());
        }

        Producto productoGuardado =  productoRepository.save(productoExistente);
        return ProductoMapper.entityToDTO(productoGuardado);
    }


    public ProductoDTO delete(String id) {
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El formato del id es incorrecto");
        }

        Producto productoExistente = productoRepository.findById(idL)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        ProductoDTO productoDTO = ProductoMapper.entityToDTO(productoExistente);
        productoRepository.deleteById(idL);
        return productoDTO;
    }
}
