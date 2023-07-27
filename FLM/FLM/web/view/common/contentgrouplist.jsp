

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
            .custom-select {
                width: 100%;
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
                                    <div style="display:flex"><h2 >Content Group Management</h2>
                                    </div>
                                </div>
                                <div class="col-12" style="max-width:95%; margin: auto;">
                                    <div >
                                        <table class="table1" style="margin-bottom: 20px">
                                            <tr>
                                                <td>CurriCulum ID </td>
                                                <td><b>${c.curriculum_id}<b></td>
                                                            </tr>
                                                            <tr>
                                                                <td>CurriCulum Code</td>
                                                                <td><b>${c.code}</b></td>
                                                            </tr>
                                                            <tr>
                                                                <td>CurriCulum Name</td>
                                                                <td><b>${c.name}</b> </td>
                                                            </tr>
                                                            </table>
                                                            </div>                                     
                                                            </div>

                                                            <form id="filter" action="contentgroup?action=list&curriculumid=${c.curriculum_id}" method="POST">
                                                                <div class="row justify-content-start align-items-center" style="max-width:95%;margin: auto">
                                                                    <input type="hidden" name="start" id="start">
                                                                    <input type="hidden" name="end" id="end">
                                                                    <input type="hidden" name="thisPage" id="thisPage" value="${thisPage}">
                                                                    <div  class="col-sm-auto col-md-auto col-lg-auto" >
                                                                        <div class="mb-0 position-relative">
                                                                            <select name="selectFilter"  class="form-control time-during select2input" onchange="submitForm()">

                                                                                <option <c:if test="${selectFilter == 'name'}"> selected="" </c:if> value="name">Combo Name</option>
                                                                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                                                                                    <option <c:if test="${selectFilter == 'active'}"> selected="" </c:if> value="active" >Active</option>                                                       
                                                                                    <option <c:if test="${selectFilter == 'deactive'}"> selected="" </c:if> value="deactive" >Deactive</option>
                                                                                </c:if>
                                                                            </select>                                            
                                                                        </div>
                                                                    </div>        

                                                                    <div  class="col-sm-auto col-md-3" >
                                                                        <div class="d-grid">
                                                                            <input style="line-height: 33px" type="text" name="search" placeholder="Search" value="${search}">
                                                                        </div>
                                                                    </div>
                                                                    <div  class="col-sm-auto col-md-auto col-lg-auto" >
                                                                        <div class="d-grid">
                                                                            <button type="submit" name='btnSearch'  class="btn btn-primary" >Search</button>
                                                                        </div>
                                                                    </div>  
                                                                    <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >

                                                                        <div  class="col-sm-auto col-md-auto col-lg-auto" >
                                                                            <div class="d-grid">
                                                                                <a href="/FLM_NEW/view/admin/contentgroup?action=newcontentgroup&curriculumid=${c.curriculum_id}" class="btn btn-success " >
                                                                                    <i class="ri-add-circle-line align-middle me-2"></i>Add New Content Group</a>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>

                                                                    <div class="col-12" style="max-width:95%;margin: auto">
                                                                        <div style="display:flex">                                                        
                                                                            <h4 style="color:red; margin-top:10px;">${alert}</h4>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                            <!-- Table -->
                                                            <c:if test="${listContents != null}">
                                                                <div class="col-12" style="max-width:95%; margin: auto;">
                                                                    <div class="table-responsive bg-white shadow rounded">
                                                                        <table class="table table-center table-padding mb-0 table2">
                                                                            <col style="width:12%">
                                                                            <col style="width:80%">
                                                                            <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                                                                                <col style="width:5%">
                                                                            </c:if>
                                                                            <thead>
                                                                                <tr>
                                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Content Group ID</th>
                                                                                    <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Content Group Name</th>
                                                                                        <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                                                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Status</th>
                                                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Update</th>
                                                                                        <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Delete</th>
                                                                                        </c:if>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody class="listContents">
                                                                                <c:set var="countContents" value="0"></c:set>
                                                                                <c:forEach items="${listContents}" var="list">
                                                                                    <c:set var="countContents" value="${countContents + 1}"></c:set>
                                                                                        <tr class="itemContents">
                                                                                            <td class="p-3" style="text-align: center;"><b>${list.getId()}</b></td>
                                                                                        <td class="p-3" ><a href="/FLM_NEW/view/common/contentgroup?action=detail&contentgroupid=${list.getId()}&curriculumid=${list.getCurriculum_id()}">${list.name}</a></td>
                                                                                            <c:choose>
                                                                                                <c:when test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD'|| sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                                                                                                    <c:if test="${list.getGc_is_active() == 'TRUE'}" >
                                                                                                    <td id="active" class="p-3"><span class="badge bg-soft-warning" 
                                                                                                                                      style="color:red !important;
                                                                                                                                      background-color: #ddd !important;
                                                                                                                                      ">${list.getGc_is_active()}</span></td>
                                                                                                    </c:if>
                                                                                                    <c:if test="${list.getGc_is_active() == 'FALSE'}" >
                                                                                                    <td id="active" class="p-3"><span class="badge bg-soft-warning" 
                                                                                                                                      style="color:blue !important;
                                                                                                                                      background-color: #ddd !important;
                                                                                                                                      ">${list.getGc_is_active()}</span></td>
                                                                                                    </c:if>

                                                                                                <td class="text-end p-3">
                                                                                                    <div style="display:flex; justify-content: center">
                                                                                                        <div>
                                                                                                            <a href="/FLM_NEW/view/admin/contentgroup?action=updatecontentgroup&contentgroupid=${list.getId()}&curriculumid=${list.getCurriculum_id()}"
                                                                                                               class="btn btn-icon btn-pills btn-soft-success"><i class="uil uil-pen">
                                                                                                                </i>
                                                                                                            </a>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td id="inactive" class="p-3">
                                                                                                    <span class="form-switch">
                                                                                                        <form id="change-${list.getId()}" action="contentgroup?action=changestatus&contentgroupid=${list.getId()}&curriculumid=${list.getCurriculum_id()}" method="POST">
                                                                                                            <span class="form-switch">
                                                                                                                <input id="checkbox-${list.getId()}" style="height: 20px; width: 40px" onclick="changeStatus('${list.getId()}', this)" 
                                                                                                                       class="form-check-input checkbox-row" type="checkbox" 
                                                                                                                       <c:if test="${list.getGc_is_active() == 'TRUE'}">checked</c:if> />
                                                                                                                <p id="isActive-${list.getId()}" class="badge bg-soft-warning"></p>
                                                                                                            </span>
                                                                                                            <input type="hidden" name="hiddenIsActive" id="hiddenIsActive-${list.getId()}" value="${list.getGc_is_active()}"> 
                                                                                                        </form>
                                                                                                    </span>
                                                                                                </td>
                                                                                            </c:when>
                                                                                        </c:choose>
                                                                                    </c:forEach> 
                                                                            </tbody>
                                                                        </table>
                                                                    </div>                                     
                                                                </div>
                                                            </c:if>
                                                            </div>
                                                            </div>
                                                            <div  hidden=""id = "numberOfContent">${numberOfContent}</div>
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
                                                            </td>


                                                            <script>
                                                                function changeStatus(id, checkbox) {
                                                                    var form = checkbox.closest('form');
                                                                    var hiddenIsActive = checkbox.checked ? 'INACTIVE' : 'ACTIVE'; // Sửa giá trị của hiddenIsActive khi checkbox được checked
                                                                    var actionUrl = form.action.replace(/contentgroupid=[0-9]+/, "contentgroupid=" + id);
                                                                    form.action = actionUrl;
                                                                    document.getElementById("hiddenIsActive-" + id).setAttribute("value", hiddenIsActive); // Truyền tên của input hidden vào và set giá trị cho hiddenIsActive
                                                                    form.submit();
                                                                }
                                                            </script>

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
                                                            <script>
                                                                function submitForm() {
                                                                    document.getElementById("filter").submit();
                                                                }
                                                            </script>     

                                                            </body>
                                                            </html>
