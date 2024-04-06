/*
 * Copyright (c) 2023 ivfzhou
 * cert_parser is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *          http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package csms.cmd;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.security.auth.x500.X500Principal;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.concurrent.Callable;

import csms.vo.CertificateVo;

@Component
@Command(name = "certificate")
public class Certificate implements Callable<Integer> {

    @Option(names = {"-f"}, required = true, description = "证书文件路径")
    private String filePath;

    @Option(names = {"-p"}, description = "密码")
    private String passwd;


    @Override
    public Integer call() throws Exception {
        // 获取证书
        KeyStore pkcs12;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            pkcs12 = KeyStore.getInstance("pkcs12");
            if (passwd != null) {
                pkcs12.load(fis, passwd.toCharArray());
            }
        }

        // 解析数据
        Enumeration<String> aliases = pkcs12.aliases();
        CertificateVo certificateVo = new CertificateVo();
        if (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            certificateVo.setPrivateKeyName(alias);
            X509Certificate certificate = (X509Certificate) pkcs12.getCertificate(alias);
            byte[] data = certificate.getEncoded();
            // 计算 SHA1 值
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] digest = sha1.digest(data);
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte v : digest) {
                sb.append(String.format("%02X", v));
            }
            certificateVo.setSHA1(sb.toString());
            // 时间
            certificateVo.setExpirationDate(certificate.getNotAfter());
            certificateVo.setCreatationDate(certificate.getNotBefore());
            // 组织等信息
            X500Principal principal = certificate.getSubjectX500Principal();
            String subject = principal.getName();
            for (String v : subject.split(", ")) {
                if (v.startsWith("UID")) {
                    certificateVo.setBundleId(v.split("=")[1]);
                } else if (v.startsWith("OU")) {
                    certificateVo.setTeamId(v.split("=")[1]);
                } else if (v.startsWith("CN")) {
                    certificateVo.setCerName(v.split("=")[1]);
                } else if (v.startsWith("O")) {
                    certificateVo.setTeamName(v.split("=")[1]);
                }
            }
        }

        // 输出 JSON 数据到标准输出
        System.out.println(new Gson().toJson(certificateVo));
        return 0;
    }
}
