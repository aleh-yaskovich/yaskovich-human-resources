package com.epam.brest.repository;

import com.epam.brest.entity.DepartmentDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDtoMapper implements RowMapper<DepartmentDto> {
    @Override
    public DepartmentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
        departmentDto.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
        departmentDto.setAvgSalary(rs.getInt("AVG_SALARY"));
        return departmentDto;
    }
}
