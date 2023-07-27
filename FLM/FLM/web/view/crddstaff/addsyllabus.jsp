<%-- 
    Document   : addSyllabus
    Created on : Jun 22, 2023, 11:58:58 PM
    Author     : Admin
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta charset="utf-8" />
        <title>FLM_USER MANAGEMENT</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="../../../index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="../../assets/images/flm-dark.png">
        <!-- Bootstrap -->
        <link href="../../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="../../assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="../../assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="../../assets/css/flatpickr.min.css">
        <!-- Icons -->
        <link href="../../assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="../../assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="../../assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
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
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Add New Syllabus</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item">Syllbus</li>
                                    <li class="breadcrumb-item active" aria-current="page">Add New Syllabus</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="card border-0 p-5 rounded shadow" style="width: fit-content">
                                <div class="col-lg-12 mt-4">
                                    <form id="formAdd" action="syllabus?action=add" method="post" class="mt-4" onsubmit ="resetToast()">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Subject<span class="text-danger">*</span></label>
                                                    <select name="selectSubject" id="selectSubject"  class="form-control time-during select2input" required="" >
                                                        <option value="">None</option>
                                                        <c:forEach items="${listSubject}" var="subject">
                                                            <c:choose>
                                                                <c:when test="${subjectR == subject.getId()}">
                                                                    <option selected="" class="select" value="${subject.getId()}">${subject.getCode()}: ${subject.getName()}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option class="select" value="${subject.getId()}">${subject.getCode()}: ${subject.getName()}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>

                                                    </select>
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Syllabus Name<span class="text-danger">*</span></label>
                                                    <input id="sName" name="sName"class="form-control" value="${name}" required="">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Degree Level<span class="text-danger">*</span></label>
                                                    <select name="selectDegree" id="selectCombo"  class="form-control time-during select2input" required="" >
                                                        <option value="">None</option>
                                                        <c:forEach items="${listDegree}" var="degree">
                                                            <c:choose>
                                                                <c:when test="${degreeR == degree.getId()}">
                                                                    <option selected="" class="select" value="${degree.getId()}">${degree.getName()}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option class="select" value="${degree.getId()}">${degree.getName()}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">NoCredit<span class="text-danger">*</span></label>
                                                    <input id="noCredit" name="noCredit"class="form-control" value="${noCredit}" required="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Decision<span class="text-danger">*</span></label>
                                                    <select name="selectDecision" id="selectDecision"  class="form-control time-during select2input" required="" >
                                                        <option value="">None</option>
                                                        <c:forEach items="${listDecision}" var="decision">
                                                            <c:choose>
                                                                <c:when test="${decisionR == decision.getDecision_id()}">
                                                                    <option selected="" class="select" value="${decision.getDecision_id()}">${decision.getDecision_no()}/${decision.getDecision_date()}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option class="select" value="${decision.getDecision_id()}">${decision.getDecision_no()}/${decision.getDecision_date()}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <c:if test="${sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                <div class="col-md-2">
                                                    <label class="form-label">IsApproved</label>
                                                    <div class="form-check form-switch">
                                                        <c:choose>
                                                            <c:when test="${isApproved == '1'}">
                                                                <input checked="" name='isApproved' class="form-check-input" type="checkbox" id="isActive" onclick="selectBt('isActiveText')">
                                                                <p id="isActiveText" class="badge bg-soft-warning" style="text-transform: capitalize">true</p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input name='isApproved' class="form-check-input" type="checkbox" id="isActive" onclick="selectBt('isActiveText')">
                                                                <p id="isActiveText" class="badge bg-soft-warning" style="text-transform: capitalize">false</p>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="mb-3">
                                                        <label class="form-label">Desginer<span class="text-danger"></span></label>
                                                        <select name="selectDesginer" id="selectDesginer"  class="form-control time-during select2input" >
                                                            <option value="">None</option>
                                                            <c:forEach items="${listUser}" var="user">
                                                                <c:choose>
                                                                    <c:when test="${desginer == user.getUserId()}">
                                                                        <option selected="" class="select" value="${user.getUserId()}">${user.getUserId()}-${user.getName()}-${user.getUsername()}-${user.getEmail()}-${user.getMobile()}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option class="select" value="${user.getUserId()}">${user.getUserId()}-${user.getName()}-${user.getUsername()}-${user.getEmail()}-${user.getMobile()}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </c:if>

                                        </div>
                                        <div style="color: red">${alert}</div>
                                        <button type="submit" name='btn' style="margin-top: 20px" class="btn btn-primary" >Submit</button>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--end container-->

                <!-- Footer Start -->
                <%@include file="../common/footerforsidebar.jsp" %>
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->
        <!-- Modal end -->

        <!-- javascript -->
        <script>
            function resetToast(){
                sessionStorage.removeItem("toastShow");
            };
            $(window).on('submit',function(){
                sessionStorage.removeItem("toastShow");
            });
        </script>
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
        <!-- Icons -->
        <script src="../../assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="../../assets/js/app.js"></script>

        <script src="../../JS/AddCurriculumSubject.js"></script>


    </body>
</html>
