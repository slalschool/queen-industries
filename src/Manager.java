import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {

    private int tier;
    private int salary;
    private String name;
    private String department;
    private String titleString;
    protected int bonus;
    private ArrayList<Employee> reports;

    public Manager(int salary, int bonus, String name, String department, String titleString, ArrayList<Employee> reports, int tier) throws Exception {
        super(salary, name, department, titleString);

        for (Employee emp : reports) {
            if (emp.getTier() >= 3) throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
        }

        this.bonus = bonus;
        this.reports = reports;
        this.tier = tier;
    }

    public int getBonus() {
        return bonus;
    }

    public int getTier() {
        return tier;
    }

    public void hire(Manager manager) throws Exception {
        if (this.getTier() < manager.getTier()) {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        } else {
            reports.add(manager);
            System.out.println("LOG: new Employee hired (" + manager.getName() + ", " + manager.getDepartment() + ", " + manager.getTitleString() + ")");
        }
    }

    public void hire(Employee employee) throws Exception {
        if (this.getTier() < employee.getTier()) {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        } else {
            reports.add(employee);
            System.out.println("LOG: new Employee hired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitleString() + ")");
        }
    }

    public void fire(Manager manager) throws Exception {
        List<Employee> gone = manager.reports;

        if (this.getDepartment().equals(manager.getDepartment())) {
            this.reports.remove(manager);

            System.out.println("LOG: existing Employee fired (" + manager.getName() + ", " + manager.getDepartment() + ", " + manager.getTitleString() + ")");

            boolean test = false;

            String msg = "LOG: reports re-assigned [";
            for (int h = 0; h < this.reports.size(); h++) {
                if (this.reports.get(h).getDepartment().equals(manager.getDepartment())) {
                    for (Employee emp : gone) {
                        test = true;
                        this.reports.add(emp);
                        msg += emp.getName() + ", " + emp.getDepartment() + ", " + emp.getTitleString() + ", ";
                    }
                }
            }

            if (test == true){
                System.out.println(msg.substring(0, msg.length() - 2) + "]");
            }
        } else if (this.getTier() <= manager.getTier()){
            throw new Exception("ERROR: cannot fire an Employee of an equal or greater tier.");
        } else {
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
    }

    public void fire(Employee employee) throws Exception {
        if (this.getDepartment().equals(employee.getDepartment())) {
            reports.remove(employee);
            System.out.println("LOG: existing Employee fired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitleString() + ")");
        } else if (this.getTier() <= employee.getTier()){
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        } else {
            throw new Exception("ERROR: cannot fire an Employee of an equal or greater tier.");
        }
    }

    public void adjustSalary(int newSalary, Employee employee) throws Exception {
        boolean test = false;

        for (Employee mng : this.reports) {
            if (mng.getName().equals(employee.getName())) {
                test = true;
                break;
            }
            if (mng.getTier() >= 2) {
                for (Employee emp1 : ((Manager) mng).getEmployees()) {
                    if (emp1.getName().equals(employee.getName())) {
                        test = true;
                        break;
                    }
                }
            }
        }
        if (this.getTier() <= employee.getTier()) {
            throw new Exception("ERROR: cannot alter salary of an Employee of an equal or greater tier.");
        } else if (!test) {
            throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
        } else {
            employee.setSalary(employee.getSalary() + newSalary);
        }
    }

    public ArrayList<Employee> getManagers() {
        return reports;
    }

    public ArrayList<Employee> getEmployees() {
        return reports;
    }
}