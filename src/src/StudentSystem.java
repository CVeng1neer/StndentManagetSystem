import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    private static Scanner scanner = new Scanner(System.in);
    public static void StudentManager() {
        ArrayList<Student> list = new ArrayList<>();
        while (true){
            System.out.println("学生管理系统");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            System.out.println("输入你的选择：");
            Scanner scanner = new Scanner(System.in);
            String choose = scanner.next();
            switch (choose){
                case "1":
                    addStu(list);
                    break;
                case "2":
                    delStu(list);
                    break;
                case "3":
                    updStu(list);
                    break;
                case "4":
                    qryStu(list);
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("无效操作");
            }
        }
    }

    public static void addStu(ArrayList<Student> list) {
        System.out.println("输入id");
        String id = null;
        while (true) {
            id = scanner.next();
            if(contains(list,id)){
                System.out.println("id已经存在，重新输入");
            }
            else {
                break;
            }
        }
        System.out.println("输入姓名");
        String name = scanner.next();
        System.out.println("输入年龄");
        int age = scanner.nextInt();
        System.out.println("输入家庭地址");
        String addr = scanner.next();
        Student student = new Student(id,name,age,addr);
        list.add(student);
        System.out.println("添加成功");

    }
    public static void delStu(ArrayList<Student> list) {
        System.out.println("输入要删除的id：");
        String id = scanner.next();
        int index = getIndex(list,id);
        if(index >= 0){
            list.remove(index);
            System.out.println("id为" + id + "的学生信息已被删除");
        }
        else {
            System.out.println("id不存在，删除失败");
        }

    }
    public static void updStu(ArrayList<Student> list) {
        System.out.println("输入id");
        String id = scanner.next();
        int index = getIndex(list,id);
        if(index == -1){
            System.out.println("id不存在，重新输入");
            return;
        }
        Student student = list.get(index);
        System.out.println("输入要修改的姓名：");
        String newName = scanner.next();
        student.setName(newName);
        System.out.println("输入要修改的年龄：");
        int newAge = scanner.nextInt();
        student.setAge(newAge);
        System.out.println("输入要修改的家庭地址：");
        String newAddr = scanner.next();
        student.setAddr(newAddr);
        System.out.println("修改成功");

    }
    public static void qryStu(ArrayList<Student> list) {
        if(list.size() == 0){
            System.out.println("当前无学生信息");
            return;
        }
        System.out.println("id\t\t姓名\t年龄\t家庭地址");
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            System.out.println(student.getId() + "\t" + student.getName() + "\t" + student.getAge() + "\t" + student.getAddr());
        }

    }
    private static boolean contains(ArrayList<Student> list, String id) {

        return getIndex(list,id) >= 0;
    }
    private static int getIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            if (student.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
