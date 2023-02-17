package io.github.liuruinian.phone.store.detail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author liuruinian
 * @since 2023-02-17
 */
@Slf4j
public class JdbcBindDetailStore implements BindDetailStore {

    private final JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "bind_detail";

    private static final String FIELDS = "request_id,code,message,pool_key,subs_id,status,extension,phone_no_a,phone_no_b,phone_no_x,group_id," +
            "gmt_create,expire_date,need_record,call_restrict,asr_status,asr_model_id";

    private static final String DEFAULT_ADD_BIND_DETAIL_STATEMENT = String.format("insert into %s ( %s ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", TABLE_NAME, FIELDS);

    private String addBindDetailStatement = DEFAULT_ADD_BIND_DETAIL_STATEMENT;

    public JdbcBindDetailStore(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setAddBindDetailStatement(String addBindDetailStatement) {
        this.addBindDetailStatement = addBindDetailStatement;
    }

    @Override
    public boolean addBindDetails(Collection<BindDetail> details) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("adding bind detail records: [%s]", details));
        }

        boolean success = true;
        for (BindDetail detail : details) {
            if (!updateBindDetail(addBindDetailStatement, detail)) {
                success = false;
            }
        }

        return success;
    }

    private boolean updateBindDetail(String sql, BindDetail detail) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("refreshing bind detail record: [%s]", detail));
        }

        int refreshed = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, detail.getRequestId());
                ps.setString(2, detail.getCode());
                ps.setString(3, detail.getMessage());
                ps.setString(4, detail.getPoolKey());
                ps.setString(5, detail.getSubsId());
                ps.setLong(6, detail.getStatus());
                ps.setString(7, detail.getExtension());
                ps.setString(8, detail.getPhoneNoA());
                ps.setString(9, detail.getPhoneNoB());
                ps.setString(10, detail.getPhoneNoX());
                ps.setString(11, String.valueOf(detail.getGroupId()));
                ps.setString(12, detail.getGmtCreate());
                ps.setString(13, detail.getExpireDate());
                ps.setString(14, String.valueOf(detail.getNeedRecord()));
                ps.setString(15, detail.getCallRestrict());
                ps.setString(16, String.valueOf(detail.getAsrStatus()));
                ps.setString(17, detail.getAsrModelId());
            }
        });

        return refreshed == 1;
    }
}
