package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.model.vo.BdComment;
import board.model.vo.Board;
import common.JDBCTemplate;

public class BoardDao {

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		System.out.println(b.getBoardWriter());//컬럼순서가 안맞는데요 erd로 뽑다가 외래키가 지정이 안되서 컬럼삭제하고 다시 넣어서 그래요..
		//그러니까 순서가안맞으니까 지금 작성자가 3번째위치홀더에들어가고 filepath가 4번째에 들어가는데 디비컬럼은 4번째위치홀더자리가 작성자니까 없다고나오겠죠아아 순서가 잘못되구나 위치홀더..알겠습니다.
		int result = 0;
		String query = "insert into board values(board_seq.nextval,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getBoardFilepath());
			pstmt.setString(4, b.getBoardWriter());
			pstmt.setString(5, b.getBoardFilename());
			pstmt.setInt(6, b.getBoardLevel());
			pstmt.setString(7, b.getDeadLine());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Board> selectBoardList(Connection conn, int start, int end,int listFind) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<Board>();
		String query = "";
		if(listFind==1) {
			query = "SELECT BB.*,(select count (*) from BD_comment where BOARD_REF=BB.BOARD_NO) AS \"BD_COUNT\"FROM (SELECT ROWNUM AS RNUM, B.*FROM (SELECT * FROM BOARD where board_level =1 or board_level =2 ORDER BY BOARD_NO DESC)B)BB WHERE RNUM BETWEEN ? AND ?";
		}else if(listFind == 2) {
			query = "SELECT BB.*,(select count (*) from BD_comment where BOARD_REF=BB.BOARD_NO) AS \"BD_COUNT\"FROM (SELECT ROWNUM AS RNUM, B.*FROM (SELECT * FROM BOARD where board_level =1 ORDER BY BOARD_NO DESC)B)BB WHERE RNUM BETWEEN ? AND ?";
		}else if(listFind == 3) {
			query = "SELECT BB.*,(select count (*) from BD_comment where BOARD_REF=BB.BOARD_NO) AS \"BD_COUNT\" FROM (SELECT ROWNUM AS RNUM, B.* FROM (SELECT * FROM BOARD WHERE BOARD_LEVEL=2 AND TO_DATE(DEADLINE,'YYYY-MM-DD')-SYSDATE > 0 ORDER BY 1 DESC)B)BB WHERE RNUM BETWEEN ? AND ?";
		}
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardWriter(rset.getString("board_writer"));
				b.setReadCount(rset.getInt("read_count"));
				b.setBoardLevel(rset.getInt("board_level"));
				b.setRegDate(rset.getString("reg_date"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBdCount(rset.getInt("bd_count"));
				b.setDeadLine(rset.getString("deadline"));
				list.add(b);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public int selectTotalCount(Connection conn,int listFind) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		String query = "";
		if(listFind==1) {
			query="select count(*) as cnt from board where board_level=1 or board_level=2";
		}else if(listFind==2) {
			query="select count(*) as cnt from board where board_level=1";
		}else if(listFind==3) {
			query="select count(*) as cnt from board where board_level=2 and  TO_DATE(DEADLINE,'YYYY-MM-DD')-SYSDATE > 0";
		}
		try {
			pstmt=conn.prepareStatement(query);
			rset= pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result =0;
		String query = "update board set read_count = read_count+1 where board_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Board selectOneBoard(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		String query ="select*from board where board_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();
					if(rset.next()) {
						b=new Board();
						b.setBoardNo(rset.getInt("board_no"));
						b.setBoardTitle(rset.getString("board_title"));
						b.setBoardContent(rset.getString("board_content"));
						b.setBoardWriter(rset.getString("board_writer"));
						b.setReadCount(rset.getInt("read_count"));
						b.setRegDate(rset.getString("reg_date"));
						b.setBoardFilename(rset.getString("board_filename"));
						b.setBoardFilepath(rset.getString("board_filepath"));
						b.setBoardLevel(rset.getInt("board_level"));
						b.setDeadLine(rset.getString("deadline"));
					}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	public ArrayList<BdComment> selectCommentList(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BdComment> list = new ArrayList<BdComment>();
		String query = "select*from bd_comment where board_ref=? order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				BdComment bc = new BdComment();
				bc.setBdNo(rset.getInt("bd_no"));
				bc.setBdContent(rset.getString("bd_content"));
				bc.setBdWriter(rset.getString("bd_writer"));
				bc.setBdDate(rset.getString("bd_date"));
				bc.setBoardRef(rset.getInt("board_ref"));
				list.add(bc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int deleteBoard(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from board where board_no=?";
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result =0;
		String query = "update board set board_title=?, board_content=? , board_filename=?,board_filepath=?,deadline=? where board_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getBoardFilename());
			pstmt.setString(4, b.getBoardFilepath());
			pstmt.setString(5, b.getDeadLine());
			pstmt.setInt(6, b.getBoardNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Board> selectOneBoard(Connection conn, int start, int end, String type, String keyword,int listFind) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list  = new ArrayList<Board>();
		String query ="";
		if(listFind==1) {
			if(type.equals("title")) {
				query="SELECT*FROM (SELECT ROWNUM AS RNUM, B.*FROM (SELECT * FROM BOARD WHERE BOARD_TITLE LIKE ? ORDER BY BOARD_NO DESC)B) WHERE RNUM BETWEEN ? AND ?";
			}else {
				query="SELECT*FROM (SELECT ROWNUM AS RNUM, B.*FROM (SELECT * FROM BOARD WHERE BOARD_WRITER LIKE ? ORDER BY BOARD_NO DESC)B) WHERE RNUM BETWEEN ? AND ?";
			}			
		}else if(listFind==2) {
			if(type.equals("title")) {
				query="SELECT*FROM (SELECT ROWNUM AS RNUM, B.*FROM (SELECT * FROM BOARD WHERE  board_level = 1 and BOARD_TITLE LIKE ? ORDER BY BOARD_NO DESC)B) WHERE RNUM BETWEEN ? AND ?";
			}else {
				query="SELECT*FROM (SELECT ROWNUM AS RNUM, B.*FROM (SELECT * FROM BOARD WHERE board_level = 1 and BOARD_WRITER LIKE ? ORDER BY BOARD_NO DESC)B) WHERE RNUM BETWEEN ? AND ?";
			}		
		}else if(listFind==3) {
			if(type.equals("title")) {
				query="SELECT*FROM (SELECT ROWNUM AS RNUM, B.*FROM (SELECT * FROM BOARD WHERE  board_level = 2 and BOARD_TITLE LIKE ? and TO_DATE(DEADLINE,'YYYY-MM-DD')-SYSDATE > 0 ORDER BY BOARD_NO DESC)B) WHERE RNUM BETWEEN ? AND ?";
			}else {
				query="SELECT*FROM (SELECT ROWNUM AS RNUM, B.*FROM (SELECT * FROM BOARD WHERE  board_level = 2 and BOARD_TITLE LIKE ? and TO_DATE(DEADLINE,'YYYY-MM-DD')-SYSDATE > 0 ORDER BY BOARD_NO DESC)B) WHERE RNUM BETWEEN ? AND ?";
			}	
		}
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardWriter(rset.getString("board_writer"));
				b.setReadCount(rset.getInt("read_count"));
				b.setBoardLevel(rset.getInt("board_level"));
				b.setRegDate(rset.getString("reg_date"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setDeadLine(rset.getString("deadline"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public int selectTotalCount(Connection conn, String type, String keyword,int listFind) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		int result = 0;
		String query ="";
		if(listFind==3) {
			if(type.equals("title")) {
				query = "select count(*) as cnt from board where to_date(deadline,'yyyy-mm-dd')-sysdate>0 and board_title like ?";
			}else {
				query ="select count(*) as cnt from board where to_date(deadline,'yyyy-mm-dd')-sysdate>0 and board_writer like ?";
			}			
		}else {
			if(type.equals("title")) {
				query = "select count(*) as cnt from board where board_title like ?";
			}else {
				query ="select count(*) as cnt from board where and board_writer like ?";
			}	
		}
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%"); 
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertComment(Connection conn, BdComment bd) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into bd_comment values(bd_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bd.getBdContent());
			pstmt.setString(2, bd.getBdWriter());
			pstmt.setInt(3, bd.getBoardRef());
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteComment(Connection conn, int bdNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="delete from bd_comment where bd_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bdNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateComment(Connection conn, int bdNo, String bdContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update bd_comment set bd_content=? where bd_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bdContent);
			pstmt.setInt(2, bdNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
