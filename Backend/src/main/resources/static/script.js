
const URL_API = "http://localhost:8080/api/carritos";
const correoActual = "amalia@test.com"; 


const peticionAñadir = async function(idArticulo, precio, nombreProducto) {
    const nuevaLinea = {
        idArticulo: idArticulo,
        precioUnitario: precio,
        numeroUnidades: 1
    };

    try {
        const respuesta = await fetch(`${URL_API}/${correoActual}/lineaCarrito`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevaLinea)
        });

        if (respuesta.ok) {
            const objeto = await respuesta.json();
            alert(nombreProducto + " añadido al carrito de " + correoActual);
            return objeto;
        } else {
            console.log('Respuesta KO:', respuesta.status);
            throw respuesta;
        }
    } catch (error) {
        console.error('Error inesperado:', error);
        throw error;
    }
}

const cargarCarrito = async function() {

    const idCarrito = 1;
    const contenedorTabla = document.getElementById("contenedor-tabla");
    const precioTotal = document.querySelector(".pagar p")

    try {
        const respuesta = await fetch(`${URL_API}/${idCarrito}`, {
            method: 'GET',
            headers: { 'Accept': 'application/json' }
        });

        if (respuesta.ok) {
            const carrito = await respuesta.json();
            if(carrito.precioTotal === 0 || carrito.precioTotal === null){
                if(contenedorTabla){
                    contenedorTabla.innerHTML = "<div class='vacio'><h3>Tu carrito está vacío</h3><p>Añade productos</p></div>";
                }
                if(precioTotal){
                    precioTotal.innerText = "Precio total: 0€";
                }
            } else{
                if (contenedorTabla) {
                    contenedorTabla.innerHTML = `
                        <div class="resumen-container">
                            <table class="tabla-resumen">
                                <thead>
                                    <tr>
                                        <th>Concepto</th>
                                        <th>Estado</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Artículos seleccionados</td>
                                        <td><span>Listo</span></td>
                                        <td>${carrito.precioTotal}€</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>`;
                }

                if(precioTotal){
                    precioTotal.innerText = `Precio total: ${carrito.precioTotal}€`;
                }
                console.log("Total actualizado en servidor: " + carrito.precioTotal);
            }
        }
    } catch (error) {
        console.error('Error al cargar el carrito:', error);
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const botonesAñadir = document.querySelectorAll(".boton-añadir");
    
    botonesAñadir.forEach(function(boton, index) {
        boton.addEventListener("click", function(evento) {
            const tarjeta = evento.target.closest(".descripcion");
            const nombre = tarjeta.querySelector("h2").innerText;
            const textoPrecio = tarjeta.querySelector(".precio").innerText;
            const precio = parseFloat(textoPrecio.replace("€", ""));
            const idArticulo = index + 1;

            peticionAñadir(idArticulo, precio, nombre);
        });
    });

    if (document.querySelector(".pagar")) {
        cargarCarrito();
    }

    const formulario = document.getElementById("formulario");
    if (formulario) {
        formulario.addEventListener("submit", function(event) {
            event.preventDefault(); // Evita que se envíe solo
            
            let nombreInput = document.getElementById("nombre");
            let errorNombre = document.getElementById("errorNombre");
            let emailInput = document.getElementById("email");
            let errorEmail = document.getElementById("errorEmail");

            // Validar Email
            if (emailInput.value.trim() === "") {
                errorEmail.textContent = "Por favor, introduce tu email.";
                errorEmail.style.display = "block";
            } else if (!validarEmail(emailInput.value)) {
                errorEmail.textContent = "Por favor, introduce un email válido.";
                errorEmail.style.display = "block";
            } else {
                errorEmail.style.display = "none";
            }
        });
    }
});

function validarEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}