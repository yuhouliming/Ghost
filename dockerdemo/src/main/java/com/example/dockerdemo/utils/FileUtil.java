package com.example.dockerdemo.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static final List<String> correctFileFormat = Arrays.asList(".txt", ".zip", ".json");

    public static String getFileNameFromUrl(String url) {
        String[] dirs = url.split("/");
        return (dirs[dirs.length - 1]);
    }


    /**
     * 将文件写入磁盘指定路径
     *
     * @param file MultipartFile
     * @param path 路径
     * @return <code>True</code> 成功
     */
    public static void writeToDisk(MultipartFile file, String path) {
        createDir(path);
        // 保存文件
        FileOutputStream fos = null;
        try {
            String name = path + "/" + file.getOriginalFilename();
            fos = new FileOutputStream(name);
            fos.write(file.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(fos);
        }
    }

    /**
     * 去除路径多余的符号
     *
     * @param path
     * @return
     */
    public static String prettyPath(String path) {
        char[] pathChars = path.toCharArray();
        int repeatNum = 0; // 路径中间重复的次数
        char preChar = ' ';  // 记录前一个字符
        for (int i = 0; i < pathChars.length; i++) {
            // 当前字符和前一个字符都是“/” 并且当前字符不在末尾
            if (pathChars[i] == '/' && preChar == '/' && i < pathChars.length - repeatNum) {
                boolean has = false;
                while (pathChars[i] == '/') {
                    // 与前一位交换，直到末尾
                    for (int j = i; j < pathChars.length - 1; j++) {
                        char t = pathChars[j + 1];
                        pathChars[j + 1] = pathChars[j];
                        pathChars[j] = t;
                        if (t != '/' && !has) {
                            has = true;
                        }
                    }
                    if (!has) {
                        break;
                    } else {
                        repeatNum++;
                    }
                }
                preChar = '/';
            } else {
                preChar = pathChars[i];
            }
        }
        int num = 0;
        for (int i = pathChars.length - 1; i > 0; i--) {
            if (pathChars[i] != '/') {
                break;
            }
            num++;
        }
        if (path.endsWith("/")) {
            return new String(pathChars).substring(0, path.length() - num + 1);
        }
        return new String(pathChars).substring(0, path.length() - num);
    }


    public static void createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

//    public static void deCompress(String sourceFile, String destDir) throws Exception {
//        // 保证文件夹路径最后是"/"或者"\"
//        char lastChar = destDir.charAt(destDir.length() - 1);
//        if (lastChar != '/' && lastChar != '\\') {
//            destDir += File.separator;
//        }
//        // 根据类型，进行相应的解压缩
//        String type = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
//        if (type.equals("zip")) {
//            ZipUtil.unzip(sourceFile, destDir);
//        } else if (type.equals("rar")) {
//            RarUtil.unrar(sourceFile, destDir);
//        } else if (type.equals("txt")) {
//
//        } else {
//            throw new Exception("只支持zip和rar格式的压缩包！");
//        }
//
//    }


    /**
     * 释放资源
     *
     * @param source 资源
     */
    public static void close(Closeable source) {
        if (source != null) {
            try {
                source.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    /**
     * 从上传的文件获取数据
     *
     * @param path 上传文件保存路径
     * @return
     * @throws IOException
     */



    /**
     * 过滤文本或者json文件
     *
     * @param
     * @return
     */
    public static File[] listFile(String path) {
        File dir = new File(path);
        return dir.listFiles((dir1, name) -> {
            if (name.endsWith(".selRelation") ) {
                return true;
            }
            return false;
        });
    }

    /**
     * 过滤文本或者json文件
     *
     * @param
     * @return
     */
    public static File[] listFile(String path, Collection<String> suffixes) {
        File dir = new File(path);
        return dir.listFiles((dir1, name) -> {
            String suffix = name.substring(name.lastIndexOf(".") + 1);
            if (suffixes.contains(suffix)) {
                return true;
            }
            return false;
        });
    }






    /**
     * 读取文件的每一行数据
     *
     * @return 每行数据列表
     */
    public static List<String> getLines(InputStream inputStream) {

        List<String> lines = new ArrayList<>();
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            isr = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(isr);

            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                close(reader);
            } else {
                close(isr);
            }
        }
        return lines;
    }

    /**
     * 读取文件的每一行数据
     *
     * @return 每行数据列表
     * @throws IOException
     */

    /**
     * 向输出流中写数据
     *
     * @param out  OutputStream 输出流
     * @param data 数据
     */
    public static void writeDataResponse(OutputStream out, List<String> data) {
        try (OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            for (String d : data) {
                osw.write(d);
                osw.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向输出流中写数据
     *
     * @param out  OutputStream 输出流
     * @param data 数据
     */
    public static void writeDataResponse4GBK(OutputStream out, List<String> data) {
        try (OutputStreamWriter osw = new OutputStreamWriter(out, "gbk")) {
            for (String d : data) {
                if (d==null){
                    osw.write("");
                    osw.write("\r\n");
                    continue;
                }
                osw.write(d);
                osw.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String generateName(int capacity) {
        return UUID.randomUUID().toString().replace("-", "")
                .substring(0, capacity);

    }

    public static String currentTime() {

        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        //获取String类型的时间
        return sdf.format(date);
    }

    /**
     * 从存储服务读取的outputStream，需要转化成可以解析的输入流
     *
     * @param out
     * @return input
     */
    public static InputStream swapStream(OutputStream out) {
        ByteArrayOutputStream baos;
        baos = (ByteArrayOutputStream) out;
        return new ByteArrayInputStream(baos.toByteArray());
    }


    public static String getFileContent(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return IOUtils.toString(inputStream, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
