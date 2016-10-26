# アプリ説明

アプリからスマホに保存されている画像を選択する処理のサンプル アプリです。
KitKat (ver. 19) 前後で推奨される方法が異なっていますが、その両方を試すことができるように作ってあります。

# KitKat 前後の差異

KitKat 以降で推奨される方法は、ACTION_OPEN_DOCUMENT で Intent を送信する方法です。
KitKat 以前は、ACTION_PICK で Intent を送信します。

ACTION_OPEN_DOCUMENT を用いた場合、Storage Access Framework に従い、画像選択たのための専用アプリが起動します。
ACTION_PICK を用いた場合、Intent で指定した MIME タイプに対応するアプリの一覧が表示され、選択したアプリからファイルを選択する形式となります。
