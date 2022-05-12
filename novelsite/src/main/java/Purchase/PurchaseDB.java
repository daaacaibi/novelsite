package Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.DBConnection;
import Purchase.PurchaseInfo;

public class PurchaseDB {
	private Connection con = null;
    public ArrayList<PurchaseInfo> getAll() {
        ResultSet rs=null;
        Statement sql=null;
        ArrayList<PurchaseInfo> List=new ArrayList<PurchaseInfo>();
        try {
        	con=DBConnection.getConnection();
            sql=con.createStatement();
            rs=sql.executeQuery("select * from purchase");
            while(rs.next()){
            	PurchaseInfo purchase=new PurchaseInfo();
            	purchase.setUID(rs.getInt("UID"));
            	purchase.setID(rs.getInt("ID"));
            	purchase.setRechargeMoney(rs.getInt("RechargeMoney"));
            	purchase.setToken(rs.getInt("Token"));
            	purchase.setPurchaseTime(rs.getInt("PurchaseTime"));
            	List.add(purchase);
	    	} 
			rs.close();
			sql.close();
        } catch (Exception e) {
            System.out.println("获取所有订单信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return List;
    }
    public PurchaseInfo getPurchaseById(int id) {
        ResultSet rs=null;
    	PreparedStatement pStmt=null; 
    	PurchaseInfo purchase=new PurchaseInfo();
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("SELECT * FROM purchase where UID=?");
    		pStmt.setInt(1,id);		
    		rs = pStmt.executeQuery();
	    	if(rs.next()){
	    		purchase=new PurchaseInfo();
	    		purchase.setUID(rs.getInt("UID"));
            	purchase.setID(rs.getInt("ID"));
            	purchase.setRechargeMoney(rs.getInt("RechargeMoney"));
            	purchase.setToken(rs.getInt("Token"));
            	purchase.setPurchaseTime(rs.getInt("PurchaseTime"));	    		
	    	} 
			rs.close();
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("获取指定订单信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return purchase;
    }
    public int insertPurchase(PurchaseInfo ps) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("insert into purchase (UID,ID,RechargeMoney,Token,PurchaseTime) values (?,?,?,?,?)");
    				
    		pStmt.setInt(1,ps.getUID());		
    		pStmt.setInt(2,ps.getID());		
    		pStmt.setInt(3,ps.getRechargeMoney());		
    		pStmt.setInt(4,ps.getToken());		
    		pStmt.setInt(5,ps.getPurchaseTime());
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("添加订单失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }    
}
