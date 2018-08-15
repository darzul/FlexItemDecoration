FlexItemDecoration
========

Create your RecyclerView.ItemDecoration easily

![](http://i.makeagif.com/media/3-25-2017/h7o0yX.gif)

Basic usage
========

```java 
RecyclerView.ItemDecoration itemDecoration = new BaseItemDecoration.Builder(context)
                .setDecorationSize(R.dimen.pokemons_item_space)
                .setDecorationColor(R.color.colorAccent)
                .build();
mRecyclerView.addItemDecoration(itemDecoration);          
```

Features
========

```java 
new BaseItemDecoration.Builder(context)
                .setNumberItemPerLine(4) // gridLayout management
                .setIsHorizontal(true) // orientation
                .setDecorationSize(R.dimen.pokemons_item_space) // decoration size
                .setDecorationColor(R.color.colorAccent) // decoration color
                .showDecorationAtBeginning(true) // show decoration above the first list item
                .showDecorationAtEnd(true) // show decoration below the last list item
                .setPaddingTop(R.dimen.padding_top) // add scrollable paddingTop above the first list item
                .setPaddingBottom(R.dimen.padding_bottom) // add scrollable paddingTop below the last list item
                .build();         
```

Download
=======

Gradle dependency:
```groovy
compile 'com.darzul.flexitemdecoration:flexitemdecoration:1.0.1'
```

License
=======

    Copyright 2017 Guillaume 'DarzuL' Bourderye

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


