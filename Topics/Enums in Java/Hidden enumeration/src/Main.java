import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println(Arrays.stream(Secret.values()).filter(secret -> secret.toString().startsWith("STAR")).count());

    }
}


enum Secret {
    STAR, CRASH, START, // ...
}
