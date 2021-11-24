package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;
import notice.model.vo.NoticePageData;
import notice.model.vo.NoticeViewDate;

public class NoticeService {

	public NoticePageData selectNoticeList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int numPerPage = 10;
		int end = numPerPage * reqPage;
		int start = end-numPerPage+1;
		
		NoticeDao dao = new NoticeDao();
		ArrayList<Notice> list = dao.selectNoticeList(conn,start,end);
		
		int totalCount = dao.selectTotalCount(conn);
		int totalPage = 0;
		if(totalCount %numPerPage ==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		
		if(pageNo != 1) {
			pageNavi += "<li class='page-itme'>";
			pageNavi += "<a class='page-link' href='/noticeList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/noticeList?reqPage"+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/noticeList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/noticeList?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		NoticePageData npd = new NoticePageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return npd;
	}

	public NoticeViewDate selectOneNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = dao.updateReadCount(conn,noticeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Notice n = dao.selectOneNotice(conn,noticeNo);
		ArrayList<NoticeComment> list = dao.selectCommentList(conn,noticeNo);
		NoticeViewDate nvd = new NoticeViewDate(list, n);
		JDBCTemplate.close(conn);
		return nvd;
	}

	public Notice getNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		Notice n = new NoticeDao().selectOneNotice(conn, noticeNo);
		JDBCTemplate.close(conn);
		return n;
	}

	public int deleteNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().deleteNotice(conn,noticeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateNotice(Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().updateNotice(conn,n);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


	public int insertComment(NoticeComment nc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().insertComment(conn,nc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertNotice(Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().insertNotice(conn,n);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateComment(int ncNo, String ncContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().updateComment(conn,ncNo,ncContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteComment(int ncNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().deleteComment(conn,ncNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public NoticePageData searchNotice(int reqPage, String type, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		
		NoticeDao dao = new NoticeDao();
		ArrayList<Notice> list = dao.selectSearchNotice(conn,start,end,type,keyword);
		
		int totalCount = dao.selectTotalCount(conn,type,keyword);
				int totalPage = 0;
				if(totalCount%numPerPage == 0) {
					totalPage = totalCount/numPerPage;
				}else {
					totalPage = totalCount/numPerPage+1;
				}
				int pageNaviSize = 5;
				int pageNo = ((reqPage-1)/pageNaviSize) * pageNaviSize + 1 ;
				//페이지네비 태그 제작 시작
				String pageNavi = "<ul class='pagination pagination-lg'>";
				//이전버튼
				if(pageNo != 1) {
					pageNavi += "<li class='page-item'>";
					pageNavi += "<a class='page-link' href='/searchNotice?reqPage="+(pageNo-1)+"&type="+type+"&keyword="+keyword+"'>";
					pageNavi += "&lt;</a></li>";
				}
				//페이지숫자
				for(int i=0;i<pageNaviSize;i++) {
					if(pageNo == reqPage) {
						pageNavi += "<li class='page-item active'>";
						pageNavi += "<a class='page-link' href='/searchNotice?reqPage="+pageNo+"&type="+type+"&keyword="+keyword+"'>";
						pageNavi += pageNo+"</a></li>";
					}else {
						pageNavi += "<li class='page-item'>";
						pageNavi += "<a class='page-link' href='/searchNotice?reqPage="+pageNo+"&type="+type+"&keyword="+keyword+"'>";
						pageNavi += pageNo+"</a></li>";
					}
					pageNo++;
					if(pageNo>totalPage) {
						break;
					}
				}
				//다음버튼
				if(pageNo <= totalPage) {
					pageNavi += "<li class='page-item'>";
					pageNavi += "<a class='page-link' href='/searchNotice?reqPage="+pageNo+"'>";
					pageNavi += "&gt;</a></li>";
				}
				pageNavi += "</ul>";
				NoticePageData npd = new NoticePageData(list, pageNavi,start);
				JDBCTemplate.close(conn);
				return npd;
	}
	
}
