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
                window.onload = addToast("Success", "Update PLO successfully!", "success", 5000);
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
                            <h5 class="mb-0">PLO List</h5><br>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item">Curriculum</li>
                                    <li class="breadcrumb-item">PLOs</li>
                                    <li class="breadcrumb-item active" aria-current="page"><a href="account">PLO List</a></li>
                                </ul>
                            </nav>

                        </div>
                        <table class="table1">
                            <tr>
                                <td>CurriCulum ID </td>
                                <td><b>${c.curriculum_id}<b></td>
                                            <input type="hidden" name="cid" value="${c.curriculum_id}">
                                            </tr>
                                            <tr>
                                                <td>CurriCulum Code</td>
                                                <td><b>${c.code}</b></td>
                                            </tr>
                                            <tr>
                                                <td>CurriCulum Name</td>
                                                <td><b>${c.name}</b> </td>
                                            </tr>
                                            </table>
                                            <div style="padding-top: 20px;" class="col-sm-12 col-md-12 col-lg-12 d-flex justify-content-lg-start">
                                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' ||  sessionScope.acc.getRoleName() == 'CRDD STAFF'}">    
                                                    <div class="d-grid" style="padding-right: 20px;">
                                                        <a href="/FLM_NEW/view/admin/addPLOs?Ccode=${c.getCode()}" class="btn btn-success">Add New PLOs</a>
                                                    </div>
                                                    <div class="d-grid" style="padding-right: 20px;">
                                                        <!--<a  class="btn btn-success">Import File</a>-->
                                                        <input  class="btn btn-success" type="button" id="importButton" value="Import File">
                                                        <form id="fExcel" action="import?action=importPlo" method="post" enctype="multipart/form-data">
                                                            <input hidden type="file" id="file" name="file" accept=".xlsx, .xls">
                                                            <input hidden value="${c.code}" name='Ccode'>
                                                            <input hidden value="${c.curriculum_id}" name='Cid'>
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
                                            </div>
                                            <div>
                                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN'}" >
                                                    <form id="filter" action="plo?action=search&Cid=${c.curriculum_id}" method="post">
                                                        <input type="hidden" value="${c.curriculum_id}" name ="Cid"/>
                                                        <input type="hidden" value="${c.code}" name ="Ccode"/>
                                                        <div class="row">
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
                                                </c:if>     
                                            </div>
                                            <div class=" row">
                                                <div class="   col-12 mt-4">
                                                    <div class="table-responsive shadow rounded">
                                                        <table class="  table table-center bg-white mb-0">
                                                            <thead>
                                                                <tr id="tr" >
                                                                    <th class="border-bottom p-3" style="min-width: 50px;">ID PLOs</th>
                                                                    <th class="border-bottom p-3" style="min-width: 90px;">PLO Name</th>
                                                                    <th class="border-bottom p-3" style="min-width: 150px;">PLO Description</th>

                                                                    <c:choose>
                                                                        <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD STAFF' || sessionScope.acc.getRoleName() == 'CRDD HEAD'}">
                                                                            <th class="border-bottom p-3" style="min-width: 50px;">Active</th>
                                                                            <th class="border-bottom p-3" style="min-width: 90px;">Create_At</th>
                                                                            <th class="border-bottom p-3" style="min-width: 120px;"></th>

                                                                        </c:when>
                                                                    </c:choose>
                                                                </tr>
                                                            </thead>
                                                            <c:forEach  var="o" items="${ListPLO}" >
                                                                <tr class="ListPLO <c:if test="${o.getIs_Active() == '0' &&( sessionScope.acc.getRoleName() != 'ADMIN' && sessionScope.acc.getRoleName() != 'CRDD STAFF' && sessionScope.acc.getRoleName() != 'CRDD HEAD') }">hidden-row</c:if>">

                                                                        <td class="border-bottom p-3" style="min-width: 30px;">${o.getPlo_id()}</td>
                                                                    <td class="border-bottom p-3" style="min-width: 70px;">${o.getPlo_name()}</td>
                                                                    <td class="border-bottom p-3" style="height: 100px;width: 800px">${o.getPlo_description()}</td>
                                                                    <c:choose>
                                                                        <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD STAFF' || sessionScope.acc.getRoleName() == 'CRDD HEAD'}">
                                                                            <td class="border-bottom p-3" style="min-width: 70px;">
                                                                                <c:choose>
                                                                                    <c:when test="${o.getIs_Active() == '1'}">
                                                                                        <span class="badge bg-soft-warning">Active</span>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <span class="badge bg-soft-warning">Non active</span>

                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </td>
                                                                            <td class="border-bottom p-3" style="min-width: 30px;">${o.getCreated_by()}</td>
                                                                            <td class="border-bottom p-3" style="min-width: 70px;">
                                                                                <!--<a class="btn btn-icon btn-pills btn-soft-success"   href="/FLM_NEW/view/admin/plodetails?plo_id=${o.plo_id}&amp;Ccode=${c.code}">-->
                                                                                <a class="btn btn-icon btn-pills btn-soft-success"   href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}&amp;plo_id=${o.plo_id}">
                                                                                    <i class="uil uil-pen"></i></a>
                                                                                <span class=" form-switch">
                                                                                    <input id="checkbox-${o.getPlo_id()}" style="height: 20px; width: 40px" onclick="changeStatus('${o.getPlo_id()}', this)" class="form-check-input checkbox-row" type="checkbox" <c:if test="${o.is_Active == '1'}">checked</c:if> />
                                                                                        <p id="isActive" class="badge bg-soft-warning"></p>
                                                                                    </span>



                                                                                </td>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row text-center">
                                                <c:set var="page" value="${page}"/>
                                                <div class="col-1" style="max-width:95%;margin: auto; margin-top:20px">
                                                    <div class="pagination align-items-center text-center justify-content-between">
                                                        <c:forEach begin="1" end="${numPage}" var="i">
                                                            <a class="${i==page?"active" : ""} "href="/FLM_NEW/view/common/plo?Ccode=${c.code}&page=${i}">
                                                                ${i}
                                                            </a>
                                                        </c:forEach>                                                                
                                                    </div>
                                                </div>
                                            </div>


                                            <!--end row-->
                                            </div>
                                            </div><!--end container-->
                                            <form id="change" action="plo?action=changestatus&Ccode=${c.code}" method="post">
                                                <input id="Plo_id" type="hidden" name="Plo_id">
                                                <input id="active" type="hidden" name="active">
                                            </form>
                                            <!-- Footer Start -->
                                            <%@include file="../common/footerforsidebar.jsp" %>
                                            <!--end footer-->
                                            <!-- End -->
                                            </main>
                                            <!--End page-content" -->
                                            </div>
                                            <!-- page-wrapper -->



                                            <!-- javascript -->
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
                                            <script src ="../../JS/Plo.js"></script>
                                            <script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
                                            </body>

                                            </html>
