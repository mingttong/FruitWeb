/**
 * Created by lenovo on 2017/1/2.
 */

//function addCart(btn) {
//
//    var goods_id = btn.getAttribute("data-sku"); // 商品ID
//    var url = "addCart.do";
//    var dataToPost = {
//        goodsID : goods_id
//    };
//
//    // 生成虚拟表单
//    var tmp_form = document.createElement("form");
//    tmp_form.action = url;
//    tmp_form.method = "post";
//    tmp_form.style.display = "none";
//
//    for (var dataName in dataToPost) {
//
//        // 避免搜索到原型链上的属性和方法
//        if (dataToPost.hasOwnProperty(dataName)) {
//            var option = document.createElement("textarea");
//            option.name = dataName;
//            option.value = dataToPost[dataName];
//
//            tmp_form.appendChild(option);
//        }
//    }
//
//    document.body.appendChild(tmp_form);
//    tmp_form.submit();
//
//    return tmp_form;
//}

//window.onload = function () {
//    var anchors = document.querySelectorAll(".p-operate a");
//
//    for (var i = 0; i < anchors.length; i ++) {
//        anchors[i].addEventListener('click', function () {
//            var that = anchors[i];
//            test(that);
//        })
//    }
//};

/**
 * 将商品加入购物车
 * @param anchor 点击的锚元素
 */
function addCart(anchor) {
    var goods_id = anchor.getAttribute("data-sku"); // 商品ID
    var url = "addCart.do?goodsID=" + goods_id;

    ajaxAddCart(url);

}

function ajaxAddCart(url) {

    var httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = (function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            // 加载成功后
            alert("添加成功！");
        }
    });

    httpRequest.open("GET", url, true);
    httpRequest.send();
}