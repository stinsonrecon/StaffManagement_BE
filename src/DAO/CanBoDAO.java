package DAO;

import Connection.DBConnect;
import Model.CanBo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CanBoDAO {
    private ArrayList<CanBo> canBoList;
    private DBConnect dbConnect = new DBConnect();

    public ArrayList<CanBo> CanBoGetGall() throws SQLException {
        Connection connection = dbConnect.getConnection();
        canBoList = new ArrayList<>();
        String select = "select * from CanBo";
        PreparedStatement pe = connection.prepareStatement(select);
        ResultSet resultSet = pe.executeQuery();
        while (resultSet.next()){
            int IdCanBo = resultSet.getInt("IdCanBo");
            String ten = resultSet.getString("Ten");
            String gioiTinh = resultSet.getString("GioiTinh");
            int tuoi = resultSet.getInt("Tuoi");
            String diaChi = resultSet.getString("DiaChi");
            String SDT = resultSet.getString("SDT");
            String email = resultSet.getString("Email");
            CanBo canBo = new CanBo(IdCanBo,ten,gioiTinh,tuoi,diaChi,SDT,email);
            canBoList.add(canBo);
        }
        connection.close();
        return canBoList;
    }

    public CanBo CanBoGetById(int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        canBoList = new ArrayList<>();
        String select = "select * from CanBo where IdCanBo = " + IdCanBo;
        PreparedStatement pe = connection.prepareStatement(select);
        ResultSet resultSet = pe.executeQuery();
        while (resultSet.next()){
            int idCanBo = resultSet.getInt("IdCanBo");
            String ten = resultSet.getString("Ten");
            String gioiTinh = resultSet.getString("GioiTinh");
            int tuoi = resultSet.getInt("Tuoi");
            String diaChi = resultSet.getString("DiaChi");
            String SDT = resultSet.getString("SDT");
            String email = resultSet.getString("Email");
            CanBo canBo = new CanBo(idCanBo,ten,gioiTinh,tuoi,diaChi,SDT,email);
            connection.close();
            return canBo;
        }
        connection.close();
        return null;
    }

    public void CanBoInsert(String ten,
                            String gioiTinh,
                            int tuoi,
                            String diaChi,
                            String SDT,
                            String email) throws SQLException{
        Connection connection = dbConnect.getConnection();
        String insert = "insert into CanBo (Ten, GioiTinh, Tuoi, DiaChi, SDT, Email) " +
                        "value ('" + ten + " '" +
                ", '" + gioiTinh + " '" +
                ", " + tuoi + "" +
                ", '" + diaChi + " '" +
                ", '" + SDT + " '" +
                ", '" + email + " ')";
        PreparedStatement pe = connection.prepareStatement(insert);
        pe.execute();
        connection.close();
    }

    public void CanBoUpdate(String ten, String gioiTinh, int tuoi, String dicChi, String SDT, String email, int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String update = "update CanBo set Ten = '" + ten +
                "', GioiTinh = '" + gioiTinh +
                "', Tuoi = " + tuoi +
                ", DiaChi = '" + dicChi +
                "', SDT = '" + SDT +
                "', Email = '" + email +
                "' where IdCanBo = " + IdCanBo + ";";
        PreparedStatement pe = connection.prepareStatement(update);
        pe.execute();
        connection.close();
    }
    
    public int LastestIdCanBo() throws SQLException {
        Connection connection = dbConnect.getConnection();
        String select = "select max(IdCanBo) from CanBo";
        PreparedStatement pe = connection.prepareStatement(select);
        ResultSet resultSet = pe.executeQuery();
        while (resultSet.next()) {
            int IdCanBo = resultSet.getInt("max(IdCanBo)");
            return IdCanBo;
        }
        return -1;
    }
    
}
