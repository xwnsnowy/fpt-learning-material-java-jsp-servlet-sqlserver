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
        <title>Subject Successors</title>
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
                        <div class="d-md-flex justify-content-between">


                        </div>
                        <div >
                            <div class="row" >
                                <div class="col-12" style="max-width:95%;margin: auto">
                                    <h2 style="margin-top:10px; font-family: sans-serif">A subject is the pre-requisite of</h2>
                                    <form action="subjectsuccessors" method="POST">
                                        <div class="mb-2" style="max-width:95%;margin: auto; margin-top:20px">
                                            Subject Code:	
                                            <input type="text" id="code" name="code" class="border bg-white rounded-pill"
                                                   value="${returncode}"
                                                   style="border:1px solid #000 !important;height: 40px; width: 250px; ">
                                            <button type="submit" name="search" class="btn btn-pills btn-primary"><i class="ri-search-line align-middle me-1"></i> Search</button>
                                        </div>
                                        <div style="max-width:95%;margin: auto; margin-top:15px">
                                            <h5 style="color: red">${errorSearch}</h5>
                                            <c:if test="${errorNotFoundSearch!= null}">
                                                <div style="display:flex">
                                                    <h5 style="color: red;">Subject code&nbsp;</h5>
                                                    <h5 style="color: #746969">${errorNotFoundSearch}&nbsp;</h5>
                                                    <h5 style="color: red;">does not exist or has no syllabus !</h5>
                                                </div>
                                            </c:if>
                                        </div>                                      
                                    </form>
                                </div>
                                <!-- Table -->
                                <div class="col-12" style="max-width:95%; margin: auto; margin-top: 20px">
                                    <div class="table-responsive bg-white shadow rounded">
                                        <c:if test="${code != null}">
                                            <table class="table table-center table-padding mb-0" >
                                                <col style="width:8%">
                                                <col style="width:10%">
                                                <col style="width:30%">
                                                <col style="width:20%">
                                                <col style="width:40%">
                                                <thead>
                                                    <tr>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Syllabus ID</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Subject CODE</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Syllabus Name</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">DecisionNo MM/dd/yyyy</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Subject Successors</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr >
                                                        <td class="p-3" style="vertical-align: top;">${syllabus.getId()}</td>
                                                        <td class="p-3" style="vertical-align: top;">${code}</td>
                                                        <td class="p-3" style="vertical-align: top;">${syllabus.getName()}</td>
                                                        <td class="p-3" style="vertical-align: top;">${decision.getDecision_no()} ${date}</td>
                                                        <td class="p-3" style="vertical-align: top;"><b>${listPreSubject}${codeNonePre}${nonePre}</b>
                                                               <c:if test="${arrayListPreForEachSubject    !=null}">
                                                                <br> <br>
                                                                <ul>
                                                                    <c:forEach items="${arrayListPreForEachSubject}" var="list">
                                                                        <li> ${list}</li>
                                                                        </c:forEach>
                                                                </ul>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>  
                                        </c:if>              
                                        <!-- END Table -->                                      
                                    </div>
                                </div>
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
        <!--account list-->
        <script src="../../JS/AccountList.js"></script>

    </body>

</html>
