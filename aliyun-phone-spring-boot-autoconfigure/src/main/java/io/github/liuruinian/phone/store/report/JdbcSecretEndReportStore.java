package io.github.liuruinian.phone.store.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.util.Assert;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
@Slf4j
public class JdbcSecretEndReportStore implements SecretEndReportStore {

    private final JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "secret_end_report";

    private static final String FIELDS = "pool_key,sub_id,download_url,ring_record_url,call_id,phone_no,secret_no,peer_no,called_display_no,call_type" +
            ",call_time,start_time,call_out_time,ring_time,free_ring_time,release_time,sms_number,release_dir,out_id,unconnected_cause,release_cause,release_cause_describe," +
            "control_msg";

    private static final String DEFAULT_ADD_SECRET_END_REPORT_STATEMENT = String.format("insert into %s ( %s ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", TABLE_NAME,
            FIELDS);

    private String addSecretEndReportStatement = DEFAULT_ADD_SECRET_END_REPORT_STATEMENT;

    public JdbcSecretEndReportStore(DataSource dataSource) {
        Assert.notNull(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setAddSecretStartReportStatement(String addSecretEndReportStatement) {
        this.addSecretEndReportStatement = addSecretEndReportStatement;
    }

    @Override
    public boolean addSecretEndReports(Collection<SecretEndReport> reports) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("adding secret end reports: [%s]", reports));
        }

        boolean success = true;
        for (SecretEndReport report : reports) {
            if (!updateSecretEndReport(addSecretEndReportStatement, report)) {
                success = false;
            }
        }
        return success;
    }

    private boolean updateSecretEndReport(String sql, SecretEndReport report) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("refreshing secret end report: [%s]", report));
        }

        int refreshed = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, report.getPoolKey());
                ps.setLong(2, report.getSubId());
                ps.setString(3, report.getRecordUrl());
                ps.setString(4, report.getRingRecordUrl());
                ps.setString(5, report.getCallId());
                ps.setString(6, report.getPhoneNo());
                ps.setString(7, report.getSecretNo());
                ps.setString(8, report.getPeerNo());
                ps.setString(9, report.getCalledDisplayNo());
                ps.setInt(10, report.getCallType());
                ps.setString(11, report.getCallTime());
                ps.setString(12, report.getStartTime());
                ps.setString(13, report.getCallOutTime());
                ps.setString(14, report.getRingTime());
                ps.setString(15, report.getFreeRingTime());
                ps.setString(16, report.getReleaseTime());
                ps.setInt(17, Optional.ofNullable(report.getSmsNumber()).orElse(0));
                ps.setInt(18, report.getReleaseDir());
                ps.setString(19, report.getOutId());
                ps.setInt(20, report.getUnconnectedCause());
                ps.setInt(21, report.getReleaseCause());
                ps.setString(22, report.getReleaseCauseDescribe());
                ps.setString(23, report.getControlMsg());

            }
        });

        return refreshed == 1;
    }
}
