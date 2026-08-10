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

import lombok.Data;

@Data
public class CertificateVo {

    // 私钥名称。
    private String privateKeyName;

    // 证书SHA1指纹。
    private String SHA1;

    // 证书过期时间（毫秒时间戳）。
    private Long expirationDate;

    // 证书创建时间（毫秒时间戳）。
    private Long creationDate;

    // 应用 Bundle ID。
    private String bundleId;

    // 团队 ID。
    private String teamId;

    // 团队名称。
    private String teamName;

    // 证书名称。
    private String cerName;
}
