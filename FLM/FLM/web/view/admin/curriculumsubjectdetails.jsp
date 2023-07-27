<%-- 
    Document   : curriculumsubjectdetails
    Created on : Jul 21, 2023, 1:12:18 AM
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
        <style>
            .table1, th, td {
                border: 1px solid #dee2e6;
                vertical-align: top;
                text-align: left;
                padding: 8px;
                border-collapse: collapse;
            }
        </Style>
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
                            <h5 class="mb-0">Edit Curiculum Subject</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="/FLM_NEW/view/common/home">ADMIN</a></li>
                                    <li class="breadcrumb-item">CURRICULUM</li>
                                    <li class="breadcrumb-item">Subject</li>
                                    <li class="breadcrumb-item active" aria-current="page">Edit Curiculum Subject</li>
                                </ul>
                            </nav>
                        </div>
                        <div class="col-12" style="max-width:95%; margin-bottom: 15px;">
                            <table class="table1">
                                <tr>
                                    <td>Curriculum ID </td>
                                    <th>${c.getCurriculum_id()}</th>
                                </tr>
                                <tr>
                                    <td>Curriculum Code</td>
                                    <th>${c.getCode()}</th>
                                </tr>
                                <tr>
                                    <td>Curriculum Name</td>
                                    <th>${c.getName()}</th>
                                </tr>
                            </table>
                        </div>
                        <div class="row">
                            <div class="card border-0 p-5 rounded shadow" style="width: fit-content">
                                <div class="col-lg-12 ">
                                    <!--end row-->

                                    <form id="formCS" action="curriculumsubject?action=edit&cId=${c.getCurriculum_id()}&cSId=${cSId}" method="post" onsubmit ="resetToast()">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="mb-3">
                                                    <h4 class="form-label">Curriculum Subject:<span class="text-danger"></span></h4>
                                                    <h5 class="ms-4 text-success">${cs.getSubjectCode()} - ${cs.getSubjectName()}</h5>
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label class="form-label">Semester<span class="text-danger">*</span></label>
                                                    <input id="semester" name="semester"class="form-control" value="${cs.getCurriculumSubjectSemester()}" required="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label class="form-label">NoCredit<span class="text-danger">*</span></label>
                                                    <input id="noCredit" name="noCredit"class="form-control" value="${cs.getCurriculumSubjectNoCredit()}" required="">
                                                </div>
                                            </div>



                                            <div class="col-md-6">
                                                <label class="form-label">Curriculum Subject Active</label>
                                                <div class="form-check form-switch">
                                                    <c:choose>
                                                        <c:when test="${cs.getCurriculumSubjectIsActive() == '1'}">
                                                            <input name='isActive' checked="" class="form-check-input" type="checkbox" id="isActive" onclick="selectBt('isActiveText')">
                                                            <p id="isActiveText" class="badge bg-soft-warning" style="text-transform: capitalize">true</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input name='isActive' class="form-check-input" type="checkbox" id="isActive" onclick="selectBt('isActiveText')">
                                                            <p id="isActiveText" class="badge bg-soft-warning" style="text-transform: capitalize">flase</p>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                            </div>
                                            <c:choose>
                                                <c:when test="${isCombo == 'true'}">

                                                    <input type="hidden" name="isCombo" value="true">
                                                    <div class="col-md-12">
                                                        <div class="mb-3">
                                                            <h4 class="form-label">Subject:<span class="text-danger"></span></h4>
                                                        </div>
                                                    </div><!--end col-->

                                                    <c:forEach items="${listSOCS}" var="subjectS">
                                                        <div class="col-md-12">
                                                            <div class="mb-3">
                                                                <h5 class="ms-4 text-warning">${subjectS.subject.getCode()} - ${subjectS.subject.getName()}</h5>
                                                                <input type="hidden" name="listS" value="${subjectS.subject.getId()}">
                                                            </div>
                                                        </div><!--end col-->
                                                        <div class="col-md-6">
                                                            <div class="mb-3">
                                                                <label class="form-label" >Syllabus Name<span class="text-danger"></span></label>
                                                                <select name="selectSyllabus" id=""  class="form-control time-during select2input" >
                                                                    <option value="">None</option>
                                                                    <c:forEach items="${listSl}" var="syllabus">
                                                                        <c:if test="${syllabus.subject.getId() == subjectS.subject.getId()}">
                                                                            <c:choose>
                                                                                <c:when test="${subjectS.getId() == syllabus.getId()}">
                                                                                    <option class="select" selected="" value="${syllabus.getId()}">${syllabus.getName()}</option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option class="select" value="${syllabus.getId()}">${syllabus.getName()}</option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:if>
                                                                    </c:forEach>

                                                                </select>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-6">
                                                            <div class="mb-3">
                                                                <label class="form-label" >Content Group<span class="text-danger"></span></label>
                                                                <select name="content" id=""  class="form-control time-during select2input">
                                                                    <option value="">None</option>
                                                                    <c:forEach items="${listContent}" var="content">
                                                                        <c:choose>
                                                                            <c:when test="${subjectS.subject.getCurriculumId() == content.getId()}">
                                                                                <option class="select" selected="" value="${content.getId()}">${content.getName()}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option class="select" value="${content.getId()}">${content.getName()}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>

                                                                    </c:forEach>

                                                                </select>
                                                            </div>
                                                        </div>

                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="isCombo" value="false">
                                                    <c:forEach items="${listSOCS}" var="subjectS">
                                                        <div class="col-md-6">
                                                            <div class="mb-3">
                                                                <label class="form-label" >Syllabus Name<span class="text-danger"></span></label>
                                                                <select name="selectSyllabus" id=""  class="form-control time-during select2input" >
                                                                    <option value="">None</option>
                                                                    <c:forEach items="${listSl}" var="syllabus">
                                                                        <c:if test="${syllabus.subject.getId() == subjectS.subject.getId()}">
                                                                            <c:choose>
                                                                                <c:when test="${subjectS.getId() == syllabus.getId()}">
                                                                                    <option class="select" selected="" value="${syllabus.getId()}">${syllabus.getName()}</option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option class="select" value="${syllabus.getId()}">${syllabus.getName()}</option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:if>
                                                                    </c:forEach>

                                                                </select>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-6">
                                                            <div class="mb-3">
                                                                <label class="form-label" >Content Group<span class="text-danger"></span></label>
                                                                <select name="content" id=""  class="form-control time-during select2input">
                                                                    <option value="">None</option>
                                                                    <c:forEach items="${listContent}" var="content">
                                                                        <c:choose>
                                                                            <c:when test="${subjectS.subject.getCurriculumId() == content.getId()}">
                                                                                <option class="select" selected="" value="${content.getId()}">${content.getName()}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option class="select" value="${content.getId()}">${content.getName()}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>

                                                                    </c:forEach>

                                                                </select>
                                                            </div>
                                                        </div>

                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>



                                        </div>
                                        <div id="alert" style="color: red">${alert}</div>
                                        <button type="button" name='btn' style="margin-top: 20px" class="btn btn-primary" onclick="checkSubmit()">Save</button>

                                        <input type="hidden" name="Ccode" value="${c.code}">
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
