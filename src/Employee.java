public class Employee {

    private static int index = 1;
    private final int id;
    private final String surname;
    private final String name;
    private final String patronymic;
    private String department;
    private double salary;

    public Employee(String surname, String name, String patronymic, String department, double salary) {
        this.id = index++;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Сотрудник №" + id + " {\n" +
                "\tФамилия = '" + surname + "',\n" +
                "\tИмя = '" + name + "',\n" +
                "\tОтчество = '" + patronymic + "',\n" +
                "\tОтдел = '" + department + "',\n" +
                "\tЗарплата = " + salary + "\n" +
                '}';
    }
}
