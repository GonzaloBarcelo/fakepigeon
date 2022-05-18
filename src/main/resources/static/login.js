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
        sessionStorage.setItem("usernameLogin",username);
        
        for(var [key,value] of Object.entries(data)){
            sessionStorage.setItem("access_token",value);
            console.log("Printing values...");
            console.log(key+' value='+value);

        }
        document.location.href="./home.html";
    }
    else{
        console.log("pasa");
        document.querySelector('#alertDiv').style.visibility='visible';
        document.querySelector('#saltos').style.visibility="hidden";
        document.getElementById('username').innerHTML="";
        document.getElementById('password').innerHTML="";
    }
}

