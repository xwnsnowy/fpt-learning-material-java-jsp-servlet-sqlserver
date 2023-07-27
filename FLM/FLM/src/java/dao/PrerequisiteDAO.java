/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Prerequisite;

/**
 *
 * @author user
 */
public class PrerequisiteDAO extends BaseDAO {

    public ArrayList<Prerequisite> getListPrerequisiteSubjectByCode(String code) {
        ArrayList<Prerequisite> listListPrerequisiteSubjectByCode = new ArrayList<>();
        try {
            String strSelect = "SELECT prerequisite.prerequisite_id, prerequisite.curriculum_id, prerequisite.subject_id, prerequisite.is_active, prerequisite.description, \n"
                    + " prerequisite.created_at, prerequisite.created_by, prerequisite.updated_at, prerequisite.updated_by\n"
                    + "FROM flm_db.prerequisite join flm_db.`subject` on \n"
                    + "`subject`.subject_id = prerequisite.subject_id \n"
                    + "WHERE `subject`.subject_code = ? and `subject`.subject_is_active = '1';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listListPrerequisiteSubjectByCode.add(new Prerequisite(
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }
            return listListPrerequisiteSubjectByCode;
        } catch (Exception e) {
            System.out.println("error getListPrerequisiteSubjectByCode " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Prerequisite> getListSuccessorsSubjectByCode(String code) {
        ArrayList<Prerequisite> listListSuccessorsSubjectByCode = new ArrayList<>();
        try {
            String strSelect = "SELECT prerequisite.prerequisite_id, prerequisite.curriculum_id, prerequisite.subject_id, prerequisite.is_active, prerequisite.description, \n"
                    + "prerequisite.created_at, prerequisite.created_by, prerequisite.updated_at, prerequisite.updated_by \n"
                    + "FROM flm_db.prerequisite join flm_db.`subject` on \n"
                    + "`subject`.subject_id = prerequisite.prerequisite_id \n"
                    + "WHERE `subject`.subject_code = ? and `subject`.subject_is_active = '1';";
            connection = getConnection();

            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listListSuccessorsSubjectByCode.add(new Prerequisite(
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }
            return listListSuccessorsSubjectByCode;
        } catch (Exception e) {
            System.out.println("error getListSuccessorsSubjectByCode " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public boolean checkPrerequisiteSubjectBySubjectId(String subjectId) {
        try {
            String strSelect = "SELECT * FROM flm_db.prerequisite "
                    + "WHERE subject_id = ? and is_active = '1';";
            connection = getConnection();

            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, subjectId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkPrerequisiteSubjectBySubjectId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public boolean checkSuccessorsSubjectBySubjectId(String subjectId) {
        try {
            String strSelect = "SELECT * FROM flm_db.prerequisite\n"
                    + "WHERE prerequisite_id = ? \n"
                    + "and is_active = '1';";
            connection = getConnection();

            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, subjectId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkSuccessorsSubjectBySubjectId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

}
