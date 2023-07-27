

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" >
        <title>FLM_USER MANAGEMENT</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" >
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" >
        <meta name="author" content="Shreethemes" >
        <meta name="email" content="support@shreethemes.in" >
        <meta name="website" content="../../../../../../index.html" >
        <meta name="Version" content="v1.2.0" >

        <!-- favicon -->
        <link rel="shortcut icon" href="../../assets/images/flm-dark.png">
        <!-- Bootstrap -->
        <link href="../../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
        <!-- simplebar -->
        <link href="../../assets/css/simplebar.css" rel="stylesheet" type="text/css" >
        <!-- Select2 -->
        <link href="../../assets/css/select2.min.css" rel="stylesheet" >
        <!-- Date picker -->
        <link rel="stylesheet" href="../../assets/css/flatpickr.min.css">
        <link href="../../assets/css/jquery.timepicker.min.css" rel="stylesheet" type="text/css" >
        <!-- Icons -->
        <link href="../../assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" >
        <link href="../../assets/css/remixicon.css" rel="stylesheet" type="text/css" >
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="../../assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" >
        <!--account css-->

    </head>
    <style>
        .mb-0 tr:nth-child(odd) {
            background-color: #ffffff;
        }
        .mb-0 tr:hover {
            background-color: #ddd;
            ;
        }
        #tr{
            background-color:#396cf0;
            color: #ffffff;
        }
        .hidden-row {
            display: none;
        }
        .table1, th, td {
            border: 1px solid #dee2e6;
            vertical-align: top;
            text-align: left;
            padding: 8px;
            border-collapse: collapse;
        }
        .pagination{
            display: inline-block;
        }
        .pagination a {
            color: black;
            font-size:22px;
            float:left;
            padding:8px 16px;
            text-decoration:none;
        }
        .pagination a.active{
            background-color: #396cf0;
            color:white;
        }
        .pagination a:hover:not(.active){
            background-color: red;
            color:white;

        </style>
        <body>
            <!-- Loader -->
            <div id="preloader">
                <div id="status">
                    <div class="spinner">
                        <div class="double-bounce1"></div>
                        <div class="double-bounce2"></div>
                    </div>
                </div>
            </div>
            <!-- Loader -->

            <div class="page-wrapper doctris-theme toggled">
                <%@include file="../common/sidebar.jsp" %>
                <!-- sidebar-wrapper  -->

                <!-- Start Page Content -->
                <main class="page-content bg-light">
                    <%@include file="../common/headerforsidebar.jsp" %>
                    <!--Duy-->

                    <div class="container-fluid">

                        <div class="layout-specing">
                            <div class="d-md-flex justify-content-between">
                                <h5 class="mb-0">PO Management</h5><br>

                                <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                    <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                        <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">HOME</a></li>
                                        <li class="breadcrumb-item">Curriculum</li>
                                        <li class="breadcrumb-item"> <a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.code}">POs</a></li>
                                        <li class="breadcrumb-item active" aria-current="page"><a href="account">PO List</a></li>
                                    </ul>
                                </nav>

                            </div>
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>CurriCulum ID</th>
                                        <td>${c.curriculum_id}</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>CurriCulum Code</th>
                                        <td>${c.code}</td>
                                    </tr>
                                    <tr>
                                        <th>CurriCulum Name</th>
                                        <td>${c.name}</td>
                                    </tr>
                                </tbody>
                            </table> 
                            <form id="filter" action="po?action=search&Ccode=${c.code}" method="post" style="margin-top: 10px"> 
                                    <div class="col-sm-12 col-md-8">
                                        <div class="d-grid">
                                            <div class="input-group">
                                                <div class="input-group-prepend" >
                                                    <select name="selectFilter" class=" form-control time-during select2input" >
                                                        <option <c:if test="${selectFilter == 'name'}"> selected="" </c:if> value="name">Po Name</option>
                                                        <option <c:if test="${selectFilter == 'description'}"> selected="" </c:if> value="description">Description</option>
                                                        </select>
                                                    </div>
                                                    <input type="text" name="search" class=" form-control" placeholder="Search" id="myInput" value="${search}">
                                                <input type="submit" name="btn" class="btn btn-primary" value="Search">
                                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' ||  sessionScope.acc.getRoleName() == 'CRDD STAFF'}">    
                                                    <div class="d-grid" style="padding-left: 20px;">
                                                    <a href="/FLM_NEW/view/admin/addpos?Ccode=${c.getCode()}" class="btn btn-success">Add New POs</a>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div><!--end col-->
                            </form>

                            <div>
                                <h2 style="color: red">${alert}</h2>
                                </div>
                                <div class=" row">
                                    <div class="col-12 mt-4">
                                        <div class="table-responsive shadow rounded">
                                            <table class="  table table-center bg-white mb-0" id="dataTable">
                                                <thead>
                                                    <tr id="tr">
                                                    <th class="border-bottom p-3" style="min-width: 50px;">POs ID</th>
                                                    <th class="border-bottom p-3" style="min-width: 90px;">PO Name</th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;">PO Description</th>
                                                        <c:choose>
                                                            <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                            <th class="border-bottom p-3" style="min-width: 50px;">Active</th>
                                                            <th class="border-bottom p-3" style="min-width: 120px;"></th>
                                                            </c:when>
                                                        </c:choose>
                                                </tr>
                                            </thead>
                                            <c:forEach  var="o" items="${listPage}" >
                                                <tbody id="tableBody">
                                                    <tr class="ListPO <c:if test="${o.getIs_Active() == '0' && sessionScope.acc.getRoleName() != 'ADMIN'}">hidden-row</c:if>">
                                                        <td class="border-bottom p-3" style="min-width: 30px;">${o.getPo_id()}</td>
                                                        <td class="border-bottom p-3" style="min-width: 70px;">${o.getPo_name()}</td>
                                                        <td class="border-bottom p-3" style="height: 100px;
                                                            width: 1000px">${o.getPo_description()}</td>
                                                            <c:choose>
                                                                <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                                    <td class="border-bottom p-3" style="min-width: 70px;">
                                                                    <c:choose>
                                                                        <c:when test="${o.getIs_Active() == '1'}">
                                                                            <span class="badge bg-soft-success">Active</span>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span class="badge bg-soft-warning">Non active</span>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                                <td class="border-bottom p-3" style="min-width: 70px;">
                                                                    <a class="btn btn-icon btn-pills btn-soft-success"   href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}&amp;po_id=${o.po_id}"><i class="uil uil-pen"></i></a>
                                                                </td>
                                                            </c:when>
                                                        </c:choose>
                                                    </tr>
                                                </tbody>
                                            </c:forEach>
                                            <tr id="noResults" style="display: none;">
                                                <td colspan="3" style="color: red">Not found</td><!--<a href="#" class="btn btn-icon btn-pills btn-soft-danger" onclick="deleteSetting(${i.getId()})"><i class="uil uil-trash"></i></a>-->
                                                </tr>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                                <!--end row-->
                                <div class="row text-center">
                                    <c:set var="page" value="${page}"/>
                                    <div class="col-12" style="max-width:95%;
                                    margin: auto;
                                    margin-top:20px">
                                        <div class="pagination align-items-center text-center justify-content-between">
                                            <c:forEach begin="${1}" end="${numPage}" var="i">
                                                <a class="${i==page?"active" : ""}" href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.code}&page=${i}">
                                                    ${i}
                                                </a>
                                            </c:forEach>                                                                
                                        </div>
                                    </div>
                                    <!-- PAGINATION END -->
                                </div><!--end row-->
                            </div>
                        </div><!--end container-->

                        <!-- Footer Start -->
                        <%@include file="../common/footerforsidebar.jsp" %>
                        <!--end footer-->
                        <!-- End -->
                    </main>
                    <!--End page-content" -->
                </div>
                <!-- page-wrapper -->
                <!-- javascript -->
                <script src="../../assets/js/jquery.min.js"></script>
                <script src="../../assets/js/bootstrap.bundle.min.js"></script>
                <!-- simplebar -->
                <script src="../../assets/js/simplebar.min.js"></script>
                <!-- Select2 -->
                <script src="../../assets/js/select2.min.js"></script>
                <script src="../../assets/js/select2.init.js"></script>
                <!-- Datepicker -->
                <script src="../../assets/js/flatpickr.min.js"></script>
                <script src="../../assets/js/flatpickr.init.js"></script>
                <!-- Datepicker -->
                <script src="../../assets/js/jquery.timepicker.min.js"></script> 
                <script src="../../assets/js/timepicker.init.js"></script> 
                <!-- Icons -->
                <script src="../../assets/js/feather.min.js"></script>
                <!-- Main Js -->
                <script src="../../assets/js/app.js"></script>
                <!--<script src="../../JS/Po.js"></script>-->
                <script>
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
                </script>
            </body>

        </html>
