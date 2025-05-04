/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function mostrarConfirmacion(event) {
    event.preventDefault();

    var nombre = document.getElementById('nombre').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var telefono = document.getElementById('telefono').value;

    if (nombre.trim() === "") {
        mostrarAlerta("El nombre del producto es obligatorio.");
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
        mostrarAlerta("El telefono del cliente es obligatorio.");
        return false;
    }

    document.getElementById('confirmModal').style.display = 'flex';
}

function mostrarAlerta(mensaje, tipo = 'error') {
    const alerta = document.getElementById('alertaCampos');
    alerta.innerText = mensaje;

    alerta.className = 'alerta';

    if (tipo === 'error') {
        alerta.classList.add('alerta-error');
    } else if (tipo === 'exito') {
        alerta.classList.add('alerta-exito');
    }

    alerta.style.display = 'block';

    setTimeout(() => {
        alerta.style.display = 'none';
    }, 8000);
}


