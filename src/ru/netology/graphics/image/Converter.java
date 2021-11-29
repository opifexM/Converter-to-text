package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {
    int width;
    int height;
    int maxWidth;
    int maxHeight;
    double maxRatio;
    TextColorSchema textColorSchema;

    public Converter() {
        maxWidth = Integer.MAX_VALUE;
        maxHeight = Integer.MAX_VALUE;
        maxRatio = Double.MAX_VALUE;
        textColorSchema = new Schema();
    }

    public Converter(int maxWidth, int maxHeight, double maxRatio, TextColorSchema textColorSchema) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.maxRatio = maxRatio;
        this.textColorSchema = textColorSchema;
    }

    private void isRatioCorrect() throws BadImageSizeException {
        double currentRatio = (double) width / (double) height;
        if (currentRatio > maxRatio) {
            throw new BadImageSizeException(currentRatio, maxRatio);
        }
    }

    private Image imgSizeCorrection(BufferedImage img) {
        if (width > maxWidth || height > maxHeight) {
            int sizeWithRatio = width / maxWidth;
            int sizeHeightRatio = height / maxHeight;
            int sizeRatio = Math.max(sizeWithRatio, sizeHeightRatio);
            width /= sizeRatio;
            height /= sizeRatio;
        }
        return img.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
    }

    private BufferedImage convertImgToBlackWhite(Image scaledImage) {
        BufferedImage bwImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        return bwImg;
    }

    private void imgSaveToDisk(BufferedImage img, String fileName) throws IOException {
        ImageIO.write(img, "png", new File(fileName));
    }

    private StringBuilder imgApplySchema(BufferedImage img) {
        WritableRaster bwRaster = img.getRaster();
        int[] arg = new int[3];
        StringBuilder sb = new StringBuilder();
        sb.append("<div style=\"white-space: nowrap;\">");
        for (int h = 0; h < bwRaster.getHeight(); h++) {
            for (int w = 0; w < bwRaster.getWidth(); w++) {
                int color = bwRaster.getPixel(w, h, arg)[0];
                char c = textColorSchema.convert(color);
                sb.append(c).append(c);
                System.out.print(c + "" + c);
            }
            sb.append("\n");
            System.out.println();
        }
        sb.append("</div>");
        return sb;
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));
        width = img.getWidth();
        height = img.getHeight();

        isRatioCorrect();
        Image scaledImage = imgSizeCorrection(img);
        BufferedImage bwImg = convertImgToBlackWhite(scaledImage);

        imgSaveToDisk(bwImg, "bw_pic.png");

        StringBuilder sb = imgApplySchema(bwImg);

        return sb.toString();
    }


    @Override
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    @Override
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema textColorSchema) {
        this.textColorSchema = textColorSchema;
    }
}
