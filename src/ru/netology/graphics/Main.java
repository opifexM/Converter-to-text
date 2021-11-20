package ru.netology.graphics;

import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
        // String url = "https://www.pngkey.com/png/full/113-1134700_download-svg-download-png-google-dog-emoji.png";
        // String imgTxt = converter.convert(url);
        // System.out.println(imgTxt);
    }
}
