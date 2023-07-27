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
    var check = [ "mobile","otp","confirmPassword", "password"];
    for (var i = 0; i < check.length; i++) {
        if (checkEmpty(check[i]) === true) {
            checkForAll = true;
        }
    }
    if (checkForAll === true) {
        document.getElementById("alert").innerHTML = "Not be empty!";
    } else {
        if (checkConfirmPassword() === true && validatePassword()===true && verifyOTP()===true) {
            document.getElementById("changepassbymobile").submit();
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
        document.getElementById("changepassbymobile").submit();
    }
}
function countdownSendCode(x) {
    const button = document.querySelector('#button');
    const countdown = document.querySelector('#alertcountdown');
    let secondsLeft = x;
        document.querySelector("#button").style.display = "none";
        const intervalId = setInterval(() => {
            secondsLeft--;
            countdown.textContent = "Resend code after: " + secondsLeft+"s";
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


const firebaseConfig = {
 apiKey: "AIzaSyBAxNJQYSTKiMbjJlGcvQFL6S5vKXzIZcw",
  authDomain: "flm2-36fe8.firebaseapp.com",
  projectId: "flm2-36fe8",
  storageBucket: "flm2-36fe8.appspot.com",
  messagingSenderId: "186086954028",
  appId: "1:186086954028:web:fed297165e2c46c512ad89",
  measurementId: "G-QZYPTS8CLW"
};

// Khởi tạo Firebase
firebase.initializeApp(firebaseConfig);

// Lấy tham chiếu đến Firebase Auth
var auth = firebase.auth();


// Hàm xác thực OTP
function verifyOTP() {
    var otp = document.getElementById("otp").value;
    window.confirmationResult.confirm(otp)
            .then(function (result) {
                document.getElementById("changepassbymobile").submit();
                document.getElementById("statusMessage").innerHTML = "Xác thực thành công.";
                return true;
            })
            .catch(function (error) {
                console.error(error);
                document.getElementById("statusMessage").innerHTML = "Xác thực thất bại.";
                return false;
            });
          
}

//register with email/mobile
var countRecaptcha = 0;
// Send Verification Code For Mobile
function SendVerificationCodeForMobile(){
     phoneNumber = document.getElementById("mobile").value;
    var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
    var appVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-'+countRecaptcha);
    // k hien thi capcha cu
    for(var i=0; i<countRecaptcha; i++){
        document.getElementById('recaptcha-'+i).style.display="none";
    }
    if(!checkEmpty("mobile")){
        
        if(phoneNumber.match(vnf_regex)){
            phoneNumber = "+84"+phoneNumber.slice(1);
            auth.signInWithPhoneNumber(phoneNumber, appVerifier)
            .then(function (confirmationResult) {
                // Lưu trữ thông tin xác thực để sử dụng trong hàm xác thực OTP
                window.confirmationResult = confirmationResult;
            })
            .catch(function (error) {
                console.error(error);
                document.getElementById("alartForSendCodeForMobile").innerHTML = "Mã Xác Thực Không Chính Xác";
            });
        }else
        document.getElementById("alartForSendCodeForMobile").innerHTML = "123";
        
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
