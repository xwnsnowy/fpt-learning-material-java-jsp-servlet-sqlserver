<%-- 
    Document   : elective
    Created on : Jun 11, 2023, 9:30:15 PM
    Author     : ADMIN
--%>

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
            margin-bottom: 20px;
            text-decoration:none;
        }
        .pagination a.active{
            background-color: #396cf0;
            color:white;
        }
        .pagination a:hover:not(.active){
            background-color: red;
            color:white;
        }
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
                            <h5 class="mb-0">Elective List</h5><br>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">HOME</a></li>
                                    <li class="breadcrumb-item">Curriculum</li>
                                    <li class="breadcrumb-item">Elective</li>
                                    <li class="breadcrumb-item active" aria-current="page">Elective List</li>
                                </ul>
                            </nav>
                        </div>
                        <div>
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
                            <form id="filter" action="elective?action=search&Ccode=${c.code}" method="post" style="margin-top: 10px"> 
                                <input type="hidden" name="thisPage" id="thisPage" value="${thisPage}" >
                                <div class="row justify-content-start align-items-center">

                                    <div class="col-sm-6 col-md-3">
                                        <div class="d-grid">
                                            <div class="input-group">
                                                <div class="input-group-prepend" >
                                                    <select name="selectFilter" class=" form-control time-during select2input" >
                                                        <option <c:if test="${selectFilter == 'name'}"> selected="" </c:if> value="name">Name Elective </option>
                                                        <option <c:if test="${selectFilter == 'id'}"> selected="" </c:if> value="id">Id Elective</option>
                                                        </select>
                                                    </div>
                                                    <input type="text" name="search" class=" form-control" placeholder="Search" id="myInput" value="${search}">
                                            </div>
                                        </div>
                                    </div><!--end col-->
                                    <div class="col-sm-auto col-md-auto col-lg-auto">
                                        <div class="d-grid m-1">
                                            <input type="submit" name="btn" class="btn btn-primary" value="Search">
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                        <div  class="col-sm-auto col-md-auto col-lg-auto">
                                            <div class="d-grid m-1 ">
                                                <a href="/FLM_NEW/view/crddstaff/electivedetail?action=add&Ccode=${c.code}" class="btn btn-success" ><i class="ri-add-circle-line align-middle me-2"></i>Add New Elective</a>
                                            </div>
                                        </div><!--end col-->
                                    </c:if>
                                </div>
                            </form>
                        </div><!--end row-->
                        <div class=" row">
                            <div class="   col-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <table class="table table-center bg-white mb-0">
                                        <thead>
                                            <tr id="tr">
                                                <th class="border-bottom p-3" style="max-width: 70px;">Elective ID </th>
                                                <th class="border-bottom p-3" style="min-width: 250px;">Elective Name</th>
                                                <th class="border-bottom p-3" style="min-width: 300px;"> Subject</th>
                                                    <c:choose>
                                                        <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                        <th class="border-bottom p-3" style="min-width: 50px;">Active</th>
                                                        <th></th>
                                                        </c:when>
                                                    </c:choose>
                                            </tr>
                                        </thead>
                                        <c:forEach  var="o" items="${listPage}" >
                                            <c:set var="t" value="${t+1}"/>
                                            <tbody>
                                                <tr <c:if test="${o.is_active == '0' && sessionScope.acc.getRoleName() != 'CRDD STAFF'|| o.is_active == '0' && sessionScope.acc.getRoleName() != 'ADMIN'}">hidden-row</c:if>>
                                                    <td class="border-bottom p-3" style="min-width: 30px;">${o.id}</td>
                                                    <td class="border-bottom p-3" style="min-width: 250px;">${o.name}</td>
                                                    <td class="border-bottom p-3" style="min-width: 250px;"><a href="/FLM_NEW/view/common/elective?action=electivesubject&id=${o.id}&Ccode=${c.code}">${o.name}</a></td>
                                                        <c:choose>
                                                            <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                            <td class="border-bottom p-3" style="min-width: 70px;">
                                                                <c:choose>
                                                                    <c:when test="${o.is_active == '1'}">
                                                                        <span class="badge bg-soft-success">Active</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge bg-soft-warning">Non active</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="border-bottom p-3" style="min-width: 70px;">
                                                                <a class="btn btn-icon btn-pills btn-soft-success"   href="/FLM_NEW/view/crddstaff/electivedetail?action=electivedetail&Ccode=${c.code}&amp;id=${o.id}"><i class="uil uil-pen"></i></a>
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
                                <div class="row text-center">
                                    <c:set var="page" value="${page}"/>
                                    <div class="col-12" style="max-width:95%;margin: auto; margin-top:20px">
                                        <div class="pagination align-items-center text-center justify-content-between">
                                            <c:forEach begin="${1}" end="${numPage}" var="i">
                                                <a class="${i==page?"active" : ""}" href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.code}&page=${i}">
                                                    ${i}
                                                </a>
                                            </c:forEach>                                                                
                                        </div>
                                    </div>
                                    <!-- PAGINATION END -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="../common/footerforsidebar.jsp" %>
        </div>
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
<!-- pt src="../../assets/js/flatpickr.min.js"></script>
<scriDatepicker -->
<script src="../../assets/js/flatpickr.init.js"></script>
<!-- Datepicker -->
<script src="../../assets/js/jquery.timepicker.min.js"></script> 
<script src="../../assets/js/timepicker.init.js"></script> 
<!-- Icons -->
<script src="../../assets/js/feather.min.js"></script>
<!-- Main Js -->
<script src="../../assets/js/app.js"></script>

</body>

</html>
