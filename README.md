# kakebo-app

## 概要
Spring Bootで開発した家計簿アプリです。  
ユーザー認証（JWT）を実装し、収入・支出の管理、月別集計、グラフ表示が可能です。

バックエンドではREST APIを意識した設計を行い、  
例外処理・バリデーション・セキュリティ面も考慮して実装しています。

---

## 制作目的
単なるCRUDアプリではなく、実務を意識した以下の要素を取り入れることを目的に開発しました。

- 認証機能（JWT）の実装
- APIベースの設計
- 入力バリデーション
- 例外処理の共通化
- 機密情報の分離管理
- Git / GitHubによるバージョン管理

---

## 主な機能

### 🔐 認証機能
- ユーザー登録
- ログイン
- JWTによる認証
- 認証済みユーザーのみデータ操作可能

### 💰 家計簿機能
- 収入登録
- 収入編集
- 収入削除
- 支出登録
- 支出編集
- 支出削除
- 月別集計
- サマリー表示
- グラフ表示
---

## 使用技術

### バックエンド
- Java
- Spring Boot
- MySQL
- JWT

### フロントエンド
- HTML
- CSS
- JavaScript

### その他
- Maven
- Git / GitHub

---

## 技術的な工夫

### 1. JWT認証
ログイン時にトークンを発行し、認証が必要なAPIではトークンを用いてアクセス制御を行っています。

### 2. 例外処理の共通化
`GlobalExceptionHandler` を実装し、エラーレスポンスを統一しました。

### 3. DTOバリデーション
`@NotBlank` や `@Positive` を使用し、不正な入力を防止しています。

### 4. セキュリティ対策
- DB接続情報・JWT secretを外部ファイルに分離
- `.gitignore` により機密情報を非公開化

---

## ディレクトリ構成
src
└ main
├ java/com/example/kakebo
│  ├ controller
│  ├ dto
│  ├ exception
│  ├ repository
│  ├ service
│  └ util
└ resources
├ templates
├ static
├ application.properties
└ application-local.properties

---

## API例

### 認証
- POST `/api/auth/register`
- POST `/api/auth/login`

### 収入
- GET `/api/incomes`
- POST `/api/incomes`
- DELETE `/api/incomes/{id}`
- PUT `/api/incomes/{id}`

### 支出
- GET `/api/expenses`
- POST `/api/expenses`
- DELETE `/api/expenses/{id}`
- PUT `/api/expenses/{id}`

### 集計
- GET `/api/summary/monthly`
- GET `/api/summary/trend`

---

## 起動方法

### 1. リポジトリをクローン

```bash
git clone https://github.com/kyokami-prog/kakebo-app.git
cd kakebo
```
### 2. データベース作成

MySQLで以下を実行してください。
```sql
CREATE DATABASE kakebo;
```

### 3. テーブル作成

以下のSQLファイルを実行してください。
	•	users.sql
	•	incomes.sql
	•	expenses.sql

### 4. ローカル設定ファイル作成

src/main/resources/application-local.properties を作成してください。

spring.datasource.url=jdbc:mysql://localhost:3306/kakebo
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_real_secret_key

### 5. アプリ起動
./mvnw spring-boot:run

## 今後の改善点
	•	UI/UX改善
	•	CSS / JavaScriptの分離
	•	テストコードの追加
	•	デプロイ対応

## 補足

本リポジトリには機密情報を含めていません。
DB接続情報やJWT secretはローカル設定ファイルで管理しています。

