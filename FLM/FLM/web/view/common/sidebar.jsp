
<%-- 
    Document   : sidebar
    Created on : May 26, 2023, 4:21:10 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>

        <!--<div class="page-wrapper doctris-theme toggled">-->
        <nav id="sidebar" class="sidebar-wrapper">
            <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
                <div class="sidebar-brand">
                    <a href="/FLM_NEW/view/common/home">
                        <!--<a href="index.html">-->
                        <img src="../../assets/images/flm-dark.png" height="150" class="logo-light-mode" alt="">
                        <img src="../../assets/images/flm-dark.png" height="150" class="logo-dark-mode" alt="">
                    </a>
                </div>
                <c:if test="${sessionScope.acc.getRoleName() == null}">
                    <ul class="sidebar-menu pt-3">
                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/curriculum/listcurriculum" >
                                <i >
                                    <img src="../../assets/images/iconsb/curriculum.png" alt="curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Curriculum
                            </a>                
                            <c:if test="${op == 4}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/curriculum/overviewcurriculum?Ccode=${c.getCode()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.getCode()}">POs</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/plo?Ccode=${c.getCode()}">PLOs</a></li> 
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingPLO_PO?Ccode=${c.getCode()}">PLO-POs</a></li>
                                        <li><a href="/FLM_NEW/view/common/curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=">Subjects</a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingplosubject?Ccode=${c.code}">PLO_Subject</a></li>
                                        <li><a href="/FLM_NEW/view/common/combo?action=combolist&curriculumid=${c.curriculum_id}">Combo</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.getCode()}">Elective</a></li>
                                        <li><a href="/FLM_NEW/view/common/contentgroup?action=list&curriculumid=${c.curriculum_id}">Content Group</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=contentgrouplist&contentgroupid=${contentgroupid}&curriculumid=${c.curriculum_id}"></a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/combolistfull?action=list">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Combo" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Combo</a>     

                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/subjectpredecessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Predecessors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Predecessor</a>     

                        </li>
                        <li>  
                            <a href="/FLM_NEW/view/common/subjectsuccessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Successors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Successor</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.acc.getRoleName() == 'ADMIN'}" >
                    <ul class="sidebar-menu pt-3">


                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/curriculum/listcurriculum" >
                                <i >
                                    <img src="../../assets/images/iconsb/curriculum.png" alt="curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Curriculum
                            </a>                
                            <c:if test="${op == 4}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/curriculum/overviewcurriculum?Ccode=${c.getCode()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.getCode()}">POs</a></li>
                                            <c:if test="${oppo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}&amp;po_id=${p.po_id}">POs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}"></a></li>
                                        <!--<li><a href="/FLM_NEW/view/admin/addPLOs?Ccode=${c.getCode()}">Add New PLO</a></li>-->
                                        <li><a href="/FLM_NEW/view/common/plo?Ccode=${c.getCode()}">PLOs</a></li> 
                                            <c:if test="${opplo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}&amp;plo_id=${p.plo_id}">PLOs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingPLO_PO?Ccode=${c.getCode()}">PLO-POs</a></li>
                                        <li><a href="/FLM_NEW/view/common/curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=">Subjects</a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingplosubject?Ccode=${c.code}">PLO_Subject</a></li>
                                        
                                        <li><a href="/FLM_NEW/view/common/combo?action=combolist&curriculumid=${c.curriculum_id}">Combo</a></li>
                                        <li hidden=""><a href="/FLM_NEW/view/common/combo?action=updatecombo&comboid=${comboid}&curriculumid=${curriculumid}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.getCode()}">Elective</a></li>
                                        <li><a href="/FLM_NEW/view/common/contentgroup?action=list&curriculumid=${c.curriculum_id}">Content Group</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/contentgroup?action=contentgrouplist&contentgroupid=${contentgroupid}&curriculumid=${c.curriculum_id}"></a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/common/syllabus?action=list" >
                                <i><img src="../../assets/images/iconsb/syllabus.png" alt="syllabus" 
                                        style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus</a> 
                                <c:if test="${dropDownS == 1}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/common/syllabus?action=overview&sylId=${syllabus.getId()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=list&sylId=${syllabus.getId()}">CLOs</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=mapping&sylId=${syllabus.getId()}">CLOs_PLOs</a></li>
                                        <li><a href="/FLM_NEW/view/question/question?action=1">Question</a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/combolistfull?action=list">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Combo" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Combo</a>     

                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/subjectpredecessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Predecessors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Predecessor</a>     

                        </li>
                        <li>  
                            <a href="/FLM_NEW/view/common/subjectsuccessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Successors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Successor</a>
                        </li>
                    </ul>
                    <ul class="sidebar-menu pt-3">
                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/systemsetting.png" alt="System Settings" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>System Settings</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/admin/setting?action=list">Setting List</a></li>
                                    <li><a href="/FLM_NEW/view/admin/settingdetail?&mod=1">Add New Setting</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/usermanagement.png" alt="User Management" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>User Management</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/admin/account">Account List</a></li>
                                    <li><a href="/FLM_NEW/view/admin/adduser">Add New User</a></li>
                                </ul>
                            </div>
                        </li>



                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/trainningcurriculum.png" alt="Training Curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Training Curriculum</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/curriculum/listcurriculum">Curriculum List</a></li>
                                    <li><a href="/FLM_NEW/view/curriculum/newcurriculum">New Curriculum</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/subjectmanagement.png" alt="Subject Management" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Management</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/admin/subject?action=list">Subject List</a></li>
                                    <li><a href="/FLM_NEW/view/admin/subject?action=add">Add New Subject</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown" >
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/decision.png" alt="Material Decisions" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Material Decisions</a>     
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/decision/dicisioncontroller">Decision List</a></li>
                                </ul>
                            </div>
                        </li>
                        <!-- comment -->
                    </ul>




                </c:if>

                <c:if test="${sessionScope.acc.getRoleName() == null}">
                    <!--                    <ul class="sidebar-menu pt-3">
                    
                    
                                            <li class="sidebar-dropdown" >
                                                <a href="javascript:void(0)">
                                                    <i >
                                                        <img src="../../assets/images/iconsb/design.png" alt="Syllabus Designing" 
                                                             style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                                    </i>Syllabus Designing</a>     
                                                <div class="sidebar-submenu">
                                                    <ul>
                                                        <li><a href="#">Syllabus List</a></li>
                                                        <li><a href="#">Syllabus Design</a></li>
                                                    </ul>
                                                </div>
                                            </li>
                                        </ul>-->
                </c:if>
                <c:if test="${sessionScope.acc.getRoleName() == 'CRDD HEAD'}" >
                    <ul class="sidebar-menu pt-3">


                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/curriculum/listcurriculum" >
                                <i >
                                    <img src="../../assets/images/iconsb/curriculum.png" alt="curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Curriculum
                            </a>                
                            <c:if test="${op == 4}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/curriculum/overviewcurriculum?Ccode=${c.getCode()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.getCode()}">POs</a></li>
                                            <c:if test="${oppo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}&amp;po_id=${p.po_id}">POs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}"></a></li>
                                        <!--<li><a href="/FLM_NEW/view/admin/addPLOs?Ccode=${c.getCode()}">Add New PLO</a></li>-->
                                        <li><a href="/FLM_NEW/view/common/plo?Ccode=${c.getCode()}">PLOs</a></li> 
                                            <c:if test="${opplo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}&amp;plo_id=${p.plo_id}">PLOs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingPLO_PO?Ccode=${c.getCode()}">PLO-POs</a></li>
                                        <li><a href="/FLM_NEW/view/common/curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=">Subjects</a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingplosubject?Ccode=${c.code}">PLO_Subject</a></li>
                                        
                                        <li><a href="/FLM_NEW/view/common/combo?action=combolist&curriculumid=${c.curriculum_id}">Combo</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.getCode()}">Elective</a></li>
                                        <li><a href="/FLM_NEW/view/common/contentgroup?action=list&curriculumid=${c.curriculum_id}">Content Group</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/contentgroup?action=details&contentgroupid=${contentgroupid}&curriculumid=${c.curriculum_id}"></a></li>

                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/common/syllabus?action=list" >
                                <i><img src="../../assets/images/iconsb/syllabus.png" alt="syllabus" 
                                        style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus</a> 
                                <c:if test="${dropDownS == 1}">
                                <div class="sidebar-submenu">
                                    <ul>

                                        <li><a href="/FLM_NEW/view/common/syllabus?action=overview&sylId=${syllabus.getId()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=list&sylId=${syllabus.getId()}">CLOs</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=mapping&sylId=${syllabus.getId()}">CLOs_PLOs</a></li>
                                        <li><a href="/FLM_NEW/view/question/question?action=1">Question</a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/combolistfull?action=list">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Combo" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Combo</a>     

                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/subjectpredecessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Predecessors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Predecessor</a>     

                        </li>
                        <li>  
                            <a href="/FLM_NEW/view/common/subjectsuccessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Successors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Successor</a>
                        </li>
                    </ul>

                    <ul class="sidebar-menu pt-3">

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/trainningcurriculum.png" alt="Training Curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Training Curriculum</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/curriculum/listcurriculum">Curriculum List</a></li>
                                    <li><a href="/FLM_NEW/view/curriculum/newcurriculum">New Curriculum</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/subjectmanagement.png" alt="Subject Management" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Management</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/admin/subject?action=list">Subject List</a></li>
                                    <li><a href="/FLM_NEW/view/admin/subject?action=add">Add New Subject</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown" >
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/decision.png" alt="Material Decisions" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Material Decisions</a>     
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/decision/dicisioncontroller">Decision List</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown" >
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/design.png" alt="Syllabus Designing" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus Designing</a>     
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="#">Syllabus List</a></li>
                                    <li><a href="#">Syllabus Design</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>




                </c:if>
                <c:if test="${sessionScope.acc.getRoleName() == 'CRDD STAFF'}" >
                    <ul class="sidebar-menu pt-3">


                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/curriculum/listcurriculum" >
                                <i >
                                    <img src="../../assets/images/iconsb/curriculum.png" alt="curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Curriculum
                            </a>                
                            <c:if test="${op == 4}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/curriculum/overviewcurriculum?Ccode=${c.getCode()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.getCode()}">POs</a></li>
                                            <c:if test="${oppo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}&amp;po_id=${p.po_id}">POs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}"></a></li>
                                        <!--<li><a href="/FLM_NEW/view/admin/addPLOs?Ccode=${c.getCode()}">Add New PLO</a></li>-->
                                        <li><a href="/FLM_NEW/view/common/plo?Ccode=${c.getCode()}">PLOs</a></li> 
                                            <c:if test="${opplo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}&amp;plo_id=${p.plo_id}">PLOs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingPLO_PO?Ccode=${c.getCode()}">PLO-POs</a></li>
                                        <li><a href="/FLM_NEW/view/common/curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=">Subjects</a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingplosubject?Ccode=${c.code}">PLO_Subject</a></li>
                                        
                                        <li><a href="/FLM_NEW/view/common/combo?action=combolist&curriculumid=${c.curriculum_id}">Combo</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.getCode()}">Elective</a></li>
                                        <li><a href="/FLM_NEW/view/common/contentgroup?action=list&curriculumid=${c.curriculum_id}">Content Group</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=contentgrouplist&contentgroupid=${contentgroupid}&curriculumid=${c.curriculum_id}"></a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/common/syllabus?action=list" >
                                <i><img src="../../assets/images/iconsb/syllabus.png" alt="syllabus" 
                                        style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus</a> 
                                <c:if test="${dropDownS == 1}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/common/syllabus?action=overview&sylId=${syllabus.getId()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=list&sylId=${syllabus.getId()}">CLOs</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=mapping&sylId=${syllabus.getId()}">CLOs_PLOs</a></li>
                                        <li><a href="/FLM_NEW/view/question/question?action=1">Question</a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/combolistfull?action=list">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Combo" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Combo</a>     

                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/subjectpredecessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Predecessors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Predecessor</a>     

                        </li>
                        <li>  
                            <a href="/FLM_NEW/view/common/subjectsuccessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Successors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Successor</a>
                        </li>
                    </ul>

                    <ul class="sidebar-menu pt-3">

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/trainningcurriculum.png" alt="Training Curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Training Curriculum</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/curriculum/listcurriculum">Curriculum List</a></li>
                                    <li><a href="/FLM_NEW/view/curriculum/newcurriculum">New Curriculum</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/subjectmanagement.png" alt="Subject Management" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Management</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="/FLM_NEW/view/admin/subject?action=list">Subject List</a></li>
                                    <li><a href="/FLM_NEW/view/admin/subject?action=add">Add New Subject</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown" >
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/decision.png" alt="Material Decisions" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Material Decisions</a>     
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="#">Syllabus List</a></li>
                                    <li><a href="#">Syllabus Design</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown" >
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/design.png" alt="Syllabus Designing" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus Designing</a>     
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="#">Syllabus List</a></li>
                                    <li><a href="#">Syllabus Design</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>




                </c:if>
                <c:if test="${sessionScope.acc.getRoleName() == 'STUDENT'}" >
                    <ul class="sidebar-menu pt-3">
                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/curriculum/listcurriculum" >
                                <i >
                                    <img src="../../assets/images/iconsb/curriculum.png" alt="curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Curriculum
                            </a>                
                            <c:if test="${op == 4}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/curriculum/overviewcurriculum?Ccode=${c.getCode()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.getCode()}">POs</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/plo?Ccode=${c.getCode()}">PLOs</a></li> 
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingPLO_PO?Ccode=${c.getCode()}">PLO-POs</a></li>
                                        <li><a href="/FLM_NEW/view/common/curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=">Subjects</a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingplosubject?Ccode=${c.code}">PLO_Subject</a></li>
                                        <li><a href="/FLM_NEW/view/common/combo?action=combolist&curriculumid=${c.curriculum_id}">Combo</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.getCode()}">Elective</a></li>
                                        <li><a href="/FLM_NEW/view/common/contentgroup?action=list&curriculumid=${c.curriculum_id}">Content Group</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=contentgrouplist&contentgroupid=${contentgroupid}&curriculumid=${c.curriculum_id}"></a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/common/syllabus?action=list" >
                                <i><img src="../../assets/images/iconsb/syllabus.png" alt="syllabus" 
                                        style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus</a> 
                                <c:if test="${dropDownS == 1}">
                                <div class="sidebar-submenu">
                                    <ul>

                                        <li><a href="/FLM_NEW/view/common/syllabus?action=overview&sylId=${syllabus.getId()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=list&sylId=${syllabus.getId()}">CLOs</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=mapping&sylId=${syllabus.getId()}">CLOs_PLOs</a></li>
                                        <li><a href="/FLM_NEW/view/question/question?action=1">Question</a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/combolistfull?action=list">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Combo" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Combo</a>     

                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/subjectpredecessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Predecessors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Predecessor</a>     

                        </li>
                        <li>  
                            <a href="/FLM_NEW/view/common/subjectsuccessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Successors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Successor</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.acc.getRoleName() == 'TEACHER'}" >
                    <ul class="sidebar-menu pt-3">
                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/curriculum/listcurriculum" >
                                <i >
                                    <img src="../../assets/images/iconsb/curriculum.png" alt="curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Curriculum
                            </a>                
                            <c:if test="${op == 4}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/curriculum/overviewcurriculum?Ccode=${c.getCode()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.getCode()}">POs</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/plo?Ccode=${c.getCode()}">PLOs</a></li> 
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingPLO_PO?Ccode=${c.getCode()}">PLO-POs</a></li>
                                        <li><a href="/FLM_NEW/view/common/curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=">Subjects</a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingplosubject?Ccode=${c.code}">PLO_Subject</a></li>
                                        <li><a href="/FLM_NEW/view/common/combo?action=combolist&curriculumid=${c.curriculum_id}">Combo</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.getCode()}">Elective</a></li>
                                        <li><a href="/FLM_NEW/view/common/contentgroup?action=list&curriculumid=${c.curriculum_id}">Content Group</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=contentgrouplist&contentgroupid=${contentgroupid}&curriculumid=${c.curriculum_id}"></a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/common/syllabus?action=list" >
                                <i><img src="../../assets/images/iconsb/syllabus.png" alt="syllabus" 
                                        style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus</a> 
                                <c:if test="${dropDownS == 1}">
                                <div class="sidebar-submenu">
                                    <ul>

                                        <li><a href="/FLM_NEW/view/common/syllabus?action=overview&sylId=${syllabus.getId()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=list&sylId=${syllabus.getId()}">CLOs</a></li>
                                        <li><a href="/FLM_NEW/view/common/clo?action=mapping&sylId=${syllabus.getId()}">CLOs_PLOs</a></li>
                                        <li><a href="/FLM_NEW/view/question/question?action=1">Question</a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/combolistfull?action=list">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Combo" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Combo</a>     

                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/subjectpredecessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Predecessors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Predecessor</a>     

                        </li>
                        <li>  
                            <a href="/FLM_NEW/view/common/subjectsuccessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Successors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Successor</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.acc.getRoleName() == 'SYLLABUS REVIEWER'}" >

                    <ul class="sidebar-menu pt-3">


                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/curriculum/listcurriculum" >
                                <i >
                                    <img src="../../assets/images/iconsb/curriculum.png" alt="curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Curriculum
                            </a>                
                            <c:if test="${op == 4}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/curriculum/overviewcurriculum?Ccode=${c.getCode()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.getCode()}">POs</a></li>
                                            <c:if test="${oppo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}&amp;po_id=${p.po_id}">POs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}"></a></li>
                                        <li><a href="/FLM_NEW/view/admin/addPLOs?Ccode=${c.getCode()}">Add New PLO</a></li>
                                        <li><a href="/FLM_NEW/view/common/plo?Ccode=${c.getCode()}">PLOs</a></li> 
                                            <c:if test="${opplo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}&amp;plo_id=${p.plo_id}">PLOs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingPLO_PO?Ccode=${c.getCode()}">PLO-POs</a></li>
                                        <li><a href="/FLM_NEW/view/common/curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=">Subjects</a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingplosubject?Ccode=${c.code}">PLO_Subject</a></li>
                                        
                                        <li><a href="/FLM_NEW/view/common/combo?action=combolist&curriculumid=${c.curriculum_id}">Combo</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.getCode()}">Elective</a></li>
                                        <li><a href="/FLM_NEW/view/common/contentgroup?action=list&curriculumid=${c.curriculum_id}">Content Group</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=contentgrouplist&contentgroupid=${contentgroupid}&curriculumid=${c.curriculum_id}"></a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>

                        <li>
                            <a href="/FLM_NEW/view/common/combolistfull?action=list">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Combo" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Combo</a>     

                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/subjectpredecessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Predecessors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Predecessor</a>     

                        </li>
                        <li>  
                            <a href="/FLM_NEW/view/common/subjectsuccessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Successors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Successor</a>
                        </li>
                    </ul>
                    <ul class="sidebar-menu pt-3">      

                        <li class="sidebar-dropdown" >
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/design.png" alt="Syllabus Designing" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus Designing</a>     
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="#">Syllabus List</a></li>
                                    <li><a href="#">Syllabus Design</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>

                </c:if>
                <c:if test="${sessionScope.acc.getRoleName() == 'SYLLABUS DESIGNER'}" >

                    <ul class="sidebar-menu pt-3">


                        <li class="sidebar-dropdown">
                            <a href="/FLM_NEW/view/curriculum/listcurriculum" >
                                <i >
                                    <img src="../../assets/images/iconsb/curriculum.png" alt="curriculum" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Curriculum
                            </a>                
                            <c:if test="${op == 4}">
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li><a href="/FLM_NEW/view/curriculum/overviewcurriculum?Ccode=${c.getCode()}">Overview</a></li>
                                        <li><a href="/FLM_NEW/view/admin/addpos?Ccode=${c.getCode()}">Add New PO</a></li>
                                        <li><a href="/FLM_NEW/view/common/po?action=polist&Ccode=${c.getCode()}">POs</a></li>
                                            <c:if test="${oppo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}&amp;po_id=${p.po_id}">POs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/podetail?Ccode=${c.code}"></a></li>
                                        <li><a href="/FLM_NEW/view/admin/addPLOs?Ccode=${c.getCode()}">Add New PLO</a></li>
                                        <li><a href="/FLM_NEW/view/common/plo?Ccode=${c.getCode()}">PLOs</a></li> 
                                            <c:if test="${opplo == 0}">
                                            <li><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}&amp;plo_id=${p.plo_id}">PLOs Details</a></li>
                                            </c:if>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/plodetails?Ccode=${c.code}"></a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/admin/syllabuses?subjectId=${cSubject.getSubjectId()}&cId=${c.getCurriculum_id()}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingPLO_PO?Ccode=${c.getCode()}">PLO-POs</a></li>
                                        <li><a href="/FLM_NEW/view/common/curriculumsubject?action=list&cId=${c.getCurriculum_id()}&selectFilter=&search=">Subjects</a></li>
                                        <li><a href="/FLM_NEW/view/common/mappingplosubject?Ccode=${c.code}">PLO_Subject</a></li>
                                        <li><a href="/FLM_NEW/view/common/combo?action=combolist&curriculumid=${c.curriculum_id}">Combo</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=combodetail&comboid=${comboid}&curriculumid=${c.curriculum_id}"></a></li>
                                        <li><a href="/FLM_NEW/view/common/elective?action=electivelist&Ccode=${c.getCode()}">Elective</a></li>
                                        <li><a href="/FLM_NEW/view/common/contentgroup?action=list&curriculumid=${c.curriculum_id}">Content Group</a></li>
                                        <li hidden=""><a  href="/FLM_NEW/view/common/combo?action=contentgrouplist&contentgroupid=${contentgroupid}&curriculumid=${c.curriculum_id}"></a></li>
                                    </ul>
                                </div>
                            </c:if>
                        </li>

                        <li>
                            <a href="/FLM_NEW/view/common/combolistfull?action=list">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Combo" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Combo</a>     

                        </li>
                        <li>
                            <a href="/FLM_NEW/view/common/subjectpredecessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Predecessors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Predecessor</a>     

                        </li>
                        <li>  
                            <a href="/FLM_NEW/view/common/subjectsuccessors">
                                <i >
                                    <img src="../../assets/images/iconsb/subject.png" alt="Subject Successors" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Subject Successor</a>
                        </li>
                    </ul>
                    <ul class="sidebar-menu pt-3">      

                        <li class="sidebar-dropdown" >
                            <a href="javascript:void(0)">
                                <i >
                                    <img src="../../assets/images/iconsb/design.png" alt="Syllabus Designing" 
                                         style="margin-left: 3px;margin-right: 5px;width:20px"/>
                                </i>Syllabus Designing</a>     
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="#">Syllabus List</a></li>
                                    <li><a href="#">Syllabus Design</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>

                </c:if>
            </div>
        </nav>
    </body>
</html>
