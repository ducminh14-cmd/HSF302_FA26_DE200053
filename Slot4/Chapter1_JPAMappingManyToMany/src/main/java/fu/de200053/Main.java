package fu.de200053;

import fu.de200053.dao.EmployeeDAO;
import fu.de200053.pojo.Employee;
import fu.de200053.pojo.Gender;
import fu.de200053.pojo.Project;
import fu.de200053.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {



        EmployeeDAO employeeDAO = new EmployeeDAO();

        employeeDAO.findEmployeesInMoreThanOneProject();
    }
}


