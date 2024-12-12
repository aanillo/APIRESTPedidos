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
    public ResponseEntity<ProductoDTO> getByName(
            @PathVariable String nombre
    ) {
        if(nombre == null) {
            throw new BadRequestException("Nombre no válido");
        }

        ProductoDTO productoDTO = productoService.getByName(nombre);

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
}
