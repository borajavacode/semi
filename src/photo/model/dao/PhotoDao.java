package photo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import photo.model.vo.Photo;
import photo.model.vo.PhotoComment;

public class PhotoDao {

	public ArrayList<Photo> selectPhotoList(Connection conn, int start, int end) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Photo> list=new ArrayList<>();
		String query="select pp.*,(select count(*) from photo_comment where pc_ref=pp.photo_no) as \"pc_count\" from (select rownum as rnum, p.* from (select * from photo order by photo_no desc)p)pp where rnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Photo p=new Photo();
				p.setPhotoNo(rset.getInt("photo_no"));
				p.setPhotoTitle(rset.getString("photo_title"));
				p.setPhotoWriter(rset.getString("photo_writer"));
				p.setPhotoContent(rset.getString("photo_content"));
				p.setReadCount(rset.getInt("read_count"));
				p.setRegDate(rset.getString("reg_date"));
				p.setPhotoFilename(rset.getString("photo_filename"));
				p.setPhotoFilepath(rset.getString("photo_filepath"));
				p.setPcCount(rset.getInt("pc_count"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		int result=0;
		String query="select count(*) as cnt from photo";
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertPhoto(Connection conn, Photo p) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="insert into photo values(photo_seq.nextval,?,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, p.getPhotoWriter());
			pstmt.setString(2, p.getPhotoTitle());
			pstmt.setString(3, p.getPhotoContent());
			pstmt.setString(4, p.getPhotoFilepath());
			pstmt.setString(5, p.getPhotoFilename());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int photoNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="update photo set read_count = read_count+1 where photo_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, photoNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Photo selectOnePhoto(Connection conn, int photoNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Photo p=null;
		String query="select * from photo where photo_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, photoNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				p=new Photo();
				p.setPhotoNo(rset.getInt("photo_no"));
				p.setPhotoTitle(rset.getString("photo_title"));
				p.setPhotoWriter(rset.getString("photo_writer"));
				p.setPhotoContent(rset.getString("photo_content"));
				p.setReadCount(rset.getInt("read_count"));
				p.setRegDate(rset.getString("reg_date"));
				p.setPhotoFilename(rset.getString("photo_filename"));
				p.setPhotoFilepath(rset.getString("photo_filepath"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return p;
	}

	public ArrayList<PhotoComment> selectCommentList(Connection conn, int photoNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<PhotoComment> list=new ArrayList<>();
		String query="select * from photo_comment where pc_ref=? order by 1";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, photoNo);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				PhotoComment pc=new PhotoComment();
				pc.setPcNo(rset.getInt("pc_no"));
				pc.setPcWriter(rset.getString("pc_writer"));
				pc.setPcContent(rset.getString("pc_content"));
				pc.setPcDate(rset.getString("pc_date"));
				pc.setPcRef(rset.getInt("pc_ref"));
				list.add(pc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int updatePhoto(Connection conn, Photo p) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="update photo set photo_title=?, photo_content=?, photo_filename=?, photo_filepath=? where photo_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, p.getPhotoTitle());
			pstmt.setString(2, p.getPhotoContent());
			pstmt.setString(3, p.getPhotoFilename());
			pstmt.setString(4, p.getPhotoFilepath());
			pstmt.setInt(5, p.getPhotoNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deletePhoto(Connection conn, int photoNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="delete from photo where photo_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, photoNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertComment(Connection conn, PhotoComment pc) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="insert into photo_comment values (pc_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pc.getPcWriter());
			pstmt.setString(2, pc.getPcContent());
			pstmt.setInt(3, pc.getPcRef());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateComment(Connection conn, int pcNo, String pcContent) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="update photo_comment set pc_content=? where pc_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pcContent);
			pstmt.setInt(2, pcNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int pcNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="delete from photo_comment where pc_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, pcNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Photo> orderPhoto(Connection conn, int start, int end, int type) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<Photo> list=new ArrayList<>();
		String query="";
		if(type==1) {
			query="select pp.*,(select count(*) from photo_comment where pc_ref=pp.photo_no) as \"pc_count\" from (select rownum as rnum, p.* from (select * from photo order by photo_no desc)p)pp where rnum between ? and ? order by \"pc_count\" desc";
		}else if(type==2) {
			query="select pp.*,(select count(*) from photo_comment where pc_ref=pp.photo_no) as \"pc_count\" from (select rownum as rnum, p.* from (select * from photo order by photo_no desc)p)pp where rnum between ? and ? order by read_count desc";
		}
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Photo p=new Photo();
				p.setPhotoNo(rset.getInt("photo_no"));
				p.setPhotoTitle(rset.getString("photo_title"));
				p.setPhotoWriter(rset.getString("photo_writer"));
				p.setPhotoContent(rset.getString("photo_content"));
				p.setReadCount(rset.getInt("read_count"));
				p.setRegDate(rset.getString("reg_date"));
				p.setPhotoFilename(rset.getString("photo_filename"));
				p.setPhotoFilepath(rset.getString("photo_filepath"));
				p.setPcCount(rset.getInt("pc_count"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
}
