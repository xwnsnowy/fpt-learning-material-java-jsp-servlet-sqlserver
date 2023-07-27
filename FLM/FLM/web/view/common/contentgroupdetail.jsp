

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" >
        <title>Content Group Management</title>
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
        <!--curriculum subject css-->
        <link rel="stylesheet" href="../../Style/CurriculumSubject.css">
        <link rel="stylesheet" href="../../assets/css/toast.style.css"/>
        <!--toast-js-->
        <script src="../../assets/js/jquery.min.js"></script>
        <script src="../../assets/js/toast.script.js"></script>
        <style>
            .cell-breakWord {
                word-wrap: break-word;
                max-width: 1px;
                -webkit-hyphens: auto; /* iOS 4.2+ */
                -moz-hyphens: auto; /* Firefox 5+ */
                -ms-hyphens: auto; /* IE 10+ */
                hyphens: auto;
            }
            .table1, th, td {
                border: 1px solid #dee2e6;
                vertical-align: top;
                text-align: left;
                padding: 8px;
                border-collapse: collapse;
            }

            .border-bottom p-3 {
                /*            border: 1px solid #dee2e6;*/
                vertical-align: top;
                text-align: left;
            }
            .p-3 {
                /*            border: px solid rgba(0,0,0,.05);*/
                vertical-align: top;
                text-align: left;
            }
            .mb-0 tr:nth-child(odd) {
                background-color: #f2f2f2;
            }
            .mb-0 tr:hover {
                background-color: #ddd;
                ;
            }
            .pagination{
                display: inline-block;
            }

        </style>

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
                        rtl: false,
                    });
                }
                ;
                // type: success, warning, error, info or notice
                //            addToast(title, content, type, count)
                window.onload = addToast("Success", "Successfully!", "success", 5000);
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
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div >
                            <div class="row" >

                                <div class="col-12" style="max-width:95%;margin: auto">
                                    <div style="display:flex"><h3 >View Content Group Detail</h3>
                                    </div>
                                </div>
                                <div class="col-12" style="max-width:95%; margin: auto;">
                                    <div >
                                        <table class="table1" style="margin-bottom: 20px">
                                            <tr>
                                                <td>Content Group Name	</td>
                                                <td><b>${g.getName()}<b></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Note</td>
                                                                <td><b></b></td>
                                                            </tr>

                                                            </table>
                                                            </div>                                     
                                                            </div>

                                                            <form id="filter" action="contentgroup?action=detail&contentgroupid=${contentgroupid}&curriculumid=${curriculumid}" method="POST">
                                                                <div class="row justify-content-start align-items-center" style="max-width:95%;margin: auto">
                                                                    <input type="hidden" name="start" id="start">
                                                                    <input type="hidden" name="end" id="end">
                                                                    <input type="hidden" name="thisPage" id="thisPage" value="${thisPage}">       


                                                                    <div class="col-12" style="max-width:95%;margin: auto">
                                                                        <div style="display:flex">                                                        
                                                                            <h4 style="color:red; margin-top:10px;">${alert}</h4>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                            <div class="row justify-content-start align-items-center" style="max-width:97%;margin: auto">
                                                                <div  class="col-sm-auto col-md-auto col-lg-auto" >
                                                                    <div class="mb-0 position-relative">
                                                                        <h3 >List Subject Content</h3>
                                                                    </div>
                                                                </div>
                                                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                                                                    <div  class="col-sm-auto col-md-auto col-lg-auto" >
                                                                        <div class="d-grid">
                                                                            <a href="/FLM_NEW/view/admin/contentgroup?action=newsubjectcontent&contentgroupid=${contentgroupid}&curriculumid=${curriculumid}" class="btn btn-success" >Add New Subject Or Update</a>
                                                                        </div>
                                                                    </div>
                                                                </c:if> 
                                                            </div>
                                                            <!-- Table -->
                                                                <c:if test="${listSubjectContent != null}">
                                                                <div class="col-12" style="max-width:95%; margin: auto;">
                                                                    <div class="table-responsive bg-white shadow rounded">
                                                                        <table class="table table-center table-padding mb-0 table2">
                                                                            <col style="width:8%">
                                                                            <col style="width:10%">
                                                                            <col style="width:50%">
                                                                            <col style="width:5%">
                                                                            <col style="width:18%">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Subject ID</th>
                                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Subject Code</th>
                                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Subject Name</th>
                                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Semester</th>
                                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Note</th>
                                                                                        <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                                                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Status</th>
                                                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff"></th>
                                                                                        </c:if>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody class="listSubjectContent">
                                                                                <c:set var="countSubjectContents" value="0"></c:set>
                                                                                <c:forEach items="${listSubjectContent}" var="list">
                                                                                    <c:set var="countSubjectContents" value="${countSubjectContents + 1}"></c:set>
                                                                                        <tr class="itemSubjectContents">
                                                                                            <td class="p-3" style="text-align: center;"><b>${list.getSubjectId()}</b></td>
                                                                                        <td class="p-3" style="text-align: center;"><b>${list.getSubjectCode()}</b></td>
                                                                                        <td class="p-3" >${list.getSubjectName()}</td>
                                                                                        <td class="p-3" style="text-align: center;">${list.getCurriculumSubjectSemester()}</td>
                                                                                        <td class="p-3" ">${list.getSubjectDescription()}</td>
                                                                                        <c:choose>
                                                                                            <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                                                                <c:if test="${list.getCurriculumSubjectIsActive() == 'TRUE'}" >
                                                                                                    <td id="active" class="p-3"><span class="badge bg-soft-warning" 
                                                                                                                                      style="color:red !important;
                                                                                                                                      background-color: #ddd !important;
                                                                                                                                      ">${list.getCurriculumSubjectIsActive()}</span></td>
                                                                                                    </c:if>
                                                                                                    <c:if test="${list.getCurriculumSubjectIsActive() == 'FALSE'}" >
                                                                                                    <td id="active" class="p-3"><span class="badge bg-soft-warning" 
                                                                                                                                      style="color:blue !important;
                                                                                                                                      background-color: #ddd !important;
                                                                                                                                      ">${list.getCurriculumSubjectIsActive()}</span></td>
                                                                                                    </c:if>
                                                                                                <td class="text-end p-3">
                                                                                                    <form id="delete" action="contentgroup?action=deletesubjectcontent&contentgroupid=${contentgroupid}&curriculumid=${curriculumid}" method="POST">
                                                                                                        <input type="hidden" name="subjectcontentid" id="subjectcontentid">
                                                                                                        <div class="btn btn-icon btn-pills btn-soft-success" onclick="deleteSubjectContent('${list.getSubjectId()}');"><i class="uil uil-trash"></i></div>
                                                                                                    </form>

                                                                                                </td>
                                                                                            </c:when>
                                                                                        </c:choose>
                                                                                    </tr>
                                                                                </c:forEach> 
                                                                            </tbody>
                                                                        </table>
                                                                    </div>                                     
                                                                </div>
                                                            </c:if>
                                                            </div>
                                                            </div>
                                                            <div  hidden=""id = "numberOfSubjectContent">${numberOfSubjectContent}</div>
                                                            <div class="row text-center" style="max-width:97%;margin: auto">
                                                                <!-- PAGINATION START -->
                                                                <div class="col-12 mt-4">
                                                                    <div class="d-md-flex align-items-center text-center justify-content-between">
                                                                        <span id="numberAcc" class="text-muted me-3"></span>
                                                                        <ul class="listPage pagination justify-content-center mb-0 mt-3 mt-sm-0" style="display:flex">
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
                                                            <script src="../../JS/ContentGroupDetail.js"></script>
                                                            <script src="../../assets/datatables/jquery.dataTables.min.js"></script>
                                                            <script>
                                                                                                            function deleteSubjectContent(id) {
                                                                                                                if (confirm('Bạn có chắc chắn muốn xóa nội dung với id ' + id + ' không ?')) {
                                                                                                                    document.getElementById("subjectcontentid").value = id;
                                                                                                                    document.getElementById("delete").submit();
                                                                                                                }
                                                                                                            }
                                                            </script>
                                                            
                                                            <script>
                                                                $(function () {
                                                                    $('#table').DataTable({
                                                                        "paging": false,
                                                                        "lengthChange": false,
                                                                        "searching": false,
                                                                        "ordering": true,
                                                                        "info": false,
                                                                        "autoWidth": true
                                                                    });
                                                                });
                                                            </script>
                                                            </body>
                                                            </html>
