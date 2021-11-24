package faq.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import faq.model.dao.FaqDao;
import faq.model.vo.Faq;
import faq.model.vo.FaqCo;
import faq.model.vo.FaqPageData;
import faq.model.vo.FaqViewData;

public class FaqService {

	public int insertFaq(Faq f) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FaqDao().insertFaq(conn,f);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public FaqPageData selectFaqList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int numPerPage =20;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		FaqDao dao = new FaqDao();
		ArrayList<Faq> list = dao.selectFaqList(conn,start,end);
		
		int totalCount = dao.selectTotalCount(conn);
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize=5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/faqList?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "&lt;</a></li>"; // "<" 표현 => &lt;
		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/faqList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>"; // 엑티브만빼주면 됨
				pageNavi += "<a class='page-link' href='/faqList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/faqList?reqPage=" + pageNo + "'>";
			pageNavi += "&gt;</a></li>";// ">" 표현 &gt
		}
		pageNavi += "</ul>";
		FaqPageData fpd = new FaqPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return fpd;
	}

	public FaqPageData searchFaq(int reqPage, String type, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		
		int numPerPage =20;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		FaqDao dao = new FaqDao();
		ArrayList<Faq> list = dao.selectSearchFaq(conn,start,end,type,keyword);
		
		int totalCount = dao.selectTotalCount(conn,type,keyword);
		int totalPage=0;
		if(totalCount%numPerPage==0) {
			totalPage=totalCount/numPerPage;
		}else {
			totalPage=totalCount/numPerPage+1;
		}
		int pageNaviSize=5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/faqList?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "&lt;</a></li>"; // "<" 표현 => &lt;
		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/faqList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>"; // 엑티브만빼주면 됨
				pageNavi += "<a class='page-link' href='/faqList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/faqList?reqPage=" + pageNo + "'>";
			pageNavi += "&gt;</a></li>";// ">" 표현 &gt
		}
		pageNavi += "</ul>";
		FaqPageData fpd = new FaqPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return fpd;
	}
	public FaqViewData selectOneFaq(int faqNo) {
		Connection conn = JDBCTemplate.getConnection();
		FaqDao dao = new FaqDao();
		Faq f = dao.selectOneFaq(conn,faqNo);
		
		ArrayList<FaqCo> list = dao.selectCommentList(conn,faqNo);
		
		FaqViewData fvd = new FaqViewData(list, f);
		JDBCTemplate.close(conn);
		return fvd;
	}
	public Faq getOneFaq(int faqNo) {
		Connection conn  = JDBCTemplate.getConnection();
		Faq f = new FaqDao().selectOneFaq(conn, faqNo);
		JDBCTemplate.close(conn);
		return f;
	}

	public int deleteFaq(int faqNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FaqDao().deleteFaq(conn,faqNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateFaq(Faq f) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FaqDao().updateFaq(conn,f);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertComment(FaqCo fc) {
		Connection conn =JDBCTemplate.getConnection();
		int result =new FaqDao().insertComment(conn,fc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public static int deleteCommentFc(int faqCoNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FaqDao().deleteCommentFc(conn,faqCoNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateCommentFaq(int faqCoNo, String fcContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FaqDao().updateCommentFaq(conn,faqCoNo,fcContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}



}
