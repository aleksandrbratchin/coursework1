import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class EmployeeBook {
    private List<Employee> employees;

    public EmployeeBook(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Вывод всего списка в консоль
     */
    public void printAll(List<Employee> employees) {
        if (employees == null) return;
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee);
            }
        }
    }

    public void printAll() {
        printAll(employees);
    }

    /**
     * Траты за месяц
     */
    public double salarySum() {
        return salarySum(employees);
    }

    public double salarySum(List<Employee> employees) {
        if (employees == null) return 0;
        double sum = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    /**
     * Траты за месяц по отделу
     */
    public double salarySumByDepartment(String department) {
        return salarySum(filterByDepartment(department));
    }

    /**
     * Сотрудник с максимальной зарплатой
     */
    public Employee maxSalary() {
        return maxSalary(employees);
    }

    private Employee maxSalary(List<Employee> employees) {
        return extremumSalary(employees, false);
    }

    /**
     * Сотрудник с максимальной зарплатой по номеру отдела
     */
    public Employee maxSalaryByDepartment(String department) {
        return maxSalary(filterByDepartment(department));
    }

    /**
     * Сотрудник с минимальной зарплатой
     */
    public Employee minSalary() {
        return minSalary(employees);
    }

    private Employee minSalary(List<Employee> employees) {
        return extremumSalary(employees, true);
    }

    /**
     * Сотрудник с минимальной зарплатой по номеру отдела
     */
    public Employee minSalaryByDepartment(String department) {
        return minSalary(filterByDepartment(department));
    }

    private Employee extremumSalary(List<Employee> employees, boolean isMin) {
        if (employees == null) return null;
        Employee extremum = null;
        for (Employee employee : employees) {
            if (employee != null) {
                if (extremum == null) {
                    extremum = employee;
                } else if (isMin) {
                    if (extremum.getSalary() > employee.getSalary()) {
                        extremum = employee;
                    }
                } else if (extremum.getSalary() < employee.getSalary()) {
                    extremum = employee;
                }
            }
        }
        return extremum;
    }

    /**
     * Среднее значение зарплат
     */
    public double averageValue() {
        return averageValue(employees);
    }

    public double averageValue(List<Employee> employees) {
        if (employees == null || employees.size() == 0) return 0;
        return salarySum(employees) / employees.size();
    }

    /**
     * Среднее значение зарплат по номеру отдела
     */
    public double averageValueByDepartment(String department) {
        return averageValue(filterByDepartment(department));
    }

    /**
     * Список Фио
     */
    public String getAllFullName() {
        if (employees == null) return "";
        StringBuilder fio = new StringBuilder();
        for (Employee employee : employees) {
            if (employee != null) {
                fio.append(employee.getName()).append(" ").append(employee.getSurname()).append(" ").append(employee.getPatronymic()).append("\n");
            }
        }
        return fio.toString();
    }

    /**
     * Индексирование зарплаты
     */
    public void salaryIndexing(double percent) {
        if (employees == null) return;
        for (Employee employee : employees) {
            if (employee != null) {
                salaryIndexing(employee, percent);
            }
        }
    }

    private void salaryIndexing(Employee employee, double percent) {
        double salary = employee.getSalary();
        employee.setSalary(salary + (salary / 100 * percent));
    }

    /**
     * Индексирование зарплаты отдела
     */
    public void salaryIndexingByDepartment(double percent, String department) {
        if (employees == null) return;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment().equals(department)) {
                salaryIndexing(employee, percent);
            }
        }
    }

    /**
     * Фильтр по отделу
     */
    public List<Employee> filterByDepartment(String department) {
        if (employees == null) return null;
        return employees.stream().filter(
                employee -> employee.getDepartment().equals(department)
        ).toList();
    }

    /**
     * Печать отдела
     */
    public void printDepartment(String department) {
        if (employees == null) return;
        List<Employee> departmentEmployees = filterByDepartment(department);
        if (departmentEmployees.size() != 0) {
            printEmployeeInfoWithoutDepartment(departmentEmployees);
        }
    }

    public void printAllDepartments() {
        var map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        map.forEach((department, employee) -> {
                    System.out.println("Список сотрудников в отделе " + department);
                    if (employee.size() == 0) {
                        System.out.println("В отделе " + department + " нет сотрудников!");
                    } else {
                        printAll(employee);
                    }
                }
        );
    }

    /**
     * Печать информации о сотруднике без отдела
     */
    public void printEmployeeInfoWithoutDepartment() {
        printEmployeeInfoWithoutDepartment(employees);
    }

    private void printEmployeeInfoWithoutDepartment(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.toString().replaceAll("\tОтдел.*?\n", ""));
            }
        }
    }

    /**
     * Все сотрудники с зарплатой меньше checkSalary
     */
    public List<Employee> filterBySalaryLessThan(double checkSalary) {
        if (employees == null) return null;
        return employees.stream().filter(employee -> employee.getSalary() < checkSalary).toList();
    }

    /**
     * Все сотрудники с зарплатой больше checkSalary
     */
    public List<Employee> filterBySalaryMoreThan(double checkSalary) {
        if (employees == null) return null;
        return employees.stream().filter(employee -> employee.getSalary() > checkSalary).toList();
    }

    /**
     * Удалить сотрудника
     */
    public void removeEmployee(int id) {
        employees.remove(id);
    }

    public void removeEmployee(String surname, String name, String patronymic) {
        employees.remove(getEmployee(surname, name, patronymic));
    }

    public void removeEmployee(Employee employee) {
        removeEmployee(employee.getId());
    }

    /**
     * Добавить сотрудника
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Изменить зарплату
     */
    public void updateSalary(Employee employee, double salary) {
        employee.setSalary(salary);
    }

    public void updateSalary(String surname, String name, String patronymic, double salary) {
        getEmployee(surname, name, patronymic).setSalary(salary);
    }

    public void updateSalary(int id, double salary) {
        employees.get(id).setSalary(salary);
    }

    /**
     * Изменить отдел
     */
    public void updateDepartment(Employee employee, String department) {
        employee.setDepartment(department);
    }

    public void updateDepartment(String surname, String name, String patronymic, String department) {
        getEmployee(surname, name, patronymic).setDepartment(department);
    }

    public void updateDepartment(int id, String department) {
        employees.get(id).setDepartment(department);
    }

    /**
     * Методы поиска id Employees[]
     */
    private Employee getEmployee(String surname, String name, String patronymic) {
        for (Employee employee : employees) {
            if (
                    surname.equals(employee.getSurname()) &&
                            name.equals(employee.getName()) &&
                            patronymic.equals(employee.getPatronymic())
            ) {
                return employee;
            }
        }
        throw new NoSuchElementException();
    }

}
