# session-sample

SessionAttributeあたりをガチャガチャやるもの

## リポジトリの概要

各URLで Spring Framework の`@SessionAttributes`あたりを試すもの。
`@ModelAttribute`や、逆に使わないなど、色々なものを特定のURLで試す。

## 使い方

git clone で手元に用意したリポジトリのsession-sampleディレクトリを、
IDEのEclipseに Mavenの既存プロジェクトのインポート で導入すれば、類似環境であればどこでも利用可能になるはず。

## 動作確認している環境

* Windows 10 の Eclipse
* Java 1.8
* Maven 3.6.3
* Tomcat 9.0.39。EclipseのWTP(WebToolsPlatform)で使用。
* Spring Framework 5.2系。ただし厳密には違う。

## どんな動きをするのか

WTPでプロジェクトを起動すると、プロジェクトのコンテキストルートである

http://localhost:8080/session-sample/

にアクセスすると、試したい各Contorollerへの振り分けリンクがある。
そこから遷移することで試せる。

## 自分への注意

動かないものでもわざと「動かない実装記録」としてコミットしてるのあるから注意すること。