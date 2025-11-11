import java.util.Scanner;

class B1 {
   
    static {
        System.loadLibrary("B1");
    }

  
    private native int add(int a, int b);
    private native int sub(int a, int b);
    private native int mult(int a, int b);
    private native int div(int a, int b);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- JNI Mathematical Operations ---");

        System.out.print("Enter value for a: ");
        int a = sc.nextInt();

        System.out.print("Enter value for b: ");
        int b = sc.nextInt();

        int ch;
        do {
            System.out.println("\n-- Menu --");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("ENTER YOUR CHOICE: ");
            ch = sc.nextInt();

            B1 calculator = new B1(); // Create an instance to call the native methods

            switch (ch) {
                case 1:
                    calculator.add(a, b);
                    break;
                case 2:
                    calculator.sub(a, b);
                    break;
                case 3:
                    calculator.mult(a, b);
                    break;
                case 4:
                    if (b != 0) {
                        calculator.div(a, b);
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (ch < 5);

        sc.close();
    }
}
