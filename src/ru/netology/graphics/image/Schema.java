package ru.netology.graphics.image;

public class Schema implements TextColorSchema {
    private final char[] letters = new char[] {'#','$','@','%','*','+','-','\''};
    @Override
    public char convert(int color) {
        return letters[color / 32];
    }
}
