<%-- 
    Document   : newjsp
    Created on : May 23, 2023, 11:20:39 AM
    Author     : hp
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>FLM</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="../../../index.jsp" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="../../assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="../../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="../../assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="../../assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- SLIDER -->
        <link rel="stylesheet" href="../../assets/css/tiny-slider.css"/>
        <!-- Css -->
        <link href="../../assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />

    </head>
    <body>
        <header id="topnav" class="navigation sticky">
            <div class="container">
                <!-- Logo container-->
                <div>
<!--                    <a class="logo" href="http://localhost:9999/FLM_NEW/view/common/home">
                        <span class="logo-light-mode">
                            <img src="../../assets/images/flm-dark.png" class="l-dark" height="80" alt="">
                            <img src="../../assets/images/flm-dark.png" class="l-light" height="80" alt="">
                        </span>
                        <img src="../../assets/images/flm-dark.png" height="50" class="logo-dark-mode" alt="">
                    </a>-->
                    <a class="logo" href="/FLM_NEW/logout">
                        <span class="logo-light-mode">
                            <img src="assets/images/flm-dark.png" class="l-dark" height="80" alt="">
                            <img src="assets/images/flm-dark.png" class="l-light" height="80" alt="">
                        </span>
                        <img src="assets/images/flm-dark.png" height="80" class="logo-dark-mode" alt="">
                    </a>
                </div>
                <!-- End Logo container-->

                <!-- Start Mobile Toggle -->
                <div class="menu-extras">
                    <div class="menu-item">
                        <!-- Mobile menu toggle-->
                        <a class="navbar-toggle" id="isToggle" onclick="toggleMenu()">
                            <div class="lines">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </a>
                        <!-- End mobile menu toggle-->
                    </div>
                </div>
                <!-- End Mobile Toggle -->


                <!-- Start Dropdown -->
                <ul class="dropdowns list-inline mb-0">
                    <c:if test="${sessionScope.acc == null}">
                        <li class="list-inline-item mb-0 ms-1">
                            <a href="http://localhost:9999/FLM_NEW/view/common/register" class="btn btnlgrg btn-soft-primary" style="font-size: medium"> Register </a>
                        </li>

                        <li class="list-inline-item mb-0 ms-1">
                            <a href="http://localhost:9999/FLM_NEW/view/common/login" class="btn btnlgrg btn-soft-primary" style="font-size: medium"> Login </a>
                        </li>

                    </c:if>
                    <c:if test="${sessionScope.acc != null}">
<!--                        <li class="list-inline-item mb-0 ms-1">
                            <div class="dropdown dropdown-primary">
                                <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="../../assets/images/doctors/${sessionScope.acc.getPicture()}" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                                <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 260px;">
                                    <div class="dropdown-item d-flex align-items-center text-dark" >
                                        <img src="../../assets/images/doctors/${sessionScope.acc.getPicture()}" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                        <span class="d-block mb-1" style="margin-left: 5px">${sessionScope.acc.getName()}</span>
                                    </div>
                                    <div class="flex-1 ms-2" ">

                                        <small style="margin-left:14px" >User_name:</small><small class="text-muted" > ${sessionScope.acc.getUsername()}</small><br>
                                        <small style="margin-left:14px" >Email:</small><small class="text-muted"> ${sessionScope.acc.getEmail()}</small><br>
                                        <small style="margin-left:14px" >Role: </small><small class="text-muted"> ${sessionScope.acc.getRoleName()}</small><br>    
                                    </div>
                                    <a class="dropdown-item text-dark" href="doctor-dashboard.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-dashboard align-middle h6"></i></span> Dashboard</a>

                                    <div class="dropdown-divider border-top"></div>
                                    <a class="dropdown-item text-dark" href="http://localhost:9999/FLM_NEW/view/user/userprofile"><span class="mb-0 d-inline-block me-1"><i class="uil uil-setting align-middle h6"></i></span> Profile Settings</a>
                                    <a class="dropdown-item text-dark" href="/FLM_NEW/logout"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Logout</a>
                                </div>
                            </div>
                        </li>-->
                    </c:if>
                </ul>
                <!--end drop down-->



                <!--start header view-->
                <div id="navigation">
                    <!-- Navigation Menu-->
                    <c:if test="${sessionScope.acc == null}">

                        <c:if test="${sessionScope.role == null}">
                            <ul class="navigation-menu nav-left nav-light">
                                <li class="has-submenu parent-menu-item">
                                    <a href="http://localhost:9999/FLM_NEW/view/curriculum/listcurriculum">Curriculum</a>
                                </li>
                                <li class="has-submenu parent-parent-menu-item">
                                    <a href="/FLM_NEW/view/common/subjectpredecessors">Subject Predeccessors</a>
                                </li>
                                <li class="has-submenu parent-menu-item">
                                    <a href="/FLM_NEW/view/common/subjectsuccessors">Subject Successors</a>
                                </li>
                            </ul>
                        </c:if>


                    </c:if>





                    <c:if test="${sessionScope.acc.roleName != null}">
                        <ul class="navigation-menu nav-left nav-light">
                            <li class="has-submenu parent-menu-item">
                                <a href="http://localhost:9999/FLM_NEW/view/curriculum/listcurriculum">Curriculum</a>
                            </li>
                            <c:if test="${sessionScope.acc.roleName == 'student'}">
                                <li class="has-submenu parent-menu-item">
                                    <a href="#">Syllabus</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc.roleName == 'teacher'}">
                                <li class="has-submenu parent-menu-item">
                                    <a href="#">Syllabus</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc.roleName == 'admin'}">
                                <li class="has-submenu parent-menu-item">
                                    <a href="#">Syllabus</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc.roleName == 'crdd staff'}">
                                <li class="has-submenu parent-menu-item">
                                    <a href="#">Syllabus</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc.roleName == 'crdd head'}">
                                <li class="has-submenu parent-menu-item">
                                    <a href="#">Syllabus</a>
                                </li>
                            </c:if>
                            <li class="has-submenu parent-parent-menu-item">
                                <a href="/FLM_NEW/view/common/subjectpredecessors">Subject Predeccessors</a>
                            </li>
                            <li class="has-submenu parent-menu-item">
                                <a href="/FLM_NEW/view/common/subjectsuccessors">Subject Successors</a>
                            </li>         

                        </ul>
                    </c:if>
                </div>
                <!--end header view-->

            </div><!--end container-->
        </header><!--end header-->
        <script src="../../assets/js/bootstrap.bundle.min.js"></script>
        <!-- SLIDER -->
        <script src="../../assets/js/tiny-slider.js"></script>
        <script src="../../assets/js/tiny-slider-init.js"></script>
        <!-- Counter -->
        <script src="../../assets/js/counter.init.js"></script>
        <!-- Icons -->
        <script src="../../assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="../../assets/js/app.js"></script>
    </body>
</html>
