<%-- 
    Document   : login
    Created on : 20-05-2023, 10:17:19
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Login FLM</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="../../assets/images/flm-dark.png">
        <!-- Bootstrap -->
        <link href="../../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="../../assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="../../assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="../../assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
         <link rel="stylesheet" href="../../assets/css/toast.style.css"/>
        <!--toast-js-->
        <script src="../../assets/js/jquery.min.js"></script>
        <script src="../../assets/js/toast.script.js"></script>
    </head>

    <body>
                    <c:if test="${toast == 'true'}">
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
                window.onload = addToast("Success", "Change password successfully!", "success", 5000);
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

        <div class="back-to-home rounded d-none d-sm-block">
            <a href="home" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->
        <section class="bg-home d-flex bg-light align-items-center" style="background: url('../assets/images/bg/bg-lines-one.png') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0" >
                            <div class="card-body" >
                                <a href="home"><img src="../../assets/images/flm-dark.png" height="150" class="mx-auto d-block" alt=""></a>
                                <h2 class="text-center">Login</h2>  
                                <form action="login" method="POST" class="login-form mt-4">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="mb-3">

                                                <c:if test="${errorAccount != null}">
                                                    <div style="display: flex">                                                    
                                                        <img src="../../assets/images/wrongaccount.png" width="25px" height="25px" alt="wrongaccount"/>
                                                        <h4 style="color: red; font-family: sans-serif; margin-left:5px"> ${errorAccount}</h4>
                                                    </div>
                                                </c:if>
                                                <c:if test="${errorEmail != null}">
                                                    <div style="display: flex">                                                    
                                                        <img src="../../assets/images/wrongaccount.png" width="25px" height="25px" alt="wrongaccount"/>
                                                        <h5 style="color: red; font-family: sans-serif; margin-left:5px"> ${errorEmail}</h5>
                                                    </div>
                                                </c:if>
                                                <label class="form-label">Email, Mobile or Username<span class="text-danger">*</span></label>
                                                <input type="text" value="${accountNew}" class="form-control" required="" placeholder="Type your Email, Mobile or Username" name="account" 
                                                       ">
                                                <!--                                                pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                                                                                       title="Must contain at least one  number and one uppercase 
                                                                                                       and lowercase letter, and at least 8 or more characters" required="-->
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Password <span class="text-danger">*</span></label>
                                                <input type="password" value="${passwordNew}" name="password" class="form-control" placeholder="Password" 
                                                       required="">
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="d-flex justify-content-between">
                                                <div class="mb-3">
                                                    <div class="form-check">
                                                        <!--                                                        <input class="form-check-input align-middle" type="checkbox" value="" id="remember-check">-->
                                                        <!--                                                        <label class="form-check-label" for="remember-check">Remember me</label>-->
                                                    </div>
                                                </div>
                                                <a href="forgotpassword" class="text-dark h6 mb-0">Forgot password ?</a>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 mb-0">
                                            <div class="d-grid">
                                                <button name="btlogin" class="btn btn-primary">Login</button>
                                            </div>
                                        </div>

                                        <div class="col-lg-12 mt-3 text-center">
                                            <h6 class="text-muted">Or</h6>
                                        </div><!--end col-->


                                    </div>
                                </form>
                                <div style="display:flex"><div class="col-6 mt-3">
                                        <!--                                        <div class="d-grid">
                                                                                    <form action="redirectapplogin" >                                        
                                                                                        <div class="d-grid">
                                                                                            <button name="btfacebook" class="btn btn-soft-primary"><i class="uil uil-google"></i> Facebook</button>
                                                                                        </div>
                                                                                    </form>
                                                                                                                                  <a class="btn btn-soft-primary" 
                                                                                                                                                                               href="
                                                                                                                                                                               https://www.facebook.com/dialog/oauth
                                                                                                                                                                               ?client_id=181456491539490
                                                                                                                                                                               &redirect_uri=http://localhost:9999/FLM_NEW/view/common/login">  
                                                                                                                                                                                <i class="uil uil-facebook"></i> Facebook</a>-->
                                    </div>
                                </div>
                                <div class="col-6 mt-6" style=" margin: 0 auto;">
                                    <form action="redirectapplogin" >                                        
                                        <div class="d-grid">
                                            <button name="btgoogle" class="btn btn-soft-primary"><i class="uil uil-google"></i> Google</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-12 text-center" style="padding-bottom: 20px">
                                <p class="mb-0 mt-3"><small class="text-dark me-2">Don't have an account ?</small> <a href="register" class="text-dark fw-bold">Sign Up</a></p>
                            </div>
                        </div>
                    </div><!---->
                </div> <!--end col-->
            </div><!--end row-->
        </div> <!--end container-->
    </section><!--end section-->
    <!-- Hero End -->

    <!-- javascript -->
    <script src="../../assets/js/bootstrap.bundle.min.js"></script>
    <!-- Icons -->
    <script src="../../assets/js/feather.min.js"></script>
    <!-- Main Js -->
    <script src="../../assets/js/app.js"></script>

</body>

</html>
