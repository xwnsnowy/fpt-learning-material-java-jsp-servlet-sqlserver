<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>FLM</title>
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
        <link rel="stylesheet" href="../../Style/register.css"/>
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-auth.js"></script>
    </head>

    <body  >
        <c:if test="${sendCode == '0'}">
            <script>
                window.onload = function () {
                    RegisterWithEmial();
                    document.getElementById('registerByMobile').style.display = 'none';
                };
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
            <a href="/FLM_NEW/view/common/home" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->
        <section class="bg-half-150 d-table w-100 bg-light" style="background: url('../../assets/images/bg/bg-lines-one.png') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">

                        <img src="../../assets/images/flm-dark.png" height="150" class="mx-auto d-block" alt="">


                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <c:choose>
                                    <c:when test="${registerSuccess == '0'}">
                                        <h4 class="text-center">Sign Up</h4>  
                                        <form id="registerForm" action="register" method="post" >
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="mb-3">                                               
                                                        <label id="fullNameText" class="form-label">Full name <span class="text-danger">*</span></label>
                                                        <input id="fullName" type="text" class="form-control" name="fullName" placeholder="Full Name" value="${fullName}">
                                                    </div>
                                                </div>
                                                <div class="text-end">
                                                    <button type="button" id="buttonRegisterWith" onclick="RegisterWith()">
                                                        Register with mobile?
                                                    </button>
                                                </div>
                                                <div id="registerByEmail" class="row">
                                                    <div class=" col-md-8">
                                                        <div>
                                                            <label id="emailText" class="form-label">Your Email <span class="text-danger">*</span> </label>
                                                            <input id="email" type="email" required="" class="form-control" placeholder="Email" name="email"  value="${email}">
                                                        </div>

                                                    </div>
                                                    <div id="button" class="col-md-3 ">
                                                        <div >
                                                            <label  class="form-label"></label>
                                                            <button name="sendCodeBT" class="btn btn-primary" >Send</button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="registerByMobile" class="row">
                                                    <div class=" col-md-8">
                                                        <label id="mobileText" class="form-label">Mobile <span class="text-danger">*</span></label>
                                                        <input id="mobile" type="tel"  class="form-control" placeholder="Phone Number" name="mobile"  value="${mobile}">


                                                    </div>
                                                    <div id="buttonForMobile" class="col-md-3">
                                                        <label  class="form-label"></label>
                                                        <button type="button" name="sendCodeBT" class="btn btn-primary" onclick="SendVerificationCodeForMobile()" >Send</button>

                                                        <div id="recaptcha">
                                                            <div id="recaptcha-0"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br>
                                                <br>

                                                <c:if test="${sendCode == '1'}">
                                                    <script>
                                                        window.onload = function () {
                                                            countdownSendCode(25, 'alartForSendCode');
                                                            document.getElementById('registerByMobile').style.display = 'none';
                                                        };
                                                    </script>
                                                </c:if>
                                                <div id="alartForSendCode">
                                                    ${alartForSendCode}
                                                </div>
                                                <div id="alartForSendCodeForMobile">
                                                    ${alartForSendCodeForMobile}
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="mb-3">
                                                        <label id="verificationCodeText" class="form-label">Verification Code <span class="text-danger">*</span></label>
                                                        <input id="verificationCode" type="text" class="form-control" name="verificationCode" placeholder="Verification Code" value="${verificationCode}" >
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="mb-3">
                                                        <label id="passwordText" class="form-label">Password <span class="text-danger">*</span></label>
                                                        <input id="password" type="password" class="form-control" name="password" placeholder="Password" value="${password}">
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="mb-3">
                                                        <label id="confirmPasswordText" class="form-label">Confirm Password <span class="text-danger">*</span></label>
                                                        <input id="confirmPassword" type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password" value="${confirmPassword}">
                                                    </div>
                                                </div>
                                                <div class="alart" id="alart">
                                                    ${alart}
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="d-grid">
                                                        <button type="button" class="btn btn-primary"  onclick="checkRregister()">Register</button>
                                                    </div>
                                                </div>

                                                <div class="mx-auto">
                                                    <p class="mb-0 mt-3"><small class="text-dark me-2">Already have an account ?</small> <a href="/FLM_NEW/view/common/login" class="text-dark fw-bold">Sign in</a></p>
                                                </div>
                                            </c:when>
                                            <c:when test="${registerSuccess == '1'}">
                                                <div id="registerSuccess">
                                                    Register Success!
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="d-grid">
                                                        <a href="/FLM_NEW/view/common/login" type="button" class="btn btn-primary">Login</a>
                                                    </div>
                                                </div>
                                            </c:when>

                                        </c:choose>
                                    </div>
                                </form>

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
        <!--register code--> 
        <script src="../../JS/Register.js"></script>

    </body>

</html>