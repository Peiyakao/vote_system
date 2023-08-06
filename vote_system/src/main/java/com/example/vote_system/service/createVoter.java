package com.example.vote_system.service;

import com.example.vote_system.respository.DboneConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
@Component
public class createVoter implements Voter {
    private DboneConn dboneConn;

    @Autowired
    public void CreateVoter(DboneConn dboneConn) {
        this.dboneConn = dboneConn;
    }
    @Override

    public int vote(SendResult sendResult) {

        String insertQuery = "INSERT INTO voting_record (`votingName`, `votingItem`) VALUES (?,?)";
        final AtomicInteger counter = new AtomicInteger(0);

        try {
            dboneConn.getJdbcTemplate().batchUpdate(insertQuery, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setString(1, HtmlUtils.htmlEscape(sendResult.getVoter()));
                    preparedStatement.setString(2, sendResult.getSelection().get(i).toString());
                    counter.incrementAndGet();
                }
                @Override
                public int getBatchSize() {
                    return sendResult.getSelection().size();
                }
            }
            );

            System.out.println("成功將選票資料寫入資料庫。");
        } catch (DataAccessException e) {
            System.err.println("寫入資料庫時發生錯誤：" + e.getMessage());
        }

        return counter.get();
    }

    @Override
    public VoteList getcandidate() {
        String sqlQuery = "SELECT vi.votingName, COUNT(vr.id) as voteCount " +
                "FROM voting_item vi " +
                "JOIN voting_record vr ON vi.id = vr.votingItem " +
                "GROUP BY vi.id, vi.votingName " +
                "ORDER BY voteCount DESC";
        try {

                List<Object[]> result = dboneConn.getJdbcTemplate().query(sqlQuery, new RowMapper<Object[]>() {
                    @Override
                    public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Object[] rowData = new Object[2];
                        rowData[0] = rs.getString("votingName");
                        rowData[1] = rs.getInt("voteCount");
                        return rowData;
                    }
                });

                VoteList voteList = new VoteList();
                voteList.setVotelist(result);

                return voteList;
            } catch (DataAccessException e) {
                System.err.println("執行查詢時發生錯誤：" + e.getMessage());
                return null;
            }
        }

    @Override
    public int addcandidate(Map<String,String> newcandidate) {
        String votingItem = newcandidate.get("newCandidate");
        // 防止XSS攻擊
        votingItem = HtmlUtils.htmlEscape(votingItem);
        String sqlQuery ="INSERT INTO voting_item (`votingItem`) VALUES (?)";
        try{
            int rowsAffected = dboneConn.getJdbcTemplate().update(sqlQuery, votingItem);
            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Transactional
    public int removecandidate(Map<String,String> removeCandidate) {
        String votingItem = removeCandidate.get("removeCandidate");
        String sqlQuery = "DELETE FROM voting_item, voting_record WHERE voting_item.votingItem = ? AND voting_record.votingItem = ?";
        try {
            int rowsAffected = dboneConn.getJdbcTemplate().update(sqlQuery, votingItem, votingItem);

            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
