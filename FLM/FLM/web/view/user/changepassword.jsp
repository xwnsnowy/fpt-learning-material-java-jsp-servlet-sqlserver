<%-- 
    Document   : changepassword
    Created on : May 22, 2023, 9:38:21 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

        <style>
            #popupContainer {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 9999;
            }

            #popupContent {
                width: 450px;
                background-color: #fff;
                margin: 100px auto;
                padding: 20px;
                border-radius: 5px;
            }

            #changeBtn {
                margin-top: 10px;
            }

            .fade-in {
                animation: fadeIn 0.5s;
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: scale(0.8);
                }
                to {
                    opacity: 1;
                    transform: scale(1);
                }
            }
            #closeBtn {
                position: absolute;
                top: 130px;
                right: 570px;
                cursor: pointer;
                font-size: 30px;
                color: #999;
            }

            #closeBtn:hover {
                color: #333;
            }
            .alert{
                color: red;
            }
            .btn-outline-secondary{
                width: 25%;
                margin-left: 12px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <button name='change' id="openBtn" class="btn btn-outline-secondary">
            Change Password
        </button>
      
            
                <div id="popupContainer">
                    <div id="popupContent">
                        <form action="changepassword" method="post">
                            <h2 >Change Password</h2>
                            <div class="mb-3">
                                <label  class="form-label">Current Password <span class="text-danger">*</span></label>
                                <input   type="password" class="form-control" placeholder="Enter Your Current Password   "
                                         name="currentpassword"  value="${currentPassword}" >
                            </div>

                            <div class="mb-3">
                                <label class="form-label">New Password <span class="text-danger">*</span></label>
                                <input  type="password" class="form-control" placeholder="Enter Your New Password   "
                                        name="newpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                                       title="Must contain at least one  number and one uppercase 
                                                       and lowercase letter, and at least 8 or more characters" value="${newPassword}">
                            </div><!-- comment -->

                            <div class="mb-3">
                                <label  class="form-label">Confirm Password <span class="text-danger">*</span></label>
                                <input   type="password" 
                                       class="form-control" placeholder="Enter Your Confirm Password   "
                                       name="confirmpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                                       title="Must contain at least one  number and one uppercase 
                                                       and lowercase letter, and at least 8 or more characters"  value="${confirmPassword}">
                                <!--class="btn btn-primary"--> 
                            </div>
                            <input type="submit" name='submit' value='Change password'>
                        </form>
                    </div>
                </div>
            
      
        <div class='alert'>${alertchangepassword}</div>

        <script>
            document.getElementById("openBtn").addEventListener("click", function () {
                document.getElementById("popupContainer").style.display = "block";
                setTimeout(function () {
                    document.getElementById("popupContent").classList.add("fade-in");
                }, 10);
            });

            document.getElementById("popupContainer").addEventListener("click", function (e) {
                if (e.target === this) {
                    document.getElementById("popupContainer").style.display = "none";
                    document.getElementById("popupContent").classList.remove("fade-in");
                }
            });
//            document.getElementById("closeBtn").addEventListener("click", function (e) {
//                if (e.target === this) {
//                    document.getElementById("popupContainer").style.display = "none";
//                    document.getElementById("popupContent").classList.remove("fade-in");
//                }
//            });
        </script>
    </body>
</html>
