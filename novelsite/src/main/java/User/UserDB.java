package User;
import java.sql.*;
import java.util.ArrayList;
import common.DBConnection;


public class UserDB {
	private Connection con = null;
	
    /* 获取所有用户信息 */
    public ArrayList<UserInfo> getAll() {
        ResultSet rs=null;
        Statement sql=null;
        ArrayList<UserInfo> userList=new ArrayList<UserInfo>();
        try {
        	con=DBConnection.getConnection();
            sql=con.createStatement();
	//TODO 1 执行查询语句获取所有用户信息
            rs=sql.executeQuery("select * from user");
	//TODO 2 循环遍历用户信息结果集，将每条记录保存为一个用户信息对象，并把用户信息对象添加到数组userList中。
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
            System.out.println("获取所有用户信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return userList;
    }
    
    /* 获取指定用户信息 1.用ID查*/
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
            System.out.println("获取指定用户信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return user;
    }
    
    /* 获取指定用户信息 2.用用UName查*/
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
            System.out.println("获取指定用户信息失败！");
        } finally{
        	DBConnection.closeConnection();
    	}		
        return user;
    }

    
    /* 添加用户信息 */
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
            System.out.println("添加用户失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* 修改用户信息 */
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
            System.out.println("更新用户失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    //修改用户余额
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
            System.out.println("更新用户余额失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    //修改用户推荐票数
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
            System.out.println("更新用户推荐票数失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    //修改用户状态
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
            System.out.println("更新用户状态失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
    
    /* 删除用户信息 */
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
            System.out.println("删除用户信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    } 
    

}



