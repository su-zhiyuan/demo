package com.qppi.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qppi.crud.bean.Memo;
import com.qppi.crud.bean.MemoExample;
import com.qppi.crud.dao.MemoMapper;

@Service
public class MemoService {
	@Autowired
	private MemoMapper memoMapper;
	
	/**
	 * 查询列表 
	 * @param memo
	 * @return
	 */
	public List<Memo> list(Memo memo,String username){
		MemoExample example = new MemoExample();
		MemoExample.Criteria criteria = example.createCriteria();
		criteria.andYl1EqualTo(username);
		String createtime = memo.getCreatetime();
		if(null != createtime && !"".equals(createtime)){
			criteria.andCreatetimeEqualTo(createtime);
		}
		String content = memo.getContent();
		if(null != content && !"".equals(content)){
			criteria.andContentLike("%"+content+"%");
		}
		
		return memoMapper.selectByExample(example);
	}
	
	/**
	 * 获取对象
	 * @param memo
	 * @return
	 */
	public Memo get(Memo memo){
		return memoMapper.selectByPrimaryKey(memo.getMemoId());
	}
	
	/**
	 * 增加对象
	 * @param memo
	 * @return
	 */
	public boolean add(Memo memo){
		try{
			memoMapper.insertSelective(memo);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param memo
	 * @return
	 */
	public boolean update(Memo memo){
		try{
			memoMapper.updateByPrimaryKeySelective(memo);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param memo
	 * @return
	 */
	public boolean delite(Memo memo){
		try{
			memoMapper.deleteByPrimaryKey(memo.getMemoId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
