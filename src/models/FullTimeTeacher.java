package models;

public class FullTimeTeacher extends Teacher {
    private double baseSalary;

    public FullTimeTeacher(String name, String lastName, String personalCode, double baseSalary) {
        super(name, lastName, personalCode);
        this.baseSalary= baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public void calculateSalary() {
        setNetSalary( baseSalary - super.calculateInsurance(baseSalary) - super.calculateTax(baseSalary));
    }
    @Override
    public String toString() {
        return super.toString()+ "baseSalary=" + baseSalary +
                '}'+'\n';

    }
}
