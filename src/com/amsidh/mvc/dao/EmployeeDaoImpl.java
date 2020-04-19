/**
 * 
 */
package com.amsidh.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.amsidh.mvc.dto.Employee;

/**
 * @author VIRU
 * 
 */
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.amsidh.mvc.dao.EmployeeDao#createEmployee(com.amsidh.mvc.dto.Employee
	 * )
	 */
	@Override
	public void createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String sql = new String(
				"INSERT INTO EMPLOYEE ( EMPLOYEEID , EMPLOYEENAME ) VALUES ( ? , ? )");
		jdbcTemplate.update(sql, new Object[] { employee.getEmployeeId(),
				employee.getEmployeeName() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amsidh.mvc.dao.EmployeeDao#removeEmployee(java.lang.Integer)
	 */
	@Override
	public void removeEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		String sql = new String("DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?");
		jdbcTemplate.update(sql, new Object[] { employeeId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.amsidh.mvc.dao.EmployeeDao#updateEmployee(com.amsidh.mvc.dto.Employee
	 * )
	 */
	@Override
	public void updateEmployee(Employee employee) {
		String sql = new String(
				"UPDATE EMPLOYEE SET EMPLOYEENAME=? WHERE EMPLOYEEID=?");
		jdbcTemplate.update(sql, new Object[] { employee.getEmployeeName(),
				employee.getEmployeeId() });

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amsidh.mvc.dao.EmployeeDao#retriveEmployee(java.lang.Integer)
	 */
	@Override
	public Employee retriveEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		String sql = new String("SELECT * FROM EMPLOYEE WHERE EMPLOYEEID = ?");
		Employee employee = jdbcTemplate.queryForObject(sql,
				new Object[] { employeeId }, new EmployeeRowMapper());
		return employee != null ? employee : new Employee();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amsidh.mvc.dao.EmployeeDao#getAllEmployee()
	 */
	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		String sql = new String("SELECT * FROM EMPLOYEE");
		List<Employee> employeeList = jdbcTemplate.query(sql,
				new EmployeeRowMapper());
		return employeeList.size() > 0 ? employeeList : null;
	}

	/**
	 * @author VIRU
	 * 
	 */
	class EmployeeRowMapper implements RowMapper<Employee> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		@Override
		public Employee mapRow(ResultSet rst, int rownum) throws SQLException {
			Employee employee = new Employee();
			employee.setEmployeeId(rst.getInt("EMPLOYEEID"));
			employee.setEmployeeName(rst.getString("EMPLOYEENAME"));
			return employee;
		}

	}
}
