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
        <!--account css-->
        <link rel="stylesheet" href="../../Style/Account.css">
        <!--curriculum subject css-->
        <link rel="stylesheet" href="../../Style/CurriculumSubject.css">
        <link rel="stylesheet" href="../../assets/css/toast.style.css"/>
        <!--toast-js-->
        <script src="../../assets/js/jquery.min.js"></script>
        <script src="../../assets/js/toast.script.js"></script>

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
            .mb-0 tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .mb-0 tr:hover {
                background-color: #ddd;
                ;
            }
        </style>
        <c:if test="${sessionScope.toast == 'true'}">
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
                        rtl: false,
                    });
                }
                ;
                // type: success, warning, error, info or notice
                //            addToast(title, content, type, count)
                window.onload = addToast("Success", "Successfully!", "success", 5000);
            </c:if>
        </script>
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
                            <h5 class="mb-0">Subject List</h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item">Subject Management</li>
                                    <li class="breadcrumb-item" aria-current="page"><a class="breadcrumb-item active" href="subject?action=list" >Subject List</a></li>
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
                        <!--Search-->
                        <form action="subject" method="get">
                            <div class="row justify-content-start align-items-center">
                                <div class="col-sm-3 col-md-3">
                                    <div class="d-grid">
                                        <input type="hidden" name="action" value="list">
                                        <input style="line-height: 33px" type="text" name="search" placeholder="Search" value="${search}">
                                    </div>
                                </div><!--end col-->
                                <div class="col-sm-auto col-md-auto col-lg-auto">
                                    <div class="d-grid">
                                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#appointmentform">Search</button>
                                    </div>
                                </div><!--end col-->
                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN'}">
                                    <div  class="col-sm-auto col-md-auto col-lg-auto">
                                        <div class="d-grid">
                                            <a href="/FLM_NEW/view/admin/subject?action=add" class="btn btn-success" >Add New Subject</a>
                                        </div>
                                    </div><!--end col-->
                                </c:if>
                            </div><!--end row-->
                        </form>
                        <!--end search-->
                        <div class=" row">
                            <div class="   col-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <table class="  table table-center bg-white mb-0">
                                        <thead>
                                            <tr  style="background-color:#396cf0; color: #ffffff">
                                                <th class="border-bottom p-3" style="min-width: 50px;">Id</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Code</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Name</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Type</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Active</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Parent Subject Code</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Group</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Description</th>
                                                <th class="border-bottom p-3"  style="min-width: 160px;"></th>
                                            </tr>
                                        </thead>
                                        <tbody class="listSubject">
                                            <c:set var="countSubject" value="0"></c:set>
                                            <c:forEach items="${listSubject}" var="subject">
                                                <c:set var="countSubject" value="${countSubject + 1}"></c:set>
                                                    <tr class="itemSubject">
                                                        <th id="id" class="p-3">${subject.getId()}</th>
                                                    <th id="code" class="p-3">${subject.getCode()}</th>
                                                    <td id="name" class="p-3">${subject.getName()}</td>
                                                    <td id="type" class="p-3">${subject.getType()}</td>
                                                    <td id="isActive" class="p-3"><span class="badge bg-soft-warning">${subject.getIsActive()}</span></td>
                                                    <td id="parentSubject" class="p-3">${subject.getParentCode()}</td>
                                                    <td id="group" class="p-3">${subject.getGroupId()}</td>
                                                    <td id="description" class="p-3">${subject.getDescription()}</td>
                                                    <td class="text-end p-3">
                                                        <a href="#" class="btn btn-icon btn-pills btn-soft-primary" data-bs-toggle="modal" data-bs-target="#viewprofil" onclick="view('${countSubject}')"><i class="uil uil-eye"></i></a>
                                                        <a href="#" class="btn btn-icon btn-pills btn-soft-success" data-bs-toggle="modal" data-bs-target="#editprofile" onclick="edit('${countSubject}')"><i class="uil uil-pen"></i></a>
                                                    </td>

                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                    <form id="delete" action="subject?action=delete&search=${search}" method="post"" method="post">
                                        <input id="deleteSubjectById" type="hidden" name="deleteSubjectById">
                                    </form>
                                </div>
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

        <!-- Modal start -->
        <!-- Profile Settings Start -->


        <div class="modal fade" id="editprofile" aria-hidden="true">

            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Edit Subject</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div id="editSubject" class="  modal-body p-3 pt-4">
                        <div class="row align-items-center">

                            <label class="form-label col-lg-2 col-md-4">
                                Id: <samp id="id"></samp>
                            </label><!--end col-->

                        </div><!--end row-->

                        <form id="editForm" action="subject?action=edit&search=${search}" method="post" class="mt-4">
                            <div class="row">
                                <input type="hidden" name='idValue' id="idValue" >
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label id="codeText" class="form-label">Code</label>
                                        <input name="code" id="code" type="text" class="form-control" placeholder="Code :" >
                                    </div>
                                </div><!--end col-->

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label id="nameText" class="form-label">Name</label>
                                        <input name="name" id="name" type="text" class="form-control" placeholder="Name :" >
                                    </div>
                                </div><!--end col-->

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label id="nameType" class="form-label">Type</label>
                                        <select name="selectType" id="selectType"  class="form-control time-during select2input-1">
                                            <c:forEach items="${listType}" var="type">
                                                <option class="selectType" value='${type.getId()}'>${type.getName()}</option>
                                            </c:forEach>
                                        </select>

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
                                <div  class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Parent Code</label>
                                        <select name="selectSubject" id="selectSubject"  class="form-control time-during select2input" >
                                            <option value="">None</option>
                                            <c:forEach items="${listSubject}" var="subject">
                                                <option class="select" value="${subject.getId()}">${subject.getCode()}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="mb-3">
                                        <label class="form-label">Description</label>
                                        <textarea name="description" id="description" rows="4" class="form-control" placeholder="description :"></textarea>
                                    </div>
                                </div>
                            </div><!--end row-->

                            <div class="row">
                                <div class="col-sm-12">
                                    <input type="button" name="send" class="btn btn-primary" value="Save changes" onclick="checkSubmitForEditSubject()">
                                    <div style="color: red" id="alertForEdit"></div>
                                </div><!--end col-->
                            </div><!--end row-->
                        </form><!--end form-->
                    </div>
                </div>
            </div>
        </div>
        <!-- Profile Settings End -->


        <!--Profile Start--> 
        <div class="modal fade" id="viewprofil" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel1">Subject</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div id="viewSubject" class="modal-body p-3 pt-4">
                        <ul class="list-unstyled mb-0 d-md-flex justify-content-between mt-4">
                            <li>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex">
                                        <h6>Id:</h6>
                                        <p id="id" class="text-muted ms-2"></p>
                                    </li>
                                    <li class="d-flex">
                                        <h6 >Code:</h6>
                                        <p id="code" class="text-muted ms-2"></p>
                                    </li>
                                    <li class="d-flex">
                                        <h6>Name:</h6>
                                        <p id="name" class="text-muted ms-2"></p>
                                    </li>
                                    <li class="d-flex">
                                        <h6>Type:</h6>
                                        <p id="type" class="text-muted ms-2"></p>
                                    </li>


                                </ul>
                            </li>
                            <li>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex">
                                        <h6>Subject Active:</h6>
                                        <p id="isActive" class="text-muted ms-2"></p>
                                    </li>
                                    <li class="d-flex">
                                        <h6>Parent Code:</h6>
                                        <p id="parentSubject" class="text-muted ms-2"></p>
                                    </li>
                                    <li class="d-flex">
                                        <h6>Description:</h6>
                                        <p id="description" class="text-muted ms-2"></p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>              
        <!-- Profile End -->
        <!-- Modal end -->

        <!-- javascript -->
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
        <script src="../../JS/ContentGroupDetail.js"></script>
        <script src="../../assets/datatables/jquery.dataTables.min.js"></script>
        <script src="../../JS/Subject.js"></script>

    </body>

</html>
