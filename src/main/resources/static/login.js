async function login(event){
    event.preventDefault();
    console.log("Realizando login");
    var username=document.getElementById('username').value.trim();
    var pass=document.getElementById('password').value.trim();

    let res= await fetch("/api/login",{
        method: 'POST',
        headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
        body:
            JSON.stringify({
             user: username,
             password: pass})
    });

    if (res.status ==200){
        const data= await res.json();
        console.log(data);
        sessionStorage.setItem("username",username);
        
        for(var [key,value] of Object.entries(data)){
            sessionStorage.setItem("access_token",value);
        }
        document.location.href="./home.html";
    }
    else{
        document.querySelector('#alertDiv').style.visibility='visible';

        document.querySelector('#alertDiv').classList.add("tada");
        document.getElementById('username').innerHTML="";
        document.getElementById('password').innerHTML="";
    }
}

