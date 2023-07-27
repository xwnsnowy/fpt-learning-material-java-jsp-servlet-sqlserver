/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function checkSubmitForEditSubject() {
    var regexCode = /^([A-Za-z0-9]|\S){1,20}$/;
    var regexName = /^.{0,100}$/;
    var id = document.getElementById('editSubject').querySelector('#id').innerHTML;
     var thisId;
    var thisCode;
    var thisName;
    var comma;
    var check = true;
    var alertForEdit ="";
    var newCode = document.getElementById('editSubject').querySelector('#code').value.toLowerCase();
    var newName = document.getElementById('editSubject').querySelector('#name').value.toLowerCase();
    if (!regexCode.test(newCode) || !regexName.test(newName)) {
        if (!regexCode.test(newCode)) {
            document.getElementById("codeText").style.color = "red";
            alertForEdit = "The code must be larger than 1 and less than 20 characters!";
            document.getElementById('alertForEdit').innerHTML = alertForEdit;
        }
        if (!regexName.test(newName)) {
            document.getElementById("nameText").style.color = "red";
            alertForEdit += " The name must be less than 100 characters!";
            document.getElementById('alertForEdit').innerHTML = alertForEdit;
        }
        return;
    }
    alertForEdit = "The subject ";
    for (var i = 0; i < list.length; i++) {
        thisId = document.getElementsByClassName('itemSubject')[i].querySelector('#id').innerText;
        thisCode = document.getElementsByClassName('itemSubject')[i].querySelector('#code').innerText.toLowerCase();
        thisName = document.getElementsByClassName('itemSubject')[i].querySelector('#name').innerText.toLowerCase();

        if (id  !== thisId) {
            if (thisCode === newCode || thisName === newName) {
                check = false;
                if (thisCode === newCode) {
                    if (comma) {
                        alertForEdit += ", ";
                        comma = false;
                    }
                    alertForEdit += "code";
                    document.getElementById("codeText").style.color = "red";
                    comma = true;

                }
                if (thisName === newName) {
                    if (comma) {
                        alertForEdit += ", ";
                        comma = false;
                    }
                    alertForEdit += "name";
                    document.getElementById("nameText").style.color = "red";
                    comma = true;
                }

            }
        }
    }
    if (!check) {
        alertForEdit += " already exists!";
        document.getElementById('alertForEdit').innerHTML = alertForEdit;
    } else {
//    document.getElementById('alertForEdit').innerHTML = "";
//    document.getElementById("codeText").style.color = "";
//    document.getElementById("nameText").style.color = "";
        document.getElementById('editForm').submit();
    }
}

function view(x) {

    var thisId = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#id').innerText;
    document.getElementById('viewSubject').querySelector('#id').innerHTML = thisId;
    document.querySelector('#idValue').value = thisId;

    var thisCode = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#code').innerText;
    document.getElementById('viewSubject').querySelector('#code').innerHTML = thisCode;
    var thisParentCode = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#parentSubject').innerText;
    document.getElementById('viewSubject').querySelector('#parentSubject').innerHTML = thisParentCode;
    var thisName = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#name').innerText;
    document.getElementById('viewSubject').querySelector('#name').innerHTML = thisName;
    var thisType = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#type').innerText;
    document.getElementById('viewSubject').querySelector('#type').innerHTML = thisType;
    var thisSubjectActive = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#isActive').innerHTML;
    document.getElementById('viewSubject').querySelector('#isActive').innerHTML = thisSubjectActive;
    var thisDescription = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#description').innerText;
    document.getElementById('viewSubject').querySelector('#description').innerHTML = thisDescription;

}
// set data for edit profile
function edit(x) {
    document.getElementById('alertForEdit').innerHTML = "";
    document.getElementById("codeText").style.color = "";
    document.getElementById("nameText").style.color = "";
    var thisId = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#id').innerText;
    document.getElementById('editSubject').querySelector('#id').innerHTML = thisId;
    document.querySelector('#idValue').value = thisId;

    var thisCode = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#code').innerText;
    document.getElementById('editSubject').querySelector('#code').value = thisCode;
    var thisName = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#name').innerText;
    document.getElementById('editSubject').querySelector('#name').value = thisName;
    var thisSubjectActive = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#isActive').innerText;
    // get subject active to view
    if (thisSubjectActive.toLowerCase() === 'true') {
        document.getElementById('editSubject').querySelector('#isActive').checked = true;
        document.getElementById('editSubject').querySelector('#isActiveText').innerHTML = 'true';
    } else {
        document.getElementById('editSubject').querySelector('#isActive').checked = false;
        document.getElementById('editSubject').querySelector('#isActiveText').innerHTML = 'false';
    }

    var thisPrarentSubject = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#parentSubject').innerText;
//    document.querySelector('#preParentSubject').value = thisSubject;
    var count = document.getElementsByClassName('select').length;
    // loop to get code of parent subject for view 
    document.getElementById('select2-selectSubject-container').setAttribute("onclick", "hideThisCode(" + x + ")");
    for (var i = 0; i < count; i++) {
        //select parent subject code
        if (thisPrarentSubject === "") {
            document.getElementById("select2-selectSubject-container").innerHTML = "None";
        }
        if (thisPrarentSubject.toLowerCase() === document.getElementsByClassName('select')[i].innerText.toLowerCase()) {
            document.getElementsByClassName('select')[i].selected = true;
            document.getElementById("select2-selectSubject-container").innerHTML = thisPrarentSubject;

        } else {
            document.getElementsByClassName('select')[i].selected = false;
        }
    }
    
    
    var thisType = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#type').innerText;
    var countType = document.getElementsByClassName('selectType').length;
    // loop to get code of type subject for view 
    for (var i = 0; i < countType; i++) {
        //select type subject code
        if (thisType.toLowerCase() === document.getElementsByClassName('selectType')[i].innerText.toLowerCase()) {
            document.getElementsByClassName('selectType')[i].selected = true;
            document.getElementById("select2-selectType-container").innerHTML = thisType;

        } else {
            document.getElementsByClassName('selectType')[i].selected = false;
        }
    }
    var thisDescription = document.getElementsByClassName('itemSubject')[x - 1].querySelector('#description').innerText;
    document.getElementById('editSubject').querySelector('#description').value = thisDescription;

}
// hide the this subject code
function hideThisCode(x) {
    document.getElementsByClassName('select2-results__option--selectable')[x].style.display = "none";
}
//delete user
function deleteSubject(x) {
    //confirm
    if (confirm('Do you want to remove the Subject with Id:' + x) === true) {
        document.getElementById('daleteSubjectById').value = x;
        document.getElementById('delete').submit();
    }

}

//change text status of subject active 
function selectBt(x) {
    var select = document.getElementById('editSubject').querySelector('#' + x).innerText;
    // change text status  getElementById('editSubject')
    if (select.toLowerCase() === 'true') {
        document.getElementById('editSubject').querySelector('#' + x).innerHTML = 'false';
        document.getElementById(x).value = false;
    } else {
        document.getElementById('editSubject').querySelector('#' + x).innerHTML = 'true';
        document.getElementById(x).value = true;
    }
}
//pagination
let thisPage = 1;
let limit = 10;
let list = document.querySelectorAll('.listSubject .itemSubject');
//loadItem
function loadItem() {
    let beginGet = limit * (thisPage - 1);
    let endGet = limit * thisPage - 1;
    list.forEach((item, key) => {
        // show item in range
        if (key >= beginGet && key <= endGet) {
            item.style.display = '';
        } else {
            item.style.display = 'none';
        }
    })
    // show number of item is displayed
    if (limit >= list.length || beginGet + 1 >= list.length) {
        document.getElementById('numberAcc').innerHTML = "Showing " + list.length + " out of " + list.length;
    } else
        document.getElementById('numberAcc').innerHTML = "Showing " + (beginGet + 1) + "-" + (endGet + 1) + " out of " + list.length;
// change number page
    listPage();
}
loadItem();
// listPage
function listPage() {
    let count = Math.ceil(list.length / limit);
    document.querySelector('.listPage').innerHTML = '';

    if (thisPage != 1) {
        let prev = document.createElement('li');
        prev.setAttribute('class', "page-item");
        let prevText = document.createElement('a');
        prevText.setAttribute('class', "page-link");
        prevText.innerText = 'PREV';
        prev.appendChild(prevText);
        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
        document.querySelector('.listPage').appendChild(prev);
    }

    for (i = 1; i <= count; i++) {
        let newPage = document.createElement('li');
        newPage.setAttribute('class', "page-item");
        let newPageText = document.createElement('a');
        newPageText.setAttribute('class', "page-link");
        newPageText.innerText = i;
        newPage.appendChild(newPageText);
        if (i == thisPage) {
            newPage.classList.add('active');
        }
        newPage.setAttribute('onclick', "changePage(" + i + ")");
        document.querySelector('.listPage').appendChild(newPage);
    }

    if (thisPage != count) {
        let next = document.createElement('li');
        next.setAttribute('class', "page-item");
        let nextText = document.createElement('a');
        nextText.setAttribute('class', "page-link");
        nextText.innerText = 'NEXT';
        next.appendChild(nextText);
        next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
        document.querySelector('.listPage').appendChild(next);
    }
}
//  number page present
function changePage(i) {
    thisPage = i;
    loadItem();
}

function selectBtForSubject(x) {
    var select = document.querySelector('#' + x).innerText;
    // change text status  
    if (select.toLowerCase() === 'true') {
        document.querySelector('#' + x).innerHTML = 'FALSE';
        document.getElementById("a-1").value = false;
    } else {
        document.querySelector('#' + x).innerHTML = 'TRUE';
        document.getElementById("a-1").value = true;
    }
}
