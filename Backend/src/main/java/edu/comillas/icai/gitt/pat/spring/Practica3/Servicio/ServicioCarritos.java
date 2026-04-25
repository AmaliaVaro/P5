package edu.comillas.icai.gitt.pat.spring.Practica3.Servicio;

import edu.comillas.icai.gitt.pat.spring.Practica3.Entidades.Carrito;
import edu.comillas.icai.gitt.pat.spring.Practica3.Entidades.LineadeCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica3.Repositorios.RepoCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica3.Repositorios.RepoLineaCarrito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.Callable;

@Service
public class ServicioCarritos {
    @Autowired
    RepoCarrito repoCarrito;
    @Autowired
    RepoLineaCarrito repoLineaCarrito;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Transactional
    public Carrito creaCarrito(Carrito carritoNuevo){
        if(repoCarrito.findByCorreoUsuario(carritoNuevo.correoUsuario) != null){
            logger.warn("Intento de crear un carrito duplicado para: "+ carritoNuevo.correoUsuario);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Carrito ya existe");
        }

        carritoNuevo.precioTotal = 0.0;
        //guardo el nuevo carrito
        Carrito carritoGuardado = repoCarrito.save(carritoNuevo);

        return carritoGuardado;
    }

    @Transactional
    public Carrito getCarrito(Long idCarrito){
        Carrito carrito = repoCarrito.findById(idCarrito).orElse(null);
        if(carrito == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El carrito no existe");
        }
        return carrito;
    }

    @Transactional
    public LineadeCarrito añadirLinea(LineadeCarrito lineadeCarritoNueva, String correoUsuario){
       Carrito carrito = repoCarrito.findByCorreoUsuario(correoUsuario);

       if(carrito == null){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
       }

       lineadeCarritoNueva.costeLineaArticulo = lineadeCarritoNueva.precioUnitario*lineadeCarritoNueva.numeroUnidades;

       carrito.setPrecioTotal(carrito.getPrecioTotal() + lineadeCarritoNueva.costeLineaArticulo);
       lineadeCarritoNueva.carrito = carrito;

       repoCarrito.save(carrito);
       return repoLineaCarrito.save(lineadeCarritoNueva);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarLineaCarrito(Long idLineaCarrito, String correo) throws Exception{
        Carrito carrito = repoCarrito.findByCorreoUsuario(correo);
        if(carrito == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }

        LineadeCarrito lineaBorrar = repoLineaCarrito.findByIdLineaCarrito(idLineaCarrito);
        if(lineaBorrar == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La línea del carrito no se ha encontrado");
        }
        try {
            carrito.setPrecioTotal(carrito.getPrecioTotal() - lineaBorrar.costeLineaArticulo);
            repoCarrito.save(carrito);
            repoLineaCarrito.delete(lineaBorrar);
        } catch (Exception e){
            throw new Exception(e);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarCarrito(Long idCarrito) throws Exception{
       Carrito carrito = repoCarrito.findById(idCarrito).orElse(null);
       if(carrito == null){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se ha encontrado el carrito");
       }

       try{
           repoCarrito.delete(carrito);
       }catch(Exception e){
           throw  new Exception(e);
       }

    }
}
