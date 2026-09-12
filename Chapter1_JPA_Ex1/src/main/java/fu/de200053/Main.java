package fu.de200053;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import fe.de200053.dao.EmployeeDAO;
import fe.de200053.pojo.Employee;
import fe.de200053.pojo.Gender;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsf302FU");
        System.out.println("EMF tao thanh cong!");

        EmployeeDAO dao = new EmployeeDAO(emf);


//        Employee employee = new Employee(
//                "Nguyen Van A",
//                "nguyenvana@gmail.com",
//                new BigDecimal("1500.00"),
//                Gender.MALE,
//                LocalDate.of(2022, 5, 10),
//                true
//        );
//
//        System.out.println("Truoc save, ID = "
//                + employee.getId());
//
//        dao.save(employee);
//
//
//        System.out.println("Sau save, ID = "
//                + employee.getId());
//
//        System.out.println("\n===== TODO 0.4 - FIND BY ID =====");
//
//        Employee foundEmployee = dao.findById(1L);
//
//        System.out.println("Employee tim thay:");
//        System.out.println(foundEmployee);
//
//        System.out.println("\n===== TODO 0.4 - FIND ALL =====");
//
//        List<Employee> employees = dao.findAll();
//
//        System.out.println("Tong so Employee: " + employees.size());
//
//        for (Employee e : employees) {
//            System.out.println(e);
//        }

//        System.out.println("\n===== TEST ID KHONG TON TAI =====");
//
//        Employee notFound = dao.findById(999999L);
//
//        System.out.println("Ket qua: " + notFound);



//        System.out.println("\n===== TODO 0.5 - FIND BY EMAIL =====");
//
//        Employee employeeByEmail =
//                dao.findByEmail("nguyenvana@gmail.com");
//
//        System.out.println("Tim theo email:");
//        System.out.println(employeeByEmail);
//
//
//
//        Employee notFoundByEmail =
//                dao.findByEmail("khongtontai@gmail.com");
//
//        System.out.println("Email khong ton tai:");
//        System.out.println(notFoundByEmail);
//
//
//
//
//        System.out.println("\n===== TODO 0.5 - FIND BY SALARY =====");
//
//        List<Employee> highSalaryEmployees =
//                dao.findBySalaryGreaterThan(new BigDecimal("1000"));
//
//        System.out.println("Employee co salary > 1000:");
//
//        for (Employee e : highSalaryEmployees) {
//            System.out.println(e);
//        }
//
//
//
//        List<Employee> noEmployees =
//                dao.findBySalaryGreaterThan(new BigDecimal("100000"));
//
//        System.out.println("Employee co salary > 100000:");
//
//        for (Employee e : noEmployees) {
//            System.out.println(e);
//        }

        System.out.println("\n===== TODO 0.6 - UPDATE =====");

        Employee employeeToUpdate = dao.findById(1L);

        System.out.println("Truoc update:");
        System.out.println(employeeToUpdate);

        employeeToUpdate.setSalary(new BigDecimal("2000.00"));

        dao.update(employeeToUpdate);

        Employee updatedEmployee = dao.findById(1L);

        System.out.println("Sau update:");
        System.out.println(updatedEmployee);

        emf.close();


    }
}
