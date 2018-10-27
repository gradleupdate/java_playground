package lars.katas.salaryslip;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlip {

    private static final BigDecimal TWELVE_MONTHS = BigDecimal.valueOf(12);
    private static final int TWO_DECIMALS = 2;
    private static final BigDecimal INSURANCE_CONTRIBUTION_THRESHOLD = BigDecimal.valueOf(8_060.00);

    private final Employee employee;
    private Tax tax;

    public SalarySlip(Employee employee) {
        this.employee = employee;
        tax = new Tax(employee.getAnnualGrossSalary());
    }

    public String getEmployeeId() {
        return employee.getId();
    }

    public String getEmployeeName() {
        return employee.getName();
    }

    public BigDecimal getMonthlyGrossSalary() {
        return toMonthly(employee.getAnnualGrossSalary());
    }

    private BigDecimal toMonthly(BigDecimal annual) {
        return annual.divide(TWELVE_MONTHS, TWO_DECIMALS, RoundingMode.HALF_UP).setScale(2);
    }

    public BigDecimal getNationalInsuranceContributions() {
        if (isSubjectToNationalInsuranceContribution()) {
            BigDecimal amountAboveThreshold = employee.getAnnualGrossSalary().subtract(INSURANCE_CONTRIBUTION_THRESHOLD);
            return toMonthly(amountAboveThreshold.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(12)));
        }
        return BigDecimal.ZERO;
    }

    private boolean isSubjectToNationalInsuranceContribution() {
        return employee.getAnnualGrossSalary().compareTo(INSURANCE_CONTRIBUTION_THRESHOLD) == 1;
    }

    public BigDecimal getTaxFreeAllowance() {
        return toMonthly(tax.taxFreeAllowance());
    }

    public BigDecimal getTaxableIncome() {
        return toMonthly(tax.getTaxableIncome());
    }

    public BigDecimal getPayableTax() {
        return toMonthly(tax.getPayableTax());
    }
}
