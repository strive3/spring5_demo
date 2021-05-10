package com.spring.study.util;

import java.io.*;
import java.util.zip.*;

/**
 * @Author duxiaopeng
 * @Date 2021/5/10 9:58 上午
 * @Description TODO
 */
public class ZipUtils {
    /**
     * Default buff byte size
     *
     */
    private static final int DEFAULT_BUFF_SIZE = 1024;

    /**
     * Default basedir value
     *
     */
    private static final boolean DEFAULT_DIR = false;

    public static void decompress(String srcPath) throws Exception {
        decompress(new File(srcPath));
    }

    public static void decompress(File srcFile) throws Exception {
        File baseFile = srcFile.getParentFile();
        decompress(srcFile, baseFile);
    }

    public static void decompress(String srcPath, String destPath) throws Exception {
        decompress(new File(srcPath), new File(destPath));
    }

    public static void decompress(File srcFile, File destFile) throws Exception {
        CheckedInputStream cis = new CheckedInputStream(new FileInputStream(srcFile), new CRC32());
        ZipInputStream zis = new ZipInputStream(cis);
        doDecompress(destFile, zis);
        zis.close();
    }

    private static void doDecompress(File destFile, ZipInputStream zis) throws Exception {
        ZipEntry zipEntry = null;
        while ((zipEntry = zis.getNextEntry()) != null) {
            String dir = destFile.getPath() + File.separator + zipEntry.getName();
            File dirFile = new File(dir);
            // 如果父文件夹不存在，则递归创建其父文件夹
            fileProber(dirFile);
            if (zipEntry.isDirectory()) {
                // 如果zipEntry是目录，则创建目录
                dirFile.mkdirs();
            } else {
                // 解压压缩文件的其中具体的一个zipEntry对象
                doDecompressFile(dirFile, zis);
            }
            zis.closeEntry();
        }
    }
    // 一般意义上的文件复制操作
    private static void doDecompressFile(File destFile, ZipInputStream zis) throws Exception {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        int len;
        byte[] buff = new byte[DEFAULT_BUFF_SIZE];
        while ((len = zis.read(buff, 0 ,DEFAULT_BUFF_SIZE)) != -1) {
            bos.write(buff, 0, len);
        }
        bos.close();
    }

    /**
     * 文件探测
     *
     * When the parent file not exist.Create it.
     *
     * @param dirFile
     * @throws Exception
     */
    public static void fileProber(File dirFile) throws Exception {
        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {
            fileProber(parentFile);
            parentFile.mkdirs();
        }
    }
    // 压缩入口1
    public static void compress(String srcPath, String destPath,boolean dirFlag) throws Exception {
        compress(new File(srcPath), new File(destPath), dirFlag);
    }
    /**
     * @Author du-xp
     * @Date 2021/5/10
     * @param srcPath: 文件源路径
     * @param destPath: 目标路径
     * @return: void
     * @Description 压缩入口2
     */
    public static void compress(String srcPath, String destPath) throws Exception {
        compress(new File(srcPath), new File(destPath), DEFAULT_DIR);
    }
    // 压缩入口3
    public static void compress(File srcFile, File destFile, boolean dirFlag) throws Exception {
        compress(srcFile, new ZipOutputStream(new FileOutputStream(destFile)), dirFlag);
    }

    public static void compress(File srcFile, ZipOutputStream zos, boolean dirFlag) throws Exception {
        // 需要解压的压缩文件对象
        // 压缩输出流
        // 是否在压缩文件时创建一个父文件夹后再压缩
        if (srcFile.isDirectory()) {
            if (dirFlag) {
                doCompress(zos, srcFile, srcFile.getName() + File.separator);
            } else {
                doCompress(zos, srcFile, "");
            }
        } else {
            doCompress(zos, srcFile, "");
        }
        zos.close();
    }

    public static void doCompress(ZipOutputStream zos, File file, String baseDir) throws Exception {
        if (file.isDirectory()) {
            // 递归循环，只压缩其中所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                doCompress(zos, files[i], baseDir);
            }
        } else {
            // 进行文件压缩的操作
            byte[] buff = new byte[DEFAULT_BUFF_SIZE];
            InputStream in = new FileInputStream(file);
            zos.putNextEntry(new ZipEntry(baseDir + File.separator + file.getName()));
            int len;
            while ((len = in.read(buff,0 ,DEFAULT_BUFF_SIZE)) != -1) {
                zos.write(buff, 0, len);
            }
            in.close();
        }
    }


    public static void main(String[] args) {
        //压缩
        try {
            compress("/Users/duxiaopeng/myFiles/中金支付/Java开发-李星辰.pdf", "/Users/duxiaopeng/myFiles/中金支付/Java开发-李星辰.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //解压

    }
}

