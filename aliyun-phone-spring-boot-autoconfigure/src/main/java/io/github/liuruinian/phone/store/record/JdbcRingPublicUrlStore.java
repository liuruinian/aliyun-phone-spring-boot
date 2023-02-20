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

/**
 * @author liuruinian
 * @since 2023-02-20
 */
@Slf4j
public class JdbcRingPublicUrlStore implements RingPublicUrlStore {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<RingPublicUrl> rowMapper = new RingPublicRowMapper();

    private static final String TABLE_NAME = "ring_public_url";

    private static final String FIELDS = "call_id,call_time,ring_public_url,phone_public_url";

    private static final String QUERY_FIELDS = "id,call_id,call_time,ring_public_url,phone_public_url,create_time,update_time";

    private static final String WHERE_KEY = "where call_id = ?";

    private static final String DEFAULT_GET_RING_PUBLIC_URL_SQL = String.format("select %s from %s " + WHERE_KEY, QUERY_FIELDS, TABLE_NAME);

    private static final String DEFAULT_ADD_RING_PUBLIC_URL_STATEMENT = String.format("insert into %s ( %s ) values (?,?,?,?)", TABLE_NAME, FIELDS);

    private String addRingPublicUrlStatement = DEFAULT_ADD_RING_PUBLIC_URL_STATEMENT;

    private String findRingPublicUrlStatement = DEFAULT_GET_RING_PUBLIC_URL_SQL;

    public JdbcRingPublicUrlStore(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setAddRingPublicUrlStatement(String addRingPublicUrlStatement) {
        this.addRingPublicUrlStatement = addRingPublicUrlStatement;
    }

    public void setFindRingPublicUrlStatement(String findRingPublicUrlStatement) {
        this.findRingPublicUrlStatement = findRingPublicUrlStatement;
    }

    @Override
    public boolean addRingPublicUrls(Collection<RingPublicUrl> records) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("adding ring public urls: [%s]", records));
        }

        boolean success = true;
        for (RingPublicUrl record : records) {
            if (!updateRingPublicUrl(addRingPublicUrlStatement, record)) {
                success = false;
            }
        }

        return success;
    }

    private boolean updateRingPublicUrl(String sql, RingPublicUrl record) {
        int refreshed = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, record.getCallId());
                ps.setString(2, record.getCallTime());
                ps.setString(3, record.getRingPublicUrl());
                ps.setString(4, record.getPhonePublicUrl());
            }
        });

        return refreshed == 1;
    }

    @Override
    public List<RingPublicUrl> queryRingPublicUrl(String callId) {
        return jdbcTemplate.query(findRingPublicUrlStatement, rowMapper, callId);
    }

    private static class RingPublicRowMapper implements RowMapper<RingPublicUrl> {
        @Override
        public RingPublicUrl mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String callId = rs.getString("call_id");
            String callTime = rs.getString("call_time");
            String ringPublicUrl = rs.getString("ring_public_url");
            String phonePublicUrl = rs.getString("phone_public_url");
            String createTime = rs.getString("create_time");
            String updateTime = rs.getString("update_time");

            return new RingPublicUrl(id, callId, callTime, ringPublicUrl, phonePublicUrl, createTime, updateTime);
        }
    }
}
