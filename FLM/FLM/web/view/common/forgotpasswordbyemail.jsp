
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

    </head>

    <c:if test="${sendCode == '1'}">
        <body onload="countdownSendCode(25)">
        </c:if>
        <c:if test="${sendCode == '0'}">
        <body>
        </c:if>
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
                window.onload = addToast("Success", "Update CLO successfully!", "success", 5000);
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
            <a href="login" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->

        <section class="bg-home d-flex bg-light align-items-center" style="background: url('../assets/images/bg/bg-lines-one.png') center;">

            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">

                        <c:choose>
                            <c:when test="${ChangeSuccess == '0'}">
                                <form  id='changepass' class="login-form mt-4"action="forgotpassword" method="post">
                                    <div class="card login-page bg-white shadow mt-4 rounded border-0">
                                        <div class="card-body">
                                            <img src="../../assets/images/flm-dark.png" height="150" class="mx-auto d-block" alt="">
                                            <h4 class="text-center">Recover Account</h4>  

                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <p class="text-muted">Please enter your email address. You will receive a code to create a new password via email.</p>
                                                    <div class="mb-3">
                                                        <label class="form-label">Your Email Address <span id="emailText" class="text-danger">*</span></label>
                                                        <input id="email" type="text" class="form-control" 
                                                               placeholder="Enter Your Email Address" 
                                                               name="email" value='${email}' oninput ="checkEmpty('email')">
                                                    </div>
                                                    <div id="button">
                                                        <div class="col-lg-12">
                                                            <div class="d-grid">
                                                                <button type='submit' class="btn btn-primary" name='btn'>Send</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Enter code <span id="codeVText" class="text-danger">*</span></label>
                                                        <input  
                                                            id='codeV' name="codeV" type="text" class="form-control" 
                                                            placeholder="Enter Your Code" oninput ="checkEmpty('codeV')">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label id="passwordText" class="form-label">New Password <span class="text-danger">*</span></label>
                                                        <input id="password"  type="password" 
                                                               class="form-control" placeholder="Enter Your New Password" 
                                                               name="password" value="${password}">
                                                    </div>

                                                    <div class="mb-3">
                                                        <label id="confirmPasswordText" class="form-label">Confirm Password <span class="text-danger">*</span></label>
                                                        <input id="confirmPassword"  type="password" 
                                                               class="form-control" placeholder="Enter Your Confirm Password   "
                                                               name="confirmPassword"  value="${confirmPassword}">
                                                    </div>
                                                </div>
                                                <div class="mx-auto">
                                                    <p class="mb-0 mt-3"><small class="text-dark me-2"></small> <a href="forgotpasswordbymobile" class="text-dark h6 mb-0">Reset Password By Moblie </a></p> <br>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="d-grid">
                                                    <button type="button" onclick="checkEmptyAll()" class="btn btn-primary">
                                                        Change password </button>
                                                </div>
                                            </div>
                                            <div class="mx-auto">
                                                <p class="mb-0 mt-3"><small class="text-dark me-2">Remember your password ?</small> <a href="login" class="text-dark h6 mb-0">Sign in</a></p>
                                            </div>
                                        </div>
                                        <div id='alertcountdown'>${alert}</div>
                                        <div id='alertconfirm'style="color: red"></div>
                                        <div id='alertregex'style="color: red"></div>
                                        <div id='alertempty'style="color: red"></div>
                                        <div id=''style="color: red">${alert1}</div>
                                        <div style="color: red">${alertCode}</div>
                                    </div>
                                </form>
                            </div><!---->


                        </c:when>
                        <c:when test="${ChangeSuccess == '1'}">
                            <div id="ChangeSuccess">
                                Change Password Success!
                            </div>
                            <div class="container-login100-form-btn">
                                <div class="wrap-login100-form-btn">
                                    <div class="login100-form-bgbtn"></div>
                                    <script>
                                        window.location.href = "login";
                                    </script>
                                    <a href="login"class="login100-form-btn">
                                        Login
                                    </a>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                </div> <!--end col-->

            </div><!--end row-->

        </div> <!--end container-->

        <!--</section>end section-->
        <%--<%@include file="../common/fooster.jsp" %>--%>
        <!-- Hero End -->
        <!-- javascript -->
        <script src="../../assets/js/bootstrap.bundle.min.js"></script>
        <script src="../../assets/js/forget.js"></script>
        <!-- Icons -->
        <script src="../../assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="../../assets/js/app.js"></script>
        <script src="../../assets/js/forgetbyemail.js"></script>
</body>

</html>
