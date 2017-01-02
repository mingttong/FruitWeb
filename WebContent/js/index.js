/**
 * Created by lenovo on 2017/1/2.
 */

function addCart(btn) {

    var goods_id = btn.getAttribute("data-sku"); // 商品ID
    var url = "addCart.do";
    var dataToPost = {
        goodsID : goods_id
    };

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
    document.body.removeChild(tmp_form);

    return tmp_form;
}