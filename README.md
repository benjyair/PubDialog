# PubDialog
A common list dialog, use simple, the function is powerful, can be customized and strong, like UIActionSheet in IOS 

![PubDialog](https://github.com/KokerWang/PubDialog/blob/master/show_demo.gif)

[Download Demo](https://github.com/KokerWang/PubDialog/blob/master/apk/PubDialogExample-debug.apk?raw=true)

##Use

```java

//Initialize an array of characters
List<String> list = new ArrayList<>(3);
list.add("Send message");
list.add("Like profile");
list.add("Add to favorites");

//Initialize PubDialogFragment
PubDialogFragment pubDialog = PubDialogFragment.newInstance(list, false);

//Callback (can also be not provided)
pubDialog.setItemClickListener(new PubDialogFragment.ItemClickListener() {

    @Override
    public void onItemClick(View clickedView, DialogObject dialogObject,
                            int groupIndex, int itemIndex) {
        //Handle events in the callback
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

//In the use of the local
pubDialog.show(getSupportFragmentManager(), "setting");

```
## Function Customization
* The font color
* Background
* Icon
* Multi packet

[More](http://www.kokerwang.com/2015/02/27/PubDialog%20----%20%E4%B8%80%E4%B8%AA%E7%AE%80%E5%8D%95%E7%9A%84%E6%8F%90%E7%A4%BA%E5%AF%B9%E8%AF%9D%E6%A1%86.html)

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
