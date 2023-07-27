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
public class MappingClo_PloDAO extends BaseDAO {
    PreparedStatement ps;
    ResultSet rs;
    public boolean check(String plo, String clo) {
        try {
            String sql
                    = "SELECT * FROM flm_db.clo_plo cp\n"
                    + "where cp.clo_id = ? and cp.plo_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            
            pstm.setString(1, clo);
            pstm.setString(2, plo);
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
    public void deleteMappingPLO_CLO(String plo, String clo) {
        try {
            String sqlDetele = ""
                    + "delete from flm_db.clo_plo cp where cp.clo_id = ? and  cp.plo_id = ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sqlDetele);
            pstm.setString(1, clo);
            pstm.setString(2, plo);
            pstm.execute();

        } catch (Exception ex) {
            System.out.println("delete :" + ex.getMessage());

        }  finally {
            closeConnection(connection);
        }
    }

    public void addMappingPLO_CLO(String plo, String clo) {

        try {
            String sqlUpdate = ""
                    + "INSERT INTO `flm_db`.`clo_plo` (`clo_id`, `plo_id`) VALUES (?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sqlUpdate);
            pstm.setString(1, clo);
            pstm.setString(2, plo);
            pstm.executeUpdate();

        } catch (Exception ex) {
            System.out.println("addMappingPLO_PO :" + ex.getMessage());
        }  finally {
            closeConnection(connection);
        }
    }
}
