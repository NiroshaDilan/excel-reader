package lk.icoder.excelreader.model;

import lombok.*;

/**
 * @Project excel-reader
 * @Author DILAN on 10/7/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto {
    private String name;
    private String email_address;
    private String age;
}
