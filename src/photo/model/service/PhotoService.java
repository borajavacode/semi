package photo.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import photo.model.dao.PhotoDao;
import photo.model.vo.Photo;
import photo.model.vo.PhotoComment;
import photo.model.vo.PhotoPageData;
import photo.model.vo.PhotoViewData;

public class PhotoService {

	public PhotoPageData selectPhotoList(int reqPage) {
		Connection conn=JDBCTemplate.getConnection();
		int numPerPage=9;
		// reqPage=1 -> start=1 / end=9; reqPage=2 -> start=10 / end=18;
		int end=reqPage*numPerPage;
		int start=end-numPerPage+1;
		PhotoDao dao=new PhotoDao();
		ArrayList<Photo> list=dao.selectPhotoList(conn,start,end);
		
		int totalCount=dao.selectTotalCount(conn);
		int totalPage=0;
		if(totalCount%numPerPage==0) {
			totalPage=totalCount/numPerPage;
		}else {
			totalPage=totalCount/numPerPage+1;
		}
		
		int pageNaviSize=5;
		int pageNo=((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi="<ul class='pagination'>";
		// 이전 버튼
		if(pageNo!=1) {
			pageNavi+="<li class='page-item'>";
			pageNavi+="<a class='page-link' href='/photoList?reqPage="+(pageNo-1)+"'>&lt;</a></li>";
		}
		// 페이지 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi+="<li class='page-item active'>";
				pageNavi+="<a class='page-link' href='/photoList?reqPage="+pageNo+"'>";
				pageNavi+=pageNo+"</a></li>";
			}else {
				pageNavi+="<li class='page-item'>";
				pageNavi+="<a class='page-link' href='/photoList?reqPage="+pageNo+"'>";
				pageNavi+=pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		// 다음 버튼
		if(pageNo<=totalPage) {
			pageNavi+="<li class='page-item'>";
			pageNavi+="<a class='page-link' herf='/photoList?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi+="</ul>";
		PhotoPageData ppd=new PhotoPageData(list, pageNavi, start);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public int insertPhoto(Photo p) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new PhotoDao().insertPhoto(conn,p);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public PhotoViewData selectOnePhoto(int photoNo) {
		Connection conn=JDBCTemplate.getConnection();
		PhotoDao dao=new PhotoDao();
		int result=dao.updateReadCount(conn,photoNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Photo p=dao.selectOnePhoto(conn,photoNo);
		ArrayList<PhotoComment> list=dao.selectCommentList(conn,photoNo);
		PhotoViewData pvd=new PhotoViewData(list, p);
		JDBCTemplate.close(conn);
		return pvd;
	}

	public Photo getPhoto(int photoNo) {
		Connection conn=JDBCTemplate.getConnection();
		Photo p=new PhotoDao().selectOnePhoto(conn, photoNo);
		JDBCTemplate.close(conn);
		return p;
	}

	public int updatePhoto(Photo p) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new PhotoDao().updatePhoto(conn,p);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deletePhoto(int photoNo) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new PhotoDao().deletePhoto(conn,photoNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertComment(PhotoComment pc) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new PhotoDao().insertComment(conn,pc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateComment(int pcNo, String pcContent) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new PhotoDao().updateComment(conn,pcNo,pcContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int deleteComment(int pcNo) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new PhotoDao().deleteComment(conn, pcNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public PhotoPageData orderPhoto(int reqPage, int type) {
		Connection conn=JDBCTemplate.getConnection();
		int numPerPage=9;
		// reqPage=1 -> start=1 / end=9; reqPage=2 -> start=10 / end=18;
		int end=reqPage*numPerPage;
		int start=end-numPerPage+1;
		PhotoDao dao=new PhotoDao();
		ArrayList<Photo> list=dao.orderPhoto(conn,start,end,type);
		
		int totalCount=dao.selectTotalCount(conn);
		int totalPage=0;
		if(totalCount%numPerPage==0) {
			totalPage=totalCount/numPerPage;
		}else {
			totalPage=totalCount/numPerPage+1;
		}
		
		int pageNaviSize=5;
		int pageNo=((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi="<ul class='pagination'>";
		// 이전 버튼
		if(pageNo!=1) {
			pageNavi+="<li class='page-item'>";
			pageNavi+="<a class='page-link' href='/photoList?reqPage="+(pageNo-1)+"&type"+type+"'>";
			pageNavi+="&lt;</a></li>";
		}
		// 페이지 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi+="<li class='page-item active'>";
				pageNavi+="<a class='page-link' href='/photoList?reqPage="+pageNo+"&type"+type+"'>";
				pageNavi+=pageNo+"</a></li>";
			}else {
				pageNavi+="<li class='page-item'>";
				pageNavi+="<a class='page-link' href='/photoList?reqPage="+pageNo+"&type"+type+"'>";
				pageNavi+=pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		// 다음 버튼
		if(pageNo<=totalPage) {
			pageNavi+="<li class='page-item'>";
			pageNavi+="<a class='page-link' herf='/photoList?reqPage="+pageNo+"&type"+type+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi+="</ul>";
		PhotoPageData ppd=new PhotoPageData(list, pageNavi, start);
		
		JDBCTemplate.close(conn);
		return ppd;
	}
}
