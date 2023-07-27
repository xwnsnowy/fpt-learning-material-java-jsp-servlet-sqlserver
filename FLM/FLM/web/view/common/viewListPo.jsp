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
                            <div class="col-lg-12 mt-4">
                                <!--end row-->

                                <form action="clo?action=addlist&sylId=${syllabus.id}" method="post" enctype="multipart/form-data">
                                    <div class="col-12 mt-4">
                                        <div class="table-responsive shadow rounded">
                                            <table class="table table-center bg-white mb-0">
                                                <thead>
                                                    <tr class="headtable" >
                                                        <td colspan="100%"  class="border-bottom p-9" style="background-color:#396cf0;text-align: center;height:60px; color: #ffffff">Valid list</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="border-bottom p-3" style="min-width: 90px;">CLO ID</th>
                                                        <th class="border-bottom p-3" style="min-width: 90px;">CLO Name</th>
                                                        <th class="border-bottom p-3" style="min-width: 150px;">CLO Description</th>

                                                        <th class="border-bottom p-3" style="max-width: 50px;">Syllabus_ID</th>
                                                        <th class="border-bottom p-3" style="max-width: 50px;">Active</th>
                                                        <th class="border-bottom p-3" style="max-width: 50px;">Create_by</th>
                                                        <th class="border-bottom p-3" style="max-width: 50px;">Create_at</th>
                                                        <!--<th class="border-bottom p-3" style="min-width: 90px;">Create_At</th>-->
                                                        <!--<th class="border-bottom p-3" style="min-width: 120px;"></th>-->

                                                    </tr>
                                                </thead>
                                                <c:forEach var="clo" items="${validCloList}">
                                                    <tr>
                                                        <td class="border-bottom p-3" style="min-width: 100px;">${clo.getClo_id()}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${clo.getClo_name()}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${clo.getClo_description()}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${clo.syllabus_id}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${clo.is_active}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${clo.created_by}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${clo.created_at}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                            <table class="table table-center bg-white mb-0">
                                                <thead>
                                                    <tr class="headtable" >
                                                        <td colspan="100%"  class="border-bottom p-9" style="background-color:#396cf0;text-align: center;height:60px; color: #ffffff">Invalid list</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="border-bottom p-3" style="min-width: 90px;">CLO ID</th>
                                                        <th class="border-bottom p-3" style="min-width: 90px;">CLO Name</th>
                                                        <th class="border-bottom p-3" style="min-width: 150px;">CLO Description</th>

                                                        <th class="border-bottom p-3" style="max-width: 50px;">Syllabus_ID</th>
                                                        <th class="border-bottom p-3" style="max-width: 50px;">Active</th>
                                                        <th class="border-bottom p-3" style="max-width: 50px;">Create_by</th>
                                                        <th class="border-bottom p-3" style="max-width: 50px;">Create_at</th>
                                                        <!--<th class="border-bottom p-3" style="min-width: 90px;">Create_At</th>-->
                                                        <!--<th class="border-bottom p-3" style="min-width: 120px;"></th>-->

                                                    </tr>
                                                </thead>
                                                <c:forEach var="o" items="${invalidCloList}">
                                                    <tr>
                                                        <td class="border-bottom p-3" style="min-width: 100px;">${o.getClo_id()}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${o.getClo_name()}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${o.getClo_description()}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${o.syllabus_id}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${o.is_active}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${o.created_by}</td>
                                                        <td class="border-bottom p-3" style="min-width: 110px;">${o.created_at}</td>

                                                    </tr>
                                                </c:forEach>

                                            </table>
                                            <table class="table table-center bg-white mb-0">
                                                <tr class="headtable" >
                                                    <td colspan="100%"  class="border-bottom p-9" style="background-color:#396cf0;text-align: center;height:60px; color: #ffffff">Reason</td>
                                                </tr>
                                                <td class="border-bottom p-3" style="min-width: 100px;">
                                                    <ul>
                                                        <c:forEach var="message" items="${invalidCloMessage}">
                                                            <li><c:out value="${message}" /></li>
                                                            </c:forEach>
                                                    </ul>
                                                </td>

                                            </table>
                                            <button type="submit"  style="margin-top: 20px" class="btn btn-primary">Add list</button>




                                        </div>
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
        <script src="../../JS/AccountList.js"></script>

    </body>
</html>