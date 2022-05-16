package DAO;

import Connection.DBConnect;
import Model.CanBo;
import Model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {
    private ArrayList<NhanVien> nhanVienList;
    private DBConnect dbConnect = new DBConnect();

    public ArrayList<NhanVien> NhanVienGetGall() throws SQLException {
        Connection connection = dbConnect.getConnection();
        nhanVienList = new ArrayList<>();
        String select = "select * from NhanVien";
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
            String congViec = resultSet.getString("CongViec");
            NhanVien nhanVien = new NhanVien(IdCanBo,ten,gioiTinh,tuoi,diaChi,SDT,email,congViec);
            nhanVienList.add(nhanVien);
        }
        connection.close();
        return nhanVienList;
    }

    public NhanVien NhanVienGetById(int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String select = "select * from NhanVien where IdPREFIX = 'NV' and IdCanBo = " + IdCanBo;
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
            String congViec = resultSet.getString("CongViec");
            NhanVien nhanVien = new NhanVien(IdCanBo,ten,gioiTinh,tuoi,diaChi,SDT,email,congViec);
            connection.close();
            return nhanVien;
        }
        connection.close();
        return null;
    }

    public void NhanVienInsert(String congViec, int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String insert = "insert into NhanVien(IdCanBo, CongViec) " +
                "value ((select IdCanBo from CanBo where IdCanBo = " + IdCanBo + "), '" + congViec + "')";
        PreparedStatement pe = connection.prepareStatement(insert);
        pe.execute();
        connection.close();
    }

    public void NhanVienUpdate(String congViec, int IdCanBo) throws SQLException {
        Connection connection = dbConnect.getConnection();
        String update = "update NhanVien set CongViec = '" + congViec + "' where IdCanBo = " + IdCanBo;
        PreparedStatement pe = connection.prepareStatement(update);
        pe.execute();
        connection.close();
    }
    
    public void UpdateThongTinNV( int IdCanBoMoi, int IdCanBoCu) throws SQLException {
    	Connection connection = dbConnect.getConnection();
    	String update = "update NhanVien set IdCanBo =  (select IdCanBo from CanBo where IdCanBo = " + IdCanBoMoi + " ) where IdCanBo = " + IdCanBoCu + ";"; 
    	PreparedStatement pe = connection.prepareStatement(update);
    	pe.execute();
    	connection.close();
    }
    
    public void DeleteNhanVien( int IdCanBo) throws SQLException{
    	Connection connection = dbConnect.getConnection();
    	String deleteNhanVien = "delete from NhanVien where IdCanBo = " + IdCanBo;
    	String deleteCanBo = "delete from CanBo where IdCanBo = " + IdCanBo;
    	PreparedStatement pe = connection.prepareStatement(deleteNhanVien);
    	PreparedStatement pe1 = connection.prepareStatement(deleteCanBo);
    	pe.execute();
    	pe1.execute();
    	connection.close();
    	
    }
}
