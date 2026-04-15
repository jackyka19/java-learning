# Java Spring Boot 學習練習專案：圖書館管理系統

這個專案紀錄了我從零開始學習 Java 後端開發的歷程。目前主要實作了使用者管理系統（註冊與登入邏輯），並練習了 Spring Boot 與 MySQL 資料庫的串接。

## 📚 參考資料
本專案的學習路徑參考自以下優質教學資源：
* **[小八 Java 學習歷程](https://hackmd.io/@learning-official/Java_learning)**
* **[古古的後端筆記 - Spring Boot 零基礎入門](https://kucw.io/categories/spring-boot/)**

特別感謝以上作者提供的教學，讓我能順利跨入 Spring Boot 的大門。

---

## 🛠️ 目前已實現功能
### 1. 使用者系統 (User System)
* **使用者註冊 (`/user/register`)**：
    * 檢查帳號是否重複註冊。
    * 實作自動遞增 ID (`AUTO_INCREMENT`)。
* **使用者登入 (`/user/login`)**：
    * 帳號密碼驗證邏輯。
    * 整合 **JWT (JSON Web Token)** 發放機制 (開發中)。

## 💻 使用技術
* **語言**：Java 17 / 21
* **框架**：Spring Boot 3.x
* **資料庫**：MySQL 8.0
* **資料庫工具**：Spring JDBC Template
* **開發環境**：IntelliJ IDEA, Maven

---

## 🚀 未來開發計畫
- [ ] 完善 JWT 攔截器 (Interceptor) 驗證。
- [ ] 實作書籍管理功能 (CRUD)。
- [ ] 加入參數效驗 (Validation)。
- [ ] 密碼加密處理 (BCrypt)。

## 📝 筆記與心得
在開發過程中遇到的挑戰：
* **JSON 命名規範**：學習了 Java 駝峰式 (Camel Case) 與 JSON 底線式 (Snake Case) 的對應問題。
* **Git 版本控制**：練習了使用 Personal Access Token (PAT) 進行安全提交。