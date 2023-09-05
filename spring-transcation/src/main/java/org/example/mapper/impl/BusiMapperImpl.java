package org.example.mapper.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.entity.BusiInfo;
import org.example.mapper.BusiInfoMapper;

public class BusiMapperImpl implements BusiInfoMapper {

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void update(BusiInfo busiInfo) {
        sqlSession.getMapper(BusiInfoMapper.class).update(busiInfo);
    }

    @Override
    public void delete(BusiInfo busiInfo) {
        sqlSession.getMapper(BusiInfoMapper.class).delete(busiInfo);
    }
}
