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
//


        System.out.println("\n===== TODO 0.8 - CRUD FLOW =====");

// CREATE
        System.out.println("\n--- CREATE ---");

        Employee employee = new Employee();
        employee.setFullName("Tran Thi B");
        employee.setEmail("tranthib@gmail.com");
        employee.setSalary(new BigDecimal("1800.00"));
        employee.setGender(Gender.FEMALE);
        employee.setHireDate(LocalDate.of(2023, 3, 15));
        employee.setActive(true);

        dao.save(employee);

        System.out.println("Employee sau khi CREATE:");
        System.out.println(employee);

        Long id = employee.getId();


// READ
        System.out.println("\n--- READ ---");

        Employee employeeRead = dao.findById(id);

        System.out.println("Employee sau khi READ:");
        System.out.println(employeeRead);


// UPDATE
        System.out.println("\n--- UPDATE ---");

        System.out.println("Employee truoc UPDATE:");
        System.out.println(employeeRead);

        employeeRead.setSalary(new BigDecimal("2500.00"));

        dao.update(employeeRead);

        System.out.println("Da UPDATE salary = 2500.00");


// READ SAU UPDATE
        System.out.println("\n--- READ SAU UPDATE ---");

        Employee employeeAfterUpdate = dao.findById(id);

        System.out.println("Employee sau UPDATE:");
        System.out.println(employeeAfterUpdate);


// DELETE
        System.out.println("\n--- DELETE ---");

        dao.delete(id);

        System.out.println("Da xoa Employee co id = " + id);


// READ SAU DELETE
        System.out.println("\n--- READ SAU DELETE ---");

        Employee employeeAfterDelete = dao.findById(id);

        System.out.println("Ket qua READ sau DELETE:");
        System.out.println(employeeAfterDelete);




        emf.close();


    }
}
