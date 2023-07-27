<%-- 
    Document   : podetail
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
        <style>

        </style>
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
                                    <li class="breadcrumb-item">POs</li>
                                    <li class="breadcrumb-item active" aria-current="page">PO Details</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-lg-8 mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <div class="row align-items-center">
                                        <div class="col-lg-2 col-md-4">
                                            <img src="../../assets/images/client/avata-default.jpg" class="avatar avatar-md-md rounded-pill shadow mx-auto d-block" alt="">
                                        </div><!--end col-->

                                        <div class="col-lg-5 col-md-8 text-center text-md-start mt-4 mt-sm-0">

                                            <p class="text-muted mb-0">INFROMATION FOR USER</p>
                                        </div><!--end col-->

                                    </div><!--end col-->
                                </div><!--end row-->

                                <form action="podetail?Ccode=${c.code}&po_id=${p.po_id}" method="post" class="mt-4">
                                    <div class="row">
                                        <!--end col-->
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">PO ID</label>
                                                <input name="id" readonly=""  type="text" class="form-control" value="${p.po_id}" >
                                            </div> 
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">PO_Name</label>
                                                <input name="name" type="text" class="form-control" value="${p.po_name}" required="" autocomplete="off">
                                                <span id="nameAlert" class="alert"></span>
                                            </div> 
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Curriculum Code</label>
                                                <input readonly="" name="code" type="text" class="form-control" value="${Ccode}"/>
                                            </div> 
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Curriculum Name</label>
                                            <input readonly=""class="form-control" name="Cname" style="width:884px"value=" ${c.name}">

                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label"> PO_Description</label>
                                            <textarea class="form-control" name="description"style="width:884px; word-wrap: break-word;">${p.po_description}</textarea>

                                        </div> 
                                        <div>
                                            <li class="d-flex">
                                                <label class="form-label"  style="padding-right: 20px;font-size: 20px"> Active </label>

                                                <div class="form-check form-switch">
                                                    <input style="height: 20px; width: 60px" class="form-check-input checkbox-row" type="checkbox" name="active" value="${p.getIs_Active()}" <c:if test="${p.getIs_Active() == '1'}">checked</c:if>/>
                                                        <p id="isActive" class="badge bg-soft-warning"></p>
                                                    </div>
                                                </li>
                                            </div>
                                        </div> 
                                        <button id="btn" type="submit" name='btn' class="btn btn-primary">Update POs</button>
                                        <div>
                                            <h2 style="color: red">${errorMessage}</h2>
                                    </div>
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
        <script src="../../JS/Po.js"></script>
    </body>