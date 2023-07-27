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
                            <form action="electivedetail?action=update" method="post" class="">
                                <input type="hidden" id="id" name="id"class="form-control" value="${e.id}" required="">
                                <input type="hidden" id="code" name="Ccode"class="form-control" value="${c.code}" required="">
                                <div class="col-md-12">
                                    <div class="mb-3" style="max-width: 50%">
                                        <label class="form-label" >Elective Name<span class="text-danger">*</span></label>
                                        <input id="sName" name="name"class="form-control" value="${e.name}" required="" maxlength="200">
                                    </div>
                                </div>
                                <div class="col-md-6 d-flex">
                                    <label class="form-label">Active</label>
                                    <div class="form-check form-switch">
                                        <input style="height: 20px; width: 60px; margin-left:1px" class="form-check-input checkbox-row" type="checkbox" name="active" value="${e.is_active}" <c:if test="${e.is_active == '1'}">checked</c:if>/>
                                            <p id="isActive" class="badge bg-soft-warning"></p>
                                        </div>
                                    </div> 
                                    <button id="btn" type="submit" name='btn' class="btn btn-primary">Update </button>
                                <c:if test="${MessUpdate != ''}">
                                    <div style="width: fit-content"class="p-4 alert-outline-success" >
                                        <div class="" role="alert">
                                            <h3> ${MessUpdate} </h3>
                                        </div>
                                    </div>
                                </c:if>
                            </form>
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
    </body>
