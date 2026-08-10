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

module cn.ivfzhou.cert.parser {

    // Spring Boot 核心。
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires jakarta.annotation;

    // 命令行解析。
    requires info.picocli;
    requires info.picocli.spring.boot;

    // plist XML 解析。
    requires xmlwise;

    // JSON 序列化。
    requires com.google.gson;

    // 编译期代码生成。
    requires static lombok;

    // Spring 组件扫描与依赖注入需要反射访问。
    opens cn.ivfzhou.cert_parser to spring.core, spring.beans, spring.context;

    // picocli 需要反射访问 @Command/@Option 字段，Spring 需要实例化 @Component。
    opens cn.ivfzhou.cert_parser.cmd to spring.core, spring.beans, spring.context, info.picocli;

    // Gson 序列化 VO 需要反射访问字段。
    opens cn.ivfzhou.cert_parser.vo to com.google.gson;
}
