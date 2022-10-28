package com.professional.yang.mapperTest;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface testMapper {
	List<HashMap<Object, Object>> selectAll(HashMap<Object, Object> vo);
}
