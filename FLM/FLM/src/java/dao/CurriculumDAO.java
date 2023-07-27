/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Curriculum;

/**
 *
 * @author hp
 */
public class CurriculumDAO extends BaseDAO {
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Curriculum> searchListCurriculumByName_1(String name, String owner_id) {

        ArrayList<Curriculum> data = new ArrayList<>();
        try {

            //Step 1:           
            String sql = "select * from curriculum c  \n"
                    + " \n"
                    + "where name like ? and owner_id = ?";
            //step 2:    
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + name + "%");
            pstm.setString(2, owner_id);
//            pstm.setString(1, code);
//            System.out.println("run5");
            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                Curriculum c = new Curriculum(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBoolean(8));
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("searchListCurriculum_1()" + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return data;
    }

    public ArrayList<Curriculum> searchListCurriculumByName(String name) {
        
        ArrayList<Curriculum> data = new ArrayList<>();
        try {

            //Step 1:           
            String sql = "select * from curriculum c join decision d \n"
                    + "on c.decision_id = d.decision_id \n"
                    + "where name like ? and c.is_active = 1 ";
            //step 2:  
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + name + "%");
//            pstm.setString(1, code);
//            System.out.println("run5");
            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                Curriculum c = new Curriculum(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBoolean(8), rs.getString(12), rs.getString(14));
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("searchListCurriculum()" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return data;
    }

    public ArrayList<Curriculum> searchListCurriculum(String code) {
        ArrayList<Curriculum> data = new ArrayList<>();
        try {

            //Step 1:           
            String sql = "select * from curriculum c join decision d \n"
                    + "on c.decision_id = d.decision_id \n"
                    + "where code like ? and c.is_active = 1";
            //step 2:  
             connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + code + "%");
//            pstm.setString(1, code);
//            System.out.println("run5");
            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                Curriculum c = new Curriculum(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBoolean(8), rs.getString(12), rs.getString(14));
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("searchListCurriculum()" + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return data;
    }

    public ArrayList<Curriculum> searchListCurriculum_1(String code, String owner_id) {
        ArrayList<Curriculum> data = new ArrayList<>();
        try {

            //Step 1:           
            String sql = "select * from curriculum c  \n"
                    + " \n"
                    + "where code like ? and owner_id =  ?";
            //step 2: 
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + code + "%");
            pstm.setString(2, owner_id);

            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                Curriculum c = new Curriculum(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBoolean(8));
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("searchListCurriculum_1()" + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return data;
    }

    public Curriculum getCurriculumOverview(String Ccode) {
        Curriculum c = new Curriculum();
        try {
            String sql = "select * from curriculum c left join decision d \n"
                    + "on c.decision_id = d.decision_id \n"
                    + "where code like ? ";
             connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                c.setCurriculum_id(rs.getString(1));
                c.setCode(rs.getString(2));
                c.setName(rs.getString(3));
                c.setDescription(rs.getString(4));
                c.setDecision_id(rs.getString(5));
                c.setTotal_credit(rs.getInt(6));
                c.setOwner_id(rs.getString(7));
                c.setIs_active(rs.getBoolean(8));
                c.setEnglish_name(rs.getString(9));
                c.setDecision_no(rs.getString(12));
                c.setDecision_date(rs.getString(14));
            }

        } catch (Exception e) {
            System.out.println("getCurriculumOverview" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return c;
    }

    public String NumberOfRow(String code) {

        try {
            String sql = "select count(*)\n"
                    + "from curriculum c join decision d \n"
                    + "on c.decision_id = d.decision_id \n"
                    + "where code like ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + code + "%");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String count = rs.getString(1);
                return count;
            }

        } catch (Exception e) {
            System.out.println("getCurriculumOverview" + e.getMessage());

        } finally {
            closeConnection(connection);
        }

        return null;
    }

    public String NumberOfRow1(String code, String owner_id) {

        try {
            String sql = "select count(*)\n"
                    + "from curriculum c \n"
                    + "where code like ? and owner_id = ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + code + "%");
            pstm.setString(2, owner_id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String count = rs.getString(1);
                return count;
            }

        } catch (Exception e) {
            System.out.println("getCurriculumOverview" + e.getMessage());

        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public String NumberOfRowName(String name) {

        try {
            String sql = "select count(*)\n"
                    + "from curriculum c join decision d \n"
                    + "on c.decision_id = d.decision_id \n"
                    + "where name like ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + name + "%");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String count = rs.getString(1);
                return count;

            }

        } catch (Exception e) {
            System.out.println("getCurriculumOverview" + e.getMessage());

        }  finally {
            closeConnection(connection);
        }

        return null;
    }

    public Curriculum getCurriculumById(String id) {
        Curriculum c = new Curriculum();
        try {
            String sql = "select * from curriculum c "
                    + " \n"
                    + "where curriculum_id =?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                c.setCurriculum_id(rs.getString(1));
                c.setCode(rs.getString(2));
                c.setName(rs.getString(3));
                c.setDescription(rs.getString(4));
                c.setDecision_id(rs.getString(5));
                c.setTotal_credit(rs.getInt(6));
                c.setOwner_id(rs.getString(7));
                c.setIs_active(rs.getBoolean(8));
                c.setEnglish_name(rs.getString(9));

            }
        } catch (Exception e) {
            System.out.println("error getCurriculumById" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return c;
    }

    public void InsertCurriculum(Curriculum c) {

        try {
            String strInsert = "INSERT INTO `flm_db`.`curriculum` (`curriculum_id`,`code`, `name`, `description`, `is_active`,`english_name` , `owner_id`,`create_id`)"
                    + " VALUES ( ?,?, ?, ?, ?, ?,?,?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strInsert);
            pstm.setString(1, c.getCurriculum_id());
            pstm.setString(2, c.getCode());
            pstm.setString(3, c.getName());
            pstm.setString(4, c.getDescription());
            pstm.setBoolean(5, c.getIs_active());
            pstm.setString(6, c.getEnglish_name());
            pstm.setString(7, c.getOwner_id());
            pstm.setString(8, c.getCreate_id());
            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("InsertCurriculum " + e.getMessage());
        }finally {
            closeConnection(connection);
        }

    }

    public Curriculum CheckCurriculumByID(int id) {
        try {
            String sql = "select * from curriculum where curriculum_id = ?  ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Curriculum c = new Curriculum(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getBoolean(8));
                System.out.println("run");
                return c;

            }

        } catch (Exception e) {
            System.out.println("error CheckCurriculumByID" + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return null;
    }

    public void updateCurriculum(Curriculum c) {
        try {
            String strInsert = "UPDATE `flm_db`.`curriculum` SET `code` = ?, `name` = ?, `description` = ?, \n"
                    + " `is_active` = ? , `english_name` = ?\n"
                    + "WHERE (`curriculum_id` = ?)";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strInsert);
            
            pstm.setString(1, c.getCode());
            pstm.setString(2, c.getName());
            pstm.setString(3, c.getDescription());
            pstm.setBoolean(4, c.getIs_active());
            pstm.setString(5, c.getEnglish_name());
            pstm.setString(6, c.getCurriculum_id());
            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("updateCurriculum " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

    }

    public ArrayList<Curriculum> getListCurriculum() {
        ArrayList<Curriculum> listCurriculum = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM flm_db.curriculum;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listCurriculum.add(new Curriculum(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getBoolean(8)));
            }
            return listCurriculum;
        } catch (Exception e) {
            System.out.println("error getListCurriculum " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return null;
    }

    public Curriculum getCurriculumCodeByCurriculumId(String curriculumidcombo) {
        try {
            String strSelect = "SELECT curriculum_id, code FROM flm_db.curriculum "
                    + "WHERE curriculum_id=?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumidcombo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Curriculum(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error getCurriculumCodeByCurriculumId: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public Curriculum CheckCurriculumByID(String code) {
        try {
            String sql = "select * from curriculum where code = ?  ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, code);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Curriculum c = new Curriculum(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getBoolean(8));
                System.out.println("run");
                return c;

            }

        } catch (Exception e) {
            System.out.println("error CheckCurriculumByID" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public int getMaxCurriculumId() {
        try {
            String sql = "SELECT MAX(curriculum_id) from curriculum;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int MaxId = rs.getInt(1);
                return MaxId;
            }

        } catch (Exception e) {
            System.out.println("getMaxCurriculumId()" + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return 0;
    }

    public ArrayList<Curriculum> getListCurriculumWhereNot(String curriculumid) {
        ArrayList<Curriculum> listCurriculum = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM flm_db.curriculum\n"
                    + "WHERE NOT curriculum_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listCurriculum.add(new Curriculum(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getBoolean(8)));
            }
            return listCurriculum;
        } catch (Exception e) {
            System.out.println("error getListCurriculum " + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public ArrayList<Curriculum> getCurriculumCodeBySubjectCode(String subject_code) {
        ArrayList<Curriculum> listCurriculum = new ArrayList<>();
        try {
            String strSelect = "select  c.curriculum_id, c.code from flm_db.subject s join flm_db.curriculum_subject cs\n"
                    + "on s.subject_id = cs.subject_id\n"
                    + "join flm_db.curriculum c on c.curriculum_id = cs.curriculum_id\n"
                    + "where s.subject_code = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, subject_code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listCurriculum.add(new Curriculum(rs.getString(1), rs.getString(2)));
            }
            return listCurriculum;
        } catch (Exception e) {
            System.out.println("getCurriculumCodeBySubjectName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return null;
    }

    public String NumberOfRowName1(String name, String userId) {
        try {
            String sql = "select count(*)\n"
                    + "from curriculum c  \n"
                    + "where name like ? and owner_id = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + name + "%");
            pstm.setString(2, userId);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String count = rs.getString(1);
                return count;

            }

        } catch (Exception e) {
            System.out.println("getCurriculumOverview" + e.getMessage());

        } finally {
            closeConnection(connection);
        }

        return null;
    }

    public ArrayList<Curriculum> getListCurriculumNullDecision() {
        ArrayList<Curriculum> listCurriculum = new ArrayList<>();
        try {
            String strSelect = "select * from curriculum \n"
                    + "where decision_id is null";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listCurriculum.add(new Curriculum(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10)));
            }
            return listCurriculum;
        } catch (Exception e) {
            System.out.println("getListCurriculumNullDecision() " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return null;

    }

    public void updateDecisionForCurriculum(String curriculum_id, String decision_id) {
        try {
            String strInsert = "UPDATE `flm_db`.`curriculum` SET `decision_id` = ? WHERE (`curriculum_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strInsert);

            pstm.setString(1, decision_id);
            pstm.setString(2, curriculum_id);

            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("updateDecisionForCurriculum " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

    }

    public ArrayList<Curriculum> AllCurriculum(String code) {
        ArrayList<Curriculum> data = new ArrayList<>();
        try {

            //Step 1:           
            String sql = "select * from curriculum c join user u on c.owner_id = u.user_id where code like ? ";
            //step 2:       
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + code + "%");

            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                Curriculum c = new Curriculum(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBoolean(8), rs.getString(12));
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("AllCurriculum" + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return data;
    }

    public String Number(String code) {
        try {
            String sql = "select count(*)\n"
                    + "from curriculum c \n"
                    + "where code like ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + code + "%");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String count = rs.getString(1);
                return count;
            }

        } catch (Exception e) {
            System.out.println("getCurriculumOverview" + e.getMessage());

        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public ArrayList<Curriculum> AllCurriculum_2(String name) {
        ArrayList<Curriculum> data = new ArrayList<>();
        try {

            //Step 1:           
            String sql = "select * from curriculum c join user u on c.owner_id = u.user_id  \n"
                    + " \n"
                    + "where name like ? ";
            //step 2:   
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + name + "%");

            ResultSet rs = pstm.executeQuery();
            //step 3:        
            while (rs.next()) {
                Curriculum c = new Curriculum(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBoolean(8), rs.getString(12));
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("AllCurriculum_2" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return data;
    }

    public String Number_2(String name) {
        try {
            String sql = "select count(*)\n"
                    + "from curriculum c \n"
                    + "where name like ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + name + "%");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String count = rs.getString(1);
                return count;
            }

        } catch (Exception e) {
            System.out.println("getCurriculumOverview" + e.getMessage());

        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public void AddCurriculumForStaff(Curriculum c) {
        try {
            String strInsert = "INSERT INTO `flm_db`.`curriculum` (`curriculum_id`,`code`, `name`, `description`, `is_active`,`english_name` , `owner_id`,`create_id`)"
                    + " VALUES ( ?,?, ?, ?, ?, ?,?,?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strInsert);
            pstm.setString(1, c.getCurriculum_id());
            pstm.setString(2, c.getCode());
            pstm.setString(3, c.getName());
            pstm.setString(4, c.getDescription());
            pstm.setBoolean(5, c.getIs_active());
            pstm.setString(6, c.getEnglish_name());
            pstm.setString(7, c.getOwner_id());
            pstm.setString(8, c.getCreate_id());
            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("AddCurriculumForStaff" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

    }

    public String getTotalCredit(String cid) {

        try {
            String strSelect = "SELECT cs.curriculum_id,c.code,SUM(no_credit) AS total\n"
                    + "FROM curriculum_subject cs join curriculum c\n"
                    + "on cs.curriculum_id = c.curriculum_id\n"
                    + "GROUP by cs.curriculum_id\n"
                    + "having c.code = ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String total = rs.getString(3);
                return total;
            }
        } catch (Exception e) {
            System.out.println("getTotalCredit" + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return null;
    }
}
