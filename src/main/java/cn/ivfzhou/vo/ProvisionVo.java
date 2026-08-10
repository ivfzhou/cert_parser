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

package cn.ivfzhou.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ProvisionVo {

    // 过期时间（毫秒时间戳）。
    private Long expirationDate;

    // 创建时间（毫秒时间戳）。
    private Long creationDate;

    // 描述文件名称。
    private String name;

    // 团队名称。
    private String teamName;

    // 团队 ID。
    private String teamId;

    // 描述文件UUID。
    private String uuid;

    // 团队标识符列表。
    private List<String> teamIdentifier;

    // 权限配置。
    private Map<String, Object> entitlements;

    // 已注册设备列表。
    private List<String> provisionDevices;

    // 应用 Bundle ID。
    private String bundleId;

    // 描述文件原始文本内容。
    private String text;
}
