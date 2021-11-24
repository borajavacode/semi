package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.BdComment;
import board.model.vo.Board;
import board.model.vo.BoardPageData;
import board.model.vo.BoardViewData;
import common.JDBCTemplate;

public class BoardService {

	public int insertBoard(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().insertBoard(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public BoardPageData selectBoardList(int reqPage,int listFind) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage=20;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		BoardDao dao= new BoardDao();
		ArrayList<Board> list = dao.selectBoardList(conn,start,end,listFind);
		
		int totalCount = dao.selectTotalCount(conn,listFind);
		int totalPage=0;
		if(totalCount % numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage=totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo=((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (pageNo - 1) + "&listFind="+listFind+"'>";
			pageNavi += "&lt;</a></li>";
		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "&listFind="+listFind+"'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>"; // 엑티브만빼주면 됨
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "&listFind="+listFind+"'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "&listFind="+listFind+"'>";
			pageNavi += "&gt;</a></li>";// ">" 표현 &gt
		}
		pageNavi += "</ul>";
		
		BoardPageData bpd = new BoardPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return bpd;
	}

	public BoardViewData selectOneBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		//제목눌었을때 조회수늘리기
		BoardDao dao = new BoardDao();
		int result = dao.updateReadCount(conn,boardNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Board b = dao.selectOneBoard(conn,boardNo);
		
		//댓글
		ArrayList<BdComment> list = dao.selectCommentList(conn,boardNo);
		
		BoardViewData bvd = new BoardViewData(list, b);
		JDBCTemplate.close(conn);
		return bvd;
	}

	public Board getOneBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		Board b = new BoardDao().selectOneBoard(conn, boardNo);
		JDBCTemplate.close(conn);
		return b;
	}

	public int deleteBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().deleteBoard(conn,boardNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateBoard(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().updateBoard(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public BoardPageData searchBoard(int reqPage, String type, String keyword,int listFind) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage=20;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		BoardDao dao= new BoardDao();
		ArrayList<Board> list = dao.selectOneBoard(conn,start,end,type,keyword,listFind);
		
		int totalCount = dao.selectTotalCount(conn,type,keyword,listFind);
		int totalPage=0;
		if(totalCount % numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage=totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo=((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (pageNo - 1) + "&listFind="+listFind+"'>";
			pageNavi += "&lt;</a></li>";
		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "&listFind="+listFind+"'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>"; // 엑티브만빼주면 됨
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "&listFind="+listFind+"'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "&listFind="+listFind+"'>";
			pageNavi += "&gt;</a></li>";// ">" 표현 &gt
		}
		pageNavi += "</ul>";
		
		BoardPageData bpd = new BoardPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		
		return bpd;
	}

	public int insertComment(BdComment bd) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().insertComment(conn,bd);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public static int deleteComment(int bdNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().deleteComment(conn,bdNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateComment(int bdNo, String bdContent) {
		Connection conn =JDBCTemplate.getConnection();
		int result = new BoardDao().updateComment(conn,bdNo,bdContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
