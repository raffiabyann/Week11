package week11.raffi.id.umn.ac;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.AuthenticationException;
import exceptions.ExcessiveFailedLoginException;

public class Main {

    private static ArrayList<User> listOfUser = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void initialize() {
        User defaultUser = new User(
                "John",
                "Doe",
                'L',
                "Jl. Merpati No.1 RT 1 RW 1, Banten",
                "admin",
                "admin"
        );
        listOfUser.add(defaultUser);
    }

    public static void handleLogin() {
        System.out.print("Username : ");
        String username = sc.nextLine();

        System.out.print("Password : ");
        String password = sc.nextLine();

        try {
            for (User u : listOfUser) {
                if (u.getUserName().equals(username)) {
                    if (u.login(username, password)) {
                        System.out.println(u.greeting());
                        return;
                    }
                }
            }
            throw new AuthenticationException("Username atau password salah");
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        } catch (ExcessiveFailedLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handleSignUp() {
        System.out.print("Nama Depan : ");
        String f = sc.nextLine();

        System.out.print("Nama Belakang : ");
        String l = sc.nextLine();

        System.out.print("Jenis Kelamin (L/P) : ");
        char g = sc.nextLine().toUpperCase().charAt(0);

        System.out.print("Alamat : ");
        String addr = sc.nextLine();

        System.out.print("Username : ");
        String uname = sc.nextLine();

        if (uname.length() < 4) {
            System.out.println("Username harus lebih dari 4 karakter");
            return;
        }

        for (User u : listOfUser) {
            if (u.getUserName().equals(uname)) {
                System.out.println("Username sudah dipakai!");
                return;
            }
        }

        System.out.print("Password : ");
        String pass = sc.nextLine();

        if (!validatePassword(pass)) {
            return;
        }

        User newUser = new User(f, l, g, addr, uname, pass);
        listOfUser.add(newUser);

        System.out.println("User telah berhasil didaftarkan");
    }

    private static boolean validatePassword(String pass) {

        if (pass.length() < 6 || pass.length() > 16) {
            System.out.println("Password harus minimum 6 karakter dan maksimum 16 karakter");
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;

        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
        }

        if (!hasUpper) {
            System.out.println("Password harus mengandung huruf besar");
            return false;
        }

        if (!hasDigit) {
            System.out.println("Password harus mengandung angka");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        initialize();

        while (true) {
            System.out.println("\nMenu Utama");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan : ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    handleSignUp();
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }
}
