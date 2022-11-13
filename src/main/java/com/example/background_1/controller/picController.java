package com.example.background_1.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;



@RestController
@RequestMapping("/api/pic")
public class picController {
    /*public void Batch() {//get result
        try {
            //String shpath[]={"cmd /k start d:","cmd /k cd D:\\web_2","cmd /k dir"};
            *//*Process ps = Runtime.getRuntime().exec("cmd /k start d:");
            ps.waitFor();*//*
            *//*Process ps1 = Runtime.getRuntime().exec("cmd /k cd D:\\web_2");
            ps1.waitFor();*//*
            String cmd="cmd /c start "+System.getProperty("user.dir")+"\\tryon\\tryon.bat "+clothespicurl+" "+modelpicurl;
            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    private static String UPLOAD_CL_FOLDER = "E:/IDEA_projects/background_1/src/main/resources/clothes_pic/";
    private static String UPLOAD_MD_FOLDER = "E:/IDEA_projects/background_1/src/main/resources/model_pic/";
    private static String RESULT_PIC_FOLDER = "E:/IDEA_projects/background_1/src/main/resources/result_pic/";

    @PostMapping("/uploadPic")
    public String uploadPicture(@RequestBody JSONObject pic) {
        try {
            String picBase64=pic.getString("picture");
            String picType=pic.getString("picType");
            byte[] decodeBase64 = Base64.decodeBase64(picBase64);
            //String path=UPLOAD_CL_FOLDER+String.valueOf(System.currentTimeMillis())+picType;

            File file = File.createTempFile(String.valueOf(System.currentTimeMillis()),"."+picType, new File(UPLOAD_CL_FOLDER));
            FileUtils.writeByteArrayToFile(file, decodeBase64);

            return "upload success!";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "upload failed!";
    }
    @PostMapping("/uploadmodelPic")
    public String uploadmodelPicture(@RequestBody JSONObject pic) {
        try {
            String picBase64=pic.getString("picture");
            String picType=pic.getString("picType");
            byte[] decodeBase64 = Base64.decodeBase64(picBase64);
            File file = File.createTempFile(String.valueOf(System.currentTimeMillis()),picType, new File(UPLOAD_MD_FOLDER));
            FileUtils.writeByteArrayToFile(file, decodeBase64);

            return "uploadmodel success!";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "uploadmodel failed!";
    }

    /*public void Batch() {//get result
        try {
            //String shpath[]={"cmd /k start d:","cmd /k cd D:\\web_2","cmd /k dir"};
            *//*Process ps = Runtime.getRuntime().exec("cmd /k start d:");
            ps.waitFor();*//*
            *//*Process ps1 = Runtime.getRuntime().exec("cmd /k cd D:\\web_2");
            ps1.waitFor();*//*
            String cmd="cmd /c start "+System.getProperty("user.dir")+"\\tryon\\tryon.bat "+clothespicurl+" "+modelpicurl;
            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @PostMapping("/getPic")
    public String getPicture() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(RESULT_PIC_FOLDER+' ');//加上图片名字
            int available = inputStream.available();
            byte[] encodeBase64=new byte[available];
            inputStream.read(encodeBase64);
            String picBase64=Base64.encodeBase64String(encodeBase64);

            return "data:image/"+ "jpg" +";base64,"+picBase64;//根据图片类型更改

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "getpic failed!";

    }



}
