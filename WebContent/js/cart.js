/**
 * Created by lenovo on 2017/1/2.
 */

function editItem(inputBtn) {

    var goods_id = inputBtn.getAttribute("data-sku");
    var num = 0;

    var url = "editItem.do?goodsID=" + goods_id + "&num=" + num;

    ajaxUpdateItem(goods_id, url);
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

    // 获取更新前的商品对象
    var itemObj = getItemObj(goods_id);

    if (itemObj.getNum() === 1) {
        // 增加前数量为1，则改变其样式

        // 获取减少数量按钮并改变其样式
        var decreaseBtn = itemObj.getItemElement().querySelector(".decrement");

        if (decreaseBtn.classList) {
            decreaseBtn.classList.remove("disabled");
        } else {

            // 通过改变字符串改变类名
            // 懒得处理可能出现的多余空格
            var newClass = decreaseBtn.getAttribute("class").replace("disabled", "");
            decreaseBtn.setAttribute("class", newClass);

        }

        // 加回decreaseItem()事件
        decreaseBtn.setAttribute("onclick", "decreaseItem(this)");
    }

    ajaxUpdateItem(goods_id, url);
}

/**
 * 减少商品数量
 * @param anchor “减少数量按钮”的元素
 */
function decreaseItem(anchor) {

    var goods_id = anchor.getAttribute("data-sku");
    var url = "decreaseItem.do?goodsID=" + goods_id;

    // 获取同步通讯后的结果
    var itemObj = ajaxUpdateItem(goods_id, url);

    if (itemObj.getNum() === 1) {
        // 减少后数量为1，则改变其样式

        // 获取按钮元素
        var decreaseBtn = itemObj.getItemElement().querySelector(".decrement");

        // 添加样式
        if (decreaseBtn.classList) {
            decreaseBtn.classList.add("disabled");
        } else {
            // 通过改变字符串改变类名
            // 懒得处理可能出现的多余空格
            var newClass = decreaseBtn.getAttribute("class").concat(" disabled");
            decreaseBtn.setAttribute("class", newClass);
        }

        // 取消掉decreaseItem()事件
        decreaseBtn.removeAttribute("onclick");
    }

}

/**
 * 同步更新商品信息
 * @param id 商品id
 * @param url 通讯的url
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

    // 被逼无奈用同步的方式，不然没其他办法。
    // 再次获取节点元素也得等通讯完了才能获取，不然获取的还是老的值。
    httpRequest.open("GET", url, false);
    httpRequest.send();

    return itemObj;
}

/**
 * 获取商品对象
 * @param id
 * @returns {{num: Number, sum: Number, getNum: Function, getSum: Function, setNum: Function, setSum: Function, getItemElement: Function}}
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
        "num" : num,
        "sum" : sum,
        "getNum" : function () {
            return this.num;
        },
        "getSum" : function () {
            return this.sum;
        },
        "setNum" : function (num) {
            numElement.setAttribute("value", num);
            this.num = num;
        },
        "setSum" : function (sum) {
            sumElement.textContent = sum;
            this.sum = sum;
        },
        "getItemElement" : function () {
            return item;
        }
    };
}

window.onload = function () {

    // 给输入框添加编辑事件
    var inputElements = document.querySelectorAll(".p-quantity input");

    for (var i = 0; i < inputElements.length; i++) {

        inputElements[i].addEventListener("change", function () {

            editItem(this);
        });
    }

};