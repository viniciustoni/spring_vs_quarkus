import io.quarkus.runtime.Quarkus;

// DIFF: On Quarkus we don't need it, I created it in here only for test, if you want to use, you can uncomment @QuarkusMain
//@QuarkusMain
public class QuarkusProductServiceApplication {

    public static void main(String[] args) {
        Quarkus.run(args);
    }

}
