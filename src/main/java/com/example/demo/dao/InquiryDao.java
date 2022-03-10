package com.example.demo.dao;

import com.example.demo.entity.Inquiry;

import java.util.List;

public interface InquiryDao {
    void insertInquiry(Inquiry inquiry);
    int updateInquiry(Inquiry inquiry);
    List<Inquiry> getAll();
}
