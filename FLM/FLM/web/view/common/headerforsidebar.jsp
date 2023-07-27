<%-- 
    Document   : s
    Created on : May 26, 2023, 7:22:48 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Doctris - Doctor Appointment Booking System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="../../../index.html" />
        <meta name="Version" content="v1.2.0" />
    </head>
    <body>
        <div class="top-header">
            <div class="header-bar d-flex justify-content-between border-bottom">
                <div class="d-flex align-items-center">
                    <a href="#" class="logo-icon">
                        <img src="../../assets/images/logo-icon.png" height="30" class="small" alt="">
                        <span class="big">
                            <img src="../../assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                            <img src="../../assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                        </span>
                    </a>
                    <a id="close-sidebar" class="btn btn-icon btn-pills btn-soft-primary ms-2" href="#">
                        <i class="uil uil-bars"></i>
                    </a>

                </div>
                <c:if test="${sessionScope.acc.getRoleName() !=null}" >
<!--                <a class="btn btn-lg btn-outline-primary m-1">Curriculum</a>
                <a class="btn btn-lg btn-outline-primary m-1">Syllabus</a>
                <a class="btn btn-lg btn-outline-primary m-1">Subject Predecessor</a>
                <a class="btn btn-lg btn-outline-primary m-1">Subject Successor</a>-->
                </c:if>
<!--                <a href="listcurriculum" class="btn btn-lg btn-outline-primary m-1">Curriculum</a>
                <a class="btn btn-lg btn-outline-primary m-1">Subject Predecessor</a>
                <a class="btn btn-lg btn-outline-primary m-1">Subject Successor</a>-->
                <ul class="list-unstyled mb-0">
                    <li class="list-inline-item mb-0">
                        <div class="dropdown dropdown-primary">
                            <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="../../assets/images/language/vietnam.png" class="avatar avatar-ex-small rounded-circle p-2" alt=""></button>
                            
                        </div>
                    </li>

                    

                    <li class="list-inline-item mb-0 ms-1">
                        <div class="dropdown dropdown-primary">
                            <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="../../assets/images/doctors/${sessionScope.acc.getPicture()}" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                            <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                                <div class="dropdown-item d-flex align-items-center text-dark" >
                                    <img src="../../assets/images/doctors/${sessionScope.acc.getPicture()}" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                    <div class="flex-1 ms-2">
                                        <span class="d-block mb-1">${sessionScope.acc.getName()}</span>
                                        <small class="text-muted">${sessionScope.acc.getRoleName()}</small>
                                    </div>
                                </div>
                                
                                <a class="dropdown-item text-dark" href="http://localhost:9999/FLM_NEW/view/user/userprofile"><span class="mb-0 d-inline-block me-1"><i class="uil uil-setting align-middle h6"></i></span> Profile Settings</a>
                                <div class="dropdown-divider border-top"></div>
                                <a class="dropdown-item text-dark" href="/FLM_NEW/logout"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Logout</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>
