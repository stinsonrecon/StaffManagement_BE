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
import DAO.KySuDAO;
import DAO.NhanVienDAO;
import Model.KySu;
import Model.NhanVien;

@WebServlet("/kysu")
public class KySuServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ID = request.getParameter("ID");
		Gson gson = new Gson();
		KySuDAO kySuDao = new KySuDAO();
		ArrayList<KySu> kySuList = new ArrayList<>();
		PrintWriter out = response.getWriter();
		
		if(ID == null) {
			try {
				kySuList = kySuDao.KySuGetAll();
				String result = gson.toJson(kySuList);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		
		int idKySuCanTim = Integer.parseInt(request.getParameter("ID"));
		
		
		try {
			KySu kySu = kySuDao.KySuGetById(idKySuCanTim);
			String result = gson.toJson(kySu);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(result);
			System.out.print(result);
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		KySu kySu = gson.fromJson(result, KySu.class);
		KySuDAO kySuDao = new KySuDAO();
		try {
			kySuDao.KySuInsert(kySu.getNganhDaoTao(),kySu.getIdCanBo());
			out.write("Insert thanh cong");
		}
		catch(SQLException e) {
			out.write("Insert that bai");
		}
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		
		
		String key = request.getParameter("UpdateNganhDaoTao");
		boolean key1 = Boolean.parseBoolean(key);
		
		KySuDAO kySuDao = new KySuDAO();
		
		if(key1) {
		String nganhDaoTaoDoi = request.getParameter("NganhDaoTao");
		int idCanBoMuonDoi = Integer.parseInt(request.getParameter("IdCanBo"));
		
		
		try {
			kySuDao.UpdateKySu(nganhDaoTaoDoi, idCanBoMuonDoi);
			out.write("Update thanh cong");
		}
		catch(SQLException e ) {
			out.write("Update that bai");
		}
	}
		else {
			int idCanBoCu = Integer.parseInt(request.getParameter("IdCanBoCu"));
			int idCanBoMoi = Integer.parseInt(request.getParameter("IdCanBoMoi"));
			try {
				kySuDao.UpdateThongTinKS(idCanBoMoi, idCanBoCu);
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
		
		KySu kySu = gson.fromJson(result, KySu.class);
		KySuDAO kySuDao = new KySuDAO();
		try {
			kySuDao.DeleteKySu(kySu.getIdCanBo());
			out.write("Delete thanh cong");
		}
		catch(SQLException e) {
			out.write("Delete that bai");
		}
	}
}
