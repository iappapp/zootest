package com.github.iappapp.dao.mapper;

import com.github.iappapp.dao.domain.CustInfo;

import java.util.List;

public interface CustInfoExtMapper {
    List<CustInfo> listCustInfo(CustInfo custInfo);

    int insertCustInfo(CustInfo custInfo);
}
