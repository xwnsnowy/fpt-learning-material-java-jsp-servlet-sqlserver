/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function selectBt(x) {
    var select = document.querySelector('#' + x).innerText;
    // change text status  getElementById('editSubject')
    if (select.toLowerCase() === 'true') {
        document.querySelector('#' + x).innerHTML = 'false';
        document.getElementById(x).value = false;
    } else {
        document.querySelector('#' + x).innerHTML = 'true';
        document.getElementById(x).value = true;
    }
}

function submitSubject() {
    document.getElementById("formCS").submit();
}


function checkSubmit() {
    var semester = document.getElementById("semester").value;
    var noCredit = document.getElementById("noCredit").value;
    var alert = "";
    if (is_numeric(noCredit)) {
        var checkNocrredit = parseInt(noCredit);
        if (checkNocrredit < 1) {
            if (alert === "") {
                alert += "NoCredit must be integers greater than 0!";
            } else {
                alert += "<br> NoCredit must be integers greater than 0!";
            }
        }
    } else {
        if (alert === "") {
            alert += "NoCredit must be integers greater than 0!";
        } else {
            alert += "<br> NoCredit must be integers greater than 0!";
        }
    }
    if (is_numeric(semester)) {
        var checkSemester = parseInt(semester);
        if (checkSemester < 0) {
            if (alert === "") {
                alert += "Semester must be integers equal or greater than 0!";
            } else {
                alert += "<br> Semester must be integers equal or greater than 0!";
            }
        }
    } else {
        if (alert === "") {
            alert += "Semester must be integers equal or greater than 0!";
        } else {
            alert += "<br> Semester must be integers equal or greater than 0!";
        }
    }
    if (alert === "") {
        document.getElementById("formCS").submit();
        resetToast();
    } else {
        document.getElementById("alert").innerHTML = alert;
    }

}
function is_numeric(str) {
    return /^\d+$/.test(str);
}
function resetToast() {
    sessionStorage.removeItem("toastShow");
}

