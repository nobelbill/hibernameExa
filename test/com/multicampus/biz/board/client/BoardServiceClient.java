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
			// ���� ���
			BoardVO board = new BoardVO();
			board.setTitle("�����λ�");
			board.setWriter("ȫ�浿");
			board.setContent("�� ��Ź�帳�ϴ�.");
			session.save(board);
//			session.delete(board);
			
//			board.setTitle("---> ���� ����");
			
			// �۸�� ��ȸ
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
