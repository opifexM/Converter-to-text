package ru.netology.graphics.image;

import ru.netology.graphics.image.TextColorSchema;

public class Schema implements TextColorSchema {
    @Override
    public char convert(int color) {

        if (color < 32) {
            return '#';
        }
        else if (color < 64) {
            return '$';
        }
        else if (color < 96) {
            return '@';
        }
        else if (color < 128) {
            return '%';
        }
        else if (color < 160) {
            return '*';
        }
        else if (color < 192) {
            return '+';
        }
        else if (color < 224) {
            return '-';
        }
        return '\'';
    }
}
