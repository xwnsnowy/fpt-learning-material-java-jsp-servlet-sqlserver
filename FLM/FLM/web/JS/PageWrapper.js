//pagination
function cleanThisPage() {
    document.getElementById("thisPage").value = "0";
    document.getElementById("filter").submit();
}
var input = document.getElementById("myInput");
input.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        cleanThisPage();
    }
});
let thisPage = Number(document.getElementById("thisPage").value);
let limit = 10;
let list = document.getElementById("numberItem").innerText;
//loadItem
function loadItem() {
    let beginGet = limit * (thisPage - 1);
    let endGet = limit * thisPage - 1;

    // show number of item is displayed
    if (limit >= list || beginGet + 1 >= list) {
        document.getElementById('textNumber').innerHTML = "Showing " + list + " out of " + list;
    } else
    if (endGet < list) {
        document.getElementById('textNumber').innerHTML = "Showing " + (beginGet + 1) + "-" + (endGet + 1) + " out of " + list;

    } else {
        document.getElementById('textNumber').innerHTML = "Showing " + (beginGet + 1) + "-" + list + " out of " + list;

    }
// change number page
    listPage();
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