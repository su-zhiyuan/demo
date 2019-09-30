package com.qppi.crud.dao;

import com.qppi.crud.bean.RepoChange;
import com.qppi.crud.bean.RepoChangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepoChangeMapper {
    long countByExample(RepoChangeExample example);

    int deleteByExample(RepoChangeExample example);

    int deleteByPrimaryKey(String repochangeId);

    int insert(RepoChange record);

    int insertSelective(RepoChange record);

    List<RepoChange> selectByExample(RepoChangeExample example);

    RepoChange selectByPrimaryKey(String repochangeId);

    int updateByExampleSelective(@Param("record") RepoChange record, @Param("example") RepoChangeExample example);

    int updateByExample(@Param("record") RepoChange record, @Param("example") RepoChangeExample example);

    int updateByPrimaryKeySelective(RepoChange record);

    int updateByPrimaryKey(RepoChange record);
}