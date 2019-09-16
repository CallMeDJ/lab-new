package com.bigbanana.lab.lab3.alyenc.step1;

import com.google.common.primitives.Bytes;

import java.util.*;
import java.util.stream.Stream;

/**
 * @description 分布式文件系统LEVEL 1
 * @package com.bigbanana.lab.lab3.alyenc.step1
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/7/7 16:59
 * @version v1.0.0
 */
public class AlyencDistributeFSInMemory {

    private final static String COMM_GET = "get";
    private final static String COMM_PUT = "put";

    public static Map<String, Map<String, byte[]>> servers = new HashMap<>();
    public static Integer serverSize = 3;

    static {
        /**
         * 初始化三台虚拟的机器
         */
        for(int i = 0 ; i < serverSize ;i++){
            Map<String, byte[]> server = new HashMap<>();
            servers.put(i+"",server);
        }
    }

    public static void main(String[] args) {

        /**
         * 命令行初始化
         */
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] commandArray = command.split(" ");

            if(commandArray.length < 2){
                System.out.println("command is illegal");
                continue;
            }

            String targetCommand = commandArray[0];
            String fileName = commandArray[1];

            switch (targetCommand) {
                case COMM_GET :
                    execGet(fileName);
                    break;

                case COMM_PUT :
                    if(commandArray.length < 3){
                        System.out.println("command is illegal");
                        break;
                    }
                    String file = commandArray[2];
                    execPut(fileName, file);
                    break;

                default:
                    System.out.println("command is illegal");
            }
        }
    }

    private static void execGet(String fileName){
        Integer serverIndex = getServerIndex(fileName);

        Map<String, byte[]> fileBucket = servers.get(serverIndex.toString());

        if(null == fileBucket){
            System.out.println("file bucket not found");
            return;
        }

        byte[] fileBytes = fileBucket.getOrDefault(fileName, new byte[0]);
        System.out.println(new String(fileBytes));

    }

    private static void execPut(String fileName, String file){
        Integer serverIndex = getServerIndex(fileName);
        Map<String, byte[]> fileBucket = servers.get(serverIndex.toString());

        byte[] fileBytes = file.getBytes();

        fileBucket.put(fileName, fileBytes);
        System.out.println("success");
    }

    private static Integer getServerIndex(String fileName){
        return fileName.hashCode() % serverSize;
    }
}
