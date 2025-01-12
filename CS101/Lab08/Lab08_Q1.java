/**
 * Lab08_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Lab08_Q1 {
    public static void main(String[] args) {
        int choice;
        Scanner scan = new Scanner(System.in);
        System.out.print("Press 1 for Tester 2 for EmployeeApp:");
        choice = scan.nextInt();
        if(choice == 1){
            ArrayList<Project> proj = new ArrayList<Project>();
            Project pro1 = new Project( "ArcTech Business Solution", 200000, 10, 4);
            Project pro2 = new Project("SunMarkets POS Implementation", 300000, 15, 26);
            proj.add(pro1);
            proj.add(pro2);
            System.out.println(pro1);
            System.out.println(pro2);
            pro1.setProjectWeeks(20);
            pro2.setRatePerHour(50);
            System.out.println(pro1);
            System.out.println(pro2);
            for(int count = 0; count<proj.size();count++){
                boolean inactive = false;
                for(int count2 = count+1;count2<proj.size();count2++){
                    if((proj.get(count)).getProjectType().equals("i") && !inactive){
                        System.out.println((proj.get(count)).getProjectName()+"is INACTIVE!!!");
                        inactive = true;
                    }
                    else if((proj.get(count)).calculatePersonResources()>(proj.get(count2)).calculatePersonResources() ){
                        System.out.println((proj.get(count)).getProjectName()+"   requires a larger team than   "+(proj.get(count2)).getProjectName());
                    }
                    else{
                        System.out.println((proj.get(count2)).getProjectName()+"   requires a larger team than   "+(proj.get(count)).getProjectName());
                    }
                    
                }
            }

        }
        else if(choice == 2){
            Project proo = new Project("ArcTech Business Solution", 200000, 10, 4);
            Employee emp1 = new Employee("  Rocca, Denis",70,proo.getProjectName(), "Human Resources", "HR");
            Employee emp2 = new Employee(" Karakus, Zana",50,proo.getProjectName(), "Information Technology",  "IT");
            Employee emp3 = new Employee(" Anders, Jamie",40,proo.getProjectName(), "Human Resources", "HR");
            Employee empCop = new Employee(emp1);
            ArrayList<Employee> dept = new ArrayList<Employee>();
            dept.add(emp1);
            dept.add(emp2);
            dept.add(emp3);
            dept.add(empCop);


            System.out.println("Employees:");
            System.out.println(emp1);
            System.out.println(proo);
            System.out.println();
            System.out.println(emp2);
            System.out.println(proo);
            System.out.println();
            System.out.println(emp3);
            System.out.println(proo);
            System.out.println();
            System.out.println(empCop);
            System.out.println(proo);
            System.out.println();
            System.out.println();
            System.out.println("--------- end employee list ----------");
            int matchC;
            matchC = 0;
            for(int count = 0; count<dept.size();count++){

                for(int count2 = count+1;count2<dept.size();count2++){

                    if((dept.get(count)).getDepartment().equals((dept.get(count2)).getDepartment())){
                        System.out.println();
                        System.out.printf("Employees with Matching Departments(%d)",matchC+1);
                        System.out.println(dept.get(count));
                        System.out.println(dept.get(count2));
                        matchC++;
                    }
                    
                }
            }
        }
        
    }
}
