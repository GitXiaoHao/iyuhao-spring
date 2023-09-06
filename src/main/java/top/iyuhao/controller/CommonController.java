package top.iyuhao.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.iyuhao.utils.exception.MyException;
import top.iyuhao.utils.result.Result;
import top.iyuhao.utils.result.ResultCodeEnum;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuhao
 * @date 2023/8/25
 **/
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${filePath.imagePath}")
    private String imagePath;
    @SneakyThrows
    @PostMapping("/upload")
    public Result<String> upload(@RequestPart("file") MultipartFile file){
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        //获得原始文件名
        String originalFilename = file.getOriginalFilename();
        //使用uuid重新生成文件名，防止文件名重复
        String uuid = UUID.randomUUID().toString();
        //截取最后一个文件格式
        assert originalFilename != null;
        String fileName = uuid + originalFilename.substring(originalFilename.lastIndexOf("."));
        //创建目录
        String canonicalPath = ResourceUtils.getFile("classpath:").getPath();

        File dir = new File(canonicalPath + imagePath + fileName);
        //判断当前目录是否存在
        if(!dir.exists()){
            //不存在就创建
            boolean mkdirs = dir.mkdirs();
            if(!mkdirs){
                throw new MyException(ResultCodeEnum.FAIL.getCode(),"文件上传失败");
            }
        }
        //传输位置
        file.transferTo(dir);
        return Result.ok(fileName);
    }
    /**
     * 文件下载
     */
    @SneakyThrows
    @GetMapping("/download")
    public void downLoad(String name, HttpServletResponse response){
        //设置响应的文件
        response.setContentType("image/jpeg");
        //输入流 通过输入流读取文件内容
        String canonicalPath = ResourceUtils.getFile("classpath:").getPath();
        FileInputStream inputStream = new FileInputStream(canonicalPath + imagePath + name);
        //输出流 通过输出流将文件写回浏览器
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,len);
            outputStream.flush();
        }
        inputStream.close();
        outputStream.close();
    }
    @SneakyThrows
    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String, Object> map){
        String name = (String) map.get("name");
        String canonicalPath = ResourceUtils.getFile("classpath:").getPath();
        String path = canonicalPath + imagePath + name;
        File file = new File(path);
        if(file.exists()){
            if(file.delete()){
                return Result.ok("删除成功");
            }
        }
        return Result.fail("删除失败");
    }

}
