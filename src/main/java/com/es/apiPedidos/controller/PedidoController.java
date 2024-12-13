package com.es.apiPedidos.controller;

import com.es.apiPedidos.dto.PedidoDTO;
import com.es.apiPedidos.error.exception.BadRequestException;
import com.es.apiPedidos.error.exception.InternalServerError;
import com.es.apiPedidos.error.exception.NoContentException;
import com.es.apiPedidos.error.exception.NotFoundException;
import com.es.apiPedidos.model.Pedido;
import com.es.apiPedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NotContextException;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getById(
            @PathVariable String id
    ) {
        if(id==null) {
            throw new BadRequestException("Id no válido");
        }

        PedidoDTO pedidoDTO = pedidoService.getById(id);
        if(pedidoDTO == null) {
            throw new NotFoundException("Pedido no encontrado");
        }

        return ResponseEntity.ok(pedidoDTO);
    }


    @GetMapping("/")
    public ResponseEntity<List<PedidoDTO>> getAll() {
        List<PedidoDTO> pedidos = pedidoService.getAll();
        if(pedidos.isEmpty()) {
            throw new NoContentException("No hay elementos en la lista");
        }

        return ResponseEntity.ok(pedidos);
    }


    @GetMapping("/{username}")
    public ResponseEntity<List<PedidoDTO>> getByUser(
            @PathVariable String username
    ) {
        List<PedidoDTO> pedidos = pedidoService.getPedidosByUser(username);
        if(pedidos.isEmpty()) {
            throw new NoContentException("No hay elementos en la lista");
        }

        return ResponseEntity.ok(pedidos);
    }


    @PostMapping("/")
    public ResponseEntity<PedidoDTO> insert(
            @RequestBody PedidoDTO pedidoDTO
    ) {
        if(pedidoDTO == null) {
            throw new InternalServerError("El pedido no puede ser nulo");
        }

        PedidoDTO nuevoPedido = pedidoService.insert(pedidoDTO);

        return new ResponseEntity<PedidoDTO>(nuevoPedido, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(
            @PathVariable String id, @RequestBody PedidoDTO pedidoDTO
    ) {
        if(id == null || id.isEmpty() || pedidoDTO == null) {
            throw new BadRequestException("El pedido y su id no pueden ser nulos");
        }

        PedidoDTO pedidoActualizado = pedidoService.update(id, pedidoDTO);

        return ResponseEntity.ok(pedidoActualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoDTO> delete(
            @PathVariable String id
    ) {
        if(id == null || id.isEmpty()) {
            throw new BadRequestException("La id no puede ser nula");
        }

        PedidoDTO pedidoEliminado = pedidoService.delete(id);

        return new ResponseEntity<PedidoDTO>(pedidoEliminado, HttpStatus.NO_CONTENT);
    }
}
