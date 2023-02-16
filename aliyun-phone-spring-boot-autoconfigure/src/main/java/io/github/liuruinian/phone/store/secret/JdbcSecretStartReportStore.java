package io.github.liuruinian.phone.store.secret;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
@Slf4j
public class JdbcSecretStartReportStore implements SecretStartReportStore {

    private final JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "secret_start_report";

    private static final String FIELDS = "pool_key,sub_id,call_id,phone_no,secret_no,peer_no,called_display_no,call_type,call_time,out_id,control_msg";

    private static final String DEFAULT_ADD_SECRET_START_REPORT_STATEMENT = String.format("insert into %s ( %s ) values (?,?,?,?,?,?,?,?,?,?,?)", TABLE_NAME,
            FIELDS);

    private String addSecretStartReportStatement = DEFAULT_ADD_SECRET_START_REPORT_STATEMENT;

    public JdbcSecretStartReportStore(DataSource dataSource) {
        Assert.notNull(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setAddSecretStartReportStatement(String addSecretStartReportStatement) {
        this.addSecretStartReportStatement = addSecretStartReportStatement;
    }

    @Override
    public boolean addSecretStartReports(Collection<SecretStartReport> reports) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("adding secret start reports: [%s]", reports));
        }

        boolean success = true;
        for (SecretStartReport report : reports) {
            if (!updateSecretStartReport(addSecretStartReportStatement, report)) {
                success = false;
            }
        }
        return success;
    }

    private boolean updateSecretStartReport(String sql, SecretStartReport report) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("refreshing secret start report: [%s]", report));
        }

        int refreshed = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, report.getPoolKey());
                ps.setLong(2, report.getSubId());
                ps.setString(3, report.getCallId());
                ps.setString(4, report.getPhoneNo());
                ps.setString(5, report.getSecretNo());
                ps.setString(6, report.getPeerNo());
                ps.setString(7, report.getCalledDisplayNo());
                ps.setInt(8, report.getCallType());
                ps.setString(9, report.getCallTime());
                ps.setString(10, report.getOutId());
                ps.setString(11, report.getControlMsg());
            }
        });

        return refreshed == 1;
    }
}
