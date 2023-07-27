/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.User;

/**
 *
 * @author user
 */
public class FilterAuthorizationNew implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterAuthorizationNew() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("NewFilter:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("NewFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("acc");
        String url = httpRequest.getServletPath();
        String roleName = user == null ? "GUEST" : user.getRoleName();

        List<String> allowedUrlsForGuest = Arrays.asList(
                "/index.jsp",
                "/view/common/login",
                "/view/common/home",
                "/view/common/redirectapplogin",
                "/view/user/userprofile",
                "/view/common/forgotpassword",
                "/view/common/forgotpasswordbymobile",
                "/view/common/register",
                "/view/curriculum/listcurriculum",
                "/logout",
                "/view/curriculum/overviewcurriculum",
                "/view/common/plo",
                "/view/common/combo",
                "/view/common/po",
                "/view/common/curriculumsubject",
                "/view/curriculum/editcurriculum",
                "/view/common/mappingPLO_PO",
                "/view/common/syllabuses",
                "/view/common/elective",
                "/view/common/mappingplosubject",
                "/view/common/syllabus",
                "/view/common/clo",
                "/view/common/subjectpredecessors",
                "/view/common/subjectsuccessors",
                "/view/decision/dicisioncontroller",
                "/view/common/import",
                "/view/common/contentgroup",
                "/view/common/combolistfull",
                "/view/common/errorauthorization.jsp"
        );

        List<String> allowedUrlsForAdmin = Arrays.asList(
                "/index.jsp",
                "/view/common/login",
                "/view/common/home",
                "/view/common/redirectapplogin",
                "/view/user/userprofile",
                "/view/user/changepassword",
                "/view/common/forgotpassword",
                "/view/common/forgotpasswordbymobile",
                "/view/common/register",
                "/view/curriculum/listcurriculum",
                "/logout",
                "/view/curriculum/overviewcurriculum",
                "/view/common/plo",
                "/view/admin/plo",
                "/view/common/combo",
                "/view/common/po",
                "/view/curriculum/newcurriculum",
                "/view/common/curriculumsubject",
                "/view/curriculum/editcurriculum",
                "/view/common/mappingPLO_PO",
                "/view/common/syllabuses",
                "/view/common/elective",
                "/view/admin/electivedetail",
                "/view/common/mappingplosubject",
                "/view/common/syllabus",
                "/view/common/clo",
                "/view/admin/clo",
                "/view/common/subjectpredecessors",
                "/view/common/subjectsuccessors",
                "/view/decision/dicisioncontroller",
                "/view/common/import",
                "/view/common/contentgroup",
                "/view/common/combolistfull",
                "/view/admin/adduser",
                "/view/admin/account",
                "/view/admin/setting",
                "/view/admin/settingdetail",
                "/view/admin/plodetails",
                "/view/admin/subject",
                "/view/admin/combo",
                "/view/admin/addPLOs",
                "/view/admin/curriculumsubject",
                "/view/admin/podetail",
                "/view/curriculum/editcurriculum",
                "/view/admin/addpos",
                "/view/admin/addsetting",
                "/view/admin/contentgroup",
                "/view/admin/combolistfull",
                "/view/crddstaff/syllabus",
                "/view/crddstaff/electivedetail",
                "/view/question/question",
                "/view/common/errorauthorization.jsp"
               
        );
        List<String> allowedUrlsForStudent = Arrays.asList(
                "/index.jsp",
                "/view/common/login",
                "/view/common/home",
                "/view/common/redirectapplogin",
                "/view/user/userprofile",
                "/view/user/changepassword",
                "/view/common/forgotpassword",
                "/view/common/forgotpasswordbymobile",
                "/view/common/register",
                "/view/curriculum/listcurriculum",
                "/logout",
                "/view/curriculum/overviewcurriculum",
                "/view/common/plo",
                "/view/common/combo",
                "/view/common/po",
                "/view/common/curriculumsubject",
                "/view/curriculum/editcurriculum",
                "/view/common/mappingPLO_PO",
                "/view/common/syllabuses",
                "/view/common/elective",
                "/view/common/mappingplosubject",
                "/view/common/syllabus",
                "/view/common/clo",
                "/view/common/subjectpredecessors",
                "/view/common/subjectsuccessors",
                "/view/decision/dicisioncontroller",
                "/view/common/import",
                "/view/common/contentgroup",
                "/view/common/combolistfull",
                "/view/question/question",
                "/view/common/errorauthorization.jsp"
        );
        List<String> allowedUrlsForTeacher = Arrays.asList(
                "/index.jsp",
                "/view/common/login",
                "/view/common/home",
                "/view/common/redirectapplogin",
                "/view/user/userprofile",
                "/view/user/changepassword",
                "/view/common/forgotpassword",
                "/view/common/forgotpasswordbymobile",
                "/view/common/register",
                "/view/curriculum/listcurriculum",
                "/logout",
                "/view/curriculum/overviewcurriculum",
                "/view/common/plo",
                "/view/common/combo",
                "/view/common/po",
                "/view/common/curriculumsubject",
                "/view/curriculum/editcurriculum",
                "/view/common/mappingPLO_PO",
                "/view/common/syllabuses",
                "/view/common/elective",
                "/view/common/mappingplosubject",
                "/view/common/syllabus",
                "/view/common/clo",
                "/view/common/subjectpredecessors",
                "/view/common/subjectsuccessors",
                "/view/decision/dicisioncontroller",
                "/view/common/import",
                "/view/common/contentgroup",
                "/view/common/combolistfull",
                "/view/question/question",
                "/view/common/errorauthorization.jsp"
        );
        List<String> allowedUrlsForCrddHead = Arrays.asList(
                "/index.jsp",
                "/view/common/login",
                "/view/common/home",
                "/view/common/redirectapplogin",
                "/view/user/userprofile",
                "/view/user/changepassword",
                "/view/common/forgotpassword",
                "/view/admin/contentgroup",
                "/view/common/forgotpasswordbymobile",
                "/view/common/register",
                "/view/curriculum/listcurriculum",
                "/logout",
                "/view/curriculum/overviewcurriculum",
                "/view/common/plo",
                "/view/admin/plo",
                "/view/admin/addPLOs",
                "/view/common/combo",
                "/view/common/po",
                "/view/common/curriculumsubject",
                "/view/curriculum/editcurriculum",
                "/view/common/mappingPLO_PO",
                "/view/common/syllabuses",
                "/view/common/elective",
                "/view/common/mappingplosubject",
                "/view/common/syllabus",
                "/view/common/clo",
                "/view/common/subjectpredecessors",
                "/view/common/subjectsuccessors",
                "/view/decision/dicisioncontroller",
                "/view/common/import",
                "/view/common/contentgroup",
                "/view/common/combolistfull",
                "/view/crddstaff/syllabus",
                "/view/curriculum/newcurriculum",
                "/view/question/question",
                "/view/common/errorauthorization.jsp",
                "/view/crddstaff/electivedetail",
                "/view/admin/curriculumsubject"
        );
        List<String> allowedUrlsForCrddStaff = Arrays.asList(
                "/index.jsp",
                "/view/common/login",
                "/view/common/home",
                "/view/common/redirectapplogin",
                "/view/user/userprofile",
                "/view/user/changepassword",
                "/view/common/forgotpassword",
                "/view/admin/contentgroup",
                "/view/common/forgotpasswordbymobile",
                "/view/common/register",
                "/view/curriculum/listcurriculum",
                "/logout",
                "/view/curriculum/overviewcurriculum",
                "/view/common/plo",
                "/view/admin/plo",
                "/view/admin/addPLOs",
                "/view/common/combo",
                "/view/common/po",
                "/view/common/curriculumsubject",
                "/view/curriculum/editcurriculum",
                "/view/common/mappingPLO_PO",
                "/view/common/syllabuses",
                "/view/common/elective",
                "/view/crddstaff/electivedetail",
                "/view/common/mappingplosubject",
                "/view/common/syllabus",
                "/view/common/clo",
                "/view/common/subjectpredecessors",
                "/view/common/subjectsuccessors",
                "/view/decision/dicisioncontroller",
                "/view/common/import",
                "/view/common/contentgroup",
                "/view/common/combolistfull",
                "/view/crddstaff/syllabus",
                "/view/common/errorauthorization.jsp",
                "/view/question/question",
                "/view/curriculum/newcurriculum",
                "/view/admin/addpos",
               "/view/admin/curriculumsubject"
        );
        List<String> allowedUrlsForSyllabusDesigner = Arrays.asList(
                "/index.jsp",
                "/view/common/login",
                "/view/common/home",
                "/view/common/redirectapplogin",
                "/view/user/userprofile",
                "/view/user/changepassword",
                "/view/common/forgotpassword",
                "/view/common/forgotpasswordbymobile",
                "/view/common/register",
                "/view/curriculum/listcurriculum",
                "/logout",
                "/view/curriculum/overviewcurriculum",
                "/view/common/plo",
                "/view/common/combo",
                "/view/common/po",
                "/view/common/curriculumsubject",
                "/view/curriculum/editcurriculum",
                "/view/common/mappingPLO_PO",
                "/view/common/syllabuses",
                "/view/common/elective",
                "/view/common/mappingplosubject",
                "/view/common/syllabus",
                "/view/common/clo",
                "/view/common/subjectpredecessors",
                "/view/common/subjectsuccessors",
                "/view/decision/dicisioncontroller",
                "/view/common/import",
                "/view/common/contentgroup",
                "/view/common/combolistfull",
                "/view/common/errorauthorization.jsp"
        );
        List<String> allowedUrlsForSyllabusReviewer = Arrays.asList(
                "/index.jsp",
                "/view/common/login",
                "/view/common/home",
                "/view/common/redirectapplogin",
                "/view/user/userprofile",
                "/view/user/changepassword",
                "/view/common/forgotpassword",
                "/view/common/forgotpasswordbymobile",
                "/view/common/register",
                "/view/curriculum/listcurriculum",
                "/logout",
                "/view/curriculum/overviewcurriculum",
                "/view/common/plo",
                "/view/common/combo",
                "/view/common/po",
                "/view/common/curriculumsubject",
                "/view/curriculum/editcurriculum",
                "/view/common/mappingPLO_PO",
                "/view/common/syllabuses",
                "/view/common/elective",
                "/view/common/mappingplosubject",
                "/view/common/syllabus",
                "/view/common/clo",
                "/view/common/subjectpredecessors",
                "/view/common/subjectsuccessors",
                "/view/decision/dicisioncontroller",
                "/view/common/import",
                "/view/common/contentgroup",
                "/view/common/combolistfull",
                "/view/common/errorauthorization.jsp"
        );
        Map<String, List<String>> roleUrlMapping = new HashMap<>();
        roleUrlMapping.put("GUEST", allowedUrlsForGuest);
        roleUrlMapping.put("ADMIN", allowedUrlsForAdmin);
        roleUrlMapping.put("STUDENT", allowedUrlsForStudent);
        roleUrlMapping.put("TEACHER", allowedUrlsForTeacher);
        roleUrlMapping.put("CRDD HEAD", allowedUrlsForCrddHead);
        roleUrlMapping.put("CRDD STAFF", allowedUrlsForCrddStaff);
        roleUrlMapping.put("SYLLABUS DESIGNER", allowedUrlsForSyllabusDesigner);
        roleUrlMapping.put("SYLLABUS REVIEWER", allowedUrlsForSyllabusReviewer);

        boolean isAllowedForRole = false;

        if (roleUrlMapping.containsKey(roleName)) {
            List<String> allowedUrlsForRole = roleUrlMapping.get(roleName);
            for (String allowedUrl : allowedUrlsForRole) {
                if (url.startsWith(allowedUrl)) {
                    isAllowedForRole = true;
                    break;
                }
            }
        }

        if (!isAllowedForRole) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/view/common/errorauthorization.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("NewFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("NewFilter()");
        }
        StringBuffer sb = new StringBuffer("NewFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
