# 株式会社ゆめみ Android エンジニアコードチェック課題

## 概要

本プロジェクトは株式会社ゆめみ（以下弊社）が、弊社に Android エンジニアを希望する方に出す課題のベースプロジェクトです。本課題が与えられた方は、下記の概要を詳しく読んだ上で課題を取り組んでください。

## アプリ仕様

本アプリは GitHub のリポジトリやユーザーを検索するアプリです。

<img src="docs/app.gif" width="320">

### 環境

- IDE：Android Studio Electric Eel | 2022.1.1 Patch 1
- Kotlin：1.5.31
- Java：11
- Gradle：7.0.2
- minSdk：23
- targetSdk：31

### 動作
リポジトリ検索機能

1. 何かしらのキーワードを入力
2. GitHub API（`search/repositories`）でリポジトリを検索し、結果一覧を概要（リポジトリ名）で表示
3. 特定の結果を選択したら、該当リポジトリの詳細（リポジトリ名、オーナーアイコン、プロジェクト言語、Star 数、Watcher 数、Fork 数、Issue 数）を表示

ユーザー名検索機能
1. 何かしらのキーワードを入力
2. GitHub API（`search/users`）でユーザーを検索し、結果一覧を概要（ユーザー名）で表示
3. 特定の結果を選択したら、該当ユーザーの詳細（ユーザー名、オーナーアイコン、リポジトリ数、Follower 数、Following 数）GitHub API（`users`）で検索し表示

## コミットのメッセージ
コミットメッセージには最初にprefixを書いています。以下はprefix一覧です。
* add: 新規ファイル追加
* remove：ファイル削除
* update：機能修正
* feat: 新しい機能
* fix: バグの修正
* docs: ドキュメントのみの変更
* style: 空白、フォーマット、セミコロン追加など
* refactor: 仕様に影響がないコード改善(リファクタ)
* perf: パフォーマンス向上関連
* test: テスト関連
* chore: ビルド、補助ツール、ライブラリ関連
