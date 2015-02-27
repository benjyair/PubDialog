# PubDialog
一款常用的列表对话框，使用简单，功能强大，可定制性强

![PubDialog](https://github.com/KokerWang/PubDialog/blob/master/show_demo.gif)

[下载demo](https://github.com/KokerWang/PubDialog/blob/master/apk/PubDialogExample-debug.apk)

##使用

```java

	//初始化一个字符数组
	List<String> list1 = new ArrayList<>(3);
    list1.add("Send message");
    list1.add("Like profile");
    list1.add("Add to favorites");

    //初始化PubDialogFragment
    PubDialogFragment pubDialogFragment = PubDialogFragment.newInstance(list1, false);

    //设置回调(也可以不设置)
    pubDialogFragment.setItemClickListener(new PubDialogFragment.ItemClickListener() {

                @Override
                public void onItemClick(View clickedView, DialogObject dialogObject, int groupIndex, int itemIndex) {
                	//在回调中处理事件
                    Intent intent;
                    if (itemIndex == 1) {
                        Uri uri = Uri.parse("https://github.com/KokerWang/PubDialog");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                    } else {
                        Uri uri = Uri.parse("http://www.kokerwang.com");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                    }
                    startActivity(intent);
                }
            });

    //在使用的地方
    pubDialogFragment.show(getSupportFragmentManager(), "setting");

```
##功能
* 字体颜色
* 背景
* icon
* 多分组

License
============

    Copyright 2015 KokerWang

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
