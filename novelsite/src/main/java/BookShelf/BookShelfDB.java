package BookShelf;
import java.sql.*;
import java.util.ArrayList;

import Classify.ClassifyInfo;
import User.UserInfo;
import common.DBConnection;

public class BookShelfDB {
	private Connection con = null;
	
    /* 获取所有书架信息 */
    public ArrayList<BookShelfInfo> getAll() {
        ResultSet rs=null;
        Statement sql=null;
        ArrayList<BookShelfInfo> BSList=new ArrayList<BookShelfInfo>();
        try {
        	con=DBConnection.getConnection();
            sql=con.createStatement();
	//TODO 1 执行查询语句获取所有分类信息
            rs=sql.executeQuery("select * from Bookshelf");
	//TODO 2 循环遍历分类信息结果集，将每条记录保存为一个分类信息对象，并把分类信息对象添加到数组ClassifyList中。
            while(rs.next()){
            	BookShelfInfo bs=new BookShelfInfo();
            	bs.setID(rs.getInt("ID"));
            	bs.setBID(rs.getInt("BID"));
            	bs.setChapterID(rs.getInt("ChapterID"));
            	bs.setUID(rs.getInt("UID"));
            	bs.setBookShelfStatus(rs.getInt("BookShelfStatus"));        	
            	BSList.add(bs);
	    	}         
       
			rs.close();
			sql.close();
        } catch (Exception e) {
            System.out.println("获取所有书架信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return BSList;
    }
    
    /* 获取指定书架信息 1.用UID查*/
    public ArrayList<BookShelfInfo> getClassifyByUID(int uid) {
        ResultSet rs=null;
    	PreparedStatement pStmt=null; 
    	ArrayList<BookShelfInfo> BSList=new ArrayList<BookShelfInfo>();
    	BookShelfInfo bs=new BookShelfInfo();
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("SELECT * FROM BookShelf  where UID=?");
    		pStmt.setInt(1,uid);		
    		rs = pStmt.executeQuery();
	    	if(rs.next()){
	    		bs =new BookShelfInfo();
            	bs.setID(rs.getInt("ID"));
            	bs.setBID(rs.getInt("BID"));
            	bs.setChapterID(rs.getInt("ChapterID"));
            	bs.setUID(rs.getInt("UID"));
            	bs.setBookShelfStatus(rs.getInt("BookShelfStatus"));        	
            	BSList.add(bs);		
	    	} 
			rs.close();
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("获取指定书架信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return BSList;
    }
    
    /* 获取指定用户信息 2.用id查  不需要*/
   
    
    /* 添加书架信息 */
    public int insertBookShelf(BookShelfInfo bs) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("insert into BookShelf (ID,BID,ChapterID,UID,BookShelfStatus) values (?,?,?,?,?)");
    				
    		pStmt.setInt(1,bs.getID());	
    		pStmt.setInt(2,bs.getBID());
    		pStmt.setInt(3,bs.getChapterID());
      		pStmt.setInt(4,bs.getUID());
      		pStmt.setInt(5,bs.getBookShelfStatus());
    
    		//System.out.println(pStmt);
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("添加书架失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* 修改书架信息 */
    public int updateBookShelf(BookShelfInfo bs) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("update BookShelf set BID=?,ChapterID=?,UID=?,BookShelfStatus=? where ID=? ");
    					
    		pStmt.setInt(1,bs.getBID());		
    		pStmt.setInt(2,bs.getChapterID());
    		pStmt.setInt(3,bs.getUID());
    		pStmt.setInt(4,bs.getBookShelfStatus());
    		pStmt.setInt(5,bs.getID());
    
    		//System.out.println(pStmt);
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("更新书架失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* 删除书架信息 1.ID*/
    public int deleteBookShelfByID(int id) {
     	PreparedStatement pStmt=null; 
     	PreparedStatement pst1=null;
     	PreparedStatement pst2=null;
    	int count=0;
    	
        try {
        	con=DBConnection.getConnection();
        	
    		pStmt = con.prepareStatement("delete from BookShelf  where ID=?");
    		pst1 = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
        	pst2 = con.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
    		pStmt.setInt(1,id);		
    		try {
    			pst1.executeUpdate();
			} catch (Exception e) {
				System.out.println(88888);
			}
    		
    		count=pStmt.executeUpdate();
    		try {
    			pst2.executeUpdate();
			} catch (Exception e) {
				System.out.println(99999);
			}
    		
    		pStmt.close();   
    		pst1.close();
    		pst2.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("删除书架信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    /* 删除书架信息 2.UID，一个用户所有记录*/
    public int deleteBookShelfByUID(int id) {
     	PreparedStatement pStmt=null; 
     	PreparedStatement pst1=null;
     	PreparedStatement pst2=null;
    	int count=0;
    	
        try {
        	con=DBConnection.getConnection();
        	
    		pStmt = con.prepareStatement("delete from BooKShelf  where UID=?");
    		pst1 = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
        	pst2 = con.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
    		pStmt.setInt(1,id);		
    		try {
    			pst1.executeUpdate();
			} catch (Exception e) {
				System.out.println(88888);
			}
    		
    		count=pStmt.executeUpdate();
    		try {
    			pst2.executeUpdate();
			} catch (Exception e) {
				System.out.println(99999);
			}
    		
    		pStmt.close();   
    		pst1.close();
    		pst2.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("删除书架信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    


}
