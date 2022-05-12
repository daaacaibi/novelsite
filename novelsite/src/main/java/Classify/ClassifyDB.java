package Classify;
import java.sql.*;
import java.util.ArrayList;
import common.DBConnection;


public class ClassifyDB {
	private Connection con = null;
	
    /* ��ȡ���з�����Ϣ */
    public ArrayList<ClassifyInfo> getAll() {
        ResultSet rs=null;
        Statement sql=null;
        ArrayList<ClassifyInfo> classifyList=new ArrayList<ClassifyInfo>();
        try {
        	con=DBConnection.getConnection();
            sql=con.createStatement();
	//TODO 1 ִ�в�ѯ����ȡ���з�����Ϣ
            rs=sql.executeQuery("select * from Classify");
	//TODO 2 ѭ������������Ϣ���������ÿ����¼����Ϊһ��������Ϣ���󣬲��ѷ�����Ϣ������ӵ�����ClassifyList�С�
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
            System.out.println("��ȡ���з�����Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return classifyList;
    }
    
    /* ��ȡָ��������Ϣ 1.��Classify��*/
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
            System.out.println("��ȡָ��������Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return classifyList;
    }
    
    /* ��ȡָ���û���Ϣ 2.��Bid��*/
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
            System.out.println("��ȡָ��������Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return classifyList;
    }

    
    /* ��ӷ�����Ϣ */
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
            System.out.println("��ӷ���ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* ɾ��������Ϣ 1.ID*/
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
            System.out.println("ɾ���û���Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    /* ɾ��������Ϣ 2.BID��һ�������з���*/
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
            System.out.println("ɾ���û���Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    

}



