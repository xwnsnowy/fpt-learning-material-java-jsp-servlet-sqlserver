<%-- 
    Document   : addelective
    Created on : Jul 10, 2023, 10:52:05 PM
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
        <link rel="stylesheet" href="../../Style/Account.css">

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
                            <h5 class="mb-0">Add New Elective</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.code}">Elective List</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Add New Elective</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="card border-0 p-3  shadow" >
                            <div class="col-lg-12 mt-4">
                                <form id="formAdd" action="electivedetail?action=addnewelective" method="post" class="mt-4" onsubmit ="resetToast()">
                                    <div class="row">
                                        <input type="hidden" name="id"class="form-control" required="">
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Elective Name<span class="text-danger">*</span></label>
                                                <input id="sName" name="name"class="form-control" required="" maxlength="100">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Type<span class="text-danger">*</span></label>
                                                <input id="noCredit" name="type"class="form-control" placeholder="elective" readonly="">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">CurriCulum Name <span class="text-danger">*</span></label>
                                                <input id="noCredit" name="cName"class="form-control" value="${c.name}" readonly="">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">CurriCulum Code <span class="text-danger">*</span></label>
                                                <input id="noCredit" name="Ccode"class="form-control" value="${c.code}" readonly="">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Subject<span class="text-danger">*</span></label>
                                                <div class="col-12" style="max-height:100%; margin: auto;">
                                                    <div class="table-responsive bg-white shadow rounded">
                                                        <table class="table table-center table-padding mb-0 table2">
                                                            <thead>
                                                                <tr>
                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Subject ID</th>
                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Subject Code</th>
                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Subject Name</th>
                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Status</th>
                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">ADD</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody class="listSubject">
                                                                <c:forEach items="${listname}" var="list">
                                                                    <tr class="itemSubjects">
                                                                        <td class="p-3" style="text-align: center;"><b>${list.getId()}</b></td>
                                                                        <td class="p-3" style="text-align: center;"><b>${list.getCode()}</b></td>
                                                                        <td class="p-3" >${list.getName()}</td>
                                                                        <td class="p-3">
                                                                            <c:choose>
                                                                                <c:when test="${list.isActive == '1'}"><span class="badge bg-soft-success">Active</span>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <span class="badge bg-soft-warning">Non active</span>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>
                                                                        <td class="p-3"> 
                                                                            <input class="checkbox" type="checkbox" name="subjectId" value="${list.getId()}" id="subjectId_${list.getId()}">
                                                                        </td>    
                                                                    </tr>
                                                                </c:forEach> 
                                                            </tbody>
                                                        </table>
                                                    </div>         
                                                    <div class="col-sm-auto col-md-auto col-lg-auto ml-auto" style="width:20%; margin: auto;margin-top:20px">
                                                        <div class="d-grid">
                                                            <button type="submit" name='btnAddListSubject' class="btn btn-primary">Add</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                            <div class="col-md-2" style="display: none">
                                                <label class="form-label">Active</label>
                                                <div class="form-check form-switch">
                                                    <input style="height: 20px; width: 60px" class="form-check-input checkbox-row" type="checkbox" name="active" value="${e.getIs_active()}" <c:if test="${e.getIs_Active() == '0'}">checked</c:if>/>
                                                        <p id="isActive" class="badge bg-soft-warning"></p>
                                                    </div>
                                                </div>
                                        </c:if>
                                    </div>
                                    <div style="color: red">${MessUpdate}</div>

                                </form>

                            </div>
                        </div>
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
    </script>
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

</body>

</html>

