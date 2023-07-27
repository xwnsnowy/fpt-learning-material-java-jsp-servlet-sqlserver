<%-- 
    Document   : syllabuslist
    Created on : Jun 14, 2023, 6:10:13 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.net.URLEncoder" %>

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


        <!--<link rel="stylesheet" href="../../assets/datatables/dataTables.bootstrap.css">-->
        <!--<link rel="stylesheet" href="../../assets/bootstrap/css/bootstrap.min.css">-->  
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
        
        <!--toast-->
        <link rel="stylesheet" href="../../assets/css/toast.style.css"/>
        <script src="../../assets/js/jquery.min.js"></script>
        <script src="../../assets/js/toast.script.js"></script>

    </head>
    <body>
        <c:if test="${importData == 'true'}">
            <%
                // Đường dẫn tới tệp tin bạn muốn tải xuống trên máy chủ
                String filePath = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/bin/‪‪tem/Import Syllabus.xlsx";
                //String filePath = "../../../‪‪tem/Import Syllabus.xlsx";
                // Tên tệp tin hiển thị cho người dùng
                String fileName = "Import Syllabus.xlsx";
                // Loại nội dung của tệp tin
                String contentType = "application/octet-stream";

                // Thiết lập tiêu đề và loại nội dung cho phản hồi
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                response.setContentType(contentType);

                // Đọc tệp tin và gửi nội dung về trình duyệt
                FileInputStream fileInputStream = new FileInputStream(filePath);
                OutputStream outputStream = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                fileInputStream.close();
                outputStream.close();
            %>
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
            #btImport{
                top: 0px;
                position: relative;
                z-index: 3;
            }
            input[type="file"] {
                position: absolute;
                z-index: 0;
                top: 28px;
                left: 22px;
                color: #b8b8b8;
            }
        </style>

        <c:if test="${importTable == 'true'}">
            <c:remove var="importTable" scope="session" /> 
            <script type="text/javascript">
                $(window).on('load', function () {
                    $('#importTable').modal('show');
                });

            </script>
        </c:if>  
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
                    window.onload = addToast("Success", "Add syllabus successfully!", "success", 5000);
                    sessionStorage.setItem("toastShow", "1");

                }
                ;

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
                <!--Trung-->

                <div class="container-fluid">

                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Syllabus List</h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item">Syllabus</li>
                                    <li class="breadcrumb-item" aria-current="page"><a href="/FLM_NEW/view/common/syllabus?action=list" class=" breadcrumb-item active">Syllabus List</a></li>
                                </ul>
                            </nav>

                        </div>
                        <c:if test="${alert != ''}">
                            <div style="width: fit-content"class="p-4 alert alert-danger alert-dismissible fade show" >
                                <div class="" role="alert">
                                    <strong>Update error!</strong><samp> ${alert} </samp>
                                    <button  type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </div>
                        </c:if>
                        <form id="filter" action="syllabus?action=list" method="post">
                            <input type="hidden" name="thisPage" id="thisPage" value="${thisPage}" >
                            <div class="row justify-content-start align-items-center">

                                <div class="col-sm-3 col-md-3">
                                    <div class="d-grid">
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <select name="selectFilter"  class=" form-control time-during select2input" >
                                                    <option <c:if test="${selectFilter == 'code'}"> selected="" </c:if> value="code">Code</option>
                                                    <option <c:if test="${selectFilter == 'name'}"> selected="" </c:if> value="name">Name</option>
                                                    </select>

                                                </div>
                                                <!--<input type="text" class="form-control" aria-label="Text input with dropdown button">-->
                                                <input type="text" name="search" class=" form-control" placeholder="Search" id="myInput" value="${search}">
                                        </div>
                                    </div>
                                </div><!--end col-->
                                <div class="col-sm-auto col-md-auto col-lg-auto">
                                    <div class="d-grid m-1">
                                        <button type="button" class="btn btn-primary" onclick='cleanThisPage()'><i class="ri-search-line align-middle me-2"></i>Search</button>
                                    </div>
                                </div><!--end col-->
                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                    <div  class="col-sm-12 col-md-auto col-lg-auto">
                                        <div class="d-grid m-1 ">
                                            <a href="/FLM_NEW/view/crddstaff/syllabus?action=add" class="btn btn-success" ><i class="ri-add-circle-line align-middle me-2"></i>Add New Syllabus</a>
                                        </div>
                                    </div><!--end col-->
                                    <div  class="col-sm-12 col-md-auto col-lg-auto">
                                        <div class="d-grid m-1">
                                            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#import" ><i class="ri-insert-row-bottom align-middle me-2" ></i>Import Syllabus</button>
                                        </div>
                                    </div><!--end col-->
                                </c:if>
                            </div><!--end row-->
                        </form>

                        <div class=" row">
                            <div class="   col-12 mt-4">
                                <c:if test="${listSyllabus !=null}">
                                    <div class="table-responsive shadow rounded">

                                        <table id="table" class="  table table-center bg-white mb-0">
                                            <thead>
                                                <tr  style="background-color:#396cf0; color: #ffffff">
                                                    <th class="border-bottom p-3" style="min-width: 50px;">Syllabus ID</th>
                                                    <th class="border-bottom p-3" style="min-width: 50px;">Subject Code</th>
                                                    <th class="border-bottom p-3" style="min-width: 120px;">Subject Name</th>
                                                    <th class="border-bottom p-3" style="min-width: 120px;">Syllabus Name</th>
                                                    <th class="border-bottom p-3" style="min-width: 100px;">DecisionNo MM/dd/yyyy</th>
                                                    <th class="border-bottom p-3" style="min-width: 50px;">IsActive</th>
                                                    <th class="border-bottom p-3" style="min-width: 50px;">IsApproved</th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;"></th>
                                                </tr>
                                            </thead>
                                            <tbody class="listSyllabus">
                                                <c:set var="countSyllabus" value="0"></c:set>
                                                <c:forEach items="${listSyllabus}" var="syllabus">
                                                    <c:set var="countSyllabus" value="${countSyllabus + 1}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                            <tr class="itemSyllabus">
                                                                <th id="id" class="p-3">${syllabus.getId()}</th>
                                                                <th id="subjectCode" class="p-3">${syllabus.getSubject().getCode()}</th>
                                                                <td id="subjectName" class="p-3">${syllabus.getSubject().getName()}</td>
                                                                <td id="syllabusName" class="p-3"><a href="syllabus?action=overview&sylId=${syllabus.getId()}">${syllabus.getName()}</a></td>
                                                                <td id="decision" class="p-3"><a href="#">${syllabus.getDecision().getDecision_no()}/${syllabus.getDecision().getDecision_date()}</a></td>
                                                                <td id="isActive" class="p-3"><span class="badge bg-soft-warning">${syllabus.getIsActive()}</span></td>
                                                                <td id="isApproved" class="p-3"><span class="badge bg-soft-warning">${syllabus.getIsApproved()}</span></td>
                                                                <td class="text-end p-3">
                                                                    <a href="#" class="btn btn-icon btn-pills btn-soft-primary" data-bs-toggle="modal" data-bs-target="#viewprofil" onclick="view('${countSyllabus}')"><i class="uil uil-eye"></i></a>
                                                                    <a href="#" class="btn btn-icon btn-pills btn-soft-success" data-bs-toggle="modal" data-bs-target="#editprofile" onclick="edit('${countSyllabus}')"><i class="uil uil-pen"></i></a>
                                                                </td>

                                                            </tr>
                                                        </c:when>
                                                        <c:when test="${sessionScope.acc.getRoleName() == 'STUDENT' || sessionScope.acc.getRoleName() == 'TEACHER' }">
                                                            <tr class="itemSyllabus">
                                                                <th id="id" class="p-3">${syllabus.getId()}</th>
                                                                <th id="subjectCode" class="p-3">${syllabus.getSubject().getCode()}</th>
                                                                <td id="subjectName" class="p-3">${syllabus.getSubject().getName()}</td>
                                                                <td id="syllabusName" class="p-3"><a href="syllabus?action=overview&sylId=${syllabus.getId()}">${syllabus.getName()}</a></td>
                                                                <td id="decision" class="p-3"><a href="#">${syllabus.getDecision().getDecision_no()}/${syllabus.getDecision().getDecision_date()}</a></td>
                                                                <td id="isActive" class="p-3"><span class="badge bg-soft-warning">${syllabus.getIsActive()}</span></td>
                                                                <td id="isApproved" class="p-3"><span class="badge bg-soft-warning">${syllabus.getIsApproved()}</span></td>

                                                                <td class="text-end p-3">
                                                                    <a href="#" class="btn btn-icon btn-pills btn-soft-primary" data-bs-toggle="modal" data-bs-target="#viewprofil" onclick="view('${countSyllabus}')"><i class="uil uil-eye"></i></a>
                                                                </td>

                                                            </tr>
                                                        </c:when>

                                                    </c:choose>
                                                </c:forEach>

                                            </tbody>
                                        </table>
                                        <form id="delete" action="account?action=2&selectFilter=${selectFilter}&search=${search}"  method="post">
                                            <input id="daleteUserId" type="hidden" name="daleteUserId">
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div><!--end row-->

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
        <!-- Profile Settings Start -->


        <div class="modal fade" id="editprofile" aria-hidden="true">

            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Edit Curriculum Subject</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div id="editCurriculumSubject" class="  modal-body p-3 pt-4">
                        <div class="row align-items-center">

                            <label class="form-label col-lg-2 col-md-4">
                                Subject Id: <samp id="id"></samp>
                            </label><!--end col-->

                        </div><!--end row-->

                        <form id="editForm" action="curriculumsubject?action=edit&cId=${c.getCurriculum_id()}&selectFilter=&search=" method="post" class="mt-4">
                            <div class="row">
                                <input type="hidden" name='idValue' id="idValue" >
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label id="codeText" class="form-label">Code:</label>
                                        <p name="subjectCode" id="subjectCode" class="text-muted ms-2"></p>
                                    </div>
                                </div><!--end col-->
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label id="nameText" class="form-label">Name:</label>
                                        <p name="subjectName" id="subjectName" class="text-muted ms-2"></p>
                                    </div>
                                </div><!--end col-->


                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label id="semesterText" class="form-label">Semester</label>
                                        <input name="semester" id="semester" pattern="[0-9]{1,2}" title="Number with 2 digits!" type="text" class="form-control" placeholder="semester :" required="">
                                    </div>
                                </div><!--end col-->

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label id="noCreditText" class="form-label">NoCredit</label>
                                        <input name="noCredit" id="noCredit" pattern="[0-9]{1,2}" title="Number with 2 digits!" type="text" class="form-control" placeholder="NoCredit :" required="">
                                    </div>
                                </div><!--end col-->




                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Subject Active</label>
                                        <div class="form-check form-switch">
                                            <input name='isActive' class="form-check-input" type="checkbox" id="isActive" onclick="selectBt('isActiveText')">
                                            <p id="isActiveText" class="badge bg-soft-warning" style="text-transform: capitalize"></p>
                                        </div>
                                    </div> 

                                </div><!--end col-->
                                <!--                                <div class="col-md-12">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Description</label>
                                                                        <textarea name="description" id="description" rows="4" class="form-control" placeholder="description :"></textarea>
                                                                    </div>
                                                                </div>-->
                            </div><!--end row-->

                            <div class="row">
                                <div class="col-sm-12">
                                    <input type="submit" name="send" class="btn btn-primary" value="Save changes">
                                    <div style="color: red" id="alertForEdit"></div>
                                </div><!--end col-->
                            </div><!--end row-->
                        </form><!--end form-->
                    </div>
                </div>
            </div>
        </div>
        <!-- importTable End -->
        <div class="modal fade" id="importTable" aria-hidden="true" >

            <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable" >
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Import Syllabus</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div id="" class="  modal-body p-3 pt-4">


                        <form id="importForm" action="syllabus?action=import" method="post" class="mt-4">
                            <table id="table2" class="  table table-center bg-white mb-0 table-sm">
                                <thead >
                                    <tr class="headtable" >
                                        <td colspan="100%"  class="border-bottom p-9 h4" style="background-color:#6ab2ff ;text-align: center;height:50px; color: #ffffff">Valid list</td>
                                    </tr>
                                    <tr  style="background-color:#396cf0; color: #ffffff">
                                        <th class="border-bottom p-3" style="min-width: 50px;">Syllabus ID</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">Subject ID</th>
                                        <th class="border-bottom p-3" style="min-width: 120px;">Syllabus Name</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">Degree Level</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">NoCredit</th>
                                        <th class="border-bottom p-3" style="min-width: 100px;">DecisionNo MM/dd/yyyy</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">IsApproved</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">Desginer</th>
                                    </tr>
                                </thead>
                                <tbody class="listSyllabus">
                                    <c:forEach items="${validList}" var="validList">
                                        <tr class="itemSyllabus">
                                            <th id="" class="p-3">${validList.getId()}</th>
                                            <td id="" class="p-3">${validList.getSubjectid()}</td>
                                            <th id="" class="p-3">${validList.getName()}</th>
                                            <th id="" class="p-3">${validList.getDegreeLevel()}</th>
                                            <th id="" class="p-3">${validList.getNoCredit()}</th>
                                            <th id="" class="p-3">${validList.getDecision_id()}</th>
                                            <th id="" class="p-3">${validList.getIsApproved()}</th>
                                            <th id="" class="p-3">${validList.getDesginerId()}</th>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                            <table id="table3" class=" table table-center bg-white mb-0 table-sm " style=" margin-top: 20px; margin-bottom: 20px;">
                                <thead>
                                    <tr class="headtable" >
                                        <td colspan="100%"  class="border-bottom p-9 h4" style="background-color:#6ab2ff;text-align: center;height:50px; color: #ffffff">Invalid list</td>
                                    </tr>
                                    <tr  style="background-color:#396cf0; color: #ffffff">
                                        <th class="border-bottom p-3" style="min-width: 50px;">Syllabus ID</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">Subject ID</th>
                                        <th class="border-bottom p-3" style="min-width: 120px;">Syllabus Name</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">Degree Level</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">NoCredit</th>
                                        <th class="border-bottom p-3" style="min-width: 100px;">DecisionNo MM/dd/yyyy</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">IsApproved</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">Desginer</th>
                                    </tr>
                                </thead>
                                <tbody class="listSyllabus">
                                    <c:forEach items="${invalidList}" var="invalidList">
                                        <tr class="itemSyllabus">
                                            <th id="" class="p-3">${invalidList.getId()}</th>
                                            <td id="" class="p-3">${invalidList.getSubjectid()}</td>
                                            <th id="" class="p-3">${invalidList.getName()}</th>
                                            <th id="" class="p-3">${invalidList.getDegreeLevel()}</th>
                                            <th id="" class="p-3">${invalidList.getNoCredit()}</th>
                                            <th id="" class="p-3">${invalidList.getDecision_id()}</th>
                                            <th id="" class="p-3">${invalidList.getIsApproved()}</th>
                                            <th id="" class="p-3">${invalidList.getDesginerId()}</th>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                            <ul>
                                <c:forEach items="${invalidMessage}" var="invalidMessage">
                                    <li><c:out value="${invalidMessage}" /></li>
                                    </c:forEach>
                            </ul>

                            <div class="row">
                                <div class="col-sm-12">
                                    <input type="submit" name="send" class="btn btn-primary" value="Import">
                                    <div style="color: red" id="alertForEdit"></div>
                                </div><!--end col-->
                            </div><!--end row-->
                        </form><!--end form-->
                    </div>
                </div>
            </div>
        </div>
        <!-- importTable End -->


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
                                        <h6>Subject Id:</h6>
                                        <p id="id" class="text-muted ms-2"></p>
                                    </li>
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
        <!-- import End -->
        <div class="modal fade" id="import" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel1">Import Syllabus</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div id="viewImportSyllabus" class="modal-body p-3 pt-4">
                        <div class="row justify-content-start align-items-center m-1">
                            <div class="col-sm-12 col-md-auto col-lg-12 ">
                                File template:
                                <a href="syllabus?action=import" class="btn btn-info btn-sm"><i class="ri-download-line align-middle me-2" ></i>Download</a>

                            </div>
                            <form id="fExcel" action="import?action=importSyllabus" method="post" enctype="multipart/form-data">
                                <div class="col-sm-auto col-md-auto col-lg-auto">
                                    Choose files:
                                </div>
                                <div class="col-sm-auto col-md-auto col-lg-auto m-1">

                                    <label id="btImport" class="btn btn-success btn-sm" for="upload"><i class="ri-upload-line align-middle me-2" ></i>Upload</label>
                                    <input name="file" type="file" id="upload" accept=".xlsx, .xls" required="">

                                </div>

                                <div class="row justify-content-center border-top" style="margin-top: 10px;">
                                    <div class="col-sm-auto col-md-auto col-lg-auto" style="margin-top: 10px;"> 
                                        <input type="submit" name="send" class="btn btn-primary " value="Import">

                                    </div><!--end col-->
                                </div><!--end row-->
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>              
        <!-- import End -->
        <!-- Modal end -->


        <!-- javascript -->
        <script>
            $(window).on('submit', function () {
                sessionStorage.removeItem("toastShow");
            });
        </script>
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
        <script src="../../JS/PageWrapper.js"></script>
        <script src="../../assets/datatables/jquery.dataTables.min.js"></script>


        <script>
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
                    "autoWidth": true
                });
            });
        </script>

    </body>

</html>

