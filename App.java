import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static ArrayList<User> list = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("选择操作：1 登录，2 注册，3 忘记密码");
            String choose = scanner.next();

            switch (choose) {
                case "1":
                    login(list);
                    break;
                case "2":
                    register(list);
                    break;
                case "3":
                    forgetPwd(list);
                    break;
                case "4": {
                    System.out.println("感谢使用，再见");
                    System.exit(0);
                }
                default:
                    System.out.println("没有这个选项");
                    break;
            }
        }
    }

    public static void login(ArrayList<User> list) {
        System.out.println("输入用户名");
        String name = scanner.next();
        if (!contains(list,name)){
            System.out.println("未注册的用户名，先注册");
            return;
        }
        System.out.println("输入密码");
        String password = scanner.next();
        while (true) {
            String rightcode = getCode();
            System.out.println("验证码为：" + rightcode);
            System.out.println("输入验证码");
            String code = scanner.next();
            if (!code.equalsIgnoreCase(rightcode)){
                System.out.println("验证码错误");
            }
            else {
                break;
            }
        }

        User userInfo = new User(name,password,null,null);
        if (checkUserInfo(list,userInfo)){
            System.out.println("登陆成功，可以使用系统");
            StudentSystem studentSystem = new StudentSystem();
            studentSystem.StudentManager();
        }
        else {
            System.out.println("登陆失败，用户名或密码错误");
        }


    }

    public static void register(ArrayList<User> list) {
        String name;
        String password;
        String personID;
        String phoneNumber;
        while (true) {
            System.out.println("输入用户名");
            name = scanner.next();
            if (!checkName(name)) {
                System.out.println("格式错误，重新输入");
                continue;
            }
            if (contains(list, name)) {
                System.out.println("用户名" + name + "已存在，重新输入");
            } else {
                System.out.println("用户名" + name + "可用");
                break;
            }
        }

        while (true) {
            System.out.println("设置密码");
            password = scanner.next();
            System.out.println("再次输入密码");
            String pwd = scanner.next();
            if (!password.equals(pwd)) {
                System.out.println("两次输入的密码不一致，重新输入");
            } else {
                System.out.println("设置密码成功");
                break;
            }
        }

        while (true) {
            System.out.println("输入身份证号");
            personID = scanner.next();
            if (checkPersonID(personID)) {
                System.out.println("身份证号码输入成功");
                break;
            } else {
                System.out.println("格式错误，重新输入");
            }
        }

        while (true) {
            System.out.println("输入手机号码");
            phoneNumber = scanner.next();
            if (checkPhoneNumber(phoneNumber)) {
                System.out.println("手机号输入成功");
                break;
            } else {
                System.out.println("格式错误，重新输入");
            }
        }

        User user = new User(name,password,personID,phoneNumber);
        list.add(user);
        System.out.println("注册成功");

    }

    public static void forgetPwd(ArrayList<User> list) {
        System.out.println("输入用户名");
        String name = scanner.next();
        if(!contains(list,name)){
            System.out.println("未注册的用户名");
        }
        else {
            System.out.println("输入身份证号码");
            String personID = scanner.next();
            System.out.println("输入手机号码");
            String phoneNumber = scanner.next();
            User user = list.get(findIndex(list,name));
            if (!(user.getPersonID().equalsIgnoreCase(personID) && user.getPhoneNumber().equals(phoneNumber))){
                System.out.println("信息有误");
                return;
            }
            String password;
            while (true) {
                System.out.println("输入新的密码");
                password = scanner.next();
                System.out.println("再次输入密码");
                String pwd = scanner.next();
                if (password.equals(pwd)) {
                    System.out.println("设置密码成功");
                    break;
                } else {
                    System.out.println("两次输入的密码不一致，重新输入");
                }
            }
            user.setPassword(password);
            System.out.println("密码已重置");
        }
    }

    private static boolean checkName(String name) {
        int length = name.length();
        if (length > 15 || length < 3) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }

        int count = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                count++;
            }
        }
        return count > 0;
    }

    private static boolean contains(ArrayList<User> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkPersonID(String personID) {
        if (personID.length() != 18) {
            return false;
        }
        if (personID.charAt(0) == 0) {
            return false;
        }
        for (int i = 0; i < personID.length() - 1; i++) {
            char c = personID.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        char endChar = personID.charAt(personID.length() - 1);
        if ((endChar >= '0' && endChar <= '9') || endChar == 'X' || endChar == 'x') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.charAt(0) == 0) {
            return false;
        }
        if (phoneNumber.length() != 11) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
    private static String getCode(){
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char)('A' + i));
        }
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }
        int number = random.nextInt(10);
        sb.append(number);
        char[] arr = sb.toString().toCharArray();
        int randomIndex = random.nextInt(arr.length);
        int temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = (char) temp;
        return new String(arr);
    }
    private static boolean checkUserInfo(ArrayList<User> list, User userInfo){
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getName().equals(userInfo.getName()) && user.getPassword().equals(userInfo.getPassword())){
                return true;
            }
        }
        return false;
    }
    private static int findIndex(ArrayList<User> list, String name){
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
}