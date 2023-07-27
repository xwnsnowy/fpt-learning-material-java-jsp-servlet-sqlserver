/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Question;

/**
 *
 * @author hp
 */
public class QuestionDAO extends BaseDAO {

    public ArrayList<Question> getListQuestion() {
        ArrayList<Question> listQuestion = new ArrayList<>();
        try {
            String strSelect = "select * from question \n"
                    + "";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listQuestion.add(new Question(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            return listQuestion;
        } catch (Exception e) {
            System.out.println("getListQuestion() " + e.getMessage());

            return null;
        }finally {
            closeConnection(connection);
        }

    }

   

    public ArrayList<Question> getListQuestionSearch(String searchString) {
        ArrayList<Question> listQuestion = new ArrayList<>();
        try {
            String strSelect = "select * from question \n"
                    + " where name like ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + searchString + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listQuestion.add(new Question(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            return listQuestion;
        } catch (Exception e) {
            System.out.println("getListQuestionSearch() " + e.getMessage());

            return null;
        } finally {
            closeConnection(connection);
        }

    }

    public Question getQuestion(String id) {
       try {
            String sql = "select * from question where question_id = ? ;  ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Question d = new Question(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4));
                
                return d;

            }

        } catch (Exception e) {
            System.out.println("error getQuestion" + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public void UpdateQuestion(Question q) {
        try {
            String strInsert = "UPDATE `flm_db`.`question` SET `name` = ?, `details` = ? WHERE (`question_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strInsert);

            pstm.setString(1, q.getName());
            pstm.setString(2, q.getDetail());
            pstm.setInt(3, q.getQuestion_id());
            

            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("UpdateQuestion " + e.getMessage());
        }finally {
            closeConnection(connection);
        }

    }

}
