package Classify;
import java.sql.*;
import java.util.ArrayList;
import common.DBConnection;


public class ClassifyDB {
	private Connection con = null;
	
    /* 获取所有分类信息 */
    public ArrayList<ClassifyInfo> getAll() {
        ResultSet rs=null;
        Statement sql=null;
        ArrayList<ClassifyInfo> classifyList=new ArrayList<ClassifyInfo>();
        try {
        	con=DBConnection.getConnection();
            sql=con.createStatement();
	//TODO 1 执行查询语句获取所有分类信息
            rs=sql.executeQuery("select * from Classify");
	//TODO 2 循环遍历分类信息结果集，将每条记录保存为一个分类信息对象，并把分类信息对象添加到数组ClassifyList中。
            while(rs.next()){
            	ClassifyInfo classify=new ClassifyInfo();
            	classify.setID(rs.getInt("ID"));
            	classify.setClassify(rs.getString("Classify"));
            	classify.setBID(rs.getInt("BID"));
            	classifyList.add(classify);
	    	}         
       
			rs.close();
			sql.close();
        } catch (Exception e) {
            System.out.println("获取所有分类信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return classifyList;
    }
    
    /* 获取指定分类信息 1.用Classify查*/
    public ArrayList<ClassifyInfo> getClassifyByclassify(String classify_info) {
        ResultSet rs=null;
    	PreparedStatement pStmt=null; 
    	ArrayList<ClassifyInfo> classifyList=new ArrayList<ClassifyInfo>();
    	ClassifyInfo classify=new ClassifyInfo();
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("SELECT * FROM classify where Classify=?");
    		pStmt.setString(1,classify_info);		
    		rs = pStmt.executeQuery();
	    	if(rs.next()){
	    		classify =new ClassifyInfo();
	    		classify.setID(rs.getInt("ID"));
	           	classify.setClassify(rs.getString("Classify"));
            	classify.setBID(rs.getInt("BID"));
            	classifyList.add(classify);   		
	    	} 
			rs.close();
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("获取指定分类信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return classifyList;
    }
    
    /* 获取指定用户信息 2.用Bid查*/
    public ArrayList<ClassifyInfo> getClassifyByBID(int bid) {
        ResultSet rs=null;
    	PreparedStatement pStmt=null; 
    	ArrayList<ClassifyInfo> classifyList=new ArrayList<ClassifyInfo>();
    	ClassifyInfo classify=new ClassifyInfo();
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("SELECT * FROM classify where BID=?");
    		pStmt.setInt(1,bid);		
    		rs = pStmt.executeQuery();
	    	if(rs.next()){
	    		classify =new ClassifyInfo();
	    		classify.setID(rs.getInt("ID"));
	           	classify.setClassify(rs.getString("Classify"));
            	classify.setBID(rs.getInt("BID"));
            	classifyList.add(classify);   		
	    	} 
			rs.close();
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("获取指定分类信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return classifyList;
    }

    
    /* 添加分类信息 */
    public int insertClassify(ClassifyInfo classify) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("insert into Classify (ID,Classify,BID) values (?,?,?)");
    				
    		pStmt.setInt(1,classify.getID());	
    		pStmt.setString(2,classify.getClassify());
    		pStmt.setInt(3,classify.getBID());
    
    		//System.out.println(pStmt);
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("添加分类失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* 删除分类信息 1.ID*/
    public int deleteClassifyByID(int id) {
     	PreparedStatement pStmt=null; 
     	PreparedStatement pst1=null;
     	PreparedStatement pst2=null;
    	int count=0;
    	
        try {
        	con=DBConnection.getConnection();
        	
    		pStmt = con.prepareStatement("delete from Classify  where ID=?");
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
            System.out.println("删除用户信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    /* 删除分类信息 2.BID，一本书所有分类*/
    public int deleteClassifyByBID(int id) {
     	PreparedStatement pStmt=null; 
     	PreparedStatement pst1=null;
     	PreparedStatement pst2=null;
    	int count=0;
    	
        try {
        	con=DBConnection.getConnection();
        	
    		pStmt = con.prepareStatement("delete from Classify  where BID=?");
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
            System.out.println("删除用户信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    

}



