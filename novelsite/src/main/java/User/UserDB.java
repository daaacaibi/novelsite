package User;
import java.sql.*;
import java.util.ArrayList;
import common.DBConnection;


public class UserDB {
	private Connection con = null;
	
    /* ��ȡ�����û���Ϣ */
    public ArrayList<UserInfo> getAll() {
        ResultSet rs=null;
        Statement sql=null;
        ArrayList<UserInfo> userList=new ArrayList<UserInfo>();
        try {
        	con=DBConnection.getConnection();
            sql=con.createStatement();
	//TODO 1 ִ�в�ѯ����ȡ�����û���Ϣ
            rs=sql.executeQuery("select * from user");
	//TODO 2 ѭ�������û���Ϣ���������ÿ����¼����Ϊһ���û���Ϣ���󣬲����û���Ϣ������ӵ�����userList�С�
            while(rs.next()){
            	UserInfo user=new UserInfo();
            	user.setUID(rs.getInt("UID"));
            	user.setUName(rs.getString("UName"));
            	user.setUSex(rs.getString("USex"));
            	user.setUMoney(rs.getInt("UMoney"));
            	user.setUContent(rs.getString("UContent"));
            	user.setUBrith(rs.getString("UBirth"));
            	user.setUPwd(rs.getString("UPwd"));
            	user.setHeadPicture(rs.getString("HeadPicture"));
            	user.setRecommend(rs.getInt("Recommend"));
            	user.setEditorStatus(rs.getInt("EditorStatus"));
            	userList.add(user);
	    	}         
       
			rs.close();
			sql.close();
        } catch (Exception e) {
            System.out.println("��ȡ�����û���Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return userList;
    }
    
    /* ��ȡָ���û���Ϣ 1.��ID��*/
    public UserInfo getUserById(int id) {
        ResultSet rs=null;
    	PreparedStatement pStmt=null; 
    	UserInfo user=new UserInfo();
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("SELECT * FROM user where UID=?");
    		pStmt.setInt(1,id);		
    		rs = pStmt.executeQuery();
	    	if(rs.next()){
            	user=new UserInfo();
            	user.setUID(rs.getInt("UID"));
            	user.setUName(rs.getString("UName"));
            	user.setUSex(rs.getString("USex"));
            	user.setUMoney(rs.getInt("UMoney"));
            	user.setUContent(rs.getString("UContent"));
            	user.setUBrith(rs.getString("UBirth"));
            	user.setUPwd(rs.getString("UPwd"));
            	user.setHeadPicture(rs.getString("HeadPicture"));
            	user.setRecommend(rs.getInt("Recommend"));
            	user.setEditorStatus(rs.getInt("EditorStatus"));	    		
	    	} 
			rs.close();
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("��ȡָ���û���Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return user;
    }
    
    /* ��ȡָ���û���Ϣ 2.����UName��*/
    public UserInfo getUserByname(String uname) {
        ResultSet rs=null;
    	PreparedStatement pStmt=null; 
    	UserInfo user=new UserInfo();
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("SELECT * FROM user where UName=?");
    		pStmt.setString(1,uname);		
    		rs = pStmt.executeQuery();
        	if(rs.next()){
            	user=new UserInfo();
            	user.setUID(rs.getInt("UID"));
            	user.setUName(rs.getString("UName"));
            	user.setUSex(rs.getString("USex"));
            	user.setUMoney(rs.getInt("UMoney"));
            	user.setUContent(rs.getString("UContent"));
            	user.setUBrith(rs.getString("UBirth"));
            	user.setUPwd(rs.getString("UPwd"));
            	user.setHeadPicture(rs.getString("HeadPicture"));
            	user.setRecommend(rs.getInt("Recommend"));
            	user.setEditorStatus(rs.getInt("EditorStatus"));	    		
        	} 
    		rs.close();
    		pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("��ȡָ���û���Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
    	}		
        return user;
    }

    
    /* ����û���Ϣ */
    public int insertUser(UserInfo user) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("insert into user (UID,UName,USex,UMoney,UContent,UBirth,UPwd,HeadPicture,Recommend,EditorStatus) values (?,?,?,?,?,?,?,?,?,?)");
    				
    		pStmt.setInt(1,user.getUID());		
    		pStmt.setString(2,user.getUName());		
    		pStmt.setString(3,user.getUSex());		
    		pStmt.setInt(4,user.getUMoney());		
    		pStmt.setString(5,user.getUContent());
    		pStmt.setString(6,user.getUBrith());
    		pStmt.setString(7,user.getUPwd());
    		pStmt.setString(8,user.getHeadPicture());
    		pStmt.setInt(9,user.getRecommend());
    		pStmt.setInt(10,user.getEditorStatus());
    
    		//System.out.println(pStmt);
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("����û�ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* �޸��û���Ϣ */
    public int updateUser(UserInfo user) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("update user set UName=?,USex=?,UContent=?,UBirth=?,UPwd=?,HeadPicture=?  where UID=?");
    					
    		pStmt.setString(1,user.getUName());		
    		pStmt.setString(2,user.getUSex());				
    		pStmt.setString(3,user.getUContent());
    		pStmt.setString(4,user.getUBrith());
    		pStmt.setString(5,user.getUPwd());
    		pStmt.setString(6,user.getHeadPicture());
    		pStmt.setInt(7,user.getUID());	
    
    		//System.out.println(pStmt);
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("�����û�ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    //�޸��û����
    public int updateUser_UMoney(int uid,int umoney) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("update user set UMoney=? where UID=?");
    					
    		pStmt.setInt(1,umoney);		
    		pStmt.setInt(2,uid);	
    
    		//System.out.println(pStmt);
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("�����û����ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    //�޸��û��Ƽ�Ʊ��
    public int updateUser_recommend(int uid,int recommend) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("update user set Recommend=? where UID=?");
    					
    		pStmt.setInt(1,recommend);		
    		pStmt.setInt(2,uid);	
    
    		//System.out.println(pStmt);
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("�����û��Ƽ�Ʊ��ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    //�޸��û�״̬
    public int updateUser_EditorStatus(int uid,int editorstatus) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("update user set EditorStatus=? where UID=?");
    					
    		pStmt.setInt(1,editorstatus);		
    		pStmt.setInt(2,uid);	
    
    		//System.out.println(pStmt);
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("�����û�״̬ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* ɾ���û���Ϣ */
    public int deleteUser(int id) {
     	PreparedStatement pStmt=null; 
     	PreparedStatement pst1=null;
     	PreparedStatement pst2=null;
    	int count=0;
    	
        try {
        	con=DBConnection.getConnection();
        	
    		pStmt = con.prepareStatement("delete from user  where UID=?");
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



