# 一、说明

解析 Apple 开发者证书（`.p12`）和描述文件（`.mobileprovision`），以 JSON 格式输出关键信息。


# 二、功能特性

- **证书解析**：提取 `.p12` 文件的 SHA-1 指纹、有效期、Bundle ID、团队信息等字段
- **描述文件解析**：解析 `.mobileprovision` 的 Entitlements、设备列表、UUID 等完整信息
- **JSON 输出**：标准输出结构化 JSON，便于脚本集成和二次处理

# 三、环境要求

- **JDK** >= 22
- **Maven**（构建使用）

# 四、构建

```bash
mvn clean package
```

产物位于 `target/cert_parser-v0.1.0.jar`。

# 五、用法

## 5.1 解析证书

```bash
java -jar cert_parser-v0.1.0.jar certificate -f <证书.p12> [-p <密码>]
```

| 参数 | 必填 | 说明 |
|------|------|------|
| `-f` | 是 | `.p12` 证书文件路径 |
| `-p`  | 否   | 证书密码 |

**输出示例：**

```json
{
  "privateKeyName": "...",
  "SHA1": "A1B2C3D4...",
  "expirationDate": 1740000000000,
  "creatationDate": 1709000000000,
  "bundleId": "com.example.app",
  "teamId": "TEAMID",
  "teamName": "Team Name",
  "cerName": "Apple Development"
}
```

## 5.2 解析描述文件

```bash
java -jar cert_parser-v0.1.0.jar provision -f <描述文件.mobileprovision>
```

| 参数 | 必填 | 说明 |
|------|------|------|
| `-f` | 是 | `.mobileprovision` 文件路径 |

**输出示例：**

```json
{
  "expirationDate": 1740000000000,
  "creationDate": 1709000000000,
  "name": "My App Provision",
  "teamName": "Team Name",
  "teamId": "TEAMID",
  "uuid": "xxxx-xxxx-xxxx",
  "teamIdentifier": ["TEAMID"],
  "entitlements": { ... },
  "provisionDevices": ["device1", "..."],
  "bundleId": "com.example.app"
}
```

# 六、技术栈

| 技术           | 版本    | 用途            |
|----------------|---------|----------------|
| Spring Boot     | 3.2.4   | 应用框架        |
| picocli        | 4.7.5   | CLI 命令行      |
| xmlwise         | 1.2.11  | Plist XML 解析  |
| Gson           | 2.10.1  | JSON 序列化     |
| Lombok         | 1.18.32 | 简化 Java 代码  |
