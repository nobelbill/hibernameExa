package com.multicampus.biz.board.client;

import java.util.List;

import org.hibernate.Session;

import com.multicampus.biz.board.vo.BoardVO;
import com.multicampus.service.util.SessionFactoryBean;

public class BoardServiceClient {
	public static void main(String[] args) {
		try {
			SessionFactoryBean.beginTransaction();
			Session session = SessionFactoryBean.getCurrentSession();
			// 새글 등록
			BoardVO board = new BoardVO();
			board.setTitle("가입인사");
			board.setWriter("홍길동");
			board.setContent("잘 부탁드립니다.");
			session.save(board);
//			session.delete(board);
			
//			board.setTitle("---> 제목 수정");
			
			// 글목록 조회
			getBoardList(session);
			SessionFactoryBean.commitTransaction();
		} catch (Exception e) {
			SessionFactoryBean.rollbackTransaction();
		} finally {
			SessionFactoryBean.closeSession();
		}
	}

	public static void getBoardList(Session session) {
		List<BoardVO> result = session.createQuery("from BoardVO").list();
		for (BoardVO board : result) {
			System.out.println(board);
		}
	}
}
