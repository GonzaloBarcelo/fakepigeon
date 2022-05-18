'use strict';

var usernamePage = document.querySelector('#entry-page');
var personalPage = document.querySelector('#personal-page');
var loginForm = document.querySelector('#loginForm');
var registerForm = document.querySelector('#registerForm');

var privateArea = document.querySelector('#privateArea');
var privateForm = document.querySelector('#privateForm');
var receiver = document.querySelector('#receiver');
var privateMessage = document.querySelector('#private');

var sender = null;
var stompClient = null;

function privateConnect(event) {
    sender = document.querySelector('#usernameLogin').value.trim();

    if(sender) {
        usernamePage.classList.add('hidden');
        personalPage.classList.remove('hidden');

        var socket = new SockJS('/ps');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, privateOnConnected, privateOnError);
    }
    event.preventDefault();
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

function privateOnConnected() {
    const address = '/topic/' + sender;
    stompClient.subscribe(address, privateOnMessageReceived);
}

function privateOnError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function privateSendMessage(event) {
    var messageContent = privateMessage.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: sender,
            receiver: receiver.value,
            content: privateMessage.value
        };

        stompClient.send("/app/chat.sendPrivateMessage", {}, JSON.stringify(chatMessage));
        privateMessage.value = '';
    }
    event.preventDefault();
}

function privateOnMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');

    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(message.sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(message.sender);

    messageElement.appendChild(avatarElement);
    
    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(message.sender);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    privateArea.appendChild(messageElement);
    privateArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

loginForm.addEventListener('submit', privateConnect, true)
privateForm.addEventListener('submit', privateSendMessage, true)