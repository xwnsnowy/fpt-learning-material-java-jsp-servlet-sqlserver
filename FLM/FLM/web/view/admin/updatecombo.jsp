<%-- 
    Document   : adduser
    Created on : May 23, 2023, 10:55:44 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta charset="utf-8" />
        <title>Update Combo</title>
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

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item">Combo</li>
                                    <li class="breadcrumb-item active" aria-current="page">Update Combo</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-lg-8 mt-4">
                                <h3>Update Combo</h3>
                                <form action="combo" method="POST" class="mt-4">
                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="mb-3">  
                                                <label class="form-label" style="font-weight: bold">Group NAME</label>
                                                <input type="hidden" name="gId" value="${gId}" >
                                                <input name="groupname" pattern="^[a-zA-Z0-9_:.()ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂ
                                                       ẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏ
                                                       ốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]{1,255}$" 
                                                       title="Can only be letters, numbers, space, _ : . () and maximum 255 characters"
                                                       id="groupname" type="text" value="${g.name}${gname}" class="form-control" placeholder="Enter Group NAME.." required="">
                                            </div> 
                                        </div>
                                        <div class="col-md-5">
                                            <div class="mb-3">
                                                <label class="form-label" style="font-weight: bold">Curriculum CODE</label>
                                                <select name="curriculumid" id="curriculumid" class="form-control department-name select2input">
                                                    <c:if test="${curriculumIdNew != null}">
                                                        <option value="${curriculumIdNew}">${curriculumCodeNew}</option>
                                                        <c:forEach items="${listCurriculum}" var="list">
                                                            <option value="${list.getCurriculum_id()}">${list.getCode()}</option>
                                                        </c:forEach>
                                                    </c:if>  

                                                    <c:if test="${curriculumIdNew == null}">
                                                        <option value="${c.getCurriculum_id()}">${c.getCode()}</option>
                                                        <c:forEach items="${listCurriculum}" var="list">
                                                            <option value="${list.getCurriculum_id()}">${list.getCode()}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div><!--end col-->
                                        </div><!--end row--> 
                                        <div class="col-md-5">
                                            <div class="mb-3">
                                                <label class="form-label" style="font-weight: bold">Combo Subject</label>
                                                <div class="form-check form-switch" style="display: flex">
                                                    <c:if test="${combo_subject == 'TRUE'}">
                                                        <input name='isActive' style="width: 85px; height:36px" 
                                                               class="form-check-input" checked value="${combo_subject}" type="checkbox" id="a-1" onclick="selectBtForCombo('isActive')">
                                                    </c:if>
                                                    <c:if test="${combo_subject == 'FALSE'}">
                                                        <input name='isActive' style="width: 85px; height:36px" 
                                                               class="form-check-input" value="${combo_subject}" type="checkbox" id="a-1" onclick="selectBtForCombo('isActive')">
                                                    </c:if>
                                                    <p id="isActive" ${combo_subject} style="width: 85px; height:36px; margin-left:20px; font-size:  large; color: red !important;padding:8px; background-color: #b3effd !important;font-family: sans-serif" class="badge bg-soft-warning">${combo_subject}</p>
                                                </div>                                            
                                            </div> 
                                        </div>
                                        <button type="submit" name='btnUpdate' class="btn btn-primary col-md-8">Update Combo</button>
                                        <div style="display:flex">
                                            <c:if test="${alertError != null}">
                                                <img src="../../assets/images/errorchamthan.png" style="width: 55px;margin-top:10px" alt="!"/>
                                                <h4 style="color:red; margin-top:20px;margin-left: 10px">${alertError}</h4>
                                            </c:if>
                                            <h4 style="color:red; margin-top:20px;margin-left: 10px">${alert}</h4>
                                        </div>
                                    </div>
                                </form>
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
        <script src="../../JS/Combo.js"></script>
    </body>
</html>
