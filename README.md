# MVC-FundQuery


**FundPortal** is a lightweight, Jakarta EE-based web application designed for housing fund inquiry and management. It follows the classic MVC (Model-View-Controller) architecture and provides a secure, efficient way for users to check their balances and manage their accounts.

[中文文档](./README.md#中文说明)

---

## Features

- **Dual Login Methods**: Authenticate using either your ID Card number or Fund Account number.
- **Balance Inquiry**: View real-time account balance and personal details.
- **Password Management**: Securely update account passwords with old password verification.
- **Centralized Configuration**: Database settings are managed via a single properties file.
- **Resource Management**: Optimized JDBC connection handling using `DBUtils` and try-with-resources.
- **CI/CD Integrated**: Automated build and release workflows using GitHub Actions.

## Tech Stack

- **Backend**: Java 11+, Jakarta Servlet 5.0
- **Database**: MySQL 8.0+
- **ORM/Data Access**: JDBC (with MyBatis configuration support)
- **Frontend**: JSP, CSS
- **Build Tool**: Maven
- **CI/CD**: GitHub Actions

## Project Structure

```text
src/main/java/com/qqhru/exam2/
├── controller/    # Jakarta Servlets (Web Layer)
├── Dao/           # Data Access Objects (JDBC Layer)
├── model/         # POJO Classes (Model Layer)
├── service/       # Business Logic Layer
└── utils/         # Utility classes (DBUtils, etc.)

src/main/resources/
├── db.properties       # Database configuration
└── mybatis-config.xml  # MyBatis settings

src/main/webapp/
├── WEB-INF/       # Web configuration
└── *.jsp          # View templates
```

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven 3.6+
- MySQL 8.0+
- A Jakarta EE 9+ compatible container (e.g., Tomcat 10+)

### Configuration

1. Create a database named `test_db` in MySQL.
2. Configure your database credentials in `src/main/resources/db.properties`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/test_db
   db.username=your_username
   db.password=your_password
   ```

### Build

Run the following command to package the project into a WAR file:
```bash
mvn clean package
```
The output file will be located at `target/exam2-1.0-SNAPSHOT.war`.

### Deployment

Copy the generated `.war` file to the `webapps` directory of your Tomcat server.

---

## 中文说明

**FundPortal** 是一个基于 Jakarta EE 的轻量级公积金查询与管理系统。

### 核心功能
- **双重登录**：支持身份证号或公积金账号登录。
- **余额查询**：实时查看账户余额及个人信息。
- **密码管理**：支持旧密码验证下的安全密码修改。
- **集中配置**：数据库连接信息统一管理。
- **自动化运维**：集成 GitHub Actions 自动构建与发布。

### 技术栈
- **后端**：Java 11+, Jakarta Servlet 5.0
- **数据库**：MySQL 8.0+
- **前端**：JSP, CSS
- **构建工具**：Maven

### 快速开始
1. 在 MySQL 中创建 `test_db` 数据库。
2. 修改 `src/main/resources/db.properties` 中的数据库连接信息。
3. 运行 `mvn clean package` 进行打包。
4. 将生成的 `war` 包部署到 Tomcat 10+ 容器中。
