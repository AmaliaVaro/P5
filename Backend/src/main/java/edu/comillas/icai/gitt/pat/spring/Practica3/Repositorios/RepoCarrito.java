package edu.comillas.icai.gitt.pat.spring.Practica3.Repositorios;

import edu.comillas.icai.gitt.pat.spring.Practica3.Entidades.Carrito;
import org.springframework.data.repository.CrudRepository;

public interface RepoCarrito extends CrudRepository<Carrito, Long> {
    Carrito findByCorreoUsuario(String correoUsuario);
}
