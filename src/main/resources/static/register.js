
async function connect(event){
    event.preventDefault();

}
var input= document.querySelector('#username');
input.onfocus = function(){
try {
    document.querySelector('#username').classList.remove('is-invalid');
} catch (error) {
  console.error(error);
}
}

async function register(event){

    event.preventDefault();
    if(validation() && document.getElementById('password').value.trim()==document.getElementById('passwordRep').value.trim()){
        var user=document.getElementById('username').value.trim();
            var pass=document.getElementById('password').value.trim();

            let res= await fetch("/register",{
                method: 'POST',
                headers: {
                      'Accept': 'application/json',
                      'Content-Type': 'application/json'
                    },
                body:
                    JSON.stringify({
                     username: user,
                     password: pass})
            });

            if (res.status ==200){
                const data= await res.json();
                console.log(data);
                for(var [key,value] of Object.entries(data)){
                    console.log("Printing values...");
                    console.log(key+' value='+value);
                    if (value==false){
                        console.log("usuario en uso");
                        document.querySelector('#username').classList.add('is-invalid');
                        alert("Username in use");
                    }
                    else{
                        document.location="./login.html";
                    }
                }

            }
    }
    else{
        if(validation()){
            document.querySelector('#passwordRep').classList.add('is-invalid');
            alert("Passwords not match.");
        }
        else{
            document.querySelector('#password').classList.add('is-invalid');
            alert("Password must contains upper case, lower case and numbers.");
        }

    }


}

function validation(){
var myInput=document.querySelector('#password');
var lowerCaseLetters = /[a-z]/g;
var upperCaseLetters = /[A-Z]/g;
var numbers = /[0-9]/g;
  if(myInput.value.match(lowerCaseLetters) && myInput.value.match(upperCaseLetters) && (myInput.value.length >= 8) && myInput.value.match(numbers)) {
    console.log("Password is valid");
    return true;
  } else {
     console.log("Password is invalid");
     return false;
  }
}

