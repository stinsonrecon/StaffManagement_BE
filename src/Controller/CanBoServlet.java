package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

import DAO.CanBoDAO;

import Model.CanBo;
import Common.Common;


@WebServlet("/canbo")
public class CanBoServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		ArrayList<CanBo> canBoList = new ArrayList<>();
		Gson gson = new Gson();
		CanBoDAO canBoDao = new CanBoDAO();
		try {
			canBoList = canBoDao.CanBoGetGall();
			String result = gson.toJson(canBoList);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		String ID = request.getParameter("ID");
		Gson gson = new Gson();
		CanBoDAO canBoDao = new CanBoDAO();
		ArrayList<CanBo> canBoList = new ArrayList<>();
		PrintWriter out = response.getWriter();
		
		if(ID == null) {
			try {
				canBoList = canBoDao.CanBoGetGall();
				String result = gson.toJson(canBoList);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		
		int idCanBoCanTim = Integer.parseInt(request.getParameter("ID"));
		
		
		try {
			CanBo canBo = canBoDao.CanBoGetById(idCanBoCanTim);
			String result = gson.toJson(canBo);
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
		/*
		PrintWriter out = response.getWriter();
		try {
		String ten = request.getParameter("Ten");
		String gioiTinh = request.getParameter("GioiTinh");
		int tuoi = Integer.parseInt(request.getParameter("Tuoi"));
		String diaChi = request.getParameter("DiaChi");
		String SDT = request.getParameter("SDT");
		String email = request.getParameter("Email");
		
		CanBoDAO canBoDao = new CanBoDAO();
		canBoDao.CanBoInsert(ten, gioiTinh, tuoi, diaChi, SDT, email);
		
		out.write("Insert thanh cong");
		}
		catch(Exception e) {
			out.write("Insert that bai");
		}
		
	*/
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		CanBo canBo = gson.fromJson(result, CanBo.class);
		CanBoDAO canBoDao = new CanBoDAO();
		try {
			canBoDao.CanBoInsert(canBo.getTen(), canBo.getGioiTinh(), canBo.getTuoi(), canBo.getDiaChi(), canBo.getSDT(), canBo.getEmail());
			CanBo canBo1 = canBoDao.CanBoGetById(canBoDao.LastestIdCanBo());
			String str = gson.toJson(canBo1);
			out.write(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.write("Insert that bai");
		}
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter(); 
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		CanBo canBo = gson.fromJson(result, CanBo.class);
		CanBoDAO canBoDao = new CanBoDAO();
		try {
			canBoDao.CanBoUpdate(canBo.getTen(), canBo.getGioiTinh(), canBo.getTuoi(), canBo.getDiaChi(), canBo.getSDT(), canBo.getEmail(), canBo.getIdCanBo());
			out.write("Update thanh cong");
		}
		catch(SQLException e) {
			out.write("Update that bai");
		}
	}
	
}
