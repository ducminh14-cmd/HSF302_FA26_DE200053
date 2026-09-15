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

        //Entity đang ở trạng thái New/Transient trước khi save()
        employee.setFullName("Tran Thi B");
        employee.setEmail("tranthib@gmail.com");
        employee.setSalary(new BigDecimal("1800.00"));
        employee.setGender(Gender.FEMALE);
        employee.setHireDate(LocalDate.of(2023, 3, 15));
        employee.setActive(true);

        dao.save(employee);
        //Sau persist() trong transaction -> Managed.
        //Khi save() return và EntityManager đóng -> entity trở thành Detached.

        System.out.println("Employee sau khi CREATE:");
        System.out.println(employee);

        Long id = employee.getId();


// READ
        System.out.println("\n--- READ ---");

        //Entity được find() bởi EntityManager của findById(),
        //sau khi EntityManager đóng thì object trả về là Detached.

        Employee employeeRead = dao.findById(id);

        System.out.println("Employee sau khi READ:");
        System.out.println(employeeRead);


// UPDATE
        System.out.println("\n--- UPDATE ---");

        System.out.println("Employee truoc UPDATE:");
        System.out.println(employeeRead);

        employeeRead.setSalary(new BigDecimal("2500.00"));

        dao.update(employeeRead);

        // merge() tạo/trả về một Managed entity trong transaction.
        //Object employeeRead ban đầu vẫn là Detached.


        System.out.println("Da UPDATE salary = 2500.00");


// READ SAU UPDATE
        System.out.println("\n--- READ SAU UPDATE ---");

        //findById() trả về entity đang Managed trong transaction,
        //sau khi EntityManager đóng thì entity trở thành Detached.

        Employee employeeAfterUpdate = dao.findById(id);

        System.out.println("Employee sau UPDATE:");
        System.out.println(employeeAfterUpdate);


// DELETE
        System.out.println("\n--- DELETE ---");

        dao.delete(id);

        System.out.println("Da xoa Employee co id = " + id);


// READ SAU DELETE
        System.out.println("\n--- READ SAU DELETE ---");

        //Entity đã bị Removed và commit,
        //nên findById() không tìm thấy entity -> null.


        Employee employeeAfterDelete = dao.findById(id);

        System.out.println("Ket qua READ sau DELETE:");
        System.out.println(employeeAfterDelete);






        System.out.println("\n===== TODO 0.9 - UNIQUE EMAIL =====");

// Employee thứ nhất
        Employee employee1 = new Employee();
        employee1.setFullName("Nguyen Van A");
        employee1.setEmail("unique_test@gmail.com");
        employee1.setSalary(new BigDecimal("1500.00"));
        employee1.setGender(Gender.MALE);
        employee1.setHireDate(LocalDate.of(2024, 1, 10));
        employee1.setActive(true);

        dao.save(employee1);

        System.out.println("Employee 1 da save thanh cong:");
        System.out.println(employee1);


// Employee thứ hai - CỐ Ý dùng cùng email
        Employee employee2 = new Employee();
        employee2.setFullName("Nguyen Van B");
        employee2.setEmail("unique_test@gmail.com");
        employee2.setSalary(new BigDecimal("1800.00"));
        employee2.setGender(Gender.FEMALE);
        employee2.setHireDate(LocalDate.of(2024, 2, 15));
        employee2.setActive(true);

        try {
            dao.save(employee2);

            System.out.println("ERROR: Employee 2 van save duoc - unique constraint co van de!");

        } catch (RuntimeException ex) {

            System.out.println("OK: Khong the save Employee 2 vi email bi trung!");
            System.out.println("Exception: " + ex.getClass().getSimpleName());
        }


        emf.close();


    }
}
