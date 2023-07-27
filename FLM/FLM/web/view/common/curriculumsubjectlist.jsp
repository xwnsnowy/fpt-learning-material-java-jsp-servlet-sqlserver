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

        <!--toast-->
        <script src="../../assets/js/jquery.min.js"></script>
        <link rel="stylesheet" href="../../assets/css/toast.style.css"/>
        <script src="../../assets/js/toast.script.js"></script>


    </head>

    <body>
        <c:if test="${toast == 'true'}">

            <c:remove var="toast" scope="session" /> 
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
                if (sessionStorage.getItem("toastShow") === null) {
                    sessionStorage.setItem("toastShow", "0");
                }
                let toastShow = sessionStorage.getItem("toastShow");
                if (toastShow === '0') {
                    // type: success, warning, error, info or notice
                    //            addToast(title, content, type, count)
                    window.onload = addToast("Success", "successfully!", "success", 5000);
                    sessionStorage.setItem("toastShow", "1");

                }
                ;

            </script>


        </c:if>
        <input type="hidden" id="hdnSession" data-value="${sessionScope.acc.getRoleName()}" />
        <style>
            .table1, th, td {
                border: 1px solid #dee2e6;
                vertical-align: top;
                text-align: left;
                padding: 8px;
                border-collapse: collapse;
            }
            .mb-0 tr:nth-child(even) {
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
                            <h5 class="mb-0">Curriculum Subject List</h5>
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
                                    <td>Curriculum ID </td>
                                    <th>${c.getCurriculum_id()}</th>
                                </tr>
                                <tr>
                                    <td>Curriculum Code</td>
                                    <th>${c.getCode()}</th>
                                </tr>
                                <tr>
                                    <td>Curriculum Name</td>
                                    <th>${c.getName()}</th>
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
                        <form id="filter" action="curriculumsubject?action=list" method="get">
                            <div class="row justify-content-start align-items-center">
                                <input type="hidden" name="action" value="list">
                                <input type="hidden" name="cId" value="${c.getCurriculum_id()}">
                                <div class="col-sm-3 col-md-3 col-lg-5">
                                    <div class="mb-0 position-relative">
                                        <div class="row-cols-3 input-group"> 
                                            <div class="col-sm-5 col-md-5 col-lg-3 input-group-prepend">
                                                <select name="selectFilter"  class="form-control time-during select2input" onchange="selectFilterSemesterSubmit()">
                                                    <c:choose>
                                                        <c:when test="${selectFilter == ''}">
                                                            <option selected="" value="">All Semester</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="">All Semester</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:forEach items="${listSemester}" var="itemSemester">
                                                        <c:choose>
                                                            <c:when test="${selectFilter == itemSemester.getCurriculumSubjectSemester()}">
                                                                <option selected="" value="${itemSemester.getCurriculumSubjectSemester()}">${itemSemester.getCurriculumSubjectSemester()}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${itemSemester.getCurriculumSubjectSemester()}">${itemSemester.getCurriculumSubjectSemester()}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>

                                                </select>
                                            </div>
                                            <div class="col-sm-7 col-md-7 col-lg-9"> 
                                                <input type="text" class="form-control" name="search" placeholder="Search" value="${search}">
                                            </div> 
                                        </div>
                                    </div>
                                </div><!--end col-->

                                <!--                                <div class="col-sm-auto col-md-3">
                                                                    <div class="d-grid">
                                                                        <input style="line-height: 33px" type="text" class="form-control" name="search" placeholder="Search" value="${search}">
                                                                    </div>
                                                                </div>end col-->
                                <div class="col-sm-auto col-md-auto col-lg-auto">
                                    <div class="d-grid">
                                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#appointmentform"><i class="ri-search-line align-middle me-2"></i>Search</button>
                                    </div>
                                </div><!--end col-->
                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                    <div  class="col-sm-auto col-md-auto col-lg-auto">
                                        <div class="d-grid">
                                            <a href="/FLM_NEW/view/admin/curriculumsubject?action=add&cId=${c.getCurriculum_id()}" class="btn btn-success" ><i class="ri-add-circle-line align-middle me-2"></i>Add New Curriculum Subject</a>
                                        </div>
                                    </div><!--end col-->
                                </c:if>
                            </div><!--end row-->
                        </form>
                        <div class=" row">
                            <div class="   col-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <table id="table" class=" table table-center bg-white mb-0">
                                        <thead>
                                            <tr  style="background-color:#396cf0; color: #ffffff">
                                                <th hidden=""></th>
                                                <th class="border-bottom p-3" style="min-width: 50px;">Subject Code</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Subject Name</th>
                                                <th class="border-bottom p-3" style="min-width: 50px;">Semester</th>
                                                <th class="border-bottom p-3" style="min-width: 50px;">NoCredit</th>
                                                <th class="border-bottom p-3" style="min-width: 50px;">Active</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;"></th>
                                            </tr>
                                        </thead>
                                        <tbody id="tbody" class="listCurriculumSubject">
                                            <c:set var="countAccount" value="0"></c:set>
                                            <c:forEach items="${listCSubject}" var="cSubject">
                                                <c:set var="countCSubject" value="${countCSubject + 1}"></c:set>
                                                <c:choose>
                                                    <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">

                                                        <tr class="itemCurriculumSubject" >
                                                            <th hidden="" id="id" class="p-3">${cSubject.getSubjectId()}</th>
                                                            <th id="subjectCode" class="p-3">${cSubject.getSubjectCode()}</th>
                                                            <td id="subjectName" class="p-3"><a href="syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}">${cSubject.getSubjectName()}</a></td>
                                                            <td id="semester" class="p-3">${cSubject.getCurriculumSubjectSemester()}</td>
                                                            <td id="noCredit" class="p-3">${cSubject.getCurriculumSubjectNoCredit()}</td>
                                                            <td id="active" class="p-3"><span class="badge bg-soft-warning">${cSubject.getCurriculumSubjectIsActive()}</span></td>
                                                            <td class="text-end p-3">
                                                                <a href="#" class="btn btn-icon btn-pills btn-soft-primary btV" data-bs-toggle="modal" data-bs-target="#viewprofil" ><i class="uil uil-eye"></i></a>
                                                                <a href="/FLM_NEW/view/admin/curriculumsubject?action=edit&cId=${c.getCurriculum_id()}&cSId=${cSubject.getSubjectId()}" class="btn btn-icon btn-pills btn-soft-success"><i class="uil uil-pen"></i></a>
                                                            </td>

                                                        </tr>
                                                    </c:when>
                                                    <c:when test="${sessionScope.acc.getRoleName() == null || sessionScope.acc.getRoleName() == 'GUEST' || sessionScope.acc.getRoleName() == 'STUDENT' || sessionScope.acc.getRoleName() == 'TEACHER' }">
                                                        <c:if test="${cSubject.getCurriculumSubjectIsActive() == 'true'}">

                                                            <tr class="itemCurriculumSubject">
                                                                <th hidden="" id="id" class="p-3">${cSubject.getSubjectId()}</th>
                                                                <th id="subjectCode" class="p-3">${cSubject.getSubjectCode()}</th>
                                                                <td id="subjectName" class="p-3"><a href="syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}">${cSubject.getSubjectName()}</a></td>
                                                                <td id="semester" class="p-3">${cSubject.getCurriculumSubjectSemester()}</td>
                                                                <td id="noCredit" class="p-3">${cSubject.getCurriculumSubjectNoCredit()}</td>
                                                                <td id="active" class="p-3"><span class="badge bg-soft-warning">${cSubject.getCurriculumSubjectIsActive()}</span></td>
                                                                <td class="text-end p-3">
                                                                    <a href="#" class="btn btn-icon btn-pills btn-soft-primary btV" data-bs-toggle="modal" data-bs-target="#viewprofil" ><i class="uil uil-eye"></i></a>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:when>

                                                </c:choose>
                                            </c:forEach>

                                        </tbody>
                                    </table>

                                    <form id="delete" action="account?action=2&selectFilter=${selectFilter}&search=${search}"  method="post">
                                        <input id="daleteUserId" type="hidden" name="daleteUserId">
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="row text-center ">
                            <!-- PAGINATION START -->
                            <div  hidden="" id = "numberItem">${numSyllabus}</div>
                            <div class="col-12 mt-4 ">
                                <span id="textNumber" class="text-muted me-3"></span>
                                <div class="d-md-flex align-items-center  text-center justify-content-end">

                                    <ul class="  listPage pagination overflow-auto justify-content-between mb-0 mt-3 mt-sm-0" style="max-width: 300px;">
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


        <!-- Modal start -->



        <!--Profile Start--> 
        <div class="modal fade" id="viewprofil" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel1">Curriculum Subject</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div id="viewCurriculumSubject" class="modal-body p-3 pt-4">
                        <ul class="list-unstyled mb-0 d-md-flex justify-content-between mt-4">
                            <li>
                                <ul class="list-unstyled mb-0">

                                    <li class="d-flex">
                                        <h6 >Code:</h6>
                                        <p id="subjectCode" class="text-muted ms-2"></p>
                                    </li>
                                    <li class="d-flex">
                                        <h6>Name:</h6>
                                        <p id="subjectName" class="text-muted ms-2"></p>
                                    </li>



                                </ul>
                            </li>
                            <li>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex">
                                        <h6>Semester:</h6>
                                        <p id="semester" class="text-muted ms-2"></p>
                                    </li>
                                    <li class="d-flex">
                                        <h6>NoCredit</h6>
                                        <p id="noCredit" class="text-muted ms-2"></p>
                                    </li>
                                    <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' }">
                                        <li class="d-flex">
                                            <h6>Active:</h6>
                                            <p id="active" class="text-muted ms-2"></p>
                                        </li>
                                    </c:if>

                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>              
        <!-- Profile End -->
        <!-- Modal end -->

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

        <script src="../../JS/PageWrapper.js"></script>
        <!--account list-->
        <!--<script src="../../JS/PageWrapper.js"></script>-->
        <script src="../../assets/datatables/jquery.dataTables.min.js"></script>

        <script src="../../JS/CurriculumSubject.js"></script>
        <script>

                                                    const vCells = document.querySelectorAll('.btV');
                                                    var count;
                                                    vCells.forEach(cell => {
                                                        cell.addEventListener('click', function () {
                                                            count = cell.closest('tr').rowIndex;
                                                            view(count);

                                                        });
                                                    });

                                                    const eCells = document.querySelectorAll('.btE');
                                                    var count;
                                                    eCells.forEach(cell => {
                                                        cell.addEventListener('click', function () {
                                                            count = cell.closest('tr').rowIndex;
                                                            edit(count);

                                                        });
                                                    });

                                                    function resetToast() {
                                                        sessionStorage.removeItem("toastShow");
                                                    }

                                                    $(function () {
                                                        $('#table').DataTable({
                                                            "paging": false,
                                                            "lengthChange": false,
                                                            "searching": false,
                                                            "ordering": true,
                                                            "info": false,
                                                            "autoWidth": true,
                                                            "order": [[3, 'asc']]
                                                        });
                                                    });
        </script>



    </body>

</html>
