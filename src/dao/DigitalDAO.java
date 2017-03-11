/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Digital;

/**
 *
 * @author Samuelson
 */
public class DigitalDAO {

    public void create(Digital d) throws SQLException {
        String sql = "INSERT INTO digital (nome,digital1,digital2) VALUES (?,?,?)";
        PreparedStatement ps = null;
        Connection connection = ConnectionFactory.getConnetion();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, d.getNome());
            ps.setBytes(2, d.getDigital1());
            ps.setBytes(3, d.getDigital1());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar: " + ex);
        } finally {

            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Digital> read() throws Exception {

        String sql = "select * from digital";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Digital> listaBairro = new ArrayList<Digital>();
        Connection connection = ConnectionFactory.getConnetion();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Digital bairro = new Digital();
                bairro.setId(resultSet.getInt("id"));
                bairro.setNome(resultSet.getString("nome"));
                bairro.setDigital1(resultSet.getBytes("digital1"));
                bairro.setDigital1(resultSet.getBytes("digital2"));
                listaBairro.add(bairro);
            }
            return listaBairro;
        } catch (SQLException ex) {
            Logger.getLogger(DigitalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }

        return null;

    }
}
