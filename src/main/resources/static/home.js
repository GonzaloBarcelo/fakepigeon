'use strict';

var joinGlobalChat = document.querySelector("#joinGlobalChat");
var joinPrivateChat = document.querySelector("#joinPrivateChat");

var username = null;

// ESTA FUNCION ES LA ENCARGADA DE LEER LA COOKIE. LA LIMPIA DESPUES DE LEERLA

window.onload = function onSuccessfulLogin() {
    username = sessionStorage.getItem("usernameLogin");
    sessionStorage.clear();
}

window.onload=function testSecureEndpoint(){

    var access_token = localStorage.getItem("access_token");
    console.log(access_token);
    if(access_token === null) {
        document.location.href="/login.html";
    }

    console.log("Connecting with a secure endpoint");
    var headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + localStorage.getItem("access_token")
            };
    fetch("/secure", {
            method: 'GET',
            headers: headers
        })
        .then(data => {
            if(data.status == 401) {
                alert("No tienes suficientes permisos");
            }
            console.log(data)

            console.log("End Connecting with a secure endpoint");
        });
}

// ESTAS FUNCIONES GENERAN NUEVAS COOKIES EN FUNCION DE QUE CHAT SE ACTIVE

function loadGlobalChat() {
    sessionStorage.setItem("usernameGlobal",username);
    window.open("global.html");

}

function loadPrivateChat() {
    sessionStorage.setItem("usernamePrivate",username);
    window.open("private.html");
    
}

joinGlobalChat.addEventListener('submit', loadGlobalChat, true);
joinPrivateChat.addEventListener('submit', loadPrivateChat, true);