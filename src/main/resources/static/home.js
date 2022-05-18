'use strict';

joinGlobalChat = document.querySelector("#joinGlobal");
joinPrivateChat = document.querySelector("#joinPrivate");

username = null;

// ESTA FUNCION ES LA ENCARGADA DE LEER LA COOKIE. LA LIMPIA DESPUES DE LEERLA

window.onload = function onSuccessfulLogin() {
    username = sessionStorage.getItem("usernameLogin");
    sessionStorage.clear();
}

// ESTAS FUNCIONES GENERAN NUEVAS COOKIES EN FUNCION DE QUE CHAT SE ACTIVE

function loadGlobalChat() {
    let username = document.querySelector('#name').value.trim();
    sessionStorage.setItem("usernameGlobal",username);
    window.open("global.html");

}

function loadPrivateChat() {
    let sender = document.querySelector('#usernameLogin').value.trim();
    sessionStorage.setItem("usernamePrivate",sender);
    window.open("private.html");
    
}

joinGlobalChat.addEventListener('submit', loadGlobalChat, true);
joinPrivateChat.addEventListener('submit', loadPrivateChat, true);