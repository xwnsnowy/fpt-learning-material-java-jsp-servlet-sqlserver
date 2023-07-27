<%-- 
    Document   : syllabusoverview
    Created on : Jun 15, 2023, 12:49:58 AM
    Author     : Admin
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
        <!--Syllabus Overview css-->
        <link rel="stylesheet" href="../../Style/SyllabusOverview.css">

    </head>

    <body>
        <input type="hidden" id="hdnSession" data-value="${sessionScope.acc.getRoleName()}" />
        <style>
            td {
                border: 1px solid #dee2e6;
            }
        </style>
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
                <!--Trung-->

                <div class="container-fluid">

                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Syllabus Overview</h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item">Syllabus</li>
                                    <li class="breadcrumb-item" aria-current="page"><a href="/FLM_NEW/view/common/syllabus?action=list&sylId=" class=" breadcrumb-item active">Syllabus Overview</a></li>
                                </ul>
                            </nav>

                        </div>
                        
                        <div class="row">
                            <div class="col-12 mt-4">
                                <c:if test="${syllabus !=null}">
                                    <div class="table-responsive shadow rounded">
                                        <table class="  table table-center bg-white mb-0">
                                            <tbody>
                                                <tr >
                                                    <td class=" p-3">Syllabus ID:</td>
                                                    <td class=" p-3">${syllabus.getId()}</td>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">Subject Name:</td>
                                                    <th class=" p-3">${syllabus.getName()}</th>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">Subject Code:</td>
                                                    <th class=" p-3">${syllabus.getSubject().getCode()}</th>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">NoCredit:</td>
                                                    <td class=" p-3">${syllabus.getNoCredit()}</td>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">Degree Level:</td>
                                                    <td class=" p-3">${syllabus.getDegreeLevel()}</td>
                                                </tr>
                                                <!--phải bổ sung chức năg của thành-->
                                                <tr>
                                                    <td class=" p-3">Pre-Requisite:</td>
                                                    <td class=" p-3"></td>
                                                </tr>
                                                <!---------------------->
                                                <tr>
                                                    <td class=" p-3">Description:</td>
                                                    <td class=" p-3">${syllabus.getSubject().getDescription()}</td>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">Scoring Scale:</td>
                                                    <td class=" p-3">${syllabus.getScoringScale()}</td>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">Note:</td>
                                                    <td class=" p-3">${syllabus.getNote()}</td>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">Min Avg Mark To Pass:</td>
                                                    <th class=" p-3">${syllabus.getMinAvgMarkToPass()}</th>
                                                </tr>
                                                
                                                <tr>
                                                    <td class=" p-3">IsApproved:</td>
                                                    <th class=" p-3">${syllabus.getIsApproved()}</th>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">DecisionNo MM/dd/yyyy:</td>
                                                    <td class=" p-3">${syllabus.getDecision().getDecision_no()}/${syllabus.getDecision().getDecision_date()}</td>
                                                </tr>
                                                <tr>
                                                    <td class=" p-3">IsActive:</td>
                                                    <th class=" p-3">${syllabus.getIsActive()}</th>
                                                </tr>
                                            </tbody>
                                            
                                        </table>
                                    </div>
                                </c:if>
                            </div>
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
        <!--account list-->
        <script src="../../JS/CurriculumSubject.js"></script>

    </body>

</html>


