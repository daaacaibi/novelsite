package Book;
import java.sql.*;
import java.util.*;
import common.DBConnection;

public class BookDB {

	private Connection con = null;
	
	public ArrayList<BookInfo> getAllBook(){//获取所有小说信息
		ResultSet rs = null;
		Statement sql = null;
		ArrayList<BookInfo> BookList = new ArrayList<BookInfo>();
		try {
			con = DBConnection.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("select * from BOOK");
			while(rs.next()) {
				BookInfo book = new BookInfo();
				book.setBID(rs.getInt("BID"));
				book.setBName(rs.getString("BName"));
				book.setAuthor(rs.getString("Author"));
				book.setPicture(rs.getString("Picture"));
				book.setVotes(rs.getInt("Votes"));
				book.setHits(rs.getInt("Hits"));
				book.setState(rs.getInt("State"));
				book.setWordCount(rs.getInt("WordCount"));
				book.setContent(rs.getString("Content"));
				book.setCreateTime(rs.getInt("Createtime"));
				book.setUpdateTime(rs.getInt("Updatetime"));
				BookList.add(book);
			}
			rs.close();
			sql.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("获取书籍信息失败！");
		}finally {
			DBConnection.closeConnection();
		}
		return BookList;
	}
	
	public BookInfo getBookById(int bid) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		BookInfo book = new BookInfo();
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("select * from BOOK where BID=?");
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				book.setBID(rs.getInt("BID"));
				book.setBName(rs.getString("BName"));
				book.setAuthor(rs.getString("Author"));
				book.setPicture(rs.getString("Picture"));
				book.setVotes(rs.getInt("Votes"));
				book.setHits(rs.getInt("Hits"));
				book.setState(rs.getInt("State"));
				book.setWordCount(rs.getInt("WordCount"));
				book.setContent(rs.getString("Content"));
				book.setCreateTime(rs.getInt("CreateTime"));
				book.setUpdateTime(rs.getInt("UpdateTime"));
				pstmt.close();
				rs.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("获取指定书籍信息失败！");
		}finally {
			DBConnection.closeConnection();
		}
		return book;
	}
	
	public int InsertBook(BookInfo book) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("insert into Book (BID,BName,Author,Picture,Content,CreateTime,UpdateTime) values (?,?,?,?,?,?,?)");
			pstmt.setInt(1,book.getBID());
			pstmt.setString(2,book.getBName());
			pstmt.setString(3,book.getAuthor());
			pstmt.setString(4,book.getPicture());
			pstmt.setString(5,book.getContent());
			pstmt.setInt(6, book.getCreateTime());
			pstmt.setInt(7, book.getCreateTime());
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("增加书籍信息错误！");
		}finally {
			DBConnection.closeConnection();
		}
		return count;
	}
	
	public int UpdateBook(BookInfo book) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("update Book set Picture=?,Content=?,UpdateTime=? where BID=?");
			pstmt.setString(1,book.getPicture());
			pstmt.setString(2,book.getContent());
			pstmt.setInt(3, book.getUpdateTime());
			pstmt.setInt(4, book.getBID());
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("修改书籍信息错误！");
		}finally {
			DBConnection.closeConnection();
		}
		return count;
	}
	
	public int UpdateBook_Votes(int bid,int votes) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("update Book set Votes=? where BID=?");
			pstmt.setInt(1,votes);
			pstmt.setInt(2,bid);
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("修改书籍信息错误！");
		}finally {
			DBConnection.closeConnection();
		}
		return count;
	}
	
	public int UpdateBook_Hits(int bid,int hits) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("update Book set Hits=? where BID=?");
			pstmt.setInt(1,hits);
			pstmt.setInt(2,bid);
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("更新书籍点击量错误！");
		}finally {
			DBConnection.closeConnection();
		}
		return count;
	}
	
	public int UpdateBook_WordCount(int bid,int wordcount) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("update Book set WordCount=? where BID=?");
			pstmt.setInt(1,wordcount);
			pstmt.setInt(2,bid);
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("更新书籍字数错误！");
		}finally {
			DBConnection.closeConnection();
		}
		return count;
	}
}
