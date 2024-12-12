package com.es.apiPedidos.service;

import com.es.apiPedidos.dto.UsuarioDTO;
import com.es.apiPedidos.dto.UsuarioRegisterDTO;
import com.es.apiPedidos.error.exception.BadRequestException;
import com.es.apiPedidos.error.exception.DuplicateException;
import com.es.apiPedidos.error.exception.NotFoundException;
import com.es.apiPedidos.utils.mapper.UsuarioMapper;
import com.es.apiPedidos.model.Usuario;
import com.es.apiPedidos.repository.UsuarioRepository;
import com.es.apiPedidos.utils.validate.UsuarioValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));


        List<GrantedAuthority> authorities = Arrays.stream(usuario.getRoles().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        UserDetails userDetails = User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRoles())
                .build();

        return userDetails;
    }


    public UsuarioRegisterDTO registerUser(UsuarioRegisterDTO usuarioRegisterDTO) {
        if(usuarioRepository.findByUsername(usuarioRegisterDTO.getUsername()).isPresent()) {
            throw new DuplicateException("El nombre de usuario ya existe");
        }

        if(!usuarioRegisterDTO.getPassword1().equals(usuarioRegisterDTO.getPassword2())) {
            throw new BadRequestException("Ambas contraseñas deben ser iguales");
        }

        String error;

        error = UsuarioValidate.isValidUsername(usuarioRegisterDTO.getUsername());
        if (!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = UsuarioValidate.isValidPassword(usuarioRegisterDTO.getPassword1());
        if(!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = UsuarioValidate.isValidEmail(usuarioRegisterDTO.getEmail());
        if(!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        error = UsuarioValidate.isValidDireccion(usuarioRegisterDTO.getDireccion());
        if(!error.isEmpty()) {
            throw new BadRequestException(error);
        }

        Usuario nuevoUsuario = new Usuario();

        nuevoUsuario.setPassword(passwordEncoder.encode(usuarioRegisterDTO.getPassword1()));
        nuevoUsuario.setUsername(usuarioRegisterDTO.getUsername());
        nuevoUsuario.setEmail(usuarioRegisterDTO.getEmail());
        nuevoUsuario.setDireccion(usuarioRegisterDTO.getDireccion());
        nuevoUsuario.setRoles(usuarioRegisterDTO.getRoles());

        usuarioRepository.save(nuevoUsuario);
        return usuarioRegisterDTO;
    }


    public UsuarioDTO findByName(String nombre) {
        Usuario usuario = usuarioRepository
                .findByUsername(nombre)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        return UsuarioMapper.entityToDto(usuario);
    }

    public List<UsuarioDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usDtos = new ArrayList<>();

        for(Usuario u: usuarios) {
            UsuarioDTO usuarioDTO = UsuarioMapper.entityToDto(u);
            usDtos.add(usuarioDTO);
        }
        return usDtos;
    }
}
