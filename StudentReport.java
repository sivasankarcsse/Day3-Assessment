package studentdemo;

public class StudentReport {
    int studentId;
    String studentName;
    String course;
    int mark;
    boolean result;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public StudentReport(int studentId, String studentName, String course, int mark, boolean result) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.course = course;
        this.mark = mark;
        this.result = result;
    }
    @Override
    public String toString() {
        return studentName + " – " + course + " – " + mark;
    }
   /* @Override
    public String toString() {
        return "StudentReport{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", course='" + course + '\'' +
                ", mark=" + mark +
                ", result=" + result +
                '}';
    }*/
}

