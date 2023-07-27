<%-- 
    Document   : addplos
    Created on : Jun 3, 2023, 2:59:50 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <h5 class="mb-0">Add New CLOs</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item">SYLLABUS</li>
                                    <li class="breadcrumb-item active" aria-current="page">ADD NEW PLOs</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-lg-8 mt-4">
                                <!--end row-->

                                <form action="clo?action=add&sylId=${syllabus.getId()}" method="post" class="mt-4">
                                    <div class="row">
<!--                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">CLO ID</label>
                                                <input name="cloID"  type="text" class="form-control" placeholder="CLO ID: " required="" >
                                            </div>
                                        </div>end col-->
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label"> CLO Name<span class="text-danger">*</span></label>
                                                <input name="cloName" required="" type="text" class="form-control" placeholder="CLO Name:" value="${clo_name}" minlength="3" maxlength="12">
                                            </div> 
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Subject Code<span class="text-danger">*</span></label>
                                                <input name="sylId"class="form-control" readonly=""  value="${syllabus.getSubject().getCode()}">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Syllabus Name<span class="text-danger">*</span></label>
                                                <input name="sylName"class="form-control" readonly=""  value="${syllabus.getName()}">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="mb-6">
                                                <label class="form-label">CLO Description</label>
                                                <textarea name="cloDescription"  type="text" class="form-control" placeholder="CLO Description:">${clo_description}</textarea>
                                            </div> 
                                        </div>
                                          <div class="col-md-6">
                                            <li class="d-flex">
                                                <label class="form-label"  style="padding-right: 20px;font-size: 20px;padding-top: 10px;"> Active </label>
                                                <div class="form-check form-switch">
                                                    <input style="height: 20px; width: 40px;margin-top: 15px; " class="form-check-input checkbox-row" type="checkbox" name="active" value="${active}" <c:if test="${active == '1'}">checked</c:if>/>
                                                        <p id="isActive" class="badge bg-soft-warning"></p>
                                                    </div>
                                                </li>
                                            </div>  
                                    </div>
                                    <button type="submit" name='btn' style="margin-top: 20px" class="btn btn-primary">Add CLO</button>
                                    <div style="color: red">${alert}</div>
                                </form>

                            </div>
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
        <script src="../../JS/AccountList.js"></script>

    </body>
</html>