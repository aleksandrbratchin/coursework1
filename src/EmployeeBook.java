import java.util.Arrays;
import java.util.NoSuchElementException;

public class EmployeeBook {
    private Employee[] employees;

    public EmployeeBook(Employee[] employees) {
        this.employees = employees;
    }

    /**
     * Вывод всего списка в консоль
     */
    public void printAll(Employee[] employees) {
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

    public double salarySum(Employee[] employees) {
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

    private Employee maxSalary(Employee[] employees) {
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

    private Employee minSalary(Employee[] employees) {
        return extremumSalary(employees, true);
    }

    /**
     * Сотрудник с минимальной зарплатой по номеру отдела
     */
    public Employee minSalaryByDepartment(String department) {
        return minSalary(filterByDepartment(department));
    }

    private Employee extremumSalary(Employee[] employees, boolean isMin) {
        if (employees.length == 0) return null;
        Employee extremum = null;
        for (Employee employee : employees) {
            if (employee != null) {
                if (extremum == null) {
                    extremum = employee;
                } else if (isMin) {
                    if(extremum.getSalary() > employee.getSalary()){
                        extremum = employee;
                    }
                } else if(extremum.getSalary() < employee.getSalary()) {
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

    public double averageValue(Employee[] employees) {
        if (employees.length == 0) return 0;
        return salarySum(employees) / employees.length;
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
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment().equals(department)) {
                salaryIndexing(employee, percent);
            }
        }
    }

    /**
     * Фильтр по отделу
     */
    public Employee[] filterByDepartment(String department) {
        if (employees.length == 0) return employees;
        Employee[] result = new Employee[employees.length];
        int size = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment().equals(department)) {
                result[size++] = employee;
            }
        }
        return Arrays.copyOf(result, size);
    }

    /**
     * Печать отдела
     */
    public void printDepartment(String department) {
        Employee[] departmentEmployees = filterByDepartment(department);
        if (departmentEmployees.length == 0) {
            System.out.println("В отделе " + department + " нет сотрудников!");
        }
        printEmployeeWithoutDepartment(departmentEmployees);
    }

    public void printAllDepartments() {
        String[] departments = getDepartments();
        for (String department : departments) {
            System.out.println("Список сотрудников в отделе " + department);
            printDepartment(department);
        }
    }

    /**
     * Печать информации о сотруднике без отдела
     */
    public void printEmployeeWithoutDepartment() {
        printEmployeeWithoutDepartment(employees);
    }

    private void printEmployeeWithoutDepartment(Employee[] employees) {
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.toString().replaceAll("\tОтдел.*?\n", ""));
            }
        }
    }

    /**
     * Все сотрудники с зарплатой меньше checkSalary
     */
    public Employee[] filterBySalaryLessThan(double checkSalary) {
        if (employees.length == 0) return employees;
        Employee[] result = new Employee[employees.length];
        int size = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < checkSalary) {
                result[size++] = employee;
            }
        }
        return Arrays.copyOf(result, size);
    }

    /**
     * Все сотрудники с зарплатой больше checkSalary
     */
    public Employee[] filterBySalaryMoreThan(double checkSalary) {
        if (employees.length == 0) return employees;
        Employee[] result = new Employee[employees.length];
        int size = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() > checkSalary) {
                result[size++] = employee;
            }
        }
        return Arrays.copyOf(result, size);
    }

    /**
     * Удалить сотрудника
     */
    public void removeEmployee(int id) {
        employees[getIdEmployees(id)] = null;
    }

    public void removeEmployee(String surname, String name, String patronymic) {
        employees[getIdEmployees(surname, name, patronymic)] = null;
    }

    public void removeEmployee(Employee employee) {
        removeEmployee(employee.getId());
    }

    /**
     * Добавить сотрудника
     */
    public void addEmployee(Employee employee) {
        if (employees.length == 0) {
            employees = new Employee[]{employee};
            return;
        }
        int len = employees.length;
        for (int i = 0; i < len; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return;
            }
        }
        employees = Arrays.copyOf(employees, len + len / 2 + 1);
        employees[len] = employee;
    }

    /**
     * Изменить зарплату
     */
    public void updateSalary(Employee employee, double salary) {
        employee.setSalary(salary);
    }

    public void updateSalary(String surname, String name, String patronymic, double salary) {
        employees[getIdEmployees(surname, name, patronymic)].setSalary(salary);
    }

    public void updateSalary(int id, double salary) {
        employees[getIdEmployees(id)].setSalary(salary);
    }

    /**
     * Изменить отдел
     */
    public void updateDepartment(Employee employee, String department) {
        employee.setDepartment(department);
    }

    public void updateDepartment(String surname, String name, String patronymic, String department) {
        employees[getIdEmployees(surname, name, patronymic)].setDepartment(department);
    }

    public void updateDepartment(int id, String department) {
        employees[getIdEmployees(id)].setDepartment(department);
    }

    /**
     * Методы поиска id Employees[]
     */
    private int getIdEmployees(String surname, String name, String patronymic) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null &&
                    surname.equals(employees[i].getSurname()) &&
                    name.equals(employees[i].getName()) &&
                    patronymic.equals(employees[i].getPatronymic())
            ) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private int getIdEmployees(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Получить массив всех отделов
     */
    private String[] getDepartments() {
        if (employees.length == 0) return new String[]{};
        String[] result = new String[employees.length];
        int size = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                String department = employee.getDepartment();
                if (containsDepartment(result, department)) {
                    result[size++] = department;
                }
            }
        }
        return Arrays.copyOf(result, size);
    }

    private boolean containsDepartment(String[] arr, String department){
        for (int i = 0; i < arr.length && arr[i] != null; i++) {
            if (arr[i].equals(department)) {
                return true;
            }
        }
        return false;
    }

}
