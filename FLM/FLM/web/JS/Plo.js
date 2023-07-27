/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
/* global XLSX, URL */

// Export File




function changeStatus(x, checkbox) {
    var active = checkbox.checked ? "1" : "0";
    console.log("active: " + active);

    if (confirm('Do you want to change status the clo with PloId:' + x) === true) {
        document.getElementById('Plo_id').value = x;
        document.getElementById('active').value = active;
        document.getElementById('change').submit();
    }

}
function selectFilterSubmit() {
    document.getElementById('filter').submit();
}
;

function exportTemplate() {
    var headers = ["PLO_Name", "PLO_Decritpion", "Active[0 or 1]"];
    var data = [
        // Các dòng dữ liệu mẫu
        ["PLO5", "abcd", "1", "Example"]
    ];
    var worksheet = XLSX.utils.aoa_to_sheet([headers, ...data]);
    var workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "PloList");

    var wbout = XLSX.write(workbook, {bookType: "xlsx", type: "array"});
    var file = new Blob([wbout], {type: "application/octet-stream"});
    var fileURL = URL.createObjectURL(file);
    var a = document.createElement("a");
    a.href = fileURL;
    a.download = "template.xlsx";
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(fileURL);

}

