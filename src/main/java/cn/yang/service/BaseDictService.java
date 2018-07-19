package cn.yang.service;

import cn.yang.domain.BaseDict;

import java.util.List;

public interface BaseDictService {
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
