package com.es.apiPedidos.controller;

import com.es.apiPedidos.dto.UsuarioDTO;
import com.es.apiPedidos.dto.UsuarioLoginDTO;
import com.es.apiPedidos.dto.UsuarioRegisterDTO;
import com.es.apiPedidos.error.exception.BadRequestException;
import com.es.apiPedidos.error.exception.GenericInternalException;
import com.es.apiPedidos.error.exception.NotFoundException;
import com.es.apiPedidos.error.exception.UnauthorizedException;
import com.es.apiPedidos.service.TokenService;
import com.es.apiPedidos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(
            @RequestBody UsuarioLoginDTO usuarioLoginDTO
    ) {

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(), usuarioLoginDTO.getPassword())
            );
        } catch (Exception e) {
            System.out.println("Excepcion en authentication");
            throw new NotFoundException("Credenciales del usuario incorrectas");
        }

        String token = "";
        try {
            token = tokenService.generateToken(authentication);
        } catch (Exception e) {
            System.out.println("Excepcion en generar token");
            throw new GenericInternalException("Error al generar el token de autenticación");
        }

        return token;
    }



    @PostMapping("/register")
    public ResponseEntity<UsuarioRegisterDTO> register(
            @RequestBody UsuarioRegisterDTO usuarioRegisterDTO) {

        usuarioService.registerUser(usuarioRegisterDTO);

        return new ResponseEntity<UsuarioRegisterDTO>(usuarioRegisterDTO, HttpStatus.OK);

    }


    @GetMapping("/{username}")
    public ResponseEntity<UsuarioDTO> findByNombre(
            @PathVariable String nombre, Authentication authentication, Principal principal) {


        if(authentication.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.equals(new SimpleGrantedAuthority("ROLE_ADMIN"))) || authentication.getName().equals(nombre)) {
            UsuarioDTO usuarioDTO = usuarioService.findByName(nombre);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } else {
            throw new UnauthorizedException("No tienes los permisos para acceder al recurso");
        }

    }


    @PutMapping("/{username}")
    public ResponseEntity<UsuarioRegisterDTO> update(
        @PathVariable String username, @RequestBody UsuarioRegisterDTO usuarioDTO
    ) {
        if(username == null || username.isEmpty() || usuarioDTO == null) {
            throw new BadRequestException("El username o el usuario son nulos");
        }

        UsuarioRegisterDTO usuarioActualizado = usuarioService.updateUser(username, usuarioDTO);

        if(usuarioActualizado == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        return ResponseEntity.ok(usuarioActualizado);
    }


    @DeleteMapping("/{username}")
    public ResponseEntity<UsuarioDTO> delete(
            @PathVariable String username
    ) {
        if(username == null || username.isEmpty()) {
            throw new BadRequestException("La id del seguro no puede ser nula");
        }

        UsuarioDTO usuarioEliminado = usuarioService.delete(username);

        if(usuarioEliminado == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        return new ResponseEntity<UsuarioDTO>(usuarioEliminado, HttpStatus.NO_CONTENT);
    }
}
