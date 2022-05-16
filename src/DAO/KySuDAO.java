package DAO;

import Connection.DBConnect;
import Model.CanBo;
import Model.KySu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KySuDAO {
    private ArrayList<KySu> kySuList;
    private DBConnect dbConnect = new DBConnect();

    public ArrayList<KySu> KySuGetAll() throws SQLException {
        Connection connection = dbConnect.getConnection();
        kySuList = new ArrayList<>();
        String select = "select * from KySu";
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
            String nganhDaoTao = resultSet.getString("NganhDaoTao");
            KySu kySu = new KySu(IdCanBo,ten,gioiTinh,tuoi,diaChi,SDT,email, nganhDaoTao);
            kySuList.add(kySu);
        }
        connection.close();
        return kySuList;
    }

    public KySu KySuGetById(int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String select = "select * from KySu where IdPREFIX = 'KS' and IdCanBo = " + IdCanBo;
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
            String nganhDaoTao = resultSet.getString("NganhDaoTao");
            KySu kySu = new KySu(IdCanBo,ten,gioiTinh,tuoi,diaChi,SDT,email,nganhDaoTao);
            connection.close();
            return kySu;
        }
        connection.close();
        return null;
    }

    public void KySuInsert(String nganhDaoTao, int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String insert = "insert into KySu(IdCanBo, NganhDaoTao) " +
                "value ((select IdCanBo from CanBo where IdCanBo = " + IdCanBo + "), '" + nganhDaoTao + "')";
        PreparedStatement pe = connection.prepareStatement(insert);
        pe.execute();
        connection.close();
    }

    public void UpdateKySu(String NganhDaotao, int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String update = "update KySu set NganhDaoTao = '" + NganhDaotao + "' where IdCanBo = " + IdCanBo; 
        PreparedStatement pe = connection.prepareStatement(update);
        pe.execute();
        connection.close();
    }
    
    public void UpdateThongTinKS(int IdCanBoCu, int IdCanBoMoi) throws SQLException {
    	Connection connection = dbConnect.getConnection();
    	String update = "update KySu set IdCanBo = (select IdCanBo from CanBo where IdCanBo = " + IdCanBoMoi + ") where IdCanBo = " + IdCanBoCu;
    	PreparedStatement pe = connection.prepareStatement(update);
    	pe.execute();
    	connection.close();
    }
    
    public void DeleteKySu( int IdCanBo) throws SQLException{
    	Connection connection = dbConnect.getConnection();
    	String deleteKySu = "delete from KySu where IdCanBo = " + IdCanBo;
    	String deleteCanBo = "delete from CanBo where IdCanBo = " + IdCanBo;
    	PreparedStatement pe = connection.prepareStatement(deleteKySu);
    	PreparedStatement pe1 = connection.prepareStatement(deleteCanBo);
    	pe.execute();
    	pe1.execute();
    	connection.close();
    	
    }
}
