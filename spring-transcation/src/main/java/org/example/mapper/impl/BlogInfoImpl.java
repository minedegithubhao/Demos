package org.example.mapper.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.entity.BlogInfo;
import org.example.mapper.BlogInfoMapper;

import java.util.List;

public class BlogInfoImpl implements BlogInfoMapper {

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    @Override
    public List<BlogInfo> selectAll() {
        return sqlSession.getMapper(BlogInfoMapper.class).selectAll();
    }
}
