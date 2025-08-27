public class threadTest {

    public static void main(String[] args) {

        System.out.println("1st message");

        try {
            Thread.sleep(1000);
        }

        catch(Exception e) {}

        System.out.println("1 second later.....");

    }
}
