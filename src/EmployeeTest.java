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

    }

    /**
     * Тестирование базовая сложность
     */
    public static void testsBase(Employee[] employees) {
        printAll(employees);
        System.out.println("Траты за месяц составляют - " + spendingPerMonth(employees) + "\n");
        System.out.println("Сотрудник с минимальной зарплатой - " + (minSalary(employees) == null ? "нет сотрудников" : minSalary(employees)) + "\n");
        System.out.println("Сотрудник с максимальной зарплатой - " + (maxSalary(employees) == null ? "нет сотрудников" : maxSalary(employees)) + "\n");
        System.out.println("Среднее значение зарплат - " + averageValue(employees) + "\n");
        System.out.println("Список Фио: {\n" + getFio(employees) + "}" + "\n");
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
    public static double spendingPerMonth(Employee[] employees) {
        double sum = 0;
        for (Employee employee : employees) {
            sum += employee.getSalary();
        }
        return sum;
    }

    /**
     * Сотрудник с минимальной зарплатой
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
     * Сотрудник с максимальной зарплатой
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
     * Среднее значение зарплат
     */
    public static double averageValue(Employee[] employees) {
        if (employees.length == 0) return 0;
        return spendingPerMonth(employees) / employees.length;
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
}
