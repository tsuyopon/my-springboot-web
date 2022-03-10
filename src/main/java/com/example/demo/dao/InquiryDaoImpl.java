package com.example.demo.dao;


import com.example.demo.entity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

@Repository
public class InquiryDaoImpl implements InquiryDao {

    private final JdbcTemplate jdbcTemplate;

    // @Autowiredにより、DIコンテナで生成されたインスタンスが引数に注入される
    @Autowired
    public InquiryDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // お問い合わせ情報が登録された際の更新処理
    @Override
    public void insertInquiry(Inquiry inquiry){
        jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES(?, ?, ?, ?)",
                inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());

    }

    // お問い合わせ一覧情報を取得して一覧で返す
    @Override
    public List<Inquiry> getAll(){
        String sql = "SELECT id, name, email, contents, created FROM inquiry";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<Inquiry> list = new ArrayList<Inquiry>();
        for(Map<String, Object> result: resultList){
            Inquiry inquiry = new Inquiry();
            inquiry.setId((int)result.get("id"));
            inquiry.setName((String) result.get("name"));
            inquiry.setEmail((String) result.get("email"));
            inquiry.setContents((String) result.get("contents"));
            inquiry.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
            list.add(inquiry);
        }

        return list;
    }

}
