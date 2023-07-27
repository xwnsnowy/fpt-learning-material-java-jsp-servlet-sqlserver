<%-- 
    Document   : addplos
    Created on : Jun 3, 2023, 2:59:50 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta charset="utf-8" />
        <title>FLM_USER MANAGEMENT</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="../../../index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="../../assets/images/flm-dark.png">
        <!-- Bootstrap -->
        <link href="../../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="../../assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="../../assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="../../assets/css/flatpickr.min.css">
        <!-- Icons -->
        <link href="../../assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="../../assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="../../assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
         <link rel="stylesheet" href="../../assets/css/toast.style.css"/>
        <!--toast-js-->
        <script src="../../assets/js/jquery.min.js"></script>
        <script src="../../assets/js/toast.script.js"></script>
    </head>
    <style>
        .table1, th, td {
            border: 1px solid #dee2e6;
            vertical-align: top;
            text-align: left;
            padding: 8px;
            border-collapse: collapse;
        }
        .hidden-row {
            display: none;
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
                            <h5 class="mb-0">CPO_PLO Mapping </h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/syllabus?action=overview&sylId=${sylId}">CLOs</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">CLOs-PLOs</li>
                                </ul>
                            </nav>
                        </div>
                        <div class="row">

                            <div class="col-lg-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <form action="clo?action=mapping&sylId=${syllabus.id}" method="post">
                                        <c:forEach var="o" items="${listC}" varStatus="rowIndex">
                                            <table class="table table-center bg-white mb-0">
                                                <thead>
                                                    <tr class="headtable">
                                                        <td colspan="100%" class="border-bottom p-9" style="background-color:#396cf0;text-align: center;height:60px; color: #ffffff">Mapping PLOs to CLOs ${o.code}</td>
                                                    </tr>
                                                </thead>
                                                <tr>
                                                    <th class="border-bottom p-3" style="text-align: center">PLO_CLO </th>
                                                    <!-- list CLOs -->
                                                    <c:forEach var="listCLO" items="${listCLO}">
                                                        <th class="border-bottom p-3" style="min-width: 120px;text-align: center">${listCLO.clo_name} </th>
                                                        </c:forEach>
                                                </tr>
                                                <tbody>
                                                    <c:forEach var="plo" items="${allListPLO[rowIndex.index]}">
                                                        <tr class="ListPLO <c:if test="${plo.getIs_Active() == '0' && sessionScope.acc.getRoleName() != 'ADMIN' && sessionScope.acc.getRoleName() != 'CRDD HEAD'}">hidden-row</c:if>">
                                                                <!--list name plo-->
                                                                <td class="p-3">${plo.plo_name}</td>
                                                            <!--end list plo-->
                                                            <c:forEach var="clo" items="${listCLO}">
                                                                <c:set var="para" value="mapping${plo.plo_id}_${clo.clo_id}" />
                                                                <c:set var="checked" value="${mappingStatus[para]}" />
                                                                <td style="text-align: center;">
                                                                    <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN'|| sessionScope.acc.getRoleName() == 'CRDD HEAD'}">
                                                            <li class="d-flex justify-content-center" style="text-align: center;">
                                                                <div class="form-check form-switch">
                                                                    <input class="form-check-input" type="checkbox" name="mapping${plo.plo_id}_${clo.clo_id}" value="0" <c:if test="${checked}">checked</c:if> />
                                                                        <p id="isActive" class="badge bg-soft-warning"></p>
                                                                    </div>
                                                                </li>
                                                        </c:if>
                                                        <c:if test="${sessionScope.acc.getRoleName() != 'ADMIN' && sessionScope.acc.getRoleName() != 'CRDD HEAD'}">
                                                            <c:if test="${checked}">✓</c:if>
                                                        </c:if>
                                                        </td>
                                                    </c:forEach>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:forEach>
                                        <br>
                                        <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD'}">
                                            <!--<button  type="submit">Save Change</button>-->
                                            <div>
                                                <input class="btn btn-outline-secondary" type="submit" value="Save Change" name="save">
                                            </div>
                                        </c:if>
                                        <!--end row-->
                                    </form>


                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--end container-->

                <!-- Footer Start -->
                <%@include file="../common/footerforsidebar.jsp" %>
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->
        <!-- Modal end -->

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
        <!-- Icons -->
        <script src="../../assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="../../assets/js/app.js"></script>
        <script>
            window.addEventListener('DOMContentLoaded', function() {
            var isAdmin = "${sessionScope.acc.getRoleName()}" === "ADMIN";
                    var rows = document.querySelectorAll('.ListPLO');
                    rows.forEach(function(row, index) {
                    var isActiveColumn = row.querySelector('td:nth-child(5)');
                            var isActive = isActiveColumn.textContent;
                            if (!isAdmin && isActive === '0') {
                    row.classList.add('hidden-row');
                    } else {
                    row.querySelector('td:first-child').textContent = index - 1;
                    }
                    });
            };
        </script>
    </body>
</html>