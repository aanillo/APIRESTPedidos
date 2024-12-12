package com.es.apiPedidos.service;

import com.es.apiPedidos.dto.PedidoDTO;
import com.es.apiPedidos.error.exception.BadRequestException;
import com.es.apiPedidos.error.exception.NotFoundException;
import com.es.apiPedidos.model.Pedido;
import com.es.apiPedidos.model.Producto;
import com.es.apiPedidos.model.Usuario;
import com.es.apiPedidos.repository.PedidoRepository;
import com.es.apiPedidos.repository.ProductoRepository;
import com.es.apiPedidos.repository.UsuarioRepository;
import com.es.apiPedidos.utils.mapper.PedidoMapper;
import com.es.apiPedidos.utils.validate.PedidoValidate;
import com.es.apiPedidos.utils.validate.ProductoValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public PedidoDTO insert(PedidoDTO pedidoDTO) {
        Usuario usuario = usuarioRepository.findByUsername(pedidoDTO.getUsuario().getUsername())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        List<Producto> productos = pedidoDTO.getProductos()
                .stream()
                .map(producto -> productoRepository.findById(Long.valueOf(producto))
                        .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + producto)))
                .toList();

        String error;

        error = PedidoValidate.isValidDestiny(pedidoDTO.getDestino());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = PedidoValidate.isValidDate(pedidoDTO.getFechaPedido());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = PedidoValidate.isValidArriveDate(pedidoDTO.getFechaLlgada());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = PedidoValidate.isValidAmount(pedidoDTO.getImporte());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        Pedido pedido = PedidoMapper.DtoToEntity(pedidoDTO, usuario, productos);
        pedidoRepository.save(pedido);
        PedidoDTO pedDto = PedidoMapper.entityToDto(pedido);
        return pedDto;
    }


    public PedidoDTO getById(String id) {
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El formato del id es incorrecto");
        }

        Pedido pedido = pedidoRepository.findById(idL)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el pedido"));

        PedidoDTO pedidoDTO = PedidoMapper.entityToDto(pedido);
        return pedidoDTO;
    }


    public List<PedidoDTO> getAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedDtos = new ArrayList<>();
        for(Pedido p: pedidos) {
            PedidoDTO pedidoDTO = PedidoMapper.entityToDto(p);
            pedDtos.add(pedidoDTO);
        }
        return pedDtos;
    }


    public List<PedidoDTO> getPedidosByUser(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));


        List<Pedido> pedidos = pedidoRepository.findByUsuario(usuario).stream().toList();


        List<PedidoDTO> pedidosDTO = pedidos.stream()
                .map(PedidoMapper::entityToDto)
                .collect(Collectors.toList());

        return pedidosDTO;
    }

}
