package com.work.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.work.model.dao.DeleteMemberDao;
import com.work.model.dao.JdbcTemplate;
import com.work.model.dto.BusinessMemberDto;
import com.work.model.dto.MemberDto;

public class DeleteMemberBiz {
	DeleteMemberDao dao = DeleteMemberDao.getInstance();
	/**사업자 회원 탈퇴 */
	public void businessDelete(BusinessMemberDto dto) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			ArrayList<String> boardNumList = new ArrayList<String>();
			dao.findMainBoardList(conn, dto,boardNumList);
			if(boardNumList.size() != 0) {
				for(String boardNum : boardNumList) {
					dao.deletereview(conn, boardNum);
				}
			}
			dao.deleteMainBoard(conn, dto);
			dao.deleteQboard(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
		}finally {
			JdbcTemplate.close(conn);
		}
	}
	/** 일반 회원 탈퇴 */
	public void memberDelete(MemberDto dto) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.deletereview(conn, dto);
			dao.deleteQboard(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
		}finally {
			JdbcTemplate.close(conn);
		}
	}
}
