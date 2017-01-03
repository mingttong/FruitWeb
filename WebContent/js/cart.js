/**
 * Created by lenovo on 2017/1/2.
 */

function submitPost(url, dataToPost) {

    // 生成虚拟表单
    var tmp_form = document.createElement("form");
    tmp_form.action = url;
    tmp_form.method = "post";
    tmp_form.style.display = "none";

    for (var dataName in dataToPost) {

        // 避免搜索到原型链上的属性和方法
        if (dataToPost.hasOwnProperty(dataName)) {
            var option = document.createElement("textarea");
            option.name = dataName;
            option.value = dataToPost[dataName];

            tmp_form.appendChild(option);
        }
    }

    document.body.appendChild(tmp_form);
    tmp_form.submit();

    return tmp_form;
}

/**
 * 删除商品
 * @param anchor “删除商品按钮”的元素
 */
function deleteItem(anchor) {

    var goods_id = anchor.getAttribute("data-sku");
    var url = "deleteItem.do?goodsID=" + goods_id;

    ajaxUpdateItem(goods_id, url);
}

/**
 * 增加商品数量
 * @param anchor “增加数量按钮”的元素
 */
function increaseItem(anchor) {

    var goods_id = anchor.getAttribute("data-sku");
    var url = "increaseItem.do?goodsID=" + goods_id;

    ajaxUpdateItem(goods_id, url);
}

/**
 * 减少商品数量
 * @param anchor “减少数量按钮”的元素
 */
function decreaseItem(anchor) {

    var goods_id = anchor.getAttribute("data-sku");
    var url = "decreaseItem.do?goodsID=" + goods_id;

    var itemObj = getItemObj(goods_id);

    if (typeof itemObj.getNum() === "number" && itemObj.getNum() > 1) {

        ajaxUpdateItem(goods_id, url);

    } else {
        // 调整样式
    }
}

/**
 * 异步更新商品信息
 * @param id
 * @param url
 */
function ajaxUpdateItem(id, url) {

    // 获取商品对象
    var itemObj = getItemObj(id);

    var httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = (function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            // 加载成功后

            // 获取服务器返回的数据，并截取其中的值
            var resArr = httpRequest.responseText.split(",");
            var num = parseInt(resArr[0]), sum = parseInt(resArr[1]);

            // 如果数量和总价都为0的话则表示删除该商品
            if (num === 0 && sum === 0) {

                // 删除该商品
                var itemElement = itemObj.getItemElement();
                itemElement.parentNode.removeChild(itemElement);

            } else {

                // 更改商品信息
                itemObj.setNum(num);
                itemObj.setSum(sum);

            }
        }
    });

    httpRequest.open("GET", url, true);
    httpRequest.send();
}

/**
 * 获取商品对象
 * @param id
 * @returns {{getNum: Function, getSum: Function, setNum: Function, setSum: Function}}
 */
function getItemObj(id) {

    var item = document.getElementById(id);

    if (item) {
        var numElement = item.querySelector(".p-quantity input");
        var sumElement = item.querySelector(".p-sum strong");
        var num = parseInt(numElement.getAttribute("value"));
        var sum = parseInt(sumElement.textContent);
    }

    return {
        "getNum" : function () {
            return num;
        },
        "getSum" : function () {
            return sum;
        },
        "setNum" : function (num) {
            numElement.setAttribute("value", num);
        },
        "setSum" : function (sum) {
            sumElement.textContent = sum;
        },
        "getItemElement" : function () {
            return item;
        }
    };
}