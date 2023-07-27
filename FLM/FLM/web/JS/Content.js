/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function selectFilter() {
    document.getElementById('filter').submit();
}
function selectActive(event) {
    var checkbox = event.target;
    var isActiveHidden = document.querySelector('input[name="isActiveValue_' + checkbox.value + '"]');
    if (checkbox.checked) {
        isActiveHidden.value = "TRUE";
    } else {
        isActiveHidden.value = "FALSE";
    }
}


function view(x) {
    var sessionValue = $("#hdnSession").data('value');
    var thisId = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#id').innerText;
    document.getElementById('viewCurriculumSubject').querySelector('#id').innerHTML = thisId;

    var thisCode = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#subjectCode').innerText;
    document.getElementById('viewCurriculumSubject').querySelector('#subjectCode').innerHTML = thisCode;
    var thisName = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#subjectName').innerText;
    document.getElementById('viewCurriculumSubject').querySelector('#subjectName').innerHTML = thisName;
    var thisSemester = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#semester').innerText;
    document.getElementById('viewCurriculumSubject').querySelector('#semester').innerHTML = thisSemester;
    var thisNoCredit = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#noCredit').innerText;
    document.getElementById('viewCurriculumSubject').querySelector('#noCredit').innerHTML = thisNoCredit;
    if (sessionValue.toLowerCase() === 'admin') {
        var thisSubjectActive = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#active').innerHTML;
        document.getElementById('viewCurriculumSubject').querySelector('#active').innerHTML = thisSubjectActive;
    }

}
// set data for edit profile
function edit(x) {
//    document.getElementById('alertForEdit').innerHTML = "";
//    document.getElementById("codeText").style.color = "";
//    document.getElementById("nameText").style.color = "";
    var thisId = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#id').innerText;
    document.getElementById('editCurriculumSubject').querySelector('#id').innerHTML = thisId;
    document.querySelector('#idValue').value = thisId;

    var thisCode = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#subjectCode').innerText;
    document.getElementById('editCurriculumSubject').querySelector('#subjectCode').innerHTML = thisCode;
    var thisName = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#subjectName').innerText;
    document.getElementById('editCurriculumSubject').querySelector('#subjectName').innerHTML = thisName;

    var thisSemester = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#semester').innerText;
    document.getElementById('editCurriculumSubject').querySelector('#semester').value = thisSemester;
    var thisNoCredit = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#noCredit').innerText;
    document.getElementById('editCurriculumSubject').querySelector('#noCredit').value = thisNoCredit;


    var thisSubjectActive = document.getElementsByClassName('itemCurriculumSubject')[x - 1].querySelector('#active').innerText;
    // get curriculum subject active to view
    if (thisSubjectActive.toLowerCase() === 'true') {
        document.getElementById('editCurriculumSubject').querySelector('#isActive').checked = true;
        document.getElementById('editCurriculumSubject').querySelector('#isActiveText').innerHTML = 'true';
    } else {
        document.getElementById('editCurriculumSubject').querySelector('#isActive').checked = false;
        document.getElementById('editCurriculumSubject').querySelector('#isActiveText').innerHTML = 'false';
    }
}

function selectBt(x) {
    var select = document.getElementById('editCurriculumSubject').querySelector('#' + x).innerText;
    // change text status  getElementById('editSubject')
    if (select.toLowerCase() === 'true') {
        document.getElementById('editCurriculumSubject').querySelector('#' + x).innerHTML = 'false';
        document.getElementById(x).value = false;
    } else {
        document.getElementById('editCurriculumSubject').querySelector('#' + x).innerHTML = 'true';
        document.getElementById(x).value = true;
    }
}

//pagination

let thisPage = Number(document.getElementById("thisPage").value);
let limit = 5;
let list = document.getElementById("numberOfContent").innerText;
//loadItem
function loadItem() {
    let beginGet = limit * (thisPage - 1);
    let endGet = limit * thisPage - 1;

    // show number of item is displayed
    if (limit >= list || beginGet + 1 >= list) {
        document.getElementById('numberAcc').innerHTML = "Showing " + list + " out of " + list;
    } else
    if (endGet < list) {
        document.getElementById('numberAcc').innerHTML = "Showing " + (beginGet + 1) + "-" + (endGet + 1) + " out of " + list;

    } else {
        document.getElementById('numberAcc').innerHTML = "Showing " + (beginGet + 1) + "-" + list + " out of " + list;

    }
// change number page
    listPage();
    document.getElementById("start").value = beginGet;
    document.getElementById("end").value = endGet + 1;
}
if (thisPage !== 0) {
    loadItem();
}

// listPage
function listPage() {
    let count = Math.ceil(list / limit);
    if (count <= 1) {
        return;
    }
    document.querySelector('.listPage').innerHTML = '';

    if (thisPage !== 1) {
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
        if (i === thisPage) {
            newPage.classList.add('active');
        }
        newPage.setAttribute('onclick', "changePage(" + i + ")");
        document.querySelector('.listPage').appendChild(newPage);
    }

    if (thisPage !== count) {
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
    document.getElementById("thisPage").value = i;
    document.getElementById("filter").submit();
}