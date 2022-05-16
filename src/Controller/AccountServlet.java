package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import Common.Common;
import DAO.AccountDAO;
import DAO.CanBoDAO;
import DAO.CongNhanDAO;
import DAO.KySuDAO;
import DAO.NhanVienDAO;
import Model.Account;
import Model.CanBo;
import Model.CongNhan;
import Model.KySu;
import Model.NhanVien;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.write("Hi");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		Account account = gson.fromJson(result, Account.class);
		AccountDAO accountDao = new AccountDAO();
		String stringResponse = null;
		Account account1 = accountDao.Login(account.getUsername(), account.getPassword());
		if (account1 != null) {
			String username = account1.getUsername();
			if (accountDao.CheckAdmin(username)) {
				stringResponse = "{ \"result\" : \"success\", \"admin\" : \"true\", \"username\" : \"" + account1.getUsername() + "\", \"IdPREFIX\" : \"" + account1.getIdPREFIX() + "\", \"IdCanBo\" : \"" + account1.getIdCanBo() + "\" }";
			} else {
				stringResponse = "{ \"result\" : \"success\", \"admin\" : \"false\", \"username\" : \"" + account1.getUsername() + "\", \"IdPREFIX\" : \"" + account1.getIdPREFIX() + "\", \"IdCanBo\" : \"" + account1.getIdCanBo() + "\" }";
			}
		} else {
			stringResponse = "{ \"result\" : \"failed\", \"admin\" : \"NA\", \"username\" : \"NA\", \"IdPREFIX\" : \"NA\", \"IdCanBo\" : \"NA\" }";
		}
		out.write(stringResponse);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);

		AccountDAO accountDao = new AccountDAO();
		Account account = gson.fromJson(result, Account.class);
		try {
			accountDao.UpdateAccount(account.getUsername(), account.getPassword(), account.getIdPREFIX(),
					account.getIdCanBo());
			out.write("Update thanh cong");
		} catch (Exception e) {
			out.write("Update that bai");
		}

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);

		Account account = gson.fromJson(result, Account.class);
		AccountDAO accountDao = new AccountDAO();
		try {
			accountDao.DeleteAccount(account.getIdCanBo());
			out.write("Delete thanh cong");
		} catch (Exception e) {
			out.write("Delete that bai");
		}
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);

		AccountDAO accountDao = new AccountDAO();
		CanBoDAO canBoDao = new CanBoDAO();

		JSONArray jsonArray = new JSONArray(result);
		JSONObject canBoObject = jsonArray.getJSONObject(0);

		String checkType = request.getParameter("type");

		switch (checkType) {
		case "NV":
			NhanVien nhanVien = gson.fromJson(canBoObject.toString(), NhanVien.class);

			NhanVienDAO nhanVienDao = new NhanVienDAO();
			try {
				nhanVienDao.NhanVienInsert(nhanVien.getCongViec(), nhanVien.getIdCanBo());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "KS":
			KySu kySu = gson.fromJson(canBoObject.toString(), KySu.class);
			KySuDAO kySuDao = new KySuDAO();
			try {
				kySuDao.KySuInsert(kySu.getNganhDaoTao(), kySu.getIdCanBo());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "CN":
			CongNhan congNhan = gson.fromJson(canBoObject.toString(), CongNhan.class);
			CongNhanDAO congNhanDao = new CongNhanDAO();
			try {
				congNhanDao.CongNhanInsert(congNhan.getCapBac(), congNhan.getIdCanBo());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
		JSONObject accountObject = jsonArray.getJSONObject(1);
		String idPREFIX = checkType;
		int idCanBo = canBoObject.getInt("IdCanBo");
		String username = accountObject.getString("username");
		String password = accountObject.getString("password");

		try {
			accountDao.Create(username, password, idPREFIX, idCanBo);
			out.write("Successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.write("Can't create account");
		}

	}
}
