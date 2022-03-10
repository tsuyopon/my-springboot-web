package com.example.demo.service;

import com.example.demo.dao.InquiryDao;
import com.example.demo.entity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * InquiryServiceインターフェースのImplファイル
 *
 * Controllerから呼び出されるクラスで、このserviceからdaoへのデータ取得要求を行います。
 */

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryDao dao;

    @Autowired InquiryServiceImpl(InquiryDao dao){
        this.dao = dao;
    }


    @Override
    public void save(Inquiry inquiry){
        dao.insertInquiry(inquiry);
    }

    @Override
    public void update(Inquiry inquiry) {
        if(dao.updateInquiry(inquiry) == 0){
            throw new InquiryNotFoundException("can't find the same ID");
        }
    }

    @Override
    public List<Inquiry> getAll(){
        return dao.getAll();
    }

}
