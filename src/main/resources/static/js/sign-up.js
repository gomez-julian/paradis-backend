start();
function validarTelefono() {
    var campo = document.getElementById('inputTelefono');
    var valor = campo.value;
    if (valor.match(/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im)) {
        campo.setCustomValidity("");
    } else {
        campo.setCustomValidity("error");
        campo.parentElement.querySelector('.invalid-feedback').innerHTML = "Introduce 10 dígitos";
    }
}
function validarPass() {
    var campo = document.getElementById('inputPass');
    var valor = campo.value;
    if (valor.match(/(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])/)) {
        campo.setCustomValidity("");
    }else{
        campo.setCustomValidity("error");
        campo.parentElement.querySelector('.invalid-feedback').innerHTML = "Necesitas al menos una letra mayúscula, una minúscula y un número";
    }
}
function confirmarPass() {
    var campo1 = document.getElementById('inputPass');
    var valor1 = campo1.value;
    var campo2 = document.getElementById('inputConfirm');
    var valor2 = campo2.value;
    if(valor1===valor2){
        campo2.setCustomValidity("");
    }else{
        campo2.setCustomValidity("error");
        campo2.parentElement.querySelector('.invalid-feedback').innerHTML = "Las contraseñas no coinciden";
    }
}
function start() {
    var formulario = document.getElementById('formulario');
    formulario.addEventListener('submit', function (event) {
        if (!formulario.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
        }
        formulario.classList.add('was-validated')
    }, false)
}