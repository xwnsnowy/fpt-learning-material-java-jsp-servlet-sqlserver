<%-- 
    Document   : home.jsp
    Created on : May 21, 2023, 1:54:34 AM
    Author     : hp
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" >
        <title>FLM Homepage</title>
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
            #bg{
                background-image: url('../..//assets/images/bg/bgr.jpg');
                background-repeat: no-repeat;
                background-size: cover;
                background-position: center center;
            }

        </style>
    </head>

    <body>

        <div class="page-wrapper doctris-theme toggled">

            <%@include file="../common/sidebar.jsp" %>
            <main class="page-content bg-light">
                <%@include file="../common/headerforsidebar.jsp" %>
                <div class="layout-specing" id="bg" ">
                    <!--                    <div class="container-fluid" >
                                            <div class="col-xl-10" >
                                                <div class="row">
                                                    When register default role guest
                    <c:if test="${sessionScope.acc.roleName == 'GUEST'}">
                                                            <div class="col-xl-3 col-md-4 col-12 mt-5">
                                                                <div class="card features feature-primary bg-transparent border-0">
                                                                    <div class="card-body p-0 mt-3">
                                                                        <div  class="title text-dark h5">GUEST</div>
                                                                        <a href="http://localhost:9999/FLM_NEW/view/curriculum/listcurriculum" class="btn btn-primary" style="margin-bottom: 10px"> Curriculum </a> <br>
                                                                        <a href="#" class="btn btn-primary" style="margin-bottom: 10px"> Subject Predeccessors </a> <br>
                                                                        <a href="#" class="btn btn-primary" style="margin-bottom: 10px"> Subject Successors </a> <br>
                                                                    </div>
                                                                </div>
                                                            </div>end col
                    </c:if>
                    When click join now or home
                    <c:if test="${sessionScope.acc == null}">
                                                            <div class="col-xl-3 col-md-4 col-12 mt-5">
                                                                <div class="card features feature-primary bg-transparent border-0">
                                                                    <div class="card-body p-0 mt-3">
                                                                        <div  class="title text-dark h5">GUEST</div>
                                                                        <a href="http://localhost:9999/FLM_NEW/view/curriculum/listcurriculum" class="btn btn-primary" style="margin-bottom: 10px"> Curriculum </a> <br>
                                                                        <a href="#" class="btn btn-primary" style="margin-bottom: 10px"> Subject Predeccessors </a> <br>
                                                                        <a href="#" class="btn btn-primary" style="margin-bottom: 10px"> Subject Successors </a> <br>
                                                                    </div>
                                                                </div>
                                                            </div>end col
                    </c:if>
                </div>
            </div>
        </div>-->
                </div>
                <%@include file="../common/footerforsidebar.jsp" %>
            </main>

        </div>


        <script src="../../assets/js/easy_background.js"></script>         

        <script src="../../assets/js/jquery.timepicker.min.js"></script> 
        <script src="../../assets/js/timepicker.init.js"></script> 
        <script src="../../assets/js/jquery.min.js"></script>
        <script src="../../assets/js/simplebar.min.js"></script>
        <script src="../../assets/js/select2.min.js"></script>
        <script src="../../assets/js/select2.init.js"></script>
        <script src="../../assets/js/flatpickr.min.js"></script>
        <script src="../../assets/js/flatpickr.init.js"></script>
        <script src="../../assets/js/simplebar.min.js"></script>
        <script src="../../assets/js/apexcharts.min.js"></script>
        <script src="../../assets/js/columnchart.init.js"></script>

        <script src="../../assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="../../assets/js/app.js"></script>

        <script src="../../assets/js/bootstrap.bundle.min.js"></script>
        <!-- SLIDER -->
        <script src="../../assets/js/tiny-slider.js"></script>
        <script src="../../assets/js/tiny-slider-init.js"></script>
        <!-- Counter -->
        <script src="../../assets/js/counter.init.js"></script>



    </body>
</html>
