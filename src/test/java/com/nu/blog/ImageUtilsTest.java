package com.nu.blog;

import com.nu.blog.base.utils.ImageUtils;

/**

 */
public class ImageUtilsTest {
    public static void main(String[] args) throws Exception {
//        File file = new File("F:/data/test.png");
//        byte[] bytes = ImageUtils.screenshot(Thumbnails.of(file), Positions.CENTER,360, 240);
//        FileKit.writeByteArrayToFile(bytes, "F:/data/test_out.jpg");

        byte[] bytes = ImageUtils.download("https://images.gitee.com/uploads/images/2021/0125/142627_fcd67bfd_116277.jpeg");
        System.out.println(bytes);
    }

}
