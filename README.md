# 【Android】mBaaSにアップロードした画像をアプリに表示しよう！ for Kolin
![画像1](/readme-img/001.png)

## 概要
* [ニフクラ mobile backend](https://mbaas.nifcloud.com/)(通称mBaaS)の『ファイルストア機能』を利用して、アップロードした画像をアプリ側で表示するサンプルプロジェクトです
* 簡単な操作ですぐに [ニフクラ mobile backend](https://mbaas.nifcloud.com/) の機能を体験いただけます

## ニフクラ mobile backendって何？？
スマートフォンアプリのバックエンド機能（プッシュ通知・データストア・会員管理・ファイルストア・SNS連携・位置情報検索・スクリプト）が**開発不要**、しかも基本**無料**(注1)で使えるクラウドサービスです。

注1：詳しくは[こちら](https://mbaas.nifcloud.com/price.htm)をご覧ください

![画像2](/readme-img/002.png)

## 動作環境

* MacOS Monterey version 12.5
* Android Studio Chipmunk | 2021.2.1 Patch 2
* Pixel 2 - Android 13 (Simulator)
* KotlinSDK v1.2.0

※上記内容で動作確認をしています。

## 手順
### 1. ニフクラ mobile backend の会員登録・ログインとアプリの新規作成
* 下記リンクから会員登録（無料）をします
  * https://console.mbaas.nifcloud.com/signup
* 登録ができたら下記リンクからログインします
  * https://console.mbaas.nifcloud.com/
* 下図のように「アプリの新規作成」画面が出るのでアプリを作成します
  * 既に mobile backend を利用したことがある方は左上の「＋新しいアプリ」をクリックすると同じ画面が表示されます

![画像3](/readme-img/003.png)

* アプリ作成されるとAPIキー（アプリケーションキーとクライアントキー）が発行されます
* この２種類のAPIキーはこの後ダウンロードするサンプルアプリと ニフクラ mobile backend を紐付けるため、あとで使います。

![画像4](/readme-img/004.png)

### 2. サンプルプロジェクトのダウンロード
* 下記リンクをクリックしてプロジェクトをダウンロードします
 * https://github.com/NiFCloud-mbaas/KotlinImageViewApp/archive/master.zip
* ダウンロードしたプロジェクトを解凍します
  - の中にはプロジェクトと別に、「setting」フォルダが入っています。この中にある画像を後ほどアップロードして使用します
* AndroidStudio を開きます、「Open an existing Android Studio projct」をクリックして解凍したプロジェクトを選択します

![画像8-1](/readme-img/android_studio.png)

* 選択したプロジェクトが開かれます

![画像9](/readme-img/009.png)

### 3. SDKの導入（実装済み）

※このサンプルアプリには既にSDKが実装済み（下記手順）となっています。（ver.1.1.0)<br>　最新版をご利用の場合は入れ替えてご利用ください。

* SDKダウンロード
SDKはここ（[SDK リリースページ](https://github.com/NIFCLOUD-mbaas/ncmb_kotlin/releases)）から取得してください.
  - NCMB.jarファイルがダウンロードします。
* SDKをインポート
  - app/libsフォルダにNCMB.jarをコピーします
* 設定追加
  - app/build.gradleファイルに以下を追加します
```gradle
dependencies {
    implementation 'com.squareup.okhttp3:okhttp:4.8.1'
    implementation 'com.google.code.gson:gson:2.3.1'
    api files('libs/NCMB.jar')

    //同期処理を使用する場合はこちらを追加していただく必要があります
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9'
}
```
  - androidManifestの設定
    - <application>タグの直前に以下のpermissionを追加します
```html
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### 4. APIキーの設定
* AndroidStudio で MainActivity.kt を開きます
  * ディレクトリはデフォルトで「Android」が選択されていますので、「Project」に切り替えてから探してください

![画像16](/readme-img/016.png)

* APIキー（アプリケーションキーとクライアントキー）の設定をします

![画像07](/readme-img/007.png)

* それぞれ `YOUR_APPLICATION_KEY` と `YOUR_CLIENT_KEY` の部分を書き換えます
 * このとき、ダブルクォーテーション（`"`）を消さないように注意してください
* 書き換え終わったら保存してください
 * Windowsの場合、Ctrl + Sで保存できます。
 * Macの場合、command + Sで保存できます。

### 5. 画像ファイルのアップロード
* [ニフクラ mobile backend](https://mbaas.nifcloud.com/) の管理画面で、「ファイルストア」を開きます
* 「↑アップロード」をクリックします

![画像12](/readme-img/012.png)

* 画像を選択します
 * ここでアップロードする画像はダウンロードしたプロジェクトにある「setting」フォルダ内の「__mBaaS_image.png__」ファイルです。

![画像15](/readme-img/015.png)

* 選択したら「アップロードする」をクリックします

![画像13](/readme-img/013.png)

* 画像がアップロードされました

![画像14](/readme-img/014.png)

### 6. 動作確認
* エミュレーターでアプリをビルドします
 * 失敗する場合は一度「Clean Project」を実行してから再度ビルドしてください
* アプリを起動したら、真ん中の「SHOW」ボタンをクリックします

![画像10](/readme-img/010.png)

* 先ほど ニフクラ mobile backend にアップロードした画像がダウンロードされアプリに表示されます

![画像11](/readme-img/011.png)

#### 画像が表示されない場合
* ネットワークを確認してください
* エラー時はアラートでエラーコードを表示しています
 * エラーコードが表示されている場合は[こちら](https://mbaas.nifcloud.com/doc/current/rest/common/error.html#REST%20API%E3%81%AE%E3%82%A8%E3%83%A9%E3%83%BC%E3%82%B3%E3%83%BC%E3%83%89%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6)を確認してください

## 解説
ここではサンプルアプリに実装済みの内容について紹介します

### SDKのインポートと初期設定
* ニフクラ mobile backend のドキュメント（クイックスタート）をご用意していますので、ご活用ください
 * [Androidのクイックスタート](https://mbaas.nifcloud.com/doc/current/introduction/quickstart_kotlin.html)

### ロジック
* `activity_main.xml`でデザインを作成し、`MainActivity.kt`にロジックを書いています

#### アップロードした画像ファイルのダウンロード
```kotlin
//**************** APIキーの設定 **************
NCMB.initialize(
    this,
    "YOUR_APPLICATION_KEY",
    "YOUR_CLIENT_KEY"
)

setContentView(R.layout.activity_main)

_btnShow = findViewById<View>(R.id.btnShow) as Button
_iv = findViewById<View>(R.id.imgShow) as ImageView
_btnShow.setOnClickListener {
    // 画像ダウンロードする
    val file = NCMBFile("mBaaS_image.png")
    file.fetchInBackground(NCMBCallback { e, ncmbFile ->
        runOnUiThread {
            if (e != null) {
                //失敗処理
                AlertDialog.Builder(this)
                    .setTitle("Notification from NIFCloud")
                    .setMessage("Error:" + e.message)
                    .setPositiveButton("OK", null)
                    .show()
            } else {
                val fileObj = ncmbFile as NCMBFile
                //成功処理
                val bMap = BitmapFactory.decodeByteArray(
                    fileObj.fileDownloadByte,
                    0,
                    fileObj.fileDownloadByte!!.size
                )
                _iv.setImageBitmap(bMap)
            }
        }
    })

}
```
