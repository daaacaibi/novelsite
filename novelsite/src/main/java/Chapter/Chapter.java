package Chapter;
import java.sql.*;
import java.util.*;

import Book.BookInfo;
import common.DBConnection;

public class Chapter {
	private Connection con = null;
	
	public ArrayList<ChapterInfo> getAll_ChapterName(int bid){//获取所有章节信息
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<ChapterInfo> ChapterList = new ArrayList<ChapterInfo>();
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("select * from BOOK where BID=?");
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ChapterInfo chapter = new ChapterInfo();
				chapter.setChapterID(rs.getInt("ChapterID"));
				chapter.setChapterName(rs.getString("ChapterName"));
				chapter.setBID(rs.getInt("BID"));
				chapter.setPart(rs.getString("Part"));
				chapter.setState(rs.getInt("State"));
				ChapterList.add(chapter);
			}
			rs.close();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("获取指定书籍章节列表失败！");
		}finally {
			DBConnection.closeConnection();
		}
		return ChapterList;
	}
	
	public ChapterInfo getChapterById(int bid,int chapterid) {
		ResultSet rs = null ;
		PreparedStatement pstmt = null;
		ChapterInfo chapter = new ChapterInfo();
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("select * from BOOK where BID=? AND ChapterID=?");
			pstmt.setInt(1, bid);
			pstmt.setInt(2, chapterid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				chapter.setChapterID(rs.getInt("ChapterID"));
				chapter.setChapterName(rs.getString("ChapterName"));
				chapter.setBID(rs.getInt("BID"));
				chapter.setPart(rs.getString("Part"));
				chapter.setState(rs.getInt("State"));
			}
			rs.close();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("获取指定书籍指定章节失败！");
		}finally {
			DBConnection.closeConnection();
		}
	return chapter;
	}
	
	public int changeChapterState(int bid,int chapterid,int state){
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("update CHAPTER set State=? where BID=? AND ChapterID=?" );
			pstmt.setInt(1,state);
			pstmt.setInt(2, bid);
			pstmt.setInt(3,chapterid);
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("修改章节状态错误！");
		}finally {
			DBConnection.closeConnection();
		}
		return count;
	}
	
	public int InsertChapter(ChapterInfo chapter) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("insert into CHAPTER (ChapterID,ChapterName,BID,Part,State) values (?,?,?,?,?)");
			pstmt.setInt(1,chapter.getChapterID());
			pstmt.setString(2,chapter.getChapterName());
			pstmt.setInt(3,chapter.getBID());
			pstmt.setString(4,chapter.getPart());
			pstmt.setInt(5,chapter.getState());
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("增加章节信息错误！");
		}finally {
			DBConnection.closeConnection();
		}
		return count;
	}
	
	public int UpdateChapter(ChapterInfo chapter) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("update CHAPTER set Part=? where BID=? AND ChapterID=?");
			pstmt.setString(1,chapter.getPart());
			pstmt.setInt(2,chapter.getBID());
			pstmt.setInt(3, chapter.getChapterID());
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("修改章节信息错误！");
		}finally {
			DBConnection.closeConnection();
		}
		return count;
	}
}
