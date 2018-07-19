package cn.yang.service.impl;

import cn.yang.dao.BaseDictDao;
import cn.yang.domain.BaseDict;
import cn.yang.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {
    private BaseDictDao baseDictDao;

    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {

        return baseDictDao.getListByTypeCode(dict_type_code);


    }

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }
}