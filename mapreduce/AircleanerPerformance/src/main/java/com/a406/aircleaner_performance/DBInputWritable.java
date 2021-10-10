package com.a406.aircleaner_performance;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class DBInputWritable implements Writable, DBWritable {

    private Long pcode;
    private Long price;

    // 각자 product 테이블 컬럼명에 맞는 형태로 수정
    private String type;
    private String area;
    private String dust;
    private String spec;
    private int ranking;

    public void write(PreparedStatement statement) throws SQLException {
        statement.setLong(1, pcode);
        statement.setLong(2, price);
        statement.setString(3, type);
        statement.setString(4, area);
        statement.setString(5, dust);
        statement.setString(6, spec);
        statement.setInt(7, ranking);
    }
    public void readFields(ResultSet resultSet) throws SQLException {
        pcode = resultSet.getLong(1);
        price = resultSet.getLong(2);
        type = resultSet.getString(3);
        area = resultSet.getString(4);
        dust = resultSet.getString(5);
        spec = resultSet.getString(6);
        ranking = resultSet.getInt(7);
    }
    public void write(DataOutput out) throws IOException {
    }
    public void readFields(DataInput in) throws IOException {
    }

    public Long getPcode() {
        return pcode;
    }

    public Long getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getArea() {
        return area;
    }

    public String getDust() {
        return dust;
    }

    public String getSpec() {
        return spec;
    }

    public int getRanking() {
        return ranking;
    }
}
