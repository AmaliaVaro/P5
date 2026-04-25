package edu.comillas.icai.gitt.pat.spring.Practica3.Repositorios;

import edu.comillas.icai.gitt.pat.spring.Practica3.Entidades.LineadeCarrito;
import org.springframework.data.repository.CrudRepository;

public interface RepoLineaCarrito extends CrudRepository<LineadeCarrito, Long> {
    LineadeCarrito findByIdLineaCarrito(Long idLineaCarrito);
}
