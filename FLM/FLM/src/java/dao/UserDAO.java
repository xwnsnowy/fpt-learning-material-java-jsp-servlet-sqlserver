/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author user
 */
public class UserDAO extends BaseDAO {

    PreparedStatement ps;
    ResultSet rs;

    public boolean checkUserbyUserName(String username, String password) {
        try {
            String strSelect
                    = "select * from User "
                    + "where user_name=? "
                    + "and password=?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkUserbyUserName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public boolean checkUserbyEmail(String email, String password) {
        try {
            String strSelect
                    = "select * from User "
                    + "where email=? "
                    + "and password=?";
            // nem query vao de thuc thi cau truy van
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, email);
            pstm.setString(2, password);
            // thuc thi cau truy van va tra ve tap ket qua (result set)
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkUserbyEmail: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public boolean checkUserbyPhone(String phone, String password) {
        try {
            String strSelect
                    = "select * from User "
                    + "where mobile=? "
                    + "and password=?";
            // nem query vao de thuc thi cau truy van
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, phone);
            pstm.setString(2, password);
            // thuc thi cau truy van va tra ve tap ket qua (result set)
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkUserbyPhone: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public User getUserByUsername(String username, String password) {
        try {
            String strSelect
                    = "SELECT u.user_id, u.full_name, u.email, u.mobile, u.user_name, "
                    + "u.password, u.picture, u.status,s.setting_name,ur.is_active\n"
                    + "FROM user As u "
                    + "INNER JOIN user_role AS ur on u.user_id=ur.user_id "
                    + "INNER JOIN setting AS s on ur.role_id = s.setting_id "
                    + "WHERE user_name =? and password=?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("loi getUserByUsername: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public boolean checkEmailGoogle(String email) {
        try {
            String strSelect
                    = "SELECT email FROM user where email = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("loi checkEmailGoogle: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public User getUserByEmail(String username, String password) {
        try {
            String strSelect
                    = "SELECT u.user_id, u.full_name, u.email, u.mobile, u.user_name, "
                    + "u.password, u.picture, u.status,s.setting_name,ur.is_active\n"
                    + "FROM user As u "
                    + "INNER JOIN user_role AS ur on u.user_id=ur.user_id "
                    + "INNER JOIN setting AS s on ur.role_id = s.setting_id "
                    + "WHERE email =? and password=?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("loi getUserByUsername: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public User getUserByPhone(String username, String password) {
        try {
            String strSelect
                    = "SELECT u.user_id, u.full_name, u.email, u.mobile, u.user_name, "
                    + "u.password, u.picture, u.status,s.setting_name,ur.is_active\n"
                    + "FROM user As u "
                    + "INNER JOIN user_role AS ur on u.user_id=ur.user_id "
                    + "INNER JOIN setting AS s on ur.role_id = s.setting_id "
                    + "WHERE mobile =? and password=?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("loi getUserByPhone: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public User getInfoUserGoogle(String email) {
        try {
            String strSelect
                    = "SELECT u.user_id, u.full_name, u.email, u.mobile, u.user_name, \n"
                    + "                    u.password, u.picture, u.status,s.setting_name,ur.is_active\n"
                    + "                    FROM user As u \n"
                    + "                    INNER JOIN user_role AS ur on u.user_id=ur.user_id \n"
                    + "                    INNER JOIN setting AS s on ur.role_id = s.setting_id \n"
                    + "                    WHERE email =?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("loi getInfoUserGoogle: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public User getInfoUserFacebook(String username) {
        try {
            String strSelect
                    = "SELECT u.user_id, u.full_name, u.email, u.mobile, u.user_name, \n"
                    + " u.password, u.picture, u.status,s.setting_name,ur.is_active\n"
                    + " FROM user As u \n"
                    + " INNER JOIN user_role AS ur on u.user_id=ur.user_id \n"
                    + " INNER JOIN setting AS s on ur.role_id = s.setting_id \n"
                    + " WHERE user_name =?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new User(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("loi getInfoUserFacebook: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public User getLastUser() {
        try {
            String strSelect
                    = "SELECT u.user_id, u.full_name, u.email, u.mobile, u.user_name, "
                    + "  u.password, u.picture, u.status,s.setting_name,ur.is_active "
                    + "  FROM user As u "
                    + "  INNER JOIN user_role AS ur on u.user_id=ur.user_id "
                    + "  INNER JOIN setting AS s on ur.role_id = s.setting_id "
                    + "  ORDER BY u.user_id DESC LIMIT 0, 1;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("loi getLastUser: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void addUserRoleGoogle(int settingId, int userId) {
        try {
            String strIns = "INSERT INTO `user_role` (`role_id`, `user_id`, `is_active`)"
                    + " VALUES (?, ?, b'1');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setInt(1, settingId);
            pstm.setInt(2, userId);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("loi addUserRoleGoogle: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public User getRoleNameUser(int id) {
        try {
            String strSelect
                    = "SELECT s.setting_name "
                    + "FROM user_role AS ur "
                    + "INNER JOIN setting AS s on ur.role_id = s.setting_id "
                    + "where ur.user_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new User(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("loi getRoleNameUser: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void addUserGoogle(int id, String email, String name, String picture) {
        try {
            String strIns = "INSERT INTO `user` (`user_id`, `full_name`, `email`, `picture`, `status`) "
                    + "VALUES (?, ?, ?, ?, b'1');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, String.valueOf(id));
            pstm.setString(2, name);
            pstm.setString(3, email);
            pstm.setString(4, picture);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("loi addUserGoogle: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void addUserFacebook(int id, String username, String name) {
        try {
            String strIns = "INSERT INTO `user` (`user_id`, `user_name`, `full_name`, `status`) "
                    + "VALUES (?, ?, ?, b'1');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, String.valueOf(id));
            pstm.setString(2, username);
            pstm.setString(3, name);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("loi addUserFacebook: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void addUserRoleFacebook(int id) {
        try {
            String strIns = "INSERT INTO `user_role` (`role_id`, `user_id`, `is_active`)"
                    + " VALUES ('1', ?, b'1');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("loi addUserRoleGoogle: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    //dev: hiep
    public User getUserProfile(String user_id) {
        User u = new User();
        try {
            //step 1:           
//            cnn = DBContext.getConnection();
            //Step 2:           
            String sql = "select * from user where user_id=? ";
            //step 3:
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, user_id);
            //step 4:        
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                u.setUserId(rs.getString(1));
                u.setName(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setMobile(rs.getString(4));
                u.setUsername(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setPicture(rs.getString(7));
                u.setStatus(rs.getString(8));
            }
//            return u;
//            System.out.println(u.getUser_id());
        } catch (Exception e) {
            System.out.println("getUserProfile" + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return u;
    }

    public void UpdateProfile(User u) {
        try {
            String sql = "UPDATE flm_db.user SET full_name = ?, "
                    + "email = ?, mobile = ? , user_name ="
                    + " ?, picture = ? WHERE (user_id = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, u.getName());
            pstm.setString(2, u.getEmail());
            pstm.setString(3, u.getMobile());
            pstm.setString(4, u.getUsername());
            pstm.setString(5, u.getPicture());
            pstm.setString(6, u.getUserId());
            pstm.execute();

        } catch (Exception e) {
            System.out.println("UpdateProfile " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void UpdateProfileNoAvatar(User u) {
        try {
            String sql = "UPDATE flm_db.user SET full_name = ?, "
                    + "email = ?, mobile = ? , user_name ="
                    + " ? WHERE (user_id = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, u.getName());
            pstm.setString(2, u.getEmail());
            pstm.setString(3, u.getMobile());
            pstm.setString(4, u.getUsername());

            pstm.setString(5, u.getUserId());
            pstm.execute();

        } catch (Exception e) {
            System.out.println("UpdateProfileNoAvatar " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
    //--------------------------------------

    //-------------------Linh-------------------------------------------------
    public boolean checkUserbyJustEmail(String email) {
        try {
            String strSelect
                    = "select * from User "
                    + "where email=? ";
            // nem query vao de thuc thi cau truy van
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, email);
            // thuc thi cau truy van va tra ve tap ket qua (result set)
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkUserbyJustEmail: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public boolean checkUserbyJustPhone(String phone) {
        try {
            String strSelect
                    = "select * from User "
                    + "where mobile=? ";
            // nem query vao de thuc thi cau truy van
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, phone);

            // thuc thi cau truy van va tra ve tap ket qua (result set)
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkUserbyJustPhone: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public boolean checkUserbyJustUserName(String userName) {
        try {
            String strSelect
                    = "select * from User "
                    + "where user_name=? ";
            // nem query vao de thuc thi cau truy van
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, userName);

            // thuc thi cau truy van va tra ve tap ket qua (result set)
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkUserbyJustUserName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public void updatePasswordByEmmail(String password, String email) {
        try {
            String strUpdate = "UPDATE user SET password = ? WHERE email = ? ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, password);
            pstm.setString(2, email);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("updatePasswordByEmail" + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void updatePasswordByMobile(String password, String mobile) {
        try {
            String strUpdate = "UPDATE user SET password = ? WHERE mobile = ? ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, password);
            pstm.setString(2, mobile);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("updatePasswordByMobile" + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void changePassword(String user_name, String password) {
        try {

            password = BaseDAO.getMD5Hash(password);
            String strUpdate = "UPDATE user SET password = ? WHERE user_name = ? ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, password);
            pstm.setString(2, user_name);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("changePassword" + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public User getUserByUserNameByLinh(String userName, String password) {
        try {
            String strSelect
                    = "select user_name,password from flm_db.user\n"
                    + "where user_name = ? and password = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new User(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("loi getUserByPhone: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getRoleIdOfStudent() {
        try {
            String strSelect
                    = "select role_id from role\n"
                    + "  where role_name = 'student'";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                return rs.getString(1);

            }
        } catch (Exception e) {
            System.out.println("getRoleIdOfStudent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
//--------------------------------------------------------------------------------------------------------------

    public void AddUserRoleInDBForAddUser(String roleId, String userId) {
        try {
            String strSelect
                    = "INSERT INTO `flm_db`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES (?, ?, b'1');";

            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, roleId);
            pstm.setString(2, userId);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("AddUserRoleInDBForAddUser: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void AddUserInDBForAdmin(String roleId, String newUserId,
            String fullName, String email, String password, String mobile, String userName, String active, String picture) {
        password = BaseDAO.getMD5Hash(password);
        try {
            String strSelect
                    = "INSERT INTO `user` (`user_id`, `full_name`, `email`, `mobile`, `user_name`, `password`, `status` ,`picture`) \n"
                    + "VALUES (?, ?, ?, ?, ?, ?, b'1','avata-default.jpg');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, newUserId);
            pstm.setString(2, fullName);
            pstm.setString(3, email);
            pstm.setString(4, mobile);
            pstm.setString(5, userName);
            pstm.setString(6, password);
            pstm.execute();
            AddUserRoleInDBForAddUser(roleId, newUserId);

        } catch (Exception e) {
            System.out.println("AddUserInDBForAdmin: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public String getRoleIdOfUser(String role) {
        try {
            String strSelect
                    = "select role_id from user_role join setting\n"
                    + "on user_role.role_id = setting.setting_id\n"
                    + "where setting.setting_name = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, role);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                return rs.getString(1);

            }
        } catch (Exception e) {
            System.out.println("getRoleIdOfUser: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getRoleNameOfUser(String roleName) {
        try {
            String strSelect
                    = "select setting_id from setting  where setting_name= ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, roleName);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                return rs.getString(1);

            }
        } catch (Exception e) {
            System.out.println("getRoleNameOfUser: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

//    public static void main(String[] args) {
//        UserDAO ud = new UserDAO();
//        String username = "admin";
//        String password = "Linh@123";
//        String passwordhs = BaseDAO.getMD5Hash(password);
//        System.out.println(passwordhs);
//        System.out.println(ud.checkUserbyUserName(username, passwordhs));
//
//    }
//+++++++++++++Trung++++++++++++++++//
//    check User Already Exist By Email
    public boolean checkUserAlreadyExistByEmail(String email) {
        try {
            String strSelect
                    = "select * from user \n"
                    + "where email = ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;

            }
        } catch (Exception e) {
            System.out.println("checkUserAlreadyExistByEmail: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }
//check User Already Exist By Mobile

    public boolean checkUserAlreadyExistByMobile(String mobile) {
        try {
            String strSelect
                    = "select * from user \n"
                    + "where mobile = ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, mobile);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;

            }
        } catch (Exception e) {
            System.out.println("checkUserAlreadyExistByMobile: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }
//Add User In DB For Registration

    public void addUserInDBForRegistration(String roleId, String picture, String newUserId, String fullName, String email, String password) {
        try {
            String strSelect
                    = "INSERT INTO `user` (`user_id`, `picture`, `full_name`, `email`, `password`, `status`)\n"
                    + "VALUES (?, ?, ?, ?, ?, b'1');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, newUserId);
            pstm.setString(2, picture);
            pstm.setString(3, fullName);
            pstm.setString(4, email);
            pstm.setString(5, password);
            pstm.execute();
            addUserRoleInDBForRegistration(roleId, newUserId);
        } catch (Exception e) {
            System.out.println("AddUserInDBForRegistration: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
//Add User In DB For Registration By Mobile

    public void addUserInDBForRegistrationByMobile(String roleId, String picture, String newUserId, String fullName, String mobile, String password) {
        try {
            String strSelect
                    = "INSERT INTO `user` (`user_id`, `picture`, `full_name`, `mobile`, `password`, `status`)\n"
                    + "VALUES (?, ?, ?, ?, ?, b'1');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, newUserId);
            pstm.setString(2, picture);
            pstm.setString(3, fullName);
            pstm.setString(4, mobile);
            pstm.setString(5, password);
            pstm.execute();
            addUserRoleInDBForRegistration(roleId, newUserId);
        } catch (Exception e) {
            System.out.println("AddUserInDBForRegistrationByMobile: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
//Add User Role In DB For Registration

    public void addUserRoleInDBForRegistration(String roleId, String userId) {
        try {
            String strSelect
                    = "INSERT INTO `user_role` (`role_id`, `user_id`, `is_active`) VALUES (?, ?, b'1');";

            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, roleId);
            pstm.setString(2, userId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("AddUserRoleInDBForRegistration: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
//get last User Id

    public int getlastUserId() {
        try {
            String strSelect
                    = "select user_id  from user order by user_id desc limit 1";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
            System.out.println("getlastUserId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return -1;
    }
//get Role Id Of Guest

    public String getRoleIdOfGuest() {
        try {
            String strSelect
                    = "select setting_id from setting  where setting_name= 'guest'";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                return rs.getString(1);

            }
        } catch (Exception e) {
            System.out.println("getRoleIdOfGuest: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
//get List Account

    public ArrayList<User> getListAccount(String selectFilter, String search) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String strSelect
                    = " select u.user_id, u.picture, u.full_name, u.user_name, u.email, u.mobile, s.setting_name, uR.is_active, u.status from user_role uR\n"
                    + "join user u on u.user_id = uR.user_id \n"
                    + "join setting s on s.setting_id = uR.role_id \n"
                    + "where s.setting_name like ?\n"
                    + "and (u.user_id like ?\n"
                    + "or u.full_name like ?\n"
                    + "or u.user_name like ?\n"
                    + "or u.email like ?\n"
                    + "or u.mobile like ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + selectFilter + "%");
            pstm.setString(2, "%" + search + "%");
            pstm.setString(3, "%" + search + "%");
            pstm.setString(4, "%" + search + "%");
            pstm.setString(5, "%" + search + "%");
            pstm.setString(6, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getString(1));
                u.setPicture((rs.getString(2) == null) ? "#N/A" : rs.getString(2));
                u.setName(rs.getString(3));
                u.setUsername((rs.getString(4) == null) ? "#N/A" : rs.getString(4));
                u.setEmail((rs.getString(5) == null) ? "#N/A" : rs.getString(5));
                u.setMobile((rs.getString(6) == null) ? "#N/A" : rs.getString(6));
                u.setRoleName(rs.getString(7).toLowerCase());
                u.setIsActive((rs.getString(8).equals("1")) ? "true" : "false");
                u.setStatus((rs.getString(9).equals("1")) ? "true" : "false");
                list.add(u);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListAccount: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
//    update User Status

    public void updateUserStatus(String id, String status) {
        try {
            String strSelect = "update user\n"
                    + "set user.status = b?\n"
                    + "where user.user_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, status);
            pstm.setString(2, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateUserStatus: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
//update User

    public boolean updateUser(String id, String preRole, String role, String active, String status) {
        try {
            String strSelect
                    = " update user_role\n"
                    + "set user_role.role_id = (select setting_id from setting where setting_name = ?), user_role.is_active=b?\n"
                    + "where user_role.user_id = ? and user_role.role_id = (select setting_id from setting where setting_name = ?)";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, role);
            pstm.setString(2, active);
            pstm.setString(3, id);
            pstm.setString(4, preRole);
            pstm.execute();
            updateUserStatus(id, status);
            return true;
        } catch (Exception e) {
            System.out.println("UpdateUser: " + e.getMessage());
            return false;
        } finally {
            closeConnection(connection);
        }
    }

// delete User By User Id
    public void deleteUserByUserId(String daleteUserId) {
        try {
            String strSelect
                    = "delete from flm_db.user_role \n"
                    + "where user_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, daleteUserId);
            pstm.execute();
            strSelect = "delete from flm_db.user\n"
                    + "where user_id = ?;";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, daleteUserId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteUserByUserId: " + e.getMessage());
        }
    }

    public ResultSet getResultSetAccount(String selectFilter, String search) {
        try {
            String strSelect
                    = " select u.user_id, u.full_name, u.user_name, u.email, u.mobile, s.setting_name, u.status from user_role uR\n"
                    + "join user u on u.user_id = uR.user_id \n"
                    + "join setting s on s.setting_id = uR.role_id \n"
                    + "where s.setting_name like ?\n"
                    + "and (u.user_id like ?\n"
                    + "or u.full_name like ?\n"
                    + "or u.user_name like ?\n"
                    + "or u.email like ?\n"
                    + "or u.mobile like ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + selectFilter + "%");
            pstm.setString(2, "%" + search + "%");
            pstm.setString(3, "%" + search + "%");
            pstm.setString(4, "%" + search + "%");
            pstm.setString(5, "%" + search + "%");
            pstm.setString(6, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();

            return rs;
        } catch (Exception e) {
            System.out.println("getResultSetAccount: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public boolean checkUserE(String desginer) {
        String type = "syllabus designer";
        try {
            String strSelect
                    = "select * from flm_db.user u \n"
                    + "join flm_db.user_role ur on ur.user_id = u.user_id \n"
                    + "join flm_db.setting s on s.setting_id = ur.role_id\n"
                    + "where s.setting_name = ? and u.user_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, type);
            pstm.setString(2, desginer);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUserE: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public List<User> ListStaff() {
        ArrayList<User> data = new ArrayList<>();
        try {

            //Step 1:           
            String sql = "select * from user u\n"
                    + "join user_role ur on u.user_id = ur.user_id\n"
                    + "join setting s on ur.role_id = s.setting_id\n"
                    + "where setting_name = 'CRDD STAFF'";
            //step 2:   
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                User c = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                data.add(c);
            }
        } catch (Exception e) {
            System.out.println("ListStaff()" + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return data;
    }
}
