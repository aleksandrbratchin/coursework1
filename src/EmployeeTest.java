public class EmployeeTest {
    public static void main(String[] args) {

        Employee[] employees = new Employee[]{
                new Employee("Малышева", "Амалия", "Михайловна", "2", 83166.43),
                new Employee("Козловский", "Денис", "Дмитриевич", "1", 60250.60),
                new Employee("Соловьева", "Серафима", "Тимофеевна", "3", 59343.29),
                new Employee("Макарова", "Дарья", "Тимофеевна", "1", 82042.89),
                new Employee("Лебедева", "Таисия", "Макаровна", "5", 72881.88),
                new Employee("Романов", "Артём", "Егорович", "2", 62761.97),
                new Employee("Широков", "Павел", "Тимофеевич", "4", 97159.11),
                new Employee("Кудрявцев", "Лев", "Егорович", "2", 89845.70),
                new Employee("Белякова", "Антонина", "Дмитриевна", "5", 79209.12),
                new Employee("Филиппова", "Алиса", "Александровна", "1", 87333.51)
        };
        EmployeeBook employeeBook = new EmployeeBook(employees);


        Employee[] empty = new Employee[]{};
        EmployeeBook employeeBookEmpty = new EmployeeBook(empty);

        System.out.println("\n *** Тест все ОК (Базовая сложность) *** \n");
        testsBase(employeeBook);

        System.out.println("\n *** Тест пустой список (Базовая сложность) *** \n");
        testsBase(employeeBookEmpty);

        System.out.println("\n *** Тест все ОК (Средняя сложность) *** \n");
        testsMedium(employeeBook);

        System.out.println("\n *** Тест пустой список (Средняя сложность) *** \n");
        testsMedium(employeeBookEmpty);

        System.out.println("\n *** Тест все ОК (Высокая сложность) *** \n");
        testsHard(employeeBook);

    }

    /**
     * Тестирование базовая сложность
     */
    public static void testsBase(EmployeeBook employeeBook) {
        employeeBook.printAll();
        System.out.println("Траты за месяц составляют - " + employeeBook.salarySum() + "\n");
        System.out.println("Сотрудник с минимальной зарплатой - " + (employeeBook.minSalary() == null ? "нет сотрудников" : employeeBook.minSalary()) + "\n");
        System.out.println("Сотрудник с максимальной зарплатой - " + (employeeBook.maxSalary() == null ? "нет сотрудников" : employeeBook.maxSalary()) + "\n");
        System.out.println("Среднее значение зарплат - " + employeeBook.averageValue() + "\n");
        System.out.println("Список Фио: {\n" + employeeBook.getAllFullName() + "}" + "\n");
    }

    /**
     * Тестирование средняя сложность
     */
    public static void testsMedium(EmployeeBook employeeBook) {
        System.out.println("Провести нидексацию на 5%");
        employeeBook.salaryIndexing(5.0);
        employeeBook.printAll();
        String department = "2";
        System.out.println();
        System.out.println("Траты за месяц в отделе " + department + " составляют - " + employeeBook.salarySumByDepartment(department) + "\n");
        System.out.println("Сотрудник с минимальной зарплатой в отделе " + department + " - " + (employeeBook.minSalaryByDepartment(department) == null ? "нет сотрудников" : employeeBook.minSalaryByDepartment(department)) + "\n");
        System.out.println("Сотрудник с максимальной зарплатой в отделе " + department + " - " + (employeeBook.maxSalaryByDepartment(department) == null ? "нет сотрудников" : employeeBook.maxSalaryByDepartment(department)) + "\n");
        System.out.println("Среднее значение зарплат в отделе " + department + " - " + employeeBook.averageValueByDepartment(department) + "\n");
        System.out.println("Провести нидексацию на 5% в отделе " + department + "\n");
        employeeBook.salaryIndexingByDepartment(5.0, department);
        System.out.println("Список сотрудников в отделе " + department);
        employeeBook.printDepartment(department);
        System.out.println();
        double checkSalary = 85000;
        System.out.println("Список сотрудников c зарплатой меньше чем " + checkSalary);
        employeeBook.printAll(employeeBook.filterBySalaryLessThan(checkSalary));
        System.out.println();
        System.out.println("Список сотрудников c зарплатой больше чем " + checkSalary);
        employeeBook.printAll(employeeBook.filterBySalaryMoreThan(checkSalary));
    }

    /**
     * Тестирование высокая сложность
     */
    public static void testsHard(EmployeeBook employeeBook) {
        System.out.println("Добавить нового сотрудника");
        employeeBook.addEmployee(new Employee("Степанов", "Алексей", "Максимович", "4", 87324.75));
        employeeBook.addEmployee(new Employee("Шестаков", "Михаил", "Романович", "1", 63263.13));
        System.out.println("Удаление Макаровой Дарьи Тимофеевны");
        employeeBook.removeEmployee("Макарова", "Дарья", "Тимофеевна");
        double salary = 70250.60;
        System.out.println("Установить зарплату Козловскому Денису Дмитриевичу " + salary);
        employeeBook.updateSalary("Козловский", "Денис", "Дмитриевич", salary);
        String department = "5";
        System.out.println("Перевести Кудрявцева Льва Егоровича в отдел " + department);
        employeeBook.updateDepartment("Кудрявцев", "Лев", "Егорович", department);
        System.out.println();
        employeeBook.printAllDepartments();
    }

}
