package com.fight.mapper;

import com.fight.pojo.Commimages;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CommimagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Commimages record);

    int insertSelective(Commimages record);

    Commimages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Commimages record);

    int updateByPrimaryKey(Commimages record);

    List<Commimages> selectImageBy(Integer commid);

    Integer deleteImagByCommid(Integer commid);
}
