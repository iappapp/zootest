package com.github.iappapp.service;

import com.github.iappapp.dao.domain.CustInfo;

import java.util.List;

public interface CustInfoService {
    List<CustInfo> listCustInfo(CustInfo custInfo);

    int insertCustInfo(CustInfo custInfo);
}
