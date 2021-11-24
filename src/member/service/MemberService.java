package member.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import member.dao.MemberDao;
import member.vo.AdminChart;
import member.vo.Member;
import member.vo.MemberPageDate;

public class MemberService {

	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMember(conn,memberId);
		JDBCTemplate.close(conn);
		return member;
	}

	public Member selectOneMember(String memberId, String memberPw) {
		Connection conn= JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMember(conn,memberId, memberPw);
		JDBCTemplate.close(conn);
		return member;
	}

	public int insertMember(Member m) {
		Connection conn= JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Member findOneId(String memberName, String phone) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().findOneId(conn,memberName,phone);
		JDBCTemplate.close(conn);
		return member;
	}

	public Member findOnePw(String memberId, String phone) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().findOnePw(conn,memberId,phone);
		JDBCTemplate.close(conn);
		return member;
	}

	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn,member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Member> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = new MemberDao().selectAllMember(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public MemberPageDate selectAllMemberList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		MemberDao dao = new MemberDao();
		ArrayList<Member> list = dao.selectMemberList(conn,start,end);
		
		int totalCount = dao.selectTotalCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination' style='justify-content: center;'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page-item disabled'>";
			pageNavi +=  "<a class='page-link' href='/memberCtl?reqPage"+(pageNo-1)+"'>";
			pageNavi += "&laquo;</a></li>";
		}
		for(int i =0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/memberCtl?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/memberCtl?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/memberCtl?reqPage="+pageNo+"'>";
			pageNavi += "&raquo;</a><li>";
		}
		pageNavi += "</ul>";
		MemberPageDate mpd = new MemberPageDate(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return mpd;
	}

	public MemberPageDate searchMember(int reqPage, String type, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		
		MemberDao dao = new MemberDao();
		ArrayList<Member> list = dao.selectSearchMember(conn,start,end,type,keyword);
		
		int totalCount = dao.selectTotalCount(conn, type, keyword);
		int totalPage = 0;
		if(totalCount%numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination' style='justify-content: center;'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page-item disabled'>";
			pageNavi +=  "<a class='page-link' href='/memberCtl?reqPage="+(pageNo-1)+"&type="+type+"&keyword="+keyword+"'>";
			pageNavi += "&laquo;</a></li>";
		}
		for(int i =0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/memberCtl?reqPage="+pageNo+"&type="+type+"&keyword="+keyword+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/memberCtl?reqPage="+pageNo+"&type="+type+"&keyword="+keyword+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/memberCtl?reqPage="+pageNo+"&type="+type+"&keyword="+keyword+"'>";
			pageNavi += "&raquo;</a><li>";
		}
		pageNavi += "</ul>";
		MemberPageDate mpd = new MemberPageDate(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return mpd;
	}

	public boolean adminChkDelete(String num) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		MemberDao dao = new MemberDao();
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int memberNo = Integer.parseInt(sT1.nextToken());
			int result1 = dao.deleteMember(conn, memberNo);
			if(result1 == 0) {
				result = false;
				break;//하나라도 실패하면 빠져나간다.
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MemberPageDate allMemberNoList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		MemberDao dao = new MemberDao();
		ArrayList<Member> list = dao.memberNoList(conn,start,end);
		
		int totalCount = dao.selectTotalCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination' style='justify-content: center;'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page-item disabled'>";
			pageNavi +=  "<a class='page-link' href='/memberNoList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&laquo;</a></li>";
		}
		for(int i =0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/memberNoList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/memberNoList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/memberNoList?reqPage="+pageNo+"'>";
			pageNavi += "&raquo;</a><li>";
		}
		pageNavi += "</ul>";
		MemberPageDate mpd = new MemberPageDate(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return mpd;
	}

	public int totalCount() {
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new MemberDao().selectTotalCount(conn);
		JDBCTemplate.close(conn);
		return totalCount;
	}

	public AdminChart selectAdminChart() {
		Connection conn = JDBCTemplate.getConnection();
		AdminChart chart = new MemberDao().selectAdminChart(conn);
		JDBCTemplate.close(conn);
		return chart;
	}



}
