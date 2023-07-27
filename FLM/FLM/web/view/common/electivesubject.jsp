<%-- 
    Document   : electivedetail
    Created on : Jun 4, 2023, 2:44:53 PM
    Author     : ADMIN
--%>

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
        <style>
            .invalid {
                border-color: red;
            }

            .alert {
                color: red;
                font-size: 12px;
            }
            .hidden {
                display: none;
            }
            #tr{
                background-color:#396cf0;
                color: #ffffff;
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
        <style>

        </style>
        <div class="page-wrapper doctris-theme toggled">
            <%@include file="../common/sidebar.jsp" %>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <%@include file="../common/headerforsidebar.jsp" %>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item" href="/FLM_NEW/view/common/home">Home</li>
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.code}">Elective List</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Elective Details</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <h3>Elective Details</h3>
                            <div class="row table1" style="max-width: 50%"> 
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>Elective Name</th>
                                            <td>${e.name}</td>
                                        </tr>
                                        <tr>
                                            <td>Note</td>
                                            <td></td>
                                        </tr>
                                    </thead>
                                </table>
                            </div>

                            <div class="row">     
                                <div class="col-12 mt-4" style="max-width:100%; margin: auto; margin-top: 10px">
                                    <c:if test="${sizePage != 0}" >
                                        <h3 style="margin-top:10px">List Subject Elective</h3>
                                        <form id="filter" action="elective?action=searchsubject&Ccode=${c.code}" method="post" style="margin-top: 10px"> 
                                            <input type="hidden" name="thisPage" id="thisPage" value="${thisPage}" >
                                            <input type="hidden" name="name" value="${e.name}">
                                            <input type="hidden" name="id" value="${e.id}">
                                            <input type="hidden" name="active" value="${e.is_active}">
                                            <div class="row justify-content-start align-items-center">

                                                <div class="col-sm-3 col-md-3">
                                                    <div class="d-grid">
                                                        <div class="input-group">
                                                            <input type="text" name="search" class=" form-control" placeholder="Search" id="myInput" value="${search}">
                                                        </div>
                                                    </div>
                                                </div><!--end col-->
                                                <div class="col-sm-auto col-md-auto col-lg-auto">
                                                    <div class="d-grid m-1">
                                                        <input type="submit" name="btn" class="btn btn-primary" value="Search">
                                                    </div>
                                                </div><!--end col-->
                                            </div>
                                        </form>
                                        <div class="table-responsive shadow rounded">                           
                                            <table class="table table-center bg-white mb-0" >
                                                <thead>
                                                    <tr id="tr">
                                                        <th class="border-bottom p-3" style="max-width: 70px;">Subject ID </th>
                                                        <th class="border-bottom p-3" style="max-width: 70px;">Subject Code</th>
                                                        <th class="border-bottom p-3" style="min-width: 250px;">Subject Name</th>
                                                            <c:choose>
                                                                <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                                <th class="border-bottom p-3" style="min-width: 50px;">Active</th>
                                                                <th></th>
                                                                </c:when>
                                                            </c:choose>
                                                    </tr>
                                                </thead>
                                                <tbody >
                                                    <c:forEach items="${listPage}" var="list">
                                                        <tr>
                                                            <td class="p-3" style="text-align: center" ><b>${list.id}</b></td>
                                                            <td class="p-3" >${list.code}</td>
                                                            <td class="p-3" >${list.name}</td>
                                                            <c:choose>
                                                                <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                                    <td class="border-bottom p-3" style="min-width: 70px;">
                                                                        <c:choose>
                                                                            <c:when test="${list.isActive == '1'}">
                                                                                <span class="badge bg-soft-success">Active</span>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <span class="badge bg-soft-warning">Non active</span>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </td>
                                                                    <td class="border-bottom p-3" style="max-width: 40px;">
                                                                        <a class="btn btn-icon btn-pills btn-soft-success"   href="/FLM_NEW/view/crddstaff/electivedetail?action=editelective&Ccode=${c.code}&amp;id=${list.id}"><i class="uil uil-pen"></i></a>
                                                                    </td>
                                                                </c:when>
                                                            </c:choose>
                                                        </tr>
                                                    </c:forEach>  
                                                </tbody>
                                            </table>
                                        </div>     
                                    </c:if>
                                </div>       
                                <div class="row text-center">
                                    <c:set var="page" value="${page}"/>
                                    <div class="col-12" style="max-width:95%;margin: auto; margin-top:20px">
                                        <div class="pagination align-items-center text-center justify-content-between">
                                            <c:forEach begin="${1}" end="${numPage}" var="i">
                                                <a class="${i==page?"active" : ""}" href="/FLM_NEW/view/common/elective?action=electivesubject&id=${e.id}&Ccode=${c.code}&page=${i}">
                                                    ${i}
                                                </a>
                                            </c:forEach>                                                                
                                        </div>
                                    </div>
                                    <!-- PAGINATION END -->
                                </div><!--end row-->
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
        <!-- Icons -->
        <script src="../../assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="../../assets/js/app.js"></script>
        <script src="../../JS/Po.js"></script>
    </body>
