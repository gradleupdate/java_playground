package lars.katas.salaryslip;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SalarySlipGenerator_should {

    private static final String ANY_ID = "12345";
    private static final String ANY_NAME = "John J Doe";
    private static final BigDecimal EXPECTED_MONTHLY_GROSS_SALARY = BigDecimal.valueOf(416.67);

    private SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();

    @ParameterizedTest
    @MethodSource("grossSalaryProvider")
    void calculate_monthly_gross_salary(BigDecimal annualGrossSalary, BigDecimal monthlyGrossSalary) {
        // given
        Employee employee = employeeWithAnnualSalaryOf(annualGrossSalary);

        // when
        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        // then
        assertAll(
            () -> assertThat(salarySlip.getEmployeeId()).isEqualTo(ANY_ID),
            () -> assertThat(salarySlip.getEmployeeName()).isEqualTo(ANY_NAME),
            () -> assertThat(salarySlip.getMonthlyGrossSalary()).isEqualTo(monthlyGrossSalary)
        );
    }

    @Test
    void calculate_monthly_national_insurance_contributions() {
        // given
        Employee employee = employeeWithAnnualSalaryOf(BigDecimal.valueOf(9_060.00));

        // when
        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        // then
        Assertions.assertThat(salarySlip.getNationalInsuranceContributions()).isEqualTo(BigDecimal.valueOf(10.00).setScale(2));
    }

    // TODO: accept int
    private Employee employeeWithAnnualSalaryOf(BigDecimal annualGrossSalary) {
        return new Employee(ANY_ID, ANY_NAME, annualGrossSalary);
    }

    static Stream<Arguments> grossSalaryProvider() {
        return Stream.of(
            Arguments.of(BigDecimal.valueOf(5_000.000), EXPECTED_MONTHLY_GROSS_SALARY),
            Arguments.of(BigDecimal.valueOf(9_060.00), BigDecimal.valueOf(755.00).setScale(2))
        );
    }
}
