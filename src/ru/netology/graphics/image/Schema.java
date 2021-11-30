package ru.netology.graphics.image;

public class Schema implements TextColorSchema {
    private static final char[] LETTERS = new char[] {'#','$','@','%','*','+','-','\''};
    @Override
    public char convert(int color) {
        return LETTERS[color / 32];
    }
}
