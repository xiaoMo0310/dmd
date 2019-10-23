package com.dmd;

import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.base.result.FileResult;
import com.dmd.exception.UploadException;
import lombok.Data;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/23 16:35
 * @Description  上传文件工具类
 */
@Data
@ConfigurationProperties(prefix = "dmd.uploadfile")
public class FileUploadUtil {

    private String extension;
    private String prefix;
    private String attachmentPath;

    /**
     * 方法描述：根据文件的绝对路径创建一个文件对象.
     * @param filePath 文件的绝对路径
     * @return 返回创建的这个文件对象
     */
    public File createFile(String filePath) throws IOException {
        // 获取文件的完整目录
        String fileDir = FilenameUtils.getFullPath(filePath);
        // 判断目录是否存在，不存在就创建一个目录
        File file = new File(fileDir);
        if (!file.isDirectory()) {
            //创建失败返回null
            if (!file.mkdirs()) {
                throw new IOException("文件目录创建失败...");
            }
        }
        // 判断这个文件是否存在，不存在就创建
        file = new File(filePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("目标文件创建失败...");
            }
        }
        return file;
    }

    /**
     * 方法描述：判断extension中是否存在extName
     * @param extName   文件的后缀名
     */
    private void isContains(String extName) throws UploadException {
        if (StringUtils.isNotEmpty(extension)) {
            // 切割文件扩展名
            String[] exts = StringUtils.split(extension, ",");
            if (ArrayUtils.isNotEmpty(exts)) {
                assert exts != null;
                List<String> extList = Arrays.asList(exts);
                //判断
                if (!extList.contains(extName)) {
                    throw new UploadException(ErrorCodeEnum.OPC10040003);
                }
            } else {
                // 判断文件的后缀名是否为extension
                if (!extension.equalsIgnoreCase(extName)) {
                    throw new UploadException(ErrorCodeEnum.OPC10040003);
                }
            }
        }
    }

    /**
     * 方法描述：处理上传的图片
     * @param serverPath 图片的绝对路径
     * @param childFile  父路径
     * @param extName    文件的后缀
     */
    private String thumbnails(String serverPath, String childFile, String extName)
            throws IOException {
        // 压缩后的相对路径文件名
        String toFilePath = getDestPath(childFile, extName);

        // scale：图片缩放比例
        // outputQuality：图片压缩比例
        // toFile：图片位置
        // outputFormat：文件输出后缀名
        // Thumbnails 如果用来压缩 png 格式的文件，会越压越大，
        // 得把png格式的图片转换为jpg格式
        if ("png".equalsIgnoreCase(extName)) {
            // 由于outputFormat会自动在路径后加上后缀名，所以移除以前的后缀名
            String removeExtensionFilePath = FilenameUtils.removeExtension(toFilePath);
            Thumbnails.of(serverPath).scale(1f)
                    .outputQuality(1f)
                    .outputFormat("jpg")
                    .toFile(getServerPath(childFile, removeExtensionFilePath));
            toFilePath = removeExtensionFilePath + ".jpg";
        } else {
            Thumbnails.of(serverPath).scale(1f).outputQuality(0.25f)
                    .toFile(getServerPath(childFile, toFilePath));
        }

        // 删除被压缩的文件
        FileUtils.forceDelete(new File(serverPath));

        return toFilePath;
    }

    /**
     * 方法描述：生成文件文件名
     * 创建时间：2018-10-20 20:46:18
     * @param childFile 子目录
     * @param extName   后缀名
     *
     */
    //
    private String getDestPath(String childFile, String extName) {
        //规则：  子目录/年月日_随机数.后缀名
        String sb = "/" + childFile +"/"
                    + System.currentTimeMillis()
                    + "_" + RandomStringUtils.randomNumeric(8)
                    + "." + extName;
        return sb;
    }

    /**
     * 方法描述：生成文件在的实际的路径
     * 创建时间：2018-10-20 20:46:18
     * @param childFile 文件父路径
     * @param destPath 文件的相对路径
     */
    private String getServerPath(String childFile, String destPath) {
        // 文件分隔符转化为当前系统的格式
        return FilenameUtils.separatorsToSystem(attachmentPath + destPath);
    }

    /**
     * 方法描述：上传文件.
     * 创建时间：2018-10-19 13:09:19
     *
     * @param multipartFile 上传的文件对象，必传\
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param isImage       上传的是否是图片，如果是就会进行图片压缩；如果不希望图片被压缩，则传false，让其以文件的形式来保存
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    private FileResult holdFile(MultipartFile multipartFile, String childFile, Boolean isImage) throws IOException, UploadException {
        if (null == multipartFile || multipartFile.isEmpty()) {
            throw new IOException("上传的文件对象不存在...");
        }
        // 文件名
        String fileName = multipartFile.getOriginalFilename();

        // 文件后缀名
        String extName = FilenameUtils.getExtension(fileName);
        if (StringUtils.isEmpty(extName)) {
            throw new UploadException(ErrorCodeEnum.OPC10040003);
        }
        // 判断文件的后缀名是否符合规则
        isContains(extName);
        // 创建目标文件的名称，规则请看destPath方法
        String destPath = getDestPath(childFile, extName);
        // 文件的实际路径
        String serverPath = getServerPath(childFile, destPath);
        // 创建文件
        File destFile = createFile(serverPath);
        // 保存文件
        multipartFile.transferTo(destFile);

        // 拼装返回的数据
        FileResult result = new FileResult();
        //如果是图片，就进行图片处理
        if (BooleanUtils.isTrue(isImage)) {
            // 图片处理
            String toFilePath = thumbnails( serverPath, childFile, extName);
            // 得到处理后的图片文件对象
            File file = new File(getServerPath(childFile, toFilePath));
            // 压缩后的文件后缀名
            String extExtName = FilenameUtils.getExtension(toFilePath);
            // 源文件=源文件名.压缩后的后缀名
            //String extFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(toFilePath);
            result.setFileSize(file.length());
            result.setServerPath(prefix + toFilePath);
            //result.setFileName(extFileName);
            result.setExtName(extExtName);
        } else {
            result.setFileSize(multipartFile.getSize());
            //result.setFileName(fileName);
            result.setExtName(extName);
            result.setServerPath(prefix + destPath);
        }
        return result;
    }

    /**
     * 方法描述：上传文件.
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    public FileResult saveFile(MultipartFile multipartFile, String childFile, boolean isImage) throws IOException, UploadException {
        try {
            FileResult fileResult = holdFile(multipartFile, childFile, isImage);
            return fileResult;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadException(ErrorCodeEnum.OPC10040002);
        }
    }

    /**
     * 方法描述：上传图片.
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    public FileResult saveImage(MultipartFile multipartFile, String childFile) throws IOException, UploadException {
        try {
            FileResult fileResult = saveFile(multipartFile, childFile, true);
            return fileResult;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadException(ErrorCodeEnum.OPC10040002);
        }

    }

    /**
     * 方法描述：多文件上传
     * @param multipartFiles 上传的所有的文件对象，必传
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    public List<FileResult> saveFiles(MultipartFile[] multipartFiles, String childFile, boolean isImage) throws UploadException {
        List<FileResult> fleResultlist =new ArrayList<>();
        for (int i = 0; i < multipartFiles.length; i++) {
            try {
                FileResult fileResult = saveFile(multipartFiles[i], childFile, isImage);
                fleResultlist.add(fileResult);
            } catch (IOException e) {
                e.printStackTrace();
                throw new UploadException(ErrorCodeEnum.OPC10040002);
            }
        }
        return fleResultlist;
    }
}
