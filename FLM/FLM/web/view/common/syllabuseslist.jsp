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
        <!--curriculum subject css-->
        <link rel="stylesheet" href="../../Style/CurriculumSubject.css">

    </head>

    <body>
        <style>
            .table1, th, td {
                border: 1px solid #dee2e6;
                vertical-align: top;
                text-align: left;
                padding: 8px;
                border-collapse: collapse;
            }
            .mb-0 tr:nth-child(odd) {
                background-color: #f2f2f2;
            }
            .mb-0 tr:hover {
                background-color: #ddd;
                ;
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
                            <h5 class="mb-0"></h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item">Curriculum</li>
                                    <li class="breadcrumb-item" aria-current="page"><a href="curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=" class=" breadcrumb-item active">Subject List</a></li>
                                </ul>
                            </nav>

                        </div>
                        <div class="col-12" style="max-width:95%; margin-bottom: 15px;">
                            <table class="table1">
                                <tr>
                                    <td>Subject</td>
                                    <th>${cs.getSubjectCode()} -- ${cs.getSubjectName()}</th>
                                </tr>
                                <tr>
                                    <td>Curriculum</td>
                                    <th>${cs.getCurriculumCode()} -- ${cs.getCurriculumName()}</th>
                                </tr>
                                <tr>
                                    <td>Semester</td>
                                    <th>${cs.getCurriculumSubjectSemester()}</th>
                                </tr>
                                <tr>
                                    <td>NoCredit</td>
                                    <th>${cs.getCurriculumSubjectNoCredit()}</th>
                                </tr>
                            </table>
                        </div>
                        <c:if test="${alert != ''}">
                            <div style="width: fit-content"class="p-4 alert alert-danger alert-dismissible fade show" >
                                <div class="" role="alert">
                                    <strong>Update error!</strong><samp> ${alert} </samp>
                                    <button  type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </div>
                        </c:if>

                        <div class=" row">
                            <div class="   col-12 mt-4">
                                <c:forEach items="${listCombo}" var="combo">
                                    <h5 style="margin-top:25px;">${combo.getCurriculumName()}</h5>
                                    <div class="table-responsive shadow rounded">


                                        <table class="  table table-center bg-white mb-0" >
                                            <thead>
                                                <tr  style="background-color:#396cf0; color: #ffffff">
                                                    <th class="border-bottom p-3" style="min-width: 50px;">Subject Code</th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;">Syllabus Name</th>
                                                    <th class="border-bottom p-3" style="min-width: 50px;">IsActive</th>
                                                    <th class="border-bottom p-3" style="min-width: 50px;">IsApproved</th>
                                                    <th class="border-bottom p-3" style="min-width: 120px;">DecisionNo</th>
                                                </tr>
                                            </thead>
                                            <tbody class="listCurriculumSubject">
                                                <c:set var="countAccount" value="0"></c:set>
                                                <c:forEach items="${listSubjectOfCombo}" var="subjectOfCombo">
                                                    <c:if test="${combo.getCurriculumSubjectGroupId() == subjectOfCombo.subject.getGroupId()}">
                                                        <c:set var="countCSubject" value="${countCSubject + 1}"></c:set>
                                                        <c:choose>
                                                            <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                                <tr class="itemCurriculumSubject">
                                                                    <th id="subjectCode" class="p-3">${subjectOfCombo.subject.getCode()}</th>
                                                                    <th id="syllabusName" class="p-3"><a href="/FLM_NEW/view/common/syllabus?action=overview&sylId=${subjectOfCombo.getId()}">${subjectOfCombo.getName()}</a></th>
                                                                    <td id="isActive" class="p-3"><span class="badge bg-soft-warning">${subjectOfCombo.getIsActive()}</span></td>
                                                                    <td id="isApproved" class="p-3"><span class="badge bg-soft-warning">${subjectOfCombo.getIsApproved()}</span></td>
                                                                    <td id="decisionNo" class="p-3">${subjectOfCombo.decision.getDecision_no()}/${subjectOfCombo.decision.getDecision_date()}</td>
                                                                    

                                                                </tr>
                                                            </c:when>
                                                            <c:when test="${sessionScope.acc.getRoleName() == null || sessionScope.acc.getRoleName() == 'GUEST' || sessionScope.acc.getRoleName() == 'STUDENT' || sessionScope.acc.getRoleName() == 'TEACHER'}">
                                                                <tr class="itemCurriculumSubject">
                                                                    <th id="subjectCode" class="p-3">${subjectOfCombo.subject.getCode()}</th>
                                                                    <th id="syllabusName" class="p-3"><a href="/FLM_NEW/view/common/syllabus?action=overview&sylId=${subjectOfCombo.getId()}">${subjectOfCombo.getName()}</a></th>
                                                                    <td id="isActive" class="p-3"><span class="badge bg-soft-warning">${subjectOfCombo.getIsActive()}</span></td>
                                                                    <td id="isApproved" class="p-3"><span class="badge bg-soft-warning">${subjectOfCombo.getIsApproved()}</span></td>
                                                                    <td id="decisionNo" class="p-3">${subjectOfCombo.decision.getDecision_no()}/${subjectOfCombo.decision.getDecision_date()}</td>
                                                                    
                                                                </tr>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:if>
                                                </c:forEach>

                                            </tbody>
                                        </table>


                                        <form id="delete" action="account?action=2&selectFilter=${selectFilter}&search=${search}" " method="post">
                                            <input id="daleteUserId" type="hidden" name="daleteUserId">
                                        </form>
                                    </div>
                                </c:forEach>
                            </div>
                        </div><!--end row-->

                        <div class="row text-center">
                            <!-- PAGINATION START -->
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center justify-content-between">
                                    <span id="numberAcc" class="text-muted me-3"></span>
                                    <ul class="listPage pagination justify-content-center mb-0 mt-3 mt-sm-0">
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
        <!--account list-->
        <!--<script src="CurriculumSubject.js"></script>-->

    </body>

</html>
