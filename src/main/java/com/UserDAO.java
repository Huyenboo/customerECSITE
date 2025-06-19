package com;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO extends DBAccess {
	public ArrayList<User> userList() {
		ArrayList<User> list = new ArrayList<>();
		
		String sql = "SELECT * FROM app_user";
		
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				User c = new User();
				c.setCompanyName(rs.getString("company_name"));
				c.setCompanyAddress(rs.getString("company_address"));
				c.setPresidentPhoneNum(rs.getString("president_phone_num"));
				c.setManagerName(rs.getString("manager_name"));
				c.setManagerEmail(rs.getString("manager_email"));
				c.setManagerPhoneNum(rs.getString("manager_phone_num"));
				c.setPassword(rs.getString("pass"));
				c.setStatus(rs.getInt("status"));
				c.setRequestedAt(rs.getTimestamp("requested_at"));
				c.setApprovedAt(rs.getTimestamp("approved_at"));
				c.setApprovedBy(rs.getInt("approved_by"));
				c.setRejectionReason(rs.getString("rejection_reson"));
				list.add(c);
			}
			
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list.isEmpty() ? null : list;
	}
    // Mã hóa mật khẩu SHA-256
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Đăng ký người dùng mới
    public boolean registerUser(User user) {
        String sql = "INSERT INTO app_user (company_name, company_address, president_phone_num, manager_name, manager_email, manager_phone_num, pass, status, requested_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, user.getCompanyName());
            ps.setString(2, user.getCompanyAddress());
            ps.setString(3, user.getPresidentPhoneNum());
            ps.setString(4, user.getManagerName());
            ps.setString(5, user.getManagerEmail());
            ps.setString(6, user.getManagerPhoneNum());
            ps.setString(7, hashPassword(user.getPassword()));
            ps.setInt(8, 1); // status = pending
            int result = ps.executeUpdate();
            ps.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return false;
    }
    public User loginUser(String phone, String rawPassword) {
        String sql = "SELECT * FROM app_user WHERE manager_phone_num = ? AND pass = ? AND status = 1"; // chỉ cho phép đã được duyệt (status = 1)

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, hashPassword(rawPassword));  // hash để so sánh

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setCompanyName(rs.getString("company_name"));
                user.setManagerName(rs.getString("manager_name"));
                user.setManagerPhoneNum(rs.getString("manager_phone_num"));
                user.setPassword(rs.getString("pass"));
                user.setStatus(rs.getInt("status"));
                rs.close();
                ps.close();
                return user;
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return null;
    }



////package com;
////
////import java.security.MessageDigest;
////import java.security.NoSuchAlgorithmException;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.util.ArrayList;
////
////
////
////public class UserDAO extends DBAccess {
////	// Mã hóa mật khẩu
////		public String hashPassword(String password) {
////		    try {
////		        MessageDigest md = MessageDigest.getInstance("SHA-256");
////		        byte[] hash = md.digest(password.getBytes());
////		        StringBuilder hexString = new StringBuilder();
////		        for (byte b : hash) {
////		            hexString.append(String.format("%02x", b));
////		        }
////		        return hexString.toString();
////		    } catch (NoSuchAlgorithmException e) {
////		        throw new RuntimeException(e);
////		    }
////		}
////	public ArrayList<User> userList() {
////		ArrayList<User> list = new ArrayList<>();
////		
////		String sql = "SELECT * FROM app_user";
////		
////		try {
////			connect();
////			PreparedStatement ps = getConnection().prepareStatement(sql);
////			ResultSet rs = ps.executeQuery();
////			
////			while (rs.next()) {
////				User c = new User();
////				c.setCompanyName(rs.getString("company_name"));
////				c.setCompanyAddress(rs.getString("company_address"));
////			    c.setPresidentPhoneNum(rs.getString("president_phone_num"));
////				c.setManagerName(rs.getString("manager_name"));
////				c.setManagerEmail(rs.getString("manager_email"));
////				c.setManagerPhoneNum(rs.getString("manager_phone_num"));
////				c.setPassword(rs.getString("pass"));
////				c.setStatus(rs.getString("status"));
////				c.setRequestedAt(rs.getTimestamp("requested_at"));
////				c.setApprovedAt(rs.getTimestamp("approved_at"));
////				c.setApprovedBy(rs.getInt("approved_by"));
////				c.setRejectionReson(rs.getString("rejection_reson"));
////				list.add(c);
////			}
////			
////			rs.close();
////			ps.close();
////		} catch (Exception e) {
////			e.printStackTrace();
////		} finally {
////			disconnect();
////		}
////		
////		return list.isEmpty() ? null : list;
////	}
////	//2.ユーザー登録
////	public boolean registerUser(User user) {
////		String sql ="INSERT INTO app_user (company_name, company_address, president_phone_num, manager_name, manager_email, manager_phone_num, pass, status, requested_at) " +
////                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
////		
////		try {
////			connect();
////			PreparedStatement ps = getConnection().prepareStatement(sql);
////			ps.setString(1, user.getCompanyName());
////			ps.setString(2, user.getCompanyAddress());
////			ps.setString(3, user.getPresidentPhoneNum());
////			ps.setString(4, user.getManagerName());
////			ps.setString(5, user.getManagerPhoneNum());
////			ps.setString(6, user.getManagerEmail());
////			ps.setString(7, hashPassword(user.getPassword())); // mã hóa mật khẩu
////			ps.setInt(8, 0); // mã hóa mật khẩu
////			int result = ps.executeUpdate();
////			ps.close();
////			return result > 0;
////		} catch (Exception e) {
////			e.printStackTrace();
////		} finally {
////			disconnect();
////		}
////		return false;
////	}
////	
////
////	// 3. ログインチェック
////	public User loginUser(String phone, String rawPassword) {
////		String sql = "SELECT * FROM app_user WHERE manager_phone_num = ? AND pass = ? AND status = 'accept'";
////
////
////	    try {
////	        connect(); 
////	        PreparedStatement ps = getConnection().prepareStatement(sql);
////	        ps.setString(1, phone);
////	        ps.setString(2, hashPassword(rawPassword)); // Mã hóa mật khẩu để so sánh
////	        ResultSet rs = ps.executeQuery();
////
////	        if (rs.next()) {
////	            User user = new User();
////	            user.setId(rs.getInt("id"));
////	            user.setPassword(rs.getString("pass"));
////	            user.setManagerPhoneNum(rs.getString("manager_phone_num"));
////	            user.setCompanyName(rs.getString("company_name"));
////	            user.setManagerName(rs.getString("manager_name"));
////	            user.setStatus(rs.getString("status"));
////	            rs.close();
////	            ps.close();
////	            return user;
////	        }
////
////	        rs.close();
////	        ps.close();
////	    } catch (Exception e) {
////	        e.printStackTrace();
////	    } finally {
////	        disconnect();
////	    }
////	    return null;
////	}
////
////	// Kiểm tra mật khẩu hiện tại
////	public boolean checkCurrentPassword(String phone, String rawPassword) {
////	    String sql = "SELECT * FROM app_user WHERE manager_phone_num = ? AND pass = ? AND status = 'accept'";
////	    try {
////	        connect();
////	        PreparedStatement ps = getConnection().prepareStatement(sql);
////	        ps.setString(1, phone);
////	        ps.setString(2, hashPassword(rawPassword));
////	        ResultSet rs = ps.executeQuery();
////	        boolean exists = rs.next();
////	        rs.close(); ps.close();
////	        return exists;
////	    } catch (Exception e) {
////	        e.printStackTrace();
////	    } finally {
////	        disconnect();
////	    }
////	    return false;
////	}
////
////	// Cập nhật mật khẩu mới
////	public boolean updatePassword(String phone, String newPassword) {
////	    String sql = "UPDATE app_user SET pass = ? WHERE manager_phone_num = ?";
////	    try {
////	        connect();
////	        PreparedStatement ps = getConnection().prepareStatement(sql);
////	        ps.setString(1, hashPassword(newPassword));
////	        ps.setString(2, phone);
////	        int result = ps.executeUpdate();
////	        ps.close();
////	        return result > 0;
////	    } catch (Exception e) {
////	        e.printStackTrace();
////	    } finally {
////	        disconnect();
////	    }
////	    return false;
////	}
////
////	
////}
//
//
//package com;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//public class UserDAO extends DBAccess {
//
//    // Mã hóa mật khẩu SHA-256
//    public String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hash = md.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                hexString.append(String.format("%02x", b));
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // Lấy danh sách người dùng
//    public ArrayList<User> userList() {
//        ArrayList<User> list = new ArrayList<>();
//        String sql = "SELECT * FROM app_user";
//
//        try {
//            connect();
//            PreparedStatement ps = getConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                User c = new User();
//                c.setId(rs.getInt("id"));
//                c.setCompanyName(rs.getString("company_name"));
//                c.setCompanyAddress(rs.getString("company_address"));
//                c.setPresidentPhoneNum(rs.getString("president_phone_num"));
//                c.setManagerName(rs.getString("manager_name"));
//                c.setManagerPhoneNum(rs.getString("manager_phone_num"));
//                c.setManagerEmail(rs.getString("manager_email"));
//                c.setPassword(rs.getString("pass"));
//                c.setStatus(rs.getInt("status"));
//                c.setRequestedAt(rs.getTimestamp("requested_at"));
//                c.setApprovedAt(rs.getTimestamp("approved_at"));
//                c.setApprovedBy(rs.getInt("approved_by"));
//                c.setRejectionReson(rs.getString("rejection_reason"));
//                list.add(c);
//            }
//
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            disconnect();
//        }
//
//        return list.isEmpty() ? null : list;
//    }
//
//    // Đăng ký người dùng mới
//    public boolean registerUser(User user) {
//    	String sql = "INSERT INTO app_user (company_name, company_address, president_phone_num, manager_name, manager_email, manager_phone_num, pass, status, requested_at) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, now())";
//
//   try {
//       connect();
//       PreparedStatement ps = getConnection().prepareStatement(sql);
//       ps.setString(1, user.getCompanyName());
//       ps.setString(2, user.getCompanyAddress());
//       ps.setString(3, user.getPresidentPhoneNum());
//       ps.setString(4, user.getManagerName());
//       ps.setString(5, user.getManagerEmail());         // ✅ đúng thứ tự với SQL
//       ps.setString(6, user.getManagerPhoneNum());      // ✅ đúng thứ tự với SQL
//       ps.setString(7, hashPassword(user.getPassword()));
//       ps.setInt(8, 0); // status = pending
//
//       int result = ps.executeUpdate();
//       ps.close();
//       return result > 0;
//   } catch (Exception e) {
//       e.printStackTrace();
//   } finally {
//       disconnect();
//   }
//   return false;
//    }
//    
//    // Đăng nhập người dùng
//    public User loginUser(String phone, String rawPassword) {
//        String sql = "SELECT * FROM app_user WHERE manager_phone_num = ? AND pass = ? AND status = 1"; // 1 = accept
//
//        try {
//            connect();
//            PreparedStatement ps = getConnection().prepareStatement(sql);
//            ps.setString(1, phone);
//            ps.setString(2, hashPassword(rawPassword));
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                User user = new User();
//                user.setId(rs.getInt("id"));
//                user.setManagerPhoneNum(rs.getString("manager_phone_num"));
//                user.setPassword(rs.getString("pass"));
//                user.setCompanyName(rs.getString("company_name"));
//                user.setManagerName(rs.getString("manager_name"));
//                user.setStatus(rs.getInt("status"));
//                rs.close();
//                ps.close();
//                return user;
//            }
//
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            disconnect();
//        }
//
//        return null;
//    }
//
//    // Kiểm tra mật khẩu hiện tại
    public boolean checkCurrentPassword(String phone, String rawPassword) {
    	String sql = "SELECT * FROM app_user WHERE manager_phone_num = ? AND pass = ? AND status = 1";

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, hashPassword(rawPassword));
            ResultSet rs = ps.executeQuery();

            boolean exists = rs.next();
            rs.close(); ps.close();
            return exists;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return false;
    }

    // Cập nhật mật khẩu mới
    public boolean updatePassword(String phone, String newPassword) {
        String sql = "UPDATE app_user SET pass = ? WHERE manager_phone_num = ?";

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, hashPassword(newPassword));
            ps.setString(2, phone);
            int result = ps.executeUpdate();
            ps.close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return false;
    }
}
