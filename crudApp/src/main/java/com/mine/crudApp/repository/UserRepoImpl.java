package com.mine.crudApp.repository;

import com.mine.crudApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl implements UserRepository{

    private static final String INSERT_USER_QUERY="INSERT INTO user (id,fname,lname,email) values(?,?,?,?)";
    private static final String UPDATE_USER_BY_ID_QUERY="UPDATE user SET fname=? where id =?";
    private static final String DELETE_USER_BY_ID="DELETE FROM user WHERE id=?";
    private static final String GET_USERS_QUERY="SELECT * FROM user";
    private static final String GET_USER_BY_ID_QUERY="SELECT * FROM user WHERE id=? ";


    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public User saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY,user.getId(),user.getFname(),user.getLname(),user.getEmail());
        return user;
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY,user.getFname(),user.getId());
        return user;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY,(rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("email")),id);
    }

    @Override
    public String deleteByID(int id) {
         jdbcTemplate.update(DELETE_USER_BY_ID,id);
         return "User got deleted with id: "+id;
    }

    @Override
    public List<User> allUsers() {
        return jdbcTemplate.query(GET_USERS_QUERY,(rs, rowNum) ->{

            return new User(
                    rs.getInt("id"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("email"));
        });
    }
//@Override
//public List<User> allUsers() {
//        UserRowMapper umap=new UserRowMapper();
//
//    return jdbcTemplate.query(GET_USERS_QUERY,umap);
//}
}


//public class EmployeeRowMapper implements RowMapper<Employee> {
//    @Override
//    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Employee User = new Employee();
//
//        employee.setId(rs.getInt("ID"));
//        employee.setFirstName(rs.getString("FIRST_NAME"));
//        employee.setLastName(rs.getString("LAST_NAME"));
//        employee.setAddress(rs.getString("ADDRESS"));
//
//        return employee;
//    }
//}
//Subsequently, we can now pass the row mapper to the query API and get fully populated Java objects:
//
//String query = "SELECT * FROM EMPLOYEE WHERE ID = ?";
//Employee employee = jdbcTemplate.queryForObject(query, new EmployeeRowMapper(), id);