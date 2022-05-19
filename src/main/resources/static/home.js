'use strict';

var username = null;

window.onload=function testSecureEndpoint(){

    var access_token = sessionStorage.getItem("access_token");

    console.log(access_token);
    if(access_token === null) {
        document.location.href="/login.html";
    }
}

function loadGlobalChat() {
    document.location.href = "global.html";
}

function loadPrivateChat() {
    document.location.href = "private.html";
}