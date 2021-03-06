package models;

public class PartTimeTeacher extends Teacher {
    private int hourlySalary;
    private int hourPerMonth;

    public PartTimeTeacher(String name, String lastName, String personalCode, int hourPerMonth, int hourlySalary) {
        super(name, lastName, personalCode);
        this.hourlySalary = hourlySalary;
        this.hourPerMonth = hourPerMonth;
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public int getHourPerMonth() {
        return hourPerMonth;
    }

    public void setHourPerMonth(int hourPerMonth) {
        this.hourPerMonth = hourPerMonth;
    }

    @Override
    public void calculateSalary() {
        double baseSalary = hourlySalary * hourPerMonth;
        setNetSalary( baseSalary - super.calculateInsurance(baseSalary) - super.calculateTax(baseSalary));
    }
//متد toString وجود نداشت اضافه شد
    @Override
    public String toString() {
        return super.toString()+
                "hourlySalary=" + hourlySalary +
                ", hourPerMonth=" + hourPerMonth +
                '}'+'\n';
    }
}
