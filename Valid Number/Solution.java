class Solution {
    public boolean isNumber(String s) {
        String int_regex = "^(-|\\+)?[0-9]+$";
        String decimal_regex = "^(-|\\+)?((([0-9]+)?\\.[0-9]+)|[0-9]+?\\.[0-9]*)$";
        String expo_regex = "^(-|\\+)?(([0-9]+)|((([0-9]+)?\\.[0-9]+)|[0-9]+?\\.[0-9]*))e(-|\\+)?[0-9]+$";

        s = s.trim();

        return s.matches(int_regex) || s.matches(decimal_regex) || s.matches(expo_regex);
    }
}