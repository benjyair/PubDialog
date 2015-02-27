# PubDialog
A common list dialog, use simple, the function is powerful, can be customized and strong

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
    PubDialogFragment pubDialogFragment = PubDialogFragment.newInstance(list, false);

    //Callback (can also be not provided)
    pubDialogFragment.setItemClickListener(new PubDialogFragment.ItemClickListener() {

                @Override
                public void onItemClick(View clickedView, DialogObject dialogObject, int groupIndex, int itemIndex) {
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
    pubDialogFragment.show(getSupportFragmentManager(), "setting");

```
## Function Customization
* The font color
* Background
* Icon
* Multi packet

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
