package com.es.apiPedidos.controller;

import com.es.apiPedidos.dto.ProductoDTO;
import com.es.apiPedidos.error.exception.BadRequestException;
import com.es.apiPedidos.error.exception.InternalServerError;
import com.es.apiPedidos.error.exception.NotFoundException;
import com.es.apiPedidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("")
    public ResponseEntity<ProductoDTO> insert(
            @RequestBody ProductoDTO productoDTO
    ) {
        if(productoDTO == null) {
            throw new NotFoundException("El producto no puede ser nulo");
        }

        ProductoDTO nuevoProducto = productoService.insert(productoDTO);

        if(nuevoProducto == null) {
            throw new InternalServerError("Fallo del servidor");
        }

        return new ResponseEntity<ProductoDTO>(nuevoProducto, HttpStatus.CREATED);
    }


    @GetMapping("/{nombre}")
    public ResponseEntity<ProductoDTO> getById(
            @PathVariable String id
    ) {
        if(id == null) {
            throw new BadRequestException("Id no válida");
        }

        ProductoDTO productoDTO = productoService.getById(id);

        if(productoDTO == null) {
            throw new NotFoundException("Producto no encontrado");
        }

        return ResponseEntity.ok(productoDTO);
    }


    @GetMapping("/")
    public ResponseEntity<List<ProductoDTO>> getAll() {
        List<ProductoDTO> productos = productoService.getAllProducts();
        if(productos.isEmpty()) {
            throw new NotFoundException("No se han encontrado elementos");
        }
        return ResponseEntity.ok(productos);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(
            @PathVariable String id, @RequestBody ProductoDTO productoDTO
    ) {
        if(id == null || id.isEmpty()) {
            throw new BadRequestException("El producto y su id no pueden ser nulos");
        }

        ProductoDTO productoActualizado = productoService.update(id, productoDTO);

        if(productoActualizado == null) {
            throw new NotFoundException("Producto no encontrado");
        }

        return ResponseEntity.ok(productoActualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDTO> delete(
        @PathVariable String id
    ) {
        if(id == null || id.isEmpty()) {
            throw new BadRequestException("La id del producto no puede ser nula");
        }

        ProductoDTO productoEliminado = productoService.delete(id);

        if(productoEliminado == null) {
            throw new NotFoundException("Producto no encontrado");
        }

        return new ResponseEntity<ProductoDTO>(productoEliminado, HttpStatus.NO_CONTENT);
    }
}
