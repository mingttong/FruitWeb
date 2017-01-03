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

function deleteItem(anchor) {

    var goods_id = anchor.getAttribute("data-sku");
    var url = "deleteItem.do?goodsID=" + goods_id;

    ajaxUpdateItem(url);
}

function increaseItem(anchor) {

    var goods_id = anchor.getAttribute("data-sku");
    var url = "increaseItem.do?goodsID=" + goods_id;

    var itemObj = getItemObj(goods_id);

    var httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = (function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            // 加载成功后
            console.log(httpRequest.responseText);
        }
    });

    httpRequest.open("GET", url, true);
    httpRequest.send();

    //ajaxUpdateItem(url);
}

function decreaseItem(anchor) {

    var goods_id = anchor.getAttribute("data-sku");
    var url = "decreaseItem.do?goodsID=" + goods_id;

    var itemObj = getItemObj(goods_id);

    if (itemObj.getNum() > 1) {

        ajaxUpdateItem(url);

    } else {
        // 调整样式
    }
}

function ajaxUpdateItem(url) {

    var httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = (function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            // 加载成功后
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
        }

    };
}