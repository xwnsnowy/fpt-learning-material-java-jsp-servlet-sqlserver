

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" >
        <title>New or Update Subject Content</title>
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
            input[name="subjectId"] {
                display: none;
            }

            input[name="subjectId"] + label:before {
                content: "";
                display: inline-block;
                vertical-align: middle;
                width: 35px;
                height: 35px;
                margin-right: 10px;
                border: 2px solid #007bff;
                border-radius: 5px;
                background-color: #fff;
            }

            input[name="subjectId"]:checked + label:before {
                content: "\2713";
                text-align: center;
                color: #fff;
                background-color: #007bff;
            }

            label[for="subject_${list.getId()}"] {
                font-size: 16px;
            }
        </style>
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
                        <div >
                            <div class="row" >

                                <div class="col-12" style="max-width:96%;margin: auto">
                                    <div style="display:flex"><h3 >Add New Or Update Subject Content</h3>
                                    </div>
                                </div>


                                <div class="row justify-content-start align-items-center" style="max-width:98%;margin: auto;">
                                    <div class="row justify-content-start align-items-center">
                                        <div class="mb-0 position-relative">
                                            <table style="border-collapse: collapse; border-spacing: 0;">
                                                <tbody>
                                                    <c:if test="${curriculumid != null}"> 
                                                        <tr>
                                                            <td style="padding: 10px; border: 1px solid #ccc;"><label class="form-label" style="font-weight: bold;">Curriculum ID</label></td>                                                   
                                                            <td style="padding: 10px; border: 1px solid #ccc;"><input type="text" readonly value="${curriculumid}" style="background-color: #decbcb; border: none; padding: 5px;"></td>     
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${contentgroupid != null}"> 
                                                        <tr>
                                                            <td style="padding: 10px; border: 1px solid #ccc;"><label class="form-label" style="font-weight: bold;">Content Group ID</label></td> 
                                                            <td style="padding: 10px; border: 1px solid #ccc;"><input type="text" readonly value="${contentgroupid}" style="background-color: #decbcb; border: none; padding: 5px;"></td>      
                                                        </tr>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <form id="filter" action="contentgroup?action=newsubjectcontent&contentgroupid=${contentgroupid}&curriculumid=${curriculumid}" method="POST">
                                        <div class="row justify-content-start align-items-center" style="margin-top:20px">
                                            <input type="hidden" name="start" id="start">
                                            <input type="hidden" name="end" id="end">
                                            <input type="hidden" name="thisPage" id="thisPage" value="${thisPage}">
                                            <div  class="col-sm-auto col-md-auto col-lg-auto" >
                                                <div class="mb-0 position-relative">
                                                    <select name="selectFilter"  class="form-control time-during select2input" onchange="submitForm()">
                                                        <option <c:if test="${selectFilter == 'code'}"> selected="" </c:if> value="code">Subject Code</option>
                                                        <option <c:if test="${selectFilter == 'name'}"> selected="" </c:if> value="name">Subject Name</option>
                                                        <option <c:if test="${selectFilter == 'active'}"> selected="" </c:if> value="active">Active</option>                                                       
                                                        <option <c:if test="${selectFilter == 'deactive'}"> selected="" </c:if> value="deactive">Deactive</option>
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
                                        </div>
                                    </form>
                                </div>
                                <!-- Table -->
                                <c:if test="${listSubject!= null}">

                                    <form action="contentgroup?action=add&contentgroupid=${contentgroupid}&curriculumid=${curriculumid}" method="POST">
                                        <div class="col-12" style="max-width:96%; margin: auto;margin-top:20px">
                                            <div class="table-responsive bg-white shadow rounded">
                                                <table class="table table-center table-padding mb-0 table2">
                                                    <col style="width:8%">
                                                    <col style="width:9%">
                                                    <col style="width:9%">
                                                    <col style="width:57%">
                                                    <col style="width:5%">
                                                    <thead>
                                                        <tr>
                                                            <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Subject ID</th>
                                                            <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Curriculum ID</th>
                                                            <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Subject Code</th>
                                                            <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff">Subject Name</th>
                                                                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                                                                <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff; text-align: center;">Status</th>
                                                                <th class="border-bottom p-3" style="background-color:#396cf0; color: #ffffff"></th>
                                                                </c:if>
                                                        </tr>
                                                    </thead>
                                                    <tbody class="listSubject">
                                                        <c:set var="countSubject" value="0"></c:set>
                                                        <c:forEach items="${listSubject}" var="list">
                                                            <c:set var="countSubject" value="${countSubject + 1}"></c:set>
                                                                <tr class="itemSubjects">
                                                                    <td class="p-3" style="text-align: center;"><b>${list.getId()}</b></td>
                                                                <td class="p-3" style="text-align: center;"><b>${list.getCurriculumId()}</b></td>
                                                                <td class="p-3" style="text-align: center;"><b>${list.getCode()}</b></td>
                                                                <td class="p-3" >${list.getName()}</td>
                                                                <c:if test="${list.getIsActive() == 'TRUE'}" >
                                                                    <td id="active" class="p-3"><span class="badge bg-soft-warning" 
                                                                                                      style="color:red !important;
                                                                                                      background-color: #ddd !important;
                                                                                                      ">${list.getIsActive()}</span></td>
                                                                    </c:if>
                                                                    <c:if test="${list.getIsActive() == 'FALSE'}" >
                                                                    <td id="active" class="p-3"><span class="badge bg-soft-warning" 
                                                                                                      style="color:blue !important;
                                                                                                      background-color: #ddd !important;
                                                                                                      ">${list.getIsActive()}</span></td>
                                                                    </c:if>
                                                                <td class="p-3" > 
                                                                    <input type="checkbox" name="subjectId" value="${list.getId()}" id="subjectId_${list.getId()}">
                                                                    <label for="subjectId_${list.getId()}"><b>${list.getCode()}</b></label>
                                                                </td>    
                                                            </tr>
                                                        </c:forEach> 
                                                    </tbody>
                                                </table>
                                            </div>         
                                            <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN' || sessionScope.acc.getRoleName() == 'CRDD HEAD' || sessionScope.acc.getRoleName() == 'CRDD STAFF'}">
                                                <div class="col-sm-auto col-md-auto col-lg-auto ml-auto" style="width:20%; margin: auto;margin-top:20px">
                                                    <div class="d-grid">
                                                        <button type="submit" name='btnAddListSubject' class="btn btn-primary">Add</button>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                        <div  hidden=""id = "numberOfSubject">${numberOfSubject}</div>
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
        <!--Combo list FULL-->
        <script src="../../JS/SubjectContent.js"></script>
        <script>
                                                                function submitForm() {
                                                                    document.getElementById("filter").submit();
                                                                }
        </script>
    </body>
</html>
