async function login(event){
    event.preventDefault();
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
        for(var [key,value] of Object.entries(data)){
            console.log("Printing values...");
            console.log(key+' value='+value);
            if (value==false){
                console.log("pasa");
                document.querySelector('#alertDiv').style.visibility='visible'
                document.querySelector('#saltos').style.visibility="hidden";

            }
        }

    }


}