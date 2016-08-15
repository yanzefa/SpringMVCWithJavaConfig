package com.zhangzhihao.SpringMVCWithJavaConfig.Utils;


import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.security.MessageDigest;

import static com.zhangzhihao.SpringMVCWithJavaConfig.Utils.LogUtils.LogToDB;

public class FileUtils {
    /**
     * 删除指定文件夹
     *
     * @param folderPath 文件夹路径
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean deleteFolder(@NotNull final String folderPath)
            throws Exception {
        File dir = new File(folderPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    file.delete();
                } catch (Exception e) {
                    LogToDB(e);
                    throw e;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 判断文件是不是图片
     *
     * @param tempFile 文件
     * @return 是不是图片
     * @throws Exception 异常
     */
    public static boolean isImage(@NotNull final File tempFile)
            throws Exception {
        @Cleanup ImageInputStream imageInputStream = ImageIO.createImageInputStream(tempFile);
        return imageInputStream != null;
    }

    /**
     * 为MultipartFile创建MD5值
     *
     * @param file MultipartFile
     * @return MD5
     * @throws Exception 异常
     */
    public static StringBuilder createMd5OfMultipartFile(@NotNull final MultipartFile file)
            throws Exception {
        StringBuilder sb = new StringBuilder();
        //生成MD5实例
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        @Cleanup InputStream inputStream = file.getInputStream();
        int available = inputStream.available();
        byte[] bytes = new byte[available];
        md5.update(bytes);
        for (byte by : md5.digest()) {
            //将生成的字节MD5值转换成字符串
            sb.append(String.format("%02X", by));
        }
        return sb;
    }


    /**
     * 保存上传的文件到指定路径
     *
     * @param savePath     路径
     * @param fileFullName 文件名
     * @param file         上传的文件
     * @return 是否保存成功
     * @throws Exception 异常
     */
    public static boolean saveFile(@NotNull final String savePath,
                                   @NotNull final String fileFullName,
                                   @NotNull final MultipartFile file)
            throws Exception {
        byte[] data = readInputStream(file.getInputStream());
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File uploadFile = new File(savePath + fileFullName);
        //判断文件夹是否存在，不存在就创建一个
        File fileDirectory = new File(savePath);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new Exception("文件夹创建失败！路径为：" + savePath);
            }
        }
        //创建输出流
        try (FileOutputStream outStream = new FileOutputStream(uploadFile)) {
            outStream.write(data);
            outStream.flush();
        } catch (Exception e) {
            LogToDB(e);
            throw e;
        }
        return uploadFile.exists();
    }

    /**
     * 从InputStream中读取字节数组
     *
     * @param inStream InputStream
     * @return 字节数组
     * @throws Exception 异常
     */
    public static byte[] readInputStream(@NotNull final InputStream inStream)
            throws Exception {
        @Cleanup ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 从stream中保存文件
     *
     * @param inputStream inputStream
     * @param filePath    保存路径
     * @throws Exception 异常 抛异常代表失败了
     */
    public static void saveStreamToFile(@NotNull final InputStream inputStream,
                                        @NotNull final String filePath)
            throws Exception {
         /*创建输出流，写入数据，合并分块*/
        OutputStream outputStream = new FileOutputStream(filePath);
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }
        } catch (IOException e) {
            LogToDB(e);
            throw e;
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }

}
