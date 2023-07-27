/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Curriculum;
import model.Decision;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hp
 */
public class DecisionDAO extends BaseDAO {

    public Decision CheckDecisionByID(int decision_id) {
        try {
            String sql = "select * from decision where decision_id = ? ;  ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, decision_id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Decision d = new Decision(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4));
                System.out.println("run");
                return d;

            }

        } catch (Exception e) {
            System.out.println("error CheckDecisionByID" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public Decision getDecisionBySubjectId(String id) {
        try {
            String strSelect = "SELECT decision.decision_id, decision.decision_no, "
                    + "decision.decision_name, decision.decision_ApprovedDate, "
                    + "decision.decision_CreateDate, decision.creator_id FROM flm_db.syllabus \n"
                    + "join flm_db.decision on syllabus.decision_id = decision.decision_id \n"
                    + "WHERE syllabus.subject_id = ? ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Decision(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getDate(5), rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("error getDecisionBySubjectId: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public Decision getDecisionByNo(String decision_no) {
        try {
            String sql = "select * from decision where decision_no = ? ;  ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, decision_no);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Decision d = new Decision(rs.getString(1), rs.getString(2),rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                
                return d;

            }

        } catch (Exception e) {
            System.out.println("error CheckDecisionByID" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }
    
       public ArrayList<Decision> SearchByNo(String searchString) {
        ArrayList<Decision> data = new ArrayList<>();
        try {

            //Step 1:           
            String sql = "SELECT * FROM decision\n"
                    + "where decision_no like ?\n"
                    + "order by decision_id\n"
                    + "limit 0, 20;";
            //step 2:        
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + searchString + "%");
//            pstm.setString(2, owner_id);

            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                Decision d = new Decision(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                data.add(d);
            }

        } catch (Exception e) {
            System.out.println("SearchByNo" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return data;
    }

    public ArrayList<Decision> SearchByName(String searchString) {
        ArrayList<Decision> data = new ArrayList<>();
        try {
            //Step 1:           
            String sql = "SELECT * FROM decision\n"
                    + "where decision_name like ?\n"
                    + "order by decision_id\n"
                    + "limit 0, 20;";
            //step 2:        
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + searchString + "%");
//            pstm.setString(2, owner_id);

            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                Decision d = new Decision(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                data.add(d);
            }

        } catch (Exception e) {
            System.out.println("SearchByNo" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return data;

    }

    public void updateDecision(Decision d) {
        try {
            String strInsert = "UPDATE `flm_db`.`decision` SET `decision_no` = ?, "
                    + "`decision_name` = ?, `decision_ApprovedDate` = ?, `notes` = ? WHERE (`decision_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strInsert);

            pstm.setString(1, d.getDecision_no());
            pstm.setString(2, d.getDecision_name());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = d.getDecision_date();
            String dateString = dateFormat.format(date);
            pstm.setString(3, dateString);
            pstm.setString(4, d.getNotes());
            pstm.setString(5, d.getDecision_id());

            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("updateDecisionForCurriculum " + e.getMessage());
        }finally {
            closeConnection(connection);
        }

    }

    public void AddNewDecision(Decision d, String date) {
        try {
            String strInsert = "INSERT INTO `flm_db`.`decision` (`decision_id`, `decision_no`, `decision_name`, `decision_CreateDate`, `creator_id`, `notes`)\n"
                    + " VALUES (?, ?, ?, ?, ?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strInsert);
            pstm.setString(1, d.getDecision_id());
            pstm.setString(2, d.getDecision_no());
            pstm.setString(3, d.getDecision_name());
            pstm.setString(4, date);
            pstm.setString(5, d.getCreator_id());
            pstm.setString(6, d.getNotes());   
            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("AddNewDecision" + e.getMessage());
        }
    }

    public int getDecisionIdNext() {
        try {
            String sql = "SELECT MAX(decision_id) from decision;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int MaxId = rs.getInt(1);
                return MaxId;
            }

        } catch (Exception e) {
            System.out.println("getDecisionIdNext()" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return 0;
    }

    public ArrayList<Decision> getListDecision() {
        ArrayList<Decision> list = new ArrayList<>();
        try {
            String sql = "select * from decision;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Decision d = new Decision(rs.getString(1), rs.getString(2),rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                list.add(d);
            }
            return list;

        } catch (Exception e) {
            System.out.println("error getListDecision" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public ResultSet getResultSetDecision() {
        try {
            String sql = "select decision_id, decision_no, CAST(decision_ApprovedDate as nchar) as decision_ApprovedDate  from flm_db.decision;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            return rs;

        } catch (Exception e) {
            System.out.println("error getResultSetDecision" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }
    
    public boolean CheckDecisionE(String decision_id) {
        try {
            String sql = "select * from decision where decision_id = ? ;  ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, decision_id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;

            }

        } catch (Exception e) {
            System.out.println("error CheckDecisionE" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return false;
    }

}
