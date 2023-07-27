<%-- 
    Document   : newcurriculum
    Created on : Jun 5, 2023, 10:38:49 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" >
        <title>FLM New Curriculum</title>
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
                <!--Duy-->

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <div>
                                <h5 class="mb-0">Add new Curriculum</h5>
                            </div>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/curriculum/listcurriculum">Curriculum</a></li>

                                    <li class="breadcrumb-item active" aria-current="page">New curriculum </li>
                                </ul>
                            </nav>



                        </div>

                        <div style="color: red">${err}</div>
                        <div style="color: red">${DecisionId}</div>
                        <div style="color: red">${errCode}</div>
                        <div style="color: red">${errdecisionid}</div>
                        <div style="color: red">${CurriculumId}</div>


                        <div class="modal-body p-3 pt-4">
                            <form class="mt-4" action="newcurriculum" method="post">
                                <div class="row">
                                    <!--                                    <div class="col-md-6">
                                                                            <div class="mb-3">
                                                                                <label class="form-label" style="font-weight: bold;">Curriculum ID<span class="text-danger">*</span></label>
                                                                                <input name="id" id="id" type="text" class="form-control" value="${id}" placeholder="Curriculum id: " required="">
                                                                            </div>
                                                                        </div>-->

                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label" style="font-weight: bold;">Curriculum Code<span class="text-danger">*</span></label>
                                            <input name="code" id="code" type="text" class="form-control" value="${code}" placeholder="Curriculum Code:" required="">
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label" style="font-weight: bold;">Curriculum Name<span class="text-danger">*</span></label>
                                            <input name="name" id="name" type="text" class="form-control" list="list" value="${name}" placeholder="Curriculum Name:" required="">

                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label" style="font-weight: bold;">Curriculum English Name<span class="text-danger">*</span></label>
                                            <input name="ename" id="ename" type="text" class="form-control" list="list" value="${ename}" placeholder="Curriculum Name:" required="">

                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD'}">        
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label" style="font-weight: bold;">CRDD STAFF<span class="text-danger">*</span></label><br>
                                                <select class="btn btn-light" name="staff">

                                                    <c:forEach var="item" items="${ListStaff}">
                                                        <option value="${item.getUserId()}">${item.getName()}</option>
                                                    </c:forEach>
                                                </select>

                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label" style="font-weight: bold;">Description<span class="text-danger">*</span></label>
                                            <textarea name="description" id="comments" rows="4" class="form-control" placeholder="Description :" required="">${description}</textarea>
                                        </div>
                                    </div>

                                </div><!--end row-->

                                <div class="row">
                                    <div class="col-sm-12">
                                        <input type="submit" id="submit" name="add" class="btn btn-primary" value="Add Curriculum">
                                    </div><!--end col-->
                                </div><!--end row-->



                            </form><!--end form-->
                        </div>
                    </div>
                </div>
                <!-- Footer Start -->
                <%@include file="../common/footerforsidebar.jsp" %>
                <!--end footer-->
                <!-- End -->
            </main>
        </div><!--end container-->


        <!--End page-content" -->
    </div>
    <!-- page-wrapper -->

    <!-- Offcanvas Start -->
    <div class="offcanvas offcanvas-end bg-white shadow" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
        <div class="offcanvas-header p-4 border-bottom">
            <h5 id="offcanvasRightLabel" class="mb-0">
                <img src="../../assets/images/logo-dark.png" height="24" class="light-version" alt="">
                <img src="../../assets/images/logo-light.png" height="24" class="dark-version" alt="">
            </h5>
            <button type="button" class="btn-close d-flex align-items-center text-dark" data-bs-dismiss="offcanvas" aria-label="Close"><i class="uil uil-times fs-4"></i></button>
        </div>
        <div class="offcanvas-body p-4 px-md-5">
            <div class="row">
                <div class="col-12">
                    <!-- Style switcher -->
                    <div id="style-switcher">
                        <div>
                            <ul class="text-center list-unstyled mb-0">
                                <li class="d-grid"><a href="javascript:void(0)" class="rtl-version t-rtl-light" onclick="setTheme('style-rtl')"><img src="../../assets/images/layouts/light-dash-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                <li class="d-grid"><a href="javascript:void(0)" class="ltr-version t-ltr-light" onclick="setTheme('style')"><img src="../../assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                <li class="d-grid"><a href="javascript:void(0)" class="dark-rtl-version t-rtl-dark" onclick="setTheme('style-dark-rtl')"><img src="../../assets/images/layouts/dark-dash-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                <li class="d-grid"><a href="javascript:void(0)" class="dark-ltr-version t-ltr-dark" onclick="setTheme('style-dark')"><img src="../../assets/images/layouts/dark-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                <li class="d-grid"><a href="javascript:void(0)" class="dark-version t-dark mt-4" onclick="setTheme('style-dark')"><img src="../../assets/images/layouts/dark-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Dark Version</span></a></li>
                                <li class="d-grid"><a href="javascript:void(0)" class="light-version t-light mt-4" onclick="setTheme('style')"><img src="../../assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Light Version</span></a></li>
                                <li class="d-grid"><a href="../../landing/index.html" target="_blank" class="mt-4"><img src="../../assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Landing Demos</span></a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- end Style switcher -->
                </div><!--end col-->
            </div><!--end row-->
        </div>

        <div class="offcanvas-footer p-4 border-top text-center">
            <ul class="list-unstyled social-icon mb-0">
                <li class="list-inline-item mb-0"><a href="https://1.envato.market/doctris-template" target="_blank" class="rounded"><i class="uil uil-shopping-cart align-middle" title="Buy Now"></i></a></li>
                <li class="list-inline-item mb-0"><a href="https://dribbble.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-dribbble align-middle" title="dribbble"></i></a></li>
                <li class="list-inline-item mb-0"><a href="https://www.facebook.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-facebook-f align-middle" title="facebook"></i></a></li>
                <li class="list-inline-item mb-0"><a href="https://www.instagram.com/shreethemes/" target="_blank" class="rounded"><i class="uil uil-instagram align-middle" title="instagram"></i></a></li>
                <li class="list-inline-item mb-0"><a href="https://twitter.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-twitter align-middle" title="twitter"></i></a></li>
                <li class="list-inline-item mb-0"><a href="mailto:support@shreethemes.in" class="rounded"><i class="uil uil-envelope align-middle" title="email"></i></a></li>
                <li class="list-inline-item mb-0"><a href="../../../../../../index.html" target="_blank" class="rounded"><i class="uil uil-globe align-middle" title="website"></i></a></li>
            </ul><!--end icon-->
        </div>
    </div>
    <!-- Offcanvas End -->

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
    <script src="../../JS/AccountList.js"></script>
    <!--setting list-->
    <script src="../../JS/SettingList.js.js"></script>
</body>

</html>
