/* global firebase */

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
            })
            .catch(function (error) {
                console.error(error);
                document.getElementById("statusMessage").innerHTML = "Xác thực thất bại.";
            });
            return true;
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


