document.addEventListener('DOMContentLoaded', function () {
    let nombreInput = document.getElementById('nombre');
    let apellidosInput = document.getElementById('apellidos');
    let telefonoInput = document.getElementById('telefono');
    let passwordInput = document.getElementById('password');
    let confirmPasswordInput = document.getElementById('password-confirm');
    let emailInput = document.getElementById('email');
    let nifInput = document.getElementById('nif');
    let direccionInput = document.getElementById('direccion');
    let cpInput = document.getElementById('cp');
    let localidadInput = document.getElementById('localidad');
    let provinciaInput = document.getElementById('provincia');
    let condicionesCheckbox = document.getElementById('condiciones');
    let registrarButton = document.getElementById('registrar');

    nombreInput.addEventListener('blur', validarNombre);
    apellidosInput.addEventListener('blur', validarApellidos);
    telefonoInput.addEventListener('blur', validarTelefono);
    passwordInput.addEventListener('blur', validarPassword);
    confirmPasswordInput.addEventListener('blur', validarConfirmPassword);
    emailInput.addEventListener('blur', validarEmail);
    nifInput.addEventListener('blur', validarNIF);
    direccionInput.addEventListener('blur', validarDireccion);
    cpInput.addEventListener('blur', validarCP);
    localidadInput.addEventListener('blur', validarLocalidad);
    provinciaInput.addEventListener('blur', validarProvincia);
    condicionesCheckbox.addEventListener('change', validarFormulario);

    function validarNombre() {
        let nombre = nombreInput.value.trim();
        let regex = /^[A-Za-z\s]{1,20}$/;

        if (regex.test(nombre)) {
            nombreInput.style.borderColor = 'green';
            return true;
        } else {
            nombreInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarApellidos() {
        let apellidos = apellidosInput.value.trim();
        let regex = /^\S.*$/;

        if (regex.test(apellidos)) {
            apellidosInput.style.borderColor = 'green';
            return true;
        } else {
            apellidosInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarTelefono() {
        let telefono = telefonoInput.value.trim();
        let regex = /^\d{9}$/;

        if (regex.test(telefono)) {
            telefonoInput.style.borderColor = 'green';
            return true;
        } else {
            telefonoInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarPassword() {
        let password = passwordInput.value;
        let regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,}$/;

        if (regex.test(password)) {
            passwordInput.style.borderColor = 'green';
            return true;
        } else {
            passwordInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarConfirmPassword() {
        let confirmPassword = confirmPasswordInput.value;
        let password = passwordInput.value;

        if (confirmPassword === password) {
            confirmPasswordInput.style.borderColor = 'green';
            return true;
        } else {
            confirmPasswordInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarEmail() {
        let email = emailInput.value.trim();
        let regex = /^[^\s@]+@[^\s@]+\.[a-zA-Z]{2,}$/;

        if (regex.test(email)) {
            emailInput.style.borderColor = 'green';
            return true;
        } else {
            emailInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarNIF() {
        let nif = nifInput.value.trim();
        let regex = /\S/;

        if (regex.test(nif)) {
            nifInput.style.borderColor = 'green';
            return true;
        } else {
            nifInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarDireccion() {
        let direccion = direccionInput.value.trim();
        let regex = /\S/;

        if (regex.test(direccion)) {
            direccionInput.style.borderColor = 'green';
            return true;
        } else {
            direccionInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarCP() {
        let cp = cpInput.value.trim();
        let regex = /\S/;

        if (regex.test(cp)) {
            cpInput.style.borderColor = 'green';
            return true;
        } else {
            cpInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarLocalidad() {
        let localidad = localidadInput.value.trim();
        let regex = /\S/;

        if (regex.test(localidad)) {
            localidadInput.style.borderColor = 'green';
            return true;
        } else {
            localidadInput.style.borderColor = 'red';
            return false;
        }
    }

    function validarProvincia() {
        let provincia = provinciaInput.value.trim();
        let regex = /\S/;

        if (regex.test(provincia)) {
            provinciaInput.style.borderColor = 'green';
            return true;
        } else {
            provinciaInput.style.borderColor = 'red';
            return false;
        }
    }



    function validarFormulario() {
        let nombreValido = validarNombre();
        let apellidosValido = validarApellidos();
        let telefonoValido = validarTelefono();
        let passwordValido = validarPassword();
        let confirmPasswordValido = validarConfirmPassword();
        let emailValido = validarEmail();
        let nifValido = validarNIF();
        let direccionValido = validarDireccion();
        let CPValido = validarCP();
        let localidadValido = validarLocalidad();
        let provinciaValido = validarProvincia();

        if (provinciaValido && localidadValido && CPValido && direccionValido && nifValido && nombreValido && apellidosValido && telefonoValido && passwordValido && confirmPasswordValido && emailValido && condicionesCheckbox.checked) {
            registrarButton.disabled = false;
        } else {
            registrarButton.disabled = true;
        }
    }

});
