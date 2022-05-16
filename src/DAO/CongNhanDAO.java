package DAO;

import Connection.DBConnect;
import Model.CanBo;
import Model.CongNhan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CongNhanDAO {
    private ArrayList<CongNhan> congNhanList;
    private DBConnect dbConnect = new DBConnect();

    public ArrayList<CongNhan> CongNhanGetGall() throws SQLException {
        Connection connection = dbConnect.getConnection();
        congNhanList = new ArrayList<>();
        String select = "select * from CongNhan";
        PreparedStatement pe = connection.prepareStatement(select);
        ResultSet resultSet = pe.executeQuery();
        CanBoDAO canBoDAO = new CanBoDAO();
        while (resultSet.next()) {
            String IdPREFIX = resultSet.getString("IdPREFIX");
            int IdCanBo = resultSet.getInt("IdCanBo");
            CanBo canBo = canBoDAO.CanBoGetById(IdCanBo);
            String ten = canBo.getTen();
            String gioiTinh = canBo.getGioiTinh();
            int tuoi = canBo.getTuoi();
            String diaChi = canBo.getDiaChi();
            String SDT = canBo.getSDT();
            String email = canBo.getEmail();
            String CapBac = resultSet.getString("CapBac");
            CongNhan congNhan = new CongNhan(IdCanBo, ten, gioiTinh,tuoi,diaChi,SDT,email, CapBac);
            congNhanList.add(congNhan);
            
        }
        connection.close();
        return congNhanList;
    }

    public CongNhan CongNhanGetById(int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String select = "select * from CongNhan where IdPREFIX = 'CN' and IdCanBo = " + IdCanBo;
        PreparedStatement pe = connection.prepareStatement(select);
        ResultSet resultSet = pe.executeQuery();
        CanBoDAO canBoDAO = new CanBoDAO();
        CanBo canBo = canBoDAO.CanBoGetById(IdCanBo);
        String ten = canBo.getTen();
        String gioiTinh = canBo.getGioiTinh();
        int tuoi = canBo.getTuoi();
        String diaChi = canBo.getDiaChi();
        String SDT = canBo.getSDT();
        String email = canBo.getEmail();
        while (resultSet.next()) {
            String IdPREFIX = resultSet.getString("IdPREFIX");
            String capBac = resultSet.getString("CapBac");
            CongNhan congNhan = new CongNhan(IdCanBo,ten,gioiTinh,tuoi,diaChi,SDT,email,capBac);
            connection.close();
            return congNhan;
        }
        connection.close();
        return null;
    }

    public void CongNhanInsert(String CapBac, int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String insert = "insert into CongNhan(IdCanBo, CapBac) " +
                        "value ((select IdCanBo from CanBo where IdCanBo = " + IdCanBo + "), '" + CapBac + "')";
        PreparedStatement pe = connection.prepareStatement(insert);
        pe.execute();
        connection.close();
    }

    public void CongNhanUpdate(String CapBac, int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String update = "update CongNhan set CapBac = '" + CapBac + "' where IdCanBo = " + IdCanBo; 
        PreparedStatement pe = connection.prepareStatement(update);
        pe.execute();
        connection.close();
    }
    
    public void UpdateThongTinCN(int IdCanBoCu, int IdCanBoMoi) throws SQLException {
    	Connection connection = dbConnect.getConnection();
    	String update = "update CongNhan set IdCanBo = (select IdCanBo from CanBo where IdCanBo = " + IdCanBoMoi + ") where IdCanBo = " + IdCanBoCu;
    	PreparedStatement pe = connection.prepareStatement(update);
    	pe.execute();
    	connection.close();
    }
    
    public void DeleteCongNhan( int IdCanBo) throws SQLException{
    	Connection connection = dbConnect.getConnection();
    	String deleteCongNhan = "delete from CongNhan where IdCanBo = " + IdCanBo;
    	String deleteCanBo = "delete from CanBo where IdCanBo = " + IdCanBo;
    	PreparedStatement pe = connection.prepareStatement(deleteCongNhan);
    	PreparedStatement pe1 = connection.prepareStatement(deleteCanBo);
    	pe.execute();
    	pe1.execute();
    	connection.close();
    	
    }
}
