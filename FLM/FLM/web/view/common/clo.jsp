<%-- 
    Document   : accountList
    Created on : May 23, 2023, 2:18:24 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <!--<link rel="stylesheet" href="../../assets/datatables/dataTables.bootstrap.css">-->
        <!--<link rel="stylesheet" href="../../assets/bootstrap/css/bootstrap.min.css">-->  
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
        <!-- Bootstrap 3.3.6 -->
        <!--<link rel="stylesheet" href="../../assets/bootstrap/css/bootstrap.min.css">-->      
        <link rel="stylesheet" href="../../Style/CurriculumSubject.css">
        <link rel="stylesheet" href="../../assets/css/toast.style.css"/>
        <!--toast-js-->
        <script src="../../assets/js/jquery.min.js"></script>
        <script src="../../assets/js/toast.script.js"></script>
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
        <c:if test="${toast == 'true'}">
            <script>
                function addToast(title, content, type, count) {
                    $.Toast(title, content, type, {
                        has_icon: true,
                        has_close_btn: true,
                        stack: true,
                        fullscreen: false,
                        position_class: "toast-top-right",
                        timeout: count,
                        sticky: false,
                        has_progress: true,
                        rtl: false
                    });
                }
                ;
                // type: success, warning, error, info or notice
                //            addToast(title, content, type, count)
                window.onload = addToast("Success", "Update CLO successfully!", "success", 5000);
            </script>
        </c:if>
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

                <div class="container-fluid">

                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">CLO List</h5><br>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item">Syllabus</li>
                                    <li class="breadcrumb-item">CLOs</li>
                                    <li class="breadcrumb-item active" aria-current="page"><a href="account">CLO List</a></li>
                                </ul>
                            </nav>
                            <!--<input type="text" hidden name ="syllabusId" value="1">-->
                        </div>
                        <table class="table1">
                            <tr>
                                <td>Syllabus ID </td>
                                <td><b>${syllabus.id}</b></td>
                            </tr>
                            <tr>
                                <td>Syllabus Code</td>
                                <td name ='subject_code'><b>${syllabus.getSubject().getCode()}</b></td>
                            </tr>
                            <tr>
                                <td>Syllabus Name</td>
                                <td><b>${syllabus.getName()}</b></td>
                            </tr>
                        </table>
                        <div style="padding-top: 20px;" class="col-sm-12 col-md-12 col-lg-12 d-flex justify-content-lg-start">

                            <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' ||  sessionScope.acc.getRoleName() == 'CRDD STAFF'}">    
                                <div class="d-grid" style="padding-right: 20px;">
                                    <a href="/FLM_NEW/view/common/clo?action=add&sylId=${syllabus.getId()}" class="btn btn-success">Add New CLOs</a>
                                </div>
                                <div class="d-grid" style="padding-right: 20px;">
                                    <!--<a  class="btn btn-success">Import File</a>-->
                                    <input  class="btn btn-success" type="button" id="importButton" value="Import File">
                                    <form id="fExcel" action="import?action=importClo" method="post" enctype="multipart/form-data">
                                        <input hidden type="file" id="file" name="file" accept=".xlsx, .xls">
                                        <input hidden value="${syllabus.getId()}" name='sylId'>
                                        <input type="text" hidden value="${idCurrent}" name="idCurrent" />
                                        <!--<input type="submit"  value="abcd" name="idCurrent" />-->
                                    </form>
                                    <script>
                                        const importButton = document.getElementById('importButton');
                                        const fileInput = document.querySelector('#fExcel input[type="file"]');
                                        const form = document.getElementById('fExcel');

                                        importButton.addEventListener('click', () => {
                                            fileInput.click();
                                        });
                                        fileInput.addEventListener('change', () => {
                                            form.submit();
                                        });
                                    </script>
                                    <c:forEach items="${rowErrors}" var="error">
                                        <div>${error}</div>
                                    </c:forEach>

                                </div>
                                <button style="margin-right: 20px;" class="btn btn-success" onclick="exportTemplate()" >Export File</button>
                                <!--<a href="https://docs.google.com/spreadsheets/d/1IapqfXMDHLMQ05vtnto0CpxWYMdjmAdPCLp5JnEF844/export?format=xlsx">Tải về tệp tin Excel</a>-->

                            </c:if>
                            <div class="d-grid" style="padding-right: 20px;">
                                <a href="/FLM_NEW/view/common/clo?action=mapping&sylId=${syllabus.getId()}" class="btn btn-success">CLOs_PLOs</a>
                            </div>
                            <!--<input class="btn btn-sm btn-success " type="button" id="importButton" value="Import File">-->


                        </div>
                        <div>
                            <form id="filter" action="clo?action=search&sylId=${syllabus.getId()}" method="post">
                                <div class="row">
                                    <div class="col-sm-3 col-md-3" style="padding-top: 20px">
                                        <div class="mb-0 position-relative">
                                            <select name="selectFilter"  class="form-control time-during select2input" >
                                                <c:choose>
                                                    <c:when test="${selectFilter == ''}">
                                                        <option selected="" value="${selectFilter}">All Type</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="">All Type</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:forEach items="${listStatus}" var="listStatus">
                                                    <c:choose>
                                                        <c:when test="${selectFilter == listStatus.getIs_active()}">
                                                            <option selected="" value="${listStatus.getIs_active()}">
                                                                <c:choose>
                                                                    <c:when test="${listStatus.getIs_active() == 1}">Active</c:when>
                                                                    <c:otherwise>Inactive</c:otherwise>
                                                                </c:choose>
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${listStatus.getIs_active()}">
                                                                <c:choose>
                                                                    <c:when test="${listStatus.getIs_active() == 1}">Active</c:when>
                                                                    <c:otherwise>Inactive</c:otherwise>
                                                                </c:choose>
                                                            </option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-auto col-md-auto">
                                        <div class="d-grid" style="padding-top: 20px">
                                            <input style="line-height: 33px" type="text" name="search" placeholder="Search" value="${search}">
                                        </div>
                                    </div>
                                    <div class="col-sm-auto col-md-auto" style="padding-top: 20px;">
                                        <div class="d-grid">
                                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#appointmentform">Search</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-12 mt-4">
                            <div class="table-responsive shadow rounded">
                                <table id="table" class="  table table-center bg-white mb-0">
                                    <thead>
                                        <tr id="tr">
                                            <th class="border-bottom p-3" style="max-width: 30px;"> ID CLOs</th>
                                            <th class="border-bottom p-3" style="min-width: 90px;">CLO Name</th>
                                                <c:choose>
                                                    <c:when test="${sessionScope.acc.getRoleName() == null || sessionScope.acc.getRoleName() == 'STUDENT' }">
                                                    <th class="border-bottom p-3" style="min-width: 150px;">CLO Description</th>
                                                    <!--<th hidden=""class="border-bottom p-3" style="max-width: 5px;"></th>-->
                                                    <!--<th hidden=""class="border-bottom p-3" style="max-width: 5px;"></th>-->
                                                    </c:when>
                                                </c:choose>
                                            <th class="border-bottom p-3" style="min-width: 90px;">Status</th>
                                                <c:choose>
                                                    <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' ||  sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                    <th class="border-bottom p-3" style="max-width: 50px;">Create_by</th>
                                                    <th class="border-bottom p-3" style="max-width: 120px;"></th>
                                                    </c:when>
                                                </c:choose>
                                        </tr>
                                    </thead>
                                    <c:forEach var="o" items="${listCLO}">
                                        <c:choose>
                                            <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                <tr class="listCLO">
                                                    <td class="border-bottom p-3" style="min-width: 100px;">${o.getClo_id()}</td>
                                                    <td class="border-bottom p-3" style="min-width: 110px;">${o.getClo_name()}</td>
                                                    <td class="border-bottom p-3" style="min-width: 70px;">
                                                        <c:choose>
                                                            <c:when test="${o.getIs_active() == '1'}">
                                                                <span class="badge bg-soft-warning">Active</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="badge bg-soft-warning">Inactive</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="border-bottom p-3" style="min-width: 50px;">${o.created_by}</td>
                                                    <td class="border-bottom p-3" style="min-width: 100px;">
                                                        <a class="btn btn-icon btn-pills btn-soft-success" href="/FLM_NEW/view/admin/clo?action=details&sylId=${syllabus.getId()}&clo_id=${o.clo_id}">
                                                            <i class="uil uil-pen"></i>
                                                        </a>
                                                        <span class=" form-switch">
                                                            <input id="checkbox-${o.getClo_id()}" style="height: 20px; width: 40px" onclick="changeStatus('${o.getClo_id()}', this)" class="form-check-input checkbox-row" type="checkbox" <c:if test="${o.is_active == '1'}">checked</c:if> />
                                                                <p id="isActive" class="badge bg-soft-warning"></p>
                                                            </span>
                                                        </td>
                                                </c:when>
                                                <c:when test="${sessionScope.acc.getRoleName() == null || sessionScope.acc.getRoleName() == 'STUDENT' || sessionScope.acc.getRoleName() == 'TEACHER'}">
                                                    <c:if test="${o.getIs_active() == '1'}">
                                                    <tr class="listCLO">
                                                        <td class="border-bottom p-3" style="min-width: 100px;">${o.getClo_id()}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${o.getClo_name()}</td>
                                                        <td class="border-bottom p-3" style="height: 100px;width: 800px">${o.getClo_description()}</td>                               
                                                        <td class="border-bottom p-3" style="min-width: 30px;"><span class="badge bg-soft-warning">${o.getIs_active() == '1'}</span></td>
                                                        <td hidden="" class="border-bottom p-3" style="max-width: 5px;"></td>
                                                        <td hidden="" class="border-bottom p-3" style="max-width: 5px;"></td>
                                                    <tr class="listCLO">
                                                    </c:if>
                                                </c:when>
                                            </c:choose>

                                        </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                        <div style="color: red">${alert}</div>
                    <form id="change" action="clo?action=changestatus&sylId=${syllabus.getId()}&page=${page}" method="post">
                        <input id="Clo_id" type="hidden" name="Clo_id">
                        <input id="active" type="hidden" name="active">
                    </form>
                    <!--end row-->
                </div>
                <div class="row text-center">
                    <c:set var="page" value="${page}"/>
                    <div class="col-1" style="max-width:95%;margin: auto; margin-top:20px">
                        <div class="pagination align-items-center text-center justify-content-between">
                            <c:forEach begin="1" end="${numPage}" var="i">
                                <a class="${i==page?"active" : ""} "href="/FLM_NEW/view/common/clo?action=list&sylId=${syllabus.getId()}&page=${i}">
                                    ${i}
                                </a>
                            </c:forEach>                                                                
                        </div>
                    </div>
                </div>


                <!--end container-->

                <!-- Footer Start -->
                <%@include file="../common/footerforsidebar.jsp" %>

                <!--end footer-->
                <!-- End -->

            </main>
            <!--End page-content" -->

        </div>
        <!-- page-wrapper -->



        <!--<script src="../../assets/jQuery/jquery-2.2.3.min.js"></script>-->
        <!-- Bootstrap 3.3.6 -->
        <!--<script src="bootstrap/js/bootstrap.min.js"></script>-->
        <!-- DataTables -->
        <script src="../../assets/datatables/jquery.dataTables.min.js"></script>
        <!--<script src="../../assets/js/jquery.min.js"></script>-->
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
        <!--<script src="../../JS/AccountList.js"></script>-->
        <script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
        <!-- page script -->
        <script src="../../JS/Clo.js"></script>
        <script>
                        $(function () {
                            $('#table').DataTable({
                                "paging": false,
                                "lengthChange": false,
                                "searching": false,
                                "ordering": true,
                                "info": false,
                                "autoWidth": true
                            });
                        });
        </script>

    </body>

</html>
