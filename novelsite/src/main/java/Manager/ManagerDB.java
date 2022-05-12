package Manager;
import java.sql.*;
import java.util.*;

import Book.BookInfo;
import common.DBConnection;

public class ManagerDB {
	private Connection con = null;
	public ArrayList<ManagerInfo> getAllManager(){//获取所有管理员
		ResultSet rs = null;
		Statement sql = null;
		ArrayList<ManagerInfo> ManagerList = new ArrayList<ManagerInfo>();
		try {
			con = DBConnection.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("select * from Manager");
			while(rs.next()) {
				ManagerInfo manager = new ManagerInfo();
				manager.setMName(rs.getString("MName"));
				manager.setMID(rs.getInt("MID"));
				manager.setMPwd(rs.getString("MPwd"));
				ManagerList.add(manager);
			}
			rs.close();
			sql.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("获取管理员信息失败！");
		}finally {
			DBConnection.closeConnection();
		}
		return ManagerList;
	}
	public ManagerInfo getManagerById(int mid) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ManagerInfo manager = new ManagerInfo();
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("select * from MANAGER where MID=?");
			pstmt.setInt(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				manager.setMName(rs.getString("BName"));
				manager.setMID(rs.getInt("MID"));
				manager.setMPwd(rs.getString("MPwd"));
				pstmt.close();
				rs.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("获取指定管理员信息失败！");
		}finally {
			DBConnection.closeConnection();
		}
		return manager;
	}
}
