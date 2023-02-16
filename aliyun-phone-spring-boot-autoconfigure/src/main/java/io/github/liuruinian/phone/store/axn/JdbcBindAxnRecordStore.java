package io.github.liuruinian.phone.store.axn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
@Slf4j
public class JdbcBindAxnRecordStore implements BindAxnRecordStore {

    private final JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "bind_axn_record";

    private static final String FIELDS = "code,message,request_id,extension,secret_no,subs_id,pool_key";

    private static final String DEFAULT_ADD_BIND_AXN_RECORD_STATEMENT = String.format("insert into %s ( %s ) values (?,?,?,?,?,?,?)", TABLE_NAME, FIELDS);

    private String addBindAxnRecordStatement = DEFAULT_ADD_BIND_AXN_RECORD_STATEMENT;

    public JdbcBindAxnRecordStore(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setAddBindAxnRecordStatement(String addBindAxnRecordStatement) {
        this.addBindAxnRecordStatement = addBindAxnRecordStatement;
    }

    @Override
    public boolean addBindAxnRecords(Collection<BindAxnRecord> records) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("adding bind axn records: [%s]", records));
        }

        boolean success = true;
        for (BindAxnRecord record : records) {
            if (!updateBindAxnRecord(addBindAxnRecordStatement, record)) {
                success = false;
            }
        }

        return success;
    }

    private boolean updateBindAxnRecord(String sql, BindAxnRecord record) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("refreshing bind axn record: [%s]", record));
        }

        int refreshed = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, record.getCode());
                ps.setString(2, record.getMessage());
                ps.setString(3, record.getRequestId());
                ps.setString(4, record.getExtension());
                ps.setString(5, record.getSecretNo());
                ps.setString(6, record.getSubsId());
                ps.setString(7, record.getPoolKey());
            }
        });

        return refreshed == 1;
    }
}
