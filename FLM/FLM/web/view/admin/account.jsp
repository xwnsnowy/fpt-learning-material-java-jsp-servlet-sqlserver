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
                <!--Trung-->

                <div class="container-fluid">

                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Account List</h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                     <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item">Account</li>
                                    <li class="breadcrumb-item" aria-current="page"><a href="account" class=" breadcrumb-item active">Account List</a></li>
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
                        <form id="filter" action="account" method="get">
                            <div class="row justify-content-start align-items-center">
                                <div class="col-sm-3 col-md-3">
                                    <div class="mb-0 position-relative">
                                        <select name="selectFilter"  class="form-control time-during select2input" onchange="selectFilterSubmit()">
                                            <c:choose>
                                                <c:when test="${selectFilter == ''}">
                                                    <option selected="" value="">All Role Type</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="">All Role Type</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:forEach items="${listRole}" var="itemRole">
                                                <c:choose>
                                                    <c:when test="${selectFilter == itemRole.getName()}">
                                                        <option selected="" value="${itemRole.getName()}">${itemRole.getName()}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${itemRole.getName()}">${itemRole.getName()}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>


                                        </select>
                                    </div>
                                </div><!--end col-->

                                <div class="col-sm-3 col-md-3">
                                    <div class="d-grid">
                                        <input style="line-height: 33px" type="text" name="search" placeholder="Search" value="${search}">
                                    </div>
                                </div><!--end col-->
                                <div class="col-sm-1 col-md-1">
                                    <div class="d-grid">
                                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#appointmentform">Search</button>
                                    </div>
                                </div><!--end col-->
                            </div><!--end row-->
                        </form>
                        <div class=" row">
                            <div class="   col-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <table class="  table table-center bg-white mb-0">
                                        <thead>
                                            <tr >
                                                <th class="border-bottom p-3" style="min-width: 50px;">Id</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Name</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">User Name</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Email</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Mobile No.</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Role</th>
                                                <th class="border-bottom p-3" style="min-width: 120px;">Role Active</th>
                                                <th class="border-bottom p-3" ">Status</th>
                                            </tr>
                                        </thead>
                                        <tbody class="listAccount">
                                            <c:set var="countAccount" value="0"></c:set>
                                            <c:forEach items="${listAccount}" var="acount">
                                                <c:set var="countAccount" value="${countAccount + 1}"></c:set>
                                                    <tr class="itemAccount">
                                                        <th id="id" class="p-3">${acount.getUserId()}</th>
                                                    <td class="py-3">
                                                        <a href="#" class="text-dark">
                                                            <div class="d-flex align-items-center">
                                                                <img id="avata" src="../../assets/images/client/${acount.getPicture()}" class="avatar avatar-md-sm rounded-circle shadow" alt="">
                                                                <span id="name" class="ms-2">${acount.getName()}</span>
                                                            </div>
                                                        </a>
                                                    </td>
                                                    <td id="userName" class="p-3">${acount.getUsername()}</td>
                                                    <td id="email" class="p-3">${acount.getEmail()}</td>

                                                    <td id="mobile" class="p-3">${acount.getMobile()}</td>
                                                    <td id="role" class="p-3">${acount.getRoleName()}</td>

                                                    <td id="isActive" class="p-3"><span class="badge bg-soft-warning">${acount.getIsActive()}</span></td>
                                                    <td id="status" class="p-3"><span class="badge bg-soft-success">${acount.getStatus()}</span></td>
                                                    <td class="text-end p-3">
                                                        <a href="#" class="btn btn-icon btn-pills btn-soft-primary" data-bs-toggle="modal" data-bs-target="#viewprofil" onclick="view('${countAccount}')"><i class="uil uil-eye"></i></a>
                                                        <a href="#" class="btn btn-icon btn-pills btn-soft-success" data-bs-toggle="modal" data-bs-target="#editprofile" onclick="edit('${countAccount}')"><i class="uil uil-pen"></i></a>
                                                        <a href="#" class="btn btn-icon btn-pills btn-soft-danger" onclick="deleteUser('${acount.getUserId()}')"><i class="uil uil-trash" ></i></a>
                                                    </td>

                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                    <form id="delete" action="account?action=2&selectFilter=${selectFilter}&search=${search}" " method="post">
                                        <input id="daleteUserId" type="hidden" name="daleteUserId">
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

        <!-- Modal start -->
        <!-- Profile Settings Start -->
        <div class="modal fade" id="editprofile" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Profile Settings</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form class="mt-4" action="account?action=1&selectFilter=${selectFilter}&search=${search}" method="post">
                        <div id="editAccount" class="modal-body p-3 pt-4">
                            <div class="row align-items-center">
                                <div class="d-flex align-items-center">
                                    <div id="avata"></div>
                                    <input type="text" name='idValue' id="idValue" style="display: none">
                                    <h5 id="id" name='id' class="mb-0 ms-3"></h5>
                                    <h5 class="mb-0 ms-3"> - </h5>
                                    <h5 id="name" class="mb-0 ms-3"></h5>
                                </div>
                            </div><!--end row-->



                            <ul class="list-unstyled mb-0 d-md-flex justify-content-between mt-4">
                                <li>
                                    <ul class="list-unstyled mb-0">
                                        <li class="d-flex">
                                            <h6>User Name:</h6>
                                            <p id="userName" class="text-muted ms-2"></p>
                                        </li>

                                        <li class="d-flex">
                                            <h6 >Email:</h6>
                                            <p id="email" class="text-muted ms-2"></p>
                                        </li>

                                        <li class="d-flex">
                                            <h6 class="mb-0">Mobile:</h6>
                                            <p id="mobile" class="text-muted ms-2 mb-0"></p>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <ul class="list-unstyled mb-0">
                                        <li class="d-flex">
                                            <h6>Role:</h6>
                                            <input type="text" name="preRole" id="preRole" style="display: none">
                                            <div class="col-sm-10 col-md-10" >
                                                <div class="mb-0 position-relative">
                                                    <select name='role' class="form-select form-control" id="role">
                                                        <c:forEach items="${listRole}" var="itemRole">
                                                            <option class="select" value="${itemRole.getName()}">${itemRole.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div><!--end col-->

                                        </li>

                                        <li class="d-flex">
                                            <h6 style="margin-right: 5px;">Role Active: </h6>
                                            <div class="form-check form-switch">
                                                <input name='isActive' class="form-check-input" type="checkbox" id="a-1" onclick="selectBt('isActive')">
                                                <p id="isActive" class="badge bg-soft-warning"></p>
                                            </div>
                                        </li>


                                        <li class="d-flex">
                                            <h6 style="margin-right: 5px;" class="mb-0">Status: </h6>
                                            <div class="form-check form-switch">
                                                <input name='status' class="form-check-input" type="checkbox" id="a-2" onclick="selectBt('status')">
                                                <p id="status" class="badge bg-soft-success" ></p>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <br>
                            <div class="row">
                                <div class="col-sm-12 justify-content-center" style="display: flex">
                                    <input type="submit" id="submit" name="send" class="btn btn-primary" value="Save changes">
                                </div><!--end col-->
                            </div><!--end row-->
                        </div>
                    </form><!--end form-->
                </div>
            </div>
        </div>
        <!-- Profile Settings End -->

        <!-- Profile Start -->
        <div class="modal fade" id="viewprofil" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel1">Profile</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div id="viewAccount" class="modal-body p-3 pt-4">
                        <div class="d-flex align-items-center">
                            <div id="avata"></div>
                            <h5 id="id" class="mb-0 ms-3"></h5>
                            <h5 class="mb-0 ms-3"> - </h5>
                            <h5 id="name" class="mb-0 ms-3"></h5>
                        </div>
                        <ul class="list-unstyled mb-0 d-md-flex justify-content-between mt-4">
                            <li>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex">
                                        <h6>User Name:</h6>
                                        <p id="userName" class="text-muted ms-2"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6 >Email:</h6>
                                        <p id="email" class="text-muted ms-2"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6 class="mb-0">Mobile:</h6>
                                        <p id="mobile" class="text-muted ms-2 mb-0"></p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex">
                                        <h6>Role:</h6>
                                        <p id="role" class="text-muted ms-2"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6>Role Active:</h6>
                                        <p id="isActive" class="text-muted ms-2"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6 class="mb-0">Status:</h6>
                                        <p id="status"class="text-muted ms-2 mb-0"></p>
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

    </body>

</html>
