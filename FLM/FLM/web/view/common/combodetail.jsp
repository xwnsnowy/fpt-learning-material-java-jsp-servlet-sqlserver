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
        <title>Combo Detail</title>
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

        <div class="page-wrapper doctris-theme toggled">
            <%@include file="../common/sidebar.jsp" %>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <%@include file="../common/headerforsidebar.jsp" %>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div >
                            <div class="row" >
                                <div class="col-12" style="max-width:95%;margin: auto">
                                    <h3 style="margin-top:10px">View Combo Detail</h3>
                                    <table class="table1">
                                        <tr>
                                            <td>Combo Name	 </td>
                                            <td><b>${g.name}</b> </td>
                                        </tr>
                                        <tr>
                                            <td>Note</td>
                                            <td><b></b></td>
                                        </tr>
                                    </table>
                                </div>
                                <!-- Table -->

                                <div class="col-12" style="max-width:95%; margin: auto; margin-top: 10px">
                                    <c:if test="${sizePage != 0}" >
                                        <h3 style="margin-top:10px">List Subject Combo</h3>
                                        <div class="table-responsive bg-white shadow rounded">                           
                                            <table class="table table-center table-padding mb-0" >
                                                <col style="width:5%">
                                                <col style="width:5%">   
                                                <col style="width:45%">
                                                <col style="width:5%">
                                                <col style="width:35%">
                                                <thead>
                                                    <tr>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center">Subject ID</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center">Subject Code</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Subject Name</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center">Semester</th>
                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Note</th>

                                                    </tr>
                                                </thead>
                                                <tbody >
                                                    <c:forEach items="${listPage}" var="list">
                                                        <tr>
                                                            <td class="p-3" style="text-align: center" ><b>${list.getSubjectId()}</b></td>
                                                            <td class="p-3" style="text-align: center"><b>${list.getSubjectCode()}</b></td>
                                                            <td class="p-3" >${list.getSubjectName()}</td>
                                                            <c:if test="${list.getCurriculumSubjectSemester() == null}" >
                                                                <td class="p-3" >none</td>
                                                            </c:if>  
                                                            <c:if test="${list.getCurriculumSubjectSemester() != null}" >
                                                                <td class="p-3" style="text-align: center"><b>${list.getCurriculumSubjectSemester()}</b><</td>
                                                            </c:if> 
                                                            <td class="p-3" >${list.getSubjectDescription()}</td>

                                                        </tr>
                                                    </c:forEach>  
                                                </tbody>
                                            </table>


                                        </div>     
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="row text-center">
                            <c:set var="page" value="${page}"/>
                            <div class="col-12" style="max-width:95%;margin: auto; margin-top:20px">
                                <div class="pagination align-items-center text-center justify-content-between">
                                    <c:forEach begin="${1}" end="${numPage}" var="i">
                                        <a class="${i==page?"active" : ""}" href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}&page=${i}">
                                            ${i}
                                        </a>
                                    </c:forEach>
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


</body>
<!-- javascript -->
<script src="../../assets/js/bootstrap.bundle.min.js"></script>
<!-- Icons -->
<script src="../../assets/js/feather.min.js"></script>
<!-- Main Js -->
<script src="../../assets/js/app.js"></script>

<script src="../../assets/js/bootstrap.bundle.min.js"></script>
<!-- SLIDER -->
<script src="../../assets/js/tiny-slider.js"></script>
<script src="../../assets/js/tiny-slider-init.js"></script>
<!-- Counter -->
<script src="../../assets/js/counter.init.js"></script>
</html>
