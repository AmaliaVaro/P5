# Práctica 3
En la siguiente tabla podrán encontrar una descripción de cada endpoint usado en la práctica.
Se ha usado como base la práctica 2 entregada previamente.

| **Método** | **Ruta** | **Cuerpo** | **Descripción** | **Posible respuesta** |
|------------|----------|------------|-----------------|-----------------------|
| POST       | /api/carritos | Las propiedades de la clase carrito en formato JSON: idCarrito, idArticulo, descripcion, unidades y precioFinal | Crea un carrito con el id del artículo, la descripción, las unidades y su precio final | 201 Created Te devuelve el carrito que has creado junto a los carritos creados anteriormente |
| GET        | /api/carritos/{idCarrito} | No piden un cuerpo | Devuelve el valor del carrito indicado | 200 OK Te enseña el carrito indicado |
| PUT        | /api/carritos/{correoUsuario}/lineaCarrito | Las propiedades de la entidad linea carrito en formato JSON | Actualiza el valor del carrito indicado, añadiendo una linea nueva de producto | 200 OK Te devuelve el carrito con su modificación y también devuelve el resto de carritos |
| DELETE      | /api/carritos/{idCarrito} | No piden un cuerpo | Elimina el carrito indicado | 200 OK Te enseña los carritos que quedan y en caso de que no quede ninguno una lista vacía |
|DELETE | /api/carritos/{correoUsuario}/lineaCarrito/{idLineaCarrito} | No pide un cuerpo | Elimina la linea del carrito indicada, además actualiza el precio final del carrito | 200 OK |

Además se han implementado validaciones como @NotBlank, haciendo la descripción del artículo obligatoria y @Min para no tener ningún carrito con 0 unidades. 
