public class EmployeeTest {
    public static void main(String[] args) {

        Employee[] employees = new Employee[10];
        employees[0] = new Employee("Малышева", "Амалия", "Михайловна", "2", 83166.43);
        employees[1] = new Employee("Козловский", "Денис", "Дмитриевич", "1", 60250.60);
        employees[2] = new Employee("Соловьева", "Серафима", "Тимофеевна", "3", 59343.29);
        employees[3] = new Employee("Макарова", "Дарья", "Тимофеевна", "1", 82042.89);
        employees[4] = new Employee("Лебедева", "Таисия", "Макаровна", "5", 72881.88);
        employees[5] = new Employee("Романов", "Артём", "Егорович", "2", 62761.97);
        employees[6] = new Employee("Широков", "Павел", "Тимофеевич", "4", 97159.11);
        employees[7] = new Employee("Кудрявцев", "Лев", "Егорович", "2", 89845.70);
        employees[8] = new Employee("Белякова", "Антонина", "Дмитриевна", "5", 79209.12);
        employees[9] = new Employee("Филиппова", "Алиса", "Александровна", "1", 87333.51);

        Employee[] empty = new Employee[]{};

        System.out.println("\n *** Тест все ОК (Базовая сложность) *** \n");
        testsBase(employees);

        System.out.println("\n *** Тест пустой список (Базовая сложность) *** \n");
        testsBase(empty);

        System.out.println("\n *** Тест все ОК (Средняя сложность) *** \n");
        testsMedium(employees);

        System.out.println("\n *** Тест пустой список (Средняя сложность) *** \n");
        testsMedium(empty);
    }

    /**
     * Тестирование базовая сложность
     */
    public static void testsBase(Employee[] employees) {
        printAll(employees);
        System.out.println("Траты за месяц составляют - " + salarySum(employees) + "\n");
        System.out.println("Сотрудник с минимальной зарплатой - " + (minSalary(employees) == null ? "нет сотрудников" : minSalary(employees)) + "\n");
        System.out.println("Сотрудник с максимальной зарплатой - " + (maxSalary(employees) == null ? "нет сотрудников" : maxSalary(employees)) + "\n");
        System.out.println("Среднее значение зарплат - " + averageValue(employees) + "\n");
        System.out.println("Список Фио: {\n" + getFio(employees) + "}" + "\n");
    }

    /**
     * Тестирование средняя сложность
     */
    public static void testsMedium(Employee[] employees) {
        System.out.println("Провести нидексацию на 5%");
        salaryIndexing(employees, 5.0);
        printAll(employees);
        String department = "2";
        System.out.println();
        System.out.println("Траты за месяц в отделе " + department + " составляют - " + salarySum(employees, department) + "\n");
        System.out.println("Сотрудник с минимальной зарплатой в отделе " + department + " - " + (minSalary(employees, department) == null ? "нет сотрудников" : minSalary(employees, department)) + "\n");
        System.out.println("Сотрудник с максимальной зарплатой в отделе " + department + " - " + (maxSalary(employees, department) == null ? "нет сотрудников" : maxSalary(employees, department)) + "\n");
        System.out.println("Среднее значение зарплат в отделе " + department + " - " + averageValue(employees, department) + "\n");
        System.out.println("Провести нидексацию на 5% в отделе " + department + "\n");
        salaryIndexing(employees, 5.0, department);
        System.out.println("Список сотрудников в отделе " + department);
        printDepartment(employees, department);
        System.out.println();
        double checkSalary = 85000;
        System.out.println("Список сотрудников c зарплатой меньше чем " + checkSalary);
        printAll(filterBySalaryLessThan(employees, checkSalary));
        System.out.println();
        System.out.println("Список сотрудников c зарплатой больше чем " + checkSalary);
        printAll(filterBySalaryMoreThan(employees, checkSalary));
    }

    /**
     * Вывод всего списка в консоль
     */
    public static void printAll(Employee[] employees) {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    /**
     * Траты за месяц
     */
    public static double salarySum(Employee[] employees) {
        double sum = 0;
        for (Employee employee : employees) {
            sum += employee.getSalary();
        }
        return sum;
    }

    /**
     * Траты за месяц по отделу
     */
    public static double salarySum(Employee[] employees, String otdel) {
        return salarySum(filterByDepartment(employees, otdel));
    }


    /**
     * Сотрудник с максимальной зарплатой
     */
    public static Employee maxSalary(Employee[] employees) {
        if (employees.length == 0) return null;
        Employee max = employees[0];
        for (Employee employee : employees) {
            if (max.getSalary() < employee.getSalary()) {
                max = employee;
            }
        }
        return max;
    }

    /**
     * Сотрудник с максимальной зарплатой по номеру отдела
     */
    public static Employee maxSalary(Employee[] employees, String otdel) {
        return maxSalary(filterByDepartment(employees, otdel));
    }

    /**
     * Сотрудник с минимальной зарплатой
     */
    public static Employee minSalary(Employee[] employees) {
        if (employees.length == 0) return null;
        Employee min = employees[0];
        for (Employee employee : employees) {
            if (min.getSalary() > employee.getSalary()) {
                min = employee;
            }
        }
        return min;
    }

    /**
     * Сотрудник с минимальной зарплатой по номеру отдела
     */
    public static Employee minSalary(Employee[] employees, String otdel) {
        return minSalary(filterByDepartment(employees, otdel));
    }

    /**
     * Среднее значение зарплат
     */
    public static double averageValue(Employee[] employees) {
        if (employees.length == 0) return 0;
        return salarySum(employees) / employees.length;
    }

    /**
     * Среднее значение зарплат по номеру отдела
     */
    public static double averageValue(Employee[] employees, String otdel) {
        return averageValue(filterByDepartment(employees, otdel));
    }

    /**
     * Список Фио
     */
    public static String getFio(Employee[] employees) {
        StringBuilder fio = new StringBuilder();
        for (Employee employee : employees) {
            fio.append(employee.getName()).append(" ").append(employee.getSurname()).append(" ").append(employee.getPatronymic()).append("\n");
        }
        return fio.toString();
    }

    /**
     * Индексирование зарплаты
     */
    public static void salaryIndexing(Employee[] employees, double procent) {
        for (Employee employee : employees) {
            double salary = employee.getSalary();
            employee.setSalary(salary + (salary / 100 * procent));
        }
    }

    /**
     * Индексирование зарплаты отдела
     */
    public static void salaryIndexing(Employee[] employees, double procent, String otdel) {
        salaryIndexing(filterByDepartment(employees, otdel), procent);
    }

    /**
     * Фильтр по отделу
     */
    public static Employee[] filterByDepartment(Employee[] employees, String otdel) {
        if (employees.length == 0) return employees;
        int countOtdel = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment().equals(otdel)) {
                countOtdel++;
            }
        }
        Employee[] newEmployee = new Employee[countOtdel];
        for (int i = 0, j = 0; i < employees.length; i++) {
            if (employees[i].getDepartment().equals(otdel)) {
                newEmployee[j++] = employees[i];
            }
        }
        return newEmployee;
    }

    /**
     * Печать отдела
     */
    public static void printDepartment(Employee[] employees, String otdel) {
        Employee[] departmentEmployees = filterByDepartment(employees, otdel);
        if (departmentEmployees.length == 0) {
            System.out.println("В отделе " + otdel + " нет сотрудников!");
        }
        printEmployeeWithoutDepartment(employees);
    }

    /**
     * Печать информации о сотруднике без отдела
     */
    public static void printEmployeeWithoutDepartment(Employee[] employees) {
        for (Employee employee : employees) {
            System.out.println(
                    employee.toString().replaceAll("\tОтдел.*?\n", "")
            );
        }
    }

    /**
     * Все сотрудники с зарплатой меньше checkSalary
     */
    public static Employee[] filterBySalaryLessThan(Employee[] employees, double checkSalary) {
        if (employees.length == 0) return employees;
        int countOtdel = 0;
        for (Employee employee : employees) {
            if (employee.getSalary() < checkSalary) {
                countOtdel++;
            }
        }
        Employee[] newEmployee = new Employee[countOtdel];
        for (int i = 0, j = 0; i < employees.length; i++) {
            if (employees[i].getSalary() < checkSalary) {
                newEmployee[j++] = employees[i];
            }
        }
        return newEmployee;
    }

    /**
     * Все сотрудники с зарплатой больше checkSalary
     */
    public static Employee[] filterBySalaryMoreThan(Employee[] employees, double checkSalary) {
        if (employees.length == 0) return employees;
        int countOtdel = 0;
        for (Employee employee : employees) {
            if (employee.getSalary() > checkSalary) {
                countOtdel++;
            }
        }
        Employee[] newEmployee = new Employee[countOtdel];
        for (int i = 0, j = 0; i < employees.length; i++) {
            if (employees[i].getSalary() > checkSalary) {
                newEmployee[j++] = employees[i];
            }
        }
        return newEmployee;
    }

}
