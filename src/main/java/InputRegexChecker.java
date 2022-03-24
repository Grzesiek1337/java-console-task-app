
public class InputRegexChecker {

    // yyyy-mm-dd
    public static boolean checkLocalDateRegex(String localDateInString) {
        return localDateInString.matches("((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
    }
}
