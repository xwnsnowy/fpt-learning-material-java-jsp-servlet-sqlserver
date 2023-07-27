<%-- 
Document   : accountList
Created on : May 23, 2023, 2:18:24 AM
Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" >
        <title>Combo Management</title>
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
        <style>
            .cell-breakWord {
                word-wrap: break-word;
                max-width: 1px;
                -webkit-hyphens: auto; /* iOS 4.2+ */
                -moz-hyphens: auto; /* Firefox 5+ */
                -ms-hyphens: auto; /* IE 10+ */
                hyphens: auto;
            }
            .table1, th, td {
                border: 1px solid #dee2e6;
                vertical-align: top;
                text-align: left;
                padding: 8px;
                border-collapse: collapse;
            }

            .border-bottom p-3 {
                /*            border: 1px solid #dee2e6;*/
                vertical-align: top;
                text-align: left;
            }
            .p-3 {
                /*            border: px solid rgba(0,0,0,.05);*/
                vertical-align: top;
                text-align: left;
            }
            .mb-0 tr:nth-child(odd) {
                background-color: #f2f2f2;
            }
            .mb-0 tr:hover {
                background-color: #ddd;
                ;
            }
            .pagination{
                display: inline-block;
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
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div>
                            <div class="row" >

                                <div class="col-12" style="max-width:95%;margin: auto">
                                    <div style="display:flex"><h2 >Combo Management</h2>
                                    </div>
                                </div>
                                <form id="filter" action="combolistfull?action=list" method="POST">
                                    <div class="col-12" style="max-width:95%;margin: auto">
                                        <div style="display:flex">
                                            <div  class="col-sm-auto col-md-auto col-lg-auto" style="margin-right:10px">
                                                <input type="hidden" name="start" id="start">
                                                <input type="hidden" name="end" id="end">
                                                <input type="hidden" name="thisPage" id="thisPage" value="${thisPage}">
                                                <div class="d-grid">
                                                    <select name="selectFilter"  class="form-control time-during select2input" >
                                                        <option <c:if test="${selectFilter == 'name'}"> selected="" </c:if> value="name">Combo Name</option>
                                                        <option <c:if test="${selectFilter == 'activate'}"> selected="" </c:if> value="active">Activate</option>                                                       
                                                        <option <c:if test="${selectFilter == 'deactive'}"> selected="" </c:if> value="deactive">Deactive</option>
                                                        </select>                                            
                                                    </div>
                                                </div>
                                                <div  class="col-sm-auto col-md-auto col-lg-auto" style="margin-right:10px">
                                                    <div class="d-grid">
                                                        <input id="myInput" style="line-height: 33px" type="text" name="search" placeholder="Search" value="${search}">
                                                </div>
                                            </div>
                                            <div  class="col-sm-auto col-md-auto col-lg-auto" >
                                                <div class="d-grid">
                                                    <button type="button" name='btnSearch'  class="btn btn-primary" onclick='cleanThisPage()'>Search</button>
                                                </div>
                                            </div>  

                                            <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN'}" >

                                                <div  class="col-sm-auto col-md-auto col-lg-auto" style="margin-left:10px">
                                                    <div class="d-grid">
                                                        <a href="/FLM_NEW/view/admin/combolistfull?action=newcombo" class="btn btn-success" >Add New Combo</a>
                                                    </div>
                                                </div>
                                            </c:if>

                                        </div>
                                    </div>
                                    <div class="col-12" style="max-width:95%;margin: auto">
                                        <div style="display:flex">                                                        
                                            <h4 style="color:red; margin-top:10px;">${alert}</h4>
                                        </div>
                                    </div>
                                </form>
                                <!-- Table -->
                                <c:if test="${listCombos != null}">
                                    <div class="col-12" style="max-width:95%; margin: auto; margin-top: 10px">
                                        <div class="table-responsive bg-white shadow rounded">
                                            <table class="table table-center table-padding mb-0 table2">
                                                <col style="width:5%">
                                                <col style="width:8%">
                                                <col style="width:85%">
                                                <thead>
                                                    <tr>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Combo ID</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Curriculum ID</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Combo Name</th>
                                                            <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                                                            <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Status</th>
                                                            <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff"></th>
                                                            </c:if>
                                                    </tr>
                                                </thead>
                                                <tbody class="listCombos">
                                                    <c:set var="countCombos" value="0"></c:set>
                                                    <c:forEach items="${listCombos}" var="list">
                                                        <c:set var="countCombos" value="${countCombos + 1}"></c:set>
                                                            <tr class="itemCombos">
                                                                <td class="p-3" style="text-align: center;"><b>${list.id}</b></td>
                                                            <td class="p-3" style="text-align: center;"><b>${list.getCurriculum_id()}</b></td>
                                                            <td class="p-3" ><a href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${list.id}&curriculumid=${list.getCurriculum_id()}">${list.name}</a></td>
                                                                <c:choose>
                                                                    <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD'|| sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                                        <c:if test="${list.getIs_active() == 'TRUE'}" >
                                                                        <td id="active" class="p-3"><span class="badge bg-soft-warning" 
                                                                                                          style="color:red !important;
                                                                                                          background-color: #ddd !important;
                                                                                                          ">${list.getIs_active()}</span></td>
                                                                        </c:if>
                                                                        <c:if test="${list.getIs_active() == 'FALSE'}" >
                                                                        <td id="active" class="p-3"><span class="badge bg-soft-warning" 
                                                                                                          style="color:blue !important;
                                                                                                          background-color: #ddd !important;
                                                                                                          ">${list.getIs_active()}</span></td>
                                                                        </c:if>
                                                                    <td class="text-end p-3">
                                                                        <div style="display:flex">
                                                                            <div>
                                                                                <a href="/FLM_NEW/view/admin/combo?action=updatecombo&comboid=${list.id}&curriculumid=${list.getCurriculum_id()}" 
                                                                                   class="btn btn-icon btn-pills btn-soft-success"><i class="uil uil-pen">
                                                                                    </i>
                                                                                </a>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                </c:when>
                                                            </c:choose>
                                                        </tr>
                                                    </c:forEach> 
                                                </tbody>
                                            </table>
                                        </div>                                     
                                    </div>
                                </c:if>                              
                            </div>
                        </div>


                        <div  hidden=""id = "numberOfCombo">${numberOfCombo}</div>
                        <div class="row text-center" style="max-width:95%;margin: auto">
                            <!-- PAGINATION START -->
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center justify-content-between">
                                    <span id="numberAcc" class="text-muted me-3"></span>
                                    <ul class="listPage pagination justify-content-center mb-0 mt-3 mt-sm-0" style="display:flex">
                                    </ul>
                                </div>
                            </div><!--end col-->
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
        <!--Combo list FULL-->
        <script src="../../JS/Combo.js"></script>

    </body>

</html>
