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
        <title>FLM_USER MANAGERMENT</title>
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
        <style>
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


    </head>

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
                            <h5 class="mb-0">Setting List</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item active" aria-current="">SETTING LIST</li>
                                </ul>
                            </nav>
                        </div>
                        <form action="setting?action=search" method="post">
                            <div class="row">
                                <div class="col-sm-6 col-md-3">
                                    <div class="d-grid">
                                        <input style="line-height: 33px" type="text" name="search" placeholder="Search" value="${search}">
                                    </div>
                                </div><!--end col-->
                                <div class="col-sm-6 col-md-1">
                                    <div class="d-grid">
                                        <input type="submit" name="btn" class="btn btn-primary" value="Search">
                                    </div>
                                </div><!--end col-->
                            </div>
                        </form>
                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <table class="table table-center bg-white mb-0" id="dataTable">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" style="min-width: 50px;">Id</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Name</th>
                                                <th class="border-bottom p-3">Type</th>
                                                <th class="border-bottom p-3">Value</th>
                                                <th class="border-bottom p-3">Display Order</th>
                                                <th class="border-bottom p-3">Status</th>
                                                <th class="border-bottom p-3" style="min-width: 100px;">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody class="listSetting" id="tableBody">
                                            <c:forEach items="${listPage}" var="i">
                                                <tr class="itemSetting">
                                                    <td class="p-3">${i.getId()}</td>
                                                    <td class="py-3">
                                                        <a href="#" class="text-dark">
                                                            <div class="d-flex align-items-center">
                                                                <span class="ms-2">${i.getName()}</span>
                                                            </div>
                                                        </a>
                                                    </td>
                                                    <td class="p-3">${i.getType()}</td>
                                                    <td class="p-3">${i.getValue()}</td>
                                                    <td class="p-3">${i.getDisplayOrder()}</td>
                                                    <c:choose>
                                                        <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN'}">
                                                            <td class="border-bottom p-3" style="min-width: 70px;">
                                                                <c:choose>
                                                                    <c:when test="${i.getIs_active() == '1'}">
                                                                        <span class="badge bg-soft-success">Active</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge bg-soft-warning">Non active</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="border-bottom p-3" style="min-width: 70px;">
                                                                <a class="btn btn-icon btn-pills btn-soft-success"   href="/FLM_NEW/view/admin/settingdetail?&mod=2&id=${i.getId()}"><i class="uil uil-pen"></i></a>
                                                            </td>
                                                        </c:when>
                                                    </c:choose>
                                                </tr>
                                            </c:forEach> 
                                        </tbody>
                                    </table>  
                                </div>
                            </div>
                        </div><!--end row-->

                        <div class="row text-center">
                            <c:set var="page" value="${page}"/>
                            <div class="col-12" style="max-width:95%;margin: auto; margin-top:20px">
                                <div class="pagination align-items-center text-center justify-content-between">
                                    <c:forEach begin="${1}" end="${numPage}" var="i">
                                        <a class="${i==page?"active" : ""}" href="/FLM_NEW/view/admin/setting?action=list&page=${i}">
                                            ${i}
                                        </a>
                                    </c:forEach>                                                                
                                </div>
                            </div>
                            <!-- PAGINATION END -->
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
        <!--setting list-->
    </body>

</html>
