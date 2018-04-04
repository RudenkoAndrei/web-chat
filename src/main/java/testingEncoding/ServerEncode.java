package testingEncoding;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
public class ServerEncode {
    public static void main(String[] args){
        String password = "super1234password098newerencode";
        String encode = new  BCryptPasswordEncoder().encode(password);
        System.out.println(password + " === " + encode);
    }
}
