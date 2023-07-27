/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


/* global firebase */

function checkEmpty(x) {
    var check = document.getElementById(x).value;
    if (check === "") {

        document.getElementById(x + "Text").style.color = "red";
        document.getElementById("alart").innerHTML = "Not be empty!";
        return true;
    } else {
        document.getElementById(x + "Text").style.color = "black";
        document.getElementById("alart").innerHTML = "";
    }
}

var check;
function checkRregister() {

    var checkForAll = false;
    for (var i = 0; i < check.length; i++) {
        if (checkEmpty(check[i]) === true) {
            checkForAll = true;
        }
    }
    
    if (checkForAll === true) {
        document.getElementById("alart").innerHTML = "Not be empty!";
    } else {
        if (checkConfirmPassword() === true) {
            if (checkRregisterWith === "mobile") {
                verifyOTP();
            } else
                document.getElementById("registerForm").submit();
        }
    }
}

function checkConfirmPassword() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    var regexPassword=/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,15})$/;
    if(!password.match(regexPassword)){
        document.getElementById("passwordText").style.color = "red";
        document.getElementById("alart").innerHTML = "Must contain at least one number and one uppercase and lowercase letter, and at least 8 characters!";
        return false;
    }
    if (password !== confirmPassword) {
        document.getElementById("alart").innerHTML = "Password does not match!";
        document.getElementById("confirmPasswordText").style.color = "red";
        document.getElementById("passwordText").style.color = "red";
        return false;
    }
    return true;
    
}

function sendCode(x) {
    if (checkEmpty(x) !== true) {
        document.getElementById("registerForm").submit();
    }
}


function countdownSendCode(x, y) {
    const button = (y === "alartForSendCode") ? document.querySelector('#button') : document.querySelector('#buttonForMobile');
    const countdown = document.querySelector('#' + y);
    let secondsLeft = x;


    button.style.display = "none";

    const intervalId = setInterval(() => {
        secondsLeft--;
        countdown.textContent = "Resend code after: " + secondsLeft + "s";
        if (secondsLeft === 0) {
            clearInterval(intervalId);
            countdown.textContent = '';
            button.style.display = "";
            return secondsLeft = x;
        }
    }, 1000);

}

// Điều chỉnh thông tin cấu hình Firebase của bạn ở đây
const firebaseConfig = {
    apiKey: "AIzaSyBg-FzvmLlKLgNkLxtGXR6sk3I--DwghsM",
    authDomain: "flmtq-a50be.firebaseapp.com",
    projectId: "flmtq-a50be",
    storageBucket: "flmtq-a50be.appspot.com",
    messagingSenderId: "571780269895",
    appId: "1:571780269895:web:33355dc5ced33a5d6aee62",
    measurementId: "G-7HMG35JCJ9"
};

// Khởi tạo Firebase
firebase.initializeApp(firebaseConfig);

// Lấy tham chiếu đến Firebase Auth
var auth = firebase.auth();


// Hàm xác thực OTP
function verifyOTP() {
    var otp = document.getElementById("verificationCode").value;
    window.confirmationResult.confirm(otp)
            .then(function (result) {
                document.getElementById("registerForm").submit();
            })
            .catch(function (error) {
                console.error(error);
                document.getElementById("alart").innerHTML = "Check your phone number or verification code!";

            });
}


//register with email/mobile
var checkRregisterWith = "email";
function RegisterWith() {
    if (checkRregisterWith === "email") {
        document.getElementById("email").value = "";
        document.getElementById("registerByMobile").style.display = '';
        document.getElementById("registerByEmail").style.display = 'none';
        document.getElementById("buttonRegisterWith").innerHTML = "Register with email?";
        document.getElementById("verificationCode").value = "";
        document.getElementById("alartForSendCodeForMobile").style.display = "";
        document.getElementById("alartForSendCode").style.display = "none";
        checkRregisterWith = "mobile";
        check = ["fullName", "mobile", "verificationCode", "confirmPassword", "password"];
    } else {
        document.getElementById("mobile").value = "";
        document.getElementById("registerByMobile").style.display = 'none';
        document.getElementById("registerByEmail").style.display = '';
        document.getElementById("buttonRegisterWith").innerHTML = "Register with mobile?";
        document.getElementById("verificationCode").value = "";
        document.getElementById("alartForSendCodeForMobile").style.display = "none";
        document.getElementById("alartForSendCode").style.display = "";
        checkRregisterWith = "email";
        check = ["fullName", "email", "verificationCode", "confirmPassword", "password"];

    }
}
function RegisterWithEmial() {
    document.getElementById("registerByMobile").style.display = 'none';
    check = ["fullName", "email", "verificationCode", "confirmPassword", "password"];
}
var countRecaptcha = 0;
// Send Verification Code For Mobile
function SendVerificationCodeForMobile() {

//    firebase.auth().settings.appVerificationDisabledForTesting = true;
    var phoneNumber = document.getElementById("mobile").value;

    var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
    var appVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-' + countRecaptcha);

    // k hien thi capcha cu
    for (var i = 0; i < countRecaptcha; i++) {
        document.getElementById('recaptcha-' + i).style.display = "none";
    }
    if (!checkEmpty("mobile")) {

        if (phoneNumber.match(vnf_regex)) {
            phoneNumber = "+84" + phoneNumber.slice(1);

            auth.signInWithPhoneNumber(phoneNumber, appVerifier)
                    .then(function (confirmationResult) {
                        // Lưu trữ thông tin xác thực để sử dụng trong hàm xác thực OTP
                        window.confirmationResult = confirmationResult;
                        countdownSendCode(25, 'alartForSendCodeForMobile');

                    })
                    .catch(function (error) {
                        console.error(error);
                        document.getElementById("alartForSendCodeForMobile").innerHTML = "failure";
                    });
        } else
            document.getElementById("alartForSendCodeForMobile").innerHTML = "Format: 0123456789";

    }
    add_child();

}

// add child
function add_child() {
    countRecaptcha++;
    var p = document.createElement("div");
    p.setAttribute("id", "recaptcha-" + countRecaptcha);

    var div = document.getElementById("recaptcha");
    div.appendChild(p);
}

function check() {
    countdownSendCode(25, 'alartForSendCode');
}