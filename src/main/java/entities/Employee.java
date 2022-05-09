package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;

@DbTable(name = "EMPLOYEE")
public class Employee extends AbstractComponent {
    @DbColumnNumber(name = "employee_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idEmployee;

    @DbColumnVarchar(name = "surname", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String surname;

    @DbColumnVarchar(name = "name", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String name;

    @DbColumnVarchar(name = "patronymic", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String patronymic;

    @DbColumnBoolean(name = "sex", constrains = @DbConstrains(isAllowedNull = false))
    private Boolean sex;

    @DbColumnVarchar(name = "university", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String university;

    @DbColumnDate(name = "birth_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar birthDate;

    @DbColumnNumber(name = "work_experience", constrains = @DbConstrains(isAllowedNull = false))
    private Integer workExperience;

    @DbColumnDate(name = "employment_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar employmentDate;

    @DbColumnNumber(name = "salary", constrains = @DbConstrains(isAllowedNull = false))
    private Float salary;

    @DbColumnNumber(name = "children_number", constrains = @DbConstrains(isAllowedNull = false))
    private Integer childrenNumber;

    @DbColumnNumber(name = "department_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer departmentId;

    @DbColumnNumber(name = "crew_id")
    private Integer crewId;

    @DbColumnNumber(name = "specialty_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer specialtyId;

    public Employee() {
        super(Employee.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdEmployeeAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("idEmployee").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSurnameAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("surname").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getNameAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("name").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getPatronymicAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("patronymic").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getSexAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("sex").getAnnotation(DbColumnBoolean.class).name();
    }

    public static String getUniversityAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("university").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getBirthDateAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("birthDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getWorkExperienceAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("workExperience").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getEmploymentDateDateAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("employmentDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getSalaryAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("salary").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getChildrenNumberAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("childrenNumber").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDepartmentIdAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("departmentId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getCrewIdAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("crewId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSpecialtyIdAnnotationName() throws NoSuchFieldException {
        return Employee.class.getDeclaredField("specialtyId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public TimeCalendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(TimeCalendar birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        this.workExperience = workExperience;
    }

    public TimeCalendar getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(TimeCalendar employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Integer getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(Integer childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Employee.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException {
        return new String[0][];
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {

    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {

    }
}
