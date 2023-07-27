/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
// set data for view profile
var oldAvataView;
var countAvataView=false;
function view(x) {
    var thisId = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#id').innerText;
    document.getElementById('viewAccount').querySelector('#id').innerHTML = thisId;
    var thisAvata = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#avata').cloneNode();
//    first run
    if(!countAvataView){
        countAvataView = true;
        document.getElementById('viewAccount').querySelector('#avata').appendChild(thisAvata);
    }else{
    document.getElementById('viewAccount').querySelector('#avata').replaceChild(thisAvata, oldAvataView);
    }
    oldAvataView = thisAvata;
    var thisName = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#name').innerHTML;
    document.getElementById('viewAccount').querySelector('#name').innerHTML = thisName;
    var thisUserName = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#userName').innerHTML;
    document.getElementById('viewAccount').querySelector('#userName').innerHTML = thisUserName;
    var thisMobile = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#mobile').innerHTML;
    document.getElementById('viewAccount').querySelector('#mobile').innerHTML = thisMobile;
    var thisRole = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#role').innerHTML;
    document.getElementById('viewAccount').querySelector('#role').innerHTML = thisRole;
    var thisRoleActive = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#isActive').innerHTML;
    document.getElementById('viewAccount').querySelector('#isActive').innerHTML = thisRoleActive;
    var thisEmail = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#email').innerHTML;
    document.getElementById('viewAccount').querySelector('#email').innerHTML = thisEmail;
    var thisStatus = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#status').innerHTML;
    document.getElementById('viewAccount').querySelector('#status').innerHTML = thisStatus;

}
// set data for edit profile
var oldAvata;
var countAva=false;
function edit(x) {
    
    var thisId = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#id').innerText;
    document.getElementById('editAccount').querySelector('#id').innerHTML = thisId;
    document.querySelector('#idValue').value = thisId;
    var thisAvata = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#avata').cloneNode();
    //    first run
    if(!countAva){
        countAva = true;
        document.getElementById('editAccount').querySelector('#avata').appendChild(thisAvata);
    }else{
    document.getElementById('editAccount').querySelector('#avata').replaceChild(thisAvata, oldAvata);
    }
    oldAvata = thisAvata;
    var thisName = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#name').innerHTML;
    document.getElementById('editAccount').querySelector('#name').innerHTML = thisName;
    var thisUserName = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#userName').innerHTML;
    document.getElementById('editAccount').querySelector('#userName').innerHTML = thisUserName;
    var thisMobile = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#mobile').innerHTML;
    document.getElementById('editAccount').querySelector('#mobile').innerHTML = thisMobile;
    var thisEmail = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#email').innerHTML;
    document.getElementById('editAccount').querySelector('#email').innerHTML = thisEmail;
    var thisRole = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#role').innerText;
    document.querySelector('#preRole').value = thisRole;
    var count = document.getElementsByClassName('select').length;
    // loop to get role of user for view 
    for (var i = 0; i < count; i++) {
        //select role
        if (thisRole.toLowerCase() === document.getElementsByClassName('select')[i].innerText.toLowerCase()) {
            document.getElementsByClassName('select')[i].selected = true;

        } else {
            document.getElementsByClassName('select')[i].selected = false;
        }
    }
    var thisRoleActive = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#isActive').innerText;
    // get role active to view
    if (thisRoleActive.toLowerCase() === 'true') {
        document.getElementById('editAccount').querySelector('#a-1').checked = true;
        document.getElementById('editAccount').querySelector('#isActive').innerHTML = 'true';
    } else {
        document.getElementById('editAccount').querySelector('#a-1').checked = false;
        document.getElementById('editAccount').querySelector('#isActive').innerHTML = 'false';
    }
    var thisStatus = document.getElementsByClassName('itemAccount')[x - 1].querySelector('#status').innerText;
    //get status to view
    if (thisStatus.toLowerCase() === 'true') {
        document.getElementById('editAccount').querySelector('#a-2').checked = true;
        document.getElementById('editAccount').querySelector('#status').innerHTML = 'true';
    } else {
        document.getElementById('editAccount').querySelector('#a-2').checked = false;
        document.getElementById('editAccount').querySelector('#status').innerHTML = 'false';
    }

}
//change text status of role active and status 
function selectBt(x) {
    var select = document.getElementById('editAccount').querySelector('#' + x).innerText;
    var value = x === "status" ? "a-2" : "a-1";
    // change text status  
    if (select.toLowerCase() === 'true') {
        document.getElementById('editAccount').querySelector('#' + x).innerHTML = 'false';
        document.getElementById(value).value = false;
    } else {
        document.getElementById('editAccount').querySelector('#' + x).innerHTML = 'true';
        document.getElementById(value).value = true;
    }
}
// submit form filter
function selectFilterSubmit() {
    document.getElementById('filter').submit();
}


//pagination
let thisPage = 1;
let limit = 10;
let list = document.querySelectorAll('.listAccount .itemAccount');
//loadItem
function loadItem(){
    let beginGet = limit * (thisPage - 1);
    let endGet = limit * thisPage - 1;
    list.forEach((item, key)=>{
        // show item in range
        if(key >= beginGet && key <= endGet){
            item.style.display = '';
        }else{
            item.style.display = 'none';
        }
    });
    // show number of item is displayed
    if(limit >=list.length || beginGet+1 >= list.length ){
        document.getElementById('numberAcc').innerHTML = "Showing " +list.length+ " out of " + list.length;
    }else
    document.getElementById('numberAcc').innerHTML = "Showing " +(beginGet+1)+ "-" +(endGet+1)+  " out of " + list.length;
// change number page
    listPage();
}
loadItem();
// listPage
function listPage(){
    let count = Math.ceil(list.length / limit);
    document.querySelector('.listPage').innerHTML = '';
    
    if(thisPage != 1){
        let prev = document.createElement('li');
        prev.setAttribute('class',"page-item");
        let prevText = document.createElement('a');
        prevText.setAttribute('class', "page-link");
        prevText.innerText = 'PREV';
        prev.appendChild(prevText);
        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
        document.querySelector('.listPage').appendChild(prev);
    }
    
    for(i = 1; i <= count; i++){
        let newPage = document.createElement('li');
        newPage.setAttribute('class',"page-item");
        let newPageText = document.createElement('a');
        newPageText.setAttribute('class', "page-link");
        newPageText.innerText = i;
        newPage.appendChild(newPageText);
        if(i == thisPage){
            newPage.classList.add('active');
        }
        newPage.setAttribute('onclick', "changePage(" + i + ")");
        document.querySelector('.listPage').appendChild(newPage);
    }

    if(thisPage != count){
        let next = document.createElement('li');
        next.setAttribute('class',"page-item");
        let nextText = document.createElement('a');
        nextText.setAttribute('class', "page-link");
        nextText.innerText = 'NEXT';
        next.appendChild(nextText);
        next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
        document.querySelector('.listPage').appendChild(next);
    }
}
//  number page present
function changePage(i){
    thisPage = i;
    loadItem();
}
//delete user
function deleteUser(x){
    //confirm
    if(confirm('Do you want to remove the user with Id:'+x)===true){
        document.getElementById('daleteUserId').value = x;
        document.getElementById('delete').submit();
    }
    
}