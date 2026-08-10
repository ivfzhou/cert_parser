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

package cn.ivfzhou.cert_parser.cmd;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import xmlwise.Plist;

import cn.ivfzhou.cert_parser.vo.ProvisionVo;

@Component
@Command(name = "provision")
public class Provision implements Callable<Integer> {

    @Option(names = {"-f"}, required = true, description = "描述文件路径")
    private String filePath;

    @Override
    public Integer call() throws Exception {
        // 获取文件内容。
        String text;
        try (var fis = new FileInputStream(filePath)) {
            var bs = fis.readAllBytes();
            var s = new String(bs, StandardCharsets.UTF_8);
            var begin = s.indexOf("<?xml");
            var end = s.lastIndexOf("</plist>");
            text = s.substring(begin, end + "</plist>".length());
        }

        // 解析 xml 数据。
        var plist = Plist.fromXml(text);
        var provisionVo = new ProvisionVo();
        provisionVo.setExpirationDate(((Date) plist.get("ExpirationDate")).getTime());
        provisionVo.setCreationDate(((Date) plist.get("CreationDate")).getTime());
        provisionVo.setName((String) plist.get("Name"));
        provisionVo.setTeamName((String) plist.get("TeamName"));
        provisionVo.setUuid((String) plist.get("UUID"));
        provisionVo.setTeamIdentifier((List<String>) plist.get("TeamIdentifier"));
        provisionVo.setTeamId(String.join(";", provisionVo.getTeamIdentifier()));
        provisionVo.setProvisionDevices((List<String>) plist.get("ProvisionedDevices"));
        provisionVo.setEntitlements((Map<String, Object>) plist.get("Entitlements"));
        if (provisionVo.getEntitlements() != null &&
                provisionVo.getEntitlements().containsKey("application-identifier")) {
            var identifier = (String) provisionVo.getEntitlements().get("application-identifier");
            var teamIdPrefix = provisionVo.getTeamId() + ".";
            var bundleId = identifier.replace(teamIdPrefix, "");
            provisionVo.setBundleId(bundleId);
        } else if (provisionVo.getEntitlements() != null &&
                provisionVo.getEntitlements().containsKey("com.apple.application-identifier")) {
            var identifier = (String) provisionVo.getEntitlements().get("com.apple.application-identifier");
            var teamIdPrefix = provisionVo.getTeamId() + ".";
            var bundleId = identifier.replace(teamIdPrefix, "");
            provisionVo.setBundleId(bundleId);
        }

        // 输出 JSON 数据到标准输出。
        System.out.println(new Gson().toJson(provisionVo));

        return 0;
    }
}
