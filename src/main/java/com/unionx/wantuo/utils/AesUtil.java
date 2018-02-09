package com.unionx.wantuo.utils;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class AesUtil {
	public static final String localkey="q1w2e3r4t5y6u7i8";
	// 加密
    public static String Encrypt(String content, String key) throws Exception {
        if (key == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        if(key==localkey){
        	 byte[] raw = key.getBytes();
             SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
             Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
             IvParameterSpec iv = new IvParameterSpec("0000000000123456".getBytes());
             cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
             byte[] encrypted = cipher.doFinal(content.getBytes());
             String hexStr =byte2hex(encrypted);
             return hexStr;
        }
        else{
        	return null;
        }
    }
    // 解密
    public static String Decrypt(String content, String key) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            if(key==localkey){
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0000000000123456".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] encrypted1 = hex2byte(content);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
            }else{
            	 return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
    public static void main(String[] args) throws Exception {
        /*
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
         */
        String cKey = "q1w2e3r4t5y6u7i8";
//        // 需要加密的字串
     String cSrc = "[{'taskUrl':'http://192.168.1.178:8080/wantuo/switch/list'"+","
                    +"'token':"+"'06424332FFFFFFB234B172EF48D30399'"+","
     		        + "'key':"+"'06424332FFFFFFB234B172EF48D30382'"+","
     		+"'clientID':"+"'06413EACFFFFFFB234B172EF39C4B918'"+","
     		+"'timeStamp':"+"''"+","        
     		+"'taskParams':"+"''"+"}]";
     System.out.println(cSrc);
//       
   String enString = AesUtil.Encrypt(cSrc, cKey);
     System.out.println("加密后的字串是：" + enString);
// 
//        // 解密
        String DeString = AesUtil.Decrypt("7552AEEE5A4A9B2850FB0481F77A0B1E49B24E47EBD5B6978C5623DBF6E4DA36FE954AAE17ECE6ED8C29DB167B74A92B7622556BB740879ADA6CDA0B6769537D4D09521BD2DA3CBDFD5F72D0C892FABA15B6A5F2E02290D828AAF227D939C48818F04505DC5E513931794A15A90B790C079037E918FDB0B3EE3557E40031FEC51403554A7CA61BFDAF0173ADFDAD1713EA75EA0A055451770F1790921E630302A4FC9A8EF2194E48A3D8794C852491667B3F6FBCDA146E657472BB400E3A61CEFFC24D494FEDD352FA4B8E7344A049D942AFB885F4E279F3E6A75CE5A3CF469662AD0FAED162DB248BD1936544D9EC30D2A6E1080F2263256AE5CDAA8927D7DA6542EAE996A4332317503C27A90914C38D2EF61F1ED1FE6782B004349046E48EB4FC659E14650D5F2030143EF68C05AE992BF9AFEC5FC95625440D1439FB1AC1D7AF1DECB17139D660B8035C3E1C13842A24CF0E13B7EEA5C109A3832BCCBFB99A764E205D0C04DDE9CF640F8A91056002729AF64E70B35FD9AF280A1ABE9A1FA54B8A17B338953F1D94B6B643EA9017BF99AFA0EBE25BE6101F32BBA87D2459AC6191FF72A9BEC1B4DA5DF6F3182F3C7CFF624B5A17239153B4A99CE04873BDE3E62B0FE2A23D09D9AD42D6B4C7F2CAE55C900C70832860D937A19E6F2F13C127EB3F73FEA6C5EEDCC3B369911E6B1674004C1C5C9A060255E41B5BE8B71B85", cKey);
        System.out.println("解密后的字串是：" + DeString);
        System.out.println(AesUtil.EncryptText("D", "242039303b7189c48a43e51732b64e23.epub", cKey));
    } 
   // 
    public static byte[] EncryptText(String filePath,String fileName,String key) throws Exception {
        String pathandname=filePath+"://"+fileName;
    	byte[] content=EncodeUtil.getByte(pathandname);
        if (key == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        if(key==localkey){
        	 byte[] raw = key.getBytes();
             SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
             Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
             IvParameterSpec iv = new IvParameterSpec("0000000000123456".getBytes());
             cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
             byte[] encrypted = cipher.doFinal(content);
             String hexStr =byte2hex(encrypted);
             byte[] jiamihou=AesUtil.hex2byte(hexStr);
             return jiamihou;
        }
        else{
        	return null;
        }
    }
}
