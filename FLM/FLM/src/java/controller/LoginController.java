/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.BaseDAO;
import dao.SettingDAO;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Setting;
import model.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

//Dùng thư viện restfb để truy vấn thông tin với facebook api, 
//Dùng thư viện org.apache.httpcomponents (httpclient, httpcore, fluent)
//để gửi request tới facebook, thư viện gson để convert dữ liệu từ dạng json và ngược lại.
/**
 *
 * @author user
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }
    private static final long serialVersionUID = 1L;

    public static String getGoogleToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response;
        // gui request len gooogle
        response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        //sever tra ve kieu Json
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        //lay access_token ( authentication token )
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    //get user google info
    public static User getUserGoogleInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        //goi request len sever de tra ve du lieu nguoi dung
        String response = Request.Get(link).execute().returnContent().asString();
        // cast thong tin google vao user model
        User googlePojo = new Gson().fromJson(response, User.class);
        return googlePojo;
    }

//    //get user facebook info
//    public static User getUserFacebookInfo(String accessToken) throws IOException {
//        String link = "https://graph.facebook.com/me?access_token=" + accessToken;
//        //goi request len sever de tra ve du lieu nguoi dung
//        String response = Request.Get(link).execute().returnContent().asString();
//        // cast thong tin google vao user model
//        User facebookPojo = new Gson().fromJson(response, User.class);
//        return facebookPojo;
//    }
//
//    //get facebook token
//    public static String getFacebookToken(final String code) throws ClientProtocolException, IOException {
//        String link = String.format(Constants.FACEBOOK_LINK_GET_TOKEN,
//                Constants.FACEBOOK_APP_ID, Constants.FACEBOOK_APP_SECRET, Constants.FACEBOOK_REDIRECT_URL, code);
//        String response = Request.Get(link).execute().returnContent().asString();
//        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
//        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
//        return accessToken;
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO ud = new UserDAO();
            HttpSession session = request.getSession();
            String code = request.getParameter("code");
            // kiểm tra không có biến code đưa đến trang login
            if (code == null || code.isEmpty()) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            // kiểm tra nếu có biến code thì login bằng google hoặc facebook
            if (code != null) {
                String scope = request.getParameter("scope");
                // kiểm tra nếu có biến scope thì login bằng google
                if (scope != null) {
                    //lay access token
                    String accessToken = getGoogleToken(code);
                    //khoi tao user voi thong tin google
                    User user = getUserGoogleInfo(accessToken);
                    // kiem tra email google co phai la @fpt.edu.vn khong
                    if (user.getEmail().endsWith("@fpt.edu.vn")) {
                        // kiem tra email google co ton tai trong database hay khong
                        if (ud.checkEmailGoogle(user.getEmail())) {
                            User newUser = ud.getInfoUserGoogle(user.getEmail());
                            System.out.println(newUser);
                            // kiểm tra nếu status khác 1 thì account không tồn tại
                            if (newUser.getStatus().equals("1")) {
                                // kiểm tra nếu isactive khác 1 thì account không có quyền
                                // và trở thành guest
                                if (newUser.getIsActive().equals("1")) {
                                    session.setAttribute("acc", newUser);
                                    request.getRequestDispatcher("home.jsp").forward(request, response);
                                } else {
                                    User u = new User(
                                            newUser.getUserId(), newUser.getName(),
                                            newUser.getEmail(), newUser.getMobile(),
                                            newUser.getUsername(), newUser.getPassword(),
                                            newUser.getPicture(), newUser.getStatus(),
                                            "GUEST", newUser.getIsActive());
                                    session.setAttribute("acc", u);
                                    request.getRequestDispatcher("home.jsp").forward(request, response);
                                }
                            } else {
                                request.setAttribute("errorAccount", "Account does not exist");
                                request.getRequestDispatcher("login.jsp").forward(request, response);
                            }
                            //nếu không tồn tại tài khoản trong database thì add tài khoản
                            //vào database trước sau đó mới login
                        } else {
                            SettingDAO s = new SettingDAO();
                            int lastUserId = Integer.parseInt(ud.getLastUser().getUserId());
                            int newID = lastUserId + 1;
                            ud.addUserGoogle(newID, user.getEmail(), user.getName(), user.getPicture());
                            ud.addUserRoleGoogle(Integer.parseInt(s.getRoleNameIdUserGoogle().getId()), newID);
                            User newUser = ud.getInfoUserGoogle(user.getEmail());
                            User u = new User(
                                    newUser.getUserId(), newUser.getName(),
                                    newUser.getEmail(), newUser.getMobile(),
                                    newUser.getUsername(), newUser.getPassword(),
                                    newUser.getPicture(), newUser.getStatus(),
                                    ud.getRoleNameUser(newID).getRoleName(),
                                    newUser.getIsActive());
                            System.out.println(u);
                            session.setAttribute("acc", u);
                            request.getRequestDispatcher("home.jsp").forward(request, response);
                        }
                        //khong phai mail @fpt.edu.vn thi bao loi ve trang login
                    } else {
                        String errorEmail = "Please login by email @fpt.edu.vn";
                        request.setAttribute("errorEmail", errorEmail);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                    // else nếu không có biến scope thì login bằng facebook
                }
//                else {
//                    String accessToken = getFacebookToken(code);
//                    User user = getUserFacebookInfo(accessToken);
//                    //check account có tồn tại hay không bằng cách gán id facebook
//                    // vào username của user
//                    if (ud.checkUserbyJustUserName(user.getId())) {
//                        User newUser = ud.getInfoUserFacebook(user.getId());
//                        // kiểm tra nếu status khác 1 thì account không tồn tại
//                        if (newUser.getStatus().equals("1")) {
//                            // kiểm tra nếu isactive khác 1 thì account không có quyền
//                            // và trở thành guest
//                            if (newUser.getIsActive().equals("1")) {
//                                session.setAttribute("acc", newUser);
//                                request.getRequestDispatcher("home.jsp").forward(request, response);
//                            } else {
//                                User u = new User(
//                                        newUser.getUserId(), newUser.getName(),
//                                        newUser.getEmail(), newUser.getMobile(),
//                                        newUser.getUsername(), newUser.getPassword(),
//                                        newUser.getPicture(), newUser.getStatus(),
//                                        "GUEST", newUser.getIsActive());
//                                System.out.println(u);
//                                session.setAttribute("acc", u);
//                                request.getRequestDispatcher("home.jsp").forward(request, response);
//                            }
//                        } else {
//                            request.setAttribute("errorAccount", "Account does not exist");
//                            request.getRequestDispatcher("login.jsp").forward(request, response);
//                        }
//                        //nếu không tồn tại tài khoản trong database thì add tài khoản
//                        //vào database trước sau đó mới login
//                    } else {
//                        int lastUserId = Integer.parseInt(ud.getLastUser().getUserId());
//                        int newID = lastUserId + 1;
//                        ud.addUserFacebook(newID, user.getId(), user.getName());
//                        ud.addUserRoleFacebook(newID);
//                        User newUser = ud.getInfoUserFacebook(user.getId());
//                        User u = new User(
//                                newUser.getUserId(), newUser.getName(),
//                                newUser.getEmail(), newUser.getMobile(),
//                                newUser.getUsername(), newUser.getPassword(),
//                                newUser.getPicture(), newUser.getStatus(),
//                                ud.getRoleNameUser(newID).getRoleName(),
//                                newUser.getIsActive());
//                        System.out.println(u);
//                        session.setAttribute("acc", u);
//                        request.getRequestDispatcher("home.jsp").forward(request, response);
//                    }
//                }
                String toast = (String) session.getAttribute("toast");
                if (toast != null) {
                    request.setAttribute("toast", toast);
                    response.sendRedirect("/FLM_NEW/view/common/login");
                }
            }
        } catch (ServletException | IOException e) {
            System.out.println("loi doGet loginController: " + e.getMessage());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String btlogin = request.getParameter("btlogin");
            UserDAO ud = new UserDAO();
            BaseDAO bs = new BaseDAO();
            String passwordhs = BaseDAO.getMD5Hash(password);
            System.out.println(passwordhs);
            HttpSession session = request.getSession();
            // kiem tra neu bam nut login thi thuc hien 
            if (btlogin != null) {
                // kiểm tra xem tài khoản có phải username không
                if (account.isEmpty()) {
                    request.setAttribute("passwordNew", password);
                    request.setAttribute("errorAccount", "Account cannot be empty");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                if (ud.checkUserbyUserName(account, passwordhs)) {
                    // lay thong tin account
                    User accUsername = ud.getUserByUsername(account, passwordhs);
                    if (accUsername.getStatus().equals("1")) {
                        if (accUsername.getIsActive().equals("1")) {
                            session.setAttribute("acc", accUsername);
                            request.getRequestDispatcher("home.jsp").forward(request, response);
                        } else {
                            User u = new User(
                                    accUsername.getUserId(), accUsername.getName(),
                                    accUsername.getEmail(), accUsername.getMobile(),
                                    accUsername.getUsername(), accUsername.getPassword(),
                                    accUsername.getPicture(), accUsername.getStatus(),
                                    "GUEST", accUsername.getIsActive());
                            System.out.println(u);
                            session.setAttribute("acc", u);
                            request.getRequestDispatcher("home.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("accountNew", account);
                        request.setAttribute("passwordNew", password);
                        request.setAttribute("errorAccount", "Account does not exist");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                    // kiểm tra xem tài khoản có phải email không
                } else if (ud.checkUserbyEmail(account, passwordhs)) {
                    User accEmail = ud.getUserByEmail(account, passwordhs);
                    if (accEmail.getStatus().equals("1")) {
                        if (accEmail.getIsActive().equals("1")) {
                            session.setAttribute("acc", accEmail);
                            request.getRequestDispatcher("home.jsp").forward(request, response);
                        } else {
                            User u = new User(
                                    accEmail.getUserId(), accEmail.getName(),
                                    accEmail.getEmail(), accEmail.getMobile(),
                                    accEmail.getUsername(), accEmail.getPassword(),
                                    accEmail.getPicture(), accEmail.getStatus(),
                                    "GUEST", accEmail.getIsActive());
                            session.setAttribute("acc", u);
                            request.getRequestDispatcher("home.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("accountNew", account);
                        request.setAttribute("passwordNew", password);
                        request.setAttribute("errorAccount", "Account does not exist");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                    // kiểm tra xem tài khoản có phải phone không
                } else if (ud.checkUserbyPhone(account, passwordhs)) {
                    User accPhone = ud.getUserByPhone(account, passwordhs);
                    if (accPhone.getStatus().equals("1")) {
                        if (accPhone.getIsActive().equals("1")) {
                            session.setAttribute("acc", accPhone);
                            request.getRequestDispatcher("home.jsp").forward(request, response);
                        } else {
                            User u = new User(
                                    accPhone.getUserId(), accPhone.getName(),
                                    accPhone.getEmail(), accPhone.getMobile(),
                                    accPhone.getUsername(), accPhone.getPassword(),
                                    accPhone.getPicture(), accPhone.getStatus(),
                                    "GUEST", accPhone.getIsActive());
                            session.setAttribute("acc", u);
                            request.getRequestDispatcher("home.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("accountNew", account);
                        request.setAttribute("passwordNew", password);
                        request.setAttribute("errorAccount", "Account does not exist");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                    //neu tai khoan hoac mat khau sai thi bao loi 
                } else {
                    request.setAttribute("accountNew", account);
                    request.setAttribute("passwordNew", password);
                    request.setAttribute("errorAccount", "Wrong account or password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("Loi LoginController doPOST: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
