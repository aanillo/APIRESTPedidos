package com.es.apiPedidos.utils.mapper;

import com.es.apiPedidos.dto.UsuarioDTO;
import com.es.apiPedidos.dto.UsuarioRegisterDTO;
import com.es.apiPedidos.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public static UsuarioDTO entityToDto(Usuario usuario) {

        String[] roles = usuario.getRoles().split(",");
        return new UsuarioDTO(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getDireccion(),
                roles
        );
    }

    public static UsuarioRegisterDTO entityToRegisterDto(Usuario usuario) {
        return new UsuarioRegisterDTO(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getDireccion(),
                usuario.getRoles()
        );
    }
}
