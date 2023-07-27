/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function checkEmpty(x) {
    var check = document.getElementById(x).value;
    if (check === "") {

        document.getElementById(x + "Text").style.color = "red";
        document.getElementById("alertempty").innerHTML = "Not be empty!";
        return true;
    } else {
        document.getElementById(x + "Text").style.color = "black";
        document.getElementById("alertempty").innerHTML = "";
    }
}
function checkEmptyAll() {
    var checkForAll = false;
    var check = ["codeV", "email", "confirmPassword", "password"];
    for (var i = 0; i < check.length; i++) {
        if (checkEmpty(check[i]) === true) {
            checkForAll = true;
        }
    }
    if (checkForAll === true) {
        document.getElementById("alert1").innerHTML = "Not be empty!";
    } else {
        if (checkConfirmPassword() === true && validatePassword() === true) {
            document.getElementById("changepass").submit();
        }
    }
}

function checkConfirmPassword() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    if (password !== confirmPassword) {
        document.getElementById("alertconfirm").innerHTML = "Password does not match!";
        document.getElementById("confirmPasswordText").style.color = "red";
        document.getElementById("passwordText").style.color = "red";
        return false;
    }
    return true;
}
function sendCode(x) {
    if (checkEmpty(x) !== true) {
        document.getElementById("changepass").submit();
    }
}
function countdownSendCode(x) {
    const button = document.querySelector('#button');
    const countdown = document.querySelector('#alertcountdown');
    let secondsLeft = x;
    document.querySelector("#button").style.display = "none";
    const intervalId = setInterval(() => {
        secondsLeft--;
        countdown.textContent = "Resend code after: " + secondsLeft + "s";
        if (secondsLeft === 0) {
            clearInterval(intervalId);
            countdown.textContent = '';
            document.querySelector("#button").style.display = "";
            return secondsLeft = x;
        }
    }, 1000);

}
function validatePassword() {
    var password = document.getElementById("password").value;
    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;

    if (!passwordPattern.test(password)) {

        document.getElementById("alertregex").innerHTML = "Must contain at least one  number and one uppercase and lowercase letter,\n\
 and at least 8 or more characters. ";

        return false;
    }
    return true;
}