package lk.icoder.excelreader.config;

import lk.icoder.excelreader.model.StudentDto;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Project excel-reader
 * @Author DILAN on 10/7/2021
 */
public class StudentExcelRowMapper implements RowMapper<StudentDto> {

//    @Override
//    public StudentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//        StudentDto student = new StudentDto();
//
//        student.setName(rs.getString(0));
//        student.setEmailAddress(rs.getString(1));
//        student.setAge(rs.getString(2));
//
//        return student;
//    }

    @Override
    public StudentDto mapRow(RowSet rowSet) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(rowSet.getCurrentRow()[1]);
        studentDto.setEmail_address(rowSet.getCurrentRow()[2]);
        studentDto.setAge(rowSet.getCurrentRow()[3]);

        return studentDto;
    }
}
