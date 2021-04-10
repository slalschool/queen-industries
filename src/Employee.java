public class Employee implements Comparable {

    private int salary;
    private String name;
    private String department;
    private String titleString;
    private int tier;

    public Employee(int salary, String name, String department, String titleString) {

        this.salary = salary;
        this.name = name;
        this.department = department;
        this.titleString = titleString;
        this.tier = 1;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getTitleString() {
        return titleString;
    }

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}