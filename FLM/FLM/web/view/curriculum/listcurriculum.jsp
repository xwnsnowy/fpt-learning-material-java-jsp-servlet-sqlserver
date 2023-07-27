<%-- 
    Document   : admin
    Created on : 23-05-2023, 15:44:56
    Author     : user
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" >
        <title>FLM ListCurriculum</title>
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
        <script src="../../assets/js/select2.min.js"></script>
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
                        rtl: false
                    });
                }
                ;
                // type: success, warning, error, info or notice
                //            addToast(title, content, type, count)
                window.onload = addToast("Success", "Add New Curriculum Successfully!", "success", 5000);
            </script>


        </c:if>
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
                            <h5 class="mb-0">List Curriculum</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">Home</a></li>
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/curriculum/listcurriculum">List Curriculum</a></li>

                                </ul>
                            </nav>
                        </div>


                        <div class="row">
                            <form action="listcurriculum" method="post">
                                <div class="mb-2">
                                    <select class="btn btn-light" name="optionSearch" id="mySelect">
                                        <c:if test="${select == null}">
                                            <option value="1" >Code</option>
                                            <option value="2" >Name</option> 
                                        </c:if>


                                        <c:if test="${select == '1'}">
                                            <option value="1" selected>Code</option>
                                            <option value="2" >Name</option> 
                                        </c:if> 

                                        <c:if test="${select == '2'}">
                                            <option value='1' >Code</option>
                                            <option value='2' selected>Name</option> 
                                        </c:if>

                                    </select>
                                    <input type="text" id="code" name="code" value="${code}" class="border bg-white rounded-pill" placeholder="Curriculum code..." style="height: 40px; width: 500px">
                                    <button type="submit" name="btn-search" class="btn btn-pills btn-primary"><i class="ri-search-line align-middle me-1"></i> Search</button>
                                </div>
                            </form>
                            <c:if test="${sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                <form action="newcurriculum" method="get">
                                    <button type="submit" name="btn-New" class="btn btn-pills btn-warning"> NEW CURRICULUM</button> 
                                </form>
                            </c:if>
                            <c:if test="${sessionScope.acc.getRoleName() == 'CRDD HEAD'}">
                                <form action="newcurriculum" method="get">
                                    <button type="submit" name="btn-New" class="btn btn-pills btn-warning"> NEW CURRICULUM</button> 
                                </form>
                            </c:if>
                            <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN'}">
                                <form action="newcurriculum" method="get">
                                    <button type="submit" name="btn-New" class="btn btn-pills btn-warning"> NEW CURRICULUM</button> 
                                </form>
                            </c:if>
                            <div style="color: green">${Done}</div>

                            <c:if test="${err != null}">
                                <div style="color: red">*${err}</div>
                            </c:if>


                            <div class="col-12 mt-4">
                                <c:if test="${count != null}">
                                    ${count} curriculum(s) found
                                </c:if>
                                <div class="table-responsive shadow rounded">
                                    <table class="table table-center bg-white mb-0">
                                        <c:if test="${List != null}" >
                                            <thead style="background-color: #369cf0">
                                                <tr>
                                                    <th class="border-bottom p-3" >ID</th>
                                                    <th class="border-bottom p-3" >Code</th>
                                                    <th class="border-bottom p-3" >Name</th>

                                                    <c:if test="${sessionScope.acc.getRoleName() != 'CRDD STAFF' && sessionScope.acc.getRoleName() != 'CRDD HEAD' && sessionScope.acc.getRoleName() != 'ADMIN'}">                                                   
                                                        <th class="border-bottom p-3">Description</th>
                                                        <th class="border-bottom p-3">DecisionNo MM/dd/yyyy</th>
                                                        </c:if>
                                                    <th class="border-bottom p-3">Total Credit</th>

                                                    <c:if test="${sessionScope.acc.getRoleName() == 'CRDD STAFF' || sessionScope.acc.getRoleName() == 'CRDD HEAD'|| sessionScope.acc.getRoleName() == 'ADMIN'}">         
                                                        <th class="border-bottom p-3">Status</th>
                                                            <c:if test="${sessionScope.acc.getRoleName() != 'CRDD STAFF'}">
                                                            <th class="border-bottom p-3">Assignee</th>
                                                            </c:if>
                                                        <th class="border-bottom p-3">Action</th>


                                                    </c:if>       
                                                </tr>
                                            </thead>
                                        </c:if>

                                        <c:if test="${sessionScope.acc.getRoleName() != 'CRDD STAFF' && sessionScope.acc.getRoleName() != 'CRDD HEAD' && sessionScope.acc.getRoleName() != 'ADMIN'}">
                                            <tbody>
                                                <c:forEach var="item" items="${List}">
                                                    <tr>
                                                        <td class="p-3">${item.getCurriculum_id()}</td>
                                                        <td class="p-3">${item.getCode()}</td>
                                                        <td class="p-3"><a href="overviewcurriculum?Ccode=${item.getCode()}">${item.getName()}</a></td>
                                                        <td class="p-3">${item.getDescription()}</td>
                                                        <c:if test="${sessionScope.acc == null}">
                                                            <td class="p-3"><a href="http://localhost:9999/FLM_NEW/view/common/login">${item.getDecision_no()} dated ${item.getDecision_date()}</a></td>
                                                        </c:if>
                                                        <c:if test="${sessionScope.acc != null}">
                                                            <td class="p-3"><a href="/FLM_NEW/view/decision/dicisioncontroller?decision_no=${item.getDecision_no()}&mod=1">${item.getDecision_no()} dated ${item.getDecision_date()}</a></td>
                                                        </c:if>                                                   
                                                        <td class="p-3">${item.getTotal_credit()}</td>

                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </c:if>   

                                        <c:if test="${sessionScope.acc.getRoleName() == 'CRDD STAFF' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'ADMIN'}">
                                            <tbody>
                                                <c:forEach var="item" items="${List}">
                                                    <tr>
                                                        <td class="p-3">${item.getCurriculum_id()}</td>
                                                        <td class="p-3">${item.getCode()}</td>
                                                        <td class="p-3"><a href="overviewcurriculum?Ccode=${item.getCode()}">${item.getName()}</a></td>
                                                        <td class="p-3">${item.getTotal_credit()}</td>
                                                        <td class="p-3">${item.getIs_active() ? 'Active' : 'Inactive'}</td>
                                                        <c:if test="${sessionScope.acc.getRoleName() != 'CRDD STAFF'}">
                                                            <td class="p-3">${item.getAssignee()}</td>
                                                        </c:if>
                                                        <td class="p-3"><a href="editcurriculum?C_id=${item.getCurriculum_id()}"><i class="uil uil-pen"></i></a></td>

                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </c:if>

                                    </table>  

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
        <script src="../../JS/Content.js"></script>


    </body>

</html>
