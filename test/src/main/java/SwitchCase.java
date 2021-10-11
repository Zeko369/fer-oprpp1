public class SwitchCase {
    enum DOW {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    private static DOW randomDay() {
        return DOW.Saturday;
    }

    public static void main(String[] args) {
        String type = switch (SwitchCase.randomDay()) {
            case Monday, Tuesday, Wednesday, Thursday, Friday -> "week";
            default -> "weekend";
        };

        // if using regular : syntax, use `yield`

        System.out.println(type);
    }
}
