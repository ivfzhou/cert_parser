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
import java.util.List;
import java.util.Map;

@Data
public class ProvisionVo {

    private Long expirationDate;

    private Long creationDate;

    private String name;

    private String teamName;

    private String teamId;

    private String uuid;

    private List<String> teamIdentifier;

    private Map<String, Object> entitlements;

    private List<String> provisionDevices;

    private String bundleId;

    private String text;
}
