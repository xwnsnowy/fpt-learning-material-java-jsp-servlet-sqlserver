<%-- 
    Document   : userprofile-1
    Created on : Jun 10, 2023, 9:09:59 AM
    Author     : hp
--%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" >
        <title>FLM User Profile</title>
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
                <div class="row justify-content-center">
                    <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0" style="width: 100%">
                        <h2 class="mb-0 pb-2">User Profile</h2>
                        <div class="rounded shadow mt-4">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Profile Setting :</h5>
                            </div>
                            <!--<form action="userprofile" method="post" enctype="multipart/form-data">-->

                            <div class="p-4 border-bottom">
                                <form action="userprofile" method="post" >
                                    <div class="row align-items-center">
                                        <div class="col-lg-2 col-md-4">
                                            <img src="../../assets/images/doctors/${u.getPicture()}" id="image-avatar" class="avatar avatar-md-md rounded-pill shadow mx-auto d-block" alt=""><br>

                                        </div><!--end col-->

                                        <div class="col-lg-5 col-md-8 text-center text-md-start mt-4 mt-sm-0">
                                            <h5 class="">Your Avatar </h5>
                                            <label for="imageFile" class="btn btn-soft-primary">
                                                Custom Upload
                                            </label>
                                            <input type="file" name="avatar" accept="" id="imageFile" onchange="chooseFile(this)" style="display: none">
                                            <!--<p class="text-muted mb-0">For best results, use an image at least 256px by 256px in either .jpg or .png format</p>-->
                                        </div><!--end col-->

                                        <div class="col-lg-5 col-md-12 text-lg-end text-center mt-4 mt-lg-0">                                        
                                            

                                        </div><!--end row-->
                                    </div>
                                    <div class="p-4">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Full_Name</label>
                                                    <input name="full_name" type="text" class="form-control" placeholder="First Name :" value="${u.getName()}" required="" minlength="10" maxlength="50">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">User_Name</label>
                                                    <input name="user_name" type="text" class="form-control" placeholder="Last Name :" value="${u.getUsername()}" required="" minlength="10" maxlength="50">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Your Email</label>
                                                    <input name="email" id="email" type="email" class="form-control" placeholder="Your email :" value="${u.getEmail()}" required="" minlength="10" maxlength="50">
                                                </div> 
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Phone no.</label>
                                                    <input name="mobile" id="number" type="text" class="form-control" placeholder="Phone no. :" value="${u.getMobile()}" required="" maxlength="11">
                                                </div>                                                                               
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Job title</label>
                                                    <input name="jobtitle" id="number" type="text" class="form-control" placeholder="" value="${sessionScope.acc.getRoleName()}" readonly="">
                                                </div>                                                                               
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Company</label>
                                                    <input name="company" id="number" type="text" class="form-control" placeholder="" value="FPT" readonly="">
                                                </div>                                                                               
                                            </div><!--end col-->


<!--                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Verification</label>
                                                    <input name="verification" id="" type="text" class="form-control" placeholder="" value="">
                                                </div>                                                                               
                                            </div>

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <a href="#" class="text-primary">  </a>
                                                </div>                                                                               
                                            </div>

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label"></label>
                                                    <input name="verification" type="submit" class="btn btn-success" value="Send Verification code">
                                                </div>                                                                               
                                            </div>-->


                                        </div><!--end row-->

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <input type="submit" name="save-profile" class="btn btn-primary" value="Save changes">
                                            </div><!--end col-->
                                        </div><!--end row-->

                                    </div>
                                </form>
                                                    <%@include file="../user/changepassword.jsp" %>
                            </div>
                            <!--end form--> 
                        </div>


                    </div>
                    <!-- Footer Start -->
                    <%@include file="../common/footerforsidebar.jsp" %>
                    <!--end footer-->
                    <!-- End -->
                </div>
            </main>
            <%--<%@include file="../user/changepassword.jsp" %>--%>
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
        <!--setting list-->
        <script src="../../JS/SettingList.js"></script>

    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script>
                                        function chooseFile(fileInput) {
                                            if (fileInput.files && fileInput.files[0]) {
                                                var reader = new FileReader();

                                                reader.onload = function (e) {
                                                    $('#image-avatar').attr('src', e.target.result);
                                                };
                                                reader.readAsDataURL(fileInput.files[0]);
                                            }
                                        }
                                        document.getElementById("openBtn").addEventListener("click", function () {
                                            document.getElementById("popupContainer").style.display = "block";
                                            setTimeout(function () {
                                                document.getElementById("popupContent").classList.add("fade-in");
                                            }, 10);
                                        });

                                        document.getElementById("popupContainer").addEventListener("click", function (e) {
                                            if (e.target === this) {
                                                document.getElementById("popupContainer").style.display = "none";
                                                document.getElementById("popupContent").classList.remove("fade-in");
                                            }
                                        });

    </script>

</html>

