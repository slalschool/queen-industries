import java.util.ArrayList;

public class Director extends Manager {

    private int tier;
    private int salary;
    private String name;
    private String department;
    private String titleString;
    private ArrayList<Employee> reports;
    private int stock;

    public Director(int salary, int stock, int bonus, String name, String department, String titleString, ArrayList<Employee> reports, int tier) throws Exception {

        super(salary, bonus, name, department, titleString, reports, tier);

        for(Employee emp : reports) {
            if (emp.getTier() >= 3) throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
        }

        this.stock = stock;
    }

    public int getStockShares() {
        return stock;
    }
}