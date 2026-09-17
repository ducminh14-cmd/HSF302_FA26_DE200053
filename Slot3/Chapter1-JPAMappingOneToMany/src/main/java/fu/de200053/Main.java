package fu.de200053;

import fu.de200053.dao.DepartmentDAO;
import fu.de200053.pojo.Department;
import fu.de200053.pojo.Employee;
import fu.de200053.pojo.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Department dept = new Department("Marketing", "Ha Noi");
//        Employee emp = new Employee("test2@company.com", "Test", Gender.OTHER,
//                new BigDecimal("1000"), LocalDate.now());
//        Employee e2 = new Employee("bc@company.com", "B", Gender.FEMALE,
//                new BigDecimal("1200"), LocalDate.of(2022, 2, 1));
//        Employee e3 = new Employee("cd@company.com", "C", Gender.OTHER,
//                new BigDecimal("1500"), LocalDate.of(2022, 3, 1));
//        dept.addEmployee(emp);
//        dept.addEmployee(e2);
//        dept.addEmployee(e3);
//
//        DepartmentDAO deptDAO = new DepartmentDAO();
//        deptDAO.save(dept);
//        System.out.println("Đã thêm: "+ dept.getName());

        DepartmentDAO departmentDAO = new DepartmentDAO();

        // 1. Tạo Department
        Department it = new Department("Marketing", "Ha Noi");

        // 2. Tạo 3 Employee
        Employee e1 = new Employee(
                "aa.nguyen@company.com",
                "Nguyen Van A",
                Gender.MALE,
                new BigDecimal("15000000"),
                LocalDate.of(2022, 1, 10)
        );

        Employee e2 = new Employee(
                "bb.tran@company.com",
                "Tran Thi B",
                Gender.FEMALE,
                new BigDecimal("18000000"),
                LocalDate.of(2021, 6, 1)
        );

        Employee e3 = new Employee(
                "cc.le@company.com",
                "Le Van C",
                Gender.OTHER,
                new BigDecimal("12000000"),
                LocalDate.of(2023, 3, 15)
        );

        // 3. Thêm Employee vào Department bằng helper method
        it.addEmployee(e1);
        it.addEmployee(e2);
        it.addEmployee(e3);

        // 4. Chỉ lưu Department
        // cascade = ALL sẽ tự động lưu 3 Employee
        departmentDAO.save(it);

        System.out.println("Da luu Department, id = " + it.getId());

    }
}