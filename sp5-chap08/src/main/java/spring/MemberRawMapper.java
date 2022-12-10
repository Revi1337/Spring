package spring;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// TODO RowMapper 가 겹칠때는 따로 빼고 RowMapper 를 재구현하여 사용하면 편함. (MemberDao 의 selectAll2 메서드 보면됨.)
public class MemberRawMapper implements RowMapper {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member(
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getTimestamp("REGDATE").toLocalDateTime()
        );
        member.setId(rs.getLong("ID"));
        return member;
    }
}
