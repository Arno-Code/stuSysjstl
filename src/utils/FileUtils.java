package utils;
import java.util.UUID;

public class FileUtils {

    /**
     * 获取真实的文件名
     * @param name
     * @return
     */
    public static String getRealFileName(String name){
        int index = name.lastIndexOf("\\");
        return name.substring(index+1);
    }


    /**
     * 生成文件名
     * @param name
     * @return
     */
    public static String getUUIDFileName(String name){
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString().replaceAll("-", "");
        return s+name;
    }
}
