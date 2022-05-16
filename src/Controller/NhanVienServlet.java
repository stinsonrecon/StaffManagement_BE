package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.google.gson.Gson;

import Common.Common;
import DAO.CanBoDAO;
import DAO.NhanVienDAO;
import Model.CanBo;
import Model.NhanVien;


@WebServlet("/nhanvien")
public class NhanVienServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ID = request.getParameter("ID");
		Gson gson = new Gson();
		NhanVienDAO nhanVienDao = new NhanVienDAO();
		ArrayList<NhanVien> nhanVienList = new ArrayList<>();
		PrintWriter out = response.getWriter();
		
		if(ID == null) {
			try {
				nhanVienList = nhanVienDao.NhanVienGetGall();
				String result = gson.toJson(nhanVienList);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		
		int idNhanVienCanTim = Integer.parseInt(request.getParameter("ID"));
		
		
		try {
			NhanVien nhanVien = nhanVienDao.NhanVienGetById(idNhanVienCanTim);
			String result = gson.toJson(nhanVien);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(result);
			System.out.print(result);
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
		
		/*
		ArrayList<NhanVien> nhanVienList = new ArrayList<>();
		Gson gson = new Gson();
		NhanVienDAO nhanVienDao = new NhanVienDAO();
		try {
			nhanVienList = nhanVienDao.NhanVienGetGall();
			String result = gson.toJson(nhanVienList);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(result);
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		NhanVien nhanVien = gson.fromJson(result, NhanVien.class);
		NhanVienDAO nhanVienDao = new NhanVienDAO();
		try {
			nhanVienDao.NhanVienInsert(nhanVien.getCongViec(),nhanVien.getIdCanBo());
			out.write("Insert thanh cong");
		}
		catch(SQLException e) {
			out.write("Insert that bai");
		}
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		
		String key = request.getParameter("UpdateCongViec");
		boolean key1 = Boolean.parseBoolean(key);
		
		NhanVienDAO nhanVienDao = new NhanVienDAO();
		
		if(key1) {
		String congViecDoi = request.getParameter("CongViec");
		int idCanBoMuonDoi = Integer.parseInt(request.getParameter("IdCanBo"));
		
		
		try {
			nhanVienDao.NhanVienUpdate(congViecDoi, idCanBoMuonDoi);
			out.write("Update thanh cong");
		}
		catch(SQLException e ) {
			out.write("Update that bai");
		}
	}
		else {
			int idCanBoCu = Integer.parseInt(request.getParameter("IdCanBo"));
			int idCanBoMoi = Integer.parseInt(request.getParameter("IdCanBoMoi"));
			try {
				nhanVienDao.UpdateThongTinNV(idCanBoMoi, idCanBoCu);
				out.write("Update thanh cong");
			}
			catch(SQLException e ) {
				out.write("Update that bai");
			}
		}
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		NhanVien nhanVien = gson.fromJson(result, NhanVien.class);
		NhanVienDAO nhanVienDao = new NhanVienDAO();
		try {
			nhanVienDao.DeleteNhanVien(nhanVien.getIdCanBo());
			out.write("Delete thanh cong");
		}
		catch(SQLException e) {
			out.write("Delete that bai");
		}
	}
}
