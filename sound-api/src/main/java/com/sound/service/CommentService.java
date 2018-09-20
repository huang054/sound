package com.sound.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.CommentDAO;
import com.sound.model.CommentModel;
import com.sound.utils.Page;
import com.sound.vo.CommentVo;
 
@Service
public class CommentService extends BaseService<CommentModel>{
 
	@Autowired
	private CommentDAO commentDAO;
	public List<CommentVo> findComments(long newsId,Page page) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Object> objs = commentDAO.findComments(newsId,page.getStartPos(),page.getPageSize());
		List<CommentVo> commentVos = new ArrayList<CommentVo>();
		CommentVo c = null;
		if(null==objs||objs.size()==0) {
			return null;
		}
		for(Object obj:objs) {
			c=new CommentVo();
		Object[] rowArray = (Object[])obj;
	    c.setId(Long.parseLong(rowArray[0].toString()));
	    c.setContext(rowArray[1].toString());
	    c.setCreateTime(sdf.parse(rowArray[2].toString()));
	    c.setName(rowArray[3].toString());
	    c.setUrl(rowArray[4].toString());
	    commentVos.add(c);
		
		}
		
		return commentVos;
		
	}
	public int findCount(long newsId) {
		return commentDAO.findCount(newsId);
	}
}
