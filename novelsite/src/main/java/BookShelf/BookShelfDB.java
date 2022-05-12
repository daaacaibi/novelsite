package BookShelf;
import java.sql.*;
import java.util.ArrayList;

import Classify.ClassifyInfo;
import User.UserInfo;
import common.DBConnection;

public class BookShelfDB {
	private Connection con = null;
	
    /* ��ȡ���������Ϣ */
    public ArrayList<BookShelfInfo> getAll() {
        ResultSet rs=null;
        Statement sql=null;
        ArrayList<BookShelfInfo> BSList=new ArrayList<BookShelfInfo>();
        try {
        	con=DBConnection.getConnection();
            sql=con.createStatement();
	//TODO 1 ִ�в�ѯ����ȡ���з�����Ϣ
            rs=sql.executeQuery("select * from Bookshelf");
	//TODO 2 ѭ������������Ϣ���������ÿ����¼����Ϊһ��������Ϣ���󣬲��ѷ�����Ϣ������ӵ�����ClassifyList�С�
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
            System.out.println("��ȡ���������Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return BSList;
    }
    
    /* ��ȡָ�������Ϣ 1.��UID��*/
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
            System.out.println("��ȡָ�������Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return BSList;
    }
    
    /* ��ȡָ���û���Ϣ 2.��id��  ����Ҫ*/
   
    
    /* ��������Ϣ */
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
            System.out.println("������ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* �޸������Ϣ */
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
            System.out.println("�������ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* ɾ�������Ϣ 1.ID*/
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
            System.out.println("ɾ�������Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    /* ɾ�������Ϣ 2.UID��һ���û����м�¼*/
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
            System.out.println("ɾ�������Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    


}
