/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
window.addEventListener('DOMContentLoaded', function () {
    var inputs = document.getElementsByTagName('input');
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].setAttribute('autocomplete', 'off');
    }
});
window.addEventListener('DOMContentLoaded', function () {
    var checkboxes = document.querySelectorAll('.checkbox-row');
    var isAdmin = "${sessionScope.acc.getRoleName()}" === "ADMIN";

    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            var row = checkbox.closest('tr');

            if (isAdmin || checkbox.checked) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });

        // Gọi sự kiện change ban đầu để ẩn/hiện các hàng dựa trên giá trị ban đầu của checkbox
        checkbox.dispatchEvent(new Event('change'));
    });
});
function deleteSetting(x) {
    if (confirm('Warning: Your actions may erase all settings in systems with this Setting Id:' + x) === true) {
        document.getElementById("delete").value = x;
        document.getElementById("deleteSetting").submit();
    }
}

////pagination
//let thisPage = 1;
//let limit = 5;
//let list = document.querySelectorAll('.listSetting .itemSetting');
////loadItem
//function loadItem() {
//    let beginGet = limit * (thisPage - 1);
//    let endGet = limit * thisPage - 1;
//    list.forEach((item, key) => {
//        // show item in range
//        if (key >= beginGet && key <= endGet) {
//            item.style.display = '';
//        } else {
//            item.style.display = 'none';
//        }
//    })
//    // show number of item is displayed
//    if (limit >= list.length || beginGet + 1 >= list.length) {
//        document.getElementById('numberAcc').innerHTML = "Showing " + list.length + " out of " + list.length;
//    } else
//        document.getElementById('numberAcc').innerHTML = "Showing " + (beginGet + 1) + "-" + (endGet + 1) + " out of " + list.length;
//// change number page
//    listPage();
//}
//loadItem();
//// listPage
//function listPage() {
//    let count = Math.ceil(list.length / limit);
//    document.querySelector('.listPage').innerHTML = '';
//
//    if (thisPage != 1) {
//        let prev = document.createElement('li');
//        prev.setAttribute('class', "page-item");
//        let prevText = document.createElement('a');
//        prevText.setAttribute('class', "page-link");
//        prevText.innerText = 'PREV';
//        prev.appendChild(prevText);
//        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
//        document.querySelector('.listPage').appendChild(prev);
//    }
//
//    for (i = 1; i <= count; i++) {
//        let newPage = document.createElement('li');
//        newPage.setAttribute('class', "page-item");
//        let newPageText = document.createElement('a');
//        newPageText.setAttribute('class', "page-link");
//        newPageText.innerText = i;
//        newPage.appendChild(newPageText);
//        if (i == thisPage) {
//            newPage.classList.add('active');
//        }
//        newPage.setAttribute('onclick', "changePage(" + i + ")");
//        document.querySelector('.listPage').appendChild(newPage);
//    }
//
//    if (thisPage != count) {
//        let next = document.createElement('li');
//        next.setAttribute('class', "page-item");
//        let nextText = document.createElement('a');
//        nextText.setAttribute('class', "page-link");
//        nextText.innerText = 'NEXT';
//        next.appendChild(nextText);
//        next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
//        document.querySelector('.listPage').appendChild(next);
//    }
//}
////  number page present
//function changePage(i) {
//    thisPage = i;
//    loadItem();
//}
//search items
document.getElementById('searchInput').addEventListener('input', function() {
  var searchValue = this.value.toLowerCase();
  var dataTable = document.getElementById('dataTable');
  var rows = dataTable.getElementsByTagName('tr');
  var noResultsRow = document.getElementById('noResults');

  var hasResults = false;

  for (var i = 1; i < rows.length; i++) {
    var rowData = rows[i].textContent.toLowerCase();

    if (rowData.includes(searchValue)) {
      rows[i].style.display = '';
      hasResults = true;
    } else {
      rows[i].style.display = 'none';
    }
  }

  if (hasResults) {
    noResultsRow.style.display = 'none';
  } else {
    noResultsRow.style.display = '';
  }
});

