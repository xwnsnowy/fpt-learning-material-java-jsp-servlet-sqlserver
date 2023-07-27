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
//search
document.getElementById('searchInput').addEventListener('input', function () {
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
window.addEventListener('DOMContentLoaded', function () {
    var isAdmin = "${sessionScope.acc.getRoleName()}" === "ADMIN";
    var rows = document.querySelectorAll('.ListPO');
    rows.forEach(function (row, index) {
        var isActiveColumn = row.querySelector('td:nth-child(5)');
        var isActive = isActiveColumn.textContent;
        if (!isAdmin && isActive === '0') {
            row.classList.add('hidden-row');
        } else {
            row.querySelector('td:first-child').textContent = index - 1;
        }
    });
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
