/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function mostrarConfirmacion(event) {
    event.preventDefault(); // Detiene el envío del formulario

    var nombre = document.getElementById('nombre').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var telefono = document.getElementById('telefono').value;
    var fecha = document.getElementById('fecha').value;

    if (nombre.trim() === "") {
        mostrarAlerta("El nombre del cliente es obligatorio.");
        return false;
    }
    if (email.trim() === "") {
        mostrarAlerta("El email de cliente es obligatorio.");
        return false;
    }
    if (password.trim() === "") {
        mostrarAlerta("La contraseña de cliente es obligatoria.");
        return false;
    }
    if (telefono.trim() === "") {
        mostrarAlerta("El teléfono del cliente es obligatorio.");
        return false;
    }
    if (!fecha) {
        mostrarAlerta("La fecha de nacimiento del cliente es obligatoria.");
        return false;
    }

    // Si todo es válido, se envía el formulario
    event.target.submit();
}

function mostrarAlerta(mensaje, tipo = 'error') {
    const alerta = document.getElementById('alertaCampos');
    alerta.innerText = mensaje;

    alerta.className = 'alerta';
    alerta.classList.add(tipo === 'error' ? 'alerta-error' : 'alerta-exito');
    alerta.style.display = 'block';

    setTimeout(() => {
        alerta.style.display = 'none';
    }, 4000);
}

