public class Employee {
    public final int WORKING_DAYS = 261;
    private String employeeName;
    private int dailyRate;
    private Department department;
    private String Project;
    private String deptName;
    private String Deptcode;
    public Employee(String employeeName, int dailyRate,String Project, String deptName,String Deptcode){
        this.employeeName = employeeName;
        this.dailyRate = dailyRate;
        this.Project = Project;
        this.department = new Department(deptName, Deptcode);
        this.deptName = deptName;
        this.Deptcode = Deptcode;
    }
    public Employee(Employee emp){
        this.employeeName = emp.employeeName;
        this.dailyRate = emp.dailyRate;
        this.Project = emp.Project;
        this.department = new Department(emp.deptName, emp.Deptcode);
    }
    public String geteEmployeeName(){
        return employeeName;
    }
    public int getdDailyRate(){
        return dailyRate;
    }
    public Department getDepartment(){
        return department;
    }
    public String getProject(){
        return Project;
    }
    private int calculateYearlySalary(){
        return dailyRate*WORKING_DAYS;
    }
    public String toString(){
        return "Employee Name:"+ employeeName +"Yearly Salary:"+ calculateYearlySalary()+"\n"+ department.toString();
    }


}
