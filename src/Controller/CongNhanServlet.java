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
import DAO.CongNhanDAO;
import DAO.KySuDAO;
import Model.CongNhan;
import Model.KySu;

@WebServlet("/congnhan")
public class CongNhanServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ID = request.getParameter("ID");
		Gson gson = new Gson();
		CongNhanDAO congNhanDao = new CongNhanDAO();
		ArrayList<CongNhan> congNhanList = new ArrayList<>();
		PrintWriter out = response.getWriter();
		
		if(ID == null) {
			try {
				congNhanList = congNhanDao.CongNhanGetGall();
				String result = gson.toJson(congNhanList);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		
		int idCongNhanCanTim = Integer.parseInt(request.getParameter("ID"));
		
		
		try {
			CongNhan congNhan = congNhanDao.CongNhanGetById(idCongNhanCanTim);
			String result = gson.toJson(congNhan);
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

		CongNhan congNhan = gson.fromJson(result, CongNhan.class);
		CongNhanDAO congNhanDao = new CongNhanDAO();
		try {
			congNhanDao.CongNhanInsert(congNhan.getCapBac(), congNhan.getIdCanBo());
			out.write("Insert thanh cong");
		} catch (SQLException e) {
			out.write("Insert that bai");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
	

		String key = request.getParameter("UpdateCapBac");
		boolean key1 = Boolean.parseBoolean(key);

		
		

		CongNhanDAO congNhanDao = new CongNhanDAO();

		if (key1) {
			String capBacDoi = request.getParameter("CapBac");
			int idCanBoMuonDoi = Integer.parseInt(request.getParameter("IdCanBo"));

			try {
				congNhanDao.CongNhanUpdate(capBacDoi, idCanBoMuonDoi);
				out.write("Update thanh cong");
			} catch (SQLException e) {
				out.write("Update that bai");
			}
		} else {
			int idCanBoCu = Integer.parseInt(request.getParameter("IdCanBo"));
			int idCanBoMoi = Integer.parseInt(request.getParameter("IdCanBoMoi"));
			try {
				congNhanDao.UpdateThongTinCN(idCanBoMoi, idCanBoCu);
				out.write("Update thanh cong");
			} catch (SQLException e) {
				out.write("Update that bai");
			}
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);

		CongNhan congNhan = gson.fromJson(result, CongNhan.class);
		CongNhanDAO congNhanDao = new CongNhanDAO();
		try {
			congNhanDao.DeleteCongNhan(congNhan.getIdCanBo());
			out.write("Delete thanh cong");
		} catch (SQLException e) {
			out.write("Delete that bai");
		}
	}
}
