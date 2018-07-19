package cn.yang.dao;

import cn.yang.domain.BaseDict;

import java.util.List;

public interface BaseDictDao {
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
