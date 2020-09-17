public class RegexTest1 {
    public static void main(String[] args) {
        String regex="\\d+秒|";
        String name="";
        boolean matches = name.matches(regex);
        System.out.println(matches);
    }
}
