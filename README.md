# kakebo-app

## 概要
Spring Bootで作成した家計簿アプリです。  
ユーザー登録、ログイン、収入・支出管理、月別集計に対応しています。

## 使用技術
- Java
- Spring Boot
- MySQL
- JWT
- HTML
- CSS
- JavaScript

## 主な機能
- ユーザー登録
- ログイン / ログアウト
- 収入登録
- 収入更新
- 支出登録
- 支出更新
- 月別集計
- グラフ表示

## 工夫した点
- JWTを用いた認証機能を実装
- SecretKeyやDB接続情報を設定ファイルで分離
- 例外処理をGlobalExceptionHandlerで共通化
- DTOによるバリデーションを実装

## 起動方法
1. MySQLでデータベースを作成
2. `application.properties` に公開用設定を記述
3. `application-local.properties` にローカル用の機密情報を記述
4. Spring Bootを起動

## 補足
機密情報はGitHubに含めていません。  
DB接続情報やJWT secretはローカル設定で管理しています。