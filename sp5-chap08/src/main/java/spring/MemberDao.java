package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MemberDao {

    // TODO 1. Jdbc Template 생성
    // TODO 스프링을 사용하면 DataSource, Connection, Statement, ResultSet 을 직접 사용하지 않고 JdbcTemplate 를 이용해서 편리하게 쿼리를 실행할 수 있음.
    // TODO JdbcTemplate 객체를 생성하려면 인자로 DataSource 를 전달해주면 된다.
    // TODO DataSource 를 주입받을 수 있도록 생성자로 주입패턴으로 진행
    private JdbcTemplate jdbcTemplate;
    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // TODO 2. JdbcTemplate 을 이용한 조회(SELECT) 쿼리 실행
    // TODO JdbcTemplate 의 query() 메서드를 사용해서 쿼리를 실행. (argv1: 쿼리문. ? 은 인덱스를 의미, argv2: RowMapper 를 이용해서 ResultSet 의 결과를 자바 객체로 변환. 람다식으로도 구현할 수 있는데 람다식이 더 간결함.)
    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query("select * from `MEMBER` where EMAIL = ?", new RowMapper<Member>() {
            // TODO RowMapper 의 mapRow() 메서드는 SQL 실행 결과로 구한 ResultSet 에서 한행의 데이터를 읽어와 자바 객체로 변환하는 매퍼의 기능을 구현함.
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
        }, email);  // TODO 쿼리문에 들어간 ? 인덱스가 차례대로 , 를 구분으로 들어감
        return results.isEmpty() ? null : results.get(0);   // TODO results 가 비어있는 경우와 그렇지 않은 경우를 구분해서 리턴 값을 처리
    }


    // TODO 2. 람다식으로도 사용 가능 (이놈이 더 간단함.)
    public Member selectByEmail2(String email){
        List<Member> results = jdbcTemplate.query("select * from `MEMBER` where EMAIL = ?", (ResultSet rs, int rowNum) -> {
            Member member = new Member(
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime()
            );
            member.setId(rs.getLong("ID"));
            return member;
        }, email);
        return results.isEmpty() ? null : results.get(0);
    }


    // TODO 2. JdbcTemplate 을 이용한 모든 쿼리 조회(SELECT ALL) 실행
    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("select * from `MEMBER`", (ResultSet rs, int rowNum) -> {
            Member member = new Member(
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime()
            );
            member.setId(rs.getLong("ID"));
            return member;
        });
        return results;
    }


    // TODO 2. JdbcTemplate 를 이용할때 동일한 RowMapper() 를 사용하면 아래와 같이 사용 가능. (RowMapper()를 implements 하여 새로 구현하는 방법. `MemberRawMapper` 참조)
    public List<Member> selectAll2() {
        List<Member> results = jdbcTemplate.query("select * from `MEMBER`", new MemberRawMapper());
        return results;
    }


    // TODO 3. 결과가 1개의 행 (row) 인 경우 사용할 수 있는 JdbcTemplate 의 queryForObject() 메서드
    // TODO queryForObject() 메서드 의 argv1 : 쿼리문. ? 는 인덱스, argv2 : column 을 읽어올 때 사용할 타입을 지정
    // TODO 만약 인덱스 파라미터인 ? 가 있으면 argv3 에 , 를 기준으로 넣으면 된다.
    // TODO 실행결과 Column 이 두개 이상이면 argv2 에 RowMapper() 를 파라미터로 전달해서 결과를 생성할 수 있음.
    // TODO query() 메서드와 차이점은 queryForObject() 메서드는 리턴 타입이 List 가 아니라 RowMapper 로 변환해주는 타입이라는 점임.
    // TODO 쿼리 결과는 한행이어야함. 행이 없거나 두개 이상이면 IncorrectResultSizeDataAccessException 발생.
    // TODO 쿼리 결과가 0 개 이면 EmptyResultDataAccessException 발생. 따라서 결과 행이 정확히 한개가 아니면 queryForObject() 말고 query() 메서드를 사용해야 함 .
    public int count(){
        Integer count = jdbcTemplate.queryForObject("select count(*) from `MEMBER`", Integer.class);
        return count;
    }


    // TODO 4. Jdbc 를 이용한 변경 쿼리 실행 (update). 리턴값으로 변경된 row 의 개수를 리턴함.
    public void update(Member member){
        jdbcTemplate.update("update `MEMBER` set NAME = ?, PASSWORD = ? where EMAIL = ?",
                member.getName(), member.getPassword(), member.getEmail());
    }


    // TODO 5. INSERT 쿼리 실행 시 KeyHolder 를 이용해서 자동 생성 키 값 구하기
    // TODO 일반적으로 MYSQL 에서 자동증가값 인 Auto_Increment 를 설정하면 Insert 쿼리문 실행 시 ID 컬럼을 지정하지 않음.
    // TODO 여기서 할 수 있는 생각은 Insert 수행 후 생성한 ID 는 어케 알아야할까라는 생각인데, 그 방법이 바로 KeyHolder 사용하는 것임.
    public void insert(final Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();     // TODO GeneratedKeyHolder 객체를 생성. 이 클래스는 자동 생성된 키 값을 구해주는 KeyHolder 의 구현 클래스임.
        jdbcTemplate.update(new PreparedStatementCreator() {    // TODO update() 메서드는 PreparedStatementCreate 객체와 KeyHolder 객체를 파라미터로 갖음.
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement("insert into `MEMBER` (EMAIL, PASSWORD, NAME, REGDATE) " + "values (?, ?, ?, ?)", new String[] {"ID"});

                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));

                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue()); // TODO update() 메서드는 PreparedStatement 를 실행한 후 자동 생성된 키값을 KeyHoler 에 보관함. KeyHolder 에 보관된 키 값은 getKey() 메서드를 통해서 구함. Number 를 리턴하므로 intValue() 혹은 longValue() 등 메서드를 사용해서 원하는 타입의 값으로 변환할 수 있음.
    }


    // TODO 6. PreparedStatement 를 이용한 쿼리 실행
    // TODO PreparedStatementCreater 인터페이스의 createPreparedStatement() 메서드는 Connection 타입의 파라미터를 갖음.
    // TODO PreparedStatementCreater 를 구현한 클래스는 createPreparedStatement() 메서드의 파라미터로 전달받는 Connection 을 이용해서
    // TODO PreparedStatement 객체를 생성하고 인덱스 파라미터를 알맞게 설정한 뒤에 리턴하면 됨.
    public void insert2(Member member){
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // TODO 파라미터로 전달받은 Connection 을 이용해서 PreparedStatement 생성
                PreparedStatement pstmt = con.prepareStatement("insert into `MEMBER` (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)");

                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));

                // TODO 생성한 PreparedStatement 객체 리턴
                return pstmt;
            }
        });
    }

}
