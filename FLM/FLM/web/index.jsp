<%-- 
    Document   : home
    Created on : 20-05-2023, 16:24:36
    Author     : user
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>FLM Homepage</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="index.jsp" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        
        <link rel="shortcut icon" href="assets/images/flm-dark.png">
        <!-- Bootstrap -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
    </head>
    <body>
        
        <%@include file="view/common/header.jsp" %>
                <!-- Start Hero -->
       
        <section class="bg-half-260 d-table w-100" style="background: url('assets/images/bg/overlay.png'), url('assets/images/bg/bgalpha.png') center;">
            <div class="bg-overlay-dark " ></div>
            <div class="container">
                <div class="row mt-5 mt-lg-0">
                    <div class="col-12">
                        <div class="heading-title">
                            <!--<img src="assets/images/logo-icon.png" height="50" alt="">-->
                            <h4 class="display-4 fw-bold text-white title-dark mt-3 mb-4">FPT University <br>Learning Materials</h4>
                            <p class="para-desc text-white-50 mb-0">Construct knowledge and personalize the learning way to empower leaners's full potential.</p>
                            
                            <div class="mt-4 pt-2">
                                <form action="view/common/home" method="post">
                                    <input type="submit" class="btn btn-primary" value="Join now">
                                </form>
                                
                                <!--<p class="text-white-50 mb-0 mt-2">T&C apply. Please read <a href="#" class="text-white-50">Terms and Conditions <i class="ri-arrow-right-line align-middle"></i></a></p>-->
                            </div>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->
        
        
        
        
        <%@include file="view/common/fooster.jsp" %>
        
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- SLIDER -->
        <script src="assets/js/tiny-slider.js"></script>
        <script src="assets/js/tiny-slider-init.js"></script>
        <!-- Counter -->
        <script src="assets/js/counter.init.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/app.js"></script>
    </body>
    
    
</html>