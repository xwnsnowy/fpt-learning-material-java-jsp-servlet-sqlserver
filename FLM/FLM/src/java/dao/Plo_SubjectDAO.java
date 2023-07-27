/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class Plo_SubjectDAO extends BaseDAO {

    public boolean check(String plo, String suject) {
        try {
            String sql
                    = "select * FROM flm_db.plo_subject ps\n"
                    + "where ps.plo_id = ? and ps.subject_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, plo);
            pstm.setString(2, suject);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("check :" + ex.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public void deleteMappingPLO_Subject(String plo, String Ccode, String subjectid) {
        try {
            String sqlDetele = "DELETE FROM `flm_db`.`plo_subject` WHERE (`plo_id` = ?) and (`curriculum_id` = "
                    + "(select cc.curriculum_id from flm_db.curriculum cc where cc.code = ?\n"
                    + ")) and (`subject_id` =?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sqlDetele);

            pstm.setString(1, plo);
            pstm.setString(2, Ccode);
            pstm.setString(3, subjectid);
            pstm.execute();

        } catch (Exception ex) {
            System.out.println("deleteMappingPLO_Subject :" + ex.getMessage());

        }finally {
            closeConnection(connection);
        }
    }

    public void addMappingPLO_Subject(String plo, String subjectid, String Ccode) {

        try {
            String sqlUpdate = ""
                    + "INSERT INTO `flm_db`.`plo_subject` (`plo_id`, `curriculum_id`, `subject_id`)\n"
                    + "SELECT ?, `curriculum_id`, ?\n"
                    + "FROM flm_db.curriculum cc\n"
                    + "WHERE cc.code = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sqlUpdate);
            pstm.setString(1, plo);
            pstm.setString(2, subjectid);
            pstm.setString(3, Ccode);
            pstm.executeUpdate();

        } catch (Exception ex) {
            System.out.println("addMappingPLO_Subject :" + ex.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
}
