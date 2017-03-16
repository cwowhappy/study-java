package cwowhappy.study.reflection;

import java.time.LocalDate;

/**
 * @author cwowhappy
 * 2017-02-22 Wednesday
 */
public class Student extends Person {
    private LocalDate enrollDate;
    private String schoolName;

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
