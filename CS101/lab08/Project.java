import java.time.LocalDate;

public class Project {
    public final int TAX = 13;
    public final int OVERHEAD = 50000;
    public final int EMP_HOURS_WEEK = 45;
    public static int projectCounter = 5000;

    private String projectId;
    private String projectName;
    private String projectType;
    private int personHours;
    private int ratePerHour;
    private int projectWeeks;

    public Project(String projectName,int personHours,int ratePerHour,int projectWeeks ){
        projectCounter++;
        this.projectName = projectName;
        this.setProjectId();
        
        this.setPersonHours(personHours);
        this.setRatePerHour(ratePerHour);
        this.setProjectWeeks(projectWeeks);
        setProjectType();
    }

    public String getProjectName(){
        return projectName;
    }
    public String getProjectId(){
        return projectId;
    }
    public String getProjectType(){
        return projectType;
    }
    public int getPersonHours(){
        return personHours;
    }
    public int getRatePerHour(){
        return ratePerHour;
    }
    public int getProjectWeeks(){
        return projectWeeks;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName ;
    }
    public void setPersonHours(int personHours){
        if(personHours>0){
            this.personHours = personHours ;
        }
        else{
            this.personHours = 0;
        }
        
    }
    public void setRatePerHour(int ratePerHour){
        if(ratePerHour>0){
            this.ratePerHour = ratePerHour;
        }
        else{
            this.ratePerHour = 0;
        }
    }
    public void setProjectWeeks(int projectWeeks){
        if(projectWeeks>0){
            this.projectWeeks = projectWeeks;
        }
        else{
            this.projectWeeks = 0;
        }
    }

    private void setProjectId(){
        projectId = LocalDate.now().getYear() + "-" + projectCounter;
    }
    private void setProjectType(){
        double proCost;
        
        proCost = calculateProjectCost();

        if(proCost>1000000){
            this.projectType = "m";
        }
        else if(proCost>500000 && proCost<1000000){
            this.projectType = "l";
        }
        else if(proCost>0 && proCost<500000){
            this.projectType = "s";
        }
        else{
            this.projectType = "i";
        }

    }
    public void deactivateProject(){
        projectType = "i";
        personHours = 0;
        ratePerHour = 0;
    }
    public void activateProject(int perHour,int rPer){
        personHours = perHour;
        ratePerHour = rPer;
        setProjectType();
    }
    public double calculateProjectCost(){
        int cost;
        cost = personHours*ratePerHour;
        if(cost>=20000){
            cost+= OVERHEAD;
        }
        cost = cost*(100+TAX)/100;
        return cost;
    }
    public int calculatePersonResources(){
        return personHours/(EMP_HOURS_WEEK*projectWeeks);
    }
    public boolean Compare(Project pro){
        if(pro.calculatePersonResources() > this.calculatePersonResources()){
            return true;
        }
        return false;

    }
    public String toString(){
        if(!projectType.equals("i")){
            /*Project Name: ArcTech Business Solution
            Project ID: 2023-5001
            Project Type: m
            Team Size: 5
            Estimated Project Cost: 2855284.0*/
            return "Project Name:"+this.projectName + "\nProject ID:"+this.projectId + "\nProject Type:"+this.projectType +"\nTeam Size:" +calculatePersonResources() +"\nEstimated Project Cost:"+calculateProjectCost()+"\n";
        }
        else{
            return "Project Name:"+this.projectName + "\nProject ID:"+this.projectId+"\n";
        }
    }
    








}
