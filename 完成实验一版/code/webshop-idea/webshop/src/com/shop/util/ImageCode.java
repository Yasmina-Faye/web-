//package com.shop.util;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Map;
//import java.util.Random;
//
//import javax.imageio.ImageIO;
//import javax.imageio.stream.ImageOutputStream;
//
//import com.sun.imageio.plugins.common.ImageUtil;
//
//
//public class ImageCode {
//
//    private static int fontHeight = 24;
//
//    private static String ver_codes = "0123456789qwertyuiopasdfghjklzxcvbnm";
//
//
//    public static String getImageCode(int w,int h,OutputStream os) {
//
//        StringBuffer randCode=new StringBuffer();
//        BufferedImage buffImg=new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
//        getImages(w, h, 4, randCode, buffImg);
//        try {
//            ImageIO.setUseCache(false);
//            ImageIO.write(buffImg,"jpeg", os);
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//        return randCode.toString();
//
//    }
//
//
//
//
//
//    public static void getImages(int w,int h,int codeCount,StringBuffer randCode,BufferedImage buffImg) {
//
//        // Graphics2D gd = buffImg.createGraphics();
//        // Graphics2D gd = (Graphics2D) buffImg.getGraphics();
//        Graphics gd = buffImg.getGraphics();
//        // 创建一个随机数生成器类
//        Random random = new Random();
//        // 将图像填充为
//        //gd.setColor(Color.WHITE);
//        gd.setColor(new Color(204, 204, 204));
//        gd.fillRect(0, 0, w, h);
//
//        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
//        gd.setColor(getRandColor(50, 200));
//        for (int i = 0; i < 30; i++) {
//            int x = random.nextInt(w);
//            int y = random.nextInt(h);
//            int xl = random.nextInt(12);
//            int yl = random.nextInt(12);
//            gd.drawLine(x, y, x + xl, y + yl);
//        }
//
//
//
//
//        // 创建字体，字体的大小应该根据图片的高度来定。
//        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
//        // 设置字体。
//        gd.setFont(font);
//
//        //绘制验证码字符
//        char[] charArray = ver_codes.toCharArray();
//        int red = 0, green = 0, blue = 0;
//        for (int i = 0; i < codeCount; i++) {
//            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
//            red = random.nextInt(200);
//            green = random.nextInt(200);
//            blue = random.nextInt(200);
//
//            // 用随机产生的颜色将验证码绘制到图像中。
//            gd.setColor(new Color(red, green, blue));
//
//            String code =String.valueOf(charArray[random.nextInt(charArray.length)]);
//            //获取每一位字符绘制到图像中
//
//            gd.drawString(code, (int)(i * w/codeCount),fontHeight-(int)(Math.random()*(h-fontHeight))+ (int)(Math.random()*(h-fontHeight)));
//
//            randCode.append(code);
//        }
//
//        gd.dispose();
//
//    }
//    private static Color getRandColor(int fc, int bc) {
//        Random random=new Random();
//        if (fc > 255)
//            fc = 255;
//        if (bc > 255)
//            bc = 255;
//        int r = fc + random.nextInt(bc - fc);
//        int g = fc + random.nextInt(bc - fc);
//        int b = fc + random.nextInt(bc - fc);
//        return new Color(r, g, b);
//    }
//
//    public static void main(String[] args) throws FileNotFoundException {
////		OutputStream os=new FileOutputStream("d://123.jpg");
////		System.out.println(getImageCode(120, 40, os));
//    }
//}
package com.shop.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ImageCode {

    private static final int FONT_HEIGHT = 24;
    private static final String CODES = "0123456789qwertyuiopasdfghjklzxcvbnm";

    /**
     * 生成验证码并输出图片
     */
    public static String getImageCode(int w, int h, OutputStream os) {

        StringBuilder randCode = new StringBuilder();
        BufferedImage buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        drawImage(w, h, 4, randCode, buffImg);

        try {
            ImageIO.setUseCache(false);
            ImageIO.write(buffImg, "jpeg", os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return randCode.toString();
    }

    /**
     * 绘制验证码内容
     */
    private static void drawImage(int w, int h, int codeCount, StringBuilder randCode, BufferedImage buffImg) {

        Graphics g = buffImg.getGraphics();
        Random random = new Random();

        // 背景色
        g.setColor(new Color(204, 204, 204));
        g.fillRect(0, 0, w, h);

        // 干扰线
        g.setColor(getRandColor(50, 200));
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 字体
        g.setFont(new Font("Fixedsys", Font.BOLD, FONT_HEIGHT));

        char[] chars = CODES.toCharArray();

        // 绘制字符
        for (int i = 0; i < codeCount; i++) {

            g.setColor(new Color(random.nextInt(200), random.nextInt(200), random.nextInt(200)));

            String code = String.valueOf(chars[random.nextInt(chars.length)]);

            // 字符位置
            int x = i * (w / codeCount) + 4;
            int y = FONT_HEIGHT + random.nextInt(h - FONT_HEIGHT);

            g.drawString(code, x, y);

            randCode.append(code);
        }

        g.dispose();
    }

    /**
     * 随机颜色
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
