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

package csms.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CertificateVo {

    private String privateKeyName;

    private String SHA1;

    private Date expirationDate;

    private Date creatationDate;

    private String bundleId;

    private String teamId;

    private String teamName;

    private String cerName;
}
