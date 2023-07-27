/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Plo_Po;

/**
 *
 * @author Admin
 */
public class Plo_PoDAO extends BaseDAO {
    PreparedStatement ps;
    ResultSet rs;
    

    public boolean check(String plo, String po) {
        try {
            String sql
                    = "select * FROM flm_db.po_plo pp\n"
                    + "where pp.po_id = ? and pp.plo_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, po);
            pstm.setString(2, plo);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi :" + ex.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public void deleteMappingPO_PLO(String plo, String po) {
        try {
            String sqlDetele = ""
                    + "DELETE FROM `po_plo`\n"
                    + "WHERE po_id = ? AND plo_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sqlDetele);
            connection = getConnection();
            pstm.setString(1, po);
            pstm.setString(2, plo);
            pstm.execute();
        } catch (Exception ex) {
            System.out.println("delete :" + ex.getMessage());

        } finally {
            closeConnection(connection);
        }
    }

    public void addMappingPLO_PO(String plo, String po) {

        try {
            String sqlUpdate = ""
                    + "INSERT INTO `flm_db`.`po_plo` (`po_id`, `plo_id`) VALUES (?, ?);";
            PreparedStatement pstm = connection.prepareStatement(sqlUpdate);
            connection = getConnection();
            pstm.setString(1, po);
            pstm.setString(2, plo);
            pstm.executeUpdate();

        } catch (Exception ex) {
            System.out.println("addMappingPLO_PO :" + ex.getMessage());
        } finally {
            closeConnection(connection);
        }

    }
}
