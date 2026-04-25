package edu.comillas.icai.gitt.pat.spring.Practica3.Controlador;

import edu.comillas.icai.gitt.pat.spring.Practica3.Entidades.Carrito;
import edu.comillas.icai.gitt.pat.spring.Practica3.Entidades.LineadeCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica3.Servicio.ServicioCarritos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
//El controlador acepta las peticiones y utiliza el modelo para procesar los datos. Luego los pasa a la vista para generar la respuesta.
@RestController//Estamos indicando que las peticiones y respuestas deben estar en formato JSON.
public class CarritoControlador {
    @Autowired
    ServicioCarritos servicioCarritos;

    @PostMapping("/api/carritos")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito creaCarrito(@Valid @RequestBody Carrito carrito) {
        return servicioCarritos.creaCarrito(carrito);
    }

//    @GetMapping("/api/carritos")
//    public Carrito getCarrito(String correo){
//        return servicioCarritos.getCarrito(correo);
//    }

    @GetMapping("/api/carritos/{idCarrito}")
    public Carrito getCarrito(@PathVariable Long idCarrito) {
        return servicioCarritos.getCarrito(idCarrito);
    }

    @PutMapping("/api/carritos/{correoUsuario}/lineaCarrito")
    public LineadeCarrito añadirLineaCarrito(@PathVariable String correoUsuario, @RequestBody LineadeCarrito lineadeCarrito){
        return servicioCarritos.añadirLinea(lineadeCarrito, correoUsuario);
    }

    @DeleteMapping("/api/carritos/{correoUsuario}/lineaCarrito/{idLineaCarrito}")
    public void eliminarLineaCarrito(@PathVariable Long idLineaCarrito, @PathVariable String correoUsuario) throws Exception{
        try {
            servicioCarritos.eliminarLineaCarrito(idLineaCarrito, correoUsuario);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/api/carritos/{idCarrito}")
    public void eliminarCarrito(@PathVariable Long idCarrito) throws Exception{
        try{
            servicioCarritos.eliminarCarrito(idCarrito);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}
