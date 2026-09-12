package fu.de200053;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import fe.de200053.dao.EmployeeDAO;
import fe.de200053.pojo.Employee;
import fe.de200053.pojo.Gender;
import java.math.BigDecimal;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsf302FU");
        System.out.println("EMF tao thanh cong!");

        EmployeeDAO dao = new EmployeeDAO(emf);

        // Entity đang ở trạng thái New/Transient
        Employee employee = new Employee(
                "Nguyen Van A",
                "nguyenvana@gmail.com",
                new BigDecimal("1500.00"),
                Gender.MALE,
                LocalDate.of(2022, 5, 10),
                true
        );

        System.out.println("Truoc save, ID = "
                + employee.getId());

        dao.save(employee);

        // Sau khi save() kết thúc và EntityManager đóng,
        // entity trở thành Detached.
        System.out.println("Sau save, ID = "
                + employee.getId());

        emf.close();
    }
}
