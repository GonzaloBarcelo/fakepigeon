'use strict';

var privateArea = document.querySelector('#privateArea');
var privateForm = document.querySelector('#privateForm');
var receiver = document.querySelector('#receiver');
var privateMessage = document.querySelector('#private');

var sender = null;
var stompClient = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function get() {
    sender = sessionStorage.getItem("usernamePrivate");
    sessionStorage.clear();
}

window.onload = function privateConnect() {
    get();

    if(sender) {
        var socket = new SockJS('/ps');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, privateOnConnected);
    }
}

function privateOnConnected() {
    const address = '/topic/' + sender;
    stompClient.subscribe(address, privateOnMessageReceived);
    stompClient.send("/app/chat.privateInitialLoad",{},JSON.stringify({sender}));
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

privateForm.addEventListener('submit', privateSendMessage, true);