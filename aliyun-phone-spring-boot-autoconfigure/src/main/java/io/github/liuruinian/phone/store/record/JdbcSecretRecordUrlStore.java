package io.github.liuruinian.phone.store.record;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Slf4j
public class JdbcSecretRecordUrlStore implements SecretRecordUrlStore {

    private final JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "secret_record_url";

    private static final String FIELDS = "call_id,call_time,download_url";

    private static final RowMapper<SecretRecordUrl> rowMapper = new SecretRecordRowMapper();

    private static final String WHERE_KEY = "where call_id = ?";

    private static final String DEFAULT_GET_SECRET_RECORD_URL_SQL = String.format("select %s from %s " + WHERE_KEY, FIELDS, TABLE_NAME);

    private static final String DEFAULT_ADD_SECRET_RECORD_URL_STATEMENT = String.format("insert into %s ( %s ) values (?,?,?)", TABLE_NAME, FIELDS);

    private String addSecretRecordUrlStatement = DEFAULT_ADD_SECRET_RECORD_URL_STATEMENT;

    private String findSecretRecordUrlStatement = DEFAULT_GET_SECRET_RECORD_URL_SQL;

    public JdbcSecretRecordUrlStore(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setAddSecretRecordUrlStatement(String addSecretRecordUrlStatement) {
        this.addSecretRecordUrlStatement = addSecretRecordUrlStatement;
    }

    public void setFindSecretRecordUrlStatement(String findSecretRecordUrlStatement) {
        this.findSecretRecordUrlStatement = findSecretRecordUrlStatement;
    }

    @Override
    public boolean addSecretRecordUrls(Collection<SecretRecordUrl> records) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("adding secret record urls: [%s]", records));
        }

        boolean success = true;
        for (SecretRecordUrl record : records) {
            if (!updateSecretRecordUrl(addSecretRecordUrlStatement, record)) {
                success = false;
            }
        }

        return success;
    }

    private boolean updateSecretRecordUrl(String sql, SecretRecordUrl record) {
        int refreshed = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, record.getCallId());
                ps.setString(2, record.getCallTime());
                ps.setString(3, record.getDownloadUrl());
            }
        });

        return refreshed == 1;
    }

    @Override
    public List<SecretRecordUrl> querySecretRecordUrl(String callId) {
        return jdbcTemplate.query(findSecretRecordUrlStatement, rowMapper, callId);
    }

    private static class SecretRecordRowMapper implements RowMapper<SecretRecordUrl> {
        @Override
        public SecretRecordUrl mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String callId = rs.getString("call_id");
            String callTime = rs.getString("call_time");
            String downloadUrl = rs.getString("download_url");
            String createTime = rs.getString("create_time");
            String updateTime = rs.getString("update_time");

            return new SecretRecordUrl(id, callId, callTime, downloadUrl, createTime, updateTime);
        }
    }
}
