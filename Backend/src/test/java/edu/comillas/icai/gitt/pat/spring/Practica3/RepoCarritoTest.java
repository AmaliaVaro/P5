package edu.comillas.icai.gitt.pat.spring.Practica3;

import edu.comillas.icai.gitt.pat.spring.Practica3.Entidades.Carrito;
import edu.comillas.icai.gitt.pat.spring.Practica3.Repositorios.RepoCarrito;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class RepoCarritoTest {
    @Autowired
    private RepoCarrito repoCarrito;

    @Test
    void jpaTest(){
        Carrito carrito = new Carrito();
        carrito.idUsuario = 1L;
        carrito.precioTotal = 0.0;


        DataIntegrityViolationException error = null;
        try{
            repoCarrito.save(carrito);
        } catch(DataIntegrityViolationException e){
            error = e;
        }
        assertNotNull(error);
    }
}
